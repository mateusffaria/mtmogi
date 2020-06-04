$(document).ready(function() {
			var selected = [];
			var type = document.getElementById("typeServer").value;
		    $('#tabela').DataTable( {
		    	"searching": false,
		        "processing": true,
		        "serverSide": true,
		        "filter": true, 
		        "orderMulti": false,
		        "ordering": true,
	        	"select": true,
		        "ajax": "/service/get/servers/" + type,
		        "rowCallback": function( row, data ) {
		            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
		                $(row).addClass('selected');
		            }
		        },
		        "columnDefs": [
		            //Estilos Das Colunas
		            { className: "left", "targets": [0] },
		            { className: "left", "targets": [1] },
		            { className: "center", "targets": [2] },
		            { className: "center", "targets": [3], render: function(data){
		                	return ("R$ " + data.toFixed(2));
		                } 
		            }
		        ], 
		        "columns": [
		            { "data": "nome" },
		            { "data": "cargo" },
		            { "data": "rgf" },
		            { "data": "salario" }
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
		    
		    $('#tabela tbody').on('click', 'tr', function () {
		        var id = this.id;
		        var index = $.inArray(id, selected);
		        
		        if ( index === -1 ) {
		            selected.push( id );
		        } else {
		            selected.splice( index, 1 );
		        }
		 
		        $(this).toggleClass('selected');
		    });
		    
} );