
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ include file="header.jsp"%>
<%@ include file="user_profile.jsp"%>
<%@ include file="menu.jsp"%>
<!-- page content -->
<div class="right_col" role="main">

	<div class="">
		<div class="page-title">
			<div class="title_left">
				<s:if test="%{edit}">
					<h3>Cập nhật thông tin bảng kê</h3>
				</s:if>
				<s:else>
					<h3>Thêm bảng kê</h3>
				</s:else>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_content">
						<s:form action="add_statistic" theme="bootstrap" method="post"
							cssClass="form-horizontal form-label-left">
							<s:hidden name="stat.id" value="%{statId}"></s:hidden>
							<s:hidden name="edit" value="%{edit}"></s:hidden>
							<s:if test="hasActionErrors()">
								<div class="errors">
									<s:actionerror escape="false" />
								</div>
							</s:if>
							<s:elseif test="hasActionMessages()">
								<div class="message">
									<s:actionmessage escape="false" />
								</div>
							</s:elseif>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="dateReceived">Ngày nhận <span class="required">*</span>
								</label>
								<div class="col-md-3 xdisplay_inputx has-feedback">
									<input type="text" class="form-control has-feedback-left"
										id="dateReceived" name="varDateReceived" value="${varDateReceived}"
										aria-describedby="inputSuccess2Status"> <span
										class="fa fa-calendar-o form-control-feedback left"
										aria-hidden="true"></span> <span id="inputSuccess2Status"
										class="sr-only">(success)</span>
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="cusLevel1.id">Tên cấp 1 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<s:hidden name="cusLevel1" value="%{cusLevel1.id}"></s:hidden>
									<input id="cusLevel1.statisticName" type="text" name="cusLevel1.statisticName"
										value="${cusLevel1.statisticName}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for=cusLevel2.id>Tên cấp 2 <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="cusLevel2.id" type="text" name="cusLevel2.id"
										value="${cusLevel2.statisticName}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>

							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="pro_id">Sản phẩm <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<input id="pro_id" type="text" name="pro.id"
										 value="${stat.product.id}" required="required"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="unitPriceFm">Đơn giá <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:hidden id="unitPrice" name="unitPrice"
										value="%{stat.product.unitPrice}"></s:hidden>
									<s:set var="varUnitPrice"
										value="%{getText('format.money',{stat.product.unitPrice})}"></s:set>
									<input type="text" id="unitPriceFm" name="unitPriceFm" readonly
										data-validate-minmax="1,1000000000"
										value="${varUnitPrice}"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="totalBox">Số thùng <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="totalBox" name="totalBox"
										value="${stat.totalBox}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="quantity">Số lượng <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<input type="number" id="quantity" name="quantity"
										value="${stat.quantity}" required="required"
										data-validate-minmax="0,100000"
										class="form-control col-md-7 col-xs-12">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="total">Thành tiền <span class="required">*</span>
								</label>
								<div class="col-md-3 col-sm-6 col-xs-12">
									<s:hidden id="total" name="total" value="%{stat.total}"></s:hidden>
									<s:set var="varTotal"
										value="%{getText('format.money',{stat.total})}"></s:set>
									<input type=text id="totalFm" name="totalFm"
										required="required" value="${varTotal}" readonly
										data-validate-minmax="0,1000000000"
										class="form-control col-md-7 col-xs-12">

								</div>
							</div>

							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<button id="send" type="submit" class="btn btn-success"><s:if test="%{edit}">
											Cập nhật
										</s:if>
										<s:else>
											Thêm
										</s:else></button>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer content -->
	<jsp:include page="footer.jsp"></jsp:include>
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
<!-- form validation -->
<script src="js/validator/validator.js"></script>

