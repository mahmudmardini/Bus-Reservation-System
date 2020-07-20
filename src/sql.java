import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public  class sql {

	public static Connection Conn;
	
	sql(){
		
		// MYSQL CONNECTION...
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			
			 Conn = DriverManager.getConnection("jdbc:mysql://localhost/reservation_system?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");	
			 
			 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// YENI SEFER OLUSTURMA METODU 
 public static void addjourney(String date, String time, String bus, String departure, String destination, double price) {
		
	 int journeyId = 0;
	 
		try {
			
			String query = "INSERT INTO journeys (date, time, Bus_No, departure, destination, price) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			// mysql insert statement olustur
		      PreparedStatement preparedStmt = Conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		      preparedStmt.setString (1, date);
		      preparedStmt.setString (2, time);
		      preparedStmt.setString (3, bus);
		      preparedStmt.setString (4, departure);
		      preparedStmt.setString (5, destination);
		      preparedStmt.setDouble (6, price);

		      // mysql insert kodu uygula
		      preparedStmt.executeUpdate();

		      ResultSet result = preparedStmt.getGeneratedKeys();
				
				if(result.next()){
					journeyId = result.getInt(1); // yeni olusturulan seferin id'sini tutma 
				}
				
		      // Yeni olusturan sefer icin bir otobus ayirma 
		      addSeats( bus, journeyId);
		      
		      
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Tarih ve saat formati kontrol ediniz.\nTarih Formati: Y.A.G \nSaat Formati: SS:DD");
		}
	}
 
 
 
 
 	// BOS KOLTUKLARI OLUSTURMA METODU
 	public static void addSeats(String bus, int journeyId) {
		
 		try {
			
		
		for(int i = 1 ; i<=24 ; i++) {
			
			String query = "INSERT INTO "+ bus +" (journy_id, seats) VALUES (?, ?)";
			
			// mysql insert statement olustur
		      PreparedStatement preparedStmt = Conn.prepareStatement(query);
		      preparedStmt.setInt (1, journeyId);
		      preparedStmt.setInt (2, i);

		      // mysql insert kodu uygula
		      preparedStmt.execute();
			
		}
		
		JOptionPane.showMessageDialog(null, "Olusturdugunuz yeni sefer "+ bus +" numarli otobusa eklendi.");
		
		}catch(SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Otobus bilgileri kontrol ediniz...");
			
			
			// Eklendigi yeni sefer veri tabanindan silme (olmayan bir otobus girildigi icin)
			try {
			// mysql connection..
		      Statement stmt = Conn.createStatement();
		     
		     // DELETE STATEMENT
			String query = "DELETE FROM journeys WHERE journy_id = " + journeyId ;
			
		      // mysql insert kodu uygula
		      stmt.execute(query);
		      
			}catch(SQLException e2) {
				
			}
			}
 	}
 	
	
}
