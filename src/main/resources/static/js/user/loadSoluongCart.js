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
            }).then((result) => {
                var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
                for (var i = 0; i < objArray.length; i++) {
                    objArray = []; // Hoặc đặt giá trị mặc định mà bạn muốn
                }
                localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray));
                window.location.href = "/";
            });
        }
    });
})