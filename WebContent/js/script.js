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

$(function() {

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
	function visialisation(element){
		// element
	}
	$('#loginform').click(function() {
	//Hide the menus if visible
		$('#loginform').css({'display':'none'});
	});

	$('.panel-login').click(function(event){
	    event.stopPropagation();
	});
});






// $('html').click(function() {
// //Hide the menus if visible
// 	// alert('a');
// 	// $('.row').innerHTML = 'dasdsa';
// 	// document.getElementsByClassName('panel-login')[0].style.display = 'none';
// });

// $('.panel-login').click(function(event){
//     event.stopPropagation();
// });










