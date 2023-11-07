package presentation_layer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
 
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class DesignPatternHighlight extends JFrame {

	private JPanel contentPane;
	JButton Result_btn;
	JTextArea Text1;
	JLabel lblNewLabel ;
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}

	int minDistance;
   
	
	public int getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	 JTextArea result1;
	private JTable table;
	private JScrollPane scrollpane;
	private JScrollPane scrollpane_1;
	private DefaultTableModel defaultmodel;

    public JList sujList;

	public DesignPatternHighlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		getContentPane().setBackground(SystemColor.scrollbar);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel head_1 = new JLabel("یہاں کوئی بھی متن ٹائپ کریں.........!۔");
		head_1.setBounds(351, 64, 211, 23);
		head_1.setFont(new Font("Serif", Font.BOLD, 17));
		contentPane.add(head_1);
		
		  Text1 = new JTextArea();
		  Text1.setBounds(49, 66, 292, 22);
		contentPane.add(Text1);
        Text1.setColumns(10);
        
		result1 = new JTextArea();
		result1.setBounds(49, 133, 388, 22);
		result1.setBackground(new Color(176, 224, 230));
		contentPane.add(result1);
		
		 Result_btn = new JButton(" کلک");
		 Result_btn.setBounds(208, 99, 55, 23);
		 Result_btn.setBackground(Color.MAGENTA);
		contentPane.add(Result_btn);
		
		
		JLabel resultBtnMemo = new JLabel("چیک کرنے کے لیے کلک کریں.    👈۔");
		resultBtnMemo.setBounds(273, 108, 134, 14);
		resultBtnMemo.setFont(new Font("Serif", Font.BOLD, 10));
		contentPane.add(resultBtnMemo);
		
		JLabel welcome = new JLabel("(-------اردو سپیل چیکر-------)");
		welcome.setBounds(131, 11, 323, 38);
		welcome.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 29));
		contentPane.add(welcome);
		 //Set the title
       
 
        //Make the frame visible
        this.setVisible(true);
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setBackground(new Color(192, 192, 192));
		table.setBounds(380, 355, 670, 328);
		scrollpane=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(262, 381, 300, 100);
		defaultmodel=new DefaultTableModel();
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		scrollpane.setViewportView(table);
		contentPane.add(scrollpane);
		table.setModel(defaultmodel);
		this.setVisible(true);
		defaultmodel.addColumn( " + closestWord + ");

		//defaultmodel.addColumn("suggestions");
		defaultmodel.addColumn("Edit Distance");
	

        scrollpane_1 = new JScrollPane();
        scrollpane_1.setBounds(0, 0, 0, 0);

        // add the components to the frame
        //getContentPane().add(Text1);
        getContentPane().add(scrollpane_1);
        
        // create the list
        sujList = new JList();
        sujList.setBounds(303, 185, 134, 213);
        contentPane.add(sujList);
        sujList.setVisibleRowCount(20);
        sujList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
          lblNewLabel = new JLabel("");
          lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBounds(302, 153, 135, 31);
        contentPane.add(lblNewLabel);
        
    
        sujList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e )
            {
            	Text1.replaceSelection(sujList.getSelectedValue().toString());

            } 
        });
         setSize(734, 422);
       setVisible(true);
         
    
		
		
	
	}
	public JList getTextArea_2() {
		return sujList;
	}

	public JButton getBtnNewButton() {
		return Result_btn;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.Result_btn = btnNewButton;
	}

	public JTextArea getTextArea() {
		return Text1;
	}

	public void setTextArea(JTextArea textArea) {
		this.Text1 = textArea;
	}
	public JTextArea getTextArea_1() {
		return result1;
	}

	public void setTextArea_1(JTextArea textArea_1) {
		this.result1 = textArea_1;
	}
	public JList setTextArea3() {
		return sujList;
	}
 

	public void setTextArea3(JList funcList) {
		this.sujList = funcList;
	}
	public void displayClosestWord(String closestWord2, String inputWord) {
		// TODO Auto-generated method stub
		
	}
}
