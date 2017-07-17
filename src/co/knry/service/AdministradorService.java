package co.knry.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import co.knry.db.DBConnector;
import co.knry.entities.Administradora;
import co.knry.utils.ObjConverter;

public class AdministradorService {

	static Connection connection = DBConnector.getConnection();
	static EntityManager em = DBConnector.getEntityManager();
	

	
	public void add(ObjConverter objConverter) {
		if (em!=null){
			
			em.getTransaction().begin();
			Administradora a=objConverter.getAdministradora();
			System.out.println("Guardando: "+a.getCodTpId());
			em.persist(a);
			
		}
		else {
			try {
				
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administradora "
						+ "(codigo,nombre,Cod_tp_id,Nro_id,Naturaleza,Múltiple_arp,FPS,fusionada,Fecha_fusión) "
						+ "VALUES(?,?,?,?,?,?,?,?,?);");
				preparedStatement.setString(1, objConverter.getAdministradora().getCodigo());
				preparedStatement.setString(2, objConverter.getAdministradora().getNombre());
				preparedStatement.setString(3, objConverter.getAdministradora().getCodTpId());
				preparedStatement.setString(4, objConverter.getAdministradora().getNroId());
				preparedStatement.setString(5, objConverter.getAdministradora().getNaturaleza());
				preparedStatement.setInt(6, objConverter.getAdministradora().getMultipleArp());
				preparedStatement.setInt(7, objConverter.getAdministradora().getFSP());
				preparedStatement.setInt(8, objConverter.getAdministradora().getFusionada());
				preparedStatement.setDate(9, objConverter.getAdministradora().getFechaFusion());
				
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}


	public void commit() {
		System.out.println("YA");
		try {
			if (em!=null){
				em.getTransaction().commit();
			}
			else{
				connection.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void rollback() {
		try {
			if (em!=null){
				em.getTransaction().rollback();
			}
			else{
				connection.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
