<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openModal" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Chọn cột muốn Ẩn/Hiện</h2>
			<a href="#close" title="Tắt cửa sổ" class="close">X</a>
		</div>
		<div class="modalContent">
			<div class="x_panel">
				<div class="x_content">
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
							<s:iterator value="listTableColumn" status="rowStatus" var="arr1">
								<tr class="even pointer">
									<td class=""><s:property value="#rowStatus.count" /></td>
									<td><s:property value="#arr1[0]" /></td>
									<s:if test="#arr1[1]">
										<td align="center" data-column="${rowStatus.index}"><input
											name="show_hidden0" type="checkbox" class="show_hidden"
											checked="checked"></td>
									</s:if>
									<s:else>
										<td align="center" data-column="${rowStatus.index}"><input
											name="show_hidden0" type="checkbox" class="show_hidden"></td>
									</s:else>

								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- 		<div class="modalFooter"> -->
		<!-- 			<a href="#Apply" title="Apply" class="ok">Cập nhật</a> -->
		<!-- 			<div class="clear"></div> -->
		<!-- 		</div> -->
	</div>
</div>

<script>
	function updateCookieColumnVisible(index, isColumnVisible) {
		var d = new Date();
		d.setTime(d.getTime() + (1 * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toUTCString();
		var oValue = getCookieColumnVisible("columnActive");
		var isVis = isColumnVisible == true?"1":"0";
		var cValue = oValue.substr(0, index * 2) +""+ isVis
		+""+ oValue.substr((index * 2) + 1, oValue.length);
		document.cookie = "columnActive=" + cValue + ";" + expires;
	}

	$(document).ready(
			function() {
				$('input.show_hidden').on(
						'click',
						function(e) {
							var column = $('#example').DataTable().column(
									$(this).parents('td').attr('data-column'));
							column.visible(!column.visible());
							updateCookieColumnVisible($(this).parents('td')
									.attr('data-column'), column.visible());
						});

				$('#tableDefineColumn').DataTable({
					scrollY : 250,
					paging : false
				});
			});
</script>