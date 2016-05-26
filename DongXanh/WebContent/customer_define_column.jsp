<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openModal" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Chọn cột muốn Ẩn/Hiện</h2>
			<a href="#close" title="Close" class="close">Đóng</a>
		</div>
		<div class="modalContent">
			<table style="width: 67%; margin: 0 auto 2em auto;">
				<tbody>
					<s:iterator value="listTableColumn" status="rowStatus">
						<tr>
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
				$('input.show_hidden').on(
						'click',
						function(e) {
							var column = $('#example').DataTable().column(
									$(this).parents('td').attr('data-column'));
							column.visible(!column.visible());
						});
			});

	
</script>