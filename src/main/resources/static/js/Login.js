class Login {
    loginSuccessValue;
    constructor() {
        // this.self = this;
        this.alertDisplayed = true;
    }
    loadInit = async () => {
        await this.handlerSweetAlert();
    }
    handlerSweetAlert = () => {
        var loginSuccess = document.cookie.split(';');
        var userName = $("#user_name");
        for (let i = 0; i < loginSuccess.length; i++) {
            const cookie = loginSuccess [i].trim();
            if (cookie.startsWith("loginSuccess=")) {
                this.loginSuccessValue = cookie.substring("loginSuccess=".length);
                break;
            }
        }
        if (this.loginSuccessValue === "true") {
            swal.fire({
                title: "Success!",
                text: "Login is successfuly",
                icon: "success",
                confirmButtonText: "OK"
            })
            sessionStorage.setItem("url","")
            sessionStorage.setItem("user_name",userName.text());//bắt tên người dùng cho mỗi lần đẳng nhập
            document.cookie = "loginSuccess=false ; max-age=" + (60) + ";path=/";
        }
    }
}
const services = new Login();
$(document).ready(async function () {
    services.loadInit();

    if(localStorage.getItem(sessionStorage.getItem("user_name")) == null){
        $('#soluong').text(0);
    }else {
        var arrayAlredyInWeb = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
        $('#soluong').text(arrayAlredyInWeb.length)
    }
})
