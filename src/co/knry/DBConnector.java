package co.knry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private final static String bd = "lucasian";
	private final static String login = "root";
	private final static String password = "Qinaya2017";
	private final static String url = "jdbc:mysql://localhost/" + bd;
	private final static String driver="com.mysql.jdbc.Driver";

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

//	public static EntityManager getEntityManager() {
//		Map<String, String> properties = new HashMap<String, String>();
//		properties.put("javax.persistence.jdbc.user", login);
//		properties.put("javax.persistence.jdbc.password", password);
//		properties.put("javax.persistence.jdbc.driver", driver);
//		properties.put("provider","org.hibernate.ejb.HibernatePersistence");
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(archiverPersistenceUnitInfo(), properties);
//		
//		return emf.createEntityManager();
//	}
//	
//	private static PersistenceUnitInfo archiverPersistenceUnitInfo() {
//	    return new PersistenceUnitInfo() {
//
//	        @Override
//	        public String getPersistenceUnitName() {
//	            return "ApplicationPersistenceUnit";
//	        }
//
//	        @Override
//	        public String getPersistenceProviderClassName() {
//	            return "org.hibernate.jpa.HibernatePersistenceProvider";
//	        }
//
//	        @Override
//	        public PersistenceUnitTransactionType getTransactionType() {
//	            return PersistenceUnitTransactionType.RESOURCE_LOCAL;
//	        }
//
//	        @Override
//	        public DataSource getJtaDataSource() {
//	            return null;
//	        }
//
//	        @Override
//	        public DataSource getNonJtaDataSource() {
//	            return null;
//	        }
//
//	        @Override
//	        public List<String> getMappingFileNames() {
//	            return Collections.emptyList();
//	        }
//
//	        @Override
//	        public List<URL> getJarFileUrls() {
//	            try {
//	                return Collections.list(this.getClass()
//	                                            .getClassLoader()
//	                                            .getResources(""));
//	            } catch (IOException e) {
//	                //throw new UncheckedIOException(e);
//	            	return null;
//	            }
//	        }
//
//	        @Override
//	        public URL getPersistenceUnitRootUrl() {
//	            return null;
//	        }
//
//	        @Override
//	        public List<String> getManagedClassNames() {
//	            return Collections.emptyList();
//	        }
//
//	        @Override
//	        public boolean excludeUnlistedClasses() {
//	            return false;
//	        }
//
//	        @Override
//	        public SharedCacheMode getSharedCacheMode() {
//	            return null;
//	        }
//
//	        @Override
//	        public ValidationMode getValidationMode() {
//	            return null;
//	        }
//
//	        @Override
//	        public Properties getProperties() {
//	            return new Properties();
//	        }
//
//	        @Override
//	        public String getPersistenceXMLSchemaVersion() {
//	            return null;
//	        }
//
//	        @Override
//	        public ClassLoader getClassLoader() {
//	            return null;
//	        }
//
//	        @Override
//	        public void addTransformer(ClassTransformer transformer) {
//
//	        }
//
//	        @Override
//	        public ClassLoader getNewTempClassLoader() {
//	            return null;
//	        }
//	        
//	    };
//	}
}
