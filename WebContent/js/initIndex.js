$(document).ready(function() {

    $('#login-submit').click(function(e) {
        e.preventDefault();

        $.ajax({
            method: "POST",
            url: '/snaptown/login',
            data: {
                username: $("#username").val(),
                password: $("#password").val()
            },
            statusCode: {
                    202: function(){
                        window.location.replace('/snaptown/home.html');
                    }
                    ,
                    401: function(){
                        window.location.replace('/snaptown/index.html');
                    },
                    500: function(){
                        console.log("oooops");
                    }
                }
        });
    });

});