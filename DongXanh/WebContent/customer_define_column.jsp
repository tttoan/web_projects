<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openModal" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Chọn cột muốn Ẩn/Hiện</h2>
			<a href="#close" title="Close" class="close">X</a>
		</div>
		<div class="modalContent">
			<table id="tableDefineColumn"
				class="jambo_table display nowrap cell-border" style="width: 100%">
				<thead>
					<tr class="headings">
						<th>STT</th>
						<th>Tên cột</th>
						<th>Ẩn/Hiện</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="listTableColumn" status="rowStatus">
						<tr class="even pointer">
							<td class=""><s:property value="#rowStatus.count" /></td>
							<td><s:property /></td>
							<td align="center" data-column="${rowStatus.index}"><input
								name="show_hidden0" type="checkbox" class="show_hidden"
								checked="checked"></td>
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
				$('#tableDefineColumn').DataTable({
					scrollY : 200,
					paging : false
				});

				$('input.show_hidden').on(
						'click',
						function(e) {
							var column = $('#example').DataTable().column(
									$(this).parents('td').attr('data-column'));
							column.visible(!column.visible());
						});
			});

	
</script>