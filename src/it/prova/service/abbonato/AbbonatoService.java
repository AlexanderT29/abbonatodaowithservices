package it.prova.service.abbonato;

import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.model.Abbonato;

import java.util.List;

public interface AbbonatoService {

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao);

    public List<Abbonato> listAll() throws Exception;

    public Abbonato findById(Long idInput) throws Exception;

    public int aggiorna(Abbonato input) throws Exception;

    public int inserisciNuovo(Abbonato input) throws Exception;

    public int rimuovi(Long idDaRimuovere) throws Exception;
}
