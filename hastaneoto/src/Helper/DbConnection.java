package Helper;
import java.sql.*;
public class DbConnection {
	    Connection c = null;
	    
	    public DbConnection() {
	    	
	    }
	    public Connection connDb() {
	    	try {
				this.c= DriverManager.getConnection("jdbc:mysql://localhost:3306/hastaneotomasyonu?useTimezone=true&serverTimezone=UTC","root","200112Ze");
				return c;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return c;
	    }
		
	}


