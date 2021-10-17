function post() {
    var question =$("#question_id").val()
    var comment =$("#comment_question").val()
    common(question,comment,1)
}

function twoComment() {
    var question =$("#question_id").val()
    var comment =$("#twoContent").val()
    common(question,comment,2)
}


function common(parentId,content,type) {
    console.log(parentId)
    console.log(content)
    console.log(type)
    if (parentId ==null){
        alert("请先登入")
    }else{
        $.ajax({
            type: "POST",//请求类型
            url: "/twoComment",//请求的url
            data: {
                parentId: parentId,
                content:content,
                type:type,
            },//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                /*刷新界面，重新加载*/
                window.location.reload();
            },

        });
    }


}


/*回复框判断！*/
$(document).ready(function(){


    $("#comment_question").bind("blur",function(){
        var comment =$("#comment_question").val();
        if ( comment.length==0 ){
            alert("回复内容不能为空！");
        }else{
            $("#tishi").html("内容符合要求");
            $("#tishi").css("color","green")
        }
    });
});

function showTowComment(e) {

    var question =$("#question_id").val()
    var id =e.getAttribute("data-id");
    var openComment=$("#comment-"+id);
    var menuStats=e.getAttribute("menuStats");
    if (menuStats){
        /*删除class*/
        openComment.removeClass("in");
        e.removeAttribute("menuStats");
    }else{

        //下拉查询数据
        $.ajax({
            type: "POST",//请求类型
            url: "/twoComment",//请求的url
            data: {
                id: question,
                type:2
            },//请求参数
            dataType: "json",//ajax接口（请求url）返回的数据类型
            success: function (data) {//data：返回数据（json对象）
                console.log(data)
                var commentBody =$("#comment-body"+id)
                $.each(data.data.reverse(),function (index,comment) {
                    console.log(comment)
                    var c =$("<div/>",{
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments",
                        html:comment.content
                        /*问题comment.content 和自带的content冲突，无法调用json值 */
                    });

                })

                /*给class添加后缀 in 使下拉框打开*/
                openComment.addClass("in");
                /*判断下拉框状态*/
                e.setAttribute("menuStats","in");

            },

        });

    }


}

//展示所有标签
function showTag() {
    //展示标签页
    $("#divTag").show()
}
//选择标签
function selectTag(e) {
    var data = e.getAttribute("data-tag");
    var  value=  $("#tag").val()
    //如果当前标签没有添加才可以添加
    if (value.indexOf(data) == -1){
        if (value){
            $("#tag").val(value+','+data)
        }else{
            $("#tag").val(data)
        }
    }
}
