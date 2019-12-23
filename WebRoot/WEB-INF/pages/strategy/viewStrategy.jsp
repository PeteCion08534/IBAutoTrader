<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategy-resources"/>
<html>
<head>
<title>View <fmt:message key="strategy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="strategy.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexStrategy"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.strategyid.title"/>:
						</td>
						<td>
							${strategy.strategyId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.strategyname.title"/>:
						</td>
						<td>
							${strategy.strategyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.symbol.title"/>:
						</td>
						<td>
							${strategy.symbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.activeflag.title"/>:
						</td>
						<td>
							${strategy.activeFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.initnumspreadscall.title"/>:
						</td>
						<td>
							${strategy.initNumSpreadsCall}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.initnumspreadsput.title"/>:
						</td>
						<td>
							${strategy.initNumSpreadsPut}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.percententerlow.title"/>:
						</td>
						<td>
							${strategy.percentEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.percententerhigh.title"/>:
						</td>
						<td>
							${strategy.percentEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysenterlow.title"/>:
						</td>
						<td>
							${strategy.daysEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysenterhigh.title"/>:
						</td>
						<td>
							${strategy.daysEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.amounttotalrisk.title"/>:
						</td>
						<td>
							${strategy.amountTotalRisk}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.amountriskperpoint.title"/>:
						</td>
						<td>
							${strategy.amountRiskPerPoint}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.distancebetoptions.title"/>:
						</td>
						<td>
							${strategy.distanceBetOptions}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.optpricestoget.title"/>:
						</td>
						<td>
							${strategy.optPricesToGet}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pctsettrailingstop.title"/>:
						</td>
						<td>
							${strategy.pctSetTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pcttrailingstop.title"/>:
						</td>
						<td>
							${strategy.pctTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pctexitwin.title"/>:
						</td>
						<td>
							${strategy.pctExitWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setwintrailingstop.title"/>:
						</td>
						<td>
							${strategy.setWinTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setexitatinsprice.title"/>:
						</td>
						<td>
							${strategy.setExitAtInsPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setreenteronwin.title"/>:
						</td>
						<td>
							${strategy.setReenterOnWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setreenteronloss.title"/>:
						</td>
						<td>
							${strategy.setReenterOnLoss}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.pointdiffforreentry.title"/>:
						</td>
						<td>
							${strategy.pointDiffForReentry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.daysexitbeforeexpiry.title"/>:
						</td>
						<td>
							${strategy.daysExitBeforeExpiry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setbreakevenwheninshit.title"/>:
						</td>
						<td>
							${strategy.setBreakevenWhenInsHit}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.mswaitforibresponse.title"/>:
						</td>
						<td>
							${strategy.msWaitForIbResponse}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.mindelayfromstart.title"/>:
						</td>
						<td>
							${strategy.minDelayFromStart}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.setcheckvix.title"/>:
						</td>
						<td>
							${strategy.setCheckVix}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.noentryifvixover.title"/>:
						</td>
						<td>
							${strategy.noEntryIfVixOver}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.timezone.title"/>:
						</td>
						<td>
							${strategy.timezone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.liveortestflag.title"/>:
						</td>
						<td>
							${strategy.liveOrTestFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.comissionpertrade.title"/>:
						</td>
						<td>
							${strategy.comissionPerTrade}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.createdby.title"/>:
						</td>
						<td>
							${strategy.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategy.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.updatedby.title"/>:
						</td>
						<td>
							${strategy.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategy.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategy.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="strategyaccount.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategyStrategyAccounts?strategy_strategyId=${strategy.strategyId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategyaccount.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="strategyaccount.strategyaccountid.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${strategy.strategyAccounts}" var="current"  varStatus="i">	
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td nowrap="nowrap">
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectStrategyStrategyAccounts?strategy_strategyId=${strategy.strategyId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editStrategyStrategyAccounts?strategy_strategyId=${strategy.strategyId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteStrategyStrategyAccounts?strategy_strategyId=${strategy.strategyId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.strategyAccountId}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="profitloss.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategyProfitLosses?strategy_strategyId=${strategy.strategyId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="profitloss.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="profitloss.profitlossid.title"/></th>
						<th class="thead"><fmt:message key="profitloss.profitlossstartdate.title"/></th>
						<th class="thead"><fmt:message key="profitloss.profitlossdate.title"/></th>
						<th class="thead"><fmt:message key="profitloss.profitorloss.title"/></th>
						<th class="thead"><fmt:message key="profitloss.profitorlossamt.title"/></th>
						<th class="thead"><fmt:message key="profitloss.createdby.title"/></th>
						<th class="thead"><fmt:message key="profitloss.createddate.title"/></th>
						<th class="thead"><fmt:message key="profitloss.updatedby.title"/></th>
						<th class="thead"><fmt:message key="profitloss.updateddate.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${strategy.profitLosses}" var="current"  varStatus="i">	
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td nowrap="nowrap">
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectStrategyProfitLosses?strategy_strategyId=${strategy.strategyId}&profitloss_profitLossId=${current.profitLossId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editStrategyProfitLosses?strategy_strategyId=${strategy.strategyId}&profitloss_profitLossId=${current.profitLossId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteStrategyProfitLosses?strategy_strategyId=${strategy.strategyId}&profitloss_profitLossId=${current.profitLossId}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.profitLossId}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.profitLossStartDate.time}"/>
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.profitLossDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.profitOrLoss}
						&nbsp;
						</td>
						<td>
							${current.profitOrLossAmt}
						&nbsp;
						</td>
						<td>
							${current.createdBy}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.createdDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.updatedBy}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>