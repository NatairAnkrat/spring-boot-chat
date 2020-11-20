package chatting2.demo.chatting;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import chatting2.demo.chatting.JDBCPostgreSQLConnect;

/**
 * @author Natair
 *
 */
@SpringBootApplication
public class ChattingApplication {

	public static void main(String[] args) throws SQLException {
		JDBCPostgreSQLConnect InsertTable = new JDBCPostgreSQLConnect();
		InsertTable.insertTable("adinadin", "dvadva");
    	//InsertTable.getData(0,"BBBBB");
    	//InsertTable.getData(1,"AAAAA");
		//System.out.println(InsertTable.getID() + InsertTable.getText(2) + InsertTable.getText(3) + "AAAAAAAAAAAAAAAA");
		//InsertTable.createTable();
		SpringApplication.run(ChattingApplication.class, args);
		//InsertTable.ClearTable();
	}

}
