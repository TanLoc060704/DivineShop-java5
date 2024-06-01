$(document).ready(function() {
    function loadProducts() {
        axios.get("/api/products")
            .then(function (response) {
                let products = response.data;
                console.log(products)
                let productsList = $("#productTable");
                productsList.empty();
                products.forEach(product => {
                    let row = `<tr>
                                    <th scope="row">${product.maSanPham}</th>
                                    <td>
                                        <div class="d-flex align-items-center gap-2">
                                            <div class="">
                                                <img src="/images/${product.anhSanPham}" width="45px" height="30px" alt="">
                                            </div>
                                            <div class="">
                                                <p class="m-0 fw-semibold">${product.tenSanPham}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="alert alert-light m-0 py-1 px-0 text-center" role="alert">
                                            ${product.danhMuc}
                                        </div>
                                    </td>
                                    <td>${product.giaSanPham}đ</td>
                                    <td>12</td>
                                    <td>
                                        <div class="alert alert-warning m-0 text-center p-1" role="alert">
                                            ${product.tinhTrang}
                                        </div>
                                    </td>
                                    <td>
                                        <div class="dropdown">
                                            <a class="btn btn-secondary btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                <i class="fa-solid fa-ellipsis"></i>
                                            </a>

                                            <ul class="dropdown-menu">
                                                <li><a class="dropdown-item edit-product" href="#" data-id="${product.maSanPham}"><i class="fa-solid fa-eye"></i> View & Edit</a></li>
                                                <li><a class="dropdown-item delete-product" href="#" data-id="${product.maSanPham}"><i class="fa-solid fa-trash"></i> Delete</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>`;
                    productsList.append(row);
                });
            })
            .catch(function (error) {
                console.error("Error fetching products:", error);
            });
    }

    // Load products initially
    loadProducts();


});