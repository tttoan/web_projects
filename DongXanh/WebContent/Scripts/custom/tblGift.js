$(document).ready(function() {
	var RowNumber = 0; 
	$('#GiftTableContainer').jtable({
		title : 'Danh sách quà tặng',
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)   
		sorting: true, //Enable sorting
		defaultSorting: 'giftName ASC',
		actions : {
			listAction : 'listGiftAction',
			createAction : 'createGiftAction',
			updateAction : 'updateGiftAction',
			deleteAction : 'deleteGiftAction'
		},
		fields : {
			RowNumber : {
				title: 'No',
				width : '5%',
				edit: false,
				create: false,
				sorting: false,
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
			giftName : {
				title : 'Tên quà tặng',
				width : '95%',
				edit : true,
				inputClass: 'validate[required]'
			}
		},
		//Initialize validation logic when a form is created
		formCreated: function (event, data) {
			//alert("formCreated! " + data); 
			//data.form.find('input[name="name"]').addClass('validate[required]');
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
	$('#GiftTableContainer').jtable('load');
});

