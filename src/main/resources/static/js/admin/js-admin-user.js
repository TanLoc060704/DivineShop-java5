$(document).ready(function () {
    const pageSize = 10; // Số lượng người dùng trên mỗi trang
    const maxPagesToShow = 3; // Số trang tối đa hiển thị cùng lúc

    const getAllUser = async (searchQuery = '', roleFilter = '', page = 1) => {
        let listUserContainer = $('#listUserContainer');
        let paginationContainer = $('#paginationContainer');
        let soluongItem = $('#soluongItem');
        let tongItem = $('#tongItem');
        await axios.get('/api/public/getAllUser')
            .then(response => {
                listUserContainer.html('');
                paginationContainer.html('');
                console.log(response.data)
                let responseData = response.data.data;

                // Lưu thông tin người dùng đầu tiên vào sessionStorage
                if (responseData.length > 0) {
                    sessionStorage.setItem('username', responseData[0].tenDangNhap);
                    sessionStorage.setItem('role', responseData[0].roles[0].role);
                    sessionStorage.setItem('idRole', responseData[0].roles[0].idRole);
                }

                // Lọc dữ liệu theo vai trò và từ khóa tìm kiếm nếu có
                responseData = responseData.filter(user => {
                    if (roleFilter && !user.roles.some(role => role.role === roleFilter)) {
                        return false;
                    }
                    if (searchQuery && !user.tenDangNhap.toLowerCase().includes(searchQuery.toLowerCase())) {
                        return false;
                    }
                    return true;
                });

                // Tính toán số lượng trang
                let totalUsers = responseData.length;
                tongItem.text(totalUsers);
                let paginatedUsers = [];
                let userCount = 0;

                for (let i = 0; i < totalUsers; i++) {
                    let user = responseData[i];
                    let roles = user.roles;

                    for (let j = 0; j < roles.length; j++) {
                        if (userCount < page * pageSize && userCount >= (page - 1) * pageSize) {
                            paginatedUsers.push({
                                user,
                                role: roles[j]
                            });
                        }
                        userCount++;
                    }
                }

                let totalPages = Math.ceil(userCount / pageSize);
                soluongItem.text(paginatedUsers.length);

                if (paginatedUsers.length === 0) {
                    listUserContainer.html('<tr><td colspan="5" class="text-center">No users found</td></tr>');
                } else {
                    $.each(paginatedUsers, function (index, data) {
                        let user = data.user;
                        let role = data.role;
                        let formattedDate = moment(user.ngayThamGia).format('DD-MM-YYYY');
                        let html = `
                            <tr>
                                <th scope="row">${user.sysIdUser}</th>
                                <td>
                                    <div class="d-flex align-items-center gap-2">
                                        <div class="">
                                            <img src="/img-user/${user.anhDaiDien}" width="35px" height="35px" alt="">
                                        </div>
                                        <div class="">
                                            <p class="m-0 fw-semibold">${user.tenDangNhap}</p>
                                            <p class="m-0 text-secondary">${role.role}</p>
                                        </div>
                                    </div>
                                </td>
                                <td>${user.email}</td>
                                <td>${formattedDate}</td>
                                <td>
                                    <div class="dropdown">
                                        <a class="btn btn-secondary btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa-solid fa-ellipsis"></i>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a class="btn dropdown-item viewAndEdit" data-username="${user.tenDangNhap}" data-role="${role.role}" data-idrole="${role.idRole}"><i class="fa-solid fa-eye"></i> View & Edit</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>`;
                        listUserContainer.append(html);
                    });

                    // Hiển thị nút "Back" và "Next"
                    paginationContainer.append(`<li class="page-item ${page === 1 ? 'disabled' : ''}"><a class="page-link" href="#" data-page="${page - 1}">&laquo;</a></li>`);
                    let startPage = Math.max(1, page - Math.floor(maxPagesToShow / 2));
                    let endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

                    if (endPage - startPage + 1 < maxPagesToShow) {
                        startPage = Math.max(1, endPage - maxPagesToShow + 1);
                    }

                    for (let i = startPage; i <= endPage; i++) {
                        if (i === page) {
                            paginationContainer.append(`<li class="page-item active"><span class="page-link">${i}</span></li>`);
                        } else {
                            paginationContainer.append(`<li class="page-item"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`);
                        }
                    }
                    paginationContainer.append(`<li class="page-item ${page === totalPages ? 'disabled' : ''}"><a class="page-link" href="#" data-page="${page + 1}">&raquo;</a></li>`);
                }
            })
            .catch(error => {
                alert(error);
            });
    };

    getAllUser();

    // Thêm người dùng
    const saveUser = async () => {
        let username = $('#inputUsername').val();
        let email = $('#inputEmail').val();
        let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (username === '' || email === '') {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Please fill in all fields"
            });
            return;
        } else if (!emailPattern.test(email)) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Please enter a valid email address"
            });
            return;
        }
        await axios.post('/api/public/saveAccount', {
            username: username,
            email: email
        })
            .then(response => {
                let responseData = response.data;
                console.log(responseData);
                if (responseData.message === 'Username already exists') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Username already exists"
                    });
                } else if (responseData.message === 'Email already exists') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Email already exists"
                    });
                } else if (responseData.message === 'Call Api Successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Add User Success",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    getAllUser();
                }
            })
            .catch(error => {
                console.log(error);
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Something went wrong!"
                });
            });
    };

    $('#submitAddUser').click(function () {
        saveUser();
    });

    $(document).on('click', '.viewAndEdit', function () {
        let username = $(this).data('username');
        let role = $(this).data('role');
        let idRole = $(this).data('idrole');
        console.log(username, role);
        sessionStorage.setItem('username', username);
        sessionStorage.setItem('role', role);
        sessionStorage.setItem('idRole', idRole);
        window.location.href = '/admin/account-view';
    });

    $('#filterButton').click(function () {
        let searchQuery = $('#searchInput').val();
        let roleFilter = $('#roleSelect').val();
        getAllUser(searchQuery, roleFilter);
    });

    $('#searchInput').on('keyup', function (e) {
        if (e.key === 'Enter' || e.keyCode === 13) {
            let searchQuery = $('#searchInput').val();
            let roleFilter = $('#roleSelect').val();
            getAllUser(searchQuery, roleFilter);
        }
    });

    $('#roleSelect').on('change', function () {
        let searchQuery = $('#searchInput').val();
        let roleFilter = $('#roleSelect').val();
        getAllUser(searchQuery, roleFilter);
    });

    $(document).on('click', '.pagination .page-link', function (e) {
        e.preventDefault();
        let page = $(this).data('page');
        if (page) {
            let searchQuery = $('#searchInput').val();
            let roleFilter = $('#roleSelect').val();
            getAllUser(searchQuery, roleFilter, page);
        }
    });
});