package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;

import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Helper.*;

public class BashekimDesign extends JFrame {
	
    static Bashekim bashekim = new Bashekim();
	private JPanel k_pane;
	private JTextField fld_aadsoyad;
	private JTextField fld_ttcno;
	private JTextField fld_ssifre;
	private JTable table_doktor;
    private DefaultTableModel doctorModel=null;
    private Object[] doctorData = null;
    private JTextField fld_kullaniciiid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimDesign frame = new BashekimDesign(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimDesign(Bashekim bashekim) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i=0; i < bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		setResizable(false);
		setTitle("Hastane otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 594);
		k_pane = new JPanel();
		k_pane.setBackground(new Color(153, 204, 255));
		k_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(k_pane);
		k_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n" + bashekim.getName());
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
		lblNewLabel.setBounds(22, 22, 349, 27);
		k_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton.setBounds(647, 24, 109, 27);
		k_pane.add(btnNewButton);
		
		JTabbedPane k_tabpane = new JTabbedPane(JTabbedPane.TOP);
		k_tabpane.setBounds(22, 90, 734, 466);
		k_tabpane.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		k_pane.add(k_tabpane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		k_tabpane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad");
		lblAdSoyad.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
		lblAdSoyad.setBounds(518, 10, 113, 29);
		panel.add(lblAdSoyad);
		
		JLabel lblTcNo = new JLabel("T.C No");
		lblTcNo.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
		lblTcNo.setBounds(518, 79, 113, 29);
		panel.add(lblTcNo);
		
		JLabel lblifre = new JLabel("\u015Eifre");
		lblifre.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
		lblifre.setBounds(518, 146, 113, 29);
		panel.add(lblifre);
		
		JLabel lblKullancId = new JLabel("Kullan\u0131c\u0131 ID");
		lblKullancId.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
		lblKullancId.setBounds(529, 282, 113, 29);
		panel.add(lblKullancId);
		
		fld_aadsoyad = new JTextField();
		fld_aadsoyad.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_aadsoyad.setColumns(10);
		fld_aadsoyad.setBounds(518, 40, 183, 29);
		panel.add(fld_aadsoyad);
		
		fld_ttcno = new JTextField();
		fld_ttcno.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_ttcno.setColumns(10);
		fld_ttcno.setBounds(518, 107, 183, 29);
		panel.add(fld_ttcno);
		
		fld_ssifre = new JTextField();
		fld_ssifre.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_ssifre.setColumns(10);
		fld_ssifre.setBounds(518, 180, 183, 29);
		panel.add(fld_ssifre);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_aadsoyad.getText().length()== 0 || fld_ssifre.getText().length() == 0 || fld_ttcno.getText().length() == 0) {
					Helper.showMsg("Lütfen tüm alanlarý doldurunuz.");}
				else {
				  try {
					boolean control = bashekim.addDoctor(fld_ttcno.getText(),fld_ssifre.getText(),fld_aadsoyad.getText());
					if(control) {
						Helper.showMsg("Sisteme Eklenmiþtir.");
						fld_aadsoyad.setText(null);
						fld_ttcno.setText(null);
						fld_ssifre.setText(null);
						updateDoctorModel();
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				}
					
			}
		});
		btnEkle.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
		btnEkle.setBounds(518, 231, 179, 41);
		panel.add(btnEkle);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_kullaniciiid.getText().length() == 0) {
					Helper.showMsg("dfdfds");
				}else {
					if(Helper.confirm("sure")) {
						int selectedID = Integer.parseInt(fld_kullaniciiid.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectedID);
							if(control) {
								Helper.showMsg("success");
								fld_kullaniciiid.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
					}
				}
				}
			}
			});
	
		btnSil.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
		btnSil.setBounds(522, 371, 179, 41);
		panel.add(btnSil);

		JScrollPane k_scrolldoktor = new JScrollPane();
		k_scrolldoktor.setBounds(10, 24, 485, 388);
		panel.add(k_scrolldoktor);
		
		table_doktor = new JTable(doctorModel);
		k_scrolldoktor.setViewportView(table_doktor);
		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				fld_kullaniciiid.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(),0).toString());
				
			}
			
		});
		
				
					
		fld_kullaniciiid = new JTextField();
		fld_kullaniciiid.setFont(new Font("Segoe WP Black", Font.BOLD, 21));
		fld_kullaniciiid.setColumns(10);
		fld_kullaniciiid.setBounds(522, 321, 179, 29);
		panel.add(fld_kullaniciiid);
		
		
					
		
				
	
		
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i < bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
		}
