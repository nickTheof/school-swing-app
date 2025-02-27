package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

public class Main {
	
	private static final LandingPage landingPage = new LandingPage();
	private static final LoginPage loginPage = new LoginPage();
	private static final Dashboard dashboard = new Dashboard();
	private static final InsertProfPage insertProfPage = new InsertProfPage();
	private static final UpdateProfPage updateProfPage = new UpdateProfPage();
	private static final ViewProfs viewProfsPage = new ViewProfs();
	private static final ProfViewDetail viewProfDetailPage = new ProfViewDetail();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					landingPage.setVisible(true);
					landingPage.setLocationRelativeTo(null);
					loginPage.setVisible(false);
					loginPage.setLocationRelativeTo(null);
					dashboard.setVisible(false);
					dashboard.setLocationRelativeTo(null);
					insertProfPage.setVisible(false);
					insertProfPage.setLocationRelativeTo(null);
					updateProfPage.setVisible(false);
					updateProfPage.setLocationRelativeTo(null);
					viewProfsPage.setVisible(false);
					viewProfsPage.setLocationRelativeTo(null);
					viewProfDetailPage.setVisible(false);
					viewProfDetailPage.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static LandingPage getLandingPage() {
		return landingPage;
	}
	
	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static Dashboard getDashboard() {
		return dashboard;
	}

	public static InsertProfPage getInsertProfPage() {
		return insertProfPage;
	}

	public static UpdateProfPage getUpdateProfPage() {
		return updateProfPage;
	}

	public static ViewProfs getViewProfsPage() {
		return viewProfsPage;
	}

	public static ProfViewDetail getViewProfDetailPage() {
		return viewProfDetailPage;
	}
	
}
