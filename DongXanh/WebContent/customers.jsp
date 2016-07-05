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
							cssClass="form-horizontal form-label-left">
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
								<table style="width: 100%">
									<tr>
										<td width="50%">
											<div>
												<label for="col4_filter">DSKH theo NVTT </label>
												<div>
													<input type="text" id="col4_filter" step="4"
														class="column_filter col-md-7 col-xs-12 ">
												</div>
											</div>
										</td>
										<td>
											<div>
												<label for="col1_filter">DSKH đã phân công </label> <input
													type="checkbox" id="col1_filter" step="1" checked="checked"
													class="column_filter col-md-3 col-xs-3 ">
											</div>
										</td>
									</tr>
									<tr>
										<td></td>
										<td>
											<div>
												<label for="col2_filter">DSKH chưa phân công </label> <input
													type="checkbox" id="col2_filter" step="2" checked="checked"
													class="column_filter col-md-3 col-xs-3 ">
											</div>
										</td>
									</tr>
									<tr>
										<td><a href="#openModal" class="btn btn-warning"><b>Ẩn/Hiện
													thông tin</b></a> <a href="#openImportLevel1"
											class="btn btn-primary"><b>Import Cấp I</b></a> <a
											href="#openImportLevel2" class="btn btn-success"><b>Import
													Cấp II</b></a>
										<td>
											<div>
												<label for="col3_filter">DSKH theo Cấp 1 </label> <input
													type="checkbox" id="col3_filter" step="3"
													class="column_filter col-md-3 col-xs-3 ">
											</div>
										</td>
									</tr>
								</table>
							</div>

						</s:form>

						<div class="clearfix"></div>
						<s:set var="rId">
							<s:property value="%{userSes.role.roleId}" />
						</s:set>
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

							<%-- <tbody>
								<s:iterator value="customers" status="rowStatus">
									<tr class="even pointer">
										<td class=""><s:property value="#rowStatus.count" /></td>

										<td class=""><s:property
												value="%{getText('format.date',{createTime})}" /></td>
										<td class=""><s:property value="customerCode" /></td>
										<td class=""><s:property value="groupCustomer.groupName" />
										</td>
										<td class=""><s:property value="user.fullName" /></td>
										<td class=""><s:property value="statisticName" /></td>
										<td class=""><s:property value="businessName" /></td>
										<td class=""><s:property value="certificateNumber" /></td>
										<td class=""><s:property
												value="%{getText('format.date',{certificateDate})}" /></td>
										<td class=""><s:property value="certificateAddress" /></td>
										<td class=""><s:property value="taxNumber" /></td>
										<td class=""><s:property value="budgetRegister" /></td>
										<td class=""><s:property value="telefone" /></td>
										<td class=""><s:property value="fax" /></td>
										<td class=""><s:property value="email" /></td>
										<td class=""><s:property value="socialAddress" /></td>
										<td class=""><s:property value="businessAddress" /></td>
										<td class=""><s:property value="adviser" /></td>
										<td class=""><s:property value="director" /></td>
										<td class=""><s:property value="directorMobile" /></td>
										<td class=""><s:property
												value="%{getText('format.date',{directorBirthday})}" /></td>
										<td class=""><s:property value="directorDomicile" /></td>
										<td class=""><s:property value="sellMan" /></td>
										<td class=""><s:property value="sellManMobile" /></td>
										<td class=""><s:property value="budgetOriginal" /></td>
										<td class=""><s:property value="otherBusiness" /></td>
										<td class=""><s:property
												value="customerByCustomer5Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer5Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer4Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer4Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer3Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer3Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer2Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer2Percent})}" /></td>
										<td class=""><s:property
												value="customerByCustomer1Level1Id.businessName" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{customer1Percent})}" /></td>
										<td class=""><s:property value="product1Hot" /></td>
										<td class=""><s:property value="product2Hot" /></td>
										<td class=""><s:property value="product3Hot" /></td>
										<td class=""><s:property value="product4Hot" /></td>
										<td class=""><s:property value="product5Hot" /></td>
										<td class=""><s:property value="product6Hot" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct1})}" /></td>
										<td class=""><s:property value="farmProduct1Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct2})}" /></td>
										<td class=""><s:property value="farmProduct2Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct3})}" /></td>
										<td class=""><s:property value="farmProduct3Session" /></td>
										<td class=""><s:property
												value="%{getText('format.percent',{farmProduct4})}" /></td>
										<td class=""><s:property value="farmProduct4Session" /></td>

										<s:if test="%{#rId == 1}">
											<td class="last">
												<s:url action="view_customer"
													var="viewURL">
													<s:param name="custId" value="%{id}"></s:param>
												</s:url> <s:a href="%{viewURL}" cssClass="btn btn-info btn-xs">
													<i class="fa fa-pencil"></i> Xem </s:a>
													
												<s:url action="move_to_add_customer"
													var="editURL">
													<s:param name="custId" value="%{id}"></s:param>
												</s:url> <s:a href="%{editURL}" cssClass="btn btn-info btn-xs">
													<i class="fa fa-pencil"></i> Sửa </s:a>
													 
												<s:url
													action="delete_customer" var="deleteURL">
													<s:param name="custId" value="%{id}"></s:param>
												</s:url> <s:a href="%{deleteURL}" cssClass="btn btn-danger btn-xs">
													<i class="fa fa-trash-o"></i> Xóa </s:a></td>
										</s:if>

									</tr>
								</s:iterator>
							</tbody> --%>

						</table>
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



