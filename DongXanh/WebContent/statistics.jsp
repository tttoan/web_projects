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
		
			<div class="view_pro">
				<s:form action="" theme="bootstrap" method="post"
							cssClass="form-horizontal form-label-left">
					
					<div class="item form-group" style="margin-bottom: 0px">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="dateReceived">Loại bảng kê :
						</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<s:select id="cboPromotionStatus"
								cssClass="col-md-12 col-xs-12" style="width: 250px"
								list="#{'0':'Tất cả', '1':'Cấp 1', '2':'Cấp 2'}"
								value='%{type}' required="true" />
						</div>
						
						<label class="control-label col-md-1 col-sm-3 col-xs-12"
							for="dateReceived">NVTT :
						</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<s:select id="cboEmpId" name="EmpId"  style="width: 250px"
								showDownArrow="false" headerKey="-1" headerValue="Tất cả"
								autoComplete="true" disabled="%{isPermissionAccept}"
								list="listEmployee" listKey="id"
								listValue="fullName"
								value="getUserSes().id" />
						</div>
					</div>
							
					<div class="item form-group" style="margin-bottom: 0px">
						<label class="control-label col-md-2 col-sm-2 col-xs-12"
							for="dateReceived">Thời gian từ :
						</label>
						<div class="col-md-3 xdisplay_inputx has-feedback">
							<input type="text" class="form-control has-feedback-left"
								id="single_cal1" 
								aria-describedby="inputSuccess2Status"> <span
								class="fa fa-calendar-o form-control-feedback left"
								aria-hidden="true"></span> <span id="inputSuccess2Status"
								class="sr-only">(success)</span>
						</div>
						
						<label class="control-label col-md-1 col-sm-3 col-xs-12"
							for="dateReceived">Đến :
						</label>
						<div class="col-md-3 col-sm-3 col-xs-12">
							<input type="text" class="form-control has-feedback-left"
								id="single_cal2"
								aria-describedby="inputSuccess2Status2"> <span
								class="fa fa-calendar-o form-control-feedback left"
								aria-hidden="true"></span> <span id="inputSuccess2Status2"
								class="sr-only">(success)</span>
						</div>
						
						<button type="button" class="btn btn-primary" id="btnFilter" onclick="history.go(0)">Xem kết quả</button>
					</div>
				</s:form>
			</div>
				
			<div class="col-md-12 col-sm-12 col-xs-12">
				<s:set var="rId">
					<s:property value="%{userSes.role.roleId}" />
				</s:set>
				<table id="example" class="jambo_table display nowrap cell-border"
					style="width: 100%">
					<thead>
						<tr class="headings">
							<th>STT</th>
							<th>Ngày nhận</th>
							<th>Tên cấp 2</th>
							<th>Tên cấp 1</th>
							<th>Sản phẩm</th>
							<th>Đơn giá</th>
							<th>Số thùng</th>
							<th>Số lượng</th>
							<th>Thành tiền</th>
							<th>NVTT</th>
							<%-- <s:if test="%{#rId == 1}">
								<th class="no-link last"><span class="nobr"></span></th>
							</s:if> --%>
							<th class="no-link last"></th>
						</tr>
					</thead>

				<!-- 	<tbody> 

						<s:iterator value="statistics" status="rowStatus">
							<tr class="even pointer">
								<td class=""><s:property value="#rowStatus.count" /></td>
								<td class=""><s:property
										value="%{getText('format.date',{dateReceived})}" /></td>
								<td class=""><s:property
										value="customerByCustomerCodeLevel2.businessName" /></td>
											<td class=""><s:property
										value="customerByCustomerCodeLevel1.businessName" /></td>
								<td class=""><s:property value="product.productName" /></td>
								<td class=""><s:property
										value="%{getText('format.money',{product.unitPrice})}" /></td>
								<td class=""><s:property
										value="%{getText('format.number',{totalBox})}" /></td>
								<td class=""><s:property
										value="%{getText('format.number',{quantity})}" /></td>
								<td class=""><s:property
										value="%{getText('format.money',{total})}" /></td>
								<td class=""><s:property value="user.fullName" /></td>
								<s:if test="%{#rId == 1}">
									<td class="last"><s:url action="move_to_add_statistic"
											var="editURL">
											<s:param name="statId" value="%{id}"></s:param>
										</s:url> <s:a href="%{editURL}" class="btn btn-info btn-xs">
											<i class="fa fa-pencil"></i> Sửa </s:a> <s:url
											action="delete_statistic" var="deleteURL">
											<s:param name="statId" value="%{id}"></s:param>
										</s:url> <s:a href="%{deleteURL}" class="btn btn-danger btn-xs">
											<i class="fa fa-trash-o"></i> Xóa </s:a></td>
								</s:if>
							</tr>
						</s:iterator>
					</tbody> -->

				</table>
			</div>
		</div>
	</div>

	<!-- footer content -->
	<s:include value="footer.jsp" />
	<!-- /footer content -->

