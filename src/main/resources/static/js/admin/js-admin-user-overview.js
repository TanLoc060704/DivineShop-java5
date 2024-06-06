$(document).ready(function () {
    var path = null;
    var nameImg = null;
    var nameImgCurr = null;
    var nameUser = null;

    function generateRandomName(length, characters) {
        // tách phần tên và phần mở rộng của tệp
        const parts = characters.split('.');
        const namePart = parts[0];
        const extensionPart = parts.length > 1 ? '.' + parts[1] : '';
        // tạo chuỗi ngẫu nhiên từ phần tên
        let randomName = '';
        const nameLength = namePart.length;
        for (let i = 0; i < length; i++) {
            randomName += namePart.charAt(Math.floor(Math.random() * nameLength));
        }
        // ghép chuỗi ngẫu nhiên với phần mở rộng
        return randomName + extensionPart;
    }

    const getUserByUsernameAndRole = async () => {
        // Lấy giá trị từ session storage
        let username = sessionStorage.getItem('username');
        let role = sessionStorage.getItem('role');
        let userInfoContainer = $('#userInfoContainer');
        await axios.get('/api/public/getUserByUsernameAndRole', {
            params: {
                username: username,
                role: role
            }
        })
            .then(response => {
                userInfoContainer.html('');
                console.log(response.data)
                let responseData = response.data.data;
                let formattedDate = moment(responseData.ngayThamGia).format('DD-MM-YYYY');
                nameUser = responseData.hoVaTen;
                nameImgCurr = responseData.anhDaiDien;
                nameImg = responseData.anhDaiDien;

                let html = `
                <div class="px-3 py-4">
                    <h4>Overview</h4>
                    <div class="row mt-3">
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Tên đăng nhập</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${responseData.tenDangNhap}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Email</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${responseData.email}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Họ và tên</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${responseData.hoVaTen === null ? '' : responseData.hoVaTen}</p>
                        </div>
<!--                        <div class="col-3">-->
<!--                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Nhóm khách hàng</p>-->
<!--                            <p style="font-size: 0.85rem;"  class="fw-bold"><i class="fa-solid fa-gem"></i> Vip Bạc</p>-->
<!--                        </div>-->
<!--                        <div class="col-3">-->
<!--                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Số dư</p>-->
<!--                            <p style="font-size: 0.85rem;"  class="fw-bold">4.910đ</p>-->
<!--                        </div>-->
<!--                        <div class="col-3">-->
<!--                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Đã tích lũy</p>-->
<!--                            <p style="font-size: 0.85rem;"  class="fw-bold">4.857.000đ</p>-->
<!--                        </div>-->
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Ngày tham gia</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${formattedDate}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Role</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${role}</p>
                        </div>
                    </div>
                    <hr>
                    <form action="">
                        <div class="row">
                            <div class="col-5">
                                <div class="d-flex align-items-center gap-3">
                                    <img src="/img-user/${responseData.anhDaiDien}" width="154px" height="154px" alt="" id="previewImage">
                                        <div class="text-center">
                                            <input type="file" class="form-control" id="fileInput" >
                                            <label class="custom-file-label" for="fileInput">Select photos from
                                                local</label>
                                        </div>
                                </div>
                            </div>
                            <div class="col-1"><div class="vr h-100"></div></div>
                            <div class="col-6">
                                <div class="d-flex align-items-center h-100">
                                    <div>
                                        <p style="font-size: 0.87rem;">Vui lòng chọn ảnh nhỏ hơn 5MB</p>
                                        <p class="m-0" style="font-size: 0.87rem;">Chọn hình ảnh phù hợp, không phản cảm</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-5">
                                <h4>Cá nhân</h4>
                                <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="inputHoVaTen" value="${responseData.hoVaTen === null ? '' : responseData.hoVaTen}"  placeholder="Full name">
                                    <label>Full name</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control"  value="${responseData.email}" placeholder="Email" disabled>
                                    <label>Email</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <select class="form-select" aria-label="Floating label select example" id="selectRole">
                                        <option value="ROLE_ADMIN" ${role === 'ROLE_ADMIN' ? 'selected' : ''}>ROLE_ADMIN</option>
                                        <option value="ROLE_MANAGER" ${role === 'ROLE_MANAGER' ? 'selected' : ''}>ROLE_MANAGER</option>
                                        <option value="ROLE_EMPLOYEE" ${role === 'ROLE_EMPLOYEE' ? 'selected' : ''}>ROLE_EMPLOYEE</option>
                                    </select>
                                    <label>Role</label>
                                </div>
                                <button class="btn btn-primary" id="updateUser" type="button">Save change</button>
                            </div>
                        </div>
                    </form>
                </div>
                
                    `
                userInfoContainer.append(html);

                // sự kiện khi chọn hình ở input thì sẽ thay đổi img
                document.getElementById('fileInput').addEventListener('change', function () {
                    var file = this.files[0];
                    nameImg = file.name;
                    if (file) {
                        var reader = new FileReader();
                        reader.onload = function (event) {
                            path = event.target.result;
                            document.getElementById('previewImage').setAttribute('src', event.target.result);
                            document.getElementById('previewImage').style.display = 'block';
                        }
                        reader.readAsDataURL(file);
                    }
                });

            })
            .catch(error => {
                alert(error);
            })
    }
    getUserByUsernameAndRole();
    const updateUser = async () => {
        let hoVaTen = $('#inputHoVaTen').val();
        let role = $('#selectRole').val();
        let username = sessionStorage.getItem('username');
        let idRole = sessionStorage.getItem('idRole');
        let avatar = null;
        if (nameImg && path) {
            let result = generateRandomName(10, nameImg);
            avatar = result + "," + path;
        }
        if (hoVaTen === '' || role === '') {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Please fill in all fields"
            });
            return;
        }
        if (hoVaTen !== nameUser || nameImg !== nameImgCurr) {
            await axios.post('/api/public/updateUserByTenDangNhap', {
                hoVaTen: hoVaTen,
                anhDaiDien: avatar,
                tenDangNhap: username
            })
                .then(response => {
                    let responseData = response.data;
                    console.log(responseData);
                    if (responseData.message === 'Call Api Successfully') {
                        Swal.fire({
                            icon: "success",
                            title: "Update Success",
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })
                .catch(error => {
                    console.log(error);
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Something went wrong!",
                        timer: 1500
                    });
                })
        }
        let roleSession = sessionStorage.getItem('role');
        if (role !== roleSession) {
            await axios.post('/api/public/updateRoleByIdRole', {
                username: username,
                role: role,
                idRole: idRole
            })
                .then(response => {
                    let responseData = response.data;
                    console.log(responseData);
                    if (responseData.message === 'Username already has this role') {
                        Swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: "Username already has this role"
                        });
                    } else if (responseData.message === 'Call Api Successfully') {
                        Swal.fire({
                            icon: "success",
                            title: "Update Success",
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                })
                .catch(error => {
                    console.log(error);
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Something went wrong!"
                    });
                })
        }

    }
    $(document).on('click', '#updateUser', async function () {
        await updateUser();
        getUserByUsernameAndRole();
    });

})