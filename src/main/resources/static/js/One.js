
//提交回复
function Post() {
    var questionId = $('#input_id').val();
    var commentTxt =$('#comment_id').val();
    if (!commentTxt){
        alert("回复内容为空！！！！");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
            "parentId":questionId,
            "content":commentTxt,
            "type":1
        }),
        success:function (response) {
            if(response.code === 200){
                window.location.reload();
                // $("#comment_section").hide();
            }else{
                if (response.code === 200){
                   var isAccepted = confirm(response.message);
                   if (isAccepted){
                        window.open("http://github.com/login/oauth/authorize?client_id=b3b546e022ca3539a8bb&redirect_url=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                   }
                }else{
                    alert(response.message);
                }

            }
        },
        dataType:"json"
    });
}


//显示二级回复
function collapseComments(e) {
    let id =e.getAttribute("data-id");
    var comments =$("#comment-"+id);
    comments.toggleClass("in");
    // //获取一下二级评论展开状态
    // var collapse = e.getAttribute("data-collapse");
    // if (collapse){
    //     comments.hasClass("in");
    //     e.removeAttribute("data-collapse");
    // }else{
    //     //展开二级评论
    //     comments.addClass("in");
    //     //标记二级评论状态
    //     e.setAttribute("data-collapse","in");
    // }

}
