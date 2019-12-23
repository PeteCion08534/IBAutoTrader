<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.heartbeat-resources"/>
<html>
<head>
<title>List <fmt:message key="heartbeat.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="heartbeat.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newHeartbeat"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="heartbeat.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="heartbeat.heartbeatid.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.heartbeatdate.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.inprocess.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.threadid.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.terminationstatus.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.heartbeatlog.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.createdby.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.createddate.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.updatedby.title"/></th>
					<th class="thead"><fmt:message key="heartbeat.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${heartbeats}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectHeartbeat?heartbeatIdKey=${current.heartbeatId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editHeartbeat?heartbeatIdKey=${current.heartbeatId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteHeartbeat?heartbeatIdKey=${current.heartbeatId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.heartbeatId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.heartbeatDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.inProcess}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.threadId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.terminationStatus}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.heartbeatLog}
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