package config;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String HOST = "jdbc:mysql://localhost:3312/industria_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection () {
        try{
            Connection conn = DriverManager.getConnection(HOST, USER, PASSWORD);
            System.out.println("Conectado ao banco com sucesso!");
            return conn;

        }catch (SQLException erro){
            System.out.println("Erro ao se conectar ao banco" + erro.getMessage());
        }

        return null;
    }


}
