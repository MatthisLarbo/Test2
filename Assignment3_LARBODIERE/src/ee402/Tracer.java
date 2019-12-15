package ee402;


import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

public class Tracer extends JPanel //For the use of the method paint (drawing the curve and the graph)
{
	//Overriding
	private Map<String, Curve> myothersclients;
	private ClientConstru myClient;
	
	//Pas sûr ici
	//public boolean firstC;
	//public boolean secondC;
	//public boolean thirdC;
	//public boolean fourthC;
	
	public Tracer(ClientConstru firstC, Map<String, Curve> myothersclients) //Constructor of the Tracer class
	{
		this.myothersclients = myothersclients; //Initialization of the others possible clients
		this.myClient = firstC;
	}
	
	public void paint(Graphics graph)
	{
		
		super.paint(graph);
		graph.setFont(new Font("default", Font.BOLD, 14)); //Write the title in bold
		graph.drawString("EE402 Representation of the temperature on several clients", 6, 12);
		
		//Draw the graph
		graph.setColor(Color.darkGray); //Set the color of the lines
		graph.drawLine(20, 38, 20, 432); //Abcissa
		graph.drawLine(8, 420, 640, 420); //Ordinate
		
		//Drawing crosses
		graph.setColor(Color.darkGray);
		graph.drawLine(16, 42, 20, 38);
		graph.drawLine(24, 42, 20, 38);
		graph.drawLine(636, 416, 640, 420);
		graph.drawLine(636, 424, 640, 420);
		
		//Writing Strings on the graph
		graph.setFont(new Font("default", Font.PLAIN, 12)); //Restablish the original writing
		AffineTransform vertically = new AffineTransform();
		vertically.setToRotation(Math.toRadians(90), 80, 100);
		//graph.setTransform(vertically); Don't work with Graphics but with Graphics2D yes
		graph.drawString("Temperature (°C)", 10, 30);
		graph.drawString("0", 10, 432);
		graph.drawString("Time (sec)", 630, 435);
		
		Set<String> testIn = this.myothersclients.keySet();
		Iterator<String> it = testIn.iterator();
		
		int ab = 0;
		while(it.hasNext())
		{
			ab=ab+1;
			
			Curve myCurve = myothersclients.get(it.next());
			Vector<CurvePoints> myPoint = myCurve.getSerie();
			Color curvecolor = myCurve.getCurveColor();
			
			//For only one client firstly
			if(ab==1 && myPoint.size()>1 && myClient.firstC == 1)
			{
				for(int a = 0; a<myPoint.size()-1; a++)
				{
					//Tracing the points of firstC
					CurvePoints firstP = myPoint.get(a);
					CurvePoints secondP = myPoint.get(a+1);
					
					//Get the placement of each point
					int abc1 = (int)firstP.getAbci();
					int abc2 = (int)secondP.getAbci();
					int ord1 = (int)firstP.getOrd();
					int ord2 = (int)secondP.getOrd();
					
					//Connect the different points with a line
					
					//ESSAYER DE CHANGER CA (valeurs -4)
					
					graph.setColor(curvecolor);
					graph.drawLine(abc1, ord1, abc2, ord2);
					graph.setColor(curvecolor);
					graph.drawLine(abc1-4, ord1, abc2-4, ord2);
					graph.drawLine(abc1, ord1-4, abc2, ord2-4);
				}
			}
		}
		
		
		
	}
}