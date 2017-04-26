<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="openImportStatisticLevel2" class="modalWindow">
	<div>
		<div class="modalHeader">
			<h2>Cấu Hình Import Bảng Kê Cấp II</h2>
			<a href="#close" title="Close" class="close">X</a>
		</div>
		<div class="modalContent">
			<s:form action="import_statistic" method="post"
				enctype="multipart/form-data"
				cssClass="form-horizontal form-label-left">
				<table id="tableImportStatisticLevel2"
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
						<tr class="even pointer">
							<td class="">0</td>
							<td><b>BẮT ĐẦU DÒNG</b></td>
							<td align="center"><select name="varIndexRow">
									<s:iterator value="listColumnExcel" status="rowStatusExc">
										<s:if test="%{#rowStatusExc.index == varIndexRow}">
											<option value="${rowStatusExc.index}" selected="selected"><s:property
													value="%{#rowStatusExc.count}" /></option>
										</s:if>
										<s:else>
											<option value="${rowStatusExc.index}"><s:property
													value="%{#rowStatusExc.count}" /></option>
										</s:else>

									</s:iterator>
							</select></td>
							<td></td>
						</tr>
						<s:iterator value="listDefineColStatisticLevel2" status="rowStatus">
							<tr class="even pointer">
								<td class=""><s:property value="#rowStatus.count" /></td>
								<td><s:property value="%{title}" /></td>
								<td align="center"><select name="varIndexColumn">
										<s:iterator value="listColumnExcel" status="rowStatusExc">
											<s:if test="%{indexColumn == #rowStatusExc.index}">
												<option value="${rowStatusExc.index}" selected="selected"><s:property /></option>
											</s:if>
											<s:else>
												<option value="${rowStatusExc.index}"><s:property /></option>
											</s:else>
										</s:iterator>
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
			<a href="#close" title="Close" class="close">X</a>
			<!-- 						<a href="#Apply" title="Apply" class="ok">Cập nhật</a> -->
			<div class="clear"></div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#tableImportStatisticLevel2').DataTable({
			scrollY : 250,
			paging : false
		});
	});
</script>