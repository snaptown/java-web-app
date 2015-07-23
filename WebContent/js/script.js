function show(){
	var loginform = document.getElementById('loginform');
	loginform.style.display = 'block';

	// var loginbox = document.getElementById('login-form');
	// var signupbox = document.getElementById('register-form');

	// if(signupbox.style.display === 'block'){
	// 	signupbox.style.display = 'none';
	// 	loginboxs.style.display = 'block';
	// }
	// else if(loginbox.style.display === 'block'){
	// 	loginbox.style.display = 'none';
	// 	signupbox.style.display = 'block';
	// }
}
function setEvents(){
	var login = document.getElementById('login');
	var register = document.getElementById('register');
	login.addEventListener('click', function() { 
		show();
	}, false);

	register.addEventListener('click', function() { 
		show();
	}, false);
}

var geolocation = {
	lat: 0,
	lng: 0,
	initialize: function initialize() {
		var mapCanvas = document.getElementById('map-canvas');
		var mapOptions = {
			center: new google.maps.LatLng(this.lat, this.lng),
			zoom: 17,
			mapTypeId: google.maps.MapTypeId.HYBRID
		};
		var map = new google.maps.Map(mapCanvas, mapOptions);
	},
	address: '',
	setAddressOnPage: function () {
			var address = '';
            var geocoder = new google.maps.Geocoder();
            var latlng = new google.maps.LatLng(this.lat, this.lng);
            geocoder.geocode({
                'latLng': latlng
            }, function (results, status) 
            {
                if (status === google.maps.GeocoderStatus.OK) 
                {
                    address_components = results[0].address_components;

                    if (results[0]) 
                    {  
                        address = results[0].formatted_address;
                    }                  
                    else 
                    {
                        address = 'Не е намерен адреса.';
                    }
                } 
                else 
                {
                    address = 'Address is not found.(sea)';
                }
                document.getElementById('address').innerHTML = address;
            });
        }
};





$(function() {/*
		geolocation.lat = 42.668975;
		geolocation.lng = 23.266237;
		geolocation.setAddressOnPage();
    $('#show-map').click(function(e) {
		geolocation.initialize();
		$('#show-map').css({'display':'none'});
		$('#hide-map').css({'display':'inline-block'});
		$('#map-canvas').css({'display':'block'});
	    $('html, body').animate({
	        scrollTop: $("#map-canvas").offset().top
	    }, 300);
	});*/
    $('#hide-map').click(function(e) {
		$('#show-map').css({'display':'inline-block'});
		$('#hide-map').css({'display':'none'});
		$('#map-canvas').css({'display':'none'});
	});

    $('#login').click(function(e) {
		$("#login-form").fadeIn(0);
 		$("#register-form").fadeOut(0);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register').click(function(e) {
		$("#register-form").fadeIn(0);
 		$("#login-form").fadeOut(0);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});	

	$('#loginform').click(function() {
	//Hide the menus if visible
		$('#loginform').css({'display':'none'});
	});
	$('.panel-login').click(function(event){
	    event.stopPropagation();
	});
});





$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});






