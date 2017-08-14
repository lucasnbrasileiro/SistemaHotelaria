package hotel.acess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {


	private static String DRIVER_M = "com.mysql.jdbc.Driver"; //Classe do driver JDBC

    private static String BANCO_M = "hotel"; //Nome do Banco criado

    private static String HOST_M = "localhost"; //Maquina onde onde esta o banco de dados

    private static String STR_CONEXAO_M = "jdbc:mysql://" + HOST_M + "/" + BANCO_M; //URL de conexao

    private static String USUARIO_M = "root"; //usario do bd
    private static String SENHA_M = "root"; //Senha do usuario

    public static Connection abreConexaoMySQL() throws SQLException{
	   try {
	  	Class.forName(DRIVER_M);
	  	Connection con = DriverManager.getConnection(STR_CONEXAO_M,USUARIO_M,SENHA_M);
	  	return con;
	   } catch (ClassNotFoundException e) {
	  	throw new SQLException(e.getMessage());
	   }
	}
}
