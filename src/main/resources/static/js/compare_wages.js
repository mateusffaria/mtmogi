var servers = [];

function getServerId(dado){
	
	var existe = false;
	
	for (var i = 0; i < servers_id.length; i++){
		
		if(servers_id[i] == dado){
			alert("Este servidor já foi selecionado");
			existe = true;
		}
	}
	
	if(!existe){
		if(servers_id.length < 4){
			servers_id.push(dado);
		}else{
			alert("Você já selecionou o número máximo de servidores");
		}
	}
	
}

function addServer(row){
	
	var tabela = $('#tabela').DataTable();
	
	if(!wasSelected(tabela.data()[row].id)){
		if(servers.length < 4){
			
			server = new Object();
			server.id = tabela.data()[row].id;
			server.name = tabela.data()[row].nome;
			servers.push(server);
			insertServerInTheList(server.id, server.name)
			alert("Você selecionou " + server.name + " para comparação salarial");
			
			
		}else{
			alert("Você já selecionou o número máximo de servidores para a comparação");
			console.log(servers);
		}
		
	}else{
		alert("Este servidor já foi selecionado");
	}
}

//Function to obtain the array with id´s of the selected servers
function getChecked(){
	
	if(servers.length < 2){
		alert("Para realizar comparações é necessário selecionar ao menos dois servidores.");
	
	}else{
         
		var dados = JSON.stringify(buildIdList());
		
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

function wasSelected(id){

	for (var i = 0; i < servers.length; i++){
		
		if(servers[i].id == id){
			return true;
		}
	}
	
	return false;	
}

function buildIdList(){
	
	serversId = [];
	
	for(var i = 0; i < servers.length; i++){
		serversId.push(servers[i].id);
	}
	
	return serversId;
}

function removeServerId(id){
	
	for(var i = 0; i < servers.length; i++){
		if(servers[i].id == id)
		servers.splice(i,1);
	}
	
	if(servers.length == 0){
		addStandardText();
	}
	
}

function insertServerInTheList(id, nome){

	var list = document.getElementById('selectedServers').innerHTML;
	list = list + '<li class="list-group-item" id="server'+id+'">'+nome+' <button type="button" class="btn btn-link" onclick="removeServeInTheList('+id+')" data-toggle="tooltip" data-placement="right" title="Removê-lo da lista de comparação de rendimentos"><i class="material-icons">close</i></button></li>'
	
	document.getElementById('selectedServers').innerHTML = list;
	
	if(document.getElementById("standard_text") != undefined){
		rmvStandardText();
	}
}

function removeServeInTheList(id){
	
	var node = document.getElementById("server"+id);
	  
		if (node.parentNode) {
			node.parentNode.removeChild(node);
		}
		
	removeServerId(id);	
		
}

function addStandardText(){
	
	var list = document.getElementById('selectedServers').innerHTML;
	list = list + '<li class="list-group-item center" id="standard_text"><i>Nenhum servidor selecionado</i></li>';
	document.getElementById('selectedServers').innerHTML = list;
	
}

function rmvStandardText(){

	var node = document.getElementById("standard_text");  
	if (node.parentNode) {
		node.parentNode.removeChild(node);
	}
	
}