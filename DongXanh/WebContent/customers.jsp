<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="#" method="post" enctype="multipart/form-data"
							cssClass=" form-label-left" theme="bootstrap">
							<s:hidden name="custId" value="%{custId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<s:if test="hasActionErrors()">
								<div class="errors">
									<s:actionerror escape="false" />
								</div>
							</s:if>
							<s:elseif test="hasActionMessages()">
								<div class="message">
									<s:actionmessage escape="false" />
								</div>
							</s:elseif>
                          
							<div class="item form-group">
							    <div  style="padding-left: 10px;padding-bottom: 0px ;padding-top: 10px" class="row"> 
							           <div class="control-label col-md-2 col-sm-2 col-xs-12" style="padding-top: 10px;font-size: 15px ;max-width: 170px">DSKH theo NVTT</div>
							             <div class="control-label col-md-2 col-sm-2 col-xs-12"> 
							                  <s:select id="searchlistUser" name="varCusByUser"
												list="listUser" class="form-control  col-md-12 col-sm-12 col-xs-12"
												showDownArrow="false" autoComplete="false" headerKey="" headerValue="---tất cả---"
												listKey="userName" listValue="userName"											
												/>   
							             </div> 
							           <div class="control-label col-md-2 col-sm-2 col-xs-12 " style="font-size: 15px ;padding-top: 7px">Phân loại khách hàng</div>
							            <div class="control-label col-md-2 col-sm-2 col-xs-12" style="height: 50px;">
							                  <s:select id="searchlistCustomerType" name="searchlistCustomerType"
												 list="listCustomerType"   class="form-control  col-md-12 col-xs-12"
												showDownArrow="false" autoComplete="false" headerKey="-1" headerValue="---tất cả---"
																				
												/>  
							           </div>
							            <div class="control-label col-md-4 col-sm-4 col-xs-12 " style="font-size: 15px ">
							                <a href="#openImportLevel1"	class="btn btn-primary"><b>Import Cấp I</b></a> 
							                <a href="#openImportLevel2" class="btn btn-success"><b>Import Cấp II</b></a>
							            </div>
							          
							        
							          
							    </div>
							    <div class="clearfix"></div>
							    <div style="padding-left: 10px;padding-bottom: 0px ;padding-top: 7px" class="row">
							            <div class="control-label col-md-2 col-sm-2 col-xs-12 " style="padding-top: 7px;font-size: 15px ;max-width: 170px; ">Khách hàng cấp I</div>
							            
							            <div class="control-label col-md-2 col-sm-2 col-xs-12" style="height: 50px;" >
							                
							                    <s:select id="searchlistCustomerToRank" name="searchlistCustomerToRank"
											    list="listCustomerToRank"   class="form-control  col-md-12 col-xs-12" style="padding:0px"
												showDownArrow="false" autoComplete="false" headerKey="-1" headerValue="---tất cả---"
												 listKey="id" listValue="businessName"											
												/>  
							               
							                 
							            </div>
							            
							            <div class="control-label col-md-4 col-sm-4 col-xs-12 " style="font-size: 15px ;padding-left: 0px ">
							                 <button class="btn btn-success" onclick="searchDetail();" type="button"><b>Xem kết quả</b></button>
							            </div>
							             <div class="control-label col-md-4 col-sm-4 col-xs-12" style="font-size: 15px ">	
								            <a href="#openModal" class="btn btn-warning"><b>Ẩn/Hiện	thông tin</b></a> 
								            <button type="button" class="btn btn-primary" id="btnFilter" onclick="btnExport()"><b>Xuất Excel</b></button>
																			
								       </div>
							           
							           
							    </div>
							    
							  
								
							</div>

						</s:form>
						</div>

						<div class="clearfix"></div>
<%-- 						<s:set var="rId"> --%>
<%-- 							<s:property value="%{userSes.role.roleId}" /> --%>
<%-- 						</s:set> --%>
                        <div class="table_content">
                        <table id="example" class="jambo_table display nowrap cell-border"
							style="width: 100%">
							<thead>
								<tr class="headings">

									<s:iterator value="listTableColumn" status="rowStatus">
										<th><s:property /></th>
									</s:iterator>
									<%--
									 <s:if test="%{#rId == 1}">
										<th class=" no-link last"><span class="nobr"></span></th>
									</s:if> 
									--%>
									<th class="no-link last"></th>

								</tr>
							</thead>
						</table>
                        </div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:include value="customer_define_column.jsp" />
	<s:include value="customer_level1_define_import.jsp" />
	<s:include value="customer_level2_define_import.jsp" />
	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->

