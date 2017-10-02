<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="Tasting Suggestion"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container mb-5">
	<div class="row justify-content-center mt-4">
		<h2>${title}</h2>
	</div>
	
	<div class="row justify-content-center align-items-center mt-3 mb-5">
		<div class="col-sm-8 col-md-4 text-center">
			<img class="img-fluid img-blur"
				 src="img/duvel.png">
		 </div>
		<div class="col-sm-8 col-md-4 text-center mt-3 mt-md-0">
			<h4>Duvel</h4>
			<p class="text-justify">
				A Duvel is still seen as the reference among strong golden ales. Its bouquet is lively and tickles the nose with an element of citrus which even tends towards grapefruit thanks to the use of only the highest-quality hop varieties.
			</p>
			<a href="#" class="btn btn-primary">More Information</a>
		</div>
		</div>
	</div>
	
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>