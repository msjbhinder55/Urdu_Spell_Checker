package presentation_layer;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import business_layer.ModelHighlights;

public class HighlightDesignControl {
	ModelHighlights ModelHighlights;
	DesignPatternHighlight view;
	ArrayList<String> List = new ArrayList<String>();
	String wrongWords2;

    public void displayClosestWord() {
        String closestWord = ModelHighlights.getClosestWord();
        String inputWord = ModelHighlights.getInputWord();
        view.displayClosestWord(closestWord, inputWord);
    }
	/*
	 * author: Muhammad Atif Siddique (20F-0106)
	 * 
	 * here is class of highlights usecase controller
	 */
	public HighlightDesignControl(ModelHighlights m, DesignPatternHighlight v) {
		ModelHighlights = m;
		view = v;
		
	}

	public void getStartController() {
		view.getBtnNewButton().addActionListener(e -> {
			String inputWord = view.getTextArea().getText();
			actionOnButton(inputWord);
			

		});
		
		// Text1 = new JTextField(20);
		view.getTextArea_1().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				String text = view.getTextArea_1().getText();
				inputInTextField(text);
			}
		});
	};

	public void actionOnButton(String inputWord) {
		
		List = ModelHighlights.getWord();
		inputWord += "";
		String[] w = inputWord.split(" ");
		String lineWrong = "";
		
		ArrayList<String> wrongWords = ModelHighlights.check(List, w);
		wrongWords2=	ModelHighlights.editDis(inputWord);
		
		for (int i = 0; i < wrongWords.size(); i++) {
			String string = wrongWords.get(i);
			
	
			lineWrong += string + " ";
			getSuggestions(lineWrong);
		}
		
		//lineWrong.strip();
		
		view.getTextArea_1().setText(lineWrong);
		
		Highlighter highLight = view.getTextArea_1().getHighlighter();
		highLight.removeAllHighlights();
	
		HighlightPainter y = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
		try {
			highLight.addHighlight(0, lineWrong.length(), y);
			
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};

		public String getSuggestions(String lineWrong) {
			ArrayList<String> suggestions = new ArrayList<String>();
			String	sug=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");
				String sql ;
//				String sql = "SELECT words.word FROM words INNER JOIN generatemutant ON generatemutant.word='" + lineWrong
//						+ "' AND generatemutant.id=words.wid";
//				lineWrong=	"ماہرین";
				sql="select * from words where wid in (select id from generatemutant where word = '" + lineWrong +"');";
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					do {
					 	sug = rs.getString("word");
		               System.out.println(sug);
		               System.out.println("working");
		               
						suggestions.add(rs.getString("word"));
					} while (rs.next());
				}
				view.getLblNewLabel().setText(sug);
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sug;

		}
	//////////////////////////
	private void inputInTextField(String text) {
		
		List = ModelHighlights.getWord();
		if (text.isEmpty()) {
			view.setTextArea3().setVisible(false);
		} else {
			view.setTextArea3().setVisible(true);
		}
		ArrayList<String> filteredsuj = new ArrayList<>();
		for (String sugg : List) {
			if (sugg.toLowerCase().contains(text.toLowerCase()) && !filteredsuj.contains(sugg)) {
				  filteredsuj.add(sugg);
				}

		}
		String[] filteredsujArray = filteredsuj.toArray(new String[filteredsuj.size()+1]);
		
		filteredsujArray[filteredsuj.size()]=wrongWords2;
		view.setTextArea3().setListData(filteredsujArray);
		
		

	};

}
