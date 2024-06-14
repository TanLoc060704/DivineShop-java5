$(document).ready(function() {
    let timerInterval;
    Swal.fire({
        title: "Thông báo đơn hàng",
        html: "Vui lòng chờ trong <b></b> s. để thanh toán",
        timer: 3000,
        timerProgressBar: true,
        allowOutsideClick: false, // Ngăn không cho người dùng nhấn ra ngoài để tắt bảng
        allowEscapeKey: false, // Ngăn không cho người dùng nhấn phím Escape để tắt bảng
        didOpen: () => {
            Swal.showLoading();
            const timer = Swal.getPopup().querySelector("b");
            timerInterval = setInterval(() => {
                timer.textContent = `${Swal.getTimerLeft()}`;
            }, 100);
        },
        willClose: () => {
            clearInterval(timerInterval);
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            Swal.fire({
                title: "Thông báo đơn hàng",
                text: "Đơn hàng của bạn đã được thanh toán.",
                icon: "success",
                allowOutsideClick: false, // Ngăn không cho người dùng nhấn ra ngoài để tắt bảng
                allowEscapeKey: false // Ngăn không cho người dùng nhấn phím Escape để tắt bảng
            }).then(async (result) => {

                const min = 1;
                const max = 1000000;
                const randomNumber = Math.floor(Math.random() * (max - min + 1)) + min;

                const currentDate = new Date();
                const year = currentDate.getFullYear();
                const month = String(currentDate.getMonth() + 1).padStart(2, '0'); // Thêm số 0 phía trước nếu cần
                const day = String(currentDate.getDate()).padStart(2, '0'); // Thêm số 0 phía trước nếu cần


                var maDonHang = randomNumber+"";
                const ngayLapDon = `${year}-${month}-${day}`;
                var tongTienThanhToan = 0;
                var soLuongMua = 0;
                var tenDangNhap = sessionStorage.getItem("user_name");

                var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
                for (var i = 0; i < objArray.length; i++) {
                    if(objArray[i].giadagiam == null){
                        tongTienThanhToan += parseFloat(objArray[i].giasanphamgoc) * parseFloat(objArray[i].soluong);
                        soLuongMua += parseInt(objArray[i].soluong);
                    }else {
                        tongTienThanhToan += parseFloat(objArray[i].giadagiam) * parseFloat(objArray[i].soluong);
                        soLuongMua += parseInt(objArray[i].soluong);
                    }
                }

                var donHang = {
                    maDonHang: maDonHang,
                    ngayLapDon: ngayLapDon,
                    trangThaiThanhToan: true,
                    tongTienThanhToan: parseFloat(tongTienThanhToan),
                    tienThanhToan: (sessionStorage.getItem("tiengiam") == "" ? parseFloat(tongTienThanhToan) : parseFloat(sessionStorage.getItem("tiengiam"))),
                    soLuongMua: parseInt(soLuongMua),
                    productE: {
                        id: null
                    },
                    userE: {
                        tenDangNhap: tenDangNhap
                    },
                    listProduct : objArray
                }

                var objUser ;
                var tienDaThanhToan;
                axios.get('/api/public/getUserByUserName',{ // lấy info user đó với tenDangNhap
                    params: {
                        username: tenDangNhap
                    }
                })
                    .then(response => {
                        objUser = response.data.data;
                        objUser.soDu = (parseFloat(objUser.soDu) - parseFloat(tongTienThanhToan)); // thực hiện tính toán tiền
                        tienDaThanhToan = objUser.soDu;

                        axios.post('/api/public/updateUser', null, {
                            params: {
                                soTien: tienDaThanhToan+"",
                                tenDangNhap: tenDangNhap
                            }
                        })
                            .then(response => {

                                axios.post('/api/oder',donHang) // lưu vào order
                                    .then(response => {

                                        //xử lý trừ tiền người dùng
                                        objArray = [];
                                        localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray));
                                        window.location.href = "/";
                                    })
                                    .catch(error => {
                                        // Xử lý lỗi nếu có
                                        console.error(error);
                                    });

                            })
                            .catch(error => {
                                // Xử lý lỗi nếu có
                                console.error(error);
                            });

                    })
                    .catch(error => {
                        // Xử lý lỗi nếu có
                        console.error(error);
                    });

            });
        }
    });
})