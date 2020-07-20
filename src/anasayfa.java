import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;


public class anasayfa extends JFrame {

	
	private JTextField  email_textField;
	private JLabel lblNewLabel_2;
	private JPasswordField password_Field;
	
	static  int user_id;
	private String email;
	private String password;
	sql sql = new sql();
	Ticket Ticket = new Ticket();
	Admin_Form admin = new Admin_Form();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					anasayfa home = new anasayfa();
					home.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public anasayfa() {
		setTitle("Bus Reservation");
		setIconImage(Toolkit.getDefaultToolkit().getImage(anasayfa.class.getResource("/images/icon.png")));
		setResizable(false);
		login_frame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void login_frame() {
		
		getContentPane().setBackground(new Color(224, 242, 241));
		setBounds(100, 100, 974, 687);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(248, 86, 472, 472);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
				
		JLabel lblNewLabel_1 = new JLabel("\u015eifre:");
		lblNewLabel_1.setBounds(83, 281, 125, 43);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		{// icon ekledim ï¿½ifre Alanï¿½na
		Image img1 = new ImageIcon(this.getClass().getResource("images/closed.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img1));}
		
	
	
		
		email_textField = new JTextField();
		email_textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		email_textField.setBounds(83, 236, 307, 43);
		panel.add(email_textField);
		email_textField.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		email_textField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFÝELD ALTINA ÇÝZGÝ ÇEKECEK
		email_textField.setBackground(SystemColor.inactiveCaptionBorder);
		email_textField.setColumns(10);
		
		
		
		password_Field = new JPasswordField();
		password_Field.setFont(new Font("Tahoma", Font.BOLD, 13));
		password_Field.setBounds(83, 318, 307, 43);
		password_Field.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		password_Field.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFÝELD ALTINA ÇÝZGÝ ÇEKECEK
		panel.add(password_Field);
		password_Field.setBackground(SystemColor.inactiveCaptionBorder);
		
		JButton login_button = new JButton("Giri\u015f Yap");
		login_button.setForeground(SystemColor.textHighlightText);
		login_button.setBounds(83, 372, 307, 60);
		panel.add(login_button);
		login_button.setBackground(new Color(119,103,254));
		login_button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					// kullanici tarafindan girilen degerleri degiskenlere set etme..
					
					email = email_textField.getText().trim();
					password = password_Field.getText().trim();
					
					// Validtion code..
					if( ! validation.validate_email(email)) {
						
						JOptionPane.showMessageDialog(null,"E-postan\u0131z Hatal\u0131d\u0131r L\u00fctfen Tekrar Deneyiniz.");
					
					}else  if( ! validation.validate_password_length( password_Field.getText() ) ) {
						
						JOptionPane.showMessageDialog(null, "\u015eifreniz en az 4 karakterden olusms\u0131 gerekir");
						
					}else if( ! validation.user_control(email) ) {

						JOptionPane.showMessageDialog(null,"Girdiginiz email kay\u0131tl\u0131 degildir."); 
						
						}else {
							
							try {
								// veri tabanina kullanicilrin verilrine erisim
									
								String query = "SELECT * FROM users WHERE email = ?";
								
								 PreparedStatement preparedStmt = sql.Conn.prepareStatement(query);
							      preparedStmt.setString (1, email);
							      ResultSet result = preparedStmt.executeQuery();
								
								while(result.next()) {
								if( password.equalsIgnoreCase(result.getString("password")) ) {
									
									user_id = Integer.parseInt(result.getString("user_id"));
									String user_type = result.getString("type");
									dispose();
									if(user_type.equalsIgnoreCase("admin")) {
										
										admin.setVisible(true);
										
									}else if(user_type.equalsIgnoreCase("user")){
										
									Ticket.setVisible(true);
									
									}
									
									break;
									
								}else {
									
									JOptionPane.showMessageDialog(null,"Girdiginiz sifre Yanlstir..");
									break;							
								}
								}
								
							}catch(Exception e) {
								e.printStackTrace();
							}					
							
						}
				} //action button bittigi yer
					
			}); // addActionListener bittigi yer
		
			login_button.setFont(new Font("Tahoma", Font.PLAIN, 24));
			
			JButton sign_up_button = new JButton("Kay\u0131tl\u0131 de\u011Fil misiniz? Kay\u0131t Olu\u015Fturunuz..");
			sign_up_button.setBackground(SystemColor.text);
			sign_up_button.setBounds(83, 437, 307, 32);
			panel.add(sign_up_button);
			sign_up_button.setForeground(new Color(119,103,254));
			sign_up_button.setBorderPainted(false);
			sign_up_button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				
					sign_up sign_up = new sign_up();
					sign_up.setVisible(true);
				 	sign_up.sign_up_frame();
				 	
				 	dispose();
					
				}
			});
			sign_up_button.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			JLabel lblNewLabel = new JLabel("Email:");
			lblNewLabel.setBounds(83, 203, 135, 43);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

			{// icon ekledim e mail labeline
			Image img = new ImageIcon(this.getClass().getResource("images/communications.png")).getImage();
			lblNewLabel.setIcon(new ImageIcon(img));}
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setBounds(168, 62, 135, 128);
			panel.add(lblNewLabel_3);
			
			Image img4 = new ImageIcon(this.getClass().getResource("images/buss.png")).getImage();
			lblNewLabel_3.setIcon(new ImageIcon(img4));
			
			JLabel lblNewLabel_4 = new JLabel("Oturum A\u00E7");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 31));
			lblNewLabel_4.setBounds(155, 13, 198, 51);
			panel.add(lblNewLabel_4);
			
			
		
				
		
		//git iï¿½in
		
		
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 973, 657);
		getContentPane().add(lblNewLabel_2);
		
		{//LABEL ile resim aktardim programima	arka plan 	
		Image img2 = new ImageIcon(this.getClass().getResource("images/arkaplan.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img2));
		}
		
	}
}
