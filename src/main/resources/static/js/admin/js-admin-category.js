$(document).ready(function () {
    loadAllCategory()
})

let categories = [];
let currentPage = 1; // page hiện tại
let perPage = 10; // item number in page
let totalPage = 0;
let perCategory = [];
function loadAllCategory(search = '') {
    console.log(search)
    axios.get("/api/categories")
        .then(function (respone) {
            categories = respone.data;
            console.log(categories.length)
            let start = (currentPage - 1) * perPage;
            let end = start + perPage;
            perCategory = categories.slice(start,end);
            console.log(perCategory)
            renderPageNumber()
            if(search === ''){
                renderCategory(perCategory,search);
            }else{
                renderCategory(categories,search);
            }
            $("#soluongItem").text(perCategory.length)
            $("#tongItem").text(categories.length)
        })
}
loadAllCategory()

function handlerPageNumber (num){
    currentPage = num
    console.log(currentPage)
    let start = (currentPage - 1) * perPage;
    let end = start + perPage;
    perCategory = categories.slice(start,end);
    loadAllCategory();
}
function handlerPageNumberNext (){
    if(totalPage !== currentPage){
        currentPage = currentPage + 1;
    }
    let start = (currentPage - 1) * perPage;
    let end = start + perPage;
    perCategory = categories.slice(start,end);
    loadAllCategory();
}
function handlerPageNumberPrev (){
    if(currentPage !== 1){
        currentPage = currentPage - 1;
    }
    let start = (currentPage - 1) * perPage;
    let end = start + perPage;
    perCategory = categories.slice(start,end);
    loadAllCategory();
}

function renderPageNumber(){
    totalPage = Math.ceil(categories.length / perPage);
    let htmlPagination = $("#pagination-category");
    htmlPagination.empty();

    let startPage = Math.max(currentPage - 1, 1);
    let endPage = Math.min(startPage + 2, totalPage);

    console.log(startPage)
    console.log(endPage)
    htmlPagination.append(
        `<li class="page-item" onclick="handlerPageNumberPrev()">
            <span class="page-link"><span aria-hidden="true">&laquo;</span></span>
        </li>`)

    for (let i = startPage; i <= endPage; i++){
        let li_pagination = $(`
                <li class="page-item ${currentPage === i ? "active" : ""}"  onclick="handlerPageNumber(${i});">
                    <a class="page-link" >${i}</a>
                </li>
           ` );
        htmlPagination.append(li_pagination);
    }
    htmlPagination.append(
        `<li class="page-item" onclick="handlerPageNumberNext()">
            <a class="page-link" ><span aria-hidden="true">&raquo;</span></a>
        </li>`)
}

function renderCategory (categories,search){
    let htmlCategory = $("#tr-table-category");
    htmlCategory.empty();
    if (search !== '') {
        categories.forEach(category => {
            if (category.tenTheLoai.toLowerCase().includes(search.toLowerCase())) {
                let row = `
                        <tr> 
                            <th scope="row">${category.id}</th>
                            <td>
                                <div class="d-flex align-items-center gap-2">
                                    <div class="">
                                        <p class="m-0 fw-semibold">${category.tenTheLoai}</p>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="dropdown">
                                    <a class="btn btn-secondary btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="fa-solid fa-ellipsis"></i>
                                    </a>
    
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="/admin/category-view/${category.id}"><i class="fa-solid fa-eye"></i> View & Edit</a></li>
                                        <li><a class="dropdown-item" href="#"><i class="fa-solid fa-trash"></i> Delete</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr> 
                        `
                htmlCategory.append(row);
            }
        })
    }else{
        categories.forEach(category => {
            let row = `
                    <tr> 
                    <th scope="row">${category.id}</th>
                    <td>
                        <div class="d-flex align-items-center gap-2">
                            <div class="">
                                <p class="m-0 fw-semibold">${category.tenTheLoai}</p>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="dropdown">
                            <a class="btn btn-secondary btn-sm" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fa-solid fa-ellipsis"></i>
                            </a>

                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/admin/category-view/${category.id}"><i class="fa-solid fa-eye"></i> View & Edit</a></li>
                                <li><a class="dropdown-item" href="#"><i class="fa-solid fa-trash"></i> Delete</a></li>
                            </ul>
                        </div>
                    </td>
                    </tr> 
                `
            htmlCategory.append(row);
        })
    }
}


function insertCategory() {
    let tenTheLoai = $("#tenTheLoai").val();
    let data;
    axios.post("/api/categories", {
        "tenTheLoai": tenTheLoai
    })
        .then(function (respone) {
            data = respone.data;
            console.log(data)
            console.log(data.data)

            if(data.data){
                swal.fire({
                    title: "Success",
                    text: data.message,
                    icon: "success",
                    confirmButtonText: "OK"

                })
            }else {
                swal.fire({
                    title: "Error",
                    text: data.message,
                    icon: "error",
                    confirmButtonText: "OK"

                })
            }

            loadAllCategory()
        })
        .catch(error => {
            swal.fire({
                title: data.success,
                text: data.message,
                icon: "error",
                confirmButtonText: "OK"

            })
        })
}

$('#save-category').click(function () {
    insertCategory();
});

$('#searchInput').on('keyup', function (e) {
    let searchQuery = $('#searchInput').val();
    loadAllCategory(searchQuery);
});