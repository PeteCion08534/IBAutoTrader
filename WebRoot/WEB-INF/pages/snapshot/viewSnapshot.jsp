<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshot-resources"/>
<html>
<head>
<title>View <fmt:message key="snapshot.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="snapshot.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexSnapshot"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
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
			<div class="clear">&nbsp;</div>
			<div class="spacer">&nbsp;</div>
			<h1><fmt:message key="snapshotoption.title"/></h1>
					
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSnapshotSnapshotOptions?snapshot_snapshotId=${snapshot.snapshotId}&"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="snapshotoption.title"/></span></a></div>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead">&nbsp;</th>
						<th class="thead"><fmt:message key="snapshotoption.snapshotoptionid.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.optright.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.localsymbol.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.strike.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.bidprice.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.askprice.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.lastprice.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.tradingtimeopentoday.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.tradingtimeclosetoday.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.createdby.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.createddate.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.updatedby.title"/></th>
						<th class="thead"><fmt:message key="snapshotoption.updateddate.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${snapshot.snapshotOptions}" var="current"  varStatus="i">	
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
							<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSnapshotSnapshotOptions?snapshot_snapshotId=${snapshot.snapshotId}&snapshotoption_snapshotOptionId=${current.snapshotOptionId}&"><img src="images/icons/view.gif" /></a>
							<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSnapshotSnapshotOptions?snapshot_snapshotId=${snapshot.snapshotId}&snapshotoption_snapshotOptionId=${current.snapshotOptionId}&"><img src="images/icons/edit.gif" /></a>
							<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSnapshotSnapshotOptions?snapshot_snapshotId=${snapshot.snapshotId}&snapshotoption_snapshotOptionId=${current.snapshotOptionId}&"><img src="images/icons/delete.gif" /></a>
						</td>
						<td>
							${current.snapshotOptionId}
						&nbsp;
						</td>
						<td>
							${current.optRight}
						&nbsp;
						</td>
						<td>
							${current.localSymbol}
						&nbsp;
						</td>
						<td>
							${current.strike}
						&nbsp;
						</td>
						<td>
							${current.bidPrice}
						&nbsp;
						</td>
						<td>
							${current.askPrice}
						&nbsp;
						</td>
						<td>
							${current.lastPrice}
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.tradingTimeOpenToday.time}"/>
						&nbsp;
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${current.tradingTimeCloseToday.time}"/>
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