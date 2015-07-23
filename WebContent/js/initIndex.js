$(document).ready(function() {

    $('#login').click(function(e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: 'https://b20db94c.ngrok.io/snaptown/login',
            data: {
                username: $("#username").val(),
                password: $("#password").val()
            },
            statusCode: {
                    202: function(){
                        window.location.replace('../home.html');
                    }
                    ,
                    401: function(){
                        window.location.replace('../index.html');
                    }
                }
        });
        

    });

});