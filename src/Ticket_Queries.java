
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.TextField;
import java.awt.Label;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class Ticket_Queries extends JFrame
{
	public Ticket parent;
	Ticket T = new Ticket();
	public TextField Kal_Text;
	public TextField Var_Text;
	public TextField Saat_Text;
	public TextField KNO_Text;
	public TextField KNO_Durumu_Text;
	public Boolean Time_Status;
	public Boolean Bus_NoSatus;
	public TextField Price_Text;
	public JList Saat_List;
	public JList Koltuk_List;

	DefaultListModel Saat_listModel; 
	DefaultListModel Koltuk_listModel;
	public int journey_id;
	public double price;
	public Ticket_Queries()
	{

		setTitle("Bilet Sorgula");
		setBounds(100, 100, 980, 690);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		panel.setBounds(0, 0, 971, 654);
		getContentPane().add(panel);
		panel.setLayout(null);

		
		
		Saat_listModel = new DefaultListModel();

		Saat_List = new JList(Saat_listModel);
		Saat_List.setForeground(new Color(119,103,254));
		Saat_List.setLayoutOrientation(JList.VERTICAL_WRAP);
		Saat_List.setVisibleRowCount(13);
		Saat_List.setBorder(new LineBorder(new Color(0, 0, 0)));
		Saat_List.setBounds(250, 68, 172, 222);
		panel.add(Saat_List);

		Koltuk_listModel = new DefaultListModel();

		Koltuk_List = new JList(Koltuk_listModel);
		Koltuk_List.setVisibleRowCount(13);
		Koltuk_List.setForeground(new Color(119,103,254));
		Koltuk_List.setLayoutOrientation(JList.VERTICAL_WRAP);
		Koltuk_List.setBorder(new LineBorder(new Color(0, 0, 0)));
		Koltuk_List.setBounds(577, 68, 125, 222);
		panel.add(Koltuk_List);

		
		// Veri tabanindaki kalkis ve Varis noktalarina gore saatleri saat listesinde gosterme
		
		try {
			String journeys_sql = "SELECT time, date FROM journeys where departure = ? AND destination = ?";
			PreparedStatement journeys_preparedStmt = sql.Conn.prepareStatement(journeys_sql);
			journeys_preparedStmt.setString (1, Ticket.Departure_Text);
			journeys_preparedStmt.setString (2, Ticket.Arrival_Text);
		      ResultSet journeys_rs = journeys_preparedStmt.executeQuery();
			
			while(journeys_rs.next())
			{
				Saat_listModel.addElement(journeys_rs.getString("date") + "  "+ journeys_rs.getString("time") ); // Secilen Kalkis ve Varis Noktalarina gore mevcut seferlerin saatleri
			}
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		//  Saat Secme islemi
		
		// Fare ile
			Saat_List.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					
					Saat_Text.setText(Saat_List.getSelectedValue().toString());
					
					// Koltuk verilerini sifirlama
					KNO_Text.setText("");
					KNO_Durumu_Text.setText("");
					
					try { 
						// Seferin kal. var. ve satine gore fiyati  ve seferin id sini belirleme
						String journeys_sql = "SELECT journy_id, price FROM journeys where departure = ? AND destination = ? AND date = ? AND time = ?";
						PreparedStatement journeys_preparedStmt = sql.Conn.prepareStatement(journeys_sql);
						journeys_preparedStmt.setString (1, Ticket.Departure_Text);
						journeys_preparedStmt.setString (2, Ticket.Arrival_Text);
						journeys_preparedStmt.setString (3, Saat_Text.getText().substring(0, 10));
						journeys_preparedStmt.setString (4, Saat_Text.getText().substring(12));
					      ResultSet journeys_rs = journeys_preparedStmt.executeQuery();
						
						while(journeys_rs.next())
						{							
							 journey_id = journeys_rs.getInt("journy_id");
							 price = journeys_rs.getDouble("price");
						}
						
						
						// Seferin id sine gore koltuklri kotuk listesine aktarma
						Statement A35_stmt = sql.Conn.createStatement();	
						String bus_sql = "SELECT seats FROM A35 WHERE journy_id =" + journey_id; 
						ResultSet A35_rs = A35_stmt.executeQuery(bus_sql);
						
						Koltuk_listModel.clear();
						while(A35_rs.next())
						{
							Koltuk_listModel.addElement( A35_rs.getInt("seats") ); 
							
						}
						
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					
					Price_Text.setText(String.valueOf(price)); // seferin bilgilrine gore belirlenen fiyati ilgili kisimda yazdirma
					
				}
			});
			
			// klavye ile
			Saat_List.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					
					// Koltuk verilerini sifirlama
					KNO_Text.setText("");
					KNO_Durumu_Text.setText("");
					
					try { 
						Saat_Text.setText(Saat_List.getSelectedValue().toString());
						
						
						// Seferin kal. var. ve satine gore fiyati  ve seferin id sini belirleme
						String journeys_sql = "SELECT journy_id, price FROM journeys where departure = ? AND destination = ? AND date = ? AND time = ?";
						PreparedStatement journeys_preparedStmt = sql.Conn.prepareStatement(journeys_sql);
						journeys_preparedStmt.setString (1, Ticket.Departure_Text);
						journeys_preparedStmt.setString (2, Ticket.Arrival_Text);
						journeys_preparedStmt.setString (3, Saat_Text.getText().substring(0, 10));
						journeys_preparedStmt.setString (4, Saat_Text.getText().substring(12));
					      ResultSet journeys_rs = journeys_preparedStmt.executeQuery();
						
						while(journeys_rs.next())
						{							
							 journey_id = journeys_rs.getInt("journy_id");
							 price = journeys_rs.getDouble("price");
						}
						
						
						// Seferin id sine gore koltuklri kotuk listesine aktarma
						Statement A35_stmt = sql.Conn.createStatement();	
						String bus_sql = "SELECT seats FROM A35 WHERE journy_id =" + journey_id; 
						ResultSet A35_rs = A35_stmt.executeQuery(bus_sql);
						
						Koltuk_listModel.clear();
						while(A35_rs.next())
						{
							Koltuk_listModel.addElement( A35_rs.getInt("seats") ); 
							
						}
						
						}catch(Exception e)
						{
						}
					
					Price_Text.setText(String.valueOf(price)); // seferin bilgilrine gore belirlenen fiyati ilgili kisimda yazdirma
					
				}
			});
			
			
	
			
			
		//  Koltuk Setcem islemi
			
		// Fare ile	
		Koltuk_List.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				KNO_Text.setText(String.valueOf(Koltuk_List.getSelectedValue()));
				
				try {
				Statement A35_stmt = sql.Conn.createStatement();	
				String bus_sql = "SELECT free FROM A35 WHERE seats = "+ KNO_Text.getText() +" AND journy_id =" + journey_id; 
				ResultSet A35_rs = A35_stmt.executeQuery(bus_sql);
				
				while(A35_rs.next())
				{
					if (A35_rs.getBoolean("free")) KNO_Durumu_Text.setText("BOŞ");
				    else  KNO_Durumu_Text.setText("DOLU");
				}
				
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		// Klavye ile
		Koltuk_List.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				KNO_Text.setText(String.valueOf(Koltuk_List.getSelectedValue()));
				
				try {
				Statement A35_stmt = sql.Conn.createStatement();	
				String bus_sql = "SELECT free FROM A35 WHERE seats = "+ KNO_Text.getText() +" AND journy_id =" + journey_id; 
				ResultSet A35_rs = A35_stmt.executeQuery(bus_sql);
				
				while(A35_rs.next())
				{
					if (A35_rs.getBoolean("free")) KNO_Durumu_Text.setText("BOŞ");
				    else  KNO_Durumu_Text.setText("DOLU");
				}
				
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		
		
		KNO_Text = new TextField();
		KNO_Text.setEditable(false);
		KNO_Text.setForeground(new Color(119,103,254));
		KNO_Text.setFont(new Font("Arial Black", Font.PLAIN, 22));
		KNO_Text.setBackground(Color.WHITE);
		KNO_Text.setBounds(241, 497, 200, 37);
		panel.add(KNO_Text);

		KNO_Durumu_Text = new TextField();
		KNO_Durumu_Text.setEditable(false);
		KNO_Durumu_Text.setForeground(new Color(119,103,254));
		KNO_Durumu_Text.setFont(new Font("Arial Black", Font.PLAIN, 18));
		KNO_Durumu_Text.setBackground(Color.WHITE);
		KNO_Durumu_Text.setBounds(613, 383, 200, 37);
		panel.add(KNO_Durumu_Text);

		Saat_Text = new TextField();
		Saat_Text.setForeground(new Color(119,103,254));
		Saat_Text.setFont(new Font("Arial Black", Font.PLAIN, 15));
		Saat_Text.setEditable(false);
		Saat_Text.setBackground(Color.WHITE);
		Saat_Text.setBounds(613, 439, 200, 37);
		panel.add(Saat_Text);

		Button Onayla = new Button("ONAYLA");
		Onayla.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if( ! KNO_Durumu_Text.getText().equalsIgnoreCase("dolu") ) {
				try {
					
					String query = "UPDATE A35 Set free = ? WHERE journy_id = ? AND seats = ? ";
					
					// mysql insert statement olustur
				      PreparedStatement preparedStmt = sql.Conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				      preparedStmt.setBoolean (1, false);
				      preparedStmt.setInt (2, journey_id);
				      preparedStmt.setInt (3, Integer.parseInt(KNO_Text.getText()));

				      // mysql update kodu uygula
				      preparedStmt.execute();

				      JOptionPane.showMessageDialog(null,"Bilet Satın alındı.\n" + "Tarih, Saat: " + Saat_Text.getText() + "      " + "Koltuk No:" + KNO_Text.getText());
				      KNO_Durumu_Text.setText("DOLU");
				      
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Koltuk Seçiniz");
				}
				
				}else {
					JOptionPane.showMessageDialog(null,"Seçmiş olduğunuz koltuk numarası doludur");
				}
			}
		});
		
		
		
		Onayla.setFont(new Font("Arial Black", Font.PLAIN, 36));
		Onayla.setBounds(303, 562, 360, 50);
		Onayla.setForeground(new Color(119,103,254));
		Onayla.setBackground(Color.white);
		panel.add(Onayla);

		Kal_Text = new TextField();
		Kal_Text.setForeground(new Color(119,103,254));
		Kal_Text.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Kal_Text.setEditable(false);
		Kal_Text.setBackground(Color.WHITE);
		Kal_Text.setBounds(242, 383, 200, 37);
		panel.add(Kal_Text);

		Kal_Text.setText(Ticket.Departure_Text); // Ticket formundaki comboboxtan veri cekme

		Var_Text = new TextField();
		Var_Text.setForeground(new Color(119,103,254));
		Var_Text.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Var_Text.setEditable(false);
		Var_Text.setBackground(Color.WHITE);
		Var_Text.setBounds(241, 447, 200, 37);
		panel.add(Var_Text);

		Var_Text.setText(Ticket.Arrival_Text);

		Price_Text = new TextField();
		Price_Text.setForeground(new Color(119,103,254));
		Price_Text.setFont(new Font("Arial Black", Font.BOLD, 22));
		Price_Text.setEditable(false);
		Price_Text.setBackground(Color.WHITE);
		Price_Text.setBounds(613, 497, 200, 37);
		panel.add(Price_Text);
		Image img2 = new ImageIcon(this.getClass().getResource("images/arkaplan.png")).getImage();
		
		JLabel lblNewLabel_1 = new JLabel("Kalkış:");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(174, 393, 61, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Varış:");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(184, 457, 51, 27);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Koltuk Numarası:");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(87, 507, 149, 27);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Koltuk Durumu:");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(477, 393, 149, 27);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Saat:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_5.setBounds(563, 450, 62, 27);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fiyat:");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(560, 510, 56, 16);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Bilet Bilgileri");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 29));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(420, 305, 171, 65);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Otobüs");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(599, 37, 85, 28);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("   Tarih         Saat");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(244, 37, 134, 32);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-1, 1, 971, 654);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(img2));

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent e)
			{
				T.setVisible(true);
			}
		});
	}
}
