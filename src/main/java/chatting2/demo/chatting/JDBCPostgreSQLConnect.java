package chatting2.demo.chatting;


import java.sql.Statement;

import chatting2.demo.chatting.model.ChatMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCPostgreSQLConnect {
	//private final String url = "jdbc:postgresql://localhost/postgres";
	private final String url = "jdbc:postgresql://ec2-54-235-192-146.compute-1.amazonaws.com/dfovqtjec13o1e";
	//private final String user = "postgres";
	//private final String password = "postgres";
	private final String user = "kulmfyohkaanca";
	private final String password = "37d9bd599162c93bc57ee28eb5c6158353feed7571a048e5e6005643449800f1";
	private static final String INSERT_SQL = "INSERT INTO message_db"
			+ " (id, sender, message) VALUES "
			+ " (?, ?, ?);";
	
	private static final String createTableSQL = "CREATE TABLE message_db ("
			+ " id integer NOT NULL, "
			+ " sender text,"
			+ " message text,"
			+ " CONSTRAINT message_db_pkey PRIMARY KEY (id)"
			+ " );";
	
	private static final String findTableSQL = "SELECT * FROM message_db WHERE ID = (SELECT MAX(id) FROM message_db)";
	
	private String TextText = "";
	private String SenderSender = "";
	private int flag2 = 0;
	public void getData(int flag, String data) throws SQLException {
		if (flag == 0) {
			SenderSender = data;
			flag2 = flag2 + 1;
		} else {
			if (flag == 1) {
				TextText = data;
				flag2 = flag2 + 1;
				}
		}
		if (flag2 == 2) {
			flag = 0;
			insertTable(SenderSender, TextText);
			TextText = "";
			SenderSender = "";
			flag2 = 0;
		}
		
		
	}
	
	public void insertTable(String sender, String text) throws SQLException {
		try(Connection connection = DriverManager.getConnection(url,user,password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			if(connection !=null) {
				System.out.println("Connected to PostgreSQL server successfully!");
					int num = 0;
							if (searchTable()!=0)
								num = searchTable();
					preparedStatement.setInt(1, num+1);
					preparedStatement.setString(2, sender);
					preparedStatement.setString(3, text);
					System.out.println(preparedStatement);
					int result = preparedStatement.executeUpdate();
					System.out.println(result);
			}		
			} catch (SQLException e) {
				System.out.print("Failed to connect PostgreSQL server!");
				printSQLException(e);
				
			
			}
			//Statement statement = connection.createStatement();
			//ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
			//if (resultSet.next()) {
			//	System.out.println(resultSet.getString(1));
			//}
			
			
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//}
		
		
	}
	public static void ClearTable() throws SQLException {
		String url1 = "jdbc:postgresql://localhost/postgres";
		String user1 = "postgres";
		String password1 = "postgres";
		
		Connection connection = DriverManager.getConnection(url1,user1,password1);
		if(connection !=null) {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("TRUNCATE TABLE message_db");
		}
		
	}
	
	public int getID() throws SQLException {
		return searchTable();
		
	}
	public String getText(int numberT) throws SQLException {
		int num = searchTable();
		String result = "";
		try(Connection connection = DriverManager
				.getConnection(url,user,password);){ 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(findTableSQL);
			if (resultSet.next()) {
				result = resultSet.getString(numberT);
			}
		} catch (SQLException e) {
			 printSQLException(e);
		}
		
		return result;
	}
	private static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLstate: " + ((SQLException) e).getSQLState());
				System.err.println("Error code: "+ ((SQLException) e).getErrorCode());
				System.err.println("Message: "+ e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: "+ t);
					t = t.getCause();
				}
			}
		}
	}
	public void createTable() throws SQLException {
		try(Connection connection = DriverManager
				.getConnection(url,user,password);
				Statement statement  = connection.createStatement();){ 
			//boolean resultSet = statement.execute(createTableSQL);

			//ResultSet resultSet = statement.executeQuery("SELECT * FROM pg_catalog.pg_tables");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM public;");
			int i = 1;
			if(resultSet.next()) {
			
				System.out.println(resultSet.getString(i));
				//i=i+1;
			}
			
			//System.out.println(resultSet);	
		} catch (SQLException e) {
			 printSQLException(e);
		}
	}
	
	private int searchTable() throws SQLException {
		int result = 0;
		try(Connection connection = DriverManager
				.getConnection(url,user,password);){ 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(findTableSQL);
			if (resultSet.next()) {
				result = Integer.parseInt (resultSet.getString(1));
			}
		} catch (SQLException e) {
			 printSQLException(e);
		}
		
		return result;
	}
	
	//public static void main(String[] args) throws SQLException {
		//JDBCPostgreSQLConnect sqlConnection = new JDBCPostgreSQLConnect();
		//sqlConnection.connect();
		//JDBCPostgreSQLConnect InsertTable = new JDBCPostgreSQLConnect();
		//InsertTable.insertTable();
		//InsertTable.createTable();
		//System.out.println(InsertTable.searchTable());
	
	
	
	//}
}
