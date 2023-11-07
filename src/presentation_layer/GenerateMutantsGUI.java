package presentation_layer;

import java.awt.*; // Using AWT layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.*; // Using Swing components and containers
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import facade_design_pattern.MutantGenerator;

/**
 * Generate Mutants of Each Word Automatically:
 * -> Mutants to cross varify the actual words
 * -> Cat Mutates into Kat
 * -> Site Mutates into Sit
 *
 * @author  Muhammad Saad Javed (20F-0349)
 * @version 1.0
 * @since   2022-11-22 
 */

//Creating the GUI
public class GenerateMutantsGUI extends JFrame implements ActionListener {

  // Create a logger 
  static Logger logger = Logger.getLogger(GenerateMutantsGUI.class.getName());

  public JButton importDataBtn, generateMutantsBtn;
  public JLabel inputLabel, outputLabel; // Declare a Label component
  public JTable inputTable, outputTable;
  JPanel mainPanel = new JPanel();

  public GenerateMutantsGUI() {

    setTitle("Urdu Spell Checker");
    setSize(850, 725);

    mainPanel.setLayout(null);
    mainPanel.setPreferredSize(new Dimension(500, 500));
    Color backgroundColor = Color.decode("#EFF5F5");
    mainPanel.setBackground(backgroundColor);

    inputLabel = new JLabel("ڈیٹا حاصل کریں");
    inputLabel.setBounds(250, 10, 200, 50);
    Color inputLabelColor = Color.decode("#32cc98");
    inputLabel.setForeground(inputLabelColor);
    inputLabel.setFont(new Font("Serif", Font.BOLD, 30));
    mainPanel.add(inputLabel);

    outputLabel = new JLabel("ڈسپلے ڈیٹا");
    outputLabel.setBounds(250, 335, 200, 50);
    Color outputLabelColor = Color.decode("#32cc98");
    outputLabel.setForeground(outputLabelColor);
    outputLabel.setFont(new Font("Serif", Font.BOLD, 30));
    mainPanel.add(outputLabel);

    importDataBtn = new JButton("ڈیٹا بیس سے ڈیٹا درآمد کریں");
    //    Color importDataBtnColor = Color.decode("#F56EB3");
    //    importDataBtn.setBackground(importDataBtnColor);
    importDataBtn.setBounds(620, 100, 150, 50);
    importDataBtn.addActionListener(this);

    generateMutantsBtn = new JButton("اتپریورتی پیدا کریں");
    //    Color generateMutantsBtnColor = Color.decode("#62B6B7");
    //    generateMutantsBtn.setBackground(generateMutantsBtnColor);
    generateMutantsBtn.setBounds(620, 170, 120, 50);
    generateMutantsBtn.addActionListener(this);

    mainPanel.add(importDataBtn);
    mainPanel.add(generateMutantsBtn);
    add(mainPanel);

    importDataBtn.addActionListener(this);
    generateMutantsBtn.addActionListener(this);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  //Functions of the Buttons
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == importDataBtn) {
      InputTable();

    }

    if (e.getSource() == generateMutantsBtn) {
      OutputTable();
    }
  }

  public void InputTable() {

    // Create a List to store the results
    List < String > results = new ArrayList < > ();

    try {

      // Connect to the database using JDBC
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");

      // Execute the SQL query to retrieve wid and word from the table
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT wid, word FROM words");

      String[] columnNames = {
        "word_id",
        "word"
      };
      DefaultTableModel model = new DefaultTableModel(columnNames, 0);
      //JTable table = new JTable(model);

      // Iterate over the rows of the result set and add each wid and word to the list
      while (rs.next()) {
        int wid = rs.getInt("wid");
        String word = rs.getString("word");
        results.add(wid + ": " + word);
        model.addRow(new Object[] {
          wid,
          word
        });
      }

      inputTable = new JTable(model);
      inputTable.setBounds(50, 70, 525, 250);
      mainPanel.add(inputTable);
      JScrollPane scrollPane = new JScrollPane(inputTable);
      scrollPane.setBounds(50, 70, 525, 250);
      mainPanel.add(scrollPane);
      add(mainPanel);

      // Close the result set, statement, and connection
      rs.close();
      stmt.close();
      conn.close();

    } catch (SQLException e) {
      // Catch any SQL exceptions and print the stack trace
      e.printStackTrace();
      // Log a message at the warning level 
      logger.warning("Logging a warning message.");
    }

    // Display the contents of the list
    for (String s: results) {
      System.out.println(s);
    }
  }

  public void OutputTable() {

    // Get the table model from the inputTable
    TableModel model = inputTable.getModel();

    // Create a new DefaultTableModel to store the mutant words
    String[] columnNames = {
      "word_id",
      "word"
    };
    DefaultTableModel mutantModel = new DefaultTableModel(columnNames, 0);

    // Loop through the rows of the input table
    for (int i = 0; i < model.getRowCount(); i++) {
      // Get the wid and word values for the current row
      int wid = (int) model.getValueAt(i, 0);
      String word = (String) model.getValueAt(i, 1);

      // Generate mutants for the word using the GenerateMutants class
      MutantGenerator facade = new MutantGenerator(word);
      String mutants = facade.generateMutants();
      mutants = mutants.substring(1, mutants.length() - 1);

      for (String mutant: mutants.split(",")) {
        mutantModel.addRow(new Object[] {
          wid,
          mutant
        });
      }

    }

    JTable outputTable = new JTable(mutantModel);
    outputTable.setBounds(50, 400, 525, 250);
    mainPanel.add(outputTable);
    JScrollPane scrollPane = new JScrollPane(outputTable);
    scrollPane.setBounds(50, 400, 525, 250);
    mainPanel.add(scrollPane);
    add(mainPanel);

    try {

      // Connect to the database
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhinder", "root", "");

      // Loop through the rows of the output table
      for (int i = 0; i < outputTable.getRowCount(); i++) {
        // Get the wid and mutants values for the current row
        int wid = (int) outputTable.getValueAt(i, 0);
        String mutants = (String) outputTable.getValueAt(i, 1);

        // Insert the wid and mutants values into the database table
        String sql = "INSERT INTO generatemutant (id, word) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, wid);
        pstmt.setString(2, mutants);
        pstmt.executeUpdate();

      }
    } catch (SQLException e) {
      // Catch any SQL exceptions and print the stack trace
      e.printStackTrace();
      logger.warning("Logging a warning message.");
    }
  }
}