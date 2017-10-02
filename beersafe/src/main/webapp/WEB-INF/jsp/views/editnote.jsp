<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="BeerSafe"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2 class="col-12 text-center">Update Tasting Note</h2>
	</div>

	<c:if test="${error.length() > 0}">
		<div class="row justify-content-center mt-4">
			<div class="alert alert-danger">
				<p class="m-0">${error}</p>
			</div>
		</div>
	</c:if>

	<div class="row justify-content-center mt-4 mb-2">
	    <form method="POST" action="/UpdateNote">
	    		<input type="hidden" name="id" value="${note.id}">
	    		
	        <div class="form-group">
	            <label>Title</label>
	            <input type="text" class="form-control" size="40" name="title" value="${note.title}">
	        </div>
	        <div class="form-group">
	            <label>Tasting notes</label>
	            <textarea class="form-control" rows="3" name="content">${note.content}</textarea>
	        </div>
	        <div class="form-check">
	            <label class="form-check-label">
	                <input type="checkbox" class="form-check-input" name="publicNote" value="${note.publicNote}">
	                Share this note with other users?
	            </label>
	        </div>
	        <div class="mt-2 text-center">
	            <input class="btn btn-primary" type="submit" value="Save Note">
	        </div>
	    </form>
	</div>
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>