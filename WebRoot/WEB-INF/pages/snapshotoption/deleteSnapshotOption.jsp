<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshotoption-resources"/>
<html>
<head>
<title>View <fmt:message key="snapshotoption.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="snapshotoption.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexSnapshotOption"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.snapshotoptionid.title"/>:
						</td>
						<td>
							${snapshotoption.snapshotOptionId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.optright.title"/>:
						</td>
						<td>
							${snapshotoption.optRight}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.localsymbol.title"/>:
						</td>
						<td>
							${snapshotoption.localSymbol}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.strike.title"/>:
						</td>
						<td>
							${snapshotoption.strike}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.bidprice.title"/>:
						</td>
						<td>
							${snapshotoption.bidPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.askprice.title"/>:
						</td>
						<td>
							${snapshotoption.askPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.lastprice.title"/>:
						</td>
						<td>
							${snapshotoption.lastPrice}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.tradingtimeopentoday.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshotoption.tradingTimeOpenToday.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.tradingtimeclosetoday.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshotoption.tradingTimeCloseToday.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.createdby.title"/>:
						</td>
						<td>
							${snapshotoption.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshotoption.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.updatedby.title"/>:
						</td>
						<td>
							${snapshotoption.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="snapshotoption.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${snapshotoption.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteSnapshotOption?snapshotOptionIdKey=${snapshotoption.snapshotOptionId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>