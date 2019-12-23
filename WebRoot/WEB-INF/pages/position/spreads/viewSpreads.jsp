<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.position-resources"/>
<html>
<head>
<title>View <fmt:message key="position.title"/> <fmt:message key="spread.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="spread.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectPosition?positionIdKey=${position_positionId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.spreadid.title"/>:
						</td>
						<td>
							${spread.spreadId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.openorclosed.title"/>:
						</td>
						<td>
							${spread.openOrClosed}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.moneymkrpositionid.title"/>:
						</td>
						<td>
							${spread.moneymkrPositionId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.insurancepositionid.title"/>:
						</td>
						<td>
							${spread.insurancePositionId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.strikemoneymkr.title"/>:
						</td>
						<td>
							${spread.strikeMoneymkr}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.strikeinsurance.title"/>:
						</td>
						<td>
							${spread.strikeInsurance}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entermoneymkrprice.title"/>:
						</td>
						<td>
							${spread.enterMoneymkrPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entermoneymkrdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.enterMoneymkrDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.enterinsuranceprice.title"/>:
						</td>
						<td>
							${spread.enterInsurancePrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.enterinsurancedate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.enterInsuranceDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entersecurityprice.title"/>:
						</td>
						<td>
							${spread.enterSecurityPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entersecuritydate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.enterSecurityDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.entercommission.title"/>:
						</td>
						<td>
							${spread.enterCommission}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentmoneymkrprice.title"/>:
						</td>
						<td>
							${spread.currentMoneymkrPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentinsuranceprice.title"/>:
						</td>
						<td>
							${spread.currentInsurancePrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentvixprice.title"/>:
						</td>
						<td>
							${spread.currentVixPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentsecurityprice.title"/>:
						</td>
						<td>
							${spread.currentSecurityPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.currentdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.currentDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitmoneymkrprice.title"/>:
						</td>
						<td>
							${spread.exitMoneymkrPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitmoneymkrdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.exitMoneymkrDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitinsuranceprice.title"/>:
						</td>
						<td>
							${spread.exitInsurancePrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitinsurancedate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.exitInsuranceDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitsecurityprice.title"/>:
						</td>
						<td>
							${spread.exitSecurityPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitsecuritydate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.exitSecurityDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitcommission.title"/>:
						</td>
						<td>
							${spread.exitCommission}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.trailingstopisset.title"/>:
						</td>
						<td>
							${spread.trailingStopIsSet}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitabovespreadvalue.title"/>:
						</td>
						<td>
							${spread.exitAboveSpreadValue}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.exitbelowspreadvalue.title"/>:
						</td>
						<td>
							${spread.exitBelowSpreadValue}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.profitlossunrealized.title"/>:
						</td>
						<td>
							${spread.profitLossUnrealized}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.profitlossrealized.title"/>:
						</td>
						<td>
							${spread.profitLossRealized}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.createdby.title"/>:
						</td>
						<td>
							${spread.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.updatedby.title"/>:
						</td>
						<td>
							${spread.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="spread.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${spread.updatedDate.time}"/>
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