</div>
<!-- /page content -->
</div>

</div>


<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>

<!-- Datatables -->
<script src="js/jquery-1.12.3.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.fixedHeader.min.js"></script>

<script src="js/moment.js"></script>

<style>
.btn{
 width: 144px!important;
 margin-left: 7px!important;
}
.x_content{
	border: 1px solid #becfe6;
	background-color: #fcfcfc;
	 border-radius: 10px;
}
.table_content{
    border: 1px solid #becfe6;
    margin-top: 10px;
    padding: 18px 10px 10px 10px;
     border-radius: 10px;
}
/* div.dt-buttons {
    float: right;
    margin-left:10px; */
}

.currency-style {
	text-align: right!important;
}

.num-style {
	text-align: center;
}

.view_pro {
	margin: 0px;
	text-align: left;
	padding: 5px 10px 5px 10px;
	margin: 0px 0px 5px 0px;
	border-style: outset;
}

th {
	text-align: center;
	vertical-align: middle;
}
.title_search{
 float: left;
}
.form-control {
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    color: #555;
    display: block;
    font-size: 14px;
    height: 34px;
    line-height: 1.42857;
    padding: 6px 12px;
    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
    width: 100%;
}
.form-control:focus {
    border-color: #66afe9;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6);
    outline: 0 none;
}
  .Hidden{
      display: none;
    }
</style>

