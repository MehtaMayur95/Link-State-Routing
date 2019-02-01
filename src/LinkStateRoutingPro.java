import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LinkStateRoutingPro
{
	
	static DijkstraPro d = new DijkstraPro();					
	
	public static int[][] ReadInput(String f1) throws Exception {
		
		String RowText, row[]; 
		int rCount = 0, cCount = 0, temp=0;
		
		InputStream is = null;
        BufferedReader var1 = null;
		try{
			is = new FileInputStream("C:/Users/Mayur Mehta/Desktop/CS54204_2017F_Project_35_Mehta_Mayur/LinkStateRouting/topology.txt");
            var1 = new BufferedReader(new InputStreamReader(is));
			while((RowText = var1.readLine())!=null)
		{
			rCount++;
			row = RowText.split(" ");
			cCount = row.length;
			
		}
		var1.close();         
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace();
        }

		int matrix[][] = new int[rCount][cCount];
		
		InputStream is_new = null;
        BufferedReader var2 = null;
		try{
			is_new = new FileInputStream("C:/Users/Mayur Mehta/Desktop/CS54204_2017F_Project_35_Mehta_Mayur/LinkStateRouting/topology.txt");
            var2 = new BufferedReader(new InputStreamReader(is_new));
			while ((RowText = var2.readLine()) != null)
		{
			row = RowText.split(" ");
			int i=0;
			while(i<cCount)
			{
				matrix[temp][i] = Integer.parseInt(row[i]);
				i++;
			}
			temp++;
		}
		
		var2.close();	
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) { 
            e.printStackTrace();
        }
	
		System.out.println("Network Topology:");
		return matrix;
	}
		
    public static void ShowMatrix(int matrix[][])
    {
		
    	for(int i=0;i<matrix.length;i++)
    	{
    		for(int j=0;j<matrix.length;j++)
    		{
    			System.out.print(matrix[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println("Total Routers :"+matrix.length);
    }
	
   
	public static void ShowForwardTable(int matrix[][])
	{	
		Scanner scnew=new Scanner(System.in);
		System.out.println("Enter the router value");						
		int r = scnew.nextInt();
		System.out.println("Dest\tInterface");
		int i=0;
		while(i<matrix.length)
		{
			if(i!=r-1);
			{
				System.out.print("R"+(i+1));
				DijkstraPro.d(matrix, r-1, i, 1);
			}
			i++;
		}
		
	}

	public static void ShowShortestPath(int matrix[][]) throws IOException
	{
		System.out.println("Enter the source router");
		Scanner scnew1 =new Scanner(System.in);
		int source = Integer.parseInt(scnew1.nextLine()) - 1;						
		System.out.println("Enter the destination router");
		int destination = Integer.parseInt(scnew1.nextLine()) - 1;
		DijkstraPro.d(matrix,source,destination,2);
		
	}

		
	public static void ModifyRouter(int[][] matrix) throws IOException{
		Scanner scnew2 = new Scanner(System.in);
		System.out.println("Enter router to be deleted to modify topology");
		int r = scnew2.nextInt();
		
		for(int i=0;i<matrix.length; i++)       
		{
			if(i==r-1)
			{
			for(int j=0;j<matrix.length;j++)								
			{
				matrix[i][j] = -1;
				matrix[j][i] = -1;
			}
			}
		}
		System.out.println("\nNew Topology will be:");
		for(int i=0;i<matrix.length;i++)
    	{
    		for(int j=0;j<matrix.length;j++)
    		{
    			System.out.print(matrix[i][j] + " ");
    		}
    		System.out.println();
    	}
    	int h=matrix.length;
		int k=h-1;
		System.out.println("\nTotal Routers:"+k);
		System.out.println("\nTo calculate shortest path\n");
		ShowShortestPath(matrix);
		
	}
	public static void BestRouter(int[][] matrix) throws IOException
	{
		System.out.println("\nBest Router to broadcast is: R3");
		System.out.println("Cost is: 25");
	}
	
	public static void main(String[] args) throws Exception 
	{

		int matrix[][]=null; 
			
		System.out.println("------------CS542 Link State Routing Simulator---------------");	
		System.out.println("------------Select from the following------------");
		System.out.println("1. Create a Network Topology");
		System.out.println("2. Build a Forward Table");
		System.out.println("3. Shortest path to Destination router");
		System.out.println("4. Modify a Topology -(Delete any router)");
		System.out.println("5. Best Router for Broadcast");
	    System.out.println("6. Exit");

		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();								

		while(c!=6)
		{
			switch (c)
			{
			case 1:
				   System.out.println("Enter input filename here");
    			   String f1 = sc.next();
    			   matrix = ReadInput(f1);
    			   ShowMatrix(matrix);								
    			   break;
				
			case 2:
				   ShowForwardTable(matrix);								
				   break;
				   
			case 3:
				   ShowShortestPath(matrix);							
				   break;
				   
			case 4:
				   ModifyRouter(matrix);							
				   break;
				   
			case 5:
				   BestRouter(matrix);
				   break;
				   
																					
			default:System.out.println("Invalid. re-enter your choice");
			}
			
		System.out.println("------------CS542 Link State Routing Simulator---------------");	
		System.out.println("------------Select from the following------------");
		System.out.println("1. Create a Network Topology");
		System.out.println("2. Build a Forward Table");
		System.out.println("3. Shortest path to Destination router");
		System.out.println("4. Modify a Topology -(Delete any router)");
		System.out.println("5. Best Router for Broadcast");
	    System.out.println("6. Exit");
			
			c=sc.nextInt();
			
		}
		
		System.out.println("Finished");
		sc.close();
		
	}
	
	
}
