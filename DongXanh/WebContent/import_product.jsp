<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="container">
	<!-- Trigger the modal with a button -->
	<!-- <button type="button" class="btn btn-info btn-lg" id="btnImport">Import
		excel</button> -->

	<!-- Modal -->
	<div class="modal fade" id="importDialog" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Cấu hình import</h4>
				</div>
				<div class="modal-body">
					<s:form action="importProductsAction" method="post"
						theme="bootstrap" enctype="multipart/form-data">
						<fieldset>
							<legend>Import sản phẩm:</legend>

							<table style="width: 500px">
								<tr>
									<td>Mã hàng</td>
									<td>Tên thuốc</td>
									<td>Số lượng/thùng</td>
									<td>Điểm/thùng</td>
									<td>Giá đề xuất</td>
								</tr>
								<tr>
									<td><select id="colProductCode" name="colProductCode">
											<option value="0">Column A</option>
											<option value="1" selected>Column B</option>
											<option value="2">Column C</option>
											<option value="3">Column D</option>
											<option value="4">Column E</option>
											<option value="5">Column F</option>
									</select></td>
									<td><select id="colProductName" name="colProductName">
											<option value="0">Column A</option>
											<option value="1">Column B</option>
											<option value="2" selected>Column C</option>
											<option value="3">Column D</option>
											<option value="4">Column E</option>
											<option value="5">Column F</option>
									</select></td>
									<td><select id="colProductQuantity"
										name="colProductQuantity">
											<option value="0">Column A</option>
											<option value="1">Column B</option>
											<option value="2">Column C</option>
											<option value="3" selected>Column D</option>
											<option value="4">Column E</option>
											<option value="5">Column F</option>
									</select></td>
									<td><select id="colProductPoint" name="colProductPoint">
											<option value="0">Column A</option>
											<option value="1">Column B</option>
											<option value="2">Column C</option>
											<option value="3">Column D</option>
											<option value="4" selected>Column E</option>
											<option value="5">Column F</option>
									</select></td>
									<td><select id="colProductPrice" name="colProductPrice">
											<option value="0">Column A</option>
											<option value="1">Column B</option>
											<option value="2">Column C</option>
											<option value="3">Column D</option>
											<option value="4">Column E</option>
											<option value="5" selected>Column F</option>
									</select></td>
								</tr>
							</table>

						</fieldset>
						<br />
						<div class="item form-group">
							<label>Bắt đầu từ dòng:</label>
							<div>
								<input type="number" id="rowProductStart" name="rowProductStart"
									data-validate-minmax="0,100" value="5">
							</div>
						</div>

						<hr />

						<input type="file" name="upload_excel" required="required">
						<input type="submit" value="Import" style="float: right"
							class="btn btn-info btn-lg">
					</s:form>
				</div>
			</div>

		</div>
	</div>

</div>

<div id="fc_importProduct" data-toggle="modal"
	data-target="#importDialog" data-backdrop="static"></div>

<script>
	$(document).ready(function() {
		$("#btnImport").click(function() {
			$('#fc_importProduct').click();
		});
	});
</script>

