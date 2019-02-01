
public class DijkstraPro
{

	public static Object d(int[][] matrix, int source , int destination ,int c) 
	{
		
		
		int[] dis = new int[matrix.length];						// distance[] array will maintain shortest
																    	//distance between 2 routers
		int[] vis = new int[matrix.length];							//visited[] array will keep track of visited 
																		//routers to avoid same router to be considered for shortest path calculation 			
		int TempNext = source;											// An index to predecessor array 
		
			int j=0;
			while(j<matrix[0].length)
			{
				dis[j] = matrix[source][j];
				j++;
			}
		
		int[] pre = new int[matrix[0].length];					//initialize predecessor that will help further to traverse path
			
			int i=0;
			while(i<pre.length)
			{
				pre[i] = source;
				i++;
			}
		vis[TempNext] = 1;							     		// initially visited of source make it as true
		
		for(int x=0;x<matrix[0].length; x++)							//sort distance array based on minimum path weight, to determine shortest path between routers //calculation
		{
			int min = Integer.MAX_VALUE;

			for (int l = 0; l < dis.length; l++)				
			{
				
				if (vis[l]!=1 && l != source && dis[l] != -1) 
				{
					
					if (dis[l] < min)
					{
						min = dis[l];
						TempNext = l;
					}
				}
			}
			
			if (TempNext == destination) 										
			{
				break;
			}
			
			vis[TempNext] = 1;		
			int p=0;			//int i=0;
			while(p<dis.length)
			{
				if(vis[p] != 1 && dis[p] == -1 && matrix[TempNext][p] != -1)					//handle routers with -1 weight means routers having no link/connectivity
					{
						dis[p] =  matrix[TempNext][p] + dis[TempNext];
						pre[p] = TempNext;
					}
					
					else if(matrix[TempNext][p] != -1 && dis[TempNext] > min+matrix[TempNext][p])
					{
						dis[p] =  matrix[TempNext][p] + dis[TempNext];
						pre[p] = TempNext;	
					}
					p++;
			}
			
		}
		
		
		/*Hops in to this when user opts for creating shortest path option 3. User provided source and 
		 * destination values are passed further to output minimum path*/
		
		if(c==2 ) 
		{
			Traversal(pre, source, destination, dis.length,1);
			System.out.println();
			int total = dis[destination] - dis[source];
			if(total == -1)																	// when user request for the router that is down or removed from the topology
			{
				System.out.println("Router is down, its not working. Please choose different router");
			}
			else
			{
			System.out.println("Total cost will be =  "+ total);
			}
	    }
		
		/*To determine connection table for the router user has requested*/
		
		if(c==1)
		{
			Traversal(pre, source, destination, dis.length, 2);
		}
		else return null;
		
		return null;
    }
	
	
	/*This function output's shortest path between two routers and it also populates connection router table */
	
	public static void Traversal(int[] pre, int source, int destination, int length, int c)
	{
		
		int current = 1;
		int[] edgePath = new int[length];
		int i = destination;
		edgePath[0] = i;
		
		
		boolean flag=false;
		
		while (pre[i] != source) 			// aligns predecessor routers to edgePath[] array in order to output the path
		{
			i = pre[i];
			edgePath[current] = i;
			current++;
		}
		edgePath[current] = source;

		if(c==1) 																// triggered when user wants to output the shortest path between source and destination
		{
			System.out.print("Shortest path from " + (source + 1) + " to " + (destination + 1));
			System.out.println();
			int k = current;
			while(k>0)
			{
				System.out.print("R" + (edgePath[k] + 1) + " to ");
				k--;
			}
			
			System.out.print("R" + (edgePath[0] + 1)) ;
		}
		
		
		if(c==2) 															// triggered when user wants to get connection table for specified router
		{	
			
			if(current>0)
			{
				int x = current-1;
				while(x>0)
				{
					System.out.print("\tR" + (edgePath[x] + 1) + "\n");
					flag=true;
					x--;
				}
				
			}

			if(flag==false)
			{
				System.out.print("\tR" + (edgePath[0] + 1)+"\n") ;
			}

		}
	}
}
