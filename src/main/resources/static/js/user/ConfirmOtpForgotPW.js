$(document).ready(function () {
    $('.otp-input').on('input', function () {
        const currentInput = $(this);
        if (currentInput.val().length === 1) {
            const nextInput = currentInput.next('.otp-input');
            if (nextInput.length) {
                nextInput.focus();
            }
        }
    });

    $('.otp-input').on('keydown', function (event) {
        const currentInput = $(this);
        if (event.key === 'Backspace' && currentInput.val().length === 0) {
            const prevInput = currentInput.prev('.otp-input');
            if (prevInput.length) {
                prevInput.focus();
            }
        }
    });

    const verifyOTP = async () => {
        let otp1 = $('#otp1').val();
        let otp2 = $('#otp2').val();
        let otp3 = $('#otp3').val();
        let otp4 = $('#otp4').val();
        let otp5 = $('#otp5').val();
        let otp6 = $('#otp6').val();
        if (isNaN(otp1) || isNaN(otp2) || isNaN(otp3) || isNaN(otp4) || isNaN(otp5) || isNaN(otp6)) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Mã OTP phải là số"
            });
            return;
        }
        if (!otp1 || !otp2 || !otp3 || !otp4 || !otp5 || !otp6) {
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Vui lòng nhập đầy đủ otp"
            });
            return;
        }
        let otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        await axios.post('/api/public/verify-otp-change-pw/' + otp)
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Otp does not exist') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Otp không tồn tại"
                    });
                    return;
                }
                if (responseData.message === 'OTP đã hết hạn') {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Otp đã hết hạn"
                    });
                    return;
                }
                if (responseData.message === 'Call Api Successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Đổi mật khẩu thành công",
                        showConfirmButton: true,
                        timer: 5000
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = '/log-in';
                        }
                        /* Read more about handling dismissals below */
                        if (result.dismiss === Swal.DismissReason.timer) {
                            window.location.href = '/log-in';
                        }
                    });
                    setTimeout(() => {
                        window.location.href = '/log-in';
                    }, 5000);
                }

            })
    }

    $('#btnConFirmOtp').click(() => {
        verifyOTP();
    })
    $('.otp-input').on('keypress', function (event) {
        if (event.key === 'Enter') {
            verifyOTP();
        }
    });

    const resendOtp = async () => {
        await axios.post('/api/public/resendOtpForgotPW')
            .then(response => {
                let responseData = response.data;
                if (responseData.message === 'Call Api Successfully') {
                    Swal.fire({
                        icon: "success",
                        title: "Mã otp đã được gửi lại vào email của bạn",
                        timer: 2000
                    });
                }
            })
    }

    $('#btnResendOtp').click(() => {
        resendOtp();
    })
})