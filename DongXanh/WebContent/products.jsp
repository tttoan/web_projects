<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>
					Danh sach san pham:<br> <small>
						<button id="AddProduct">Them San Pham</button>
					</small>
				</h3>
			</div>

		</div>
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
									<th>Ma SP</th>
									<th>Loai SP</th>
									<th>Ten SP</th>
									<th>Mo Ta</th>
									<th>Danh Muc</th>
									<th>Don Gia</th>
									<th>So Luong Nho Nhat</th>
									<th>So Luong Lon Nhat</th>
									<th>Ngay San Xuat</th>
									<th>Ngay Het Han</th>
									<th class=" no-link last"><span class="nobr">Action</span>
									</th>
								</tr>
							</thead>

							<tbody>
								<tr class="even pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">001</td>
									<td class=" ">Thuoc Tru sau</td>
									<td class=" ">KT2015</td>
									<td class=" ">Tru cac loai sau benh tren cay lua</td>
									<td class=" ">TTS</td>
									<td class="a-right a-right ">150.000VND</td>
									<td class="a-right a-right ">1</td>
									<td class="a-right a-right ">100</td>
									<td class="a-right a-right ">01/01/2016</td>
									<td class="a-right a-right ">31/12/2016</td>
									<td class=" last">
										<button id="EditProduct" class="btn btn-info btn-xs">
											<i class="fa fa-pencil"></i> Sua
										</button>
										<button id="DelProduct" class="btn btn-danger btn-xs">
											<i class="fa fa-trash-o"></i> Xoa
										</button>
									</td>
								</tr>
								<tr class="odd pointer">
									<td class="a-center "><input type="checkbox"
										class="tableflat"></td>
									<td class=" ">002</td>
									<td class=" ">Thuoc Tru sau</td>
									<td class=" ">KT2015</td>
									<td class=" ">Tru cac loai sau benh tren cay lua</td>
									<td class=" ">TTS</td>
									<td class="a-right a-right ">150.000VND</td>
									<td class="a-right a-right ">1</td>
									<td class="a-right a-right ">100</td>
									<td class="a-right a-right ">01/01/2016</td>
									<td class="a-right a-right ">31/12/2016</td>
									<td class=" last">
										<button id="EditProduct" class="btn btn-info btn-xs">
											<i class="fa fa-pencil"></i> Sua
										</button>
										<button id="DelProduct" class="btn btn-danger btn-xs">
											<i class="fa fa-trash-o"></i> Xoa
										</button>
									</td>
								</tr>
								<tr class="even pointer selected">
									<td class="a-center "><input type="checkbox" checked
										class="tableflat"></td>
									<td class=" ">003</td>
									<td class=" ">Thuoc Tru sau</td>
									<td class=" ">KT2015</td>
									<td class=" ">Tru cac loai sau benh tren cay lua</td>
									<td class=" ">TTS</td>
									<td class="a-right a-right ">150.000VND</td>
									<td class="a-right a-right ">1</td>
									<td class="a-right a-right ">100</td>
									<td class="a-right a-right ">01/01/2016</td>
									<td class="a-right a-right ">31/12/2016</td>
									<td class=" last"><a href="#" class="btn btn-info btn-xs"><i
											class="fa fa-pencil"></i> Sua </a> <a href="#"
										class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>
											Xoa </a></td>
								</tr>
							</tbody>

						</table>
					</div>
				</div>
			</div>

			<br /> <br /> <br />

		</div>

		<!-- Start Calender modal -->
		<div id="CalenderModalNew" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">Ã</button>
						<h4 class="modal-title" id="myModalLabel">New Calender Entry</h4>
					</div>
					<div class="modal-body">
						<div id="testmodal" style="padding: 5px 20px;">
							<form id="antoform" class="form-horizontal calender" role="form">
								<div class="form-group">
									<label class="col-sm-3 control-label">Title</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="title"
											name="title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Description</label>
									<div class="col-sm-9">
										<textarea class="form-control" style="height: 55px;"
											id="descr" name="descr"></textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default antoclose"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary antosubmit">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>
		<div id="CalenderModalEdit" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">Ã</button>
						<h4 class="modal-title" id="myModalLabel2">Edit Calender
							Entry</h4>
					</div>
					<div class="modal-body">

						<div id="testmodal2" style="padding: 5px 20px;">
							<form id="antoform2" class="form-horizontal calender" role="form">
								<div class="form-group">
									<label class="col-sm-3 control-label">Title</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="title2"
											name="title2">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Description</label>
									<div class="col-sm-9">
										<textarea class="form-control" style="height: 55px;"
											id="descr2" name="descr"></textarea>
									</div>
								</div>

							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default antoclose2"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary antosubmit2">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>

		<div id="fc_create" data-toggle="modal"
			data-target="#CalenderModalNew"></div>
		<div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>

		<!-- End Calender modal -->
		<!-- /page content -->

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

<script>
		$(document).ready(function(){
			$("#AddProduct").click(function(){
				$('#fc_create').click();
			});
		});
		$(document).ready(function(){
			$("#EditProduct").click(function(){
				$('#fc_edit').click();
			});
		});
		</script>
</body>

</html>