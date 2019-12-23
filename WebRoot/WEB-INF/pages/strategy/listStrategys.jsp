<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategy-resources"/>
<html>
<head>
<title>List <fmt:message key="strategy.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="strategy.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newStrategy"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategy.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="strategy.strategyid.title"/></th>
					<th class="thead"><fmt:message key="strategy.strategyname.title"/></th>
					<th class="thead"><fmt:message key="strategy.symbol.title"/></th>
					<th class="thead"><fmt:message key="strategy.activeflag.title"/></th>
					<th class="thead"><fmt:message key="strategy.initnumspreadscall.title"/></th>
					<th class="thead"><fmt:message key="strategy.initnumspreadsput.title"/></th>
					<th class="thead"><fmt:message key="strategy.percententerlow.title"/></th>
					<th class="thead"><fmt:message key="strategy.percententerhigh.title"/></th>
					<th class="thead"><fmt:message key="strategy.daysenterlow.title"/></th>
					<th class="thead"><fmt:message key="strategy.daysenterhigh.title"/></th>
					<th class="thead"><fmt:message key="strategy.amounttotalrisk.title"/></th>
					<th class="thead"><fmt:message key="strategy.amountriskperpoint.title"/></th>
					<th class="thead"><fmt:message key="strategy.distancebetoptions.title"/></th>
					<th class="thead"><fmt:message key="strategy.optpricestoget.title"/></th>
					<th class="thead"><fmt:message key="strategy.pctsettrailingstop.title"/></th>
					<th class="thead"><fmt:message key="strategy.pcttrailingstop.title"/></th>
					<th class="thead"><fmt:message key="strategy.pctexitwin.title"/></th>
					<th class="thead"><fmt:message key="strategy.setwintrailingstop.title"/></th>
					<th class="thead"><fmt:message key="strategy.setexitatinsprice.title"/></th>
					<th class="thead"><fmt:message key="strategy.setreenteronwin.title"/></th>
					<th class="thead"><fmt:message key="strategy.setreenteronloss.title"/></th>
					<th class="thead"><fmt:message key="strategy.pointdiffforreentry.title"/></th>
					<th class="thead"><fmt:message key="strategy.daysexitbeforeexpiry.title"/></th>
					<th class="thead"><fmt:message key="strategy.setbreakevenwheninshit.title"/></th>
					<th class="thead"><fmt:message key="strategy.mswaitforibresponse.title"/></th>
					<th class="thead"><fmt:message key="strategy.mindelayfromstart.title"/></th>
					<th class="thead"><fmt:message key="strategy.setcheckvix.title"/></th>
					<th class="thead"><fmt:message key="strategy.noentryifvixover.title"/></th>
					<th class="thead"><fmt:message key="strategy.timezone.title"/></th>
					<th class="thead"><fmt:message key="strategy.liveortestflag.title"/></th>
					<th class="thead"><fmt:message key="strategy.comissionpertrade.title"/></th>
					<th class="thead"><fmt:message key="strategy.createdby.title"/></th>
					<th class="thead"><fmt:message key="strategy.createddate.title"/></th>
					<th class="thead"><fmt:message key="strategy.updatedby.title"/></th>
					<th class="thead"><fmt:message key="strategy.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${strategys}" var="current" varStatus="i">
					<c:choose>
						<c:when test="${(i.count) % 2 == 0}">
		    				<c:set var="rowclass" value="rowtwo"/>
						</c:when>
						<c:otherwise>
		    				<c:set var="rowclass" value="rowone"/>
						</c:otherwise>
					</c:choose>	
				<tr class="${rowclass}">
					<td nowrap="nowrap" class="tabletd">
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectStrategy?strategyIdKey=${current.strategyId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editStrategy?strategyIdKey=${current.strategyId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteStrategy?strategyIdKey=${current.strategyId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strategyId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strategyName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.symbol}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.activeFlag}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.initNumSpreadsCall}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.initNumSpreadsPut}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.percentEnterLow}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.percentEnterHigh}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.daysEnterLow}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.daysEnterHigh}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.amountTotalRisk}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.amountRiskPerPoint}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.distanceBetOptions}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.optPricesToGet}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.pctSetTrailingStop}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.pctTrailingStop}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.pctExitWin}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setWinTrailingStop}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setExitAtInsPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setReenterOnWin}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setReenterOnLoss}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.pointDiffForReentry}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.daysExitBeforeExpiry}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setBreakevenWhenInsHit}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.msWaitForIbResponse}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.minDelayFromStart}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.setCheckVix}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.noEntryIfVixOver}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.timezone}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.liveOrTestFlag}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.comissionPerTrade}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.createdBy}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.createdDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.updatedBy}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.updatedDate.time}"/>
						&nbsp;
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>