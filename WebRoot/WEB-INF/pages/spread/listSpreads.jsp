<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.spread-resources"/>
<html>
<head>
<title>List <fmt:message key="spread.title"/>s</title>
</head>
<body>
<div id="contentarea" >
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
	<div id="content">
		<h1>Manage <fmt:message key="spread.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newSpread"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="spread.title"/></span></a></div>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="spread.spreadid.title"/></th>
					<th class="thead"><fmt:message key="spread.openorclosed.title"/></th>
					<th class="thead"><fmt:message key="spread.moneymkrpositionid.title"/></th>
					<th class="thead"><fmt:message key="spread.insurancepositionid.title"/></th>
					<th class="thead"><fmt:message key="spread.strikemoneymkr.title"/></th>
					<th class="thead"><fmt:message key="spread.strikeinsurance.title"/></th>
					<th class="thead"><fmt:message key="spread.entermoneymkrprice.title"/></th>
					<th class="thead"><fmt:message key="spread.entermoneymkrdate.title"/></th>
					<th class="thead"><fmt:message key="spread.enterinsuranceprice.title"/></th>
					<th class="thead"><fmt:message key="spread.enterinsurancedate.title"/></th>
					<th class="thead"><fmt:message key="spread.entersecurityprice.title"/></th>
					<th class="thead"><fmt:message key="spread.entersecuritydate.title"/></th>
					<th class="thead"><fmt:message key="spread.entercommission.title"/></th>
					<th class="thead"><fmt:message key="spread.currentmoneymkrprice.title"/></th>
					<th class="thead"><fmt:message key="spread.currentinsuranceprice.title"/></th>
					<th class="thead"><fmt:message key="spread.currentvixprice.title"/></th>
					<th class="thead"><fmt:message key="spread.currentsecurityprice.title"/></th>
					<th class="thead"><fmt:message key="spread.currentdate.title"/></th>
					<th class="thead"><fmt:message key="spread.exitmoneymkrprice.title"/></th>
					<th class="thead"><fmt:message key="spread.exitmoneymkrdate.title"/></th>
					<th class="thead"><fmt:message key="spread.exitinsuranceprice.title"/></th>
					<th class="thead"><fmt:message key="spread.exitinsurancedate.title"/></th>
					<th class="thead"><fmt:message key="spread.exitsecurityprice.title"/></th>
					<th class="thead"><fmt:message key="spread.exitsecuritydate.title"/></th>
					<th class="thead"><fmt:message key="spread.exitcommission.title"/></th>
					<th class="thead"><fmt:message key="spread.trailingstopisset.title"/></th>
					<th class="thead"><fmt:message key="spread.exitabovespreadvalue.title"/></th>
					<th class="thead"><fmt:message key="spread.exitbelowspreadvalue.title"/></th>
					<th class="thead"><fmt:message key="spread.profitlossunrealized.title"/></th>
					<th class="thead"><fmt:message key="spread.profitlossrealized.title"/></th>
					<th class="thead"><fmt:message key="spread.createdby.title"/></th>
					<th class="thead"><fmt:message key="spread.createddate.title"/></th>
					<th class="thead"><fmt:message key="spread.updatedby.title"/></th>
					<th class="thead"><fmt:message key="spread.updateddate.title"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${spreads}" var="current" varStatus="i">
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
						<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectSpread?spreadIdKey=${current.spreadId}&"><img src="images/icons/view.gif" /></a>
						<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editSpread?spreadIdKey=${current.spreadId}&"><img src="images/icons/edit.gif" /></a>
						<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteSpread?spreadIdKey=${current.spreadId}&"><img src="images/icons/delete.gif" /></a>
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.spreadId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.openOrClosed}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.moneymkrPositionId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.insurancePositionId}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strikeMoneymkr}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.strikeInsurance}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enterMoneymkrPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterMoneymkrDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enterInsurancePrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterInsuranceDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enterSecurityPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.enterSecurityDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.enterCommission}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.currentMoneymkrPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.currentInsurancePrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.currentVixPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.currentSecurityPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.currentDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitMoneymkrPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitMoneymkrDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitInsurancePrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitInsuranceDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitSecurityPrice}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							<fmt:formatDate dateStyle="short" type="date" value="${current.exitSecurityDate.time}"/>
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitCommission}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.trailingStopIsSet}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitAboveSpreadValue}
						&nbsp;
					</td>
					<td nowrap="nowrap" class="tabletd">
						
							${current.exitBelowSpreadValue}
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