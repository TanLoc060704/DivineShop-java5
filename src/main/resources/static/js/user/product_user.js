$(document).ready(function () {
    var idSP = ''
    var dataMua = new Date();//lấy ngày hiện tại
    var formattedDate = '';
    var trangThai = '';
    var anh;
    var tensp;
    var theLoai = '';
    var giaSPGoc = '';
    var phanTramGiam = '';
    var giaDaGiam = '';
    var objSanPham = {};
    var ObjArray = [];
    var categories = []
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const dmValue = params.get('p');
    const catValue = params.get('cat');
    const searchValue = params.get('searchInput');
    // Biến để lưu danh sách sản phẩm
    var allProducts = [];

    const getAllCategory = async () => {
        let listCategoryContainer = $('#listCategoryContainer');
        await axios
            .get('/api/categories')
            .then(response => {
                listCategoryContainer.empty();
                let responseData = response.data;
                categories = responseData;
                listCategoryContainer.append(`<option value="">Tất cả</option>`);
                $.each(responseData, (index, cat) => {
                    let html =
                        `<option value="${cat.tenTheLoai}" ${cat.tenTheLoai === catValue ? 'selected' : ''}>${cat.tenTheLoai}</option>`;
                    listCategoryContainer.append(html);
                });
            });
    };

    const getAllDanhMuc = async () => {
        let listDanhMucContainer = $('#listDanhMucContainer');
        await axios
            .get('/api/products')
            .then(response => {
                listDanhMucContainer.empty();
                let products = response.data;
                let uniqueDanhMuc = new Set(products.map(product => product.danhMuc));
                listDanhMucContainer.append(`<option value="">Tất cả</option>`);
                uniqueDanhMuc.forEach(dm => {
                    let html = `<option value="${dm}" ${dm === dmValue ? 'selected' : ''}>${dm}</option>`;
                    listDanhMucContainer.append(html);
                });
                $('#listCategoryContainer, #listDanhMucContainer').click();
            });
    };

    getAllCategory();
    getAllDanhMuc();

    async function loadProducts(category = "", danhMuc = "", minPrice = "", maxPrice = "", searchValue = "") {
        await axios.get("/api/products")
            .then(function (response) {
                let products = response.data;
                allProducts = response.data;
                let filteredProducts = products.filter(product => {
                    let categoryMatch = category ? product.theLoai.includes(category) : true;
                    let danhMucMatch = danhMuc ? product.danhMuc.includes(danhMuc) : true;
                    let priceMatch = true;
                    let searchMatch = searchValue ? product.tenSanPham.toLowerCase().includes(searchValue.toLowerCase()) : true;

                    if (minPrice && !isNaN(minPrice)) {
                        let originalPrice = product.giaSanPham;
                        let discountPercent = product.percentGiamGia;
                        let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)) : originalPrice;
                        priceMatch = priceMatch && discountedPrice >= parseInt(minPrice);
                    }

                    if (maxPrice && !isNaN(maxPrice)) {
                        let originalPrice = product.giaSanPham;
                        let discountPercent = product.percentGiamGia;
                        let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)) : originalPrice;
                        priceMatch = priceMatch && discountedPrice <= parseInt(maxPrice);
                    }

                    return categoryMatch && danhMucMatch && priceMatch && searchMatch;
                });
                filteredProducts.sort((a, b) => {
                    // Sắp xếp theo giá giảm giá
                    let priceA = a.percentGiamGia > 0 ? (a.giaSanPham * (1 - a.percentGiamGia / 100)) : a.giaSanPham;
                    let priceB = b.percentGiamGia > 0 ? (b.giaSanPham * (1 - b.percentGiamGia / 100)) : b.giaSanPham;
                    return priceA - priceB;
                });


                let productsList = $("#product-all-from-user");
                if (localStorage.getItem(sessionStorage.getItem("user_name")) == null) {
                    $('#soluong').text(0);
                } else {
                    var arrayAlredyInWeb = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
                    $('#soluong').text(arrayAlredyInWeb.length)
                }
                productsList.empty();
                console.log(filteredProducts);
                if (filteredProducts.length === 0) {
                    productsList.append('<p>Không có sản phẩm phù hợp.</p>');
                } else {
                    filteredProducts.forEach(product => {
                        let originalPrice = product.giaSanPham;
                        let discountPercent = product.percentGiamGia;
                        let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)).toFixed(0) : originalPrice;

                        let formattedOriginalPrice = parseFloat(originalPrice).toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                        let formattedDiscountedPrice = parseFloat(discountedPrice).toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });

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
                }
            })
            .catch(function (error) {
                console.error("Error fetching products:", error);
            });
    }

    if (window.location.pathname === "/all-products") {
        let selectedCategory = $('#listCategoryContainer').val();
        let selectedDanhMuc = $('#listDanhMucContainer').val();
        let minPrice = $('#minPrice').val();
        let maxPrice = $('#maxPrice').val();
        $('#searchInput').val(searchValue);
        loadProducts(selectedCategory, selectedDanhMuc, minPrice, maxPrice, searchValue);
    }

    $('#listCategoryContainer, #listDanhMucContainer').on('change click', function () {
        let selectedCategory = $('#listCategoryContainer').val();
        let selectedDanhMuc = $('#listDanhMucContainer').val();
        let minPrice = $('#minPrice').val();
        let maxPrice = $('#maxPrice').val();
        loadProducts(selectedCategory, selectedDanhMuc, minPrice, maxPrice, searchValue);
    });
    $('#filterButton').on('click', function () {
        let selectedCategory = $('#listCategoryContainer').val();
        let selectedDanhMuc = $('#listDanhMucContainer').val();
        let minPrice = $('#minPrice').val();
        let maxPrice = $('#maxPrice').val();
        loadProducts(selectedCategory, selectedDanhMuc, minPrice, maxPrice, searchValue);
    });

    // Thay thế sự kiện submit trên form bằng sự kiện click trên nút tìm kiếm
    $(document).on('click', '#searchButton', function () {
        let newSearchValue = $('#searchInput').val();
        let selectedCategory = $('#listCategoryContainer').val();
        let selectedDanhMuc = $('#listDanhMucContainer').val();
        let minPrice = $('#minPrice').val();
        let maxPrice = $('#maxPrice').val();

        let searchValue = window.location.pathname + '?searchInput=' + encodeURIComponent(newSearchValue);
        loadProducts(selectedCategory, selectedDanhMuc, minPrice, maxPrice, searchValue);
    });


    // loadProduct details
    function loadProductDetails() {
        let slug = window.location.pathname.split("/")[2];
        if (localStorage.getItem(sessionStorage.getItem("user_name")) == null) {
            $('#soluong').text(0);
        } else {
            var arrayAlredyInWeb = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
            $('#soluong').text(arrayAlredyInWeb.length)
        }
        axios.get("/api/products/" + slug)
            .then(function (response) {
                let product = response.data.data;
                console.log(product)
                let productHtml = $("#productHtml");
                productHtml.empty();

                let originalPrice = product.giaSanPham;
                let discountPercent = product.percentGiamGia;
                let discountedPrice = discountPercent > 0 ? (originalPrice * (1 - discountPercent / 100)).toFixed(0) : originalPrice;

                // Định dạng giá thành tiền Việt Nam Đồng
                let formattedOriginalPrice = parseFloat(originalPrice).toLocaleString('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });
                let formattedDiscountedPrice = parseFloat(discountedPrice).toLocaleString('vi-VN', {
                    style: 'currency',
                    currency: 'VND'
                });

                let productDisplay = discountPercent > 0 ?
                    `
                        <div class="d-flex align-items-center">
                            <h4 class="m-0" id="giaDaGiam">${formattedDiscountedPrice}</h4>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-bell"></i></button>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-heart"></i></button>
                        </div>
                        <div class="d-flex align-items-center gap-3">
                            <p style="font-size: 0.9rem;" class="m-0 text-decoration-line-through text-secondary" id="giaGoc">${formattedOriginalPrice}</p>
                            <p style="font-size: 0.7rem;" class="m-0 text-bg-danger fw-bold rounded p-1" id="Percent">${discountPercent}%</p>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-primary ${product.tinhTrang ? "" : "disabled"}" id="buyNow"><i class="fa-regular fa-credit-card" ></i> Mua Ngay</button></div>
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-outline-primary ${product.tinhTrang ? "" : "disabled"}" id="addCart"><i class="fa-solid fa-cart-plus"></i> Thêm Vào Giỏ</button></div>
                        </div>
                    `
                    :
                    `
                        <div class="d-flex align-items-center gap-3">
                            <h4 class="m-0" id="giaGoc">${formattedOriginalPrice}</h4>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-bell"></i></button>
                            <button type="button" class="btn text-body-secondary fs-4"><i class="fa-solid fa-heart"></i></button>
                        </div>
                        <div class="row">
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-primary ${product.tinhTrang ? "" : "disabled"}" id="buyNow"><i class="fa-regular fa-credit-card" ></i> Mua Ngay</button></div>
                            <div class="col"><button type="button" class="btn fw-semibold w-100 btn-outline-primary ${product.tinhTrang ? "" : "disabled"}" id="addCart"><i class="fa-solid fa-cart-plus" ></i> Thêm Vào Giỏ</button></div>
                        </div>
                `
                let loadProductToHtml = `
                    <div class="col-4"><img class="img-fluid rounded" src="/images/${product.anhSanPham}" alt="" id="anhSp"></div>
                    <div class="col-5">
                        <p class="mb-2 fw-light">Sản phẩm</p>
                        <h3 class="mb-3" id="tenSp">${product.tenSanPham}</h3>
                        <p class="mb-2  fw-light"><i class="fa-solid fa-box"></i> Tình trạng: <span id="trangThai" class="${product.tinhTrang ? "text-success" : " text-danger"}" >${product.tinhTrang ? "Còn Hàng" : " Hết Hàng"}</span></p>
                        <p class=" fw-light"><i class="fa-solid fa-tag"></i> Thể loại: ${product.theLoai}</p>
                        ${productDisplay}
                    </div>
                    <div class="col-3">
                        <p class="m-0">Mã sản phẩm</p>
                        <p class="fw-bold" id="maSanPham">${product.maSanPham}</p>
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

                //lấy giá trị của product detaill
                idSP = $('#maSanPham').text();
                formattedDate = dataMua.toISOString().split('T')[0]; // format data chuẩn để đưa xuống database
                trangThai = $('#trangThai').text();
                anh = $('#anhSp').attr('src');
                tensp = $('#tenSp').text();
                theLoai = product.theLoai;
                giaSPGoc = parseFloat($('#giaGoc').text());
                phanTramGiam = parseInt($('#Percent').text());
                giaDaGiam = parseFloat($('#giaDaGiam').text());

                //cho các biến thành đối tượng để lưu vào obj
                objSanPham = {
                    id: parseInt((idSP.slice(-2).includes('-')) ? idSP.slice(-1) : idSP.slice(-2)), // cắt chuổi để lấy mã sản phẩm
                    date: formattedDate,
                    soluong: 1,
                    anhSp: anh,
                    tensp: tensp,
                    theloai: theLoai,
                    trangThai: (trangThai === "Còn Hàng" ? 1 : 0), //1 là còn hàng và 0 là hết hàng
                    giasanphamgoc: giaSPGoc * 1000,
                    phantramgiam: (phanTramGiam == null ? 0 : phanTramGiam) ,
                    giadagiam: (giaDaGiam * 1000)
                }
                $("#gioi-thieu-san-pham").append(
                    `
                        <h6 class="fw-bold">Giới thiệu</h6>
                        <p>${product.mota}</p>
                        <hr>
                    `
                )

            })
            .catch(function (error) {
                console.error("Error fetching products:", error);
            })
    }

    const detailPattern = /\/detail\/.*/;
    if (detailPattern.test(window.location.pathname)) {
        loadProductDetails();
    }

    $(document).on('click', '#buyNow', function () {
        if (sessionStorage.getItem("user_name") == null || !sessionStorage.getItem("user_name")) {
            window.location.href = "/log-in";
        } else {
            if (localStorage.getItem(sessionStorage.getItem("user_name")) == null) { // kiểm tra xem localStorage đã có key đó chưa
                ObjArray.push(objSanPham);
                localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(ObjArray));
                window.location.href = "/cart";
            } else { //nếu có rồi thì
                var arraylocalStorage = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name"))); //lấy value của localStorage đó ra
                var flag = false;
                arraylocalStorage.forEach(function (obj, index) { // duyệt mảng của nó
                    if (obj.id === objSanPham.id) { //nếu id trong mảng trùng với id obj thì
                        obj.soluong++; //cộng số lượng obj lên
                        localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(arraylocalStorage)); //đẩy vào localStorage đó
                        flag = true;
                        window.location.href = "/cart";
                        return;
                    }
                })

                if (!flag) { //nếu đó là sản phẩm mới thì sẽ được vào đây
                    var arrayAlredyInWeb = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
                    arrayAlredyInWeb.push(objSanPham)
                    localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(arrayAlredyInWeb));
                    window.location.href = "/cart";
                }
            }
        }
    });

    $(document).on('click', '#addCart', function () {
        if (sessionStorage.getItem("user_name") == null || !sessionStorage.getItem("user_name")) {
            window.location.href = "/log-in";
        } else {
            if (localStorage.getItem(sessionStorage.getItem("user_name")) == null) { // kiểm tra xem localStorage đã có key đó chưa
                ObjArray.push(objSanPham);
                localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(ObjArray));
            } else { //nếu có rồi thì
                var arraylocalStorage = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name"))); //lấy value của localStorage đó ra
                var flag = false;
                arraylocalStorage.forEach(function (obj, index) { // duyệt mảng của nó
                    if (obj.id === objSanPham.id) { //nếu id trong mảng trùng với id obj thì
                        $('#soluong').text("1")
                        obj.soluong++; //cộng số lượng obj lên
                        localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(arraylocalStorage)); //đẩy vào localStorage đó
                        flag = true;
                        return;
                    }
                })

                if (!flag) { //nếu đó là sản phẩm mới thì sẽ được vào đây
                    var arrayAlredyInWeb = JSON.parse(localStorage.getItem(sessionStorage.getItem("user_name")));
                    arrayAlredyInWeb.push(objSanPham)
                    localStorage.setItem(sessionStorage.getItem("user_name"), JSON.stringify(arrayAlredyInWeb));
                    $('#soluong').text(arrayAlredyInWeb.length)
                }
            }
        }
    });

});