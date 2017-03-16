package com.excilys.persistence.java;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
/*import java.util.ArrayList;
import java.util.List;*/
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.excilys.model.java.Computer;
import com.excilys.ui.java.Page;
import com.excilys.util.java.ComputerDBException;

//DAO of Computer
public enum ComputerDAO implements ComputerDAOInterface{
	COMPUTERDAO;
	private Connection conn;
	private Computer cp;
	static Logger logger = LogManager.getLogger();

	private ComputerDAO() throws ComputerDBException{

		this.conn = ConnectionDB.CONNECTION.getConn();
	}

	public long createComputer(Computer cp) throws ComputerDBException {
		String query = "insert into computer"+"(name,introduced,discontinued,company_id)"+"values (?,?,?,?)";
		try(PreparedStatement preparedStmt = this.conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);){



			preparedStmt.setString(1,cp.getName());
			if(cp.getdIntroduced()!=null){
				preparedStmt.setTimestamp(2,new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
			}else
				preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
			if(cp.getdDiscontinued()!=null){
				preparedStmt.setTimestamp(3,new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
			}else
				preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
			preparedStmt.setLong(4,cp.getManufacturer());
			preparedStmt.execute();
			try(ResultSet rs = preparedStmt.getGeneratedKeys(); ){
				if(rs.next()){
					logger.info("Computer "+ rs.getInt(1) +" created");
					return rs.getInt(1);
				}
			}
		}catch (ComputerDBException | SQLException e) {
			logger.error("Computer not created" );
			throw new ComputerDBException("Computer not created", e);
		}
		return -1;

	}       

	public boolean delete(long id) throws ComputerDBException {
		String query = "DELETE FROM computer WHERE id = ?";


		try(PreparedStatement preparedStmt = this.conn.prepareStatement(query);) {

			this.cp = find(id);


			preparedStmt.setLong(1, cp.getId());
			preparedStmt.execute();
			logger.info("Computer "+ cp.getId() +" deleted");
			preparedStmt.close();
			return true;
		}catch (ComputerDBException | SQLException e) {
			logger.error("Computer not deleted " );
			throw new ComputerDBException("Computer not deleted ", e);
		}
	}

	public boolean update(Computer cp) throws ComputerDBException {

		find(cp.getId());

		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

		try(PreparedStatement preparedStmt = this.conn.prepareStatement(query);)  {
			preparedStmt.setString(1, cp.getName());
			if(cp.getdIntroduced()!=null){
				preparedStmt.setTimestamp(2,new Timestamp(Date.valueOf(cp.getdIntroduced()).getTime()));
			}else
				preparedStmt.setNull(2, java.sql.Types.TIMESTAMP);
			if(cp.getdDiscontinued()!=null){
				preparedStmt.setTimestamp(3,new Timestamp(Date.valueOf(cp.getdDiscontinued()).getTime()));
			}else
				preparedStmt.setNull(3, java.sql.Types.TIMESTAMP);
			preparedStmt.setLong(4,cp.getManufacturer());
			preparedStmt.setLong(5,cp.getId());
			preparedStmt.executeUpdate();
			logger.info("Computer "+ cp.getId() +" updated ");
			preparedStmt.close();
			return true;
		}catch (ComputerDBException | SQLException e) {
			logger.error("Computer not updated " );
			throw new ComputerDBException("Computer not updated ", e);
		}
	}

	public Computer find(long id) throws ComputerDBException {
		cp = null;
		String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c WHERE c.id=?;";
		try(PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {


			preparedStmt.setLong(1, id);
			preparedStmt.execute();
			try(ResultSet result = preparedStmt.getResultSet();){
				if(result.first())
					cp = new Computer.Builder()
					.id(result.getLong("c.id"))
					.name(result.getString("c.name"))
					.di(null)
					.dd(null)
					.manufacturer(result.getInt("c.company_id"))
					.build();

				if(result.getTimestamp("c.introduced")!=null)
					cp.setdIntroduced(result.getTimestamp("c.introduced").toLocalDateTime().toLocalDate());
				if(result.getDate("c.discontinued")!=null)
					cp.setdDiscontinued(result.getTimestamp("c.discontinued").toLocalDateTime().toLocalDate());
				logger.info("Computer "+ cp.getId() +" selected ");
				preparedStmt.close();
				return cp;
			}
		}catch (ComputerDBException | SQLException e) {
			logger.error("Computer not found " );
			throw new ComputerDBException("Computer not found ", e);
		}

	}

	public List<Computer> page(int index) {

		List<Computer> lcp = new ArrayList<Computer>();    
		Computer cp;
		String sql = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id FROM computer c ORDER BY c.company_id ASC LIMIT ? OFFSET ?;";
		
		try(PreparedStatement preparedStmt = this.conn.prepareStatement(sql);) {
			
			preparedStmt.setInt(1, Page.MAX_NUMBER_OF_OBJECT);
			preparedStmt.setInt(2, index*Page.MAX_NUMBER_OF_OBJECT);
			preparedStmt.execute();
			
			try(ResultSet result = preparedStmt.getResultSet();){
			while(result.next()){
				cp = new Computer.Builder()
						.id(result.getLong("c.id"))
						.name(result.getString("c.name"))
						.di(null)
						.dd(null)
						.manufacturer(result.getInt("c.company_id"))
						.build();

				if(result.getDate("c.introduced")!=null)
					cp.setdIntroduced(result.getDate("c.introduced").toLocalDate());
				if(result.getDate("c.discontinued")!=null)
					cp.setdDiscontinued(result.getDate("c.discontinued").toLocalDate());

				lcp.add(cp);
			}
			logger.info("Computers selected ");
			}
		}catch (ComputerDBException | SQLException e) {
			logger.error("Computers not found " );
			throw new ComputerDBException("Computers not found ", e);
		}

		return lcp;
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