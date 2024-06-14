$(document).ready(function() {
    var checktien = $('#checknaptien').text();
    var checktien1 = $('#checkhuynaptien').text();
    sessionStorage.setItem("url","");

    if(checktien1 == "true"){
        Swal.fire({
            title: "Thông báo từ hệ thống!",
            text: "Hóa đơn của bạn đã bị hủy do yếu tố khác! Vui lòng nạp lại.",
            icon: "error",
            allowOutsideClick: false, // Ngăn không cho người dùng nhấn ra ngoài để tắt bảng
            allowEscapeKey: false // Ngăn không cho người dùng nhấn phím Escape để tắt bảng
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                sessionStorage.setItem("url","/add-funds");
                window.location.href = "/user-info";
            }
        });
    }

    if(checktien == "true"){
        Swal.fire({
            title: "Thông báo từ hệ thống!",
            text: "Đã nạp tiền thành công!",
            icon: "success",
            allowOutsideClick: false, // Ngăn không cho người dùng nhấn ra ngoài để tắt bảng
            allowEscapeKey: false // Ngăn không cho người dùng nhấn phím Escape để tắt bảng
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                window.location.href = "/user-info";
            }
        });
    }

    $('#myButton').click(function() {
        var tienThanhToan =  $('#tiennap').val();
        if(tienThanhToan == ""){
            Swal.fire({
                title: "Thông báo từ hệ thống!",
                text: "Không được để trống ô nhập tiền!",
                icon: "question"
            })
            return;
        }else if(parseFloat(tienThanhToan) < 20000){
            Swal.fire({
                title: "Thông báo từ hệ thống!",
                text: "Số tiền tối thiếu là 20 nghìn",
                icon: "question"
            })
            return;
        }else {
            axios.get('/api/v1/payment/vn-pay?amount='+tienThanhToan+'&bankCode=NCB',{
                params: {
                    name: sessionStorage.getItem("user_name")
                }
            }) // lưu vào order
                .then(response => {
                    let timerInterval;
                    Swal.fire({
                        title: "Thông báo từ hệ thống!",
                        html: "Vui lòng đợi <b></b> s.",
                        timer: 2000,
                        timerProgressBar: true,
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
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = response.data;
                        }
                    });
                })
                .catch(error => {
                    // Xử lý lỗi nếu có
                    console.error(error);
                });
        }
    })
})