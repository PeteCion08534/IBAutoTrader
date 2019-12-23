<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.strategyaccount-resources"/>
<html>
<head>
<title>View <fmt:message key="strategyaccount.title"/> <fmt:message key="ibaccount.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="ibaccount.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectStrategyAccount?strategyAccountIdKey=${strategyaccount_strategyAccountId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.ibaccountid.title"/>:
						</td>
						<td>
							${ibaccount.ibAccountId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.ibaccountidext.title"/>:
						</td>
						<td>
							${ibaccount.ibAccountIdExt}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.ipaddress.title"/>:
						</td>
						<td>
							${ibaccount.ipAddress}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.port.title"/>:
						</td>
						<td>
							${ibaccount.port}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.clientid.title"/>:
						</td>
						<td>
							${ibaccount.clientId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.createdby.title"/>:
						</td>
						<td>
							${ibaccount.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${ibaccount.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.updatedby.title"/>:
						</td>
						<td>
							${ibaccount.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="ibaccount.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${ibaccount.updatedDate.time}"/>
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
