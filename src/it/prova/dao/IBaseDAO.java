package it.prova.dao;

import java.sql.Connection;
import java.util.List;

public interface IBaseDAO<T> {
    public List<T> list() throws Exception;

    public T get(Long idInput) throws Exception;

    public int update(T input) throws Exception;

    public int insert(T input) throws Exception;

    public int delete(Long idDaRimuovere) throws Exception;

    public void setConnection(Connection connection);
}
