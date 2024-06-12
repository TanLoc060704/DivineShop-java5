$(document).ready(function() {
    axios.get('/api/public/getUserByUserName',{
        params: {
            username: sessionStorage.getItem("user_name")
        }
    })
        .then(response => {
            var formattedtongtienPrice = parseFloat(response.data.data.soDu).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            $('#TienUser').html(formattedtongtienPrice+`<i class="fa-solid fa-plus text-primary ms-1"></i>`);
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error(error);
        });
})