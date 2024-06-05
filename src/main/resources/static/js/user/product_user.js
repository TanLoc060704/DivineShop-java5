$(document).ready(function() {
    function loadProducts() {
        axios.get("/api/products")
            .then(function (response) {
                let products = response.data;
                let productsList = $("#product-all-from-user");
                productsList.empty();
                products.forEach(product => {
                    let originalPrice = product.giaSanPham;
                    let discountPercent = product.percentGiamGia;
                    let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)).toFixed(0) : originalPrice;

                    // Định dạng giá thành tiền Việt Nam Đồng
                    let formattedOriginalPrice = parseFloat(originalPrice).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    let formattedDiscountedPrice = parseFloat(discountedPrice).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });

                    // Sử dụng toán tử 3 ngôi để chỉ hiển thị giá gốc nếu không có giảm giá
                    let giaGiam = discountPercent > 0 ? `<p style="font-size: 0.9rem;" class="m-0 text-decoration-line-through text-secondary">${formattedOriginalPrice}</p>` : "";
                    let percentGiamGia = discountPercent > 0 ? `<p style="font-size: 0.7rem;" class="m-0 text-bg-danger fw-bold rounded p-1">${discountPercent}%</p>` : "";

                    let priceDisplay = discountPercent > 0
                        ? `<div class="d-flex gap-2 align-items-center">
                              <p style="font-size: 0.9rem;" class="m-0 fw-bold">${formattedDiscountedPrice}</p>
                              ${giaGiam}
                              ${percentGiamGia}
                           </div>`
                        : `<p style="font-size: 0.9rem;" class="m-0 fw-bold">${formattedOriginalPrice}</p>`;

                    let row =
                        `<a class="col-3 text-decoration-none" href="detail/${product.slug}">
                            <div class="card border-0 bg-transparent">
                                <img src="/images/${product.anhSanPham}" class="card-img-top rounded" alt="${product.tenSanPham}">
                                <div class="card-body px-0">
                                    <h5 class="card-title" style="font-size: 0.9rem;">${product.tenSanPham}</h5>
                                    ${priceDisplay}
                                </div>
                            </div>
                        </a>`;
                    productsList.append(row);
                });
            })
            .catch(function (error) {
                console.error("Error fetching products:", error);
            });
    }
    if(window.location.pathname === "/all-products"){
        // Load products initially
        loadProducts();
    }


    // loadProduct details
    function loadProductDetails  ()  {
        let  slug = window.location.pathname.split("/")[2];
        axios.get("/api/products/" + slug)
            .then(function(response){
                let product = response.data.data;
                console.log(product)
                let productHtml = $("#productHtml");
                productHtml.empty();

                let originalPrice = product.giaSanPham;
                let discountPercent = product.percentGiamGia;
                let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)).toFixed(0) : originalPrice;

                // Định dạng giá thành tiền Việt Nam Đồng
                let formattedOriginalPrice = parseFloat(originalPrice).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                let formattedDiscountedPrice = parseFloat(discountedPrice).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });


                let productDisplay = discountPercent > 0 ?
                    `
                        <div class="d-flex align-items-center">
                            <h4 class="m-0">${formattedDiscountedPrice}</h4>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-bell"></i></button>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-heart"></i></button>
                        </div>
                        <div class="d-flex align-items-center gap-3">
                            <p style="font-size: 0.9rem;" class="m-0 text-decoration-line-through text-secondary">${formattedOriginalPrice}</p>
                            <p style="font-size: 0.7rem;" class="m-0 text-bg-danger fw-bold rounded p-1">${discountPercent}%</p>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-primary"><i class="fa-regular fa-credit-card"></i> Mua Ngay</button></div>
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-outline-primary"><i class="fa-solid fa-cart-plus"></i> Thêm Vào Giỏ</button></div>
                        </div>
                `
                    :
                `
                        <div class="d-flex align-items-center gap-3">
                            <h4 class="m-0">${formattedOriginalPrice}</h4>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-bell"></i></button>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-heart"></i></button>
                        </div>
                `
                let loadProductToHtml = `
                    <div class="col-4"><img class="img-fluid rounded" src="/images/${product.anhSanPham}" alt=""></div>
                    <div class="col-5">
                        <p class="mb-2 fw-light">Sản phẩm</p>
                        <h3 class="mb-3">${product.tenSanPham}</h3>
                        <p class="mb-2  fw-light"><i class="fa-solid fa-box"></i> Tình trạng: <span class="${product.tinhTrang ? "text-success" : " text-danger"}" >${product.tinhTrang ? "Còn Hàng" : " Hết Hàng"}</span></p>
                        <p class=" fw-light"><i class="fa-solid fa-tag"></i> Thể loại: ${product.theLoai}</p>
                        ${productDisplay}
                    </div>
                    <div class="col-3">
                        <p class="m-0">Mã sản phẩm</p>
                        <p class="fw-bold">${product.maSanPham}</p>
                        <h6 class="fw-bold">Giới thiệu bạn bè</h6>
                        <p>Bạn bè được giảm 5% giá sản phẩm và bạn nhận hoa hồng vĩnh viễn.</p>
                        <div class="d-flex gap-2 mb-2">
                            <input type="password" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock">
                            <button type="button" class="btn btn-primary"><i class="fa-solid fa-copy"></i></button>
                            <button type="button" class="btn btn-outline-primary"><i class="fa-solid fa-code"></i></button>
                        </div>
                        <a href="#" class="text-decoration-none"><i class="fa-solid fa-circle-exclamation"></i> Xem chi tiet</a>
                    </div>
                `
                productHtml.append(loadProductToHtml);
            })
            .catch(function (error){
                console.error("Error fetching products:", error);
            })
    }
    const detailPattern = /\/detail\/.*/;
    if (detailPattern.test(window.location.pathname)) {
        loadProductDetails();
    }

});
