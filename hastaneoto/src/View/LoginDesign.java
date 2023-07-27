package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;
import java.awt.SystemColor;
import java.awt.Color;
public class LoginDesign extends JFrame {

	private JPanel k_pane;
	private JTextField fld_hastatcno;
	private JTextField fld_hastasifre;
	private JTextField fld_doctortc;
	private JPasswordField fld_doctorpassword;
    private DbConnection conn= new DbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDesign frame = new LoginDesign();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginDesign() {
		setBackground(UIManager.getColor("inactiveCaptionBorder"));
		setResizable(false);
		setTitle("Hastane Otomasyonum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 455);
		k_pane = new JPanel();
		k_pane.setBackground(new Color(153, 204, 255));
		k_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(k_pane);
		k_pane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Hastane Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setBounds(78, 120, 398, 31);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		k_pane.add(lblNewLabel);
		
		JTabbedPane k_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		k_tabbedPane.setBounds(10, 161, 506, 254);
		k_pane.add(k_tabbedPane);
		
		JPanel k_hastapanel = new JPanel();
		k_hastapanel.setBackground(new Color(153, 204, 255));
		k_tabbedPane.addTab("Hasta Giriþi", null, k_hastapanel, null);
		k_hastapanel.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaran\u0131z:");
		lblTcNumaranz.setBounds(28, 20, 198, 29);
		lblTcNumaranz.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		k_hastapanel.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		lblifre.setBounds(145, 103, 66, 29);
		k_hastapanel.add(lblifre);
		
		fld_hastatcno = new JTextField();
		fld_hastatcno.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_hastatcno.setBounds(236, 20, 208, 29);
		k_hastapanel.add(fld_hastatcno);
		fld_hastatcno.setColumns(10);
		
		fld_hastasifre = new JTextField();
		fld_hastasifre.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_hastasifre.setBounds(236, 103, 208, 29);
		k_hastapanel.add(fld_hastasifre);
		fld_hastasifre.setColumns(10);
		
		JButton btnkayit = new JButton("Kay\u0131t Ol");
		btnkayit.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
		btnkayit.setBounds(39, 163, 187, 44);
		k_hastapanel.add(btnkayit);
		
		JButton btnhastagiris = new JButton("Giri\u015F Yap");
		btnhastagiris.setFont(new Font("Segoe WP Black", Font.BOLD, 17));
		btnhastagiris.setBounds(258, 163, 187, 44);
		k_hastapanel.add(btnhastagiris);
		
		JPanel k_personellogin = new JPanel();
		k_personellogin.setBackground(new Color(153, 204, 255));
		k_tabbedPane.addTab("Doktor Giriþi", null, k_personellogin, null);
		k_personellogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaran\u0131z:");
		lblTcNumaranz_1.setBounds(29, 34, 172, 29);
		lblTcNumaranz_1.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		k_personellogin.add(lblTcNumaranz_1);
		
		JLabel lblifre_1 = new JLabel("\u015Eifre:");
		lblifre_1.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		lblifre_1.setBounds(143, 95, 66, 29);
		k_personellogin.add(lblifre_1);
		
		fld_doctortc = new JTextField();
		fld_doctortc.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_doctortc.setColumns(10);
		fld_doctortc.setBounds(221, 34, 208, 29);
		k_personellogin.add(fld_doctortc);
		
		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctortc.getText().length() == 0 || fld_doctorpassword.getText().length() == 0) {
					Helper.showMsg("Lütfen boþ alanlarý doldurunuz");
				}else {
					
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("Select * FROM User");
						while(rs.next() ) {
							if(fld_doctortc.getText().equals(rs.getString("tcno")) && fld_doctorpassword.getText().equals(rs.getString("password")));
							Bashekim bhekim = new Bashekim();
							bhekim.setId(rs.getInt("id"));
							bhekim.setPassword("password");
							bhekim.setTcno(rs.getString("tcno"));
							bhekim.setName(rs.getString("name"));
							bhekim.setType(rs.getString("type"));
		                    BashekimDesign bDesign = new BashekimDesign(bhekim);
		                    bDesign.setVisible(true);
		                    dispose();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					}
			}
		});
		btn_doktorLogin.setFont(new Font("Segoe WP Black", Font.BOLD, 17));
		btn_doktorLogin.setBounds(131, 156, 208, 44);
		k_personellogin.add(btn_doktorLogin);
		
		fld_doctorpassword = new JPasswordField();
		fld_doctorpassword.setBounds(219, 100, 206, 29);
		k_personellogin.add(fld_doctorpassword);
	}
}
