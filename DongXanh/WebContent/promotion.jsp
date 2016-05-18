<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">

		<div class="row">
			<div class="col-md-12">
				<div class="x_panel">

					<div class="x_content">

						<div id="PromotionTableContainer"></div>

					</div>
						
				</div>
			</div>
		</div>
	</div>

	<!-- footer content -->
	<%@ include file="footer.jsp"%>
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


<!-- jTable metro styles. -->
<link href="Scripts/themes/metroblue/highlight.css" rel="stylesheet"
	type="text/css" />
<link href="Scripts/themes/metroblue/jquery-ui.css" rel="stylesheet"
	type="text/css" />
<link href="Scripts/jtable/themes/lightcolor/blue/jtable.css"
	rel="stylesheet" type="text/css" />
<link href="Scripts/syntaxhighligher/styles/shCore.css" rel="stylesheet"
	type="text/css" />
<link href="Scripts/syntaxhighligher/styles/shThemeDefault.css"
	rel="stylesheet" type="text/css" />
<link href="Scripts/validationEngine/validationEngine.jquery.css"
	rel="stylesheet" type="text/css" />


<script src="Scripts/modernizr-2.6.2.js" type="text/javascript"></script>
<script src="Scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="Scripts/jquery-ui-1.10.0.min.js" type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shCore.js" type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shBrushJScript.js"
	type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shBrushXml.js"
	type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shBrushCSharp.js"
	type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shBrushSql.js"
	type="text/javascript"></script>
<script src="Scripts/syntaxhighligher/shBrushPhp.js"
	type="text/javascript"></script>
<script src="Scripts/jtablesite.js" type="text/javascript"></script>
<script type="text/javascript" src="Scripts/jtable/jquery.jtable.js"></script>


<script type="text/javascript"
	src="Scripts/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="Scripts/validationEngine/jquery.validationEngine-en.js"></script>

<!-- User defined Jtable js file -->
<script src="Scripts/custom/tblPromotion.js" type="text/javascript"></script>

<style>
        .child-opener-image
        {
            cursor: pointer;
        }
        .child-opener-image
        {
            opacity: 0.6;
        }
        .child-opener-image:hover
        {
            opacity: 1.0;
        }
        .child-opener-image-column
        {
            text-align: center;
        }
        .jtable-dialog-form
        {
            min-width: 220px;
        }
        .jtable-dialog-form input[type="text"]
        {
            min-width: 200px;
        }
        .jtable-dialog-form select
        {
            min-width: 200px;
            font-weight: bold;
        }
        .promotion-status
        {
            font-weight: bold;
            color: red;
        }
         .currency-style
        {
            text-align: right;
        }
          .num-style
        {
            text-align: center;
        }
       /*  table{
    		color: red;
		} */
</style>

</body>
</html>