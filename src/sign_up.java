import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class sign_up extends JFrame {

	public sign_up() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(sign_up.class.getResource("/images/icon.png")));
		setTitle("Bus Reservation");
		setResizable(false);
		//sign_up_frame();
	}

	private JTextField name_textField;
	private JTextField last_name_textField;
	private JTextField email_textField;
	private JTextField tel_textField;
	private JPasswordField password_Field;
	private JLabel lblNewLabel_4;
	private JPasswordField repeat_password_Field;

	private String name;
	private String last_name;
	private String email;
	private String tel;
	private String password, re_password;



	public void sign_up_frame() {

		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(100, 100, 974, 688);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		



		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(0, 0, 543, 657);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblNewLabel_4 = new JLabel("Ad");
		lblNewLabel_4.setBounds(80, 25, 127, 39);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));

		name_textField = new JTextField();
		name_textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		name_textField.setBounds(80, 55, 353, 39);
		name_textField.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		name_textField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		panel.add(name_textField);
		name_textField.setBackground(SystemColor.inactiveCaptionBorder);

		// Ad tipi kontrol metodu 
		name_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if( ! validation.validate_string_type( name_textField.getText() )) {

					int name_length = name_textField.getText().length();
					String name_text = name_textField.getText();

					JOptionPane.showMessageDialog(null, "Turkce karkterlerden farkli bir sey girmeyiniz..");

					// Girilen rakamlari silme
					name_textField.setText(name_text.substring(0,name_length - 1));

				}

			}
		});
		name_textField.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Soyad");
		lblNewLabel_5.setBounds(80, 120, 67, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));

		last_name_textField = new JTextField();
		last_name_textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		last_name_textField.setBounds(80, 138, 353, 39);
		last_name_textField.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		last_name_textField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		panel.add(last_name_textField);
		last_name_textField.setBackground(SystemColor.inactiveCaptionBorder);

		// Ad tipi kontrol metodu 
		last_name_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(! validation.validate_string_type( last_name_textField.getText() )) {

					int lname_length = last_name_textField.getText().length();
					String lname_text = last_name_textField.getText();

					JOptionPane.showMessageDialog(null, "Turkce karkterlerden farkli bir sey girmeyiniz..");

					// Girilen rakamlari silme
					last_name_textField.setText(lname_text.substring(0,lname_length - 1));
				}

			}
		});
		last_name_textField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setBounds(80, 209, 67, 16);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));

		email_textField = new JTextField();
		email_textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		email_textField.setBounds(80, 225, 353, 39);
		panel.add(email_textField);
		email_textField.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		email_textField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		email_textField.setBackground(SystemColor.inactiveCaptionBorder);
		email_textField.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Telefon");
		lblNewLabel_7.setBounds(80, 297, 67, 16);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));

		tel_textField = new JTextField();
		tel_textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		tel_textField.setBounds(80, 315, 353, 39);
		panel.add(tel_textField);
		tel_textField.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		tel_textField.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		tel_textField.setBackground(SystemColor.inactiveCaptionBorder);

		tel_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if( ! validation.validate_numeric_type( tel_textField.getText() ) ) {

					int tel_length = tel_textField.getText().length();
					String tel_text = tel_textField.getText();

					JOptionPane.showMessageDialog(null, "Sayi Giriniz..");

					// Girilen rakamlari silme
					tel_textField.setText(tel_text.substring(0,tel_length - 1));

				}
			}
		});
		tel_textField.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("\u015Eifre");
		lblNewLabel_9.setBounds(80, 375, 67, 23);
		panel.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));

		password_Field = new JPasswordField();
		password_Field.setFont(new Font("Tahoma", Font.BOLD, 13));
		password_Field.setBounds(80, 396, 353, 39);
		panel.add(password_Field);
		password_Field.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		password_Field.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		password_Field.setBackground(SystemColor.inactiveCaptionBorder);

		// Kayit ol Buttonu
		JButton btnNewButton = new JButton("Kay\u0131t Ol");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBounds(80, 536, 353, 73);
		panel.add(btnNewButton);
	    btnNewButton.setBackground(new Color(119,103,254));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// kullanici tarafindan girilen degerleri degiskenlere set etme..
				name = name_textField.getText().trim();
				last_name = last_name_textField.getText().trim();
				email = email_textField.getText().trim();
				tel = tel_textField.getText().trim();
				password = password_Field.getText().trim();
				re_password = repeat_password_Field.getText().trim();

				//kullanicinin emaili zaten var ise hata mesaji goster,
				// mevcut degil ise yeni kullanicinin bilgileri veri tabanina kaydet 

				//Validation code..
				if( ! validation.validate_name_lastName( name, last_name ) ) {

					JOptionPane.showMessageDialog(null,"Adinizi ve Soyadinizi giriniz.");

				}else if( ! validation.validate_email(email)) {

					JOptionPane.showMessageDialog(null,"E-postaniz Hatalidir Lutfen Tekrar Deneyiniz.");

				}else  if(!validation.validate_password_length( password_Field.getText() ) ) {

					JOptionPane.showMessageDialog(null, "Sifreniz en az 4 karakterden olusmsi gerekir");

				}else if( ! validation.validate_password(  password , re_password)){

					JOptionPane.showMessageDialog(null,"girdiginiz sifreler esit degildir, Lutfen Tekrar Deneyiniz.");

				}else if( validation.user_control(email) ) {

					JOptionPane.showMessageDialog(null,"Girdiginiz Email Zaten Mevcut."); 

				}else {

					add_User(name, last_name, email, tel, password);


					JOptionPane.showMessageDialog(null,"Kaydedildi");

					dispose();
					anasayfa login = new anasayfa();
					login.setVisible(true);

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton back_button = new JButton("Geri");
		back_button.setForeground(SystemColor.text);
		back_button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		back_button.setBounds(80, 612, 353, 32);
		back_button.setBackground(new Color(119,103,254));
		panel.add(back_button);

		JLabel lblNewLabel = new JLabel("Å�ifreyi Tekrar Girin");
		lblNewLabel.setBounds(80, 455, 127, 16);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		repeat_password_Field = new JPasswordField();
		repeat_password_Field.setFont(new Font("Tahoma", Font.BOLD, 13));
		repeat_password_Field.setBounds(80, 473, 353, 39);
		repeat_password_Field.setOpaque(false);// TEXTFIELDLER  GORUNMEYECEK
		repeat_password_Field.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(119,103,254)));// TEXTFİELD ALTINA ÇİZGİ ÇEKECEK
		panel.add(repeat_password_Field);
		repeat_password_Field.setBackground(SystemColor.inactiveCaptionBorder);

		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				anasayfa login = new anasayfa();
				login.setVisible(true);				
				setVisible(false);
			}
		});
		
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(593, 29, 333, 410);
		getContentPane().add(lblNewLabel_2);
		
		{//LABEL ile resim aktardim programima	arka plan 	
			Image img2 = new ImageIcon(this.getClass().getResource("images/sign_up_simge.png")).getImage();
			lblNewLabel_2.setIcon(new ImageIcon(img2));
			}
				
				JLabel lblNewLabel_3 = new JLabel("HO\u015EGELD\u0130N\u0130Z;");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 34));
				lblNewLabel_3.setForeground(SystemColor.text);
				lblNewLabel_3.setBounds(632, 466, 263, 73);
				getContentPane().add(lblNewLabel_3);
						
						JLabel lblNewLabel_8 = new JLabel("Kay\u0131t olmak i\u015Fte bu kadar kolay!");
						lblNewLabel_8.setForeground(SystemColor.text);
						lblNewLabel_8.setBounds(679, 613, 247, 31);
						getContentPane().add(lblNewLabel_8);
						
								JLabel lblNewLabel_1 = new JLabel("");
								lblNewLabel_1.setBounds(0, 0, 973, 657);
								getContentPane().add(lblNewLabel_1);
								
								{//LABEL ile resim aktardim programima	arka plan 	
								Image img4 = new ImageIcon(this.getClass().getResource("images/arkaplan.png")).getImage();
								lblNewLabel_1.setIcon(new ImageIcon(img4));}


	}


	// Yeni kullanici Ekle metodu

	public void add_User(  String name, String last_name, String email, String tel, String password){

		try {
			String query = " insert into users (name, last_name, email, Tel, password, type)"
					+ " values (?, ?, ?, ?, ?, 'user')";


			// mysql insert statement olustur

			PreparedStatement preparedStmt = sql.Conn.prepareStatement(query);
			preparedStmt.setString (1, name);
			preparedStmt.setString (2, last_name);
			preparedStmt.setString   (3, email);
			preparedStmt.setString(4, tel);
			preparedStmt.setString    (5, password);

			// mysql insert kodu uygula
			preparedStmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}




}