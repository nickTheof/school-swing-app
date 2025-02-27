package gr.aueb.cf.schoolapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.model.City;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InsertProfPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField vatField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField lastnameField;
	private JTextField fatherNameField;
	private JTextField mailField;
	private JTextField streetNoField;
	private JTextField postalCodeField;
	private JComboBox<City> cityComboBox;
	private JLabel errorFirstname;
	private JLabel errorVat;
	private JLabel errorLastName;
	private JLabel errorFatherName;
	private JLabel errorMail;
	private JLabel errorStreetNo;
	private JLabel errorPostalCode;
	private JLabel errorPhoneNumber;
	private JLabel errorAddress;
	private List<City> cities = new ArrayList<>();

	public InsertProfPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cities = fetchCitiesFromDb();
				cities.forEach(city -> cityComboBox.addItem(city));
				nameField.setText("");
				vatField.setText("");
				phoneField.setText("");
				addressField.setText("");
				lastnameField.setText("");
				fatherNameField.setText("");
				mailField.setText("");
				streetNoField.setText("");
				postalCodeField.setText("");
				cityComboBox.setSelectedIndex(0);
				errorFirstname.setText("");
				errorVat.setText("");
				errorLastName.setText("");
				errorFatherName.setText("");
				errorMail.setText("");
				errorStreetNo.setText("");
				errorPostalCode.setText("");
				errorPhoneNumber.setText("");
				errorAddress.setText("");
			}
		});
		setTitle("Ποιότητα στην Εκπαίδευση");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/eduv2.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1200, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(0, 52, 117));
		header.setBounds(0, 0, 1186, 62);
		contentPane.add(header);
		
		JLabel lblImgHeader = new JLabel("");
		lblImgHeader.setIcon(new ImageIcon(Dashboard.class.getResource("/images/gov_logo_small.png")));
		lblImgHeader.setBounds(73, 10, 114, 42);
		header.add(lblImgHeader);
		
		JLabel lblUsername = new JLabel("ΝΙΚΟΛΑΟΣ ΘΕΟΦΑΝΗΣ");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(950, 18, 195, 26);
		header.add(lblUsername);
		
		JSeparator headerSeparator = new JSeparator();
		headerSeparator.setForeground(new Color(0, 251, 255));
		headerSeparator.setBounds(0, 64, 1186, 2);
		contentPane.add(headerSeparator);
		
		JSeparator footerSeparator = new JSeparator();
		footerSeparator.setForeground(new Color(0, 251, 255));
		footerSeparator.setBounds(0, 550, 1186, 2);
		contentPane.add(footerSeparator);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 560, 1186, 90);
		contentPane.add(footer);
		
		JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
		lblManual.setForeground(new Color(0, 52, 117));
		lblManual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManual.setBounds(194, 10, 136, 30);
		footer.add(lblManual);
		
		JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblOftenQuestions.setForeground(new Color(0, 52, 117));
		lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOftenQuestions.setBounds(524, 10, 136, 30);
		footer.add(lblOftenQuestions);
		
		JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
		lblCitizenSupport.setForeground(new Color(0, 52, 117));
		lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCitizenSupport.setBounds(854, 10, 136, 30);
		footer.add(lblCitizenSupport);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 74, 1186, 478);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblDataProf = new JLabel("Στοιχεία Εκπαιδευτή");
		lblDataProf.setBounds(470, 5, 259, 29);
		lblDataProf.setFont(new Font("Tahoma", Font.BOLD, 24));
		mainPanel.add(lblDataProf);
		
		JLabel lblFirstname = new JLabel("'Ονομα*");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstname.setBounds(130, 62, 80, 13);
		mainPanel.add(lblFirstname);
		
		nameField = new JTextField();
		nameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String firstname = nameField.getText().trim();
				errorFirstname.setText(firstname.equals("") ? "Το όνομα είναι υποχρεωτικό" : "");
			}
			
		});
		nameField.setBounds(220, 50, 314, 40);
		mainPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel lblVat = new JLabel("ΑΦΜ*");
		lblVat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVat.setBounds(130, 132, 80, 13);
		mainPanel.add(lblVat);
		
		JLabel lblPhoneNumber = new JLabel("Τηλέφωνο*");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNumber.setBounds(130, 202, 80, 13);
		mainPanel.add(lblPhoneNumber);
		
		vatField = new JTextField();
		vatField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String vat = vatField.getText().trim();
				errorVat.setText(vat.equals("") ? "Το ΑΦΜ είναι υποχρεωτικό" : "");
			}
		});
		vatField.setBounds(220, 120, 314, 40);
		mainPanel.add(vatField);
		vatField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String phoneNumber = phoneField.getText().trim();
				errorPhoneNumber.setText(phoneNumber.equals("") ? "Ο τηλεφωνικός αριθμός είναι υποχρεωτικός" : "");
			}
		});
		phoneField.setColumns(10);
		phoneField.setBounds(220, 190, 314, 40);
		mainPanel.add(phoneField);
		
		JLabel lblAddress = new JLabel("Διεύθυνση*");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(130, 272, 80, 13);
		mainPanel.add(lblAddress);
		
		addressField = new JTextField();
		addressField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String address = addressField.getText().trim();
				errorAddress.setText(address.equals("") ? "Η διέθυνση είναι υποχρεωτικη" : "");
			}
		});
		addressField.setColumns(10);
		addressField.setBounds(220, 260, 314, 40);
		mainPanel.add(addressField);
		
		JLabel lblCity = new JLabel("Πόλη*");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(130, 343, 80, 13);
		mainPanel.add(lblCity);
		
		cityComboBox = new JComboBox<>();
		cityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cityComboBox.setBounds(220, 330, 314, 40);
		mainPanel.add(cityComboBox);
		
		JButton btnCloseApp = new JButton("Κλείσιμο");
		btnCloseApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(true);
				Main.getInsertProfPage().setVisible(false);
			}
		});
		btnCloseApp.setBackground(new Color(192, 192, 192));
		btnCloseApp.setForeground(new Color(0, 0, 0));
		btnCloseApp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCloseApp.setBounds(220, 395, 314, 50);
		mainPanel.add(btnCloseApp);
		
		JLabel lblLastname = new JLabel("Επώνυμο*");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastname.setBounds(636, 63, 80, 13);
		mainPanel.add(lblLastname);
		
		lastnameField = new JTextField();
		lastnameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String lastname = lastnameField.getText().trim();
				errorLastName.setText(lastname.equals("") ? "Το επώνυμο είναι υποχρεωτικό" : "");
			}
		});
		lastnameField.setColumns(10);
		lastnameField.setBounds(727, 50, 314, 40);
		mainPanel.add(lastnameField);
		
		JLabel lblFatherName = new JLabel("Πατρώνυμο*");
		lblFatherName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFatherName.setBounds(636, 133, 80, 13);
		mainPanel.add(lblFatherName);
		
		fatherNameField = new JTextField();
		fatherNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String fatherName = fatherNameField.getText().trim();
				errorFatherName.setText(fatherName.equals("") ? "Το πατρώνυμο είναι υποχρεωτικό" : "");	
			}
		});
		fatherNameField.setColumns(10);
		fatherNameField.setBounds(727, 120, 314, 40);
		mainPanel.add(fatherNameField);
		
		JLabel lblMail = new JLabel("e-mail*");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMail.setBounds(636, 203, 80, 13);
		mainPanel.add(lblMail);
		
		mailField = new JTextField();
		mailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String mail = mailField.getText().trim();
				errorMail.setText(mail.equals("") ? "Το email είναι υποχρεωτικό" : "");
			}
		});
		mailField.setColumns(10);
		mailField.setBounds(727, 190, 314, 40);
		mainPanel.add(mailField);
		
		JLabel lblStreetNo = new JLabel("Αριθμός*");
		lblStreetNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStreetNo.setBounds(636, 273, 80, 13);
		mainPanel.add(lblStreetNo);
		
		streetNoField = new JTextField();
		streetNoField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String streetNo = streetNoField.getText().trim();
				errorStreetNo.setText(streetNo.equals("") ? "Ο αριθμός διεύθυνσης είναι υποχρεωτικός" : "");
			}
		});
		streetNoField.setColumns(10);
		streetNoField.setBounds(727, 260, 314, 40);
		mainPanel.add(streetNoField);
		
		JLabel lblpostalCode = new JLabel("ΤΚ*");
		lblpostalCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblpostalCode.setBounds(636, 344, 80, 13);
		mainPanel.add(lblpostalCode);
		
		postalCodeField = new JTextField();
		postalCodeField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String postalCode = postalCodeField.getText().trim();
				errorPostalCode.setText(postalCode.equals("") ? "Ο ταχυδρομικός κώδικας είναι υποχρεωτικός" : "");
			}
		});
		postalCodeField.setColumns(10);
		postalCodeField.setBounds(727, 331, 314, 40);
		mainPanel.add(postalCodeField);
		
		JButton btnSubmit = new JButton("Υποβολή");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Data Binding
				String firstname = nameField.getText().trim();
				String lastname = lastnameField.getText().trim();
				String vat = vatField.getText().trim();
				String phoneNumber = phoneField.getText().trim();
				String address = addressField.getText().trim();
				String fatherName = fatherNameField.getText().trim();
				String mail = mailField.getText().trim();
				String streetNo = streetNoField.getText().trim();
				String postalCode = postalCodeField.getText().trim();
				City selectedCity = (City) cityComboBox.getSelectedItem();
				int cityId = selectedCity.getId();
							
				// Data Validation - Simple Approach
				errorFirstname.setText(firstname.equals("") ? "Το όνομα είναι υποχρεωτικό" : "");
				errorVat.setText(vat.equals("") ? "Το ΑΦΜ είναι υποχρεωτικό" : "");
				errorLastName.setText(lastname.equals("") ? "Το επώνυμο είναι υποχρεωτικό" : "");
				errorFatherName.setText(fatherName.equals("") ? "Το πατρώνυμο είναι υποχρεωτικό" : "");
				errorMail.setText(mail.equals("") ? "Το email είναι υποχρεωτικό" : "");
				errorStreetNo.setText(streetNo.equals("") ? "Ο αριθμός διεύθυνσης είναι υποχρεωτικός" : "");
				errorPostalCode.setText(postalCode.equals("") ? "Ο ταχυδρομικός κώδικας είναι υποχρεωτικός" : "");
				errorPhoneNumber.setText(phoneNumber.equals("") ? "Ο τηλεφωνικός αριθμός είναι υποχρεωτικός" : "");
				errorAddress.setText(address.equals("") ? "Η διέθυνση είναι υποχρεωτικη" : "");
				
				// Return if any field is empty
				if (selectedCity == null || firstname.equals("") || lastname.equals("") || vat.equals("") || phoneNumber.equals("")
						|| address.equals("") || fatherName.equals("") || mail.equals("") || streetNo.equals("") || postalCode.equals("")) {
					 JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				
				// Insert
				String sql = "INSERT INTO teachers (firstname, lastname, vat, fathername, phone_num, email, street, street_num, zipcode, city_id, uuid)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				Connection conn = Dashboard.getConnection();
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, vat);
					ps.setString(4, fatherName);
					ps.setString(5, phoneNumber);
					ps.setString(6, mail);
					ps.setString(7, address);
					ps.setString(8, streetNo);
					ps.setString(9, postalCode);
					ps.setInt(10, cityId);
					ps.setString(11, UUID.randomUUID().toString());
					
					int insertedRows = ps.executeUpdate();
					JOptionPane.showMessageDialog(null,  insertedRows + " record(s) inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
				} catch (SQLException e1) {
//					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBackground(new Color(64, 128, 128));
		btnSubmit.setBounds(727, 395, 314, 50);
		mainPanel.add(btnSubmit);
		
		errorFirstname = new JLabel("");
		errorFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorFirstname.setForeground(new Color(255, 0, 0));
		errorFirstname.setBounds(220, 91, 314, 22);
		mainPanel.add(errorFirstname);
		
		errorVat = new JLabel("");
		errorVat.setForeground(new Color(255, 0, 0));
		errorVat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorVat.setBounds(220, 159, 314, 22);
		mainPanel.add(errorVat);
		
		errorLastName = new JLabel("");
		errorLastName.setForeground(Color.RED);
		errorLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLastName.setBounds(727, 91, 314, 22);
		mainPanel.add(errorLastName);
		
		errorFatherName = new JLabel("");
		errorFatherName.setForeground(Color.RED);
		errorFatherName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorFatherName.setBounds(727, 159, 314, 22);
		mainPanel.add(errorFatherName);
		
		errorMail = new JLabel("");
		errorMail.setForeground(Color.RED);
		errorMail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorMail.setBounds(727, 228, 314, 22);
		mainPanel.add(errorMail);
		
		errorStreetNo = new JLabel("");
		errorStreetNo.setForeground(Color.RED);
		errorStreetNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorStreetNo.setBounds(727, 299, 314, 22);
		mainPanel.add(errorStreetNo);
		
		errorPostalCode = new JLabel("");
		errorPostalCode.setForeground(Color.RED);
		errorPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorPostalCode.setBounds(727, 370, 314, 22);
		mainPanel.add(errorPostalCode);
		
		errorPhoneNumber = new JLabel("");
		errorPhoneNumber.setForeground(Color.RED);
		errorPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorPhoneNumber.setBounds(220, 228, 314, 22);
		mainPanel.add(errorPhoneNumber);
		
		errorAddress = new JLabel("");
		errorAddress.setForeground(Color.RED);
		errorAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorAddress.setBounds(220, 299, 314, 22);
		mainPanel.add(errorAddress);
	}
	
	private List<City> fetchCitiesFromDb() {
		List<City> citiesToReturn = new ArrayList<>();
		String sql = "SELECT * FROM cities ORDER BY name ASC";
		Connection conn = Dashboard.getConnection();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int cityId = rs.getInt("id");
				String cityName = rs.getString("name");
				City city = new City(cityId, cityName);
				citiesToReturn.add(city);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Select cities error", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return citiesToReturn;
		
	}
}
