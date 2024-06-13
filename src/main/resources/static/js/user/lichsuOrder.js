$(document).ready(function() {
    axios.get('/api/oder/getOderByUser',{
        params: {
            name: sessionStorage.getItem("user_name")
        }
    })
        .then(response => {
            var ArrayObj = response.data.data;


            function loadData(data){

                let lichsudonhang = $('#lichsudonhang');
                lichsudonhang.empty();


                if(data == null){
                    let row = `
                    <tr>
                      <td colspan="5">
                        <div class="alert alert-info" role="alert">
                          Không có thông tin!
                        </div>
                      </td>
                    </tr>
                `;
                    lichsudonhang.append(row);
                }
                var ArrayObjNew = [];
                let maDonHangSet = new Set(); // loại bỏ truy lặp

                for (let i = 0; i < data.length; i++) {
                    if (!maDonHangSet.has(data[i].maDonHang)) {
                        maDonHangSet.add(data[i].maDonHang);
                        ArrayObjNew.push(data[i]);
                    }
                }

                ArrayObjNew.forEach(function (obj,index){
                    let row = `
                      <tr>
                          <td>${obj.ngayLapDon}</td>
                          <td>${obj.maDonHang}</td>
                          <td>${obj.productE.tenSanPham} + .......</td>
                          <td>${parseFloat(obj.tongTienThanhToan).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</td>
                          ${obj.trangThaiThanhToan ? `<td style="color: green">Đã xử lý</td>`: `<td style="color: red">Đã hủy</td>`}
                          <td><a href="/order-history-detaill/${obj.maDonHang}" class="nav-link text-primary">Chi tiết</a></td>
                      </tr>
                      `;
                    lichsudonhang.append(row);
                })
            }

            loadData(ArrayObj);

            // Filter button click event
            $('#filterButton').on('click', function() {
                let startDate = $('#startDate').val();
                let endDate = $('#endDate').val();
                let startMoney = $('#startMoney').val();
                let endMoney = $('#endMoney').val();

                if ((startDate && endDate)) {
                    let filteredData = ArrayObj.filter(function(item) {
                        let itemDate = new Date(item.ngayLapDon);
                        let itemPrice = parseFloat(item.tongTienThanhToan);

                        let isDateInRange = (!startDate || itemDate >= new Date(startDate)) && (!endDate || itemDate <= new Date(endDate));
                        let isPriceInRange = (!startMoney || itemPrice >= startMoney) && (!endMoney || itemPrice <= endMoney);

                        return isDateInRange && isPriceInRange;
                    });
                    loadData(filteredData);
                }else {
                    Swal.fire({
                        title: "Thông báo từ hệ thống",
                        text: "Vui lòng nhập 2 ô ngày để lọc.",
                        icon: "question"
                    });
                }
            });

        })
        .catch(error => {
            // Xử lý lỗi nếu có
            console.error(error);
        });
})