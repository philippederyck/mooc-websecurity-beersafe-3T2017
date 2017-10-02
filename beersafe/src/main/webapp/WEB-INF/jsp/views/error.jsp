<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="BeerSafe"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2 class="col-12 text-center">BeerSafe is broken</h2>
	</div>
	
	<div class="row justify-content-center mt-4">
		<div class="alert alert-danger">
			<p class="m-0">${message}</p>
		</div>
	</div>
	
	<div class="row justify-content-center mt-4">
		<c:if test="${ exception != null}">
			<h3 class="col-12">Stacktrace</h3>
			<pre class="small">
				${pageContext.out.flush()}${exception.printStackTrace(pageContext.response.writer)}
			</pre>
		</c:if>
	</div>	
	
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>

