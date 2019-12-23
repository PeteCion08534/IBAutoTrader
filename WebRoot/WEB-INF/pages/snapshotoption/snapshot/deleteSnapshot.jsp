<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshotoption-resources"/>
<html>
<head>
<title>View <fmt:message key="snapshotoption.title"/> <fmt:message key="snapshot.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="navigation.view"/> <fmt:message key="snapshot.title"/></h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/selectSnapshotOption?snapshotOptionIdKey=${snapshotoption_snapshotOptionId}&"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.snapshotid.title"/>:
						</td>
						<td>
							${snapshot.snapshotId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.snapshotdate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshot.snapshotDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.symbol.title"/>:
						</td>
						<td>
							${snapshot.symbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.symbolprice.title"/>:
						</td>
						<td>
							${snapshot.symbolPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.expiryyear.title"/>:
						</td>
						<td>
							${snapshot.expiryYear}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.expirymonth.title"/>:
						</td>
						<td>
							${snapshot.expiryMonth}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.expirtyday.title"/>:
						</td>
						<td>
							${snapshot.expirtyDay}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.createdby.title"/>:
						</td>
						<td>
							${snapshot.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshot.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.updatedby.title"/>:
						</td>
						<td>
							${snapshot.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshot.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshot.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteSnapshotOptionSnapshot?snapshotoption_snapshotOptionId=${snapshotoption_snapshotOptionId}&snapshot_snapshotId=${snapshot.snapshotId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>
