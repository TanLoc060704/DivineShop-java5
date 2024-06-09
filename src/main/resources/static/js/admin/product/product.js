let currentPage = 0;
const pageSize = 10;
let searchTerm = "";
let danhmuc= "";
let isShowingResultUpdated = false;

function loadProducts() {
    axios.get(`/api/products/pagination?page=${currentPage}&size=${pageSize}&searchTerm=${searchTerm}&category=${danhmuc}`)
        .then(function (response) {
            let {first, last, content, totalElements, numberOfElements, totalPages} = response.data
            console.log(response.data)
            let productsList = $("#productTable");

            //Showing result
            if (!isShowingResultUpdated) {
                $('#showingResult').html('');
                $('#showingResult').append(`Showing <b>${numberOfElements}</b> of <b>${totalElements}</b> Results`)
                isShowingResultUpdated = true;
            }

            productsList.empty();
            if(numberOfElements > 0){
                content.forEach(product => {
                    let number = product.giaSanPham;
                    const formatter = new Intl.NumberFormat('vi-VN', { minimumFractionDigits: 0, maximumFractionDigits: 0 });
                    let formattedNumber = number === null ? 0 : formatter.format(number);
                    let row = `<tr>
                                    <td>${product.maSanPham}</td>
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
                                    <td>${formattedNumber}đ</td>
                                    <td>${product.soLuong}</td>
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
                                                <li><a class="dropdown-item edit-product" href="/admin/product-view/${product.slug}""><i class="fa-solid fa-eye"></i> View & Edit</a></li>
                                                <li>
                                                    <btn class="dropdown-item delete-product" data-bs-toggle="modal" data-bs-target="#deleteProductWith${product.maSanPham}"><i class="fa-solid fa-trash"></i> Delete</btn>                                                   
                                                </li>
                                            </ul>
                                        </div>                                                                                  
                                        <!-- Modal -->
                                        <div class="modal fade" id="deleteProductWith${product.maSanPham}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                          <div class="modal-dialog">
                                            <div class="modal-content">
                                              <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Are u sure u want to delete this??</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                              </div>
                                              <div class="modal-body">
                                                ${product.tenSanPham}
                                              </div>
                                              <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-danger" onclick="deleteProduct(${product.id}, false)">Understood</button>
                                              </div>
                                            </div>
                                          </div>
                                        </div> 
                                    </td>
                                </tr>`;
                    productsList.append(row);
                });
            }
            else {
                productsList.append(`<td colspan="7" class="table-active text-center">Sorry, the product you are looking for does not exist.</td>`);
            }
            updatePaginationButtons(first, last, totalPages);
        })
        .catch(function (error) {
            console.error("Error fetching products:", error);
        });
}

function searchEngine() {
    searchTerm = $('#searchListProductName').val()
    danhmuc = $('#searchListProductCate').val()
    isShowingResultUpdated = false;
    goToPage(0)
    loadProducts()
    console.log($('#searchListProductName').val())
    console.log($('#searchListProductCate').val())
}

$("#createProductForm").submit(async function (e) {
    e.preventDefault();
    let slug = $("#createProductSlug");
    const slugCheckResult = await checkUniqueSlug(slug.val());
    console.log(slugCheckResult)
    if (slugCheckResult === true) {
        slug.addClass('is-invalid');
    } else {
        // slug.classList.remove('is-invalid');
        var str = $("#createProductGenres").val()
        var arr = str.split(", ");
        var result = arr.map(function (item) {
            return {
                "tenTheLoai": item
            };
        });
        let newProduct = {
            maSanPham: $("#createProductCode").val(),
            tenSanPham: $("#createProductName").val(),
            tinhTrang: $("#createProductStatus").val(),
            theLoai: $("#createProductGenres").val(),
            giaSanPham: $("#createProductPrice").val(),
            percentGiamGia: $("#createProductDiscounts").val(),
            anhSanPham: $("#createProductImage").val(),
            slug: $("#createProductSlug").val(),
            danhMuc: $("#createProductCategory").val(),
            mota: $("#createProductDescription").val(),
            soLuong: $("#createProductQuantity").val(),
            soLuongMua: 0,
            soLuotThich: 0,
            categories: result
        };
        // console.log($("#createProductImage").val().split('\\').pop().split('/').pop())
        console.log(JSON.stringify(newProduct))
        axios({
            method: 'post',
            url: '/api/products',
            data: JSON.stringify(newProduct),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function (response) {
                console.log('Tạo product thành công!');
                // isShowingResultUpdated = false
                // $("#addProduct").modal('hide');
                // loadProducts()
                window.location.reload()
            })
            .catch(function (error) {
                console.error('Đã xảy ra lỗi:', error);
            });
    }
    // const formData = new FormData();
    // formData.append('file', $("#createProductImage").val()); // `file` là biến chứa tệp cần tải lên
    // formData.append('productDTO', JSON.stringify(newProduct));
    // axios.post("/api/products", formData, {
    //   headers: {
    //       'Content-Type': 'multipart/form-data'
    //   }
    //   })
    //     .then(function(response) {
    //         $("#addProduct").modal('hide');
    //         loadProducts();
    //     })
    //     .catch(function(error) {
    //         console.error("Error creating product:", error);
    //     });
});

function updatePaginationButtons(first, last, totalPages) {
    // Disable Previous button if on first page
    first ? $('#prevBtn').addClass('disabled') : $('#prevBtn').removeClass('disabled');

    // Disable Next button if on last page
    last ? $('#nextBtn').addClass('disabled') : $('#nextBtn').removeClass('disabled');

    // Generate page number buttons
    let paginationHtml = '';
    let startPage = Math.max(currentPage - 1, 0);
    let endPage = Math.min(startPage + 2, totalPages - 1);

    for (let i = startPage; i <= endPage; i++) {
        paginationHtml += `<li class="page-item ${currentPage === i ? 'active' : ''}"><a class="page-link" href="#" onclick="goToPage(${i})">${i + 1}</a></li>`;
    }

    $('.pagination').find('li:not(:first-child, :last-child)').remove();
    $('.pagination').find('li:last-child').before(paginationHtml);
}

function goToPage(pageNumber) {
    currentPage = pageNumber;
    loadProducts();
}

function prevPage() {
    if (currentPage > 0) {
        currentPage--;
        loadProducts();
    }
}

function nextPage() {
    currentPage++;
    loadProducts();
}

async function checkUniqueSlug(value) {
    try {
        const response = await axios.get(`/api/products/${value}`);
        return true;
    } catch (error) {
        console.error(error);
        return false;
    }
}

$(document).ready(function() {
    // Load products initially
    loadProducts();
    // Create Product
});


