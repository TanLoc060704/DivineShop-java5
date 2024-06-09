$(document).ready(function () {

    const saveAccountByUser = async () => {
        let username = $('#username').val();
        let email = $('#email').val();
        let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();
        if (username === '' || email === '' || password === '' || confirmPassword === '') {
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
        await axios.post('/api/public/saveAccountByUser', {
            username: username,
            email: email,
            hashedPassword: password
        })
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Username already exists') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Username already exists"
                    });
                    return;
                } else if (responseData.message === 'Email already exists') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Email already exists"
                    });
                    return;
                } else if (responseData.message === 'Call Api Successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Vui lòng xác nhận otp được gửi vào email để tạo tài khoản\n" +
                            "<p style=\"color: red; font-size: 0.9em; font-weight: bold;\">Lưu ý: thời hạn của otp là 1 phút</p>",
                        showConfirmButton: true,
                        timer: 7000
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = '/confirm-otp';
                        }
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = '/confirm-otp';
                        }
                    });
                    setTimeout(() => {
                        window.location.href = '/confirm-otp';
                    }, 7000);
                }
            })
    }

    $('#btnSendMailForUser').click(() => {
        saveAccountByUser();
    })
    // $(document).on('keypress', function (event) {
    //     if (event.key === 'Enter') {
    //         saveAccountByUser();
    //     }
    // });
    $('.form-control').on('keypress', function (event) {
        if (event.key === 'Enter') {
            saveAccountByUser();
        }
    });

})
