$(document).ready(function() {
    // Lấy đường dẫn của URL
    let pathname = window.location.pathname;

    // Tách đường dẫn thành các phần tử trong mảng
    let pathParts = pathname.split('/');

    // Lấy phần tử cuối cùng trong mảng, đó là giá trị bạn cần
    let orderId = pathParts[pathParts.length - 1];

    axios.get('/api/oder/getOderByMaDonHang',{
        params: {
            madonhang: orderId
        }
    })
        .then(response => {
            console.log(response.data.data)
            var ArrayOBJ = response.data.data

            var listsp = $('#listsp');
            listsp.empty();

            ArrayOBJ.forEach(function (obj,index){
                var nguoinhan = "Người nhận: "+obj.userE.email;
                var ngaylaphoadon = "Ngày tạo: "+obj.ngayLapDon;
                $('#tongGiatri').text(parseFloat(obj.tienThanhToan).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }));
                $('#nguoinhan').text(nguoinhan);
                $('#ngaylaphoadon').text(ngaylaphoadon);
                $('.maHoaDon').text(obj.maDonHang);
                $('#soluongdonhang').text(obj.soLuongMua);

                let row = `
                    <div class="col-3 mt-2">
                      <img class="img-fluid rounded" src="/images/${obj.productE.anhSanPham}" alt="">
                    </div>
                    <div class="col-7 mt-2">
                      <h6>${obj.productE.tenSanPham}</h6>
                      <p style="font-size: 0.85rem;">${obj.productE.tenSanPham}</p>
                      <div>
                        <p class="text-success">Đã xử lý</p>
                      </div>
                    </div>
                    <div class="col-2 text-end mt-2">
                      <del><p style="font-size: 0.9rem;" class="m-0 fw-bold">${parseFloat(obj.productE.giaSanPham).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</p></del>
                    </div>
                  `;
                listsp.append(row);
            })
        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error(error);
        });
})