<!-- Auto lookup  -->
<link rel="stylesheet" type="text/css" href="css/autocomplete/tautocomplete.css" />
<script src="Scripts/autocomplete/tautocomplete.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
    	alert("123");
        var text2 = $("#cusLevel1").tautocomplete({
        	 width: "500px",
             columns: ['Country ID', 'Code', 'Capital'],
             data: function () {
                 try{
						$("#ta-id").html(text2.searchdata());
                     var data = [{ "id": 1, "country": "Afghanistan", "code": "AFG", "capital": "Kabul" }, { "id": 2, "country": "Albania", "code": "ALB", "capital": "Tirane" }, { "id": 3, "country": "Algeria", "code": "DZA", "capital": "Algiers" }, { "id": 4, "country": "Andorra", "code": "AND", "capital": "Andorra la Vella" }, { "id": 5, "country": "Angola", "code": "AGO", "capital": "Luanda" }, { "id": 6, "country": "Antigua and Barbuda", "code": "ATG", "capital": "Saint John" }, { "id": 7, "country": "Argentina", "code": "ARG", "capital": "Buenos Aires" }, { "id": 8, "country": "Armenia", "code": "ARM", "capital": "Yerevan" }, { "id": 9, "country": "Australia", "code": "AUS", "capital": "Canberra" }, { "id": 10, "country": "Austria", "code": "AUT", "capital": "Vienna" }, { "id": 11, "country": "Azerbaijan", "code": "AZE", "capital": "Baku" }, { "id": 12, "country": "The Bahamas", "code": "THA", "capital": "Nassau" }, { "id": 13, "country": "Bahrain", "code": "BHR", "capital": "Manama" }, { "id": 14, "country": "Bangladesh", "code": "BGD", "capital": "Dhaka" }, { "id": 15, "country": "Barbados", "code": "BRB", "capital": "Bridgetown" }, { "id": 16, "country": "Belarus", "code": "BLR", "capital": "Minsk" }, { "id": 17, "country": "Belgium", "code": "BEL", "capital": "Brussels" }, { "id": 18, "country": "Belize", "code": "BLZ", "capital": "Belmopan" }, { "id": 19, "country": "Benin", "code": "BEN", "capital": "Porto-Novo" }, { "id": 20, "country": "Bhutan", "code": "BTN", "capital": "Thimphu" }, { "id": 21, "country": "Bolivia", "code": "BOL", "capital": "Sucre" }, { "id": 22, "country": "Bosnia and Herzegovina", "code": "BOL", "capital": "Sarajevo" }, { "id": 23, "country": "Botswana", "code": "BWA", "capital": "Gaborone" }, { "id": 24, "country": "Brazil", "code": "BRA", "capital": "Brasilia" }, { "id": 25, "country": "Brunei", "code": "IOT", "capital": "Bandar Seri Begawan" }, { "id": 26, "country": "Bulgaria", "code": "BGR", "capital": "Sofia" }, { "id": 27, "country": "Burkina Faso", "code": "BFA", "capital": "Ouagadougou" }, { "id": 28, "country": "Burundi", "code": "BDI", "capital": "Bujumbura" }, { "id": 29, "country": "Cambodia", "code": "KHM", "capital": "Phnom Penh" }, { "id": 30, "country": "Cameroon", "code": "CMR", "capital": "Yaounde" }, { "id": 31, "country": "Canada", "code": "CAN", "capital": "Ottawa" }, { "id": 32, "country": "Cape Verde", "code": "CPV", "capital": "Praia" }, { "id": 33, "country": "Central African Republic", "code": "CAF", "capital": "Bangui" }, { "id": 34, "country": "Chad", "code": "TCD", "capital": "NDjamena" }, { "id": 35, "country": "Chile", "code": "CHL", "capital": "Santiago" }, { "id": 36, "country": "China", "code": "CHN", "capital": "Beijing" }, { "id": 37, "country": "Colombia", "code": "COL", "capital": "Bogota" }, { "id": 38, "country": "Comoros", "code": "COM", "capital": "Moroni" }, { "id": 39, "country": "Congo, Republic of the", "code": "COG", "capital": "Brazzaville" }, { "id": 40, "country": "Congo, Democratic Republic of the", "code": "COG", "capital": "Kinshasa" }, { "id": 41, "country": "Costa Rica", "code": "CRI", "capital": "San Jose" }, { "id": 42, "country": "Cote d’Ivoire", "code": "CRI", "capital": "Yamoussoukro" }, { "id": 43, "country": "Croatia", "code": "CIV", "capital": "Zagreb" }, { "id": 44, "country": "Cuba", "code": "CUB", "capital": "Havana" }, { "id": 45, "country": "Cyprus", "code": "CYP", "capital": "Nicosia" }, { "id": 46, "country": "Czech Republic", "code": "CZE", "capital": "Prague" }, { "id": 47, "country": "Denmark", "code": "DNK", "capital": "Copenhagen" }, { "id": 48, "country": "Djibouti", "code": "DJI", "capital": "Djibouti" }, { "id": 49, "country": "Dominica", "code": "DMA", "capital": "Roseau" }, { "id": 50, "country": "Dominican Republic", "code": "DOM", "capital": "Santo Domingo" }, { "id": 51, "country": "East Timor (Timor-Leste)", "code": "TMP", "capital": "Dili" }, { "id": 52, "country": "Ecuador", "code": "ECU", "capital": "Quito" }, { "id": 53, "country": "Egypt", "code": "EGY", "capital": "Cairo" }, { "id": 54, "country": "El Salvador", "code": "SLV", "capital": "San Salvador" }, { "id": 55, "country": "Equatorial Guinea", "code": "GNQ", "capital": "Malabo" }, { "id": 56, "country": "Eritrea", "code": "ERI", "capital": "Asmara" }, { "id": 57, "country": "Estonia", "code": "EST", "capital": "Tallinn" }, { "id": 58, "country": "Ethiopia", "code": "ETH ", "capital": "Addis Ababa" }, { "id": 59, "country": "Fiji", "code": "FJI", "capital": "Suva" }, { "id": 60, "country": "Finland", "code": "FIN", "capital": "Helsinki" }, { "id": 61, "country": "France", "code": "FRA", "capital": "Paris" }, { "id": 62, "country": "Gabon", "code": "GAB", "capital": "Libreville" }, { "id": 63, "country": "The Gambia", "code": "THA", "capital": "Banjul" }, { "id": 64, "country": "Georgia", "code": "GEO", "capital": "Tbilisi" }, { "id": 65, "country": "Germany", "code": "DEU", "capital": "Berlin" }, { "id": 66, "country": "Ghana", "code": "GHA", "capital": "Accra" }, { "id": 67, "country": "Greece", "code": "GRC", "capital": "Athens" }, { "id": 68, "country": "Grenada", "code": "GRD", "capital": "Saint George" }, { "id": 69, "country": "Guatemala", "code": "GTM", "capital": "Guatemala City" }, { "id": 70, "country": "Guinea", "code": "GIN", "capital": "Conakry" }, { "id": 71, "country": "Guinea-Bissau", "code": "GNB", "capital": "Bissau" }, { "id": 72, "country": "Guyana", "code": "GUY", "capital": "Georgetown" }, { "id": 73, "country": "Haiti", "code": "HTI", "capital": "Port-au-Prince" }, { "id": 74, "country": "Honduras", "code": "HND", "capital": "Tegucigalpa" }, { "id": 75, "country": "Hungary", "code": "HUN", "capital": "Budapest" }, { "id": 76, "country": "Iceland", "code": "ISL", "capital": "Reykjavik" }, { "id": 77, "country": "India", "code": "IND", "capital": "New Delhi" }, { "id": 78, "country": "Indonesia", "code": "IDN", "capital": "Jakarta" }, { "id": 79, "country": "Iran", "code": "IDN", "capital": "Tehran" }, { "id": 80, "country": "Iraq", "code": "IRQ", "capital": "Baghdad" }, { "id": 81, "country": "Ireland", "code": "IRL", "capital": "Dublin" }, { "id": 82, "country": "Israel", "code": "ISR", "capital": "Jerusalem" }, { "id": 83, "country": "Italy", "code": "ITA", "capital": "Rome" }, { "id": 84, "country": "Jamaica", "code": "JAM", "capital": "Kingston" }, { "id": 85, "country": "Japan", "code": "JPN", "capital": "Tokyo" }, { "id": 86, "country": "Jordan", "code": "JOR", "capital": "Amman" }, { "id": 87, "country": "Kazakhstan", "code": "KAZ", "capital": "Astana" }, { "id": 88, "country": "Kenya", "code": "KEN", "capital": "Nairobi" }, { "id": 89, "country": "Kiribati", "code": "KIR", "capital": "Tarawa Atoll" }, { "id": 90, "country": "Korea, North", "code": "PRK", "capital": "Pyongyang" }, { "id": 91, "country": "Korea, South", "code": "KOR", "capital": "Seoul" }, { "id": 92, "country": "Kosovo", "code": "KOR", "capital": "Pristina" }, { "id": 93, "country": "Kuwait", "code": "KWT", "capital": "Kuwait City" }, { "id": 94, "country": "Kyrgyzstan", "code": "KGZ", "capital": "Bishkek" }, { "id": 95, "country": "Laos", "code": "KGZ", "capital": "Vientiane" }, { "id": 96, "country": "Latvia", "code": "LVA", "capital": "Riga" }, { "id": 97, "country": "Lebanon", "code": "LBN", "capital": "Beirut" }, { "id": 98, "country": "Lesotho", "code": "LSO", "capital": "Maseru" }, { "id": 99, "country": "Liberia", "code": "LBR", "capital": "Monrovia" }, { "id": 100, "country": "Libya", "code": "LBR", "capital": "Tripoli" }, { "id": 101, "country": "Liechtenstein", "code": "LIE", "capital": "Vaduz" }, { "id": 102, "country": "Lithuania", "code": "LTU", "capital": "Vilnius" }, { "id": 103, "country": "Luxembourg", "code": "LUX", "capital": "Luxembourg" }, { "id": 104, "country": "Macedonia", "code": "MKD", "capital": "Skopje" }, { "id": 105, "country": "Madagascar", "code": "MDG", "capital": "Antananarivo" }, { "id": 106, "country": "Malawi", "code": "MWI", "capital": "Lilongwe" }, { "id": 107, "country": "Malaysia", "code": "MYS", "capital": "Kuala Lumpur" }, { "id": 108, "country": "Maldives", "code": "MDV", "capital": "Male" }, { "id": 109, "country": "Mali", "code": "MLI", "capital": "Bamako" }, { "id": 110, "country": "Malta", "code": "MLT", "capital": "Valletta" }, { "id": 111, "country": "Marshall Islands", "code": "MHL", "capital": "Majuro" }, { "id": 112, "country": "Mauritania", "code": "MRT", "capital": "Nouakchott" }, { "id": 113, "country": "Mauritius", "code": "MUS", "capital": "Port Louis" }, { "id": 114, "country": "Mexico", "code": "MEX", "capital": "Mexico City" }, { "id": 115, "country": "Micronesia, Federated States of", "code": "FSM", "capital": "Palikir" }, { "id": 116, "country": "Moldova", "code": "FSM", "capital": "Chisinau" }, { "id": 117, "country": "Monaco", "code": "MCO", "capital": "Monaco" }, { "id": 118, "country": "Mongolia", "code": "MNG", "capital": "Ulaanbaatar" }, { "id": 119, "country": "Montenegro", "code": "MNE", "capital": "Podgorica" }, { "id": 120, "country": "Morocco", "code": "MAR", "capital": "Rabat" }, { "id": 121, "country": "Mozambique", "code": "MOZ", "capital": "Maputo" }, { "id": 122, "country": "Myanmar (Burma)", "code": "MMR", "capital": "Naypyidaw" }, { "id": 123, "country": "Namibia", "code": "NAM", "capital": "Windhoek" }, { "id": 124, "country": "Nauru", "code": "NRU", "capital": "Yaren" }, { "id": 125, "country": "Nepal", "code": "NPL", "capital": "Kathmandu" }, { "id": 126, "country": "Netherlands (Holland)", "code": "ANT", "capital": "Amsterdam" }, { "id": 127, "country": "New Zealand", "code": "NZL", "capital": "Wellington" }, { "id": 128, "country": "Nicaragua", "code": "NIC", "capital": "Managua" }, { "id": 129, "country": "Niger", "code": "NER", "capital": "Niamey" }, { "id": 130, "country": "Nigeria", "code": "NGA", "capital": "Abuja" }, { "id": 131, "country": "Norway", "code": "NOR", "capital": "Oslo" }, { "id": 132, "country": "Oman", "code": "OMN", "capital": "Muscat" }, { "id": 133, "country": "Pakistan", "code": "PAK", "capital": "Islamabad" }, { "id": 134, "country": "Palau", "code": "PLW", "capital": "Melekeok" }, { "id": 135, "country": "Panama", "code": "PAN", "capital": "Panama City" }, { "id": 136, "country": "Papua New Guinea", "code": "PNG", "capital": "Port Moresby" }, { "id": 137, "country": "Paraguay", "code": "PRY", "capital": "Asuncion" }, { "id": 138, "country": "Peru", "code": "PER", "capital": "Lima" }, { "id": 139, "country": "Philippines", "code": "PHL", "capital": "Manila" }, { "id": 140, "country": "Poland", "code": "POL", "capital": "Warsaw" }, { "id": 141, "country": "Portugal", "code": "PRT", "capital": "Lisbon" }, { "id": 142, "country": "Qatar", "code": "QAT", "capital": "Doha" }, { "id": 143, "country": "Romania", "code": "ROM", "capital": "Bucharest" }, { "id": 144, "country": "Russia", "code": "ROM", "capital": "Moscow" }, { "id": 145, "country": "Rwanda", "code": "RWA", "capital": "Kigali" }, { "id": 146, "country": "Saint Kitts and Nevis", "code": "KNA", "capital": "Basseterre" }, { "id": 147, "country": "Saint Lucia", "code": "LCA", "capital": "Castries" }, { "id": 148, "country": "Saint Vincent and the Grenadines", "code": "VCT", "capital": "Kingstown" }, { "id": 149, "country": "Samoa", "code": "WSM", "capital": "Apia" }, { "id": 150, "country": "San Marino", "code": "SMR", "capital": "San Marino" }, { "id": 151, "country": "Sao Tome and Principe", "code": "STP", "capital": "Sao Tome" }, { "id": 152, "country": "Saudi Arabia", "code": "SAU", "capital": "Riyadh" }, { "id": 153, "country": "Senegal", "code": "SEN", "capital": "Dakar" }, { "id": 154, "country": "Serbia", "code": "SRB", "capital": "Belgrade" }, { "id": 155, "country": "Seychelles", "code": "SYC", "capital": "Victoria" }, { "id": 156, "country": "Sierra Leone", "code": "SLE", "capital": "Freetown" }, { "id": 157, "country": "Singapore", "code": "SGP", "capital": "Singapore" }, { "id": 158, "country": "Slovakia", "code": "SGP", "capital": "Bratislava" }, { "id": 159, "country": "Slovenia", "code": "SVN", "capital": "Ljubljana" }, { "id": 160, "country": "Solomon Islands", "code": "SLB", "capital": "Honiara" }, { "id": 161, "country": "Somalia", "code": "SOM", "capital": "Mogadishu" }, { "id": 162, "country": "South Africa", "code": "ZAF", "capital": "Cape Town" }, { "id": 163, "country": "South Sudan", "code": "SSD", "capital": "Juba" }, { "id": 164, "country": "Spain", "code": "ESP", "capital": "Madrid" }, { "id": 165, "country": "Sri Lanka", "code": "LKA", "capital": "Colombo" }, { "id": 166, "country": "Sudan", "code": "SDN", "capital": "Khartoum" }, { "id": 167, "country": "Suriname", "code": "SUR", "capital": "Paramaribo" }, { "id": 168, "country": "Swaziland", "code": "SWZ", "capital": "Lobamba" }, { "id": 169, "country": "Sweden", "code": "SWE", "capital": "Stockholm" }, { "id": 170, "country": "Switzerland", "code": "CHE", "capital": "Bern" }, { "id": 171, "country": "Syria", "code": "CHE", "capital": "Damascus" }, { "id": 172, "country": "Taiwan", "code": "SYR", "capital": "Taipei" }, { "id": 173, "country": "Tajikistan", "code": "TJK", "capital": "Dushanbe" }, { "id": 174, "country": "Tanzania", "code": "TJK", "capital": "Dar es Salaam" }, { "id": 175, "country": "Thailand", "code": "THA", "capital": "Bangkok" }, { "id": 176, "country": "Togo", "code": "TGO", "capital": "Lome" }, { "id": 177, "country": "Tonga", "code": "TON", "capital": "Nukualofa" }, { "id": 178, "country": "Trinidad and Tobago", "code": "TTO", "capital": "Port-of-Spain" }, { "id": 179, "country": "Tunisia", "code": "TUN", "capital": "Tunis" }, { "id": 180, "country": "Turkey", "code": "TUR", "capital": "Ankara" }, { "id": 181, "country": "Turkmenistan", "code": "TKM", "capital": "Ashgabat" }, { "id": 182, "country": "Tuvalu", "code": "TUV", "capital": "Funafuti province" }, { "id": 183, "country": "Uganda", "code": "UGA", "capital": "Kampala" }, { "id": 184, "country": "Ukraine", "code": "UKR", "capital": "Kyiv" }, { "id": 185, "country": "United Arab Emirates", "code": "ARE", "capital": "Abu Dhabi" }, { "id": 186, "country": "United Kingdom", "code": "GBR", "capital": "London" }, { "id": 187, "country": "United States of America", "code": "USA", "capital": "Washington D.C." }, { "id": 188, "country": "Uruguay", "code": "URY", "capital": "Montevideo" }, { "id": 189, "country": "Uzbekistan", "code": "UZB", "capital": "Tashkent" }, { "id": 190, "country": "Vanuatu", "code": "VUT", "capital": "Port-Vila" }, { "id": 191, "country": "Vatican City (Holy See)", "code": "VUT", "capital": "Vatican City" }, { "id": 192, "country": "Venezuela", "code": "VEN", "capital": "Caracas" }, { "id": 193, "country": "Vietnam", "code": "VNM", "capital": "Hanoi" }, { "id": 194, "country": "Yemen", "code": "YEM", "capital": "Sanaa" }, { "id": 195, "country": "Zambia", "code": "ZMB", "capital": "Lusaka" }, { "id": 196, "country": "Zimbabwe", "code": "ZMB", "capital": "Harare" }];
                 }
                 catch(e)
                 {
                     alert(e)
                 }
                 var filterData = [];

                 var searchData = eval("/" + text2.searchdata() + "/gi");
                 
                 $.each(data, function(i,v)
                 {
                     if (v.country.search(new RegExp(searchData)) != -1) {
                         filterData.push(v);
                     }							
                 });
                 return filterData;
             },
             onchange: function () {
            	 
             }
        });
    });
