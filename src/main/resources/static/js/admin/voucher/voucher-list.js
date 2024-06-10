$(document).ready(function() {
    // Load vouchers initially
    loadVoucherList();
    // Create voucher
});
let currentPageVoucher = 0;
const pageSizeVoucher = 10;
let searchTermVoucher = "";
let startDayVoucher= "";
let endDayVoucher= "";
let isShowingResultUpdatedVoucher = false;

function loadVoucherList() {
    axios.get(`/api/vouchers/pagination?page=${currentPageVoucher}&size=${pageSizeVoucher}&searchTerm=${searchTermVoucher}&startDate=${startDayVoucher}&endDate=${endDayVoucher}`)
        .then(function (response) {
            let {first, last, content, totalElements, numberOfElements, totalPages} = response.data
            console.log(response.data)
            let vouchersList = $("#voucherTable");

            //Showing result
            if (!isShowingResultUpdatedVoucher) {
                $('#showingResultVoucher').html('');
                $('#showingResultVoucher').append(`Showing <b>${numberOfElements}</b> of <b>${totalElements}</b> Results`)
                isShowingResultUpdatedVoucher = true;
            }

            vouchersList.empty();
            if(numberOfElements > 0){
                content.forEach(voucher => {
                    let today = new Date();
                    let startDate = new Date(voucher.startDate);
                    let endDate = new Date(voucher.endDate);
                    let status = ''
                    let alert = ''
                    if (today >= startDate && today <= endDate) {
                        status = 'Active'
                        alert = 'success'
                    } else if (today < startDate) {
                        status = 'Inactive'
                        alert = 'warning'
                    } else {
                        status = 'Expired'
                        alert = 'danger'
                    }
                    let row = `
                        <tr>
                            <td>${voucher.sysIdVoucher}</td>
                            <td>
                                <div class="d-flex align-items-center gap-2">
                                    <div class="">
                                        <p class="m-0 fw-semibold">${voucher.voucherName}</p>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="alert alert-light m-0 py-1 px-0 text-center" role="alert">
                                   ${voucher.voucherPercentage}%
                                </div>
                            </td>
                            <td>${voucher.startDate}</td>
                            <td>${voucher.endDate}</td>
                            <td>
                                <div class="alert alert-${alert} m-0 text-center p-1" role="alert">
                                    ${status}
                                </div>
                            </td>
                            <td>
                                <div class="dropdown">
                                    <a class="btn btn-secondary btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="fa-solid fa-ellipsis"></i>
                                    </a>

                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="/admin/voucher-view/${voucher.codeVoucher}"><i class="fa-solid fa-eye"></i> View & Edit</a></li>                                        
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    `;
                    vouchersList.append(row);
                });
            }
            else {
                vouchersList.append(`<td colspan="7" class="table-active text-center">Sorry, the voucher you are looking for does not exist.</td>`);
            }
            updatePaginationButtonsVoucher(first, last, totalPages);
        })
        .catch(function (error) {
            console.error("Error fetching vouchers:", error);
        });
}

function updatePaginationButtonsVoucher(first, last, totalPages) {
    // Disable Previous button if on first page
    first ? $('#prevBtn').addClass('disabled') : $('#prevBtn').removeClass('disabled');

    // Disable Next button if on last page
    last ? $('#nextBtn').addClass('disabled') : $('#nextBtn').removeClass('disabled');

    // Generate page number buttons
    let paginationHtml = '';
    let startPage = Math.max(currentPageVoucher - 1, 0);
    let endPage = Math.min(startPage + 2, totalPages - 1);

    for (let i = startPage; i <= endPage; i++) {
        paginationHtml += `<li class="page-item ${currentPageVoucher === i ? 'active' : ''}"><a class="page-link" href="#" onclick="goToPage(${i})">${i + 1}</a></li>`;
    }

    $('.pagination').find('li:not(:first-child, :last-child)').remove();
    $('.pagination').find('li:last-child').before(paginationHtml);
}

function goToPage(pageNumber) {
    currentPageVoucher = pageNumber;
    loadVoucherList();
}

function prevPage() {
    if (currentPageVoucher > 0) {
        currentPageVoucher--;
        loadVoucherList();
    }
}

