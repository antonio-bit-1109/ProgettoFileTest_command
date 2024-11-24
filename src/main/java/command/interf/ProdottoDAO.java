package command.interf;

import database.DTOs.ProdottoDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO<T> {

    void getAllProdotti() throws SQLException;

    void getProdottoByNome() throws SQLException;

    boolean DeleteProdotto();

    void UpdateProdottoByNome() throws SQLException;

    void AddNewProdotto();
}
