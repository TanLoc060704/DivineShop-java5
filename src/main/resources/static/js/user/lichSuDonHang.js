$(document).ready(function() {
    axios.get('/api/v1/payment/lichSuDonHangAll',{
        params: {
            user: sessionStorage.getItem("user_name")
        }
    })
        .then(response => {
            var ArrayObj = response.data.data;


            function loadData(data){
                let listLichSu = $('#lichsugiaodich');
                listLichSu.empty();

                if(data == null){
                    let row = `
                    <tr>
                      <td colspan="4">
                        <div class="alert alert-info" role="alert">
                          Không có thông tin!
                        </div>
                      </td>
                    </tr>
                `;
                    listLichSu.append(row);
                }

                data.forEach(function (obj,index){
                    let formattedOriginalPrice = parseFloat(obj.sotien).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    let row = `
                    <tr>
                      <td>${obj.thoigian}</td>
                      <td>Số ID đơn hàng: #${obj.mota}</td>
                      <td>+${formattedOriginalPrice}</td>
                      ${obj.trangthai == true ? `<td style="color: green">Thành công</td>` : `<td style="color: red">Thất bại</td>`}
                    </tr>
                `;
                    listLichSu.append(row);
                });
            }

            loadData(ArrayObj);

            // Filter button click event
            $('#filterButton').on('click', function() {
                let startDate = $('#startDate').val();
                let endDate = $('#endDate').val();
                let startMoney = $('#startMoney').val();
                let endMoney = $('#endMoney').val();
                let mota = $('#mota').val().toLowerCase();

                if ((startDate && endDate)) {
                    let filteredData = ArrayObj.filter(function(item) {
                        let itemDate = new Date(item.thoigian);
                        let itemPrice = parseFloat(item.sotien);
                        let itemMota = item.mota;

                        let isDateInRange = (!startDate || itemDate >= new Date(startDate)) && (!endDate || itemDate <= new Date(endDate));
                        let isPriceInRange = (!startMoney || itemPrice >= startMoney) && (!endMoney || itemPrice <= endMoney);
                        let isMota = !mota || itemMota.includes(mota);

                        return isDateInRange && isPriceInRange && isMota;
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