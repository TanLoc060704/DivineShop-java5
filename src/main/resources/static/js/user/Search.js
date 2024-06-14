$(document).ready(function () {
    var allProducts = [];

    async function loadProducts() {
        await axios.get("/api/products")
            .then(function (response) {
                allProducts = response.data;
            })
            .catch(function (error) {
                console.error("Error fetching products:", error);
            });
    }

    loadProducts();

    // thanh tìm kiếm
    $('#searchInput').on('input', function () {
        let searchKeyword = $(this).val().toLowerCase().trim();
        let filteredProducts = allProducts.filter(product => {
            return product.tenSanPham.toLowerCase().startsWith(searchKeyword);
        });

        displaySearchSuggestions(filteredProducts);
    });

    // Hàm hiển thị danh sách gợi ý tìm kiếm
    function displaySearchSuggestions(products) {
        let suggestionList = $('#searchSuggestions');
        suggestionList.empty();

        if (products.length === 0) {
            suggestionList.append('<a href="#" class="dropdown-item disabled">Không có gợi ý tìm kiếm.</a>');
        } else {
            // Giới hạn hiển thị tối đa 10 sản phẩm
            products.slice(0, 10).forEach(product => {
                let suggestionItem =
                    `<a href="detail/${product.slug}" class="dropdown-item pt-1 pb-1 ">${product.tenSanPham}</a>`;
                suggestionList.append(suggestionItem);
            });

            // Hiển thị thông báo nếu có nhiều hơn 10 sản phẩm
            if (products.length > 10) {
                suggestionList.append('<a href="#" class="dropdown-item pt-1 pb-1">Và còn nhiều kết quả khác...</a>');
            }
        }

        // Hiển thị danh sách gợi ý tìm kiếm
        suggestionList.css('display', 'block');
    }

    // Ẩn danh sách gợi ý khi click ra ngoài ô tìm kiếm
    $(document).on('click', function (event) {
        if (!$(event.target).closest('#searchSuggestions').length) {
            $('#searchSuggestions').css('display', 'none');
        }
    });

})