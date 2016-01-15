<%@ include file="header.jsp" %>
	<%@ include file="menu.jsp" %>
	<div class="content" id="content">
		<div class="sample">
			<div class="name">
				<s:property escape="false" value="messageStore.sample_name" />
			</div>
			<div class="dsc">
				<s:property escape="false" value="messageStore.sample_dsc" />
			</div>
			<div class="clear"></div>
		</div>
		<div class="scheduler" id="scheduler">
			<s:property escape="false" value="messageStore.scheduler" />
		</div>
	</div>
</body>
</html>