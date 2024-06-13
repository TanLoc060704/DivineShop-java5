var tongGiaTriThanhToan;
var soDuHienTai = null;
var soTienCanNapThem ;
var giohang = false

$(document).ready(function() {
    loadCart(); // load giỏ hàng khi trang web được load
    tongTienSanPham(); //load tổng tiền sản phẩm
    getMonyForUser(); //lấy danh sách user
    sessionStorage.setItem("tiengiam","")

    $('#btnGiamGia').on('click', function() {
        // Xử lý sự kiện onclick tại đây
        var maGiamGia =  $('#maGiamGia').val()
        axios.get('/api/vouchers/'+maGiamGia)
            .then(response => {
                console.log()
                var voucherPercentage = response.data.data.voucherPercentage *100;
                var tongGiaTriSanPham =  $('#tonggiatrisp1').text();
                var giaTri = parseFloat(tongGiaTriSanPham.split(" ")[0].replace(".", "").replace(",", "."));
                var tiengiam  = parseFloat(giaTri) / voucherPercentage ;

                var tienDaTru = giaTri - tiengiam;

                var formattedtongtienPrice = parseFloat(tienDaTru).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                $('#tonggiatrisp1').text(formattedtongtienPrice)
                $('#tonggiatrisp2').text(formattedtongtienPrice)
                $("#btnGiamGia").prop("disabled", true);

                sessionStorage.setItem("tiengiam",tienDaTru)

            }).catch(function (error) {
            Swal.fire({
                title: "Thông báo từ hệ thống",
                text: "Có vẻ như mã giảm giá bị sai?",
                icon: "question"
            });
        });
    });
})

function loadCart (){
    if (localStorage.getItem(sessionStorage.getItem("user_name")) != null){
        var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
        $('#soluong').text(objArray.length)
        $('#soluongsp').text(objArray.length)
        let productsList = $("#list_sp_cart");
        productsList.empty();

        if(objArray.length == 0){
            $('#tonggiatrisp1').text(0);
            $('#tonggiatrisp2').text(0);
            giohang = true;
        }else {
            giohang = false;
        }

        objArray.forEach(function (obj,index){

            let formattedOriginalPrice = parseFloat(obj.giasanphamgoc).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            let formattedDiscountedPrice = parseFloat(obj.giadagiam).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            let tongtienFormatted;

            if (obj.phantramgiam == null){
                tongtienFormatted = parseFloat(obj.giasanphamgoc).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            }else {
                tongtienFormatted = parseFloat(obj.giadagiam * obj.soluong).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            }

            let row = `
              <div class="row mt-3">
              <div class="col-4 pe-0">
                <img src="${obj.anhSp}" alt="" class="img-fluid rounded">
              </div>
              <div class="col-8" >
                <div class="row">
                  <div class="col-6">
                    <a href="#" class="nav-link fw-semibold mb-1">${obj.tensp}</a>
                    <div class="d-flex" style="font-size: 0.9rem;" >
                      <div>${obj.theloai}</div>
                    </div>
                  </div>
                  <div class="col-2 p-0">
                    <div class="input-group input-group-sm">
                      <button type="button" onclick="truSoLuongCart(${index})" class="btn btn-outline-light text-black border-light-subtle">-</button>
                      <input type="text" disabled class="form-control text-center px-1" id="soluong" value="${obj.soluong}">
                      <button type="button" onclick="congSoLuongCart(${index})" class="btn btn-outline-light text-black border-light-subtle">+</button>
                    </div>
                  </div>
                  <div class="col-4 text-end">
                    <h5 class="" id="giaGoc">${tongtienFormatted}</h5>
                    <div class="d-flex align-items-center justify-content-end gap-3">
                        ${obj.phantramgiam == null ? `` : `<p style="font-size: 0.7rem;" class="m-0 text-bg-danger fw-bold rounded p-1">-${obj.phantramgiam}%</p>`}
                        ${obj.phantramgiam == null ? `` : `<p style="font-size: 0.9rem;" class="m-0 text-decoration-line-through text-secondary">${formattedOriginalPrice}</p>`}
                    </div>
                  </div>
                </div>
                <hr class="mb-2 mt-4">
                <div class="d-flex align-items-center justify-content-between">
                  <p class="m-0" style="font-size: 0.85rem;"><i class="fa-solid fa-box"></i> Tình trạng: <span class="text-success">${obj.trangThai == 1 ? "Còn Hàng" : "Hết Hàng"}</span></p>
                  <button type="button"  onclick="deleteByIdCart(${index})" class="btn text-danger"><i class="fa-solid fa-trash-can" aria-hidden="true"></i></button>
                </div>
              </div>
            </div>
        `;
            productsList.append(row);
        })
    }else {
        localStorage.setItem(sessionStorage.getItem("user_name"));
        $('#soluong').text(0);
    }
}

