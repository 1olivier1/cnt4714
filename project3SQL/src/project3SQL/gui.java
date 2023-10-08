package project3SQL;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;


import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


import com.mysql.cj.jdbc.MysqlDataSource;


import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTable;


public class gui extends JFrame  {
	private ConnectButtonH ConnectHandler;
	private ExecuteButtonH ExecuteHandler;
	private ClearWinButtonH ClearWinHandler;
	public static JTable jTable;
	private JFrame SQLGUI; 
	public static JTextField UserNameTF;
	public static JTextField PassWordTF;
	public static JComboBox comboBox;
	public static JTextField txtConnection;
	 public static JTextArea commandBox;
	 public static JLayeredPane layeredPane = new JLayeredPane();
	 public static JTable sqlOutput;
	public static JTable table_1 = new JTable();
	public static JTable empty = new JTable();
	public static JScrollPane scrollPane = new JScrollPane();
	public static ResultSetTableModel rstm;
	public static ClearCommandButtonH ClearCommandHandler;
	public static boolean isConnected = false;
	public static boolean isCreated = false;
	public static String url;
	public static Connection connection;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		    
		    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui gui = new gui();
					gui.SQLGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public gui() {
		initialize();
	}
	public void initialize() {
		
		SQLGUI = new JFrame();
		SQLGUI.setTitle("SQL Client App ");
		SQLGUI.setBounds(200,200,984,703);
		SQLGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SQLGUI.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		top.setBounds(10, 11, 948, 256);
		layeredPane.add(top);
		
		JLabel Propertiesf = new JLabel("Properties File");
		Propertiesf.setBackground(Color.DARK_GRAY);
		Propertiesf.setBounds(10, 34, 135, 30);
		
		JLabel Username = new JLabel("Username");
		Username.setBackground(Color.GRAY);
		Username.setBounds(10, 75, 135, 24);
		
		JLabel Password = new JLabel("Password");
		Password.setBackground(Color.GRAY);
		Password.setBounds(10, 121, 135, 24);
		top.setLayout(null);
		top.add(Propertiesf);
		top.add(Username);
		top.add(Password);
		
		String[]files = {"root.properties","client.properties" };
		comboBox = new JComboBox(files);
		comboBox.setBounds(182, 36, 206, 26);
		
		top.add(comboBox);
		
		commandBox = new JTextArea();
		commandBox.setBounds(398, 37, 540, 142);
		top.add(commandBox);
		
		UserNameTF = new JTextField();
		UserNameTF.setBounds(182, 72, 206, 30);
		top.add(UserNameTF);
		UserNameTF.setColumns(10);
		
		PassWordTF = new JTextField();
		PassWordTF.setBounds(182, 121, 206, 24);
		top.add(PassWordTF);
		PassWordTF.setColumns(10);
		
		scrollPane.setBounds(10, 312, 948, 296);
		layeredPane.add(scrollPane);
		
	
		ConnectHandler = new ConnectButtonH(UserNameTF.getText(), PassWordTF.getText(), comboBox.getSelectedItem().toString());
		ExecuteHandler = new ExecuteButtonH(gui.comboBox.getSelectedItem().toString(), table_1, rstm);
		ClearWinHandler = new ClearWinButtonH(gui.comboBox.getSelectedItem().toString());
	
		JButton ConnectionB = new JButton("Connect to Database");
		ConnectionB.setBounds(25, 190, 135, 27);
		ConnectionB.addActionListener(ConnectHandler);
		top.add(ConnectionB);
		
		JButton ClearWindow = new JButton("Clear SQL Command");
		ClearWindow.setForeground(Color.RED);
		ClearWindow.setBounds(408, 190, 165, 27);
		ClearWindow.addActionListener(new ClearCommandButtonH());
		top.add(ClearWindow);
		
		JButton ExecuteB = new JButton("Execute SQL Command");
		ExecuteB.setForeground(Color.BLACK);
		ExecuteB.setBackground(Color.GREEN);
		ExecuteB.setBounds(752, 191, 186, 25);
		ExecuteB.addActionListener(ExecuteHandler);
		top.add(ExecuteB);
		
		JLabel details = new JLabel("Connection Details");
		details.setForeground(Color.BLUE);
		details.setBounds(10, 0, 150, 24);
		top.add(details);
		
		JLabel enteransqlcommand = new JLabel("Enter An SQL Command");
		enteransqlcommand.setForeground(Color.BLUE);
		enteransqlcommand.setBounds(398, 0, 150, 26);
		top.add(enteransqlcommand);
		
		txtConnection = new JTextField();
		txtConnection.setBounds(10, 222, 928, 34);
		top.add(txtConnection);
		txtConnection.setForeground(Color.RED);
		txtConnection.setText("no connection");
		txtConnection.setBackground(Color.BLACK);
		txtConnection.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sql Execution result window");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(32, 267, 240, 34);
		layeredPane.add(lblNewLabel);
		
		JButton clearRwin = new JButton("Clear Result Window");
		clearRwin.setBackground(Color.YELLOW);
		clearRwin.setForeground(Color.BLACK);
		clearRwin.setBounds(0, 630, 174, 23);
		clearRwin.addActionListener(ClearWinHandler);
		layeredPane.add(clearRwin);
		
		
	
		
	}


}


