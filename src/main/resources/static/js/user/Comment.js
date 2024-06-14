$(document).ready(function (){
    getComment(commentsPerPage)
})
var commentsPerPage = 5;
let currentCommentIndex = 0;
let Comments = [];
function getComment(commentsPerPage) {
    let slug = window.location.pathname.split("/")[2]
    axios.get("/api/comments/getCommentByProduct_Slug/"+slug)
        .then(function(respone){
            Comments = respone.data
            console.log(Comments)
            if(Comments.length <= 5){
                $("#btn-load-more-comments").hide();
            }
            let html = $("#html-comment");
            html.empty();
            if(Comments.length > 0){
                for(let i = 0; i < commentsPerPage; i++){
                    let comment = Comments[i];
                    let row =
                        `
                            <div class="col-1">
                                <img class="rounded-circle" src="/img-user/${comment.user.anhDaiDien}" alt="" width="60px" height="60px">
                            </div>
                            <div class="col-11" id="div-comment-${comment.sysIdComment}">
                                <h6>${comment.user.tenDangNhap}</h6>
                                <p class="text-secondary mb-0">Bình luận vào ${formatDate(comment.ngayBinhLuan)}</p>
                                <p class="mb-0" id="id-comment-first-${comment.sysIdComment}">${comment.noiDung}</p>
                                <button class="btn ps-0 pb-3 text-primary" style=" border: none;" data-username="${comment.user.tenDangNhap}" id="Comment-btn-${comment.sysIdComment}">Trả lời</button>
                                <div class="row" id="html-reply-${comment.sysIdComment}">
                                    
                                </div>
                                <div class="row mb-3" id="div-input-text-${comment.sysIdComment}" style="display: none;">
                                    <div class="col-1">
                                        <div class="d-flex justify-content-center align-items-center">
                                            <img class="rounded-circle" src="#" id="userReplyAvatar-${comment.sysIdComment}" alt="" width="50px " height="50px">
                                        </div>
                                    </div>
                                    <div class="col-11">
                                        <div class="input-group justify-content-center align-items-center" style="height:100%">
                                            <input type="text" class="form-control" id="input-text-${comment.sysIdComment}" style="height:80%">
                                            <button type="button" class="input-group-text"  style="height:80%" id="btn-reply-${comment.sysIdComment}" onclick="saveReply(${comment.sysIdComment})">
                                                <i class="fa-solid fa-paper-plane"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `
                    html.append(row);
                    let html_reply = $(`#html-reply-${comment.sysIdComment}`);
                    let replies = comment.replies;
                    for (let y = 0; y < replies.length; y++) {
                        let reply = replies[y];
                        let row_reply =
                            `
                                <div class="col-1">
                                    <img class="rounded-circle" src="/img-user/${reply.user.anhDaiDien}" alt="" width="50px" height="50px">
                                </div>
                                <div class="col-11 mb-3">
                                    <h6>${reply.user.tenDangNhap}</h6>
                                    <p class="text-secondary mb-0">Bình luận vào ${formatDate(reply.ngayTraLoi)}</p>
                                    <p class="mb-0">${reply.noiDung}</p>
                                    <button class="btn ps-0 text-primary" style="border: none;" id="Reply-btn-${reply.sysIdReply}" data-username="${reply.user.tenDangNhap}">Trả lời</button>
                                </div>
                            `;
                        html_reply.append(row_reply);
                        $(`#Reply-btn-${reply.sysIdReply}`).last().click(function(){
                            setTextInput(`${comment.sysIdComment}`,$(this).data("username"));
                        })
                    }
                    $(`#Comment-btn-${comment.sysIdComment}`).last().click(function(){
                        setTextInput(`${comment.sysIdComment}`,$(this).data("username"));
                    })
                    if(userAuth === null){
                        $(`#userReplyAvatar-${comment.sysIdComment}`).attr("src", `/img-user/anh-khach-hanggg.png`);
                    }else{
                        $(`#userReplyAvatar-${comment.sysIdComment}`).attr("src", `/img-user/${userAuth.anhDaiDien}`);
                    }
                }
                currentCommentIndex += commentsPerPage;
            }else {
                html.append(`<h3> Hiện tại chưa có bình luận nào</h3>`);
            }
        })

}
$("#btn-load-more-comments").click(function(){
    getComment(Comments.length);
    $("#btn-load-more-comments").hide();
})
function saveComment(){
    console.log(userAuth)
    if(userAuth == null){
        swal.fire({
            title: "Error",
            text: "Bạn cần đăng nhập để bình luận",
            icon: "error",
            confirmButtonText: "OK"
        })
            .then(function(){
                window.location.href = "/log-in"
            })
    }
    let noiDung = $("#comment-content").val();
    let sysIdUser = userAuth.sysIdUser;
    let productId = product.id;

    let commentDTO = {
        noiDung: noiDung,
        user: {
            sysIdUser: sysIdUser
        },
        product: {
            id: productId
        }
    };
    console.log(commentDTO)
    axios.post("/api/comments",commentDTO)
        .then(function(respone){
            console.log(respone.data)
            $("#comment-content").val("");
            return getComment(commentsPerPage)
        })
        .catch(function(error) {
            console.error(error);
        });
}

function saveReply(IdComment){
    let noiDungComment = $(`#id-comment-first-${IdComment}`).text();
    let noiDung = $(`#input-text-${IdComment}`).val();
    let sysIdUser = userAuth.sysIdUser;
    let sysIdComment = IdComment;

    let replyDTO = {
        noiDung: noiDungComment,
        replies:[
            {
                noiDung: noiDung,
                user: {
                    sysIdUser: sysIdUser
                }
            }
        ]
    };
    console.log(replyDTO)
    axios.put("/api/comments/"+ sysIdComment, replyDTO)
        .then(function(respone){
            console.log(respone.data)
            return getComment(commentsPerPage)
        })
        .catch(function(error) {
            console.error(error);
        });
}

function formatDate(date) {
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [day, month, year].join('/');
}

function setTextInput (id, text) {
    $(`#div-input-text-${id}`).show();
    $(`#input-text-${id}`).val("@ "+text+" ")
    $(`#input-text-${id}`).focus();
}