function deleteByIdCart(id){
    var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
    objArray.forEach(function (obj, index){
        if (index == id){
            objArray.splice(index,1);
            localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray)); //đẩy vào localStorage đó
            return;
        }
    })
    $('#tonggiatrisp1').text(0);
    $('#tonggiatrisp2').text(0);
    loadCart();
    tongTienSanPham();
}

function truSoLuongCart(id){
    var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
    var flag = false;
    objArray.forEach(function (obj, index){
        if (index == id){
            if (obj.soluong <= 1){
                objArray.splice(index,1);
                localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray)); //đẩy vào localStorage đó
                $('#tonggiatrisp1').text(0);
                $('#tonggiatrisp2').text(0);
                return;
            }

            obj.soluong--;
            localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray)); //đẩy vào localStorage đó
            flag = true;
            return;
        }
    })
    loadCart();
    tongTienSanPham();
}

function congSoLuongCart(id){
    var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
    var flag = false;
    objArray.forEach(function (obj, index){
        if (index == id){
            obj.soluong++;
            localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(objArray)); //đẩy vào localStorage đó
            flag = true;
            return;
        }
    })
    loadCart();
    tongTienSanPham();
}

function tongTienSanPham (){
    var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
    var tongTienOBJ = 0;
    let formattedtongtienPrice;
    for(let i = 0 ; i < objArray.length ; i++){
        if (objArray[i].giadagiam == null){
            tongTienOBJ += parseFloat(objArray[i].soluong * objArray[i].giasanphamgoc);
            formattedtongtienPrice = parseFloat(tongTienOBJ).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        }else {
            tongTienOBJ += parseFloat(objArray[i].soluong * objArray[i].giadagiam);
            formattedtongtienPrice = parseFloat(tongTienOBJ).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        }
    }
    $('#tonggiatrisp1').text(formattedtongtienPrice);
    $('#tonggiatrisp2').text(formattedtongtienPrice);
    tongGiaTriThanhToan = tongTienOBJ; // lấy tổng tiền cần thanh toán
    soTienCanNap(); // sau khi tổng tiền sản phẩm thích check số dư
}


async function getMonyForUser(){
    var listUser;
    await axios.get("/api/public/getAllUser")
        .then(function (response) {
            listUser = response.data.data;
            let formattedtongtienPrice;
            listUser.forEach(function (obj,index){
                if (obj.tenDangNhap == sessionStorage.getItem("user_name")){
                    formattedtongtienPrice = parseFloat(obj.soDu).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    soDuHienTai = obj.soDu;
                    $('#tienuser').text(formattedtongtienPrice);
                }
            })
            soTienCanNap();
        })
        .catch(function (error) {
            console.error("Error fetching products:", error);
        });
}

function soTienCanNap(){
    soTienCanNapThem = soDuHienTai - tongGiaTriThanhToan;
    var checktien = $('#khongdutien');
    var dutien = $('#dutien');
    checktien.empty();
    dutien.empty();
    if (soTienCanNapThem < 0 ){
        let tienThieu = Math.abs(soTienCanNapThem);
        let formattedtienThieu = parseFloat(tienThieu).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        let row = `
        <div class="d-flex align-items-center justify-content-between"  style="font-size: 0.85rem;">
              <p class="mb-1">Số tiền cần nạp thêm</p>
              <p class="m-0 fw-semibold">${formattedtienThieu}</p>
        </div>
        <div class="mt-2">
          <a href="/add-funds" class="btn btn-primary py-2 w-100 fw-semibold"  style="font-size: 0.85rem;">
            <img src="https://cdn.divineshop.vn/static/b1402e84a947ed36cebe9799e47f61c2.svg" alt="" class="" width="7%">
            Nạp thêm vào tài khoản
          </a>
        </div>
      `;
        checktien.append(row)
    }else {
        let row = `
        <div class="mb-2 mt-4">
            <a href="/confirm-cart"   class="btn text-white py-2 w-100 fw-semibold ${giohang == true ? `disabled` : ``}" aria-disabled="true"  style="font-size: 0.85rem; background-color: #005baa;">
              <img src="https://cdn.divineshop.vn/static/ed4044413ba8489903d4f27bac88aa02.svg" alt="" class="" width="7%">
              Xác nhận mua hàng
            </a>
        </div>
      `;
        dutien.append(row);
    }
}