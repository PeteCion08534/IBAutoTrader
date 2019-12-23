<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.profitloss-resources"/>
<html>
<head>
<title>View <fmt:message key="profitloss.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="profitloss.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexProfitLoss"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.profitlossid.title"/>:
						</td>
						<td>
							${profitloss.profitLossId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.profitlossstartdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.profitLossStartDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.profitlossdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.profitLossDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.profitorloss.title"/>:
						</td>
						<td>
							${profitloss.profitOrLoss}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.profitorlossamt.title"/>:
						</td>
						<td>
							${profitloss.profitOrLossAmt}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.createdby.title"/>:
						</td>
						<td>
							${profitloss.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.updatedby.title"/>:
						</td>
						<td>
							${profitloss.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="profitloss.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="strategy.title"/></h1>
					
						<c:if test='${profitloss.strategy != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.strategyid.title"/>:
						</td>
						<td>
							${profitloss.strategy.strategyId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.strategyname.title"/>:
						</td>
						<td>
							${profitloss.strategy.strategyName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.symbol.title"/>:
						</td>
						<td>
							${profitloss.strategy.symbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.activeflag.title"/>:
						</td>
						<td>
							${profitloss.strategy.activeFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.initnumspreadscall.title"/>:
						</td>
						<td>
							${profitloss.strategy.initNumSpreadsCall}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.initnumspreadsput.title"/>:
						</td>
						<td>
							${profitloss.strategy.initNumSpreadsPut}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.percententerlow.title"/>:
						</td>
						<td>
							${profitloss.strategy.percentEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.percententerhigh.title"/>:
						</td>
						<td>
							${profitloss.strategy.percentEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysenterlow.title"/>:
						</td>
						<td>
							${profitloss.strategy.daysEnterLow}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysenterhigh.title"/>:
						</td>
						<td>
							${profitloss.strategy.daysEnterHigh}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.amounttotalrisk.title"/>:
						</td>
						<td>
							${profitloss.strategy.amountTotalRisk}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.amountriskperpoint.title"/>:
						</td>
						<td>
							${profitloss.strategy.amountRiskPerPoint}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.distancebetoptions.title"/>:
						</td>
						<td>
							${profitloss.strategy.distanceBetOptions}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.optpricestoget.title"/>:
						</td>
						<td>
							${profitloss.strategy.optPricesToGet}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pctsettrailingstop.title"/>:
						</td>
						<td>
							${profitloss.strategy.pctSetTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pcttrailingstop.title"/>:
						</td>
						<td>
							${profitloss.strategy.pctTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pctexitwin.title"/>:
						</td>
						<td>
							${profitloss.strategy.pctExitWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setwintrailingstop.title"/>:
						</td>
						<td>
							${profitloss.strategy.setWinTrailingStop}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setexitatinsprice.title"/>:
						</td>
						<td>
							${profitloss.strategy.setExitAtInsPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setreenteronwin.title"/>:
						</td>
						<td>
							${profitloss.strategy.setReenterOnWin}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setreenteronloss.title"/>:
						</td>
						<td>
							${profitloss.strategy.setReenterOnLoss}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.pointdiffforreentry.title"/>:
						</td>
						<td>
							${profitloss.strategy.pointDiffForReentry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.daysexitbeforeexpiry.title"/>:
						</td>
						<td>
							${profitloss.strategy.daysExitBeforeExpiry}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setbreakevenwheninshit.title"/>:
						</td>
						<td>
							${profitloss.strategy.setBreakevenWhenInsHit}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.mswaitforibresponse.title"/>:
						</td>
						<td>
							${profitloss.strategy.msWaitForIbResponse}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.mindelayfromstart.title"/>:
						</td>
						<td>
							${profitloss.strategy.minDelayFromStart}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.setcheckvix.title"/>:
						</td>
						<td>
							${profitloss.strategy.setCheckVix}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.noentryifvixover.title"/>:
						</td>
						<td>
							${profitloss.strategy.noEntryIfVixOver}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.timezone.title"/>:
						</td>
						<td>
							${profitloss.strategy.timezone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.liveortestflag.title"/>:
						</td>
						<td>
							${profitloss.strategy.liveOrTestFlag}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.comissionpertrade.title"/>:
						</td>
						<td>
							${profitloss.strategy.comissionPerTrade}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.createdby.title"/>:
						</td>
						<td>
							${profitloss.strategy.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.strategy.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.updatedby.title"/>:
						</td>
						<td>
							${profitloss.strategy.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="strategy.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${profitloss.strategy.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/editProfitLossStrategy?profitloss_profitLossId=${profitloss.profitLossId}&strategy_strategyId=${profitloss.strategy.strategyId}&"><span><img src="images/icons/edit.gif" /><fmt:message key="navigation.edit"/></span></a></div>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/confirmDeleteProfitLossStrategy?profitloss_profitLossId=${profitloss.profitLossId}&strategy_strategyId=${profitloss.strategy.strategyId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
						</c:if>
						<c:if test='${profitloss.strategy == null}'>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newProfitLossStrategy?profitloss_profitLossId=${profitloss.profitLossId}&"><span><img src="images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategy.title"/></span></a></div>
						</c:if>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>