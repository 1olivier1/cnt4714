/* Name: Olivier Ambroise
 Course: CNT 4714 – Spring 2023
 Assignment title: Project 1 – Event-driven Enterprise Simulation
 Date: Sunday January 29, 2023
*/
package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import java.io.BufferedReader;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class window extends JFrame {

	static int  itemCount = 0;

	private ViewButtonH viewHandler;
	private FindButtonH FindHandler;
	private NewButtonH NewHandler;
	private ExitButtonH ExitHandler;
	private CompButtonH CompHandler;
	private BuyButtonH BuyHandler;
	
	public static JTextField BlankTF, NumTF,IDTF,QtyTF,ItemTF,TotalTF;
	public static JLabel blankL, NumL, IDL ;
	public static JLabel qtyL;
	public static JLabel ItemL;
	public static JLabel TotalL; 
	public static JButton ViewB;
	public static JButton FindB;
	public static JButton NewB;
	public static JButton ExitB;
	public static JButton CompB;
	public static JButton buyB;
	public static JButton blankB;
	
	private JFrame frmNileDotComspring;
	
	public static String prevID;
	public static String prevTitle;
	public static double prevPrice;
	public static int prevQ;
	public static int prevD;
	public static double prevTotal;
	
	public static ArrayList <String> itemIDA = new ArrayList<String>();
	public static ArrayList <String> itemTitleA = new ArrayList<String>();
	public static ArrayList <Double> itemPriceA = new ArrayList<Double>();
	public static ArrayList <Integer> itemDiscountA = new ArrayList<Integer>();
	public static ArrayList <Double> itemtotalA = new ArrayList<Double>();
	public static ArrayList <Integer> itemqtyA = new ArrayList<Integer>();
	
	String fileName; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frmNileDotComspring.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public window() {
		initialize();
	}

	public void initialize() {
	
		frmNileDotComspring = new JFrame();
		frmNileDotComspring.setTitle("Nile Dot Com-Spring 2023");
		frmNileDotComspring.setBounds(100, 100, 675, 462);
		frmNileDotComspring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmNileDotComspring.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		top.setBounds(10, 11, 639, 215);
		layeredPane.add(top);
		top.setLayout(null);
		
		qtyL = new JLabel("Enter quantity for item #"+(itemCount+1)+":");
		qtyL.setBounds(10, 67, 201, 25);
		top.add(qtyL);
		
		ItemL = new JLabel("Details for item #"+(itemCount+1)+":");
		ItemL.setBounds(10, 103, 201, 25);
		top.add(ItemL);
		
		TotalL =  new JLabel("Order subtotal for "+ itemCount+" items: ");
		TotalL.setBounds(10, 139, 201, 26);
		top.add(TotalL);
		
		IDL = new JLabel("Enter item id for item #"+(itemCount+1));
		IDL.setBounds(10, 31, 204, 25);
		top.add(IDL);
		
		BlankTF = new JTextField();
		IDTF = new JTextField();
		QtyTF = new JTextField();
		ItemTF = new JTextField();
		TotalTF = new JTextField();
		
		IDTF = new JTextField();
		IDTF.setBounds(261, 31, 368, 25);
		top.add(IDTF);
		IDTF.setColumns(10);
		
		QtyTF = new JTextField();
		QtyTF.setBounds(261, 67, 368, 23);
		top.add(QtyTF);
		QtyTF.setColumns(10);
		
		ItemTF = new JTextField();
		ItemTF.setBounds(261, 103, 368, 23);
		top.add(ItemTF);
		ItemTF.setColumns(10);
		
		TotalTF = new JTextField();
		TotalTF.setColumns(10);
		TotalTF.setBounds(261, 140, 368, 23);
		top.add(TotalTF);
		
		
		
		JPanel bottom = new JPanel();
		bottom.setBounds(10, 225, 639, 187);
		layeredPane.add(bottom);
		bottom.setLayout(null);
		
		FindB = new JButton("Find item #1");
		FindHandler  = new FindButtonH();
		FindB.setBounds(40, 5, 177, 23);
		FindB.addActionListener(FindHandler);
		bottom.add(FindB);
		
	    ViewB = new JButton("View Current Order ");
		viewHandler = new ViewButtonH();
		ViewB.setBounds(40, 51, 177, 23);
		ViewB.addActionListener(viewHandler);
		bottom.add(ViewB);
		
		NewB = new JButton("Start New Order");
		NewHandler = new NewButtonH();
		NewB.setBounds(40, 98, 177, 23);
		NewB.addActionListener(NewHandler);
		bottom.add(NewB);
		
		ExitB = new JButton("Exit(Close App)");
		ExitHandler = new ExitButtonH();
		ExitB.setBounds(360, 98, 169, 23);
		ExitB.addActionListener(ExitHandler);
		bottom.add(ExitB);
		
		CompB = new JButton("Complete Order - Check out");
		CompHandler = new CompButtonH();
		CompB.setBounds(360, 51, 169, 23);
		CompB.addActionListener(CompHandler);
		bottom.add(CompB);
		
		buyB = new JButton("Purchase item #"+(itemCount+1));
		BuyHandler = new BuyButtonH();
		buyB.setBounds(360, 5, 169, 23);
		buyB.addActionListener(BuyHandler);
		bottom.add(buyB);
		
		FindB.setEnabled(true);
		ViewB.setEnabled(false);
		NewB.setEnabled(true);
		ExitB.setEnabled(true);
		CompB.setEnabled(false);
		buyB.setEnabled(false);
		
		ItemTF.setEditable(false);
		TotalTF.setEditable(false);
		
	}
	
}
	class ViewButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			String output = "";
			for(int i = 0; i < window.itemIDA.size(); i++) {
				output += (i+1) + ". " + window.itemIDA.get(i) + " " + window.itemTitleA.get(i) + " $" + 
						window.itemPriceA.get(i) + " " + window.itemqtyA.get(i) + " " + 
						window.itemDiscountA.get(i) + "% " + window.itemtotalA.get(i) +"\n";
			}
			JOptionPane.showMessageDialog(null,output, "nile dot com ", JOptionPane.INFORMATION_MESSAGE); 
		}
		
	}
	
	class FindButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			File inputFile = new File("inventory.txt");
			FileReader inputFileReader = null; 
			BufferedReader inputBufReader = null;
			Scanner aScanner = null;
			String inventroyLine;
			String itemIdFromFile;
			boolean found = false;
			String inStock;
			double price;
			int quantity;
			int discount;
			String searchItem = window.IDTF.getText();
			
			try {
				if(!window.QtyTF.getText().equals("")) {
					quantity = Integer.valueOf(window.QtyTF.getText());
					if(quantity < 5) {
						discount = 0;
					}
					else if(quantity >= 5 && quantity < 10) {
						discount = 10;
					}
					else if(quantity >= 10 && quantity < 15) {
						discount = 15;
					}
					else {
						discount = 20;
					}
					inputFileReader = new FileReader(inputFile);
					inputBufReader = new BufferedReader(inputFileReader);
					
					
					inventroyLine = inputBufReader.readLine();
					while(inventroyLine != null) {
						aScanner = new Scanner(inventroyLine).useDelimiter(",");
						itemIdFromFile = aScanner.next();
						if(itemIdFromFile.equals(searchItem)) {
							
							
							found = true;
							
							String title = aScanner.next();
							String details = searchItem + title;
							inStock = aScanner.next();
							
							if(inStock.equals(" false")) {
								JOptionPane.showMessageDialog(null,"Sorry... that item is out of stock, please try another item", "nile dot com ", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
							window.prevID = searchItem;
							window.prevTitle = title;
							String tempPrice = aScanner.next();
							window.prevPrice = Double.valueOf(tempPrice);
							window.prevQ = quantity;
							window.prevD = discount;
							price = Double.valueOf(tempPrice.substring(1, 5));
							window.prevTotal = quantity * price;
							details += " $" + price + " " + quantity + " " + discount + "% $" + (quantity * price);
							
							window.ItemTF.setText(details);
							window.FindB.setEnabled(false);
							window.ViewB.setEnabled(true);
							window.NewB.setEnabled(true);
							window.CompB.setEnabled(true);
							window.buyB.setEnabled(true);
							
							
							break;
						}
						else {
							inventroyLine = inputBufReader.readLine();
						}
					}
					if(found == false) {
						
						JOptionPane.showMessageDialog(null,"Item ID " + searchItem + " not in file", "nile dot com ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "please enter a quantity for the item", "nile dot com ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(FileNotFoundException fileNotFoundExeption) {
				JOptionPane.showMessageDialog(null,"error file not found", "nile dot com ", JOptionPane.INFORMATION_MESSAGE); 
			}
			catch(IOException ioException) {
				JOptionPane.showMessageDialog(null,"problem reading the file ", "nile dot com ", JOptionPane.INFORMATION_MESSAGE); 
			}
			
		}
		
	}
	
	class NewButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			window.itemIDA.clear();
			window.itemTitleA.clear();
			window.itemqtyA.clear();
			window.itemPriceA.clear();
			window.itemDiscountA.clear();
			window.itemtotalA.clear();
			window.FindB.setEnabled(true);
			window.ViewB.setEnabled(false);
			window.NewB.setEnabled(true);
			window.ExitB.setEnabled(true);
			window.CompB.setEnabled(false);
			window.buyB.setEnabled(false);
			window.IDTF.setEditable(true);
			window.QtyTF.setEditable(true);
			window.ItemTF.setEditable(false);
			window.TotalTF.setEditable(false);
			window.IDTF.setText("");
			window.QtyTF.setText("");
			window.ItemTF.setText("");
			window.TotalTF.setText("");
			window.FindB.setText("Find item #1");
			window.buyB.setText("Purchase item #1");
			window.qtyL.setText("Enter quantity for item #1:");
			window.ItemL.setText("Details for item #1:");
			window.TotalL.setText("Order subtotal for 0 items: ");
			window.IDL.setText("Enter item id for item #1:");
		}
		
	}
	class ExitButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String output;
			output = "you are now leaving the application";
			JOptionPane.showMessageDialog(null,output, "nile dot com ", JOptionPane.INFORMATION_MESSAGE); 
			System.exit(0);
			
			
		}
		
	}
	
	class CompButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			 String output = "";
			
			 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm a z");  
			 Date date = new Date(); 
			 output += "Date: " + formatter.format(date) + "\n" ;
			 output += "Number of items: " + window.itemTitleA.size() + "\n";
			 output += "Item# / ID / Title / Price / QTY / Disc % / Subtotal: \n";
			 for(int i = 0; i < window.itemTitleA.size(); i++) {
				 output += (i+1) + ". " + window.itemIDA.get(i) + " " + window.itemTitleA.get(i) + " $" + 
							window.itemPriceA.get(i) + " " + window.itemqtyA.get(i) + " " + 
							window.itemDiscountA.get(i) + "% " + window.itemtotalA.get(i) +"\n";
			 }
			 DecimalFormat twoDigits = new DecimalFormat("#.00");
			 output += "\n\n";
			 output += "Order subtotal: $" + window.prevTotal;
			 output += "\n\nTax rate:		6%\n\n";
			 output += "Tax amount:		$" + twoDigits.format(window.prevTotal*0.06);
			 output += "\n\nORDER TOTAL:	 $" + twoDigits.format(window.prevTotal+(window.prevTotal*0.06));
			 output += "\n\nThanks for shopping with the Nile dot com!";
			 SimpleDateFormat textDateFormatter = new SimpleDateFormat("DDMMYYYYHHMM");
			 Date textDate = new Date();
			 SimpleDateFormat textDateFormatter2 = new SimpleDateFormat("MM/DD/YY, HH:MM:SS a z");
			 Date textDate2 = new Date();
			 String test = textDateFormatter.format(textDate) + ", " + window.itemIDA.get(0) + ", " + window.itemTitleA.get(0) + ", " + window.itemPriceA.get(0) + ", " + window.itemqtyA.get(0) + ", " + window.itemDiscountA.get(0) + ", $" + window.itemtotalA.get(0) + " " + textDateFormatter2.format(textDate2);
			 
			 try {
				 FileWriter txt = new FileWriter("transcations.txt", true);
				 for(int i = 0; i < window.itemIDA.size(); i++) {
					 txt.write(textDateFormatter.format(textDate) + ", " + window.itemIDA.get(i) + ", " 
							 + window.itemTitleA.get(i) + ", " + window.itemPriceA.get(i) + ", " + 
							 window.itemqtyA.get(i) + ", " + window.itemDiscountA.get(i) + ", $" +
							 window.itemtotalA.get(i) + " " + textDateFormatter2.format(textDate2) + "\n");
				 }
				 txt.close();
			 }
			 catch(IOException ex){
				 ex.printStackTrace();
			 }
			 
			 JOptionPane.showMessageDialog(null,output, "nile dot com ", JOptionPane.INFORMATION_MESSAGE); 
			 window.FindB.setEnabled(false);
			 window.ViewB.setEnabled(true);
			 window.NewB.setEnabled(true);
			 window.ExitB.setEnabled(true);
			 window.CompB.setEnabled(false);
			 window.buyB.setEnabled(false);
			 window.IDTF.setEditable(false);
			 window.QtyTF.setEditable(false);
		}
		
	}
	class BuyButtonH implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String output;
			output = "Item #" + (window.itemIDA.size()+1) + " accepted. Added to your cart!";
			JOptionPane.showMessageDialog(null,output, "nile dot com ", JOptionPane.INFORMATION_MESSAGE);;

			if(!window.TotalTF.getText().equals("")) {
				double sum = window.prevTotal + Double.valueOf(window.TotalTF.getText().substring(1));
				window.prevTotal = sum;
			}
			window.TotalTF.setText("$"+window.prevTotal);
			
			window.itemIDA.add(window.prevID);
			window.itemTitleA.add(window.prevTitle);
			window.itemPriceA.add(window.prevPrice);
			window.itemqtyA.add(Integer.valueOf(window.prevQ));
			window.itemDiscountA.add(Integer.valueOf(window.prevD));
			window.itemtotalA.add(Double.valueOf(window.prevTotal));
			
			window.qtyL.setText("Enter quantity for item #"+(window.itemIDA.size()+1)+":");
			window.ItemL.setText("Details for item #"+(window.itemIDA.size()+1)+":");
			window.TotalL.setText("Order subtotal for "+ (window.itemIDA.size())+" items: ");
			window.IDL.setText("Enter item id for item #"+(window.itemIDA.size()+1));
			window.FindB.setText("Find item #" + (window.itemIDA.size()+1));
			window.buyB.setText("Purchase item #" + (window.itemIDA.size()+1));
			window.FindB.setEnabled(true);
			window.ViewB.setEnabled(true);
			window.NewB.setEnabled(true);
			window.ExitB.setEnabled(true);
			window.CompB.setEnabled(true);
			window.buyB.setEnabled(false);
		
			
		}
		
		
	}
