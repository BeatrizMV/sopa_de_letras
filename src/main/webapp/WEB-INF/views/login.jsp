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
<%-- 		<h1>Hello <c:out value="${ name }" /></h1> --%>
<!-- 		<p>This is just an example page.</p> -->

	<main class="login__container">
		<h1 class="login__title">Login</h1>
		<form:form id="loginForm" class="login__form" modelAttribute="loginData" method="post"
				action="/p7project/authenticate">
			<div>
				<label class="login__label">Introduzca el nombre de usuario</label>
				<form:input path="userName" type="text" />
			</div>
			<div>
				<label class="login__label">Introduzca la contraseña</label>
				<form:input path="password" type="password" />
			</div>
			<button class="login__button" type="submit">Conectarse</button>
		</form:form>
		</main>
	</jsp:body>
</tags:template>