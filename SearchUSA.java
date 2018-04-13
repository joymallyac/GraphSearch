import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//////////////////////////////////////////////
/////          name: Joymallya Chakraborty   /////
/////          unity ID: jchakra            /////
//////////////////////////////////////////////

public class SearchUSA {

	public static void main(String[] args) throws IOException{

		Graph g = new Graph();
		ExtractCity(g);

		g.initaladjMatrix();
		g.initalheuMatrix();

		ExtractRoad(g);
		g.calcheuMatrix();
				Node src = null;
				Node des = null;
				for(int i = 0; i < g.size; i++)
				{
					Node u = g.nodes.get(i);
					if(args[1].equals(u.city))
					{
						src = u;
					}
					if(args[2].equals(u.city)){
						des = u;
					}
				}

				if(src!=null && des!=null)
				{

					if(args[0].equals("astar"))
					{
						g.Astar(src, des);
					}
					if(args[0].equals("BFS"))
					{
						g.BFS(src, des);
					}
					if(args[0].equals("DFS"))
					{
						g.DFS(src, des);
					}
				}
				else
					System.out.println("City not found");


		g.PrintExpandedNode();
		System.out.println();

		g.PrintExpandedNodeNum();
		System.out.println();

		System.out.print("The list of nodes in the solution path: ");
		g.PrintPath(src,des);
		System.out.println();

		g.PathNodeNum();
		System.out.println();

		System.out.print("The total distance the solution path: ");
		System.out.print(des.d + des.h);

	}

	public static void ExtractCity(Graph g){
		try {
            Scanner in = new Scanner(new File("city.txt"));

            while (in.hasNextLine()) {
                String str = in.nextLine();
                String str2 = str.replace(" ", "");


                String[] array = new String[3];
                array = str2.split(",");
                Node n = new Node(array[0], Double.parseDouble(array[1]),Double.parseDouble(array[2]));
        		g.addNode(n);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	public static void ExtractRoad(Graph g){
		try {
            Scanner in = new Scanner(new File("road.txt"));

            while (in.hasNextLine()) {
                String str = in.nextLine();
                String str2 = str.replace(" ", "");


                String[] array = new String[3];
                array = str2.split(",");
                g.addEdge(array[0],array[1],Integer.parseInt(array[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	public int add(int a,int b){
    int c = 0;
	return (float)(a+b);

	}

    public static int sub(int a,int b){

	return (float)(a-b);

	}



}

