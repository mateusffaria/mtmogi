package br.com.mtmogi.mtmogi.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.repository.MtmogiRepository;
import br.com.mtmogi.mtmogi.service.MtmogiService;

@Service
public class MtmogiServiceImpl implements MtmogiService {

    @Autowired
    MtmogiRepository mRepository;

    @Override
    public Servidor findById(long id) {
        return mRepository.findById(id).get();
    }

    @Override
    public Servidor save(Servidor servidor) {
        return mRepository.save(servidor);
    }

    @Override
    public List<Servidor> findAll() {
        return mRepository.findAll();
    }

    @Override
    public List<Servidor> findByNomeLike(String nome) {
        return mRepository.findByNomeLike(nome);
    }

    @Override
    public List<Servidor> findByFuncaoLike(String funcao) {
        return mRepository.findByFuncaoLike("%" + funcao + "%");
    }
    
    public List<Servidor> findServers(ArrayList<Long> idServers){
    
    	List<Servidor> servers = new ArrayList<Servidor>();
    	
    	for (Long id : idServers) {
			
    		Servidor server = new Servidor();
    		server = mRepository.findById(id).get();
    		
    		if(server != null) {
    			servers.add(server);
    		}
		}
    	 	
    	return servers;
    }
    
    
}