package it.prova.service.abbonato;

import it.prova.connection.MyConnection;
import it.prova.dao.Constants;
import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.dao.abbonato.AbbonatoDAOImpl;
import it.prova.model.Abbonato;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AbbonatoServiceImpl implements AbbonatoService {


    private AbbonatoDAO abbonatoDAO;


    @Override
    public void setAbbonatoDao(AbbonatoDAO abbonatoDao) {
        this.abbonatoDAO = abbonatoDao;
    }

    @Override
    public List<Abbonato> listAll() throws Exception{

        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato findById(Long idInput) throws Exception{
        if (idInput == null || idInput < 1)
            throw new Exception("Valore di input non ammesso.");

        Abbonato result = null;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.get(idInput);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int aggiorna(Abbonato input) throws Exception{
        if (input == null || input.getId() == null || input.getId() < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.update(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int inserisciNuovo(Abbonato input) throws Exception{
        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.insert(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int rimuovi(Long idDaRimuovere) throws Exception{
        if (idDaRimuovere == null || idDaRimuovere < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.delete(idDaRimuovere);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato getAbbonatoChePagaDiPiuAlMese() throws Exception{

        Abbonato result = null;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.getAbbonatoChePagaDiPiuAlMese();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public List<Abbonato> getQuantiAttiviTraDueDate(LocalDate dataInizio, LocalDate dataFine) throws Exception{
        if(dataFine.isBefore(dataInizio) ){
            throw new RuntimeException("LA DATA DI FINE NON PUO' ESSERE MINORE DI QUELLA DI INIZIO!");
        }
        List<Abbonato> result = new ArrayList<Abbonato>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.getQuantiAttiviTraDueDate(dataInizio, dataFine);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;

    }

    public List<Abbonato> getAbbonatiDistintiUltimiSeiMesi() throws Exception{
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.getAbbonatiDistintiUltimiSeiMesi();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public List<Abbonato> getConCognomeOverEtaEDisdettaDopoData(String cognomeInput, int eta, LocalDate date) throws Exception{
        if(cognomeInput == null || cognomeInput == "" || eta <= 18 || date == null){
            throw new RuntimeException("INPUT NON VALIDO!");
        }
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.getConCognomeOverEtaEDisdettaDopoData(cognomeInput, eta, date);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public List<Abbonato> getSituazioniAnomale() throws Exception{
        List<Abbonato> result = new ArrayList<Abbonato>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDAO.getSituazioniAnomale();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }



}
