function Post() {
    var questionId = $('#input_id').val();
    var commentTxt =$('#comment_id').val();
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
                $("#comment_section").hide();
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
