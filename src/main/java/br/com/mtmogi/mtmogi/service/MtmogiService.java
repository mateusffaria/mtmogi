package br.com.mtmogi.mtmogi.service;

import java.util.List;
import br.com.mtmogi.mtmogi.model.Servidor;

public interface MtmogiService {
    public List<Servidor> findAll();
    public Servidor findById(long id);
    public Servidor save(Servidor servidor);
    public List<Servidor> findByNomeLike(String nome);
    public List<Servidor> findByCargoLike(String cargo);
}