$(document).ready(function() {
    loadDetailPageVoucher()

});
const detailVoucherElement = $('#voucherDetailCode');
const detailVoucher = detailVoucherElement.data('detail');
console.log(detailVoucher)

function loadDetailPageVoucher() {
    axios.get(`/api/vouchers/${detailVoucher}`)
        .then(response => {
            let {data} = response.data
            console.log(data);

            let detailVoucher = $('#detailVoucher')
            detailVoucher.html('');
            detailVoucher.append(loadDetailVoucher(data))

            // Create Voucher
            $("#updateDetailVoucher").submit(function (e) {
                e.preventDefault();
                if (!checkValidDate("createVoucherSDate", "createVoucherEDate")) {
                    let voucher = {
                        voucherName: $("#createVoucherTitle").val(),
                        voucherPercentage: $("#createVoucherPercent").val(),
                        startDate: $("#createVoucherSDate").val(),
                        endDate: $("#createVoucherEDate").val(),
                        description: $("#createVoucherDescription").val()
                    };
                    console.log(voucher)
                    let voucherID = $("#createVoucherID").val()
                    console.log(voucherID)
                    axios({
                        method: 'put',
                        url: `/api/vouchers/${voucherID}`,
                        data: JSON.stringify(voucher),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                        .then(function (response) {
                            console.log('Update voucher thành công!');
                            loadDetailPageVoucher(data)
                        })
                        .catch(function (error) {
                            console.error('Đã xảy ra lỗi:', error);
                        });
                } else {
                    console.log("CHuan bai")
                }
            });
        })
        .catch(error => {
            console.error(error);
        });
}

function loadDetailVoucher(data) {
    let today = new Date();
    let startDate = new Date(data.startDate);
    let endDate = new Date(data.endDate);
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
    return `
            <div class="row mt-3">
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Voucher ID</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.sysIdVoucher}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Voucher Code</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.codeVoucher}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Voucher Title</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.voucherName}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Voucher Percent</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.voucherPercentage}%</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Start Date</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.startDate}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">End Date</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold">${data.endDate}</p>
                </div>
                <div class="col-3">
                    <p style="font-size: 0.85rem;" class="fw-light mb-2">Status</p>
                    <p style="font-size: 0.85rem;"  class="fw-bold text-${alert}">${status}</p>
                </div>
            </div>
            <hr>
            <form id="updateDetailVoucher">
                <div class="row">
                    <div class="col-12">
                        <div class="d-flex align-items-center h-100">
                            <div>
                                <p class="fw-bold" style="font-size: 0.87rem;">Description</p>
                                <p class="m-0 overflow-hidden" style="font-size: 0.87rem;">${data.description}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="createVoucherID" disabled value="${data.sysIdVoucher}"  placeholder="Voucher ID">
                            <label>Voucher ID</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="createVoucherCode" disabled value="${data.codeVoucher}"  placeholder="Voucher Code">
                            <label>Voucher Code</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="createVoucherTitle" value="${data.voucherName}"  placeholder="Voucher Title" required>
                            <label>Voucher Title</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="createVoucherPercent" value="${data.voucherPercentage}" min="1"  max="100"  placeholder="Voucher Percent" required>
                            <label>Voucher Percent</label>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="createVoucherSDate" value="${data.startDate}"  placeholder="Start Date" required>
                            <label>Start Date</label>
                            <div class="invalid-feedback">The start date must be before the end date.</div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="createVoucherEDate" value="${data.endDate}"  placeholder="End Date" required>
                            <label>End Date</label>
                            <div class="invalid-feedback">The end date must be after the start date.</div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-floating mb-3">
                            <textarea class="form-control" id="createVoucherDescription"  placeholder="Description" style="height: 100px" required>${data.description}</textarea>
                            <label>Description</label>
                        </div>
                    </div>
                    <div>
                        <button class="btn btn-primary" type="submit">Save change</button>
                    </div>
                </div>
            </form>
        `
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


