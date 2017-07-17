package co.knry.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.knry.db.DBConnector;
import co.knry.utils.ObjConverter;

public class AdministradorService {

	static Connection connection = DBConnector.getConnection();

	
	public void add(ObjConverter objConverter) {
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


	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
