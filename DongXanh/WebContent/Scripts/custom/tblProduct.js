$(document).ready(function() {
	var RowNumber = 0;  
	var categoryOptions = null;
	$('#ProductTableContainer').jtable({
		title : 'Danh sách sản phẩm',
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)   
		sorting: true, //Enable sorting
		defaultSorting: 'product_name ASC',
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
			listAction : 'listAction',
			createAction : 'createAction',
			updateAction : 'updateAction',
			deleteAction : 'deleteAction'
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
			id :{
				key: true,
				create: false,
				edit: false,
				list: false
			},
//			productType : {
//				title : 'Loại sản phẩm',
//				width : '20%',
//				edit : true,
//				options: { 'TTS': 'Thuốc trừ sâu', 'TTC': 'Thuốc trừ cỏ' }
//			},
			category_id : {
				title : 'Loại sản phẩm',
				width : '20%',
				edit : true,
				 options: function () {
                     
                     if (categoryOptions) { //Check for cache
                         return categoryOptions;
                     }

                     var options = [];

                     $.ajax({ //Not found in cache, get from server
                         url: 'getAllCategoriesAction',
                         type: 'POST',
                         dataType: 'json',
                         async: false,
                         success: function (data) {
                        	 //alert("hiiiii  " + JSON.stringify(data)); 
                             if (data.result != 'OK') {
                                 alert(data.message);
                                 return;
                             }
                             options = data.categories;
                             //alert("333  " + JSON.stringify(options));
                         }
                     });
                     //alert("4444  " + data.categories);
                     return categoryOptions = options; //Cache results and return options
                 }
			},
			productName : {
				title : 'Tên sản phẩm',
				width : '20%',
				edit : true,
				inputClass: 'validate[required]'
			},
			description : {
				title : 'Đặc tả',
				width : '30%',
				edit : true,
				type: 'textarea',
			},
			unitPrice : {
				title : 'Đơn giá',
				width : '10%',
				edit : true,
				defaultValue:0
			},
			minQuantity : {
				title : 'Số lượng',
				width : '10%',
				edit : true,
				defaultValue:0
			},
			exportDate : {
				title : 'Hạn dùng',
				width : '10%',
				edit : true,
				type: 'date',
				displayFormat: 'dd-mm-yy',
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
			//return data.form.validationEngine('validate');
			RowNumber = 0;
		},
	});
	$('#ProductTableContainer').jtable('load');
});
