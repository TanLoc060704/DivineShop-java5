$(document).ready(function () {

    const forgotPW = async () => {
        let email = $('#email').val();
        let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();
        if (email === '' || password === '' || confirmPassword === '') {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng nhập đầy đủ thông tin"
            });
            return;
        } else if (!emailPattern.test(email)) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng nhập email đúng định dạng"
            });
            return;
        } else if (confirmPassword !== password) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng nhập xác nhận mật khẩu chính xác"
            });
            return;
        }
        await axios.post('/api/public/forgotPW', {
            email: email,
            hashedPassword: password
        })
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Email does not exist') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Email không tồn tại"
                    });
                    return;
                } else if (responseData.message === 'Call Api Successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Vui lòng xác nhận otp được gửi vào email để đổi mật khẩu\n" +
                            "Lưu ý thời hạn của otp là 1 phút",
                        showConfirmButton: true,
                        timer: 7000
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = '/confirm-otp-forgot-pw';
                        }
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = '/confirm-otp-forgot-pw';
                        }
                    })
                    setTimeout(() => {
                        window.location.href = '/confirm-otp-forgot-pw';
                    }, 7000);
                }
            })
    }

    $('#btnSendMailForUser').click(() => {
        forgotPW();
    });
    // $(document).on('keypress', function (event) {
    //     if (event.key === 'Enter') {
    //         forgotPW();
    //     }
    // });
    $('.form-control').on('keypress', function (event) {
        if (event.key === 'Enter') {
            forgotPW();
        }
    });

})