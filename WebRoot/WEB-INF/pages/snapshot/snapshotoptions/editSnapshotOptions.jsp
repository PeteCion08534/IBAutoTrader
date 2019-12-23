<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshot-resources"/>
<html>
<head>
<title>Edit <fmt:message key="snapshot.title"/> <fmt:message key="snapshotoption.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="snapshotoption.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectSnapshot?snapshotIdKey=${snapshot_snapshotId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveSnapshotSnapshotOptions" method="POST" modelAttribute="snapshotoption">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.snapshotoptionid.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="snapshotoption_snapshotOptionId" path="snapshotOptionId" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_snapshotOptionId",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.snapshotoptionid.help"/>", constraints : {places:0}}})); </script>
								</c:when>
								<c:otherwise>
							${snapshotoption.snapshotOptionId}
						&nbsp;
									<form:hidden id="snapshotoption_snapshotOptionId" path="snapshotOptionId"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.optright.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_optRight" path="optRight" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_optRight",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.optright.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.localsymbol.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_localSymbol" path="localSymbol" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_localSymbol",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.localsymbol.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.strike.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_strike" path="strike" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_strike",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.strike.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.bidprice.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_bidPrice" path="bidPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_bidPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.bidprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.askprice.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_askPrice" path="askPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_askPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.askprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.lastprice.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_lastPrice" path="lastPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_lastPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.lastprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.tradingtimeopentoday.title"/>:
						</td>
						<td>
							<input id="snapshotoption_tradingTimeOpenToday" name="tradingTimeOpenToday" type="text" value="<fmt:formatDate value="${snapshotoption.tradingTimeOpenToday.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.tradingtimeclosetoday.title"/>:
						</td>
						<td>
							<input id="snapshotoption_tradingTimeCloseToday" name="tradingTimeCloseToday" type="text" value="<fmt:formatDate value="${snapshotoption.tradingTimeCloseToday.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.createdby.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_createdBy" path="createdBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_createdBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.createdby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.createddate.title"/>:
						</td>
						<td>
							<input id="snapshotoption_createdDate" name="createdDate" type="text" value="<fmt:formatDate value="${snapshotoption.createdDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.updatedby.title"/>:
						</td>
						<td>
							<form:input id="snapshotoption_updatedBy" path="updatedBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "snapshotoption_updatedBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="snapshotoption.updatedby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.updateddate.title"/>:
						</td>
						<td>
							<input id="snapshotoption_updatedDate" name="updatedDate" type="text" value="<fmt:formatDate value="${snapshotoption.updatedDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
				<input type="hidden" name="snapshot_snapshotId" value="${snapshot_snapshotId}" >
				
				
				
				
				
				
				
				
				
				
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
