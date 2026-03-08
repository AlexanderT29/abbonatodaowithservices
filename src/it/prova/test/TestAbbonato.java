package it.prova.test;

import it.prova.model.Abbonato;
import it.prova.service.MyServiceFactory;
import it.prova.service.abbonato.AbbonatoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestAbbonato {

    public static void main(String[] args) {

        // parlo direttamente con il service
        AbbonatoService abbonatoService = MyServiceFactory.getAbbonatoServiceImpl();

        try {
            System.out.println();

            // ora con il service posso fare tutte le invocazioni che mi servono
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + " elementi.\n");

            // GET - test FindById
            System.out.println("Ricerca dell'id 3: Luca Verdi \n" + testFindById(abbonatoService) + "\n");

            // PUT - test Insert
            testInsert(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");

            // UPDATE - test Aggiorna
            testAggiorna(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");

            // DELETE - test Rimuovi by ID
            testRimuovi(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");

            // GET - test GetAbbonatoChePagaDiPiuAlMese
            testGetAbbonatoChePagaDiPiuAlMese(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");

            // GET - test GetQuantiAttiviTraDueDate
            testGetQuantiAttiviTraDueDate(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");


            // GET - test GetAbbonatiDistintiUltimiSeiMesi
            testGetAbbonatiDistintiUltimiSeiMesi(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");

            // GET - test getConCognomeOverEtaEDisdettaDopoData
            testGetConCognomeOverEtaEDisdettaDopoData(abbonatoService);
            System.out.println("In tabella ci sono " + abbonatoService.listAll().size() + "elementi.\n");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Abbonato testFindById(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testFindById inizio.............");
        Abbonato prova = abbonatoService.findById(3L);
        if(prova == null){
            throw new RuntimeException("testFindById FAILED");
        }
        System.out.println(prova);
        System.out.println(".......testFindById PASSED.............");
        return prova;
    }

    public static int testInsert(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testInsert inizio.............");
        int result = 0;
        int sizeIniziale = abbonatoService.listAll().size();
        Abbonato prova = new Abbonato();
        prova.setNome("ProvaNome1");
        prova.setCognome("ProvaCognome1");
        prova.setImportomensile(10);
        prova.setDatadinascita(LocalDate.of(1996, 3, 29));
        prova.setDatastipula(LocalDate.of(2026, 2, 28));
        prova.setDatacessazione(LocalDate.of(2026, 3, 20));
        result = abbonatoService.inserisciNuovo(prova);
        System.out.println(result);
        int sizeFinale = abbonatoService.listAll().size();
        if(result != 1){
            throw new RuntimeException("testInsert FAILED");
        }
        System.out.println("Elemento aggiunto: " + prova);
        System.out.println(".......testInsert PASSED.............");
        return result;
    }

    public static int testAggiorna(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testAggiorna inizio.............");
        int result = 0;
        List<Abbonato> abbonati = abbonatoService.listAll();
        Abbonato prova = abbonatoService.findById(abbonati.get(abbonati.size() - 1).getId());
        if (prova == null){
            throw new RuntimeException("testAggiorna FAILED");
        }
        prova.setNome("ProvaNome1Aggiornato");
        result = abbonatoService.aggiorna(prova);
        if(result != 1){
            throw new RuntimeException("testAggiorna FAILED");
        }
        System.out.println("Elemento aggiornato: " + prova);
        System.out.println(".......testAggiorna PASSED.............");
        return result;

    }

    public static int testRimuovi(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testRimuovi inizio.............");
        int result = 0;
        List<Abbonato> abbonati = abbonatoService.listAll();
        if(abbonati.size() == 0 || abbonati == null){
            throw new RuntimeException("testRimuovi FAILED");
        }
        Long idDaRimuove = abbonati.get(abbonati.size() -1).getId();
        System.out.println("Elemento rimosso: " + abbonati.get(abbonati.size() -1));

        result = abbonatoService.rimuovi(idDaRimuove);
        if (result != 1){
            throw new RuntimeException("testRimuovi FAILED");
        }
        System.out.println(".......testRimuovi inizio.............");
        return result;
    }

    public static Abbonato testGetAbbonatoChePagaDiPiuAlMese(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testGetAbbonatoChePagaDiPiuAlMese inizio.............");
        Abbonato prova = abbonatoService.getAbbonatoChePagaDiPiuAlMese();
        if (prova == null){
            throw new RuntimeException("testGetAbbonatoChePagaDiPiuAlMese FAILES");
        }
        System.out.println("Abbonato che paga di più: " + prova);
        System.out.println(".......testGetAbbonatoChePagaDiPiuAlMese PASSED.............");
        return prova;
    }

    public static void testGetQuantiAttiviTraDueDate(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testGetQuantiAttiviTraDueDate inizio.............");
        List<Abbonato> prova = new ArrayList<Abbonato>();
        LocalDate d1 = LocalDate.of(2023, 01, 01);
        LocalDate d2 = LocalDate.of(2023, 10, 01);
        prova = abbonatoService.getQuantiAttiviTraDueDate(d1, d2);
        if (prova == null || prova.size() == 0){
            throw new RuntimeException("NON CI SONO ABBONATI CHE RISPETTANO I CRITERI DI RICERCA E/O NEL DATABASE!");
        }
        System.out.println("Trovati " + prova.size() + " abbonati");
        System.out.println(".......testGetQuantiAttiviTraDueDate PASSED.............");

    }

    public static void testGetAbbonatiDistintiUltimiSeiMesi(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testGetAbbonatiDistintiUltimiSeiMesi inizio.............");
        List<Abbonato> prova = null;
        prova = abbonatoService.getAbbonatiDistintiUltimiSeiMesi();
        if(prova == null){
            throw new RuntimeException("testGetQuantiAttiviTraDueDate FAILED");
        }
        System.out.println("Trovati " + prova.size() + " abbonati.");
        System.out.println(".......testGetAbbonatiDistintiUltimiSeiMesi PASSED.............");
    }

    public static void testGetConCognomeOverEtaEDisdettaDopoData(AbbonatoService abbonatoService) throws Exception{
        System.out.println(".......testGetConCognomeOverEtaEDisdettaDopoData inizio.............");
        List<Abbonato> prova = null;
        prova = abbonatoService.getConCognomeOverEtaEDisdettaDopoData("Rossi", 60, LocalDate.of(2021, 01, 02));
        if (prova == null){
            throw new RuntimeException("testGetConCognomeOverEtaEDisdettaDopoData FAILED");
        }
        System.out.println("Trovati " + prova.size() + " abbonati.");
        System.out.println(".......testGetConCognomeOverEtaEDisdettaDopoData PASSED.............");

    }

}
