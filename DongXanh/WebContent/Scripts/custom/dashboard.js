$(document).ready(function() {
	
	$.ajax({ //Not found in cache, get from server
		url: 'statisticPromotions',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function (data) {
			//alert("hiiiii  " + data.dashboard.featurePromotion); 
			$("#statistic1_p").html(data.dashboard.currentPromotion);
			$("#statistic2_p").html(data.dashboard.featurePromotion);
			$("#statistic3_p").html(data.dashboard.nextFinishPomotion);
			$("#statistic4_p").html(data.dashboard.preFinishPromotion);
		}
	});
	
	$.ajax({ //Not found in cache, get from server
		url: 'listCustomerByBirthday1',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function (data) {
			//alert("hiiiii  " + JSON.stringify(data.birthdayCustomers)); 
			var str = "";
			var len = data.birthdayCustomers.length;
			if(len > 0){
				//for (var cus in data.birthdayCustomers) {
				for (var i=0; i<len; i++){
					//alert("hiiiii  " + data.birthdayCustomers[i].businessName); 
					str += "<li class='media event'><a " +
								"class='pull-left border-green profile_thumb'> <i " +
									"class='fa fa-user green'></i> " +
								"</a>";
					
					str += "<div class='media-body'> " +
								"<a class='title' href='#'>KH: "+data.birthdayCustomers[i].businessName+"</a>" +
								"<p>" +
								"	<strong>Ngày sinh nhật: "+data.birthdayCustomers[i].directorDomicile+" </strong>" +
								"</p>" +
								"<p>"+data.birthdayCustomers[i].groupCustomer.groupName+"</p>" +
								"<p>" +
								"	<small>Còn "+data.birthdayCustomers[i].totalVipCustomer+" ngày nữa</small>" +
								"</p>" +
							"</div>";
					
					str += "</li>";
				}
			}else{
				str += "<li class='media event'>Không có dữ liệu</li>";
			}
			
			$("#ulCussBirthday").html(str);
		}
	});
});

