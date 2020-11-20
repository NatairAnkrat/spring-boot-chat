package chatting2.demo.chatting.model;

import java.sql.SQLException;

import chatting2.demo.chatting.JDBCPostgreSQLConnect;
import chatting2.demo.chatting.model.ChatMessage.MessageType;

public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    
    
   
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws SQLException {
    	
    	
    	JDBCPostgreSQLConnect InsertTable = new JDBCPostgreSQLConnect();
    	InsertTable.getData(1, content);
    	InsertTable.getData(0, sender);
    	//InsertTable.insertTable(sender, content);
    	this.content = InsertTable.getText(2);
        //this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) throws SQLException {
    	
    	//JDBCPostgreSQLConnect InsertTable = new JDBCPostgreSQLConnect();
    	//InsertTable.getData(0,sender);
    	//System.out.println(sender);
    	this.sender = sender;
        //this.sender = sender;
    }
}
