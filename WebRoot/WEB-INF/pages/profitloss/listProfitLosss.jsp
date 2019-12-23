<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.profitloss-resources"/>
<html>
<head>
<title>List <fmt:message key="profitloss.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="profitloss.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newProfitLoss"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="profitloss.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="profitloss.profitlossid.title"/></th>
					<th class="thead"><fmt:message key="profitloss.profitlossstartdate.title"/></th>
					<th class="thead"><fmt:message key="profitloss.profitlossdate.title"/></th>
					<th class="thead"><fmt:message key="profitloss.profitorloss.title"/></th>
					<th class="thead"><fmt:message key="profitloss.profitorlossamt.title"/></th>
					<th class="thead"><fmt:message key="profitloss.createdby.title"/></th>
					<th class="thead"><fmt:message key="profitloss.createddate.title"/></th>
					<th class="thead"><fmt:message key="profitloss.updatedby.title"/></th>
					<th class="thead"><fmt:message key="profitloss.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${profitlosss}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectProfitLoss?profitLossIdKey=${current.profitLossId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editProfitLoss?profitLossIdKey=${current.profitLossId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteProfitLoss?profitLossIdKey=${current.profitLossId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.profitLossId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.profitLossStartDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.profitLossDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.profitOrLoss}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.profitOrLossAmt}
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