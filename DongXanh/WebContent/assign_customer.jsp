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
					
					<div class="item form-group">
							<div class="right">
								<s:select id="emp_id" name="emp_id"
												cssClass="form-control col-md-7 col-xs-12"
												showDownArrow="false" autoComplete="true" headerKey="-1" headerValue="---"
												list="listEmployee" listKey="id"
												listValue="fullName +' - '+ userName"
												value="" />
							</div>
							<label class="left" for="emp_id" style="padding-top:10px">Tổng số lượng khách hàng chưa phân công 
							      <span id="soluongchuaphancong" style="color: blue;"> <span>
							</label>
							<label class="right" for="emp_id" style="padding-top:10px">Nhân viên TT 
							</label>
					</div>
				
					<div id="x_content" class="x_content">
						
						<div class="subject-info-box-1" id="divCus1">
						  <select size="25" multiple="multiple" id="lstBox1" class="form-control">
						  	 <optgroup label="Khách hàng chưa có NVTT">
							  </optgroup>
						  </select>
						</div>
						
						<div class="subject-info-arrows text-center">
						  <input type="button" id="btnAllRight" value="&gt;&gt;" class="btn btn-default" /><br />
						  <input type="button" id="btnRight" value="&gt;" class="btn btn-default" /><br />
						  <input type="button" id="btnLeft" value="&lt;" class="btn btn-default" /><br />
						  <input type="button" id="btnAllLeft" value="&lt;&lt;" class="btn btn-default" />
						</div>
						
						<div class="subject-info-box-2" id="divCus2">
						  <select size="25" multiple="multiple" id="lstBox2" class="form-control">
						  </select>
						</div>
						<div class="right" style="padding-right: 18px ;"> Tổng số lượng khách phân công theo NVTT chọn 
						   <span id="soluongkhachdaphancong" style="color: blue; font-weight: bold;"></span>
						</div>
						
						<div class="clearfix"></div>
					
					</div>
				</div>
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

<script>
(function () {
    $('#btnRight').click(function (e) {
        var selectedOpts = $('#lstBox1 option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }      
        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        resetsoluongNVTT();
        assignUser(selectedOpts);      
        e.preventDefault();
       
    });
    
  

    $('#btnAllRight').click(function (e) {
        var selectedOpts = $('#lstBox1 option');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox2').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        resetsoluongNVTT();
        assignUser(selectedOpts); 
        e.preventDefault();
       
    });

    $('#btnLeft').click(function (e) {
        var selectedOpts = $('#lstBox2 option:selected');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        resetsoluongNVTT();
        unAssignUser(selectedOpts); 
        e.preventDefault();
       
    });

    $('#btnAllLeft').click(function (e) {
        var selectedOpts = $('#lstBox2 option');
        if (selectedOpts.length == 0) {
            alert("Nothing to move.");
            e.preventDefault();
        }

        $('#lstBox1').append($(selectedOpts).clone());
        $(selectedOpts).remove();
        resetsoluongNVTT();
        unAssignUser(selectedOpts); 
        e.preventDefault();
       
    });
    
    function assignUser(Cuss){
    	var user_id = document.getElementById('emp_id').value;
        for(var i=0; i<=Cuss.length; i++){
        	$.ajax({ //Not found in cache, get from server
                url: 'setAssignCusByNVTTAction?cus_id='+Cuss[i].value+'&user_id='+user_id,
                type: 'POST',
                dataType: 'json',
                async: false,
        	});
        }
        setsoluongNVTT();
    }
    
    function unAssignUser(Cuss){
        for(var i=0; i<=Cuss.length; i++){
        	$.ajax({ //Not found in cache, get from server
                url: 'setAssignFreeCusAction?cus_id='+Cuss[i].value,
                type: 'POST',
                dataType: 'json',
                async: false,
        	});
        }
    }
    
    $("#emp_id" ).change(function() {
    	 loadCustomerByNVTT();
    	 loadCustomerFree();
    	 resetsoluongNVTT();
    });
    
    $(document).ready(function() {
    	loadCustomerByNVTT();
    	loadCustomerFree();
    	var lc =0;
    	resetsoluongNVTT();
    	
    });
    function resetsoluongNVTT(){
    	var lc =0;      
	    $( "#lstBox1 option" ).each(function() {
		   lc = lc+1;
	   });
	   $("#soluongchuaphancong").text(lc);
	
	   lc=0;
	   $( "#lstBox2 option" ).each(function() {
	     	lc = lc+1;
	  });
	  $("#soluongkhachdaphancong").text(lc);
    }
   
    function loadCustomerByNVTT(){
    	var user_id = document.getElementById('emp_id').value;
		//alert(user_id);
    	 $.ajax({ //Not found in cache, get from server
             url: 'getAssignCusByNVTTAction?user_id='+user_id,
             type: 'POST',
             dataType: 'json',
             async: false,
             success: function (data) {
            	//alert("hiiiii  " + JSON.stringify(data)); 
            	 var select = $('#lstBox2');
                 select.find('option').remove();
                 for (var i = 0; i < data.listCustomer1.length; i++) {
                	 $('<option>').val(data.listCustomer1[i][0]).text(data.listCustomer1[i][1] + ' - ' + data.listCustomer1[i][2]).appendTo(select);
                 }
             }
         });
    }
    
    function loadCustomerFree(){
    	$.ajax({ //Not found in cache, get from server
            url: 'getAssignFreeCusAction',
            type: 'POST',
            dataType: 'json',
            async: false,
            success: function (data) {
           	// alert("hiiiii  " + JSON.stringify(data)); 
           	 var select = $('#lstBox1');
                select.find('option').remove();
                for (var i = 0; i < data.listCustomer2.length; i++) {
               	 $('<option>').val(data.listCustomer2[i][0]).text(data.listCustomer2[i][1] + ' - ' + data.listCustomer2[i][2]).appendTo(select);
                }
            }
        });
    }
    
}(jQuery));
</script>

<style>
.subject-info-box-1,
.subject-info-box-2 {
    float: left;
    width: 45%;
    
    select {
        height: 500px;
        padding: 0;

        option {
            padding: 4px 10px 4px 10px;
        }

        option:hover {
            background: #EEEEEE;
        }
    }
}

.subject-info-arrows {
    float: left;
    width: 10%;

    input {
        width: 70%;
        margin-bottom: 5px;
    }
}

.form-control{
    line-height:30px;
}

.right {
	    float: right;
	    padding: 4px 10px 4px 50px;
}
</style>

</body>

</html>