$('#btnConFirmOtp').click(function(){
    var otp1 = $('#otp1').val();
    var otp2 = $('#otp2').val();
    var otp3 = $('#otp3').val();
    var otp4 = $('#otp4').val();

    var OTP = otp1 + otp2 + otp3 + otp4;
      axios.post("/api/public/verify-otp/"+OTP)
        .then(response => {
            console.log(OTP)
            if (response.data.success) {
                console.log('Thành công:', response.data.message);
                // Xử lý dữ liệu trả về từ API
                console.log('Data:', response.data.data);
                window.location.href = "/log-in";
            } else {
                alert("saiotp")
                console.log('Thất bại:', response.data.message);
            }
        })
        .catch(error => {
            alert(error);
        })
});