$(document).ready( function () {
	    $('#tabela').DataTable({
	    	"language": {
	            "lengthMenu": "Mostrando _MENU_ registros por página",
	            "zeroRecords": "Nenhum registro encontrado",
	            "info": "Mostrando página _PAGE_ de _PAGES_",
	            "infoEmpty": "Nenhum registro disponível",
	            "infoFiltered": "(filtrado a partir de _MAX_ registros)",
	            "search": "Pesquisar",
	            "paginate": {
	                "previous": "Anterior",
	                "next": "Próximo"
	              }
	        },
	    	"columnDefs": [ { "orderable": false, "targets": 3 },{ "orderable": false, "targets": 0 } ]
	    });
	} );