<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="BeerSafe"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2 class="col-12 text-center">Login</h2>
	</div>
	
	<c:if test="${error.length() > 0}">
		<div class="row justify-content-center mt-4">
			<div class="alert alert-danger">
				<p class="m-0">${error}</p>
			</div>
		</div>
	</c:if>
	
	<div class="row justify-content-center mt-4 mb-2">
	    <form method="POST"">
	        <div class="input-group row mt-2">
	            <div class="input-group-addon">
	                <i class="fa fa-envelope-o fa-fw"></i>
	            </div>
	            <input type="text" class="form-control" size="40" name="email" value="info@beersafe.eu">
	        </div>
	        <div class="input-group row mt-2">
	            <div class="input-group-addon">
	                <i class="fa fa-key fa-fw"></i>
	            </div>
	            <input type="password" class="form-control" size="40" name="password" value="test1234">
	        </div>
	        <div class="mt-2 text-center">
	            <input type="submit" class="btn btn-primary" value="Login">
	        </div>
	    </form>
	</div>
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>