const myModal = document.getElementById('myModal')
const myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', () => {
    myInput.focus()
})

function deleteProduct(id, detail) {
    axios.delete('/api/products/' + id)
        .then(function (response) {
            console.log('Xóa sản phẩm thành công!');
            detail ? window.location.replace("/admin/product-list") : window.location.reload()
        })
        .catch(function (error) {
            console.error('Đã xảy ra lỗi:', error);
        });
}