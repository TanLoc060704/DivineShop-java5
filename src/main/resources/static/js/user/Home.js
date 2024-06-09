$(document).ready(function (){
    let urlSPnoiBac = "find-top8-by-custom-order";
    let idElementSPnoiBac = "#div-san-pham-noi-bac";
    let cssStyleSPnoiBac = "";
    Show8FeaturedProducts(urlSPnoiBac,idElementSPnoiBac,cssStyleSPnoiBac)
    let urlSPbanChay = "get-top8-by-order-by-so-luong-mua-desc";
    let idElementbanChay = "#div-san-pham-ban-chay";
    let cssStylebanChay = "text-white";
    Show8FeaturedProducts(urlSPbanChay,idElementbanChay,cssStylebanChay)
    let urlSPgiamGia = "get-top8-by-order-by-percent-giam-gia-desc";
    let idElementgiamGia = "#div-san-pham-giam-gia";
    let cssStylegiamGia = "";
    Show8FeaturedProducts(urlSPgiamGia,idElementgiamGia,cssStylegiamGia)
})

const Show8FeaturedProducts = async (url,idElement,cssStyle) => {
    await axios
        .get('/api/products/'+url)
        .then(response => {
            let responseData = response.data;
            console.log(responseData.data)
            let htmlDivSanPhamNoiBac = $(idElement).empty();
            responseData.data.forEach(product => {
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
                            <div class="card-body ${cssStyle} px-0">
                                <h5 class="card-title" style="font-size: 0.9rem;">${product.tenSanPham}</h5>
                                ${priceDisplay}
                            </div>
                        </div>
                    </a>`;
                htmlDivSanPhamNoiBac.append(row);
            })
        })
        .catch(function (error) {
            console.error("Error fetching products:", error);
        });
}