<script>
 	$(document).ready(function() {
 		$('#col4_filter').on('keyup click', function() {
 	 		//alert(getCookie("columnActive"));
			filterColumn($(this).attr('step'));
		});
 		
 		
	/* 	$('#col4_filter,#col1_filter,#col2_filter,#col3_filter').change(function() {
			var varCusByUser = {
				"varCusByUser" : $("#col4_filter").val(),
				"varCusAssign" : $('#col1_filter').prop('checked'),
				"varCusNotAssign" : $('#col2_filter').prop('checked'),
				"varCusByLevel1" : $('#col3_filter').prop('checked'),
			};
			$.ajax({
				url : "ParameterSession",
				data : JSON.stringify(varCusByUser),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true
			});
			var table = $('#example').DataTable();
			table.ajax.reload( null, false );
			
		}); */
	});

 	function btnExport(){
 		var searchlistUser           = document.getElementById("searchlistUser").value;
 		var searchlistCustomerType   = document.getElementById("searchlistCustomerType").value;
 		var searchlistCustomerToRank = document.getElementById("searchlistCustomerToRank").value;
 		var varCusAssign = false;
 		var varCusNotAssign = false;
 		
 		if(searchlistCustomerType =="DSKH đã phân công"){
 			varCusAssign= true;
 		}else if(searchlistCustomerType =="DSKH chưa phân công"){
 			 varCusNotAssign = true;
 		}else{
 			varCusAssign= true;
 			varCusNotAssign = true;
 		}  
 	 
 		 var hearder        = getCookieColumnVisible("columnActive");
 		 var href           = "export_customer?hearder="+hearder+"&searchlistUser="+searchlistUser+"&varCusAssign="+varCusAssign;
 		 var href           = href + "&varCusNotAssign="+varCusNotAssign+"&searchlistCustomerToRank="+searchlistCustomerToRank;
 		
 		location.href		= href;
	}
 	function searchDetail(){
 		var searchlistUser           = document.getElementById("searchlistUser").value;
 		var searchlistCustomerType   = document.getElementById("searchlistCustomerType").value;
 		var searchlistCustomerToRank = document.getElementById("searchlistCustomerToRank").value;
 		var varCusAssign = false;
 		var varCusNotAssign = false;
 		if(searchlistCustomerType =="DSKH đã phân công"){
 			varCusAssign= true;
 		}else if(searchlistCustomerType =="DSKH chưa phân công"){
 			 varCusNotAssign = true;
 		}else{
 			varCusAssign= true;
 			varCusNotAssign = true;
 		}
 		// varCusAssign = false;
 		// varCusNotAssign = false;
 		var varCusByUser = {
				"varCusByUser" : searchlistUser,
				"varCusAssign" : varCusAssign,
				"varCusNotAssign" : varCusNotAssign,
				"varCusByLevel1" : searchlistCustomerToRank,
			};
 		
 		$.ajax({
			url : "ParameterSession",
			data : JSON.stringify(varCusByUser),
			dataType : 'json',
			contentType : 'application/json',
			type : 'POST',
			async : true
		});
		var table = $('#example').DataTable();
		table.ajax.reload( null, false );
 	}
	
 	function getCookieColumnVisible(cname) {
 	    var name = cname + "=";
 	    var ca = document.cookie.split(';');
 	    for(var i = 0; i <ca.length; i++) {
 	        var c = ca[i];
 	        while (c.charAt(0)==' ') {
 	            c = c.substring(1);
 	        }
 	        if (c.indexOf(name) == 0) {
 	            return c.substring(name.length,c.length);
 	        }
 	    }
 	    return "";
 	}

 	function isColumnVisible(index) {
 	    var value = getCookieColumnVisible("columnActive");
 	    var ca = value.split('-');
 	        if (ca[index-1].indexOf(1) > -1) {
 	            return true;
 	        }
 	    return false;
 	}
	
     $(document).ready(function () {
    	var searchlistUser           = document.getElementById("searchlistUser").value;
  		var searchlistCustomerType   = document.getElementById("searchlistCustomerType").value;
  		var searchlistCustomerToRank = document.getElementById("searchlistCustomerToRank").value;
  		var varCusAssign = false;
  		var varCusNotAssign = false;
  		if(searchlistCustomerType =="DSKH đã phân công"){
  			varCusAssign= true;
  		}else if(searchlistCustomerType =="DSKH chưa phân công"){
  			 varCusNotAssign = true;
  		}else{
  			varCusAssign= true;
  			varCusNotAssign = true;
  		}
  		// varCusAssign = false;
  		// varCusNotAssign = false;
  		var varCusByUser = {
 				"varCusByUser" : searchlistUser,
 				"varCusAssign" : varCusAssign,
 				"varCusNotAssign" : varCusNotAssign,
 				"varCusByLevel1" : searchlistCustomerToRank,
 			};
  		
  		$.ajax({
 			url : "ParameterSession",
 			data : JSON.stringify(varCusByUser),
 			dataType : 'json',
 			contentType : 'application/json',
 			type : 'POST',
 			async : true
 		});
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
         $("#example").DataTable({
             "processing": true, // for show progress bar
             "serverSide": true, // for process server side
             "filter": true, // this is for disable filter (search box)
             "orderMulti": false, // for disable multiple column at once
             "scrollX" : true,
             "fixedHeader": true,
          //   dom: 'Bfrtip',
             aLengthMenu: [
                           [10,25, 50, 100, -1],
                           [10,25, 50, 100, "All"]
                       ],
           
         
            //  "pageLength": 50,
              dom: 'lBfrtip', 
              buttons: ['excel'] , 
             
             "ajax": {
                 "url": "listCustomerJSonAction",
                 "type": "POST",
                " data": "{status:tttoan, name: name}",
                 "datatype": "json",
             },
              "columns": [               
                     /*1*/{ "data": "no",  "autoWidth": true,"visible": isColumnVisible(1) },
                     /*2*/{ "data": "createTime", "autoWidth": true ,"visible": isColumnVisible(2)
                    	 ,  "render": function ( data, type, full, meta ) {
                    		/*  if(lc==1) {
                    			 alert(data);
                    			 var searchlistUser           = "";
                    		 	 var searchlistCustomerType   = "";
                    		 	 var searchlistCustomerToRank = "";
                    		 		
								$('#searchlistUser').val(searchlistUser);
								$('#searchlistCustomerType').val(searchlistUser);
								$('#searchlistCustomerToRank').val(searchlistUser);
							}
                    		 lc=10;                    		 */
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     /*3*/{ "data": "customerCode", "autoWidth": true,"visible": isColumnVisible(3) },
                     /*4*/{ "data": "groupCustomer.groupName", "autoWidth": true,"visible": isColumnVisible(4) },
                     /*5*/{ "data": "user.fullName", "autoWidth": true,"visible": isColumnVisible(5) },
                     /*6*/{ "data": "statisticName", "autoWidth": true,"visible": isColumnVisible(6)},
                     /*7*/{ "data": "businessName", "autoWidth": true,"visible": isColumnVisible(7)},
                     /*8*/{ "data": "certificateNumber", "autoWidth": true,"visible": isColumnVisible(8)},
                     /*9*/{ "data": "certificateDate", "autoWidth":  true,"visible": isColumnVisible(9) 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     /*10*/{ "data": "certificateAddress", "autoWidth": true,"visible": isColumnVisible(10) },
                     /*11*/{ "data": "taxNumber", "autoWidth": true,"visible": isColumnVisible(11) },
                     /*12*/{ "data": "budgetRegister", "autoWidth": true,"visible": isColumnVisible(12) },
                     /*13*/{ "data": "telefone", "autoWidth": true,"visible": isColumnVisible(13) },
                     /*14*/{ "data": "fax", "autoWidth": true,"visible": isColumnVisible(14) },
                     /*15*/{ "data": "email", "autoWidth": true,"visible": isColumnVisible(15) },
                     /*16*/{ "data": "socialAddress", "autoWidth": true,"visible": isColumnVisible(16) },
                     /*17*/{ "data": "businessAddress", "autoWidth": true,"visible": isColumnVisible(17) },
                     /*18*/{ "data": "adviser", "autoWidth": true,"visible": isColumnVisible(18) },
                     /*19*/{ "data": "director", "autoWidth": true,"visible": isColumnVisible(19) },
                     /*20*/{ "data": "directorMobile", "autoWidth": true,"visible": isColumnVisible(20) },
                     /*21*/{ "data": "directorBirthday", "autoWidth":  true,"visible": isColumnVisible(21) 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     /*22*/{ "data": "directorDomicile", "autoWidth": true,"visible": isColumnVisible(22) },
                     /*23*/{ "data": "sellMan", "autoWidth": true,"visible": isColumnVisible(23) },
                     /*24*/{ "data": "sellManMobile", "autoWidth": true,"visible": isColumnVisible(24) },
                     /*25*/{ "data": "budgetOriginal", "autoWidth": true,"visible": isColumnVisible(25)},
                     /*26*/{ "data": "otherBusiness", "autoWidth": true,"visible": isColumnVisible(26)},
                     /*27*/{ "data": "customerByCustomer5Level1Id.businessName", "autoWidth": true,"visible": isColumnVisible(27) },
                     /*28*/{ "data": "customer5Percent", "autoWidth": true,"visible": isColumnVisible(28) },
                     /*29*/{ "data": "customerByCustomer4Level1Id.businessName", "autoWidth": true,"visible": isColumnVisible(29)},
                     /*30*/{ "data": "customer4Percent", "autoWidth": true,"visible": isColumnVisible(30) },
                     /*31*/{ "data": "customerByCustomer3Level1Id.businessName", "autoWidth": true,"visible": isColumnVisible(31) },
                     /*32*/{ "data": "customer3Percent", "autoWidth": true,"visible": isColumnVisible(32) },
                     /*33*/{ "data": "customerByCustomer2Level1Id.businessName", "autoWidth": true,"visible": isColumnVisible(33) },
                     /*34*/{ "data": "customer2Percent", "autoWidth": true,"visible": isColumnVisible(34) },
                     /*35*/{ "data": "customerByCustomer1Level1Id.businessName", "autoWidth": true,"visible": isColumnVisible(35) },
                     /*36*/{ "data": "customer1Percent", "autoWidth": true,"visible": isColumnVisible(36) },
                     /*37*/{ "data": "product1Hot", "autoWidth": true,"visible": isColumnVisible(37) },
                     /*38*/{ "data": "product2Hot", "autoWidth": true,"visible": isColumnVisible(38) },
                     /*39*/{ "data": "product3Hot", "autoWidth": true,"visible": isColumnVisible(39) },
                     /*40*/{ "data": "product4Hot", "autoWidth": true,"visible": isColumnVisible(40) },
                     /*41*/{ "data": "product5Hot", "autoWidth": true,"visible": isColumnVisible(41) },
                     /*42*/{ "data": "product6Hot", "autoWidth": true,"visible": isColumnVisible(42)},
                     /*43*/{ "data": "farmProduct1", "autoWidth": true,"visible": isColumnVisible(43) },
                     /*44*/{ "data": "farmProduct1Session", "autoWidth": true,"visible": isColumnVisible(44) },
                     /*45*/{ "data": "farmProduct2", "autoWidth": true,"visible": isColumnVisible(45) },
                     /*46*/{ "data": "farmProduct2Session", "autoWidth": true,"visible": isColumnVisible(46) },
                     /*47*/{ "data": "farmProduct3", "autoWidth": true,"visible": isColumnVisible(47)},
                     /*48*/{ "data": "farmProduct3Session", "autoWidth": true,"visible": isColumnVisible(48) },
                     /*49*/{ "data": "farmProduct4", "autoWidth": true,"visible": isColumnVisible(49)},
                     /*50*/{ "data": "farmProduct4Session", "autoWidth": true,"visible": isColumnVisible(50) },
                   
                     { "data": "id"
                    	 ,  "render": function ( data, type, full, meta ) {
                    		/*   return '<a href="view_customer?custId='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Xem <a>' + 
                    		 		'<a href="move_to_add_customer?custId='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa <a>' + 
                    				'<a href="delete_customer?custId='+data+'" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa <a>'; 
                    		  */
                    		  return '<a href="move_to_add_customer?custId='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa <a>' + 
             				'<a href="delete_customer?custId='+data+'" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa <a>'; 
                     	}
                     }
                       
                   
             ] 
         });
      //  alert(lc);
         var table = $('#example').DataTable();
         $('#example').on('dblclick', 'tr', function () {
              var data = table.row( this ).data();
              //alert( 'You dblclick on '+data.id+' row' );
               window.location = 'view_customer?custId='+data.id;
           } );
     });
     
     function formatCurrency(num){
    	 if(num != null){
    		var n = num.toString(), p = n.indexOf('.');
     		return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){return p<0 || i<p ? ($0+',') : $0; }) + ' VNĐ';
    	 }
     }
     
 </script>

<script>
	/* $(document).ready(function() {
		$('#example').DataTable({
			"scrollX" : true,
			 "columnDefs": [
			                {"targets": [ 7 ],"visible": false},
			                {"targets": [ 8 ],"visible": false},
			                {"targets": [ 9 ],"visible": false},
			                {"targets": [ 10 ],"visible": false},
			                {"targets": [ 11 ],"visible": false},
			                {"targets": [	13	],"visible": false},
			                {"targets": [	15	],"visible": false},	
			                {"targets": [	16	],"visible": false},	
			                {"targets": [	17	],"visible": false},	
			                {"targets": [	18	],"visible": false},	
			                {"targets": [	19	],"visible": false},	
			                {"targets": [	20	],"visible": false},	
			                {"targets": [	21	],"visible": false},	
			                {"targets": [	22	],"visible": false},	
			                {"targets": [	23	],"visible": false},	
			                {"targets": [	24	],"visible": false},	
			                {"targets": [	25	],"visible": false},	
			                {"targets": [	26	],"visible": false},	
			                {"targets": [	27	],"visible": false},	
			                {"targets": [	28	],"visible": false},	
			                {"targets": [	29	],"visible": false},	
			                {"targets": [	30	],"visible": false},	
			                {"targets": [	31	],"visible": false},	
			                {"targets": [	32	],"visible": false},	
			                {"targets": [	33	],"visible": false},	
			                {"targets": [	34	],"visible": false},	
			                {"targets": [	35	],"visible": false},	
			                {"targets": [	36	],"visible": false},	
			                {"targets": [	37	],"visible": false},	
			                {"targets": [	38	],"visible": false},	
			                {"targets": [	39	],"visible": false},	
			                {"targets": [	40	],"visible": false},	
			                {"targets": [	41	],"visible": false},	
			                {"targets": [	42	],"visible": false},	
			                {"targets": [	43	],"visible": false},	
			                {"targets": [	44	],"visible": false},	
			                {"targets": [	45	],"visible": false},	
			                {"targets": [	46	],"visible": false},	
			                {"targets": [	47	],"visible": false},	
			                {"targets": [	48	],"visible": false},	
			                {"targets": [	49	],"visible": false},	

			            ]
		});

		$('input.column_filter').on('keyup click', function() {
			filterColumn($(this).attr('step'));
		});

		var table = $('#example').DataTable();
		$('#example tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#button').click(function() {
			table.row('.selected').remove().draw(false);
		});
		
	}); */

// 	function filterColumn(i) {
// 		var value;
// 		var table = $('#example').DataTable();
// 		if (i == 1 || i == 2) {
// 			var check1 = $('#col1_filter').prop('checked');
// 			var check2 = $('#col2_filter').prop('checked');
// 			if (check1 && check2) {
// 				value = '^.*$';
// 			} else if (check1) {
// 				value = '^.+$';
// 			} else if (check2) {
// 				value = '^\\s*$';
// 			} else {
// 				value = '^\\s+$';
// 			}
// 			table.column(4).search(value, true, true).draw();
// 		} else if (i == 3) {
// 			var isCheck = $('#col' + i + '_filter').prop('checked');
// 			if (isCheck) {
// 				table.column(i).search('1', true, true).draw();
// 			} else {
// 				table.column(i).search('^.*$', true, true).draw();
// 			}
// 		} else {
// 			value = $('#col' + i + '_filter').val();
// 			if (i == 3) {
// 				table.column(3).search(value, true, true).draw();
// 			} else if (i == 4) {
// 				table.column(4).search(value, true, true).draw();
// 			}
// 		}
// 	}
</script>

</body>

</html>