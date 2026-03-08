package it.prova.dao.abbonato;

import it.prova.dao.IBaseDAO;
import it.prova.model.Abbonato;

import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.List;

public interface AbbonatoDAO extends IBaseDAO<Abbonato> {

    public Abbonato getAbbonatoChePagaDiPiuAlMese() throws Exception;

    public List<Abbonato> getQuantiAttiviTraDueDate(LocalDate dataInizio, LocalDate dataFine) throws Exception;

    public List<Abbonato> getAbbonatiDistintiUltimiSeiMesi() throws Exception;

    public List<Abbonato> getConCognomeOverEtaEDisdettaDopoData(String cognomeInput, int eta, LocalDate date) throws Exception;

    public List<Abbonato> getSituazioniAnomale() throws Exception;
}
