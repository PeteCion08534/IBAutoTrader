<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.profitloss-resources"/>
<html>
<head>
<title>View <fmt:message key="profitloss.title"/> <fmt:message key="strategy.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="strategy.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectProfitLoss?profitLossIdKey=${profitloss_profitLossId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
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
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
