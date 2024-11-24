package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DbManagerTest {

    //private final String connString = "jdbc:oracle:thin:@localhost:1521:xe";
    private DbManager dbmanager;
    private Connection connMock;
    private Statement stmtMock;
    private ResultSet resMock;

    @BeforeEach
    void SetUp() throws SQLException {
        dbmanager = new DbManager();
        connMock = mock(Connection.class);
        stmtMock = mock(Statement.class);
        resMock = mock(ResultSet.class);
    }

//    @Test
//    void SeConessioneNullReturnFalse() throws SQLException {
//
//        //when(connMock.isValid(anyInt())).thenReturn(false);
//        assertTrue(dbmanager.OpenConnection());
//        //  connMock = null;
//
//    }
//
//    @Test
//    void connessioneFallitaEdSqlErr() {
//
//    }


//    @Test
//    void ThrowSQLExcIfGetProdottoMACOnnChiusa() throws SQLException {
//

    /// /        when(connMock.prepareCall())
//        // when(connMock.isValid())
//        when(connMock.isValid(anyInt())).thenReturn(false);
//        //when(connMock.createStatement()).thenThrow(new SQLException("connessione chiusa"));
//
//        SQLException exc = assertThrows(
//                SQLException.class,
//                () -> dbmanager.getProdotto()
//        );
//
//        String ExpectedErrMsg = "apertura connessione fallita.";
//        String ActualMsg = exc.getMessage();
//
//        assertTrue(ActualMsg.contains(ExpectedErrMsg));
//
//    }
//    @Test
//    void GetProdottoTest() throws SQLException {
//
//
//        when(connMock.isValid(1)).thenReturn(true);
//        when(stmtMock.executeQuery("SELECT IDPRODOTTO , NOME_PRODOTTO , QTA FROM PRODOTTI"));
//
//        when(resMock.next()).thenReturn(true).thenReturn(false);
//        when(resMock.getInt("IDPRODOTTO")).thenReturn(1);
//        when(resMock.getString("NOME_PRODOTTO")).thenReturn("Prodotto1");
//        when(resMock.getFloat("QTA")).thenReturn(10.0f);
//
//        dbmanager.getProdotto();
//    }

}
