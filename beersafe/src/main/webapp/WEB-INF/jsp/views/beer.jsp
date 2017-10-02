<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="List of Beers"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center align-items-center mt-3">
	    <h2>${beer.name}</h2>
	</div>
	<div class="row justify-content-center align-items-center mt-3 mb-3">
	    <div class="col-sm-4 col-md-4 col-lg-3 text-center p-4">
	        <img class="img-fluid img-blur" src="img/${beer.image}">
	        <p>
	            <div class="text-left">Category: <i>${beer.category}</i></div>
	            <div class="text-left">Color: <i>${beer.color}</i></div>
	            <div class="text-left">Alcohol: <i>${beer.alcohol} %</i></div>
	        </p>
	    </div>
	    <div class="col-sm-8 col-md-7 col-lg-5 text-center mt-3 mt-md-0">
	        <p class="text-justify">
	            ${beer.description}
	        </p>
	    </div>
	</div>
	
	<div class="row justify-content-center mt-5">
	    <div class="col-10 col-md-8 col-lg-6 text-center">
			<c:choose>
				<c:when test="${requestScope.session.isAuthenticated()}">
					<a class="nav-item btn btn-primary" href="/NewNote?id=${beer.id}">New Tasting Note</a>
				</c:when>    
				<c:otherwise>
					<p>You need to login to create a new tasting note</p>
					<a class="nav-item btn btn-primary" href="/Login">Login</a>
				</c:otherwise>
			</c:choose>
	    </div>
    </div>
    
	<div class="row justify-content-center mt-5">
	    <div class="col-10 col-md-8 col-lg-6">
	        <div class="d-flex flex-column">
	            <c:forEach var="note" items="${notes}">
		            <div class="row justify-content-center note">
					    <div class="col-12">
					        <h5>${note.title}</h5>
					        <p>${note.content}</p>
					        <p class="small"><i><fmt:formatDate value="${note.created}" pattern="yyyy-MM-dd HH:mm:ss" /> - ${note.getUser().name}</i></p>
					    </div>
					</div>
				</c:forEach>
	        </div>
	    </div>
	</div>
	
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>