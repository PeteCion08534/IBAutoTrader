<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategy-resources"/>
<html>
<head>
<title>View <fmt:message key="strategy.title"/> <fmt:message key="profitloss.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="profitloss.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectStrategy?strategyIdKey=${strategy_strategyId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
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
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
