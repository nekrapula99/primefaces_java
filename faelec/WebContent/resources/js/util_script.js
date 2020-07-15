/**
 * 
 */
function onload(){
	//Redimenciona la altura del div principal de la plantilla de la app
	//var obj = document.getElementById('content');
	//var newHeight = (screen.height) - (screen.height * 0.4);
	//obj.style.height = newHeight+"px";
}



function calcularDimensionPantalla(){
	var WINDOW_HEIGHT = $(window).height();   // returns height of browser viewport
	var DOCUMENT_HEIGHT = $(document).height(); // returns height of HTML document
	var WINDOW_WIDTH = $(window).width();   // returns width of browser viewport
	var DOCUMENT_WIDTH = $(document).width(); // returns width of HTML document
	
	var elem_windowHeight = document.getElementById('formTemplate:WINDOW_HEIGHT');		
	var elem_documentHeight = document.getElementById('formTemplate:DOCUMENT_HEIGHT');
	var elem_windowWidth = document.getElementById('formTemplate:WINDOW_WIDTH');
	var elem_documentWidth = document.getElementById('formTemplate:DOCUMENT_WIDTH');	

	elem_windowHeight.value = WINDOW_HEIGHT;
	elem_documentHeight.value = DOCUMENT_HEIGHT;
	elem_windowWidth.value = WINDOW_WIDTH;
	elem_documentWidth.value = DOCUMENT_WIDTH;
}

//Funcion que capture el evento del enter
document.onkeypress=function(e){
	 var esIE=(document.all);
	 tecla=(esIE) ? event.keyCode : e.which;
	 
	 if(tecla==13 && e.target.nodeName !== 'TEXTAREA' && e.target.nodeName !== 'BUTTON'){
	  return false;
	  }
	}

/*
 * Método encargado de vlaidar que los campos requeridos de los formularios estén diligenciados
 */
function handleComplete(xhr, status, args) {
	alert("hello deja el show");
	return false;
	 if(args.validationFailed) {
		 return true;
     } else { 
    	 return false; 
     }   
}

/*
 * Retorna la url del login
 */
function getUrlLogin(){
	return "http://"+location.host+"/dinox";
}

/*
 * Lim
*/
function limpiarInputText(input){	
	document.getElementById(input).value = '';
}
