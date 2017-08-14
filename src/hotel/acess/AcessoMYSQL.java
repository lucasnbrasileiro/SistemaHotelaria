
package hotel.acess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcessoMYSQL {
	
	Connection con;

	public static void main(String args[]) {
		
		AcessoMYSQL acessoMySql = new AcessoMYSQL();		
	}

	public AcessoMYSQL() {
		
		conectar();
	}

	public final Connection conectar() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			
			Logger.getLogger(AcessoMYSQL.class.getName()).log(Level.SEVERE,null, ex);
			System.out.println("N�o foi poss�vel encontrar o driver");		
			
		}
		
		try {
			
			System.out.println("Conex�o bem sucedida!");
			con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root","root");
			
		} catch (SQLException ex) {
			
			Logger.getLogger(AcessoMYSQL.class.getName()).log(Level.SEVERE,null, ex);
			System.out.println("N�o foi poss�vel conectar ao banco!");
		}

		return con;
	}

	public void desconectar() {
		
		try {
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(AcessoMYSQL.class.getName()).log(Level.SEVERE,null, ex);
		}
	}
}
