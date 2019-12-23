<%@page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.holiday-resources"/>
<html>
<head>
<title>View <fmt:message key="holiday.title"/></title>
</head>
<body>
<div id="contentarea">      
	<div id="lb"><div id="rb"><div id="bb"><div id="blc">
	<div id="brc"><div id="tb"><div id="tlc"><div id="trc">
		<div id="content">
			<h1><fmt:message key="holiday.title"/> Details</h1>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexHoliday"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.holidayid.title"/>:
						</td>
						<td>
							${holiday.holidayId}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.holidayname.title"/>:
						</td>
						<td>
							${holiday.holidayName}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.holidayyear.title"/>:
						</td>
						<td>
							${holiday.holidayYear}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.holidaydate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${holiday.holidayDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.createdby.title"/>:
						</td>
						<td>
							${holiday.createdBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.createddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${holiday.createdDate.time}"/>
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.updatedby.title"/>:
						</td>
						<td>
							${holiday.updatedBy}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td class="label" valign="top">
							<fmt:message key="holiday.updateddate.title"/>:
						</td>
						<td>
							<fmt:formatDate dateStyle="short" type="date" value="${holiday.updatedDate.time}"/>
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteHoliday?holidayIdKey=${holiday.holidayId}&"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
			<div class="clear">&nbsp;</div>
		</div>
	</div></div></div></div>
	</div></div></div></div>
</div>
</body>
</html>