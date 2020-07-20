import java.sql.ResultSet;
import java.sql.Statement;

public class validation {
	
	// girilen emaili kontrol etme metodu
		
				public static boolean validate_email(String email) {
		
					String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
					if (email.matches(regex)) return true;
					else return false;
					
					}
								
	
				// kullanici kontrol metodu
				public static boolean user_control(String email){
					
					try {
						
					Statement stmt = sql.Conn.createStatement();	
					ResultSet result = stmt.executeQuery("SELECT * FROM users");
					
					
					while(result.next()) {
						
						if(result.getString("email").equalsIgnoreCase(email) ) return true;	
					}
					
					}catch(Exception e) {
						e.printStackTrace();
						
					}
						return false;
				}
				
				// sifrelerin esleme kontrol metodu
				public static boolean validate_password( String password1 , String password2){

					if( ! password1.equals(password2) ) return false;
					
					else return true;
					
				}
				
				
				// sifrelerin uzunlugu kontrol metodu
				public static boolean validate_password_length( String password ){
					
				 if(password.length() < 4) return false;
				 
				 else return true;
				 
				}
				
				// AD SOYAD kontrol metodu
				public static boolean validate_name_lastName( String name, String lastName ){
					
				 if(name.isEmpty() || lastName.isEmpty()) return false;
				 
				 else return true;
				 
				}
				
				// AD SOYAD String tipi kontrol metodu
				public static boolean validate_string_type( String str ){
					
					if(!str.matches("[a-zA-Z/\u00c7/\u00e7/\u015e/\u015f/\u0130/\u011e/\u011f/\u0131/\u00d6/\u00f6/\u00dc/\u00fc]*")) return false;
					else return true;
				 
				}
				

				// Telefon Numeric Tipi kontrol Metodu
				public static boolean validate_numeric_type( String telefon ){
					
				if(!telefon.isEmpty()) {
					
					try{
						
					    double i = Double.parseDouble(telefon);
					    
					} catch(NumberFormatException ex){ 
						
						   return false;
					}
					}
				
				return true;
				
				}
				
				
	
}
