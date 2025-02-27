package gr.aueb.cf.schoolapp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static Connection connection; // This one static connection is shared to all application. Simple approach with potential risks (e.g. connection timeouts, concurrency issues).
	// It would be better to use a connection pool instead of a single static connection to improve performance and manage ability.
	

	public Dashboard() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				String url = "jdbc:mysql://localhost:3306/school7dbpro?serverTimezone=UTC";
				// Simple approach for user database credentials. Under normal circumstances, we fetch these values from .env files
				String user = "user7pro";
				String password = "12345";
				
				try {
					connection = DriverManager.getConnection(url, user, password);
					System.out.println("Connection Success");
				} catch (SQLException e1) {
//					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection DB", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(ABORT);;
				}
				
			}
		});
		setTitle("Ποιότητα στην Εκπαίδευση");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/images/eduv2.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblNewLabel = new JLabel("ΝΙΚΟΛΑΟΣ ΘΕΟΦΑΝΗΣ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(950, 18, 195, 26);
		header.add(lblNewLabel);
		
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
		footer.setBounds(0, 552, 1186, 90);
		contentPane.add(footer);
		
		JLabel lblManual = new JLabel("Εγχειρίδιο Χρήσης");
		lblManual.setForeground(new Color(0, 52, 117));
		lblManual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManual.setBounds(194, 30, 136, 30);
		footer.add(lblManual);
		
		JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblOftenQuestions.setForeground(new Color(0, 52, 117));
		lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOftenQuestions.setBounds(524, 30, 136, 30);
		footer.add(lblOftenQuestions);
		
		JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
		lblCitizenSupport.setForeground(new Color(0, 52, 117));
		lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCitizenSupport.setBounds(854, 30, 136, 30);
		footer.add(lblCitizenSupport);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 52, 113));
		leftPanel.setBounds(0, 67, 315, 481);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel lblstartPage = new JLabel("Αρχική");
		lblstartPage.setBounds(50, 60, 170, 20);
		lblstartPage.setForeground(new Color(255, 255, 0));
		lblstartPage.setFont(new Font("Tahoma", Font.BOLD, 16));
		leftPanel.add(lblstartPage);
		
		JLabel lblProfs = new JLabel("Εκπαιδευτές");
		lblProfs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProfs.setForeground(new Color(255, 255, 255));
		lblProfs.setBounds(50, 70, 170, 52);
		leftPanel.add(lblProfs);
		
		JLabel lblFetchProfs = new JLabel("Προβολή Εκπαιδευτών");
		lblFetchProfs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFetchProfs.setForeground(new Color(0, 251, 255));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblFetchProfs.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getViewProfsPage().setVisible(true);
			}
		});
		lblFetchProfs.setForeground(new Color(255, 255, 255));
		lblFetchProfs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFetchProfs.setBounds(70, 115, 170, 25);
		leftPanel.add(lblFetchProfs);
		
		JLabel lblInsertProf = new JLabel("Εισαγωγή Εκπαιδευτή");
		lblInsertProf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblInsertProf.setForeground(new Color(0, 251, 255));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblInsertProf.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getInsertProfPage().setVisible(true);
			}
		});
		lblInsertProf.setForeground(Color.WHITE);
		lblInsertProf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInsertProf.setBounds(70, 144, 170, 25);
		leftPanel.add(lblInsertProf);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(318, 64, 868, 481);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblQualityEducation = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblQualityEducation.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblQualityEducation.setBounds(240, 33, 463, 52);
		mainPanel.add(lblQualityEducation);
		
		JLabel lblDataStoreProfs = new JLabel("Προβολή Μητρώου Εκπαιδευτών");
		lblDataStoreProfs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataStoreProfs.setBounds(100, 120, 300, 30);
		mainPanel.add(lblDataStoreProfs);
		
		JLabel lblDataStoreProfsCF = new JLabel("Προβολή Μητρώου Εγγεγραμμένων Εκπαιδευτών στο Μητρώο του Coding Factory");
		lblDataStoreProfsCF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataStoreProfsCF.setBounds(100, 145, 600, 30);
		mainPanel.add(lblDataStoreProfsCF);
		
		JButton btnShowProfs = new JButton("Συνέχεια");
		btnShowProfs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getViewProfsPage().setVisible(true);
			}
		});
		btnShowProfs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowProfs.setBackground(new Color(0, 128, 0));
		btnShowProfs.setForeground(new Color(255, 255, 255));
		btnShowProfs.setBounds(100, 185, 157, 52);
		mainPanel.add(btnShowProfs);
		
		
		JLabel lblInsertProfDataStore = new JLabel("Εισαγωγή Εκπαιδευτή στο Μητρώο Εκπαιδευτών");
		lblInsertProfDataStore.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInsertProfDataStore.setBounds(100, 250, 450, 30);
		mainPanel.add(lblInsertProfDataStore);
		
		JLabel lblInsertProfDataStoreCF = new JLabel("Εισαγωγή Εκπαιδευτή στο Μητρώο του Coding Factory");
		lblInsertProfDataStoreCF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInsertProfDataStoreCF.setBounds(100, 275, 600, 30);
		mainPanel.add(lblInsertProfDataStoreCF);
		
		JButton btnInsertProf = new JButton("Συνέχεια");
		btnInsertProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getInsertProfPage().setVisible(true);
				
			}
		});
		btnInsertProf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsertProf.setBackground(new Color(0, 128, 0));
		btnInsertProf.setForeground(new Color(255, 255, 255));
		btnInsertProf.setBounds(100, 325, 157, 52);
		mainPanel.add(btnInsertProf);
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
