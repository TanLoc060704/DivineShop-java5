$(document).ready(function(){
    let idCategory = window.location.pathname.split("/")[3]
    console.log(idCategory);
    function findById () {
        axios.get("/api/categories/"+idCategory)
            .then(function(respone){
                let category = respone.data.data;
                let htmlCategory = $("#html-overview-category");
                htmlCategory.empty();
                let loadCategoryHtml = `
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Category ID</p>
                        <p style="font-size: 0.85rem;"  class="fw-bold" id="category_id">${category.id}</p>
                    </div>
                    <div class="col-3">
                        <p style="font-size: 0.85rem;" class="fw-light mb-2">Category Title</p>
                        <p style="font-size: 0.85rem;"  class="fw-bold">${category.tenTheLoai}</p>
                    </div>
                `
                htmlCategory.append(loadCategoryHtml);
            })
    }
    findById()

    function updateCategory(){
        let categoryId = $("#category_id").text()
        let categoryTenTheLoai = $("#category_tenTheLoai").val()
        console.log(categoryId)
        console.log(categoryTenTheLoai)
        let data;
        axios.put("/api/categories/" + categoryId,{
           "tenTheLoai": categoryTenTheLoai
        })
        .then(function(respone){
            data = respone.data;
            console.log(data)
            findById()
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

    $("#btn-update-category").click(function (){
        updateCategory();
    })
})