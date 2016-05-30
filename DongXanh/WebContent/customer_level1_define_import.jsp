<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openImportLevel1" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Chọn cột muốn Ẩn/Hiện</h2>
			<a href="#close" title="Close" class="close">X</a>
		</div>
		<div class="modalContent">
			<s:form action="import_customer" method="post" enctype="multipart/form-data"
				cssClass="form-horizontal form-label-left">
				<table id="defineLevelI"
					class="jambo_table display nowrap cell-border" style="width: 100%">
					<thead>
						<tr class="headings">
							<th>STT</th>
							<th>Tên cột</th>
							<th>Vị trí cột</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listDefineColumns" status="rowStatus">
							<%-- 							<td><s:property value="%{fieldName}" /></td> --%>
							<tr class="even pointer">
								<td class=""><s:property value="#rowStatus.count" /></td>

								<td><s:property value="%{title}" /></td>
								<td align="center"><select name="varIndexColumn">
										<option value="0">Cột A</option>
										<option value="1" selected="selected">Cột B</option>
										<option value="2">Cột C</option>
										<option value="3">Cột D</option>
										<option value="4">Cột E</option>
										<option value="5">Cột F</option>
										<option value="6">Cột G</option>
										<option value="7">Cột H</option>
										<option value="8">Cột I</option>
										<option value="9">Cột J</option>
										<option value="10">Cột K</option>
										<option value="11">Cột L</option>
										<option value="12">Cột M</option>
										<option value="13">Cột N</option>
										<option value="14">Cột O</option>
										<option value="15">Cột P</option>
										<option value="16">Cột Q</option>
										<option value="16">Cột R</option>
										<option value="16">Cột S</option>
										<option value="16">Cột T</option>
										<option value="16">Cột U</option>
								</select></td>
								<td><input type="hidden" name="varFieldEntName"
									value="${fieldEntName}"></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="clear"></div>
				<input id="uploadLevel1" type="file" name="upload"
					required="required" class="col-md-7 col-xs-12">
				<button id="send2" type="submit" class="btn btn-primary">Cập
					nhật</button>
			</s:form>
		</div>
		<div class="modalFooter">
			<a href="#close" title="Close" class="close">Đóng</a>
			<!-- 						<a href="#Apply" title="Apply" class="ok">Cập nhật</a> -->
			<div class="clear"></div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#defineLevelI').DataTable({
			scrollY : 250,
			paging : false
		});
	});

	
</script>