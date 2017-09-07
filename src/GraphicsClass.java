//First.java  
import java.applet.Applet;
import java.awt.Graphics;
import java.util.Scanner;

public class GraphicsClass extends Applet{

	int xMin = 60,yMin = 60,xMax = 150,yMax = 150;
	private final int TOP = 8;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int BOTTOM = 4;

	private int outCode0;
	private int outCode1;
	private int outCodeOut;


	public int[] getInput(){
		Scanner sc = new Scanner(System.in);

		int x[] = new int[2];
		int y[] = new int[2];

		int xy[] = new int[4];

		System.out.println("Enter the coordinate of the first point");
		x[0] = sc.nextInt();
		y[0] = sc.nextInt();

		System.out.println("Enter the coordinate of the second point");
		x[1] = sc.nextInt();
		y[1] = sc.nextInt();

		System.out.println("Enter the xmin and ymin");
		xMin = sc.nextInt();
		yMin = sc.nextInt();

		System.out.println("Enter the xmax and ymax");
		xMax = sc.nextInt();
		yMax = sc.nextInt();

		xy[0] = x[0];
		xy[1] = y[0];
		xy[2] = x[1];
		xy[3] = y[1];

		return xy;
	}

	public void paint(Graphics g){

		Scanner sc = new Scanner(System.in);

		int coordinate[] = new int[4];
		coordinate = this.getInput();

		g.drawLine(coordinate[0],coordinate[1],coordinate[2],coordinate[3]);
//		g.drawRect(xMin,yMin,xMax,yMax);

//		System.out.println("coordinate[0]"+coordinate[0]+"\ncoordinate[1]"+coordinate[1]+"\ncoordinate[2]"+coordinate[2]+"\ncoordinate[3]"+coordinate[3]);
		coordinate = this.cohenScutherlandClipping(coordinate[0],coordinate[1],coordinate[2],coordinate[3]);

//		int x[] = {40,170};
//		int y[] = {40,170};



//		int coordinate[] = new int[4];
//		coordinate = this.getInput();
//		coordinate = this.cohenScutherlandClipping(coordinate[0],coordinate[1],coordinate[2],coordinate[3]);

		System.out.println("coordinate[0]"+coordinate[0]+"\ncoordinate[1]"+coordinate[1]+"\ncoordinate[2]"+coordinate[2]+"\ncoordinate[3]"+coordinate[3]);
//		g.drawRect(xMin+200,yMin+200,xMax,yMax);
		g.drawLine(coordinate[0]+60,coordinate[1],coordinate[2]+60,coordinate[3]+60);
	}
	//This Method will compute code i:e top/right/left/bottom
	//This method takes two values of a coordinate
	//And provide a code which indicates where the point lies.
	public int computeCode(int x, int y){
		int code = 0;

		//This if-else construct checks whether the y component
		//is inside the window or not
		if (y > yMax){
			code = code|TOP;
		}else if (y < yMin){
			code = code|BOTTOM;
		}


		//This if else construct checks whether the x component
		//lies inside the clipping window.
		//
		if (x > xMax){
			code = code|RIGHT;
		}else if (x < xMin){
			code = code|LEFT;
		}

		return code;
	}

	public int[] cohenScutherlandClipping(int x0,int y0,int x1, int y1){

		boolean accept = false;
		boolean done = false;

		outCode0 = computeCode(x0,y0);
		outCode1 = computeCode(x1,y1);

		int x = 0;
		int y = 0;

		int arr1[]  = new int[4];

		int x0New = x0;
		int y0New = y0;
		int x1New = x1;
		int y1New = y1;

		do{
			if ((outCode0|outCode1)==0){
				accept = true;
				done = true;
			}else if ((outCode0&outCode1) != 0){
				done = true;
			}else {

				//This if-else construct determines Which point
				//is outside of the clipping window boundary
				if (outCode0 != 0){
					outCodeOut = outCode0;
				}else{
					outCodeOut = outCode1;
				}

				if ((outCodeOut & TOP)!=0){
					y = yMax;
					x = (int)Math.floor((x0+((x1-x0)/(y1-y0))*(yMax-y0))) ;
				}else if ((outCodeOut & BOTTOM)!=0){
					y= yMin;
					x = (int) Math.floor (x0+((x1-x0)/(y1-y0))*(yMin-y0));
				}else if ((outCodeOut & RIGHT)!=0){
					x= xMax;
					y = (int)Math.floor(y0+((y1-y0)/(x1-x0))*(xMax-x0));
				}else{
					x= yMin;
					y = (int)Math.floor(y0+((y1-y0)/(x1-x0))*(xMin-x0));
				}

			}

			if (outCodeOut == outCode0){
				x0New = x;
				y0New = y;
				outCode0 = computeCode(x0New,y0New);
			}else if(outCodeOut == outCode1){
				x1New = x;
				y1New = y;
				outCode1 = computeCode(x1New,y1New);
			}
		}while (!done);

		if(accept){
			System.out.println("x0:\t"+x0New+"\ty0:\t"+y0New+"\nx1:\t"+x1New+"\ty1:\t"+y1New);
			int arr[] = {x0New,y0New,x1New,y1New};
			return arr;
		}
		return arr1;
	}


}  
/* 
<applet code="First.class" width="300" height="300"> 
</applet> 
*/  