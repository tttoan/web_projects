<!DOCTYPE html>
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
						<table id="example"
							class="table table-striped responsive-utilities jambo_table">
							<thead>
								<tr class="headings">
									<th><input type="checkbox" class="tableflat"></th>
									<th>Invoice</th>
									<th>Invoice Date</th>
									<th>Order</th>
									<th>Bill to Name</th>
									<th>Status</th>
									<th>Amount</th>
									<th class=" no-link last"><span class="nobr">Action</span>
									</th>
								</tr>
							</thead>

							<tbody>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 23, 2014 11:47:56 PM</td>
									<td class=" ">121000210 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#" class="btn btn-info btn-xs"><i
											class="fa fa-pencil"></i> Edit </a> <a href="#"
										class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>
											Delete </a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 23, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#" class="btn btn-info btn-xs"><i
											class="fa fa-pencil"></i> Edit </a> <a href="#"
										class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>
											Delete </a></td>
								</tr>
								<tr class="even pointer selected">
									<td class="a-center "><input type="checkbox" checked
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 24, 2014 10:55:33 PM</td>
									<td class=" ">121000203 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 24, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 24, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 26, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="error fa fa-long-arrow-down"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 26, 2014 10:55:33 PM</td>
									<td class=" ">121000203</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 26, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>

								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 27, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 28, 2014 11:30:12 PM</td>
									<td class=" ">121000208</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 23, 2014 11:47:56 PM</td>
									<td class=" ">121000210 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 23, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer selected">
									<td class="a-center "><input type="checkbox" checked
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 24, 2014 10:55:33 PM</td>
									<td class=" ">121000203 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 24, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 24, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 26, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="error fa fa-long-arrow-down"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 26, 2014 10:55:33 PM</td>
									<td class=" ">121000203</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 26, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>

								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 27, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 28, 2014 11:30:12 PM</td>
									<td class=" ">121000208</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 23, 2014 11:47:56 PM</td>
									<td class=" ">121000210 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 23, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer selected">
									<td class="a-center "><input type="checkbox" checked
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 24, 2014 10:55:33 PM</td>
									<td class=" ">121000203 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 24, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 24, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 26, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="error fa fa-long-arrow-down"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 26, 2014 10:55:33 PM</td>
									<td class=" ">121000203</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 26, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>

								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 27, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 28, 2014 11:30:12 PM</td>
									<td class=" ">121000208</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 23, 2014 11:47:56 PM</td>
									<td class=" ">121000210 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 23, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer selected">
									<td class="a-center "><input type="checkbox" checked
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 24, 2014 10:55:33 PM</td>
									<td class=" ">121000203 <i
										class="success fa fa-long-arrow-up"></i>
									</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 24, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 24, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 26, 2014 11:30:12 PM</td>
									<td class=" ">121000208 <i
										class="error fa fa-long-arrow-down"></i>
									</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000038</td>
									<td class=" ">May 26, 2014 10:55:33 PM</td>
									<td class=" ">121000203</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$432.26</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000037</td>
									<td class=" ">May 26, 2014 10:52:44 PM</td>
									<td class=" ">121000204</td>
									<td class=" ">Mike Smith</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$333.21</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>

								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000040</td>
									<td class=" ">May 27, 2014 11:47:56 PM</td>
									<td class=" ">121000210</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$7.45</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">121000039</td>
									<td class=" ">May 28, 2014 11:30:12 PM</td>
									<td class=" ">121000208</td>
									<td class=" ">John Blank L</td>
									<td class=" ">Paid</td>
									<td class="a-right a-right ">$741.20</td>
									<td class=" last"><a href="#">View</a></td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>

			<br /> <br /> <br />

		</div>
	</div>
	<!-- footer content -->
	<footer>
		<div class="">
			<p class="pull-right">
				@Copyright by <a>ThienTran</a>. | <span class="lead"> <i
					class="fa fa-paw"></i> VNFamily Tech!
				</span>
			</p>
		</div>
		<div class="clearfix"></div>
	</footer>
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


<!-- Datatables -->
<script src="js/datatables/js/jquery.dataTables.js"></script>
<script src="js/datatables/tools/js/dataTables.tableTools.js"></script>
<script>
            $(document).ready(function () {
                $('input.tableflat').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });
            });

            var asInitVals = new Array();
            $(document).ready(function () {
                var oTable = $('#example').dataTable({
                    "oLanguage": {
                        "sSearch": "Search all columns:"
                    },
                    "aoColumnDefs": [
                        {
                            'bSortable': false,
                            'aTargets': [0]
                        } //disables sorting for column one
            ],
                    'iDisplayLength': 12,
                    "sPaginationType": "full_numbers",
                    "dom": 'T<"clear">lfrtip',
                    "tableTools": {
                        "sSwfPath": "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
                    }
                });
                $("tfoot input").keyup(function () {
                    /* Filter on the column based on the index of this element's parent <th> */
                    oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
                });
                $("tfoot input").each(function (i) {
                    asInitVals[i] = this.value;
                });
                $("tfoot input").focus(function () {
                    if (this.className == "search_init") {
                        this.className = "";
                        this.value = "";
                    }
                });
                $("tfoot input").blur(function (i) {
                    if (this.value == "") {
                        this.className = "search_init";
                        this.value = asInitVals[$("tfoot input").index(this)];
                    }
                });
            });
        </script>
</body>

</html>