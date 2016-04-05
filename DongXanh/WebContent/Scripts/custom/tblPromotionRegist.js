$(document).ready(function() {
	var RowNumber = 0;
	var ProductNumber = 0;
	var GiftNumber = 0;
	var CustomerRegisterOptions = null;
	var productOptions = null;
	var giftOptions = null;
	var vPromotion_id = $('[name="promotion_id"]').val();
	var vGroup_customer_id = $('[name="group_customer_id"]').val();
	
	$('#RegisterTableContainer').jtable({
		title : 'Danh sách khách hàng đăng ký',
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
			listAction : 'listPromotionRegisterAction?promotion_id='+vPromotion_id+'&group_customer_id='+vGroup_customer_id,
			createAction : 'createPromotionRegisterAction?promotion_id='+vPromotion_id+'&group_customer_id='+vGroup_customer_id,
			updateAction : 'updatePromotionRegisterAction?promotion_id='+vPromotion_id+'&group_customer_id='+vGroup_customer_id,
			deleteAction : 'deletePromotionRegisterAction?promotion_id='+vPromotion_id+'&group_customer_id='+vGroup_customer_id,
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
                    var $img = $('<img class="child-opener-image" src="images/gift.png" title="Đăng ký quà tặng" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#RegisterTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
		                        	title: '[' + promotionGift.record.id + '] - Đăng ký quà tặng',
									paging: true, //Enable paging
									pageSize: 10, //Set page size (default: 10) 
									actions: {
										listAction : 'listRegisterGiftAction?promotion_id='+vPromotion_id+'&register_id='+promotionGift.record.id,
										createAction : 'createRegisterGiftAction?promotion_id='+vPromotion_id+'&register_id='+promotionGift.record.id,
										updateAction : 'updateRegisterGiftAction?promotion_id='+vPromotion_id+'&register_id='+promotionGift.record.id,
										deleteAction : 'deleteRegisterGiftAction?promotion_id='+vPromotion_id+'&register_id='+promotionGift.record.id,
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
        								p_gift_id: {
        									title: 'Quà tặng',
        									width: '40%',
        									edit : true,
        									options: function () {

        										if (giftOptions) { //Check for cache
        											return giftOptions;
        										}

        										var options = [];

        										$.ajax({ //Not found in cache, get from server
        											url: 'getRegisterGiftsAction?promotion_id='+vPromotion_id,
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
        								total: {
                                            title: 'Số lượng quà',
                                            width: '20%',
                                            inputClass: 'validate[required,custom[integer]]'
                                        },
        								applyAll: {
        									title: 'Áp dụng cho tất cả khách hàng',
        									list: false,
        									type: 'checkbox',
        				                    values: { 'false': 'Không áp dụng', 'true': 'Áp dụng' },
        				                    defaultValue: 'false'
        								}
                                    },
                                  //Initialize validation logic when a form is created
                            		formCreated: function (event, data) {
                            			//alert("formCreated! " + data); 
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
                    var $img = $('<img class="child-opener-image" src="images/product.png" title="Đăng ký sản phẩm"   />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                    	
                    	//alert(promotionProduct.record.promotion_id);
                    	//document.getElementById("promotion_id").value=promotionProduct.record.promotion_id;
                    	//document.forms["setPromotionId"].submit();
                    	
                        $('#RegisterTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                title: '[' + promotionProduct.record.id + '] - Đăng ký sản phẩm',
                                paging: true, //Enable paging
                        		pageSize: 10, //Set page size (default: 10) 
                                actions: {
                                	listAction : 'listRegisterProductAction?promotion_id='+vPromotion_id+'&register_id='+promotionProduct.record.id,
                        			createAction : 'createRegisterProductAction?promotion_id='+vPromotion_id+'&register_id='+promotionProduct.record.id,
                        			updateAction : 'updateRegisterProductAction?promotion_id='+vPromotion_id+'&register_id='+promotionProduct.record.id,
                        			deleteAction : 'deleteRegisterProductAction?promotion_id='+vPromotion_id+'&register_id='+promotionProduct.record.id,
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
                                    p_product_id: {
                                        title: 'Sản phẩm',
                                        width: '50%',
                                        edit : true,
                                        options: function () {
                                                
                                                if (productOptions) { //Check for cache
                                                    return productOptions;
                                                }

                                                var options = [];

                                                $.ajax({ //Not found in cache, get from server
                                                    url: 'getRegisterProductsAction?promotion_id='+vPromotion_id,
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
                                    point: {
                                        title: 'Số điểm đăng ký',
                                        width: '20%',
                                        inputClass: 'validate[required,custom[integer]]'
                                    },
                                    box: {
                                        title: 'Số thùng đăng ký',
                                        width: '20%',
                                        inputClass: 'validate[required,custom[integer]]'
                                    }
                                },
                                //Initialize validation logic when a form is created
                        		formCreated: function (event, data) {
                        			//alert("formCreated! " + data); 
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
			customer_id : {
				title : 'Khách hàng',
				width : '40%',
				edit : true,
				 options: function () {
                     
                     if (CustomerRegisterOptions) { //Check for cache
                         return CustomerRegisterOptions;
                     }

                     var options = [];

                     $.ajax({ //Not found in cache, get from server
                         url: 'getCustomerRegisterAction',
                         type: 'POST',
                         dataType: 'json',
                         async: false,
                         success: function (data) {
                        	 //alert("hiiiii  " + JSON.stringify(data)); 
                             if (data.result != 'OK') {
                                 alert(data.message);
                                 return;
                             }
                             options = data.mapCustomers;
                             //alert("333  " + JSON.stringify(options));
                         }
                     });
                     //alert("4444  " + data.categories);
                     return CustomerRegisterOptions = options; //Cache results and return options
                 }
			},
			totalPoint : {
				title : 'Tổng số điểm đăng ký',
				width : '20%',
				create: true,
				edit: true,
				inputClass: 'validate[required,custom[integer]]'
			},
			totalBox : {
				title : 'Tổng số thùng đăng ký',
				width : '20%',
				create: true,
				edit: true,
				inputClass: 'validate[required,custom[integer]]'
			},
			
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
	$('#RegisterTableContainer').jtable('load');
});


