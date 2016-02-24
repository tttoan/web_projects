$(document).ready(function() {
	var RowNumber = 0;  
	var groupCusOptions = null;
	$('#PromotionTableContainer').jtable({
		title : 'Danh sách các chương trình khuyến mãi',
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
			//CHILD TABLE DEFINITION FOR "PHONE NUMBERS"
            Phones: {
                title: '',
                width: '5%',
                sorting: false,
                edit: false,
                create: false,
                display: function (studentData) {
                    //Create an image that will be used to open child table
                    var $img = $('<img src="/Content/images/Misc/phone.png" title="Edit phone numbers" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#StudentTableContainer').jtable('openChildTable',
                                $img.closest('tr'),
                                {
                                    title: studentData.record.Name + ' - Phone numbers',
                                    actions: {
                                        listAction: '/Demo/PhoneList?StudentId=' + studentData.record.StudentId,
                                        deleteAction: '/Demo/DeletePhone',
                                        updateAction: '/Demo/UpdatePhone',
                                        createAction: '/Demo/CreatePhone'
                                    },
                                    fields: {
                                        StudentId: {
                                            type: 'hidden',
                                            defaultValue: studentData.record.StudentId
                                        },
                                        PhoneId: {
                                            key: true,
                                            create: false,
                                            edit: false,
                                            list: false
                                        },
                                        PhoneType: {
                                            title: 'Phone type',
                                            width: '30%',
                                            options: { '1': 'Home phone', '2': 'Office phone', '3': 'Cell phone' }
                                        },
                                        Number: {
                                            title: 'Phone Number',
                                            width: '30%'
                                        },
                                        RecordDate: {
                                            title: 'Record date',
                                            width: '20%',
                                            type: 'date',
                                            displayFormat: 'yy-mm-dd',
                                            create: false,
                                            edit: false
                                        }
                                    }
                                }, function (data) { //opened handler
                                    data.childTable.jtable('load');
                                });
                    });
                    //Return image to show on the person row
                    return $img;
                }
            },
            //CHILD TABLE DEFINITION FOR "EXAMS"
            Exams: {
                title: '',
                width: '5%',
                sorting: false,
                edit: false,
                create: false,
                display: function (studentData) {
                    //Create an image that will be used to open child table
                    var $img = $('<img src="/Content/images/Misc/note.png" title="Edit exam results" />');
                    //Open child table when user clicks the image
                    $img.click(function () {
                        $('#StudentTableContainer').jtable('openChildTable',
                                $img.closest('tr'), //Parent row
                                {
                                title: studentData.record.Name + ' - Exam Results',
                                actions: {
                                    listAction: '/Demo/ExamList?StudentId=' + studentData.record.StudentId,
                                    deleteAction: '/Demo/DeleteExam',
                                    updateAction: '/Demo/UpdateExam',
                                    createAction: '/Demo/CreateExam'
                                },
                                fields: {
                                    StudentId: {
                                        type: 'hidden',
                                        defaultValue: studentData.record.StudentId
                                    },
                                    StudentExamId: {
                                        key: true,
                                        create: false,
                                        edit: false,
                                        list: false
                                    },
                                    CourseName: {
                                        title: 'Course name',
                                        width: '40%'
                                    },
                                    ExamDate: {
                                        title: 'Exam date',
                                        width: '30%',
                                        type: 'date',
                                        displayFormat: 'yy-mm-dd'
                                    },
                                    Degree: {
                                        title: 'Degree',
                                        width: '10%',
                                        options: ["AA", "BA", "BB", "CB", "CC", "DC", "DD", "FF"]
                                    }
                                }
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
				width : '20%',
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
				title : 'Tên chương trình khuyến mãi',
				width : '20%',
				edit : true,
				inputClass: 'validate[required]'
			},
			startDate : {
				title : 'Ngày bắt đầu',
				width : '10%',
				edit : true,
				type: 'date',
				displayFormat: 'dd-mm-yy',
				inputClass: 'validate[required]'
			},
			endDate : {
				title : 'Ngày kết thúc',
				width : '10%',
				edit : true,
				type: 'date',
				displayFormat: 'dd-mm-yy',
				inputClass: 'validate[required]'
			},
			remarks : {
				title : 'Đặc tả',
				width : '30%',
				edit : true,
				type: 'textarea',
			},
			status: {
                 title: 'Tình trạng',
                 width: '12%',
                 type: 'checkbox',
                 values: { 'false': 'Kết thúc', 'true': 'Áp dụng khuyến mãi' },
                 defaultValue: 'true',
                 create: false,
 				 edit: true,
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
	$('#PromotionTableContainer').jtable('load');
});

