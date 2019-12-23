<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategyaccount-resources"/>
<html>
<head>
<title>Edit <fmt:message key="strategyaccount.title"/> <fmt:message key="strategy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1><fmt:message key="navigation.edit"/> <fmt:message key="strategy.title"/></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectStrategyAccount?strategyAccountIdKey=${strategyaccount_strategyAccountId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveStrategyAccountStrategy" method="POST" modelAttribute="strategy">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.strategyid.title"/>:
						</td>
						<td>
							<c:choose>
								<c:when test='${newFlag}' >
							<form:input id="strategy_strategyId" path="strategyId" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_strategyId",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.strategyid.help"/>", constraints : {places:0}}})); </script>
								</c:when>
								<c:otherwise>
							${strategy.strategyId}
						&nbsp;
									<form:hidden id="strategy_strategyId" path="strategyId"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.strategyname.title"/>:
						</td>
						<td>
							<form:input id="strategy_strategyName" path="strategyName" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_strategyName",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.strategyname.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.symbol.title"/>:
						</td>
						<td>
							<form:input id="strategy_symbol" path="symbol" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_symbol",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.symbol.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.activeflag.title"/>:
						</td>
						<td>
							<form:input id="strategy_activeFlag" path="activeFlag" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_activeFlag",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.activeflag.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.initnumspreadscall.title"/>:
						</td>
						<td>
							<form:input id="strategy_initNumSpreadsCall" path="initNumSpreadsCall" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_initNumSpreadsCall",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.initnumspreadscall.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.initnumspreadsput.title"/>:
						</td>
						<td>
							<form:input id="strategy_initNumSpreadsPut" path="initNumSpreadsPut" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_initNumSpreadsPut",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.initnumspreadsput.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.percententerlow.title"/>:
						</td>
						<td>
							<form:input id="strategy_percentEnterLow" path="percentEnterLow" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_percentEnterLow",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.percententerlow.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.percententerhigh.title"/>:
						</td>
						<td>
							<form:input id="strategy_percentEnterHigh" path="percentEnterHigh" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_percentEnterHigh",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.percententerhigh.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysenterlow.title"/>:
						</td>
						<td>
							<form:input id="strategy_daysEnterLow" path="daysEnterLow" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_daysEnterLow",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.daysenterlow.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysenterhigh.title"/>:
						</td>
						<td>
							<form:input id="strategy_daysEnterHigh" path="daysEnterHigh" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_daysEnterHigh",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.daysenterhigh.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.amounttotalrisk.title"/>:
						</td>
						<td>
							<form:input id="strategy_amountTotalRisk" path="amountTotalRisk" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_amountTotalRisk",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.amounttotalrisk.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.amountriskperpoint.title"/>:
						</td>
						<td>
							<form:input id="strategy_amountRiskPerPoint" path="amountRiskPerPoint" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_amountRiskPerPoint",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.amountriskperpoint.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.distancebetoptions.title"/>:
						</td>
						<td>
							<form:input id="strategy_distanceBetOptions" path="distanceBetOptions" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_distanceBetOptions",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.distancebetoptions.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.optpricestoget.title"/>:
						</td>
						<td>
							<form:input id="strategy_optPricesToGet" path="optPricesToGet" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_optPricesToGet",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.optpricestoget.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pctsettrailingstop.title"/>:
						</td>
						<td>
							<form:input id="strategy_pctSetTrailingStop" path="pctSetTrailingStop" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_pctSetTrailingStop",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.pctsettrailingstop.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pcttrailingstop.title"/>:
						</td>
						<td>
							<form:input id="strategy_pctTrailingStop" path="pctTrailingStop" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_pctTrailingStop",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.pcttrailingstop.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pctexitwin.title"/>:
						</td>
						<td>
							<form:input id="strategy_pctExitWin" path="pctExitWin" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_pctExitWin",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.pctexitwin.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setwintrailingstop.title"/>:
						</td>
						<td>
							<form:input id="strategy_setWinTrailingStop" path="setWinTrailingStop" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setWinTrailingStop",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setwintrailingstop.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setexitatinsprice.title"/>:
						</td>
						<td>
							<form:input id="strategy_setExitAtInsPrice" path="setExitAtInsPrice" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setExitAtInsPrice",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setexitatinsprice.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setreenteronwin.title"/>:
						</td>
						<td>
							<form:input id="strategy_setReenterOnWin" path="setReenterOnWin" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setReenterOnWin",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setreenteronwin.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setreenteronloss.title"/>:
						</td>
						<td>
							<form:input id="strategy_setReenterOnLoss" path="setReenterOnLoss" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setReenterOnLoss",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setreenteronloss.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pointdiffforreentry.title"/>:
						</td>
						<td>
							<form:input id="strategy_pointDiffForReentry" path="pointDiffForReentry" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_pointDiffForReentry",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.pointdiffforreentry.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysexitbeforeexpiry.title"/>:
						</td>
						<td>
							<form:input id="strategy_daysExitBeforeExpiry" path="daysExitBeforeExpiry" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_daysExitBeforeExpiry",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.daysexitbeforeexpiry.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setbreakevenwheninshit.title"/>:
						</td>
						<td>
							<form:input id="strategy_setBreakevenWhenInsHit" path="setBreakevenWhenInsHit" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setBreakevenWhenInsHit",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setbreakevenwheninshit.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.mswaitforibresponse.title"/>:
						</td>
						<td>
							<form:input id="strategy_msWaitForIbResponse" path="msWaitForIbResponse" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_msWaitForIbResponse",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.mswaitforibresponse.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.mindelayfromstart.title"/>:
						</td>
						<td>
							<form:input id="strategy_minDelayFromStart" path="minDelayFromStart" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_minDelayFromStart",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.mindelayfromstart.help"/>", constraints : {places:0}}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setcheckvix.title"/>:
						</td>
						<td>
							<form:input id="strategy_setCheckVix" path="setCheckVix" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_setCheckVix",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.setcheckvix.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.noentryifvixover.title"/>:
						</td>
						<td>
							<form:input id="strategy_noEntryIfVixOver" path="noEntryIfVixOver" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_noEntryIfVixOver",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.noentryifvixover.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.timezone.title"/>:
						</td>
						<td>
							<form:input id="strategy_timezone" path="timezone" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_timezone",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.timezone.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.liveortestflag.title"/>:
						</td>
						<td>
							<form:input id="strategy_liveOrTestFlag" path="liveOrTestFlag" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_liveOrTestFlag",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.liveortestflag.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.comissionpertrade.title"/>:
						</td>
						<td>
							<form:input id="strategy_comissionPerTrade" path="comissionPerTrade" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_comissionPerTrade",widgetType : "dijit.form.NumberTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.comissionpertrade.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.createdby.title"/>:
						</td>
						<td>
							<form:input id="strategy_createdBy" path="createdBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_createdBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.createdby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.createddate.title"/>:
						</td>
						<td>
							<input id="strategy_createdDate" name="createdDate" type="text" value="<fmt:formatDate value="${strategy.createdDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.updatedby.title"/>:
						</td>
						<td>
							<form:input id="strategy_updatedBy" path="updatedBy" cssStyle="width:300px;"/>
							<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "strategy_updatedBy",widgetType : "dijit.form.ValidationTextBox",widgetAttrs : {promptMessage: "<fmt:message key="strategy.updatedby.help"/>"}})); </script>
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.updateddate.title"/>:
						</td>
						<td>
							<input id="strategy_updatedDate" name="updatedDate" type="text" value="<fmt:formatDate value="${strategy.updatedDate.time}" pattern="yyyy-MM-dd"/>" dojoType="dijit.form.DateTextBox" constraints="{datePattern:'<fmt:message key="date.format"/>'}" trim="true" promptMessage="<fmt:message key="date.format" />" invalidMessage="<fmt:message key="date.format.invalid" /> <fmt:message key="date.format" />." style="width:300px;" />
						</td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>"/></span>
			<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));</script>
				<input type="hidden" name="strategyaccount_strategyAccountId" value="${strategyaccount_strategyAccountId}" >
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
