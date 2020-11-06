<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tags:template>
	<jsp:attribute name="head">  
		<script type="text/javascript">
			// inline JavaScript here 
		</script>
  	</jsp:attribute>  
  	
  	
	<jsp:body>
	
		<div>
			<c:out value="${ tabla }" />
		</div> 
		
		<div>
			<c:out value="${ palabras }" />
		</div> 
	

	
		<h1>Bienvenido a Sopa de letras, <c:out value="${ userName }" /></h1>
		
		<c:out value="${ htmlTabla }"  escapeXml="false" />
		
		<h3>Puntos</h3>
		<h3>Tiempo</h3>
		<div>
		
		</div>
		
		<form action="/p7project/new-game" method="post"> 
  			<input type="submit" value="Nueva partida">
		</form>
		
		
		
	</jsp:body>
</tags:template>