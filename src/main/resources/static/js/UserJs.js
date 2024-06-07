$(document).ready(async function(){
    let fullNameRegex = /^[a-zA-Z\s]*$/;
    let usernameRegex = /^\w{3,16}$/;
    let emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    let passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    let phoneNumberRegex = /^\d{10,11}$/; // Optional
    let referralCodeRegex = /^[a-zA-Z0-9]{6,10}$/; // Optional

    let fullName =  $('#fullName').val();
    let username =  $('#username').val();
    let email =  $('#email').val();
    let password = $('#password').val();
    let confirmPassword = $('#confirmPassword').val();
    let phoneNumber = $('#phoneNumber').val(); // Optional
    let referralCode = $('#referralCode').val(); // Optional

    //TruongAnDev - Đoạn này thông báo alert khi nhập sai dữ liệu, nếu muốn sử dụng thì bỏ comment
    // if (!fullNameRegex.test(fullName)) {
    //     alert('Invalid full name');
    //     return;
    // }
    //
    // if (!usernameRegex.test(username)) {
    //     alert('Invalid username');
    //     return;
    // }
    //
    // if (!emailRegex.test(email)) {
    //     alert('Invalid email');
    //     return;
    // }
    //
    // if (!passwordRegex.test(password)) {
    //     alert('Invalid password');
    //     return;
    // }
    //
    // if (password !== confirmPassword) {
    //     alert('Passwords do not match');
    //     return;
    // }
    //
    // if (phoneNumber && !phoneNumberRegex.test(phoneNumber)) {
    //     alert('Invalid phone number');
    //     return;
    // }
    //
    // if (referralCode && !referralCodeRegex.test(referralCode)) {
    //     alert('Invalid referral code');
    //     return;
    // }
    $('input[name="username"]').focusout(function() {
    if (!usernameRegex.test($(this).val())) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

$('input[name="email"]').focusout(function() {
    if (!emailRegex.test($(this).val())) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

$('input[name="password"]').focusout(function() {
    if (!passwordRegex.test($(this).val())) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

$('input[name="confirmPassword"]').focusout(function() {
    if ($(this).val() !== $('input[name="password"]').val()) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

$('input[name="phoneNumber"]').focusout(function() {
    if ($(this).val() && !phoneNumberRegex.test($(this).val())) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

$('input[name="referralCode"]').focusout(function() {
    if ($(this).val() && !referralCodeRegex.test($(this).val())) {
        $(this).next('.error').show();
    } else {
        $(this).next('.error').hide();
    }
});

    // let hashedPassword = hashFunction(password); // Replace this with your actual hashing function

    const sendEmailForUser = async (usernameSuccess, emailSuccess,passwordSuccess) => {


        await axios
        .post("/api/public/send-email-for-user",{
            "username": usernameSuccess,
            "email": emailSuccess,
            "hashedPassword": passwordSuccess
        })
        .then(response => {
            console.log('Response:', response.data);
            if (response.data.success) {
                console.log('Thành công:', response.data.message);
                // Xử lý dữ liệu trả về từ API
                console.log('Data:', response.data.data);
                window.location.href = "/confirm-otp";
            } else {
                console.log('Thất bại:', response.data.message);
            }
        })
        .catch(error => {
            alert(error);
        })
}
    $('#btnConFirmOtp').click(()=>{
        confirmOtp()
    })
    $('#btnSendMailForUser').click(() => {
    let fullName = $('input[name="fullName"]').val();
    let username = $('input[name="username"]').val();
    let email = $('input[name="email"]').val();
    let password = $('input[name="password"]').val();
    let confirmPassword = $('input[name="confirmPassword"]').val();
    // let phoneNumber = $('input[name="phoneNumber"]').val(); // Optional
    // let referralCode = $('input[name="referralCode"]').val(); // Optional



        $('input[name="fullName"]').focusout(function() {
            if (!fullNameRegex.test($(this).val())) {
                // Show error for this field
                $(this).next('.error').show();
            } else {
                // Hide error for this field
                $(this).next('.error').hide();
            }
        });
    if (!fullNameRegex.test(fullName)) {
        alert('Invalid full name');
        return;
    }

    if (!usernameRegex.test(username)) {
        alert('Invalid username');
        return;
    }

    if (!emailRegex.test(email)) {
        alert('Invalid email');
        return;
    }

    if (!passwordRegex.test(password)) {
        alert('Invalid password');
        return;
    }

    if (password !== confirmPassword) {
        alert('Passwords do not match');
        return;
    }

    if (phoneNumber && !phoneNumberRegex.test(phoneNumber)) {
        alert('Invalid phone number');
        return;
    }

    if (referralCode && !referralCodeRegex.test(referralCode)) {
        alert('Invalid referral code');
        return;
    }

        let usernameSuccess =  $('#username').val();
        let emailSuccess =  $('#email').val();
        let passwordSuccess = $('#password').val();
        sendEmailForUser(usernameSuccess, emailSuccess, passwordSuccess);

});
})