<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<dl class="memas" style="height: 330px">
	<% int i=0; %>
	<c:forEach items="${memas}" var="mema">
		<dt><span class="num"><%= ++i %>.</span>${mema.title}</dt>
		<dd>${mema.description}</dd>
	</c:forEach>
</dl>