<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.ibaccount-resources"/>
<html>
<head>
<title>View <fmt:message key="ibaccount.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="ibaccount.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexIbAccount"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
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
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="strategyaccount.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newIbAccountStrategyAccounts?ibaccount_ibAccountId=${ibaccount.ibAccountId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="strategyaccount.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="strategyaccount.strategyaccountid.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ibaccount.strategyAccounts}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectIbAccountStrategyAccounts?ibaccount_ibAccountId=${ibaccount.ibAccountId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editIbAccountStrategyAccounts?ibaccount_ibAccountId=${ibaccount.ibAccountId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteIbAccountStrategyAccounts?ibaccount_ibAccountId=${ibaccount.ibAccountId}&strategyaccount_strategyAccountId=${current.strategyAccountId}&"><img src="images/icons/delete.gif" /></a>
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
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>