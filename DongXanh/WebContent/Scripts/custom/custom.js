//Change curentcy format
function formatCurrency(num){
	var n = num.toString(), p = n.indexOf('.');
	return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){return p<0 || i<p ? ($0+',') : $0; }) + ' VNĐ';
}


//Only input special character
var keys = {
        UP: 38,
        DOWN: 40,
        ENTER: 13,
        TAB: 9,
        BACKSPACE: 8
};

function onlySpecialChar(event, pattern) {
	var regex = new RegExp(pattern);
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	
	//alert(event.charCode + ": " + key + "/" + regex + " = " + (regex.test(key)));
	
	if (key == null || key == '' || key == ' '
			|| event.charCode <= 0
			|| event.keyCode == keys.ENTER 
			|| event.keyCode == keys.TAB 
			|| event.keyCode == keys.UP 
			|| event.keyCode == keys.DOWN) {
		return true;
	}else{
		if (!regex.test(key)) {
			event.preventDefault();
			return false;
		}
	}
}

//Store value into cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + (cvalue) + ";" + expires + ";path=/";
    //alert(cname + "=" + (cvalue));
}
//Get value from cookie
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}