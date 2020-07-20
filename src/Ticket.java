import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.text.*;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;

import javax.swing.Timer;

import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

//================================================================================================================
public class Ticket extends JFrame {
	public JFrame frmOtobsBiletSorgulama;
	public JFrame frmBiletSorgulama;
	private JTextField Name;
	private JTextField No;
	private JTextField Surname;
	private JTextField EMail;
	private JTextField Phone;
	private JTextField Date_T;
	private JTextField Time_T;
	public static String Departure_Text;
	public static String Arrival_Text;

	public Ticket() {
		setResizable(false);

		initialize();

	}

	private void initialize() {

		addWindowListener(new WindowAdapter() {

			@Override

			public void windowActivated(WindowEvent arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				Date tim = new Date();
				Time_T.setText(String.valueOf(sdf.format(tim)));

				LocalDate ld = LocalDate.now();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				Date_T.setText(ld.format(format));

				try {
					Statement users_stmt = sql.Conn.createStatement();
					String users_sql = "SELECT user_id, name, last_name, email, tel FROM users WHERE user_id= "
							+ anasayfa.user_id;
					ResultSet users_rs = users_stmt.executeQuery(users_sql);

					while (users_rs.next()) {
						No.setText(users_rs.getString("user_id"));
						Name.setText(users_rs.getString("name"));
						Surname.setText(users_rs.getString("last_name"));
						EMail.setText(users_rs.getString("email"));
						Phone.setText(users_rs.getString("tel"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		setBackground(SystemColor.activeCaption);
		setForeground(SystemColor.activeCaption);
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Otob\u00FCs Bilet Sorgulama");
		setBounds(100, 100, 978, 689);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("images/ticketbackground.png")).getImage();
		Image img1 = new ImageIcon(this.getClass().getResource("images/ticketbackground2.png")).getImage();

		JPanel User_Panel = new JPanel();
		User_Panel.setBounds(217, 80, 574, 496);
		getContentPane().add(User_Panel);
		User_Panel.setBackground(Color.WHITE);
		User_Panel.setToolTipText("");
		User_Panel.setLayout(null);

		Name = new JTextField();
		Name.setForeground(Color.WHITE);
		Name.setFont(new Font("Tahoma", Font.BOLD, 12));
		Name.setEditable(false);
		Name.setOpaque(false); // jfield� �effaf yapt�m
		Name.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));// Altina cizgi cektim
		Name.setBounds(179, 87, 245, 32);
		User_Panel.add(Name);
		Name.setColumns(10);

		JLabel Name_Bar = new JLabel("Ad\u0131:");
		Name_Bar.setForeground(SystemColor.text);
		Name_Bar.setFont(new Font("Tahoma", Font.BOLD, 14));
		Name_Bar.setBounds(138, 102, 42, 18);
		User_Panel.add(Name_Bar);

		JLabel Soyad_Bar = new JLabel("Soyad\u0131:");
		Soyad_Bar.setForeground(SystemColor.text);
		Soyad_Bar.setFont(new Font("Tahoma", Font.BOLD, 14));
		Soyad_Bar.setBounds(120, 144, 51, 20);
		User_Panel.add(Soyad_Bar);

		JLabel No_Bar = new JLabel("No:");
		No_Bar.setForeground(SystemColor.text);
		No_Bar.setFont(new Font("Tahoma", Font.BOLD, 14));
		No_Bar.setBounds(138, 55, 70, 20);
		User_Panel.add(No_Bar);

		JLabel EMail_Bar = new JLabel("E-Mail:");
		EMail_Bar.setForeground(SystemColor.text);
		EMail_Bar.setFont(new Font("Tahoma", Font.BOLD, 14));
		EMail_Bar.setBounds(127, 183, 70, 20);
		User_Panel.add(EMail_Bar);

		JLabel Tel_Bar = new JLabel("Telefon:");
		Tel_Bar.setForeground(SystemColor.text);
		Tel_Bar.setFont(new Font("Tahoma", Font.BOLD, 14));
		Tel_Bar.setBounds(123, 228, 70, 20);
		User_Panel.add(Tel_Bar);

		Surname = new JTextField();
		Surname.setForeground(Color.WHITE);
		Surname.setFont(new Font("Tahoma", Font.BOLD, 12));
		Surname.setEditable(false);
		Surname.setColumns(10);
		Surname.setOpaque(false); // jfield� �effaf yapt�m
		Surname.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));// Altina cizgi cektim
		Surname.setBounds(179, 130, 245, 32);
		User_Panel.add(Surname);

		EMail = new JTextField();
		EMail.setForeground(Color.WHITE);
		EMail.setFont(new Font("Tahoma", Font.BOLD, 12));
		EMail.setEditable(false);
		EMail.setColumns(10);
		EMail.setOpaque(false); // jfield� �effaf yapt�m
		EMail.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));// Altina cizgi cektim
		EMail.setBounds(179, 173, 245, 32);
		User_Panel.add(EMail);

		Phone = new JTextField();
		Phone.setForeground(Color.WHITE);
		Phone.setFont(new Font("Tahoma", Font.BOLD, 12));
		Phone.setEditable(false);
		Phone.setColumns(10);
		Phone.setOpaque(false); // jfield� �effaf yapt�m
		Phone.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));// Altina cizgi cektim
		Phone.setBounds(179, 218, 245, 32);
		User_Panel.add(Phone);

		No = new JTextField();
		No.setOpaque(false); // jfield� �effaf yapt�m
		No.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));// Altina cizgi cektim
		No.setForeground(Color.WHITE);
		No.setBounds(179, 42, 245, 32);
		User_Panel.add(No);
		No.setFont(new Font("Tahoma", Font.BOLD, 12));
		No.setEditable(false);
		No.setColumns(10);

		Time_T = new JTextField();
		Time_T.setBackground(Color.WHITE);
		Time_T.setBounds(493, 19, 86, 18);
		User_Panel.add(Time_T);
		Time_T.setHorizontalAlignment(SwingConstants.CENTER);
		Time_T.setText("ZAMAN");
		Time_T.setEditable(false);
		Time_T.setColumns(10);

		Date_T = new JTextField();
		Date_T.setBackground(Color.WHITE);
		Date_T.setBounds(493, 0, 86, 20);
		User_Panel.add(Date_T);
		Date_T.setHorizontalAlignment(SwingConstants.CENTER);
		Date_T.setText("TARIH");
		Date_T.setEditable(false);
		Date_T.setColumns(10);

		JLabel Departure_Lab = new JLabel("Kalk\u0131\u015F:");
		Departure_Lab.setForeground(SystemColor.text);
		Departure_Lab.setBounds(127, 292, 70, 20);
		User_Panel.add(Departure_Lab);
		Departure_Lab.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox Departure = new JComboBox();
		Departure.setBackground(Color.white);
		Departure.setForeground(SystemColor.activeCaptionText);
		Departure.setBounds(179, 288, 245, 32);
		User_Panel.add(Departure);

		JLabel Arrival_Lab = new JLabel("Var\u0131\u015F:");
		Arrival_Lab.setForeground(SystemColor.text);
		Arrival_Lab.setBounds(129, 346, 51, 20);
		User_Panel.add(Arrival_Lab);
		Arrival_Lab.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox Arrival = new JComboBox();
		Arrival.setBackground(new Color(255, 255, 255));
		Arrival.setForeground(SystemColor.activeCaptionText);
		Arrival.setBounds(179, 342, 245, 32);
		User_Panel.add(Arrival);

		JButton Sorgula = new JButton("SORGULA");
		Sorgula.setForeground(new Color(119, 103, 254));
		Sorgula.setFont(new Font("Tahoma", Font.BOLD, 15));
		Sorgula.setBounds(179, 411, 245, 52);
		User_Panel.add(Sorgula);
		Sorgula.setBackground(Color.white);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 574, 500);
		User_Panel.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(img1));
		Sorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Departure_Text = String.valueOf(Departure.getSelectedItem());
				Arrival_Text = String.valueOf(Arrival.getSelectedItem());
				Ticket_Queries TQ = new Ticket_Queries();

				// TQ.Kal_Text.setText(Departure_Text);
				// TQ.Var_Text.setText(Arrival_Text);
				TQ.setVisible(true);

				setVisible(false);
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 972, 654);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(img));

		// Veri tabanindaki kalkis ve Varis noktalari Combobox'a aktrma
		try {
			Statement journeys_stmt = sql.Conn.createStatement();
			String journeys_sql = "SELECT * FROM journeys ";
			ResultSet journeys_rs = journeys_stmt.executeQuery(journeys_sql);

			while (journeys_rs.next()) {
				int checkDepartue = 0;
				int checkDestination = 0;

				for (int i = 0; i < Departure.getItemCount(); i++) {

					if (Departure.getItemAt(i).toString().equalsIgnoreCase(journeys_rs.getString("departure"))) {
						checkDepartue++;
					}
				}

				for (int i = 0; i < Arrival.getItemCount(); i++) {
					if (Arrival.getItemAt(i).toString().equalsIgnoreCase(journeys_rs.getString("destination"))) {
						checkDestination++;
					}
				}

				if (checkDepartue == 0) {

					Departure.addItem(journeys_rs.getString("departure"));// Veri Tabanindaki kalkis noktalari set etme

				}

				if (checkDestination == 0) {

					Arrival.addItem(journeys_rs.getString("destination"));// Veri Tabanindaki Varis noktalari set etme

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
