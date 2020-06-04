$(document).ready(function() {
			var selected = [];
			var type = document.getElementById("typeServer").value;
  var tabela = $('#tabela').DataTable( {
		    	"searching": true,
		        "processing": true,
		        "serverSide": true,
		        "filter": true, 
		        "orderMulti": false,
		        "ordering": true,
	        	"select": false,
		        "ajax": "/service/get/servers/" + type,
		        "rowCallback": function( row, data ) {
		            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
		                $(row).addClass('selected');
		                $(row).attr("id", data.id_servidor);
		            }
		        },
		        "columnDefs": [
		            //Estilos Das Colunas
		        	{ className: "center", "orderable": false, "targets": [0], render: function(data){
	                	return '<a href="/historico/'+data+'"><i class="material-icons">history</i></a>';
	                	}
		            },
		            { className: "left", "targets": [1] },
		            { className: "left", "targets": [2] },
		            { className: "center", "targets": [3] },
		            { className: "center", "targets": [4], render: function(data){
		                	return ("R$ " + data.toFixed(2));
		                } 
		            },
		            { className: "right", "orderable": false, "targets": [5], render: function(data){
                		return '<td class="center"><input class="form-check-input" type="checkbox" value="'+data+'" id="check" onclick="check()"></td>';
                	}
                }
		        ], 
		        "columns": [
		        	{ "name": "historico", "data": "id" },
		            { "name": "nome", "data": "nome" },
		            { "name": "cargo", "data": "cargo" },
		            { "name": "rgf", "data": "rgf" },
		            { "name": "salario", "data": "salario" },
		            { "name": "compara", "data": "id"}
		        ],
		        "language": {
		            "lengthMenu": "Mostrando _MENU_ registros por página",
		            "loadingRecords": "Buscando...",
		            "processing": "Carregando...",
		            "zeroRecords": "Nenhum registro encontrado",
		            "info": "Mostrando página _PAGE_ de _PAGES_ de um total de _TOTAL_ registros",
		            "infoEmpty": "Nenhum registro disponível",
		            "infoFiltered": "(filtrado a partir de _MAX_ registros)",
		            "search": "Pesquisar",
		            "searchPlaceholder": "Digite algo...",
		            "paginate": {
		                "previous": "Anterior",
		                "next": "Próximo"
		              }
		        }
		    });
} );