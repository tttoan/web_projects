$(document).ready(function() {
	var RowNumber = 0;
	var ProductNumber = 0;
	var GiftNumber = 0;
	var groupCusOptions = null;
	var productOptions = null;
	var giftOptions = null;
	$('#PromotionTableContainer').jtable({
		title : 'Danh sách các chương trình khuyến mãi',
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)   
		sorting: true, //Enable sorting
		defaultSorting: 'product_name ASC',
		tableId : 'PromotionTable',
		toolbar: {
			items: [{
				icon: '/images/excel.png',
				text: 'Export to Excel',
				click: function () {
					//perform your custom job...
					alert("Supported not yet!^^")
				}
			},{
				icon: '/images/pdf.png',
				text: 'Export to Pdf',
				click: function () {
					//perform your custom job...
					alert("Supported not yet!^^")
				}
			}]
		},
		actions : {
			listAction : 'listPromotionAction',
			createAction : 'createPromotionAction',
			updateAction : 'updatePromotionAction',
			deleteAction : 'deletePromotionAction'
		},

		fields : {
			RowNumber : {
				title: 'No',
				width : '1%',
				sorting: false,
				edit: false,
				create: false,
				display: function () {
					RowNumber++;
					return RowNumber;
				}
			},
			promotion_id : {
				type: 'hidden',
			},
			//CHILD TABLE DEFINITION FOR "CUSTOMER"
            Customer: {
                title: '',
                width: '3%',
                sorting: false,
                edit: false,
                create: false,
                listClass: 'child-opener-image-column',
                display: function (Customer) {
                    if(Customer.record.customerRegist == 1){
                    	 var $img = $('<img class="child-opener-image" src="images/user-group.png" title="Khách hàng đăng ký" />');
                         //Open child table when user clicks the image
                         $img.click(function () {
                         	 window.location = 'promotion_register.jsp?promotion_id='+Customer.record.promotion_id+'&group_customer_id='+Customer.record.group_customer_id;
                         	 //document.location.href='listPromotionRegisterAction?promotion_id='+Customer.record.promotion_id+'&group_customer_id='+Customer.record.group_customer_id;
//                        	 $.ajax({ //Not found in cache, get from server
//									url: 'listPromotionRegisterAction?promotion_id='+Customer.record.promotion_id+'&group_customer_id='+Customer.record.group_customer_id,
//									type: 'POST',
//									dataType: 'json',
//									async: false
//                        	 });
                         });
                         //Return image to show on the person row
                         return $img;
                    }
                }
            },
			//CHILD TABLE DEFINITION FOR "GIFT"
            Gift: {
                title: '',
                width: '3%',
                sorting: false,
                edit: false,
                create: false,
                listClass: 'child-opener-image-column',
                display: function (promotionGift) {
                    //Create an image that will be used to open child table
                    var $img = $('<img class="child-opener-image" src="images/gift.png" title="Quà tặng" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#PromotionTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
		                        	title: '[' + promotionGift.record.promotionName + '] - Quà tặng',
									paging: true, //Enable paging
									pageSize: 10, //Set page size (default: 10) 
									actions: {
										listAction : 'listPromotionGiftAction?promotion_id='+promotionGift.record.promotion_id,
										createAction : 'createPromotionGiftAction?promotion_id='+promotionGift.record.promotion_id,
										updateAction : 'updatePromotionGiftAction?promotion_id='+promotionGift.record.promotion_id,
										deleteAction : 'deletePromotionGiftAction?promotion_id='+promotionGift.record.promotion_id,
									},
                                    fields: {
                                    	GiftNumber : {
                            				title: 'No',
                            				width : '1%',
                            				sorting: false,
                            				edit: false,
                            				create: false,
                            				display: function () {
                            					GiftNumber++;
                            					return GiftNumber;
                            				}
                            			},
                                    	id: {
        									key: true,
        									create: false,
        									edit: false,
        									list: false
        								},
        								gift_id: {
        									title: 'Quà tặng',
        									width: '30%',
        									edit : true,
        									options: function () {

        										if (giftOptions) { //Check for cache
        											return giftOptions;
        										}

        										var options = [];

        										$.ajax({ //Not found in cache, get from server
        											url: 'getAllGiftsAction',
        											type: 'POST',
        											dataType: 'json',
        											async: false,
        											success: function (data) {
        												//alert("hiiiii  " + JSON.stringify(data)); 
        												if (data.result != 'OK') {
        													alert(data.message);
        													return;
        												}
        												options = data.gifts;
        												//alert("333  " + JSON.stringify(options));
        											}
        										});
        										//alert("gifts  " + data.gifts);
        										return giftOptions = options; //Cache results and return options
        									}
        								},
        								unit: {
        									title: 'Đơn vị',
        									width: '10%',
        								},
        								price: {
        									title: 'Giá tiền',
        									width: '10%',
        									inputClass: 'validate[required,custom[number]]',
    										listClass: 'currency-style',
    										display: function (data) {
    											return formatCurrency(data.record.price);
    										}
        								},
        								maxQuantity: {
        									title: 'Số thùng phải đạt',
        									width: '10%',
        									inputClass: 'validate[required,custom[integer]]',
        									listClass: 'num-style',
        								},
        								maxPoint: {
        									title: 'Số điểm phải đạt',
        									width: '10%',
        									inputClass: 'validate[required,custom[integer]]',
        									listClass: 'num-style',
        								},
//        								formula: {
//        									title: 'Công thức',
//        									width: '24%',
//        									type: 'textarea',
//        									defaultValue: '$ketqua = ($diemth>=$diemdk); return ($diemth-$diemdk)+" điểm";',
//        									list: false
//        								}
                                    },
                                    //Initialize validation logic when a form is created
                            		formCreated: function (event, data) {
                            			data.form.validationEngine();
                            		},
                            		//Validate form when it is being submitted
                            		formSubmitting: function (event, data) {
                            			//alert("formSubmitting! " + data);
                            			return data.form.validationEngine('validate');
                            		},
                            		//Dispose validation logic when form is closed
                            		formClosed: function (event, data) {
                            			//alert("formClosed! " + data);
                            			data.form.validationEngine('hide');
                            			data.form.validationEngine('detach');
                            		},
                                    loadingRecords: function (event, data) {
                            			GiftNumber = 0;
                            		},
                                }, function (data) { //opened handler
                                    data.childTable.jtable('load');
                                });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            //CHILD TABLE DEFINITION FOR "EXAMS"
            Product: {
                title: '',
                width: '3%',
                sorting: false,
                edit: false,
                create: false,
                listClass: 'child-opener-image-column',
                display: function (promotionProduct) {
                    //Create an image that will be used to open child table
                    var $img = $('<img class="child-opener-image" src="images/product.png" title="Sản phẩm áp dụng"   />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                    	
                    	//alert(promotionProduct.record.promotion_id);
                    	//document.getElementById("promotion_id").value=promotionProduct.record.promotion_id;
                    	//document.forms["setPromotionId"].submit();
                    	
                        $('#PromotionTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                title: '[' + promotionProduct.record.promotionName + '] - Sản phẩm áp dụng',
                                paging: true, //Enable paging
                        		pageSize: 10, //Set page size (default: 10) 
                                actions: {
                                	listAction : 'listPromotionProductAction?promotion_id='+promotionProduct.record.promotion_id,
                        			createAction : 'createPromotionProductAction?promotion_id='+promotionProduct.record.promotion_id,
                        			updateAction : 'updatePromotionProductAction?promotion_id='+promotionProduct.record.promotion_id,
                        			deleteAction : 'deletePromotionProductAction?promotion_id='+promotionProduct.record.promotion_id,
                                },
                                fields: {
                                	ProductNumber : {
                        				title: 'No',
                        				width : '1%',
                        				sorting: false,
                        				edit: false,
                        				create: false,
                        				display: function () {
                        					ProductNumber++;
                        					return ProductNumber;
                        				}
                        			},
                                    id: {
                                        key: true,
                                        create: false,
                                        edit: false,
                                        list: false
                                    },
                                    product_id: {
                                        title: 'Sản phẩm',
                                        width: '50%',
                                        edit : true,
                                        options: function () {
                                                
                                                if (productOptions) { //Check for cache
                                                    return productOptions;
                                                }

                                                var options = [];

                                                $.ajax({ //Not found in cache, get from server
                                                    url: 'getAllProductsAction',
                                                    type: 'POST',
                                                    dataType: 'json',
                                                    async: false,
                                                    success: function (data) {
                                                   	 //alert("hiiiii  " + JSON.stringify(data)); 
                                                        if (data.result != 'OK') {
                                                            alert(data.message);
                                                            return;
                                                        }
                                                        options = data.products;
                                                        //alert("333  " + JSON.stringify(options));
                                                    }
                                                });
                                               // alert("products  " + (options));
                                                return productOptions = (options); //Cache results and return options
                                            }
                                    },
//                                    maxQuantity: {
//                                        title: 'Số thùng đạt',
//                                        width: '20%',
//                                    },
                                    maxPoint: {
                                        title: 'Điểm cho mỗi thùng',
                                        width: '20%',
                                        inputClass: 'validate[required,custom[integer]]'
                                    }
                                },
                              //Initialize validation logic when a form is created
                        		formCreated: function (event, data) {
                        			data.form.validationEngine();
                        		},
                        		//Validate form when it is being submitted
                        		formSubmitting: function (event, data) {
                        			//alert("formSubmitting! " + data);
                        			return data.form.validationEngine('validate');
                        		},
                        		//Dispose validation logic when form is closed
                        		formClosed: function (event, data) {
                        			//alert("formClosed! " + data);
                        			data.form.validationEngine('hide');
                        			data.form.validationEngine('detach');
                        		},
                                loadingRecords: function (event, data) {
                        			ProductNumber = 0;
                        		},
                            }, function (data) { //opened handler
                                data.childTable.jtable('load');
                            });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            
			id :{
				key: true,
				create: false,
				edit: false,
				list: false
			},
			group_customer_id : {
				title : 'Nhóm khách hàng',
				width : '15%',
				edit : true,
				 options: function () {
                     
                     if (groupCusOptions) { //Check for cache
                         return groupCusOptions;
                     }

                     var options = [];

                     $.ajax({ //Not found in cache, get from server
                         url: 'getAllGroupCustomerAction',
                         type: 'POST',
                         dataType: 'json',
                         async: false,
                         success: function (data) {
                        	 //alert("hiiiii  " + JSON.stringify(data)); 
                             if (data.result != 'OK') {
                                 alert(data.message);
                                 return;
                             }
                             options = data.groupCustomers;
                             //alert("333  " + JSON.stringify(options));
                         }
                     });
                     //alert("4444  " + data.categories);
                     return groupCusOptions = options; //Cache results and return options
                 }
			},
			promotionName : {
				title : 'Tên khuyến mãi',
				width : '20%',
				edit : true,
				inputClass: 'validate[required]'
			},
			startDate : {
				title : 'Bắt đầu',
				width : '10%',
				edit : true,
				type: 'date',
				displayFormat: 'dd-mm-yy',
				inputClass: 'validate[required]'
			},
			endDate : {
				title : 'Kết thúc',
				width : '10%',
				edit : true,
				type: 'date',
				displayFormat: 'dd-mm-yy',
				inputClass: 'validate[required]'
			},
			remarks : {
				title : 'Đặc tả',
				width : '25%',
				edit : true,
				type: 'textarea',
			},
			customerRegist: {
                title: 'Khách hàng đăng ký',
                width: '10%',
                type: 'checkbox',
                values: { '0': 'Không', '1': 'Có' },
                defaultValue: '1',
                create: true,
				edit: true,
				list: false
            },
			status: {
                 title: 'Tình trạng',
                 width: '15%',
                 type: 'checkbox',
                 values: { 'false': 'Kết thúc', 'true': 'Áp dụng' },
                 defaultValue: 'true',
                 create: false,
 				 edit: true,
 				 //listClass: 'promotion-status',
             },
             rule: {
            	 title: 'Công thức',
            	 type: 'textarea',
            	 defaultValue: '$ketqua = ($diemth>=$diemdk);\nif($ketqua){\n	if($diemth>$diemdk){\n		return "Nợ dư: "+format(($diemth-$diemdk)*$giatien)+ "VNĐ";\n	}\n}else{\n	return "Nợ âm: "+format(($diemdk-$diemth)*$giatien)+ "VNĐ";\n}',
            	 list: false,
            	 inputClass: 'validate[required]'
             }
		},
		//Initialize validation logic when a form is created
		formCreated: function (event, data) {
			//alert("formCreated! " + data); 
			//data.form.find('input[name="product_name"]').addClass('validate[required]');
			//data.form.find('input[name="emailId"]').addClass('validate[required,custom[email]]');
			//data.form.find('input[name="Password"]').addClass('validate[required]');
			//data.form.find('input[name="BirthDate"]').addClass('validate[required,custom[date]]');
			//data.form.find('input[name="Education"]').addClass('validate[required]');
			data.form.validationEngine();
		},
		//Validate form when it is being submitted
		formSubmitting: function (event, data) {
			//alert("formSubmitting! " + data);
			return data.form.validationEngine('validate');
		},
		//Dispose validation logic when form is closed
		formClosed: function (event, data) {
			//alert("formClosed! " + data);
			data.form.validationEngine('hide');
			data.form.validationEngine('detach');
		},
		//Validate form when it is being submitted
		loadingRecords: function (event, data) {
			//alert("loadingRecords! " + data);
			//alert("333  " + JSON.stringify(data));
			//return data.form.validationEngine('validate');
			RowNumber = 0;
		},
	});
	$('#PromotionTableContainer').jtable('load');
});


function sortObject(obj) {
    var arr = [];
    var prop;
    for (prop in obj) {
        if (obj.hasOwnProperty(prop)) {
            arr.push({
                'key': prop,
                'value': obj[prop]
            });
        }
    }
    arr.sort(function(a, b) {
        return a.value - b.value;
    });
    return arr; // returns array
}


function formatCurrency(num){
	var n = num.toString(), p = n.indexOf('.');
	return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){return p<0 || i<p ? ($0+',') : $0; }) + ' VNĐ';
}

