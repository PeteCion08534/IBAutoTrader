<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategyaccount-resources"/>
<html>
<head>
<title>View <fmt:message key="strategyaccount.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="strategyaccount.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexStrategyAccount"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="strategyaccount.strategyaccountid.title"/>:
						</td>
						<td>
							${strategyaccount.strategyAccountId}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="strategy.title"/></h1>
					
						<c:if test='${strategyaccount.strategy != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.strategyid.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.strategyId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.strategyname.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.strategyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.symbol.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.symbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.activeflag.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.activeFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.initnumspreadscall.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.initNumSpreadsCall}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.initnumspreadsput.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.initNumSpreadsPut}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.percententerlow.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.percentEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.percententerhigh.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.percentEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysenterlow.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.daysEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysenterhigh.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.daysEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.amounttotalrisk.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.amountTotalRisk}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.amountriskperpoint.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.amountRiskPerPoint}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.distancebetoptions.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.distanceBetOptions}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.optpricestoget.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.optPricesToGet}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pctsettrailingstop.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.pctSetTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pcttrailingstop.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.pctTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pctexitwin.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.pctExitWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setwintrailingstop.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setWinTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setexitatinsprice.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setExitAtInsPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setreenteronwin.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setReenterOnWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setreenteronloss.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setReenterOnLoss}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pointdiffforreentry.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.pointDiffForReentry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysexitbeforeexpiry.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.daysExitBeforeExpiry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setbreakevenwheninshit.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setBreakevenWhenInsHit}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.mswaitforibresponse.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.msWaitForIbResponse}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.mindelayfromstart.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.minDelayFromStart}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setcheckvix.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.setCheckVix}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.noentryifvixover.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.noEntryIfVixOver}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.timezone.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.timezone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.liveortestflag.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.liveOrTestFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.comissionpertrade.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.comissionPerTrade}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.createdby.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategyaccount.strategy.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.updatedby.title"/>:
						</td>
						<td>
							${strategyaccount.strategy.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategyaccount.strategy.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/editStrategyAccountStrategy?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&strategy_strategyId=${strategyaccount.strategy.strategyId}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteStrategyAccountStrategy?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&strategy_strategyId=${strategyaccount.strategy.strategyId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${strategyaccount.strategy == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategyAccountStrategy?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategy.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="ibaccount.title"/></h1>
					
						<c:if test='${strategyaccount.ibAccount != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.ibaccountid.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.ibAccountId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.ibaccountidext.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.ibAccountIdExt}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.ipaddress.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.ipAddress}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.port.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.port}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.clientid.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.clientId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.createdby.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategyaccount.ibAccount.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.updatedby.title"/>:
						</td>
						<td>
							${strategyaccount.ibAccount.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="ibaccount.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${strategyaccount.ibAccount.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/editStrategyAccountIbAccount?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&ibaccount_ibAccountId=${strategyaccount.ibAccount.ibAccountId}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteStrategyAccountIbAccount?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&ibaccount_ibAccountId=${strategyaccount.ibAccount.ibAccountId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${strategyaccount.ibAccount == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategyAccountIbAccount?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="ibaccount.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="position.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategyAccountPositions?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="position.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="position.positionid.title"/></th>
						<th class="thead"><fmt:message key="position.strategyname.title"/></th>
						<th class="thead"><fmt:message key="position.symbol.title"/></th>
						<th class="thead"><fmt:message key="position.optright.title"/></th>
						<th class="thead"><fmt:message key="position.expiryyear.title"/></th>
						<th class="thead"><fmt:message key="position.expirymonth.title"/></th>
						<th class="thead"><fmt:message key="position.expiryday.title"/></th>
						<th class="thead"><fmt:message key="position.expirytimeframe.title"/></th>
						<th class="thead"><fmt:message key="position.goalnumspreads.title"/></th>
						<th class="thead"><fmt:message key="position.numopenspreads.title"/></th>
						<th class="thead"><fmt:message key="position.numwins.title"/></th>
						<th class="thead"><fmt:message key="position.numlosses.title"/></th>
						<th class="thead"><fmt:message key="position.profitlossunrealized.title"/></th>
						<th class="thead"><fmt:message key="position.profitlossrealized.title"/></th>
						<th class="thead"><fmt:message key="position.lastexitsecurityprice.title"/></th>
						<th class="thead"><fmt:message key="position.reentrysecpricecallabove.title"/></th>
						<th class="thead"><fmt:message key="position.reentrysecpriceputbelow.title"/></th>
						<th class="thead"><fmt:message key="position.totalrisked.title"/></th>
						<th class="thead"><fmt:message key="position.createdby.title"/></th>
						<th class="thead"><fmt:message key="position.createddate.title"/></th>
						<th class="thead"><fmt:message key="position.updatedby.title"/></th>
						<th class="thead"><fmt:message key="position.updateddate.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${strategyaccount.positions}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectStrategyAccountPositions?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&position_positionId=${current.positionId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editStrategyAccountPositions?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&position_positionId=${current.positionId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteStrategyAccountPositions?strategyaccount_strategyAccountId=${strategyaccount.strategyAccountId}&position_positionId=${current.positionId}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.positionId}
						&nbsp;
						</td>
						<td>
							${current.strategyName}
						&nbsp;
						</td>
						<td>
							${current.symbol}
						&nbsp;
						</td>
						<td>
							${current.optRight}
						&nbsp;
						</td>
						<td>
							${current.expiryYear}
						&nbsp;
						</td>
						<td>
							${current.expiryMonth}
						&nbsp;
						</td>
						<td>
							${current.expiryDay}
						&nbsp;
						</td>
						<td>
							${current.expiryTimeframe}
						&nbsp;
						</td>
						<td>
							${current.goalNumSpreads}
						&nbsp;
						</td>
						<td>
							${current.numOpenSpreads}
						&nbsp;
						</td>
						<td>
							${current.numWins}
						&nbsp;
						</td>
						<td>
							${current.numLosses}
						&nbsp;
						</td>
						<td>
							${current.profitLossUnrealized}
						&nbsp;
						</td>
						<td>
							${current.profitLossRealized}
						&nbsp;
						</td>
						<td>
							${current.lastExitSecurityPrice}
						&nbsp;
						</td>
						<td>
							${current.reentrySecPriceCallAbove}
						&nbsp;
						</td>
						<td>
							${current.reentrySecPricePutBelow}
						&nbsp;
						</td>
						<td>
							${current.totalRisked}
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