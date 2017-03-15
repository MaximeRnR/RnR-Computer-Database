package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
/*import java.util.ArrayList;
import java.util.List;*/

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.model.java.Computer;
import com.excilys.ui.java.App;

//DAO of Computer
public enum ComputerDAO implements ComputerDAOInterface{
	COMPUTERDAO;
	private Connection conn;
	private Computer cp;
	static Logger logger = LogManager.getLogger();
	
	private ComputerDAO() {

		this.conn = ConnectionDB.CONNECTION.getConn();
	}

	public long createComputer(Computer cp) {
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

				logger.info("Computer "+ rs.getInt(1) +" created");
				return rs.getInt(1);
			}

		}catch (Exception e) {
			logger.error("Computer create ERROR" );
			App.menu();
		}
		return -1;

	}       

	public boolean delete(int id) {
		try {

			this.cp = find(id);

			String query = "DELETE FROM computer WHERE id = ?";

			PreparedStatement preparedStmt = this.conn.prepareStatement(query);
			preparedStmt.setInt(1, cp.getId());
			preparedStmt.execute();
			logger.info("Computer "+ cp.getId() +" deleted");
			return true;
		}catch(Exception e){
			logger.error("Computer deleted ERROR" );
			App.menu();
		}return false;
	}

	public boolean update(Computer cp) {

		find(cp.getId());

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
			logger.info("Computer "+ cp.getId() +" updated ");
			return true;
		}catch(Exception e) {
			logger.error("Computer not updated");
		}return false;
	}

	public Computer find(int id) {
		cp = new Computer.Builder().build();
		try {
			String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id=?;";
			PreparedStatement preparedStmt;
			preparedStmt = this.conn.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();
			ResultSet result = preparedStmt.getResultSet();
			if(result.first())
				cp = new Computer.Builder()
				.id(result.getInt("c.id"))
				.name(result.getString("c.name"))
				.di(null)
				.dd(null)
				.manufacturer(result.getInt("c.company_id"))
				.build();

			if(result.getDate("c.introduced")!=null)
				cp.setdIntroduced(result.getDate("c.introduced").toLocalDate());
			if(result.getDate("c.discontinued")!=null)
				cp.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());
			if(cp.getId()==0){
				throw new IllegalArgumentException();
			}

			logger.info("Computer "+ cp.getId() +" selected ");
			return cp;
		} catch (Exception e) {
			logger.error("Computer not selected ");
			System.out.println("This computer does'nt exist");
			App.menu();
		}
		return cp;

	}
	/* A MODIFIER
	public List<Computer>  findAll() {
		List<Computer> lcp = new ArrayList<Computer>();    
		Computer cp;
		try {
			String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c ORDER BY c.company_id ASC;";
			ResultSet result = this.conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if(!result.first())
				throw new IllegalArgumentException();
			while(result.next()){
				cp = new Computer(
						result.getInt("c.id"),
						result.getString("c.name"),
						null,
						null,
						result.getInt("c.company_id")
						); 

				if(result.getDate("c.introduced")!=null)
					cp.setdIntroduced(result.getDate("c.introduced").toLocalDate());
				if(result.getDate("c.discontinued")!=null)
					cp.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());

				lcp.add(cp);
			}
			logger.info("Computers selected ");
		} catch (Exception e) {
			logger.error("Computers not selected ");
			App.menu();
		}

		return lcp;
	}*/


}