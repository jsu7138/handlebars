var main = {
    init: function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },
    /*
        Json 을 반환 타입으로받으면 Body 부분이 비어있음을 나타냄. 아래의 해결 방법중 하나를 이용한다.
        dataType을 지워라
        dataType = ‘text’ 로 해라
        header값에 캐릭터셋을 UTF-8로 오도록 설정해라.
    */
    save: function() {
        var data = {
            title: $('#title').val(),
            "member_name": $('#member_name').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success:function(data){
                $("tbody").append(`<tr>
                <td>${data.member_id}</td>
                <td>${data.member_name}</td>
                <td>${data.post_id}</td>
                <td>${data.title}</td>
                <td>${data.content}</td>
                </tr>`);
            },
            error: function(e){
                alert(e.status + "! 멤버를 찾을 수 없음");
                console.log(e);
            }
        });
    }
};

main.init();