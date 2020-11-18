<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tags:template>
	<jsp:attribute name="head">  

  	</jsp:attribute>


	<jsp:body>
	
	    	<header class="app-header">
	    	<div class="user-logged-in-container">
	    		<a href="/p7project/login" class="user-logged-in">Desconectarse como <c:out
					value="${ userName }" /></a>
	    	</div>
	    		
	        	<h1 class="main-title">Sopa de letras</h1>
	        </header>
	        <main class="app-main">
	        	<c:out value="${ htmlTabla }" escapeXml="false" />
	        	<section class="palabras__container">
	        		<div class="palabras__title">Palabras a buscar</div>
	        		<ul id="a-buscar">
	        			<c:forEach var="item" items="${ palabras }">
	        				<li id="${item}" class="a-buscar--palabra">${item}</li>
	        			</c:forEach>
	        		</ul>
	        	</section>
	        </main>	
		
		<div class="new-game-block">
		<h2 id="puntuacion-final" class="score hide"></h2>
		
			<form action="/p7project/new-game" method="post"> 
  				<input class="new-game-button" type="submit" value="Nueva partida">
			</form>
		</div>
		
		
		<script type="text/javascript">
		
		function getCurrentCharElement(h, v){
			const id = "#" + v + "_" + h;
			return $(id)[0];
		}
		
		function getCurrentChar(h, v){
			const element = getCurrentCharElement(h, v);
			if(element){
				console.log("Current char is: ", element.textContent);
				return element.textContent;	
			} else {
				console.log("No element with id: ", id);
				return null;
			}
			
		}
		
		function paintCurrentChar(h, v, fontColor, backgroundColor){
			const element = getCurrentCharElement(h, v);
			//Si el elemento ha sido previamente seleccionado y acertado, no cambiar
			if(element.className.includes("selected-char")){
				console.log("Letra previamente seleccionada, no se cambia el color");
			} else {
				const styleStr = "color: " + fontColor + "; background-color: " + backgroundColor +";";
				console.log("Applying this style to this coordinates:", styleStr, h, v);
				element.style = styleStr;
			}
		}
		
		function setCurrentCharSelected(h, v){
			const element = getCurrentCharElement(h, v);
			console.log("Applying the selected style to the cell");
			element.className += " selected-char";
		}
		
		function traverseSelectedWordAndApply(fCharV, 
											  fCharH, 
											  lCharV, 
											  lCharH,
											  callback) {
			
						
			//detectar direccion
			//misma horizontal
			if(fCharV === lCharV){
				//derecha
				if(fCharH < lCharH){
					console.log("Same horizontal, right");
					for(let i=fCharH; i <= lCharH; i++){
						callback(i, fCharV);
						
					}
				//izquierda	
				} else {
					console.log("Same horizontal, left");
					for(let i=fCharH; i >= lCharH; i--){
						callback(i, fCharV);
						
					}
				}
			//misma vertical
			} else if(fCharH === lCharH) {
				//abajo
				if(fCharV < lCharV){
					console.log("Same vertical, down");
					for(let i=fCharV; i <= lCharV; i++){
						callback(fCharH, i);
						
					}
				//arriba	
				} else {
					console.log("Same vertical, up");
					for(let i=fCharV; i >= lCharV; i--){
						callback(fCharH, i);
						
					}	
				}
			//diagonal abajo derecha y arriba izquierda
			} else if((fCharH - fCharV) === (lCharH - lCharV)){
				//abajo derecha
				if(lCharV > fCharV){
					console.log("Same diagonal, down right");
					for(let i=fCharH, j=fCharV; i <= lCharH && j <= lCharV; i++, j++){
						callback(i, j);
						
					}
				//arriba izquierda
				} else {
					console.log("Same diagonal, up left");
					for(let i=fCharH, j=fCharV; i >= lCharH && j >= lCharV; i--, j--){
						callback(i, j);
						
					}
				}
			//diagonal arriba derecha y abajo izquierda	
			} else if((fCharH + fCharV) === (lCharH + lCharV)){
				//abajo izquierda
				if(lCharV > fCharV){
					console.log("Same diagonal, down left");
					for(let i=fCharH, j=fCharV; i >= lCharH && j <= lCharV; i--, j++){
						callback(i, j);
						
					}
				//arriba derecha
				} else {
					console.log("Same diagonal, up right");
					for(let i=fCharH, j=fCharV; i <= lCharH && j >= lCharV; i++, j--){
						callback(i, j);
						
					}
				}
			//valores que no conectan
			} else {
				console.log("The coordinates can't be connected:", fCharV, fCharH, lCharV, lCharH);
			}
		}
		
		
		let firstCharV= null; 
		let firstCharH= null; 
		let lastCharV= null; 
		let lastCharH = null;
		
		
		
		document.querySelectorAll(".celda").forEach(function(item){
		    item.addEventListener("click", function(event){
		    	const fullId = event.target.id;
		    	const vertical = event.target.id.split("_")[0];
		    	const horizontal = event.target.id.split("_")[1];
		    	console.log("vertical:" + vertical);
		    	console.log("horizontal:" + horizontal);
		    	
		    	if(!firstCharV){
		    		firstCharV = vertical;
		    		firstCharH = horizontal;
		    	} else {
		    		lastCharV = vertical;
		    		lastCharH = horizontal;
		    		let charsToCheck = []; 
		    		console.log("Getting chars for", firstCharV, firstCharH, lastCharV, lastCharH);
		    		traverseSelectedWordAndApply(parseInt(firstCharV), parseInt(firstCharH), 
		    				parseInt(lastCharV), parseInt(lastCharH),
		    				function(i, j){
		    					const currentChar = getCurrentChar(i, j);
								paintCurrentChar(i, j, "black", "grey");
								if(currentChar){
									charsToCheck.push(currentChar);
								}
		    				});
		    		console.log("Sending request for chars: ", charsToCheck);
		    		let objToSend = {
		    				palabraComprobar: charsToCheck
		    		};
		    		const jsonToSend = JSON.stringify(objToSend);
		    		console.log("Sending json:", jsonToSend);
		    		$.ajax({
		    			type: "POST",
		    			url: "/p7project/checkWord",
		    			data: jsonToSend,
		    			dataType: "json",
		    			contentType: "application/json; charset=utf-8",
		    			success: function(resp){
		    				console.log("Received from server:", resp);
		    				if(resp && resp.correcto && resp.palabrasRestantes === 0){
		    					let text = "Juego finalizado!!";
		    					text += "\nSegundos en terminar la partida: " + resp.segundosUtilizados;
		    					text += "\nPuntuación final: " + resp.puntos;
		    					alert(text);
		    					traverseSelectedWordAndApply(parseInt(firstCharV), parseInt(firstCharH), 
					    				parseInt(lastCharV), parseInt(lastCharH),
					    				function(i, j){
				    						console.log("Painting the cells in green");
											paintCurrentChar(i, j, "white", "green");
											setCurrentCharSelected(i, j);
					    				});
		    					tachaPalabraLista(resp.palabraRecienAcertada);
		    					finalizarPartida(resp.puntos);
		    				} else if(resp && resp.correcto){
		    					let found = "Palabra encontrada!\nPalabras restantes: " + resp.palabrasRestantes; 
				    			alert(found);
				    			//Dibuja la palabra que ya esta en gris, ahora en verde
				    			traverseSelectedWordAndApply(parseInt(firstCharV), parseInt(firstCharH), 
					    				parseInt(lastCharV), parseInt(lastCharH),
					    				function(i, j){
				    						console.log("Painting the cells in green");
				    						paintCurrentChar(i, j, "white", "green");
				    						setCurrentCharSelected(i, j);
					    				});
				    			tachaPalabraLista(resp.palabraRecienAcertada.toLowerCase());
		    				} else {
		    					//No se ha encontrado nada, pintar la casilla de blanco de nuevo
		    					traverseSelectedWordAndApply(parseInt(firstCharV), parseInt(firstCharH), 
					    				parseInt(lastCharV), parseInt(lastCharH),
					    				function(i, j){
				    						console.log("Painting the cells in white");
											paintCurrentChar(i, j, "black", "white");
					    				});
		    				}
		    				
			    		}
		    		})
		    			.done(function(){
		    				console.log("Process finished");
		    			})
		    			.fail(function(error){
		    				console.log("Error posting to /checkWord", error.status);
		    				console.log("Status text:", error.statusText);
		    			})
		    			.always(function(){
		    				console.log("Reseteando las coordenadas");
		    				firstCharV= null; 
				    		firstCharH= null; 
				    		lastCharV= null; 
				    		lastCharH = null;
		    			});
		    		;
		    	}
		    	
		    	
		    });
		});
		
		function tachaPalabraLista(palabra){
			//cada palabra de la lista tiene un id igual a su string
			console.log("Tachando la palabra:", palabra);
			const element = document.getElementById(palabra);
			console.log("desde tachaPalabraLista", element);
			element.className += " encontrado";
		}
		
		function finalizarPartida(puntos) {
			const elem = document.getElementById("puntuacion-final");
			const text = "Puntuación final: " + puntos;
			elem.textContent = text;
			elem.classList.remove("hide");
			//deshabilitar los clicks en la sopa de letras ya que se ha terminado
			const elem2 = document.getElementById("sopa-letras");
			elem2.className += " disable-clicks";
		}
		
		</script>
		
	</jsp:body>
</tags:template>