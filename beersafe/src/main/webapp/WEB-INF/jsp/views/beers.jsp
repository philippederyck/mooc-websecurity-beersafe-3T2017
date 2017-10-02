<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="List of Beers"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2>${title}</h2>
	</div>
	
	<div class="row justify-content-center mt-4">
		<form class="form-inline" action="Beers">
			<label class="sr-only" for="searchField">Search</label>
			<div class="input-group mb-2 mr-sm-2 mb-sm-0">
				<div class="input-group-addon">
					<i class="fa fa-search"></i>
				</div>
				<input type="text" class="form-control" id="searchField" name="search" size="50" 
					placeholder="Enter search term" value="${query}">
			</div>
			<button type="submit" class="btn btn-primary">Search</button>
		</form>
	</div>
	
	<div class="row justify-content-center mt-5">
		<c:if test="${beers.size() == 0}">
			<p class="text-center">No beers found.</p>
		</c:if>

		<c:forEach var="b" items="${beers}">
			<div class="card beercard m-3">
				<img class="card-img-top" src="img/${b.image}">
				<div class="card-block">
					<h5 class="text-center">${b.name}</h5>
					<div>Category: <i>${b.category}</i></div>
					<div>Color: <i>${b.color}</i></div>
					<div>Alcohol: <i>${b.alcohol}%</i></div>
				</div>
				<div class="card-block row justify-content-center">
					<a href="/Beers?id=${b.id}" class="btn btn-outline-primary btn-text-l">
						<i class="fa fa-info-circle"></i>
					</a>
				</div>
			</div>
		</c:forEach>
		
	</div>
	
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>