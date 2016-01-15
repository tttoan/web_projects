<style>
#form1 {
	font-family: Tahoma;
	font-size: 10pt;
}
#form1 label {
	width: 100px;
	display: inline-block;
}
</style>
<div>
	<form id='form1'>
		<label form="event_text">Text</label><input id="event_text" name="text" type="text"/><br />
		<label for="event_start_date">From</label><input id="event_start_date" name="start_date"  type="text"/><br />
		<label for="event_end_date">To</label><input id="event_end_date" name="end_date" type="text"/><br />
		<input id="Hidden1" name="id" type="hidden"/>
	</form>
	<div>
		<input type="button" value="Save" onclick="lightbox.save()" />
		<input type="button" value="Cancel" onclick="lightbox.close()" />
		<input style="margin-left:86px" type="button" value="Delete" onclick="lightbox.remove()" />
	</div>
</div>
<script>
	getValues = function () {
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
		return ev;
	};

	setValues = function (obj) {
		var inputs = document.body.getElementsByTagName('input');
		for (var i = 0; i < inputs.length; i++)
			if (inputs[i].type != "button") {
				var name = inputs[i].getAttribute('name');
				if (name == "start_date" || name == "end_date")
					inputs[i].value = parent.scheduler.date.date_to_str(parent.scheduler.config.api_date)(obj[name]);
				else
					inputs[i].value = obj[name];
			}
	};

</script>