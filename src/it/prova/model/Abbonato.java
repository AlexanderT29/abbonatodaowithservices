package it.prova.model;

import java.time.LocalDate;
import java.util.Date;

public class Abbonato {
    private Long id;
    private String nome;
    private String cognome;
    private int importomensile;
    private LocalDate datadinascita;
    private LocalDate datastipula;
    private LocalDate datacessazione;

    public Abbonato(){

    }



    public Abbonato(String nome, String cognome, int importomensile, LocalDate datadinascita, LocalDate datastipula, LocalDate datacessazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.importomensile = importomensile;
        this.datadinascita = datadinascita;
        this.datastipula = datastipula;
        this.datacessazione = datacessazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getImportomensile() {
        return importomensile;
    }

    public void setImportomensile(int importomensile) {
        this.importomensile = importomensile;
    }

    public LocalDate getDatadinascita() {
        return datadinascita;
    }

    public void setDatadinascita(LocalDate datadinascita) {
        this.datadinascita = datadinascita;
    }

    public LocalDate getDatastipula() {
        return datastipula;
    }

    public void setDatastipula(LocalDate datastipula) {
        this.datastipula = datastipula;
    }

    public LocalDate getDatacessazione() {
        return datacessazione;
    }

    public void setDatacessazione(LocalDate datacessazione) {
        this.datacessazione = datacessazione;
    }

    @Override
    public String toString() {
        return "Abbonato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", importomensile=" + importomensile +
                ", datadinascita=" + datadinascita +
                ", datastipula=" + datastipula +
                ", datacessazione=" + datacessazione +
                '}';
    }
}