<!-- Datatables -->
<script src="js/jquery-1.12.3.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>

<script src="js/moment.js"></script>

<style>
.currency-style {
	text-align: right;
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
</style>

<script>
 	$(document).ready(function() {
 		$('#col4_filter').on('keyup click', function() {
			filterColumn($(this).attr('step'));
		});
		$('#col4_filter,#col1_filter,#col2_filter,#col3_filter').change(function() {
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
		});
	});
	
     $(document).ready(function () {
         $("#example").DataTable({
             "processing": true, // for show progress bar
             "serverSide": true, // for process server side
             "filter": true, // this is for disable filter (search box)
             "orderMulti": false, // for disable multiple column at once
             "scrollX" : true,
             "ajax": {
                 "url": "listCustomerJSonAction",
                 "type": "POST",
                 "datatype": "json"
             },
              "columns": [
                     { "data": "no",  "autoWidth": true },
                     { "data": "createTime", "autoWidth": true 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     { "data": "customerCode", "autoWidth": true },
                     { "data": "groupCustomer.groupName", "autoWidth": true },
                     { "data": "user.fullName", "autoWidth": true },
                     { "data": "statisticName", "autoWidth": true},
                     { "data": "businessName", "autoWidth": true},
                     { "data": "certificateNumber", "autoWidth": true},
                     { "data": "certificateDate", "autoWidth":  true 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     { "data": "certificateAddress", "autoWidth": true },
                     { "data": "taxNumber", "autoWidth": true },
                     { "data": "budgetRegister", "autoWidth": true },
                     { "data": "telefone", "autoWidth": true },
                     { "data": "fax", "autoWidth": true },
                     { "data": "email", "autoWidth": true },
                     { "data": "socialAddress", "autoWidth": true },
                     { "data": "businessAddress", "autoWidth": true },
                     { "data": "adviser", "autoWidth": true },
                     { "data": "director", "autoWidth": true },
                     { "data": "directorMobile", "autoWidth": true },
                     { "data": "directorBirthday", "autoWidth":  true 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 return moment(new Date(data)).format('DD-MM-YYYY')
                         	}
                         	return data;
       					}		 
                     },
                     { "data": "directorDomicile", "autoWidth": true },
                     { "data": "sellMan", "autoWidth": true },
                     { "data": "sellManMobile", "autoWidth": true },
                     { "data": "budgetOriginal", "autoWidth": true },
                     { "data": "otherBusiness", "autoWidth": true },
                     { "data": "customerByCustomer5Level1Id.businessName", "autoWidth": true },
                     { "data": "customer5Percent", "autoWidth": true },
                     { "data": "customerByCustomer4Level1Id.businessName", "autoWidth": true },
                     { "data": "customer4Percent", "autoWidth": true },
                     { "data": "customerByCustomer3Level1Id.businessName", "autoWidth": true },
                     { "data": "customer3Percent", "autoWidth": true },
                     { "data": "customerByCustomer2Level1Id.businessName", "autoWidth": true },
                     { "data": "customer2Percent", "autoWidth": true },
                     { "data": "customerByCustomer1Level1Id.businessName", "autoWidth": true },
                     { "data": "customer1Percent", "autoWidth": true },
                     { "data": "product1Hot", "autoWidth": true },
                     { "data": "product2Hot", "autoWidth": true },
                     { "data": "product3Hot", "autoWidth": true },
                     { "data": "product4Hot", "autoWidth": true },
                     { "data": "product5Hot", "autoWidth": true },
                     { "data": "product6Hot", "autoWidth": true },
                     { "data": "farmProduct1", "autoWidth": true },
                     { "data": "farmProduct1Session", "autoWidth": true },
                     { "data": "farmProduct2", "autoWidth": true },
                     { "data": "farmProduct2Session", "autoWidth": true },
                     { "data": "farmProduct3", "autoWidth": true },
                     { "data": "farmProduct3Session", "autoWidth": true },
                     { "data": "farmProduct4", "autoWidth": true },
                     { "data": "farmProduct4Session", "autoWidth": true },
                     
                     
                     { "data": "id"
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 return '<a href="view_customer?custId='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Xem <a>' + 
                    		 		'<a href="move_to_add_customer?custId='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa <a>' + 
                    				'<a href="delete_customer?custId='+data+'" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa <a>';
                     	}
                     }
             ] 
         });
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