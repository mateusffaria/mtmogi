package br.com.mtmogi.mtmogi.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import br.com.mtmogi.mtmogi.dto.ServidorSalarioDTO;
import br.com.mtmogi.mtmogi.model.TipoServidor;

@Repository
@Transactional
public class DataTablesDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	/**
	 * Method responsible for query and pagination of results
	 * @param columnIndex index of column
	 * @param order data display order
	 * @param start index for beginning of displayed records
	 * @param length number of rows to be returned in the query
	 * @return
	 */
	public List<ServidorSalarioDTO> findServersWithPage(String typeServer, int columnIndex, String order, int start, int length){
		
		String column = obtainColumName(columnIndex);
		StringBuilder sb = new StringBuilder();
		TipoServidor tipo = TipoServidor.valueOf(typeServer);
		
		sb.append("SELECT id, nome, cargo, rgf, salario, desconto ");
		sb.append("FROM servidor as servidor ");
		sb.append("LEFT JOIN vw_rendimento_servidor as rendimentos ON servidor.id = rendimentos.idservidor ");
		sb.append("LEFT JOIN vw_desconto_servidor as descontos ON servidor.id = descontos.idservidor ");
		sb.append("WHERE cargo ");
		sb.append("ILIKE '%");
		sb.append(tipo.getDescricao());
		sb.append("%'");
		sb.append("ORDER BY ");
		sb.append(column);
		sb.append(" ");
		sb.append(order);
		sb.append(" OFFSET ");
		sb.append(start);
		sb.append(" LIMIT ");
		sb.append(length);
		
		System.out.println(sb.toString());
		
		Query query = em.createNativeQuery(sb.toString());
		
		return builderServidorSalarioList(query.getResultList().iterator());
	}
	
	/**
	 * Method responsible for counting of the total records of a query
	 * @return
	 */
	public int countTotalServers(String typeServer) {
		
		StringBuilder sb = new StringBuilder();
		TipoServidor tipo = TipoServidor.valueOf(typeServer);
		
		sb.append("SELECT COUNT(*) total ");
		sb.append("FROM (SELECT * FROM servidor as servidor ");
		sb.append("LEFT JOIN vw_rendimento_servidor as rendimentos ON servidor.id = rendimentos.idservidor ");
		sb.append("LEFT JOIN vw_desconto_servidor as descontos ON servidor.id = descontos.idservidor ");
		sb.append("WHERE cargo ");
		sb.append("ILIKE '%");
		sb.append(tipo.getDescricao());
		sb.append("%'");
		sb.append(") as resultado");
		
		Query query = em.createNativeQuery(sb.toString());
		BigInteger total = (BigInteger) query.getSingleResult();
		
		return total.intValue();
	}
	
	/**
	 * Method responsible for creating a list of ServidorSalarioDTO from a list of objects
	 * @param queryResult
	 * @return
	 */
	private List<ServidorSalarioDTO> builderServidorSalarioList(Iterator<Object> queryResult) {
	
		Iterator<Object> iterator = queryResult;	
		List<ServidorSalarioDTO> servidores = new ArrayList<ServidorSalarioDTO>();
		 	
			while(iterator.hasNext()) {
				
				Object[] obj = (Object[]) iterator.next();
				Double salario = Double.valueOf(obj[4].toString());
				
				ServidorSalarioDTO servidor = new ServidorSalarioDTO();
				
				servidor.setId(Integer.valueOf(obj[0].toString()));
				servidor.setNome(obj[1].toString());
				servidor.setCargo(obj[2].toString());
				servidor.setRgf(obj[3].toString());
				servidor.setSalario(BigDecimal.valueOf(salario).setScale(2, RoundingMode.HALF_EVEN));
				
				servidores.add(servidor);
			}
				
			return servidores;
	}
	
	/**
	 * Method responsible for obtaining the name of a column based on its index
	 * @param columIndex int Reference to the selected column in DataTables
	 * @return columName String containing the column for the informed index
	 */
	private String obtainColumName(int columIndex) {
			
			String columName = null;
			
			switch (columIndex) {
			case 0:	
				columName = "nome";
				break;
			case 1:
				columName = "cargo";
				break;
			case 2:
				columName = "rgf";
				break;
			case 3:
				columName = "salario";
				break;	
			case 4:
				columName = "desconto";
				break;
			default:
				columName = "nome";
				break;
			}			
			return columName;
		}
}
