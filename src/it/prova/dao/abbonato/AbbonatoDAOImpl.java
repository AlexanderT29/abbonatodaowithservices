package it.prova.dao.abbonato;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Abbonato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AbbonatoDAOImpl extends AbstractMySQLDAO implements AbbonatoDAO{

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Abbonato> list() throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        ArrayList<Abbonato> result = new ArrayList<Abbonato>();

        try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from abbonato")) {
            while (rs.next()) {
                Abbonato abbonatoTemp = new Abbonato();
                abbonatoTemp.setId(rs.getLong("id"));
                abbonatoTemp.setNome(rs.getString("nome"));
                abbonatoTemp.setCognome(rs.getString("cognome"));
                abbonatoTemp.setImportomensile(rs.getInt("importomensile"));
                abbonatoTemp.setDatadinascita(rs.getDate("datadinascita").toLocalDate());
                abbonatoTemp.setDatastipula(
                        rs.getDate("datastipula") != null ? rs.getDate("datastipula").toLocalDate() : null);
                abbonatoTemp.setDatacessazione(rs.getDate("datacessazione") != null ? rs.getDate("datacessazione").toLocalDate() : null);
                result.add(abbonatoTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
        return result;
    }

    @Override
    public Abbonato get(Long idInput) throws Exception {
        // prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (idInput == null || idInput < 1)
            throw new Exception("Valore di input non ammesso.");

        Abbonato result = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from abbonato where id=?")) {

            ps.setLong(1, idInput);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new Abbonato();
                    result.setNome(rs.getString("nome"));
                    result.setCognome(rs.getString("cognome"));
                    result.setImportomensile(rs.getInt("importomensile"));
                    result.setDatadinascita(rs.getDate("datadinascita") != null ? rs.getDate("datadinascita").toLocalDate() : null);
                    result.setDatastipula(rs.getDate("datastipula") != null ? rs.getDate("datastipula").toLocalDate() : null);
                    result.setDatacessazione(rs.getDate("datacessazione") != null ? rs.getDate("datacessazione").toLocalDate() : null);
                    result.setId(rs.getLong("id"));
                } else {
                    result = null;
                }
            } // niente catch qui

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int insert(Abbonato input) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO abbonato (nome, cognome, importomensile, datadinascita, datastipula, datacessazione) VALUES (?, ?, ?, ?, ?, ?);")) {
            ps.setString(1, input.getNome());
            ps.setString(2, input.getCognome());
            ps.setInt(3, input.getImportomensile());
            ps.setDate(4, java.sql.Date.valueOf(input.getDatadinascita()));
            // quando si fa il setDate serve un tipo java.sql.Date
            ps.setDate(5, java.sql.Date.valueOf(input.getDatastipula()));
            if(input.getDatacessazione() != null) {
                ps.setDate(6, java.sql.Date.valueOf(input.getDatacessazione()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int update(Abbonato input) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null || input.getId() == null || input.getId() < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE abbonato SET nome=?, cognome=?, importomensile=?, datadinascita=?, datastipula=?, datacessazione = ? where id=?;")) {
            ps.setString(1, input.getNome());
            ps.setString(2, input.getCognome());
            ps.setInt(3, input.getImportomensile());
            ps.setDate(4, java.sql.Date.valueOf(input.getDatadinascita()));
            ps.setDate(5, java.sql.Date.valueOf(input.getDatastipula()));
            ps.setDate(6, java.sql.Date.valueOf(input.getDatacessazione()));
            ps.setLong(7, input.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int delete(Long idDaRimuovere) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (idDaRimuovere == null || idDaRimuovere < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM abbonato WHERE id=?")) {
            ps.setLong(1, idDaRimuovere);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
