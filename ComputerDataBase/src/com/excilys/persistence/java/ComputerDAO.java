package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.excilys.connection.java.ConnectionDB;
import com.excilys.model.java.Computer;

public class ComputerDAO {
	private Connection conn;

	public ComputerDAO() {

		this.conn = ConnectionDB.getInstance().getConn();

	}

	public int createComputer(Computer cp) {
		ResultSet rs = null;
		try{

			String query = "insert into computer"+"(name,introduced,discontinued,company_id)"+"values (?,?,?,?)";
			PreparedStatement preparedStmt = this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1,cp.getName());
			if(cp.getdIntroduced()!=null){
				preparedStmt.setTimestamp(2,new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
			}else
				preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
			if(cp.getdDiscontinued()!=null){
				preparedStmt.setTimestamp(3,new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
			}else
				preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
			preparedStmt.setInt(4,cp.getManufacturer());
			preparedStmt.execute();
			rs = preparedStmt.getGeneratedKeys();

			if(rs.next()){
				return rs.getInt(1);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}return -1;

	}       

	public boolean delete(Computer cp) {
		try {
			String query = "DELETE FROM computer WHERE id = ?";

			PreparedStatement preparedStmt = this.conn.prepareStatement(query);
			preparedStmt.setInt(1, cp.getId());
			preparedStmt.execute();

			return true;
		}catch(SQLException e){

			e.printStackTrace();

		}return false;
	}

	public boolean update(Computer cp) {

		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = this.conn.prepareStatement(query);
			preparedStmt.setString(1, cp.getName());
			if(cp.getdIntroduced()!=null){
				preparedStmt.setTimestamp(2,new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
			}else
				preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
			if(cp.getdDiscontinued()!=null){
				preparedStmt.setTimestamp(3,new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
			}else
				preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
			preparedStmt.setInt(4,cp.getManufacturer());
			preparedStmt.setInt(5,cp.getId());
			preparedStmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}return false;
	}

	public Computer find(int id) {
		Computer computer = new Computer();      

		try {
			String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id='"+id+"';";
			ResultSet result = this.conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if(result.first())
				computer = new Computer(
						result.getInt("c.id"),
						result.getString("c.name"),
						null,
						null,
						result.getInt("c.company_id")
						); 

			if(result.getDate("c.introduced")!=null)
				computer.setdIntroduced(result.getDate("c.introduced").toLocalDate());
			if(result.getDate("c.discontinued")!=null)
				computer.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computer;
	}

}