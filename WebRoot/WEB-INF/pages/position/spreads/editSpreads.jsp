<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.position-resources"/>
<html>
<head>
<title>Edit <fmt:message key="position.title"/> <fmt:message key="spread.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="spread.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectPosition?positionIdKey=${position_positionId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/savePositionSpreads" method="POST" modelAttribute="spread">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.spreadid.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="spread_spreadId" path="spreadId" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_spreadId",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.spreadid.help"/>", constraints : {places:0}}})); </script>
								</c:when>
								<c:otherwise>
							${spread.spreadId}
						&nbsp;
									<form:hidden id="spread_spreadId" path="spreadId"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.openorclosed.title"/>:
						</td>
						<td>
							<form:input id="spread_openOrClosed" path="openOrClosed" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_openOrClosed",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.openorclosed.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.moneymkrpositionid.title"/>:
						</td>
						<td>
							<form:input id="spread_moneymkrPositionId" path="moneymkrPositionId" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_moneymkrPositionId",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.moneymkrpositionid.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.insurancepositionid.title"/>:
						</td>
						<td>
							<form:input id="spread_insurancePositionId" path="insurancePositionId" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_insurancePositionId",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.insurancepositionid.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.strikemoneymkr.title"/>:
						</td>
						<td>
							<form:input id="spread_strikeMoneymkr" path="strikeMoneymkr" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_strikeMoneymkr",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.strikemoneymkr.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.strikeinsurance.title"/>:
						</td>
						<td>
							<form:input id="spread_strikeInsurance" path="strikeInsurance" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_strikeInsurance",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.strikeinsurance.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entermoneymkrprice.title"/>:
						</td>
						<td>
							<form:input id="spread_enterMoneymkrPrice" path="enterMoneymkrPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_enterMoneymkrPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.entermoneymkrprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entermoneymkrdate.title"/>:
						</td>
						<td>
							<input id="spread_enterMoneymkrDate" name="enterMoneymkrDate" type="text" value="<fmt:formatDate value="${spread.enterMoneymkrDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.enterinsuranceprice.title"/>:
						</td>
						<td>
							<form:input id="spread_enterInsurancePrice" path="enterInsurancePrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_enterInsurancePrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.enterinsuranceprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.enterinsurancedate.title"/>:
						</td>
						<td>
							<input id="spread_enterInsuranceDate" name="enterInsuranceDate" type="text" value="<fmt:formatDate value="${spread.enterInsuranceDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entersecurityprice.title"/>:
						</td>
						<td>
							<form:input id="spread_enterSecurityPrice" path="enterSecurityPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_enterSecurityPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.entersecurityprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entersecuritydate.title"/>:
						</td>
						<td>
							<input id="spread_enterSecurityDate" name="enterSecurityDate" type="text" value="<fmt:formatDate value="${spread.enterSecurityDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entercommission.title"/>:
						</td>
						<td>
							<form:input id="spread_enterCommission" path="enterCommission" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_enterCommission",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.entercommission.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentmoneymkrprice.title"/>:
						</td>
						<td>
							<form:input id="spread_currentMoneymkrPrice" path="currentMoneymkrPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_currentMoneymkrPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.currentmoneymkrprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentinsuranceprice.title"/>:
						</td>
						<td>
							<form:input id="spread_currentInsurancePrice" path="currentInsurancePrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_currentInsurancePrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.currentinsuranceprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentvixprice.title"/>:
						</td>
						<td>
							<form:input id="spread_currentVixPrice" path="currentVixPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_currentVixPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.currentvixprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentsecurityprice.title"/>:
						</td>
						<td>
							<form:input id="spread_currentSecurityPrice" path="currentSecurityPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_currentSecurityPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.currentsecurityprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentdate.title"/>:
						</td>
						<td>
							<input id="spread_currentDate" name="currentDate" type="text" value="<fmt:formatDate value="${spread.currentDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitmoneymkrprice.title"/>:
						</td>
						<td>
							<form:input id="spread_exitMoneymkrPrice" path="exitMoneymkrPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitMoneymkrPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitmoneymkrprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitmoneymkrdate.title"/>:
						</td>
						<td>
							<input id="spread_exitMoneymkrDate" name="exitMoneymkrDate" type="text" value="<fmt:formatDate value="${spread.exitMoneymkrDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitinsuranceprice.title"/>:
						</td>
						<td>
							<form:input id="spread_exitInsurancePrice" path="exitInsurancePrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitInsurancePrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitinsuranceprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitinsurancedate.title"/>:
						</td>
						<td>
							<input id="spread_exitInsuranceDate" name="exitInsuranceDate" type="text" value="<fmt:formatDate value="${spread.exitInsuranceDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitsecurityprice.title"/>:
						</td>
						<td>
							<form:input id="spread_exitSecurityPrice" path="exitSecurityPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitSecurityPrice",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitsecurityprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitsecuritydate.title"/>:
						</td>
						<td>
							<input id="spread_exitSecurityDate" name="exitSecurityDate" type="text" value="<fmt:formatDate value="${spread.exitSecurityDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitcommission.title"/>:
						</td>
						<td>
							<form:input id="spread_exitCommission" path="exitCommission" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitCommission",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitcommission.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.trailingstopisset.title"/>:
						</td>
						<td>
							<form:input id="spread_trailingStopIsSet" path="trailingStopIsSet" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_trailingStopIsSet",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.trailingstopisset.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitabovespreadvalue.title"/>:
						</td>
						<td>
							<form:input id="spread_exitAboveSpreadValue" path="exitAboveSpreadValue" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitAboveSpreadValue",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitabovespreadvalue.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitbelowspreadvalue.title"/>:
						</td>
						<td>
							<form:input id="spread_exitBelowSpreadValue" path="exitBelowSpreadValue" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_exitBelowSpreadValue",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.exitbelowspreadvalue.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.profitlossunrealized.title"/>:
						</td>
						<td>
							<form:input id="spread_profitLossUnrealized" path="profitLossUnrealized" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_profitLossUnrealized",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.profitlossunrealized.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.profitlossrealized.title"/>:
						</td>
						<td>
							<form:input id="spread_profitLossRealized" path="profitLossRealized" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_profitLossRealized",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.profitlossrealized.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.createdby.title"/>:
						</td>
						<td>
							<form:input id="spread_createdBy" path="createdBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_createdBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.createdby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.createddate.title"/>:
						</td>
						<td>
							<input id="spread_createdDate" name="createdDate" type="text" value="<fmt:formatDate value="${spread.createdDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.updatedby.title"/>:
						</td>
						<td>
							<form:input id="spread_updatedBy" path="updatedBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "spread_updatedBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="spread.updatedby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.updateddate.title"/>:
						</td>
						<td>
							<input id="spread_updatedDate" name="updatedDate" type="text" value="<fmt:formatDate value="${spread.updatedDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
				<input type="hidden" name="position_positionId" value="${position_positionId}" >
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
