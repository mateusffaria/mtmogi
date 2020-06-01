//Function to obtain the id´s contained in the checkbox type components
function check(){
			
	var array = []
	var checks = document.querySelectorAll('input[type="checkbox"]');
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
	
	for (var i = 0; i < checkboxes.length; i++ ){
		array.push(checkboxes[i].value);
	}
	
	if(checkboxes.length >= 4){
		
		for (var i = 0; i < checks.length; i++ ) {
			checks[i].disabled = checks[i].checked ? '' : 'disabled';
		};
		
	}else{
		
		for (var i = 0; i < checks.length; i++ ) {
			checks[i].disabled =  '';
		};
		
	}
	
	return array;
	
}

//Function to obtain the array with id´s of the selected servers
function getChecked(){
	var checkboxes = check();
	
	if(checkboxes.length < 2){
		alert("Para realizar comparações é necessário selecionar ao menos dois servidores.");
	
	}else{
         
		var dados = JSON.stringify(checkboxes);
		
		console.log(dados);
		
         $.ajax({
        	    url: "/service/compare",
        	    type: "POST",
        	    contentType: "application/json",
        	    data: dados
        	    	
        	}).done(function(resposta) {
        	    console.log(resposta);

        	}).fail(function(jqXHR, textStatus ) {
        	    console.log("Houve erro: " + textStatus);

        	}).always(function() {
        		console.log("Completou");
        		window.location = "/salario/comparar";
        	});
		
	}
	
	
}