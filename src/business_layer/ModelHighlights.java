package business_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import database_layer.Highlightdatabase;

public class ModelHighlights {

	
	  Highlightdatabase db = new Highlightdatabase();
	  String closestWord;
	  int minDistance;
	    private String inputWord;

	    // Other code

	    public String getClosestWord() {
	        return closestWord;
	    }

	    public String getInputWord() {
	        return inputWord;
	    }

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public ArrayList<String> getWord() {
		  db.createConnection();
	  ArrayList<String> list = new ArrayList<String>(); 
	  return db.getWord();
	  
	  }
	  /*
	     * author: Muhammad Atif Siddique
	     * (20F-0106)
	     * 
	     * here is code of highlights logic part
	     * */

	public ArrayList<String> check(ArrayList<String> List, String[] words) {
		ArrayList<String> wrongWord = new ArrayList<String>();
		boolean flag=false;
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			flag=false;
			for (Iterator iterator = List.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				if (word.equals(string)) {
					flag=true;
				}
			}
			if(flag==false) {				
				wrongWord.add(word);
			}
		}
		return wrongWord;

	}
	public String editDis (String inputWord)
	{

		// Calculating the edit distance
		ArrayList<String> wordList = db.getWord();
		int minDistance = Integer.MAX_VALUE;
		String closestWord = "";
		for (String word : wordList) {
			int distance = calculateEditDistance(inputWord, word);
			if (distance < minDistance) {
				minDistance = distance;
				String[] array = new String[20];
		        for (int i = 0; i < array.length; i++) {
		            array[i] = "String " + (i + 1);
		        }
		     
				closestWord = word;
			}
		}
		// Printing the result
		
		System.out.println("The closest match to '"+ inputWord+ "' is '" + closestWord + "' with edit distance of " + minDistance);
	
			return closestWord;
		
	
	}

	public  int calculateEditDistance(String str1, String str2) {
		   int m = str1.length(); 
		    int n = str2.length(); 
		  
		    // Create a table to store results of subproblems 
		    int dp[][] = new int[m + 1][n + 1]; 
		  
		    // Fill d[][] in bottom up manner 
		    for (int i = 0; i <= m; i++) { 
		        for (int j = 0; j <= n; j++) { 
		            // If first string is empty, only option is to 
		            // insert all characters of second string 
		            if (i == 0) 
		                dp[i][j] = j; // Min. operations = j 
		  
		            else if (j == 0) 
		                dp[i][j] = i; // Min. operations = i 
		  
		      
		            else if (str1.charAt(i - 1) == str2.charAt(j - 1)) 
		                dp[i][j] = dp[i - 1][j - 1]; 
		  
		            else
		                dp[i][j] = 1 + Math.min(dp[i][j - 1],   // Insert 
		                                       Math.min(dp[i - 1][j],   // Remove 
		                                       dp[i - 1][j - 1])); // Replace 
		        } 
		    } 
		  
		    return dp[m][n]; 
		
	}
	
	public static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public boolean check(ArrayList<String> list, String word) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
