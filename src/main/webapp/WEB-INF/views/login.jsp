<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here
		</script>
  	</jsp:attribute>
	<jsp:body>

	<main class="login__container">
		<h1 class="login__title">Login</h1>
		<form:form id="loginForm" class="login__form" modelAttribute="loginData" method="post"
				action="/p7project/authenticate">
			<div>
				<label class="login__label">Introduzca el nombre de usuario</label>
				<form:input path="userName" type="text" />
			</div>
			<div>
				<label class="login__label">Introduzca la contraseņa</label>
				<form:input path="password" type="password" />
			</div>
			<button class="login__button" type="submit">Conectarse</button>
		</form:form>
		</main>
		
		<c:choose>
			<c:when test="${empty loginError }">
			</c:when>
			<c:otherwise>
				<section class="error__block">
					<c:out value="${loginError}" />
				</section>
			</c:otherwise>
		
		</c:choose>
		
		
		<script type="text/javascript">
			//Caducamos cookie
			//document.cookie = "spuser=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
			
			
		</script>
		
		
	</jsp:body>
</tags:template>