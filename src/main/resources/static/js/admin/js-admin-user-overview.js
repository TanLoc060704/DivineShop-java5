$(document).ready(function () {
    var path = null;
    var nameImg = null;
    var nameImgCurr = null;
    var nameUser = null;
    var roleChanges = [];

    function generateRandomName(length, characters) {
        const parts = characters.split('.');
        const namePart = parts[0];
        const extensionPart = parts.length > 1 ? '.' + parts[1] : '';
        let randomName = '';
        const nameLength = namePart.length;
        for (let i = 0; i < length; i++) {
            randomName += namePart.charAt(Math.floor(Math.random() * nameLength));
        }
        return randomName + extensionPart;
    }

    const getUserByUsernameAndRole = async () => {
        let username = sessionStorage.getItem('username');
        let userInfoContainer = $('#userInfoContainer');
        await axios.get('/api/public/getUserByUsername', {
            params: {username: username}
        })
            .then(response => {
                userInfoContainer.html('');
                console.log(response.data);
                let responseData = response.data.data;
                let formattedDate = moment(responseData.ngayThamGia).format('DD-MM-YYYY');
                nameUser = responseData.hoVaTen;
                nameImgCurr = responseData.anhDaiDien;
                nameImg = responseData.anhDaiDien;
                let rolesHtml = responseData.roles.map(role => role.role).join(', ');
                let html = `
            <div class="px-3 py-4">
                <h4>Overview</h4>
                <div class="row mt-3">
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Tên đăng nhập</p>
                        <p style="font-size: 0.85rem;" class="fw-bold">${responseData.tenDangNhap}</p>
                    </div>
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Email</p>
                        <p style="font-size: 0.85rem;" class="fw-bold">${responseData.email}</p>
                    </div>
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Họ và tên</p>
                        <p style="font-size: 0.85rem;" class="fw-bold">${responseData.hoVaTen === null ? '' : responseData.hoVaTen}</p>
                    </div>
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Ngày tham gia</p>
                        <p style="font-size: 0.85rem;" class="fw-bold">${formattedDate}</p>
                    </div>
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Roles</p>
                        <p style="font-size: 0.85rem;" class="fw-bold">${rolesHtml}</p>
                    </div>
                </div>
                <hr>
                <form action="">
                    <div class="row">
                        <div class="col-5">
                            <div class="d-flex align-items-center gap-3">
                                <img src="/img-user/${responseData.anhDaiDien}" width="154px" height="154px" alt="" id="previewImage">
                                    <div class="text-center">
                                        <input type="file" class="form-control" id="fileInput">
                                        <label class="custom-file-label" for="fileInput">Select photos from local</label>
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
                                <input type="text" class="form-control" id="inputHoVaTen" value="${responseData.hoVaTen === null ? '' : responseData.hoVaTen}" placeholder="Full name">
                                <label>Full name</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" value="${responseData.email}" placeholder="Email" disabled>
                                <label>Email</label>
                            </div>
                            <label>Role</label>
                            <div class="form-floating mb-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="ROLE_ADMIN" id="roleAdmin" name="roleCheckbox" ${responseData.roles.some(role => role.role === 'ROLE_ADMIN') ? 'checked' : ''}>
                                    <label class="form-check-label" for="roleAdmin">ROLE_ADMIN</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="ROLE_MANAGER" id="roleManager" name="roleCheckbox" ${responseData.roles.some(role => role.role === 'ROLE_MANAGER') ? 'checked' : ''}>
                                    <label class="form-check-label" for="roleManager">ROLE_MANAGER</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="ROLE_EMPLOYEE" id="roleEmployee" name="roleCheckbox" ${responseData.roles.some(role => role.role === 'ROLE_EMPLOYEE') ? 'checked' : ''}>
                                    <label class="form-check-label" for="roleEmployee">ROLE_EMPLOYEE</label>
                                </div>
                            </div>
                            <button class="btn btn-primary" id="updateUser" type="button">Save change</button>
                        </div>
                    </div>
                </form>
            </div>
                `;
                userInfoContainer.append(html);

                document.getElementById('fileInput').addEventListener('change', function () {
                    var file = this.files[0];
                    var fileType = file['type'];
                    var validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
                    if (!validImageTypes.includes(fileType)) {
                        Swal.fire({
                            icon: "error",
                            title: "Lỗi",
                            text: "Chỉ được chọn tệp hình ảnh (gif, jpeg, png)"
                        });
                        this.value = "";
                        return;
                    }
                    if (file.size > 5 * 1024 * 1024) {
                        Swal.fire({
                            icon: "error",
                            title: "Lỗi",
                            text: "Kích thước ảnh quá lớn, vui lòng chọn ảnh nhỏ hơn 5MB"
                        });
                        this.value = "";
                        return;
                    }
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

                $('#roleAdmin').change(function () {
                    handleCheckboxChange($(this));
                });

                // Gán sự kiện cho checkbox ROLE_MANAGER
                $('#roleManager').change(function () {
                    handleCheckboxChange($(this));
                });

                // Gán sự kiện cho checkbox ROLE_EMPLOYEE
                $('#roleEmployee').change(function () {
                    handleCheckboxChange($(this));
                });

            })
            .catch(error => {
                alert(error);
            })
    }

    function handleCheckboxChange(checkbox) {
        let role = checkbox.val();
        let username = sessionStorage.getItem('username');
        let existingIndex = roleChanges.findIndex(change => change.username === username && change.role === role);

        if (existingIndex === -1) {
            // Nếu không tìm thấy sự thay đổi trong mảng, thêm vào mảng
            roleChanges.push({username: username, role: role});
        } else {
            // Nếu đã có sự thay đổi trong mảng, loại bỏ khỏi mảng
            roleChanges.splice(existingIndex, 1);
        }

        console.log("roleChanges:", roleChanges);
    }

    getUserByUsernameAndRole();

    const updateUser = async () => {
        let hoVaTen = $('#inputHoVaTen').val();
        let username = sessionStorage.getItem('username');
        let avatar = null;
        if (nameImg && path) {
            let result = generateRandomName(10, nameImg);
            avatar = result + "," + path;
        }
        if (hoVaTen === '') {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Please fill in all fields"
            });
            return;
        }
        if (hoVaTen !== nameUser || nameImg !== nameImgCurr || avatar !== null) {
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
        // Cập nhật vai trò nếu có thay đổi
        if (roleChanges.length > 0) {
            await axios.post('/api/public/updateRole', roleChanges)
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
                    } else {
                        Swal.fire({
                            icon: "error",
                            title: "Oops...",
                            text: responseData.message
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

        // Reset lại mảng roleChanges sau khi cập nhật
        roleChanges = [];
    }
    $(document).on('click', '#updateUser', async function () {
        await updateUser();
        getUserByUsernameAndRole();
    });

})