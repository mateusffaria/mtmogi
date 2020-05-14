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
	
	return checkboxes;
	
}

//Function to obtain the matrix of ids of the selected servers
function getChecked(){
	var checkboxes = check();
	
	if(checkboxes.length < 2){
		alert("Para realizar comparações é necessário selecionar ao menos dois servidores.");
	}
	
	
}