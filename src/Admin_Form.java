import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Admin_Form extends JFrame {

	private JPanel contentPane;
	private JTextField dateTextField;
	private JTextField timeTextField;
	private JTextField busTextField;
	private JTextField priceTextField;
	private JLabel timeLable;
	private JLabel busLable;
	private JLabel deprtureLable;
	private JLabel lblNereye;
	private JLabel priceLable;
	private JLabel dateLable;
	private JButton btnGeri;
	private JComboBox dep_cbox;
	private JComboBox des_cbox;

	/**
	 * Create the frame.
	 */
	public Admin_Form() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Admin_Form.class.getResource("/images/icon.png")));
		setTitle("Bus Reservation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(448, 0, 525, 657);
		contentPane.add(panel);
		panel.setBackground(Color.white);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(12, 0, 506, 657);
		panel.add(lblNewLabel_1);
		Image img = new ImageIcon(this.getClass().getResource("images/administrator.png")).getImage();

		lblNewLabel_1.setIcon(new ImageIcon(img));
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		dateTextField.setBounds(97, 271, 311, 47);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);
		
		timeTextField = new JTextField();
		timeTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		timeTextField.setColumns(10);
		timeTextField.setBounds(97, 345, 311, 47);
		contentPane.add(timeTextField);
		
		busTextField = new JTextField();
		busTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		busTextField.setColumns(10);
		busTextField.setBounds(97, 421, 311, 47);
		contentPane.add(busTextField);
		
		dep_cbox = new JComboBox();
		dep_cbox.setFont(new Font("Tahoma", Font.BOLD, 13));
		dep_cbox.setModel(new DefaultComboBoxModel(new String[] {"Adana", "Ad\u0131yaman", "Afyon", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingül", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kilis", "Kırıkkale", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "\u015Eanl\u0131urfa", "Siirt", "Sinop", "Sivas", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uçak", "Van", "Yalova", "Yozgat", "Zonguldak"}));
		dep_cbox.setSelectedIndex(38);
		dep_cbox.setBounds(97, 49, 311, 47);
		contentPane.add(dep_cbox);
		
		des_cbox = new JComboBox();
		des_cbox.setFont(new Font("Tahoma", Font.BOLD, 13));
		des_cbox.setBounds(97, 124, 311, 47);
		des_cbox.setModel(new DefaultComboBoxModel(new String[] {"Adana", "Ad\u0131yaman", "Afyon", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingül", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu", "Kayseri", "Kilis", "Kırıkkale", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "\u015Eanl\u0131urfa", "Siirt", "Sinop", "Sivas", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uçak", "Van", "Yalova", "Yozgat", "Zonguldak"}));
		des_cbox.setSelectedIndex(12);
		contentPane.add(des_cbox);
		
		priceTextField = new JTextField();
		priceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if( ! validation.validate_numeric_type( priceTextField.getText() ) ) {

					int price_length = priceTextField.getText().length();
					String price_text = priceTextField.getText();

					JOptionPane.showMessageDialog(null, "Sayı Giriniz..");

					// Girilen rakamlari silme
					priceTextField.setText(price_text.substring(0, price_length - 1));

				}
			}
		});
		priceTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		priceTextField.setColumns(10);
		
		priceTextField.setBounds(97, 195, 311, 47);
		contentPane.add(priceTextField);
		
		JButton btnNewButton = new JButton("SEFER EKLE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setForeground(new Color(65, 105, 225));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				sql.addjourney(dateTextField.getText(), timeTextField.getText(),
						      busTextField.getText(), dep_cbox.getSelectedItem().toString(), 
						      des_cbox.getSelectedItem().toString(), Double.parseDouble(priceTextField.getText()));
				
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Girdiginiz bilgileri kontrol ediniz...");
				}
				
			}
		});
		btnNewButton.setBounds(64, 493, 357, 80);
		contentPane.add(btnNewButton);
		
		timeLable = new JLabel("SAAT:");
		timeLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeLable.setForeground(Color.WHITE);
		timeLable.setBounds(48, 371, 55, 14);
		contentPane.add(timeLable);
		
		busLable = new JLabel("OTOBÜS:");
		busLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		busLable.setForeground(Color.WHITE);
		busLable.setBounds(26, 439, 68, 19);
		contentPane.add(busLable);
		
		deprtureLable = new JLabel("NEREDEN:");
		deprtureLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		deprtureLable.setForeground(Color.WHITE);
		deprtureLable.setBounds(15, 75, 86, 14);
		contentPane.add(deprtureLable);
		
		lblNereye = new JLabel("NEREYE:");
		lblNereye.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNereye.setForeground(Color.WHITE);
		lblNereye.setBounds(26, 150, 72, 14);
		contentPane.add(lblNereye);
		
		priceLable = new JLabel("FİYAT:");
		priceLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		priceLable.setForeground(Color.WHITE);
		priceLable.setBounds(39, 222, 56, 14);
		contentPane.add(priceLable);
		
		dateLable = new JLabel("TARIH:");
		dateLable.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateLable.setForeground(Color.WHITE);
		dateLable.setBounds(38, 298, 60, 14);
		contentPane.add(dateLable);
		
		btnGeri = new JButton("GERİ");
		btnGeri.setForeground(new Color(65, 105, 225));
		btnGeri.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGeri.setBackground(Color.WHITE);
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				anasayfa login = new anasayfa();
				login.setVisible(true);
				
			}
		});
		btnGeri.setBounds(97, 585, 281, 31);
		contentPane.add(btnGeri);
		//LABEL ile resim aktardim programima	arka plan 	
		Image img2 = new ImageIcon(this.getClass().getResource("images/arkaplan.png")).getImage();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 973, 657);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(img2));
		
		
	}
}
