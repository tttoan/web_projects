<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
#form1 {
	font-family: Tahoma;
	font-size: 10pt;
}

#form1 label {
	width: 100px;
	display: inline-block;
}

.div_right {
    position: absolute;
    right: 5px;
    width: 250px;
    padding: 5px;
}

</style>
<div>
	<form id='form1'>
		<table id="form1" class="jambo_table display nowrap cell-border"
			style="width: 100%">

			<tbody>

				<tr class="even pointer">
					<td class="" form="event_text">Công việc:</td>
					<td><input id="event_text" name="text" type="text" style="width: 100%"/></td>
				</tr>

				<tr class="even pointer">
					<td class="">Bắt đầu:</td>
					<td><input id="event_start_date" name="start_date" type="text" style="width: 100%"/></td>
				</tr>
				<tr class="even pointer">
					<td class="">Kết thúc:</td>
					<td><input id="event_end_date" name="end_date" type="text" style="width: 100%"/></td>
				</tr>
				<tr class="even pointer">
					<td><s:select label="Khách hàng" id="customerId"
							showDownArrow="false" autoComplete="true" list="listCustomer"
							listKey="id" name="customerId"
							listValue="businessName +' - '+ customerCode" style="width: 100%"/></td>
				</tr>

				<tr class="even pointer">
					<td><s:select label="Hình thức liên hệ" id="contactType"
							headerKey="-1" headerValue="Gặp trực tiếp" name="contactType"
							list="listCustomer" showDownArrow="false" disabled="true"
							listKey="id" listValue="telefone" style="width: 100%"/></td>
				</tr>
				<tr class="even pointer">
					<td class=""></td>
					<td><label for="customerPhone">Điện thoại:</label><input
						type="checkbox" id="customerPhone"></td>
				</tr>
				<tr class="even pointer">
					<td><s:select label="Người QĐCV" id="directorTemp"
							name="directorName" disabled="true" list="listCustomer"
							listKey="id" listValue="director" style="width: 100%"/></td>
				</tr>
			</tbody>
		</table>
		<input id="hiddenEmployeeId" name="employeeId" type="hidden"
			value="${userSes.id}" /> <input id="hiddenEventId" name="id"
			type="hidden" />
	</form>
	<br>
	<div class="div_right">
		<input type="button" value="Cập nhật" onclick="lightbox.save()" style="width: 80px" /> 
		<input type="button" value="Đóng" onclick="lightbox.close()" style="width: 80px"/> 
		<!-- <input type="button" value="Xoá" onclick="lightbox.remove()" style="width: 80px"/>  -->
	</div>
</div>
<script src="js/jquery.min.js"></script>

<script>
	getValues = function() {
		var ev = {};
		var inputs = document.body.getElementsByTagName('input');

		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "button") {
				continue;
			}
			var name = inputs[i].getAttribute('name');
			if (name == "start_date" || name == "end_date")
				ev[name] = parent.scheduler.templates.api_date(inputs[i].value);
			else
				ev[name] = inputs[i].value;
		}

		var selectBoxes = document.body.getElementsByTagName('select');
		for (var i = 0; i < selectBoxes.length; i++) {
			var name = selectBoxes[i].getAttribute('name');
			ev[name] = selectBoxes[i].value;
		}

		return ev;
	};

	setValues = function(obj) {
		var inputs = document.body.getElementsByTagName('input');
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].type != "button") {
				var name = inputs[i].getAttribute('name');
				if (name == "employeeId")
					continue;
				if (name == "start_date" || name == "end_date")
					inputs[i].value = parent.scheduler.date.date_to_str(
							parent.scheduler.config.api_date)(obj[name]);
				else {
					if (obj[name] == null)
						inputs[i].value = "";
					else
						inputs[i].value = obj[name];
				}
			}
		}
		var selectBoxes = document.body.getElementsByTagName('select');
		for (var i = 0; i < selectBoxes.length; i++) {
			var name = selectBoxes[i].getAttribute('name');
			if (name == "contactType") {
				if (obj[name] > 0 && obj[name] != null) {
					selectBoxes[i].value = obj[name];
					document.getElementById("customerPhone").checked = true;
				}
				else
					selectBoxes[i].value = -1;
			} else if (name == "directorTemp")
				selectBoxes[i].value = obj["customerId"];
			else
				selectBoxes[i].value = obj[name];
		}
	};

	$(document).ready(function() {
		$('#customerId').change(function() {
			$('#directorTemp').val($(this).val());
		});
		$('#customerPhone,#customerId').change(function() {
			if ($('#customerPhone').prop('checked') == false)
				$('#contactType').val(-1);
			else
				$('#contactType').val($('#customerId').val());
		});
	});

	
</script>
