$(document).ready(function() {
    loadCart(); // load giỏ hàng khi trang web được load
    tongTienSanPham(); //load tổng tiền sản phẩm
})

function loadCart (){
    if (localStorage.getItem(sessionStorage.getItem("user_name")) != null){
        var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
        $('#soluong').text(objArray.length)
        $('#soluongsp').text(objArray.length)
        let productsList = $("#list_sp_cart");
        productsList.empty();

        objArray.forEach(function (obj,index){

            let formattedOriginalPrice = parseFloat(obj.giasanphamgoc).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            let formattedDiscountedPrice = parseFloat(obj.giadagiam).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            let tongtienFormatted = parseFloat(obj.giadagiam * obj.soluong).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });

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
                      <button type="button" onclick="truSoLuongCart(${index})" disabled class="btn btn-outline-light text-black border-light-subtle">-</button>
                      <input type="text" disabled class="form-control text-center px-1" id="soluong" value="${obj.soluong}">
                      <button type="button" onclick="congSoLuongCart(${index})" disabled class="btn btn-outline-light text-black border-light-subtle">+</button>
                    </div>
                  </div>
                  <div class="col-4 text-end">
                    <h5 class="" id="giaGoc">${tongtienFormatted}</h5>
                    <div class="d-flex align-items-center justify-content-end gap-3">
                      <p style="font-size: 0.7rem;" class="m-0 text-bg-danger fw-bold rounded p-1">-${obj.phantramgiam}%</p>
                      <p style="font-size: 0.9rem;" class="m-0 text-decoration-line-through text-secondary">${formattedOriginalPrice}</p>
                    </div>
                  </div>
                </div>
                <hr class="mb-2 mt-4">
                <div class="d-flex align-items-center justify-content-between">
                  <p class="m-0" style="font-size: 0.85rem;"><i class="fa-solid fa-box"></i> Tình trạng: <span class="text-success">${obj.trangThai == 1 ? "Còn Hàng" : "Hết Hàng"}</span></p>
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

function tongTienSanPham (){
    var objArray = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
    var tongTienOBJ = 0;
    var formattedtongtienPrice;
    for(let i = 0 ; i < objArray.length ; i++){
        tongTienOBJ += parseFloat(objArray[i].soluong * objArray[i].giadagiam);
        formattedtongtienPrice = parseFloat(tongTienOBJ).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    }
    if(sessionStorage.getItem("tiengiam") != ""){
        console.log(sessionStorage.getItem("tiengiam"))
        var formattedtongtienPrice1 = parseFloat(sessionStorage.getItem("tiengiam")).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        $('#tonggiatrisp1').text(formattedtongtienPrice1);
        return;
    }
    $('#tonggiatrisp1').text(formattedtongtienPrice);
}