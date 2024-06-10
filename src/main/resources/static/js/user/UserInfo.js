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

    const getUserByUsername = async () => {
        let username = sessionStorage.getItem("user_name");
        let userInfoContainer = $('#userInfoContainer');
        await axios.get("/api/public/getUserByUsername", {
            params: {
                username: username
            }
        })
            .then(response => {
                userInfoContainer.empty();
                let u = response.data.data;
                nameUser = u.hoVaTen;
                nameImgCurr = u.anhDaiDien;
                nameImg = u.anhDaiDien;
                let formattedDate = moment(u.ngayThamGia).format('DD-MM-YYYY');
                let html =
                    `
                    <div class="px-3 py-4">
                    <h4>Tổng quan</h4>
                    <div class="row mt-3">
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Tên đăng nhập</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold" id="username">${u.tenDangNhap}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Email</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${u.email}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Họ và tên</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${u.hoVaTen === null ? '(chưa có)' : u.hoVaTen}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Số dư</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${u.soDu === null ? '(chưa có)' : u.soDu}</p>
                        </div>
                        <div class="col-3">
                            <p style="font-size: 0.85rem;" class="fw-light mb-2">Ngày tham gia</p>
                            <p style="font-size: 0.85rem;"  class="fw-bold">${formattedDate}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-5">
                            <div class="d-flex align-items-center gap-3">
                                    <img src="/img-user/${u.anhDaiDien}" width="154px" height="154px" alt="" id="previewImage">
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
                                <div class="mb-3 form-floating">
                                    <input type="text" id="inputHoVaTen" class="form-control" placeholder="Họ và tên" value="${u.hoVaTen === null ? '' : u.hoVaTen}">
                                    <label>Họ và tên</label>
                                </div>
                                <button class="btn btn-primary" type="button" id="updateUser">Lưu thay đổi</button>
                        </div>
                    </div>
                </div>
                   `;

                userInfoContainer.append(html);

                // sự kiện khi chọn hình ở input thì sẽ thay đổi img
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
                        this.value = ""; // Reset input file
                        return;
                    }
                    if (file.size > 5 * 1024 * 1024) {
                        Swal.fire({
                            icon: "error",
                            title: "Lỗi",
                            text: "Kích thước ảnh quá lớn, vui lòng chọn ảnh nhỏ hơn 5MB"
                        });
                        this.value = ""; // Reset input file
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

            })
            .catch(error => {
                console.log(error);
            })
    }
    getUserByUsername();

    const updateUser = async () => {
        let hoVaTen = $('#inputHoVaTen').val();
        let username = $('#username').text();
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
                            title: "Cập nhật thành công",
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
                        text: "Lỗi!",
                        timer: 1500
                    });
                })
        }
    }
    $(document).on('click', '#updateUser', async function () {
        await updateUser();
        getUserByUsername();
    });

})