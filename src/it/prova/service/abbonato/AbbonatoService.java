package it.prova.service.abbonato;

import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.model.Abbonato;

import java.time.LocalDate;
import java.util.List;

public interface AbbonatoService {

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao);

    public List<Abbonato> listAll() throws Exception;

    public Abbonato findById(Long idInput) throws Exception;

    public int aggiorna(Abbonato input) throws Exception;

    public int inserisciNuovo(Abbonato input) throws Exception;

    public int rimuovi(Long idDaRimuovere) throws Exception;

    public Abbonato getAbbonatoChePagaDiPiuAlMese() throws Exception;

    public List<Abbonato> getQuantiAttiviTraDueDate(LocalDate dataInizio, LocalDate dataFine) throws Exception;

    public List<Abbonato> getAbbonatiDistintiUltimiSeiMesi() throws Exception;
}
