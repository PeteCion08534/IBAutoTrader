<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.position-resources"/>
<html>
<head>
<title>View <fmt:message key="position.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="position.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexPosition"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.positionid.title"/>:
						</td>
						<td>
							${position.positionId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.strategyname.title"/>:
						</td>
						<td>
							${position.strategyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.symbol.title"/>:
						</td>
						<td>
							${position.symbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.optright.title"/>:
						</td>
						<td>
							${position.optRight}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.expiryyear.title"/>:
						</td>
						<td>
							${position.expiryYear}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.expirymonth.title"/>:
						</td>
						<td>
							${position.expiryMonth}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.expiryday.title"/>:
						</td>
						<td>
							${position.expiryDay}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.expirytimeframe.title"/>:
						</td>
						<td>
							${position.expiryTimeframe}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.goalnumspreads.title"/>:
						</td>
						<td>
							${position.goalNumSpreads}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.numopenspreads.title"/>:
						</td>
						<td>
							${position.numOpenSpreads}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.numwins.title"/>:
						</td>
						<td>
							${position.numWins}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.numlosses.title"/>:
						</td>
						<td>
							${position.numLosses}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.profitlossunrealized.title"/>:
						</td>
						<td>
							${position.profitLossUnrealized}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.profitlossrealized.title"/>:
						</td>
						<td>
							${position.profitLossRealized}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.lastexitsecurityprice.title"/>:
						</td>
						<td>
							${position.lastExitSecurityPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.reentrysecpricecallabove.title"/>:
						</td>
						<td>
							${position.reentrySecPriceCallAbove}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.reentrysecpriceputbelow.title"/>:
						</td>
						<td>
							${position.reentrySecPricePutBelow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.totalrisked.title"/>:
						</td>
						<td>
							${position.totalRisked}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.createdby.title"/>:
						</td>
						<td>
							${position.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${position.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.updatedby.title"/>:
						</td>
						<td>
							${position.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="position.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${position.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="strategyaccount.title"/></h1>
					
						<c:if test='${position.strategyAccount != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="strategyaccount.strategyaccountid.title"/>:
						</td>
						<td>
							${position.strategyAccount.strategyAccountId}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/editPositionStrategyAccount?position_positionId=${position.positionId}&strategyaccount_strategyAccountId=${position.strategyAccount.strategyAccountId}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeletePositionStrategyAccount?position_positionId=${position.positionId}&strategyaccount_strategyAccountId=${position.strategyAccount.strategyAccountId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${position.strategyAccount == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newPositionStrategyAccount?position_positionId=${position.positionId}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategyaccount.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="spread.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newPositionSpreads?position_positionId=${position.positionId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="spread.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="spread.spreadid.title"/></th>
						<th class="thead"><fmt:message key="spread.openorclosed.title"/></th>
						<th class="thead"><fmt:message key="spread.moneymkrpositionid.title"/></th>
						<th class="thead"><fmt:message key="spread.insurancepositionid.title"/></th>
						<th class="thead"><fmt:message key="spread.strikemoneymkr.title"/></th>
						<th class="thead"><fmt:message key="spread.strikeinsurance.title"/></th>
						<th class="thead"><fmt:message key="spread.entermoneymkrprice.title"/></th>
						<th class="thead"><fmt:message key="spread.entermoneymkrdate.title"/></th>
						<th class="thead"><fmt:message key="spread.enterinsuranceprice.title"/></th>
						<th class="thead"><fmt:message key="spread.enterinsurancedate.title"/></th>
						<th class="thead"><fmt:message key="spread.entersecurityprice.title"/></th>
						<th class="thead"><fmt:message key="spread.entersecuritydate.title"/></th>
						<th class="thead"><fmt:message key="spread.entercommission.title"/></th>
						<th class="thead"><fmt:message key="spread.currentmoneymkrprice.title"/></th>
						<th class="thead"><fmt:message key="spread.currentinsuranceprice.title"/></th>
						<th class="thead"><fmt:message key="spread.currentvixprice.title"/></th>
						<th class="thead"><fmt:message key="spread.currentsecurityprice.title"/></th>
						<th class="thead"><fmt:message key="spread.currentdate.title"/></th>
						<th class="thead"><fmt:message key="spread.exitmoneymkrprice.title"/></th>
						<th class="thead"><fmt:message key="spread.exitmoneymkrdate.title"/></th>
						<th class="thead"><fmt:message key="spread.exitinsuranceprice.title"/></th>
						<th class="thead"><fmt:message key="spread.exitinsurancedate.title"/></th>
						<th class="thead"><fmt:message key="spread.exitsecurityprice.title"/></th>
						<th class="thead"><fmt:message key="spread.exitsecuritydate.title"/></th>
						<th class="thead"><fmt:message key="spread.exitcommission.title"/></th>
						<th class="thead"><fmt:message key="spread.trailingstopisset.title"/></th>
						<th class="thead"><fmt:message key="spread.exitabovespreadvalue.title"/></th>
						<th class="thead"><fmt:message key="spread.exitbelowspreadvalue.title"/></th>
						<th class="thead"><fmt:message key="spread.profitlossunrealized.title"/></th>
						<th class="thead"><fmt:message key="spread.profitlossrealized.title"/></th>
						<th class="thead"><fmt:message key="spread.createdby.title"/></th>
						<th class="thead"><fmt:message key="spread.createddate.title"/></th>
						<th class="thead"><fmt:message key="spread.updatedby.title"/></th>
						<th class="thead"><fmt:message key="spread.updateddate.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${position.spreads}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectPositionSpreads?position_positionId=${position.positionId}&spread_spreadId=${current.spreadId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editPositionSpreads?position_positionId=${position.positionId}&spread_spreadId=${current.spreadId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeletePositionSpreads?position_positionId=${position.positionId}&spread_spreadId=${current.spreadId}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.spreadId}
						&nbsp;
						</td>
						<td>
							${current.openOrClosed}
						&nbsp;
						</td>
						<td>
							${current.moneymkrPositionId}
						&nbsp;
						</td>
						<td>
							${current.insurancePositionId}
						&nbsp;
						</td>
						<td>
							${current.strikeMoneymkr}
						&nbsp;
						</td>
						<td>
							${current.strikeInsurance}
						&nbsp;
						</td>
						<td>
							${current.enterMoneymkrPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterMoneymkrDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.enterInsurancePrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterInsuranceDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.enterSecurityPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterSecurityDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.enterCommission}
						&nbsp;
						</td>
						<td>
							${current.currentMoneymkrPrice}
						&nbsp;
						</td>
						<td>
							${current.currentInsurancePrice}
						&nbsp;
						</td>
						<td>
							${current.currentVixPrice}
						&nbsp;
						</td>
						<td>
							${current.currentSecurityPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.currentDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.exitMoneymkrPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitMoneymkrDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.exitInsurancePrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitInsuranceDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.exitSecurityPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitSecurityDate.time}"/>
						&nbsp;
						</td>
						<td>
							${current.exitCommission}
						&nbsp;
						</td>
						<td>
							${current.trailingStopIsSet}
						&nbsp;
						</td>
						<td>
							${current.exitAboveSpreadValue}
						&nbsp;
						</td>
						<td>
							${current.exitBelowSpreadValue}
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