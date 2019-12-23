<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.position-resources"/>
<html>
<head>
<title>List <fmt:message key="position.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="position.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newPosition"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="position.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="position.positionid.title"/></th>
					<th class="thead"><fmt:message key="position.strategyname.title"/></th>
					<th class="thead"><fmt:message key="position.symbol.title"/></th>
					<th class="thead"><fmt:message key="position.optright.title"/></th>
					<th class="thead"><fmt:message key="position.expiryyear.title"/></th>
					<th class="thead"><fmt:message key="position.expirymonth.title"/></th>
					<th class="thead"><fmt:message key="position.expiryday.title"/></th>
					<th class="thead"><fmt:message key="position.expirytimeframe.title"/></th>
					<th class="thead"><fmt:message key="position.goalnumspreads.title"/></th>
					<th class="thead"><fmt:message key="position.numopenspreads.title"/></th>
					<th class="thead"><fmt:message key="position.numwins.title"/></th>
					<th class="thead"><fmt:message key="position.numlosses.title"/></th>
					<th class="thead"><fmt:message key="position.profitlossunrealized.title"/></th>
					<th class="thead"><fmt:message key="position.profitlossrealized.title"/></th>
					<th class="thead"><fmt:message key="position.lastexitsecurityprice.title"/></th>
					<th class="thead"><fmt:message key="position.reentrysecpricecallabove.title"/></th>
					<th class="thead"><fmt:message key="position.reentrysecpriceputbelow.title"/></th>
					<th class="thead"><fmt:message key="position.totalrisked.title"/></th>
					<th class="thead"><fmt:message key="position.createdby.title"/></th>
					<th class="thead"><fmt:message key="position.createddate.title"/></th>
					<th class="thead"><fmt:message key="position.updatedby.title"/></th>
					<th class="thead"><fmt:message key="position.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${positions}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectPosition?positionIdKey=${current.positionId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editPosition?positionIdKey=${current.positionId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeletePosition?positionIdKey=${current.positionId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.positionId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strategyName}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.symbol}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.optRight}
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
						
							${current.expiryDay}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.expiryTimeframe}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.goalNumSpreads}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.numOpenSpreads}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.numWins}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.numLosses}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.profitLossUnrealized}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.profitLossRealized}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.lastExitSecurityPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.reentrySecPriceCallAbove}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.reentrySecPricePutBelow}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.totalRisked}
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