function nextPage() {
    currentPageVoucher++;
    loadVoucherList();
}

function searchEngine() {
    searchTermVoucher = $('#searchListVoucherName').val()
    startDayVoucher = $('#searchListVoucherStartDate').val()
    endDayVoucher = $('#searchListVoucherEndDate').val()
    if(!checkValidDate("searchListVoucherStartDate", "searchListVoucherEndDate")){
        console.log(searchTermVoucher)
        console.log(startDayVoucher)
        console.log(endDayVoucher)

        isShowingResultUpdatedVoucher = false;
        goToPage(0)
        loadVoucherList()
    }
}

function checkValidDate(start, end) {
    let startDate = new Date($(`#${start}`).val());
    let endDate = new Date($(`#${end}`).val());

    if (startDate > endDate){
        $(`#${start}`).addClass('is-invalid')
        $(`#${end}`).addClass('is-invalid')
        return true
    }else {
        $(`#${start}`).hasClass('is-invalid') && $(`#${start}`).removeClass('is-invalid')
        $(`#${end}`).hasClass('is-invalid') && $(`#${end}`).removeClass('is-invalid')
        return false
    }
}

$("#createVoucherForm").submit(async function (e) {
    // e.preventDefault();
    if(!checkValidDate("createVoucherSDate", "createVoucherEDate")){
        axios.get('/api/vouchers/generate')
            .then(function (response) {
                let voucherCode = response.data;
                let voucher = {
                    codeVoucher: voucherCode,
                    voucherName: $("#createVoucherTitle").val(),
                    voucherPercentage: $("#createVoucherPercent").val(),
                    startDate: $("#createVoucherSDate").val(),
                    endDate: $("#createVoucherEDate").val(),
                    description: $("#createVoucherDescription").val()
                };
                console.log(voucher)
                axios({
                    method: 'post',
                    url: '/api/vouchers',
                    data: JSON.stringify(voucher),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(function (response) {
                        console.log('Tạo voucher thành công!');
                        window.location.reload()
                    })
                    .catch(function (error) {
                        console.error('Đã xảy ra lỗi:', error);
                    });
            })
            .catch(function (error) {
                console.error("Lỗi khi tạo mã voucher: " + error);
            });
    }else console.log("CHuan bai")


    // let slug = $("#createProductSlug");
    // const slugCheckResult = await checkUniqueSlug(slug.val());
    // console.log(slugCheckResult)
    // if (slugCheckResult === true) {
    //     slug.addClass('is-invalid');
    // } else {
    //     // slug.classList.remove('is-invalid');
    //     var str = $("#createProductGenres").val()
    //     var arr = str.split(", ");
    //     var result = arr.map(function (item) {
    //         return {
    //             "tenTheLoai": item
    //         };
    //     });
    //     let newProduct = {
    //         maSanPham: $("#createProductCode").val(),
    //         tenSanPham: $("#createProductName").val(),
    //         tinhTrang: $("#createProductStatus").val(),
    //         theLoai: $("#createProductGenres").val(),
    //         giaSanPham: $("#createProductPrice").val(),
    //         percentGiamGia: $("#createProductDiscounts").val(),
    //         anhSanPham: $("#createProductImage").val(),
    //         slug: $("#createProductSlug").val(),
    //         danhMuc: $("#createProductCategory").val(),
    //         mota: $("#createProductDescription").val(),
    //         soLuong: $("#createProductQuantity").val(),
    //         soLuongMua: 0,
    //         soLuotThich: 0,
    //         categories: result
    //     };
    //     // console.log($("#createProductImage").val().split('\\').pop().split('/').pop())
    //     console.log(JSON.stringify(newProduct))
    //     axios({
    //         method: 'post',
    //         url: '/api/products',
    //         data: JSON.stringify(newProduct),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //         .then(function (response) {
    //             console.log('Tạo product thành công!');
    //             // isShowingResultUpdated = false
    //             // $("#addProduct").modal('hide');
    //             // loadProducts()
    //             window.location.reload()
    //         })
    //         .catch(function (error) {
    //             console.error('Đã xảy ra lỗi:', error);
    //         });
    // }
});

