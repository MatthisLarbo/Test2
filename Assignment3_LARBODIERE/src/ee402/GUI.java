package ee402;


import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame //implements ItemListener
{
	private ThreadedServer myThread;
	private Map<String, Curve> myothersclients;
	
	private Tracer myTrace;
	private ClientConstru myClient;
	
	private JPanel myPanel;
	private JPanel allThePanels;
	
	public GUI() //Constructor of the GUI class
	{
		this.setSize(750, 510);
		
		//Exit the window
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		this.myClient = new ClientConstru();
		this.myothersclients = new HashMap<String, Curve>(); //Stores pairs of items 
		
		this.myTrace = new Tracer(myClient, this.myothersclients);
		this.myPanel = new JPanel(new GridLayout(1, 4)); //display of the GUI 
		
		//Creation of differents Checkboxes
		CheckboxGroup box = new CheckboxGroup();
		Checkbox cb1 = new Checkbox("First Client", box, true);
		Checkbox cb2 = new Checkbox("Second Client", box, false);
		Checkbox cb3 = new Checkbox("Third Client", box, false);
		Checkbox cb4 = new Checkbox("Fourth Client", box, false);
		Checkbox cb5 = new Checkbox("All of my Clients", box, false);
		
		myPanel.add(cb1);myPanel.add(cb2);myPanel.add(cb3);myPanel.add(cb4);myPanel.add(cb5);
		
		//When the checkbox is check (for all the 5 types of boxes)
		cb1.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e) {
						if(cb1.getState())
						{
							myClient.firstC = 0;
						}
						else 
						{
							myClient.firstC = 1;
						}
					}
				});
		
		cb2.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(cb2.getState())
				{
					myClient.secondC = 0;
				}
				else 
				{
					myClient.secondC = 1;
				}
			}
		});
		
		cb3.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(cb3.getState())
				{
					myClient.thirdC = 0;
				}
				else 
				{
					myClient.thirdC = 1;
				}
			}
		});
		
		cb4.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(cb4.getState())
				{
					myClient.fourthC = 0;
				}
				else 
				{
					myClient.fourthC = 1;
				}
			}
		});
		
		cb5.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(cb5.getState())
				{
					myClient.firstC = 0;  //Test à revérifier
					myClient.secondC = 0;
					myClient.thirdC = 0;
					myClient.fourthC = 0;
				}
				else 
				{
					myClient.firstC = 1;
					myClient.secondC = 1;
					myClient.thirdC = 1;
					myClient.fourthC = 1;
				}
			}
		});
		
		this.allThePanels = new JPanel(new BorderLayout());
		this.allThePanels.add(myPanel, BorderLayout.SOUTH);
		this.allThePanels.add(this.myTrace, BorderLayout.CENTER);
		
		this.myThread = new ThreadedServer(this.myothersclients);
		this.myThread.start();
		
		this.getContentPane().add(this.allThePanels);
		this.setVisible(true); //Needed to display all the things
		
		this.setLocationRelativeTo(null); //The window is at the center 
		
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0); //When we click on the cross to close the window
			}
		});
		
	}
	
	public void itemStateChanged(ItemEvent event)
	{
		
	}
	
	
	//Definition of the main function calling the Graphic User Interface class
	//public static void main(String[] args)
	  //{
	//	  new GUI();
	  //}

}

