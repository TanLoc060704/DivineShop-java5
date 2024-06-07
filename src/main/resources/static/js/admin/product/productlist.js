$(document).ready(function() {
    const detailElement = $('#productDetailSlug');
    const detail = detailElement.data('detail');
    console.log(detail)
    function loadDetailPage() {
        axios.get(`/api/products/${detail}`)
            .then(response => {
                console.log(response.data.data);
                let detailProduct = $('#detailProduct')
                detailProduct.html('');
                detailProduct.append(loadDetailProduct(response.data.data))
                // Create Product
                const oldSlug = $("#createProductSlug").val()
                $("#updateDetailProduct").submit(async function (e) {
                    e.preventDefault();
                    let slug = $("#createProductSlug");
                    const slugCheckResult = await checkUniqueSlug(slug.val());
                    console.log(slugCheckResult)


                    console.log(oldSlug !== $("#createProductSlug").val() && slugCheckResult === true)
                    if (oldSlug !== $("#createProductSlug").val() && slugCheckResult === true) {
                        slug.addClass('is-invalid');
                    }else{

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
                            anhSanPham: response.data.data.anhSanPham,
                            slug: $("#createProductSlug").val(),
                            danhMuc: $("#createProductCategory").val(),
                            mota: $("#createProductDescription").val(),
                            soLuong: $("#createProductQuantity").val(),
                            soLuongMua: response.data.data.soLuongMua,
                            soLuotThich: response.data.data.soLuotThich,
                            categories: result
                        };
                        // console.log($("#createProductImage").val().split('\\').pop().split('/').pop())
                        console.log(JSON.stringify(newProduct))
                        axios({
                            method: 'put',
                            url: `/api/products/${response.data.data.id}`,
                            data: JSON.stringify(newProduct),
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        })
                            .then(function (response) {
                                console.log('Update product thành công!');
                                loadDetailPage();
                                // window.location.reload();
                            })
                            .catch(function (error) {
                                console.error('Đã xảy ra lỗi:', error);
                            });
                    }

                });
            })
            .catch(error => {
                console.error(error);
            });
    }
    loadDetailPage();
    function loadDetailProduct(data) {
        let number = data.giaSanPham;
        const formatter = new Intl.NumberFormat('vi-VN', { minimumFractionDigits: 0, maximumFractionDigits: 0 });
        let formattedNumber = number === null ? 0 : formatter.format(number);
        $('#createProductImage').value = data.anhSanPham;
        return `
            <div class="row mt-3">
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Product ID</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold"">${data.id}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Product Code</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.maSanPham}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Product Title</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.tenSanPham}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Quantity</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.soLuong}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Price</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${formattedNumber}đ</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Discounts</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.percentGiamGia}%</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Category</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.danhMuc}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Status</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold text-success">${data.tinhTrang}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Genres</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.theLoai}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Sales</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.soLuongMua}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Likes</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.soLuotThich}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Slug</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.slug}</p>
                </div>
            </div>
            <hr>
            <form id="updateDetailProduct">                
                <div class="row">
                    <div class="col-6">
                        <div class="d-flex align-items-center gap-3 h-100">
                            <img src="/images/${data.anhSanPham}" class="rounded" width="274px" height="154px" alt="">
                            <button type="button" class="btn btn-outline-primary">Change Image</button>
                        </div>
                    </div>
                    <div class="col-1"><div class="vr h-100"></div></div>
                    <div class="col-5">
                        <div class="d-flex align-items-center h-100">
                            <div>
                                <p class="fw-bold" style="font-size: 0.87rem;">Description</p>
                                <p class="m-0 overflow-hidden" style="font-size: 0.87rem;">${data.mota}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">                
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" value="${data.maSanPham}"  placeholder="Product Code" id="createProductCode" required>
                            <label>Product Code</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" value="${data.slug}"  placeholder="Product Slug" id="createProductSlug" required>
                            <label>Product Slug</label>
                            <div class="invalid-feedback">Slug must be unique.</div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" value="${data.tenSanPham}"  placeholder="Product Title" id="createProductName" required>
                            <label>Product Title</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" value="${data.soLuong}" min="0"  placeholder="Quantity" id="createProductQuantity" required>
                            <label>Quantity</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" value="${data.giaSanPham}" min="0"  placeholder="Price" id="createProductPrice" required>
                            <label>Price</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" value="${data.percentGiamGia}" min="0" max="100"  placeholder="Discounts" id="createProductDiscounts" required>
                            <label>Discounts</label>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-floating mb-3">
                            <textarea class="form-control" placeholder="Description" id="createProductDescription" style="height: 100px" required>${data.mota}</textarea>
                            <label>Description</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <select class="form-select" aria-label="Floating label select example" id="createProductStatus">
                                <option value="true" ${data.tinhTrang === 'true' ? 'selected' : ''}>True</option>
                                <option value="false" ${data.tinhTrang === 'false' ? 'selected' : ''}>False</option>
                            </select>
                            <label>Status</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <select class="form-select" aria-label="Floating label select example" id="createProductCategory">
                                <option value="Giải trí" ${data.danhMuc === 'Giải trí' ? 'selected' : ''}>Giải trí</option>
                                <option value="Làm việc" ${data.danhMuc === 'Làm việc' ? 'selected' : ''}>Làm việc</option>
                                <option value="Học tập" ${data.danhMuc === 'Học tập' ? 'selected' : ''}>Học tập</option>
                                <option value="Game Steam" ${data.danhMuc === 'Game Steam' ? 'selected' : ''}>Game Steam</option>
                                <option value="Edit Ảnh, Video" ${data.danhMuc === 'Edit Ảnh, Video' ? 'selected' : ''}>Edit Ảnh, Video</option>
                                <option value="Window, Office " ${data.danhMuc === 'Window, Office ' ? 'selected' : ''}>Window, Office</option>
                                <option value="Google Drive" ${data.danhMuc === 'Google Drive' ? 'selected' : ''}>Google Drive</option>
                                <option value="Steam Wallet" ${data.danhMuc === 'Steam Wallet' ? 'selected' : ''}>Steam Wallet</option>
                                <option value="Diệt Virus" ${data.danhMuc === 'Diệt Virus' ? 'selected' : ''}>Diệt Virus</option>
                                <option value="Google Play, iTunes" ${data.danhMuc === 'Google Play, iTunes' ? 'selected' : ''}>Google Play, iTunes</option>
                            </select>
                            <label>Category</label>
                        </div>
                    </div>
                    <div class="col-12">
                         <div class="form-floating mb-3">                            
                            <input class="form-control" value="${data.theLoai}" list="datalistOptions"  id="createProductGenres" placeholder="Type to search..." required>
                            <label>Genres</label>                            
                         </div>        
                         <datalist id="datalistOptions">
                            <option value="App, Giải trí, Xem phim">
                            <option value="App, Làm việc, Diệt virus">
                            <option value="Action, Adventure, RPG">
                            <option value="windows, Làm việc, microsoft">
                            <option value="Ggplay, itune, chplay">
                        </datalist>             
                    </div>
                    <div class="">
                        <button class="btn btn-primary" type="submit">Save change</button>
                        <button class="btn btn-outline-danger" type="button" data-bs-toggle="modal" data-bs-target="#deleteDetailProductWith${data.maSanPham}">Delete</button>
                    </div>
                </div>
            </form>
            
            <!-- Modal -->
            <div class="modal fade" id="deleteDetailProductWith${data.maSanPham}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Are u sure u want to delete this??</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    ${data.tenSanPham}
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-danger" onclick="deleteProduct(${data.id}, true)">Understood</button>
                  </div>
                </div>
              </div>
            </div> 
        `
    }

});

async function checkUniqueSlug(value) {
    try {
        const response = await axios.get(`/api/products/${value}`);
        return true;
    } catch (error) {
        console.error(error);
        return false;
    }
}