</script>
<!-- Auto lookup  -->

<!-- daterangepicker -->
<script type="text/javascript" src="js/moment.min2.js"></script>
<script type="text/javascript" src="js/datepicker/daterangepicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		alert("456");
		$('#dateReceived').daterangepicker({
			singleDatePicker : true,
			calender_style : "picker_2",
			format : 'DD/MM/YYYY',
			showDropdowns : true
		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>
<script>
	// initialize the validator function
	validator.message['date'] = 'not a real date';

	// validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
	$('form').on('blur', 'input[required], input.optional, select.required',
			validator.checkField).on('change', 'select.required',
			validator.checkField).on('keypress', 'input[required][pattern]',
			validator.keypress);

	$('.multi.required').on('keyup blur', 'input', function() {
		validator.checkField.apply($(this).siblings().last()[0]);
	});

	// bind the validation to the form submit event
	//$('#send').click('submit');//.prop('disabled', true);

	$('form').submit(function(e) {
		e.preventDefault();
		var submit = true;
		// evaluate the form using generic validaing
		if (!validator.checkAll($(this))) {
			submit = false;
		}

		if (submit)
			this.submit();
		return false;
	});


</script>

<script>
	$(document).ready(function() {
		$('#quantity').change(function() {
			var unitPrice = $("#unitPrice").val();
			var quantity = $("#quantity").val();
			$('#total').val((unitPrice * quantity));
			$('#totalFm').val(((unitPrice * quantity)+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
		});
	});
</script>

<script>
	$(document).ready(function() {
		$('#pro_id').change(function() {
			var proId = {
				"proId" : $("#pro_id").val()
			};
			$.ajax({
				url : "readInfoStatistic",
				data : JSON.stringify(proId),
				dataType : 'json',
				contentType : 'application/json',
				type : 'POST',
				async : true,
				success : function(res) {
					$('#unitPrice').val(res);
					$('#unitPriceFm').val((res+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
					var quantity = $("#quantity").val();
					$('#total').val(res * quantity);
					$('#totalFm').val(((res * quantity)+"").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
					// 															for (var i = 0; i < res.length; i++) {
					// 																$('#district')
					// 																		.append(
					// 																				'<option value=' + res[i] + '>'
					// 																						+ res[i]
					// 																						+ '</option>');
					// 															}
				}
			});
		});
	});
</script>

</body>

</html>