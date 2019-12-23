<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshot-resources"/>
<html>
<head>
<title>List <fmt:message key="snapshot.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="snapshot.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSnapshot"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="snapshot.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="snapshot.snapshotid.title"/></th>
					<th class="thead"><fmt:message key="snapshot.snapshotdate.title"/></th>
					<th class="thead"><fmt:message key="snapshot.symbol.title"/></th>
					<th class="thead"><fmt:message key="snapshot.symbolprice.title"/></th>
					<th class="thead"><fmt:message key="snapshot.expiryyear.title"/></th>
					<th class="thead"><fmt:message key="snapshot.expirymonth.title"/></th>
					<th class="thead"><fmt:message key="snapshot.expirtyday.title"/></th>
					<th class="thead"><fmt:message key="snapshot.createdby.title"/></th>
					<th class="thead"><fmt:message key="snapshot.createddate.title"/></th>
					<th class="thead"><fmt:message key="snapshot.updatedby.title"/></th>
					<th class="thead"><fmt:message key="snapshot.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${snapshots}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSnapshot?snapshotIdKey=${current.snapshotId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSnapshot?snapshotIdKey=${current.snapshotId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSnapshot?snapshotIdKey=${current.snapshotId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.snapshotId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.snapshotDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.symbol}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.symbolPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.expiryYear}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.expiryMonth}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.expirtyDay}
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