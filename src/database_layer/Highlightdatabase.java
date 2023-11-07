package database_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Highlightdatabase {
	Statement stat;
	ArrayList<String> wordList;
	
	public Highlightdatabase()
	{
		wordList = new ArrayList<String>();
	}
	  public void createConnection() { 
		  /*
		     * author: Muhammad Atif Siddique
		     * (20F-0106)
		     * 
		     * here is code of highlights usecase Highlightdatabase management
		     * */
			try {
				// Establishing a connection to the Highlightdatabase
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder","root","");
				
				// Get the wordList from the Highlightdatabase
				String query = "SELECT DISTINCT * FROM words";
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(query);
				while (rs.next()) {
					wordList.add(rs.getString("word"));
				}
			
				con.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	 
	public ArrayList<String> getWord() {
		
		return wordList;
	}

}
