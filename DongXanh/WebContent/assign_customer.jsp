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
								<select id="emp_id" name="emp.id" style="width: 200px;">
									<option value="tttoan">tttoan</option>
				    				<option value="tttoan">tttoan</option>
								</select>
							</div>
							<label class="right" for="emp_id">Nhân viên TT 
							</label>
					</div>
				
					<div id="x_content" class="x_content">
						
						<div class="subject-info-box-1">
						  <select size="25" multiple="multiple" id="lstBox1" class="form-control">
						  	 <optgroup label="Khách hàng chưa có NVTT">
							    <option value="ajax">Ajax</option>
							    <option value="jquery">jQuery</option>
							    <option value="javascript">JavaScript</option>
							    <option value="mootool">MooTools</option>
							    <option value="prototype">Prototype</option>
							    <option value="dojo">Dojo</option>
							  </optgroup>
						  </select>
						</div>
						
						<div class="subject-info-arrows text-center">
						  <input type="button" id="btnAllRight" value="&gt;&gt;" class="btn btn-default" /><br />
						  <input type="button" id="btnRight" value="&gt;" class="btn btn-default" /><br />
						  <input type="button" id="btnLeft" value="&lt;" class="btn btn-default" /><br />
						  <input type="button" id="btnAllLeft" value="&lt;&lt;" class="btn btn-default" />
						</div>
						
						<div class="subject-info-box-2">
						  <select size="25" multiple="multiple" id="lstBox2" class="form-control">
						    <option value="asp">ASP.NET</option>
						    <option value="c#">C#</option>
						    <option value="vb">VB.NET</option>
						    <option value="java">Java</option>
						    <option value="php">PHP</option>
						    <option value="python">Python</option>
						  </select>
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
        e.preventDefault();
    });
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