package co.knry.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnector {

	private final static String bd = "lucasian";
	private final static String login = "root";
	private final static String password = "Qinaya2017";
	private final static String url = "jdbc:mysql://localhost/" + bd;
	private final static String driver="com.mysql.jdbc.Driver";
	
	private static EntityManagerFactory entityManagerFactory  = Persistence
			.createEntityManagerFactory(bd);

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, login, password);
			conn.setAutoCommit(false);
			if (conn != null) {
				System.out.println("Conectado a la base de datos [" + bd + "]");
			}
			return conn;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
