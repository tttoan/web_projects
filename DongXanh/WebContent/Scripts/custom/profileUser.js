$(document).ready(function() {
	$.ajax({ //Not found in cache, get from server
		url: 'currentUserLogin',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function (data) {
			//alert("hiiiii  " + data); 
			$("#profile_fullname").html(data.user.fullName);
			var usernameInfo = "<img src='images/user.png' alt=''>"+data.user.userName+" />"+
						"<span class=' fa fa-angle-down'></span>"; 
			$("#profile_username").html(data.user.userName);
		}
	});	
});

