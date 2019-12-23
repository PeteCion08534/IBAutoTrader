<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.snapshotoption-resources"/>
<html>
<head>
<title>List <fmt:message key="snapshotoption.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="snapshotoption.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSnapshotOption"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="snapshotoption.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
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
				<c:forEach items="${snapshotoptions}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSnapshotOption?snapshotOptionIdKey=${current.snapshotOptionId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSnapshotOption?snapshotOptionIdKey=${current.snapshotOptionId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSnapshotOption?snapshotOptionIdKey=${current.snapshotOptionId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.snapshotOptionId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.optRight}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.localSymbol}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strike}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.bidPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.askPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.lastPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.tradingTimeOpenToday.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.tradingTimeCloseToday.time}"/>
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