class ClearCommandButtonH implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		gui.commandBox.setText("");
		
		
	}
	
}


class ClearWinButtonH implements ActionListener{
	
	private String url;
	
	ClearWinButtonH(String url){
		this.url = url;
	}
	
	public void actionPerformed(ActionEvent e) {
		gui.empty = new JTable(new DefaultTableModel());
		gui.scrollPane.setViewportView(gui.empty);
		
	}
	
}



class ExecuteButtonH implements ActionListener{
	
	private ResultSetTableModel tableModel;
	private JTable table;
	private JPanel panel;
	private String url;
	
	ExecuteButtonH(String url, JTable table, ResultSetTableModel r){
		
		this.url = url;
		table = this.table;
		tableModel = r;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(gui.isConnected) {
		
				gui.empty = new JTable();
				try {
					gui.rstm = new ResultSetTableModel(gui.commandBox.getText(), gui.connection);
					
				} catch (SQLException sqlException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog( null, 
							sqlException.getMessage(), "Database error", 
	                           JOptionPane.ERROR_MESSAGE );
				}
				 catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				if(gui.commandBox.getText().contains("select")) {
					table = new JTable(gui.rstm);
					gui.scrollPane.setViewportView(table);
					
					gui.isCreated = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Successful Update..." + (Math.abs(gui.rstm.getRowCount() ) + " row(s) updated."), "Successful Update", JOptionPane.INFORMATION_MESSAGE);
				}
			
		}
		else {
			JOptionPane.showMessageDialog( null, "Please connect to a database first.","Database error", 
                    JOptionPane.ERROR_MESSAGE );
		}
	}
	
}



class ConnectButtonH implements ActionListener{
	private String username,password, url;
	Properties properties = new Properties();
	public static ResultSetTableModel tableModel;
	ConnectButtonH(String username, String password, String url){
		this.username = username;
		this.password = password;
		this.url = url;
	}
	
