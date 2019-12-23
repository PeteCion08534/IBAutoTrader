<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.holiday-resources"/>
<html>
<head>
<title>List <fmt:message key="holiday.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="holiday.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newHoliday"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="holiday.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="holiday.holidayid.title"/></th>
					<th class="thead"><fmt:message key="holiday.holidayname.title"/></th>
					<th class="thead"><fmt:message key="holiday.holidayyear.title"/></th>
					<th class="thead"><fmt:message key="holiday.holidaydate.title"/></th>
					<th class="thead"><fmt:message key="holiday.createdby.title"/></th>
					<th class="thead"><fmt:message key="holiday.createddate.title"/></th>
					<th class="thead"><fmt:message key="holiday.updatedby.title"/></th>
					<th class="thead"><fmt:message key="holiday.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${holidays}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectHoliday?holidayIdKey=${current.holidayId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editHoliday?holidayIdKey=${current.holidayId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteHoliday?holidayIdKey=${current.holidayId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.holidayId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.holidayName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.holidayYear}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.holidayDate.time}"/>
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