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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewProfs extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastnameField;
	private JTable table;
	private DefaultTableModel model;
	private String selectedUUID;


	public ViewProfs() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameField.setText("");
				buildTable();
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
		lblManual.setBounds(190, 10, 136, 30);
		footer.add(lblManual);
		
		JLabel lblOftenQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblOftenQuestions.setForeground(new Color(0, 52, 117));
		lblOftenQuestions.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOftenQuestions.setBounds(520, 10, 136, 30);
		footer.add(lblOftenQuestions);
		
		JLabel lblCitizenSupport = new JLabel("Υποστήριξη Πολιτών");
		lblCitizenSupport.setForeground(new Color(0, 52, 117));
		lblCitizenSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCitizenSupport.setBounds(850, 10, 136, 30);
		footer.add(lblCitizenSupport);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 74, 1186, 478);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblDataProf = new JLabel("Αιτήσεις Εκπαιδευτών");
		lblDataProf.setBounds(470, 5, 288, 29);
		lblDataProf.setFont(new Font("Tahoma", Font.BOLD, 24));
		mainPanel.add(lblDataProf);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setBounds(120, 60, 80, 40);
		mainPanel.add(lblLastname);
		
		lastnameField = new JTextField();
		lastnameField.setBounds(210, 60, 240, 40);
		mainPanel.add(lastnameField);
		lastnameField.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSearch.setBackground(new Color(0, 128, 0));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBounds(470, 60, 150, 40);
		mainPanel.add(btnSearch);
		
		JButton btnCleanUp = new JButton("Εκκαθάριση");
		btnCleanUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastnameField.setText("");
				buildTable();
			}
		});
		btnCleanUp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCleanUp.setForeground(new Color(192, 192, 192));
		btnCleanUp.setBounds(640, 60, 150, 40);
		mainPanel.add(btnCleanUp);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
            public void valueChanged(ListSelectionEvent e) {
				// Check if the selection is still adjusting
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                    int selectedRow = table.getSelectedRow();
                    // Check if a row is selected
                    if (selectedRow != -1) {
                        // Get data from the selected row
                    	selectedUUID = (String) model.getValueAt(selectedRow, 0);
                    }
                }
			}
		});
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"Κωδικός", "Όνομα", "Επώνυμο"}
		));
		table.setBounds(120, 110, 670, 360);
		model = (DefaultTableModel) table.getModel();

		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(120, 110, 670, 360);
		mainPanel.add(scrollPane);
		
		JButton btnShowDetailsProf = new JButton("Προβολή");
		btnShowDetailsProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewProfsPage().setEnabled(false);
				Main.getViewProfDetailPage().setVisible(true);
			}
		});
		btnShowDetailsProf.setBackground(new Color(0, 128, 0));
		btnShowDetailsProf.setForeground(new Color(255, 255, 255));
		btnShowDetailsProf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowDetailsProf.setBounds(874, 207, 220, 50);
		mainPanel.add(btnShowDetailsProf);
		
		JButton btnUpdate = new JButton("Ενημέρωση");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewProfsPage().setEnabled(false);
				Main.getUpdateProfPage().setVisible(true);
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(0, 128, 0));
		btnUpdate.setBounds(874, 267, 220, 50);
		mainPanel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Διαγραφή");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteByUUID(selectedUUID);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(0, 128, 0));
		btnDelete.setBounds(874, 327, 220, 50);
		mainPanel.add(btnDelete);
		
		JButton btnClose = new JButton("Κλείσιμο");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(true);
				Main.getViewProfsPage().setVisible(false);
			}
		});
		btnClose.setBackground(new Color(192, 192, 192));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClose.setBounds(874, 420, 220, 50);
		mainPanel.add(btnClose);
		
	}
	
	public String getSelectedUUID() {
		return selectedUUID;
	}
	
	private void buildTable() {
		String sql = "SELECT uuid, firstname, lastname FROM teachers WHERE lastname LIKE ?";
		Connection conn = Dashboard.getConnection();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, lastnameField.getText().trim() + "%");
			ResultSet rs = ps.executeQuery();
			model.setRowCount(0); // clean the table model
			while (rs.next()) {
				Object[] row = {
						rs.getString("uuid"),
						rs.getString("firstname"),
						rs.getString("lastname")
				};
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Select error", "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	private void deleteByUUID(String uuid) {
		String sql = "DELETE FROM teachers WHERE uuid=?";
		Connection conn = Dashboard.getConnection();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, uuid);
			int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Διαγραφή", 
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				int rowsAffected = ps.executeUpdate();
				JOptionPane.showMessageDialog(null, rowsAffected + " γρααμμή/ες διαγράφηκαν", "Διαγραφή", 
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				return;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