	public boolean authenticate() {
		boolean out = false;
		if(username.equals(properties.getProperty("MYSQL_DB_USERNAME")) && password.equals(properties.getProperty("MYSQL_DB_PASSWORD"))) {
			return true;
		}
		if(!username.equals(properties.getProperty("MYSQL_DB_USERNAME"))) {
		
			JOptionPane.showMessageDialog(null,"Username should be " + properties.getProperty("MYSQL_DB_USERNAME") , username, JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else {
		
			JOptionPane.showMessageDialog(null,"Incorect password ", password, JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent e){
		FileInputStream filein = null;
		MysqlDataSource dataSource = null;
		url = gui.comboBox.getSelectedItem().toString();
		try {
	    	filein = new FileInputStream(url);
	    	properties.load(filein);
	    	dataSource = new MysqlDataSource();
	    	dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
	    	gui.url = properties.getProperty("MYSQL_DB_URL");
	    	dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
	    	dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));	 	
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		username = gui.UserNameTF.getText();
		password = gui.PassWordTF.getText();
		
	   
	    if(!authenticate()) {
	    	return;
	    }
	    String out = "";
	    gui.isConnected = true;
	
	 
	    out = ("Output from SimpleJDBC_PropertiesFile:   Using a properties file to hold connection details.");
		java.util.Date date = new java.util.Date();
		
		
		try {
			gui.connection = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    out += ("\nDatabase connected");
	    DatabaseMetaData dbMetaData = null;
		try {
			dbMetaData = gui.connection.getMetaData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out += ("\nJDBC Driver name " + dbMetaData.getDriverName() );
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out += ("\nJDBC Driver version " + dbMetaData.getDriverVersion());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out += ("\nDriver Major version " +dbMetaData.getDriverMajorVersion());
		out += ("\nDriver Minor version " +dbMetaData.getDriverMinorVersion()  + "\n");

	    
		Statement statement = null;
		try {
			statement = gui.connection.createStatement();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	  
		gui.txtConnection.setText("CONNECTED TO: " + properties.getProperty("MYSQL_DB_URL"));
		gui.txtConnection.setForeground(Color.YELLOW);
	}
	
}



class ResultSetTableModel extends AbstractTableModel 
{
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	private Statement statement;
	
	ResultSetTableModel(String query, Connection connection ) throws SQLException, IllegalStateException, IOException{
        if(!query.contains("select")) {
            statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            numberOfRows = setUpdate(query);
        }
        else {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            setQuery(query);
        }
        
	}
	public Class getColumnClass( int column ) throws IllegalStateException
	{
	
		try 
	      {
	         String className = metaData.getColumnClassName( column + 1 );
	         return Class.forName( className );
	      }
	      	catch ( Exception exception ) 
	      {
	         exception.printStackTrace();
	      }
	      return Object.class;
     }
	public String getColumnName( int column ) throws IllegalStateException
	{ 
		try 
	      {
	        return metaData.getColumnName( column + 1 );  
	      } 
	      catch ( SQLException sqlException ) 
	      {
	         sqlException.printStackTrace();
	      } 
	      return "";
	}
	public void setQuery(String query) throws SQLException, IOException {
		
	
		resultSet = statement.executeQuery( query );
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        Properties properties = new Properties();
        FileInputStream filein = null;
        MysqlDataSource dataSource = null;
        filein = new FileInputStream("project3operationslog.properties");
        properties.load(filein);
        dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
        dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
        Connection connection = dataSource.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate("update operationscount set num_queries = num_queries + 1");
        connection.close();
        fireTableStructureChanged();
	}


	
	
	public int setUpdate(String query)
	throws SQLException, IllegalStateException, IOException 
	{
		
		int res;
        res = statement.executeUpdate(query);
        Properties properties = new Properties();
        FileInputStream filein = null;
        MysqlDataSource dataSource = null;
        filein = new FileInputStream("project3operationslog.properties");
        properties.load(filein);
        dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
        dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
        Connection connection = dataSource.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate("update operationscount set num_queries = num_queries + 1");
        statement.executeUpdate("update operationscount set num_updates = num_updates + 1");
        connection.close();
        fireTableStructureChanged();
       
        return res;
	}

	@Override
	public int getRowCount() {
		return numberOfRows;
	}

	@Override
	public int getColumnCount() {
        try {
			return metaData.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0; 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 try {
			resultSet.next();
			resultSet.absolute( rowIndex + 1 );
			return resultSet.getObject( columnIndex + 1 );
		 }
		 catch (SQLException e) {
			e.printStackTrace();
		}
      return "";
	}     
} 
