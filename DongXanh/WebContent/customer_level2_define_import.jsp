<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openImportLevel2" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Chọn cột muốn Ẩn/Hiện</h2>
			<a href="#close" title="Close" class="close">X</a>
		</div>
		<div class="modalContent">
			<table id="tableDefineLevel2"
				class="jambo_table display nowrap cell-border" style="width: 100%">
				<thead>
					<tr class="headings">
						<th>STT</th>
						<th>Tên cột</th>
						<th>Ẩn/Hiện</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="defineColumns" status="rowStatus">
						<tr class="even pointer">
							<td class=""><s:property value="#rowStatus.count" /></td>
							<td><s:property value="%{title}" /></td>
							<s:hidden name="fieldName" value="%{fieldName}" />
							<td align="center"><select name="index">
									<option value="0" selected="selected">Cột A</option>
									<option value="1">Cột B</option>
									<option value="2">Cột C</option>
									<option value="3">Cột D</option>
							</select></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div class="modalFooter">
			
			<a href="#Apply" title="Apply" class="ok">Cập nhật</a>
			<div class="clear"></div>
		</div>
	</div>
</div>

<script>
	$(document).ready(
			function() {
				$('#tableDefineLevel2').DataTable({
					scrollY : 200,
					paging : false
				});
			});
</script>