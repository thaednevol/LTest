package co.knry.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import co.knry.DBConnector;
import co.knry.MyETL;

public class AdministradorService {

	static Connection connection = DBConnector.getConnection();

	
	public void add(MyETL myETL) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administradora "
					+ "(codigo,nombre,Cod_tp_id,Nro_id,Naturaleza,Múltiple_arp,FPS,fusionada,Fecha_fusión) "
					+ "VALUES(?,?,?,?,?,?,?,?,?);");
			preparedStatement.setString(1, myETL.getAdministradora().getCodigo());
			preparedStatement.setString(2, myETL.getAdministradora().getNombre());
			preparedStatement.setString(3, myETL.getAdministradora().getCodTpId());
			preparedStatement.setString(4, myETL.getAdministradora().getNroId());
			preparedStatement.setString(5, myETL.getAdministradora().getNaturaleza());
			preparedStatement.setInt(6, myETL.getAdministradora().getMultipleArp());
			preparedStatement.setInt(7, myETL.getAdministradora().getFSP());
			preparedStatement.setInt(8, myETL.getAdministradora().getFusionada());
			preparedStatement.setDate(9, myETL.getAdministradora().getFechaFusion());
			
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