</div>
<!-- /page content -->
</div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
	<ul class="list-unstyled notifications clearfix"
		data-tabbed_notifications="notif-group">
	</ul>
	<div class="clearfix"></div>
	<div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="js/bootstrap.min.js"></script>

<!-- chart js -->
<script src="js/chartjs/chart.min.js"></script>
<!-- bootstrap progress js -->
<script src="js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="js/icheck/icheck.min.js"></script>

<script src="js/custom.js"></script>
<script src="js/moment.js"></script>

<style>
	.currency-style
	{
	    text-align: right;
	}
	.num-style
	{
	    text-align: center;
	}
	.view_pro {
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

<!-- Datatables -->
<script src="js/jquery.dataTables.min.js"></script>

 <!-- daterangepicker -->
 <script type="text/javascript" src="js/moment.min2.js"></script>
 <script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
    
 <script type="text/javascript">
        $(document).ready(function () {
            $('#single_cal1').daterangepicker({
                singleDatePicker: true,
                calender_style: "picker_2",
                format: 'DD/MM/YYYY',
                showDropdowns: true
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            $('#single_cal2').daterangepicker({
            	 singleDatePicker: true,
                 calender_style: "picker_2",
                 format: 'DD/MM/YYYY',
                 showDropdowns: true
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            
           /*  var d = new Date();
            var currDate = d.getDate();
            if(currDate < 10)currDate = '0'+currDate;
            var currMonth = d.getMonth()+1;
            if(currMonth < 10) currMonth = '0'+currMonth;
            var currYear = d.getFullYear();
            var startDate = currDate + "/" + currMonth + "/" + currYear; */
           // $("#single_cal1").attr("value", startDate);
           // $("#single_cal2").attr("value", startDate);
           
            var startDate;
            var endDate;
            
            var date = new Date();
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() - 5);
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 2);
            $("#single_cal1").attr("value", moment(startDate).format('DD/MM/YYYY'));
            $("#single_cal2").attr("value", moment(endDate).format('DD/MM/YYYY'));
        });
</script>

 <script>
	
     $(document).ready(function () {
         $("#example").DataTable({
             "processing": true, // for show progress bar
             "serverSide": true, // for process server side
             "filter": true, // this is for disable filter (search box)
             "orderMulti": false, // for disable multiple column at once
             "scrollX" : true,
             "ajax": {
                 "url": "listStatisticJSonAction",
                 "type": "POST",
                 "datatype": "json",
                 "data": {
                     "startday": document.getElementById('single_cal1').value,
                     "endday": document.getElementById('single_cal2').value,
                     "statistic_type": document.getElementById('cboPromotionStatus').value,
                     "emp_id": document.getElementById('cboEmpId').value,
                 }
             },
              "columns": [
                     { "data": "no",  "autoWidth": true },
                     { "data": "dateReceived", "autoWidth": true 
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 if(type == "display"){
                                 var date = new Date(data);
                                 //return date.toISOString().substr(0,10);;
                                 return moment(date).format('DD-MM-YYYY')
                         	}

                         	return data;
       					}		 
                     },
                     { "data": "customerByCustomerCodeLevel2.businessName", "autoWidth": true },
                     { "data": "customerByCustomerCodeLevel1.businessName", "autoWidth": true },
                     { "data": "product.productName", "autoWidth": true },
                     { "data": "product.unitPrice", "autoWidth": true, "className": "currency-style" 
                    	 ,  "render": function ( data, type, full, meta ) {
           					return formatCurrency(data);
       					}		 
                     },
                     { "data": "totalBox", "autoWidth": true, "className": "num-style" },
                     { "data": "quantity", "autoWidth": true, "className": "num-style" },
                     { "data": "total", "autoWidth": true, "className": "currency-style"
                    	 ,  "render": function ( data, type, full, meta ) {
          					return formatCurrency(data);
      					}	
                     },
                     { "data": "user.userName", "autoWidth": true },
                     { "data": "id"
                    	 ,  "render": function ( data, type, full, meta ) {
                    		 return '<a href="move_to_add_statistic?id='+data+'" class="btn btn-info btn-xs"><i class="fa fa-pencil"></i> Sửa <a>' + 
                    				'<a href="delete_statistic?id='+data+'" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Xóa <a>';
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
    

</body>

</html>