import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	public ArrayList<Node> nodes = new ArrayList<Node>(); //store nodes in the graph
	public ArrayList<Node> expandedNodes = new ArrayList<Node>(); //store expanded nodes in the graph
	public int[][] adjMatrix; //edge is represented as weighted adjacency Matrix
	public double[][] heuMatrix; //edge is represented as heuristic Matrix
	public int size; //total amount of nodes
	public int pathNodeNum = 0;
	
	//add node to the graph
	public void addNode(Node n)
	{
		nodes.add(n);
	}
	
	//initialize adjMatrix
	public void initaladjMatrix()
	{
		size = nodes.size();
		adjMatrix = new int[size][size];
	}
	
	public void initalheuMatrix(){
		size = nodes.size();
		heuMatrix = new double[size][size];
	}
	
	//add weighted edge to the graph
	public void addEdge(String c1, String c2, int weight)
	{
		Node n1 = getNodefromName(c1);
		Node n2 = getNodefromName(c2);
		int sIndex=nodes.indexOf(n1);
		int eIndex=nodes.indexOf(n2);
		
		adjMatrix[sIndex][eIndex] = weight;
		adjMatrix[eIndex][sIndex] = weight;
	}
	
	public void calcheuMatrix(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(j != i){
					Node n1 = nodes.get(i);
					Node n2 = nodes.get(j);
					double Lat1 = n1.latitude;
					double Lat2 = n2.latitude;
					double Long1 = n1.longitude;
					double Long2 = n2.longitude;
					
					double v = 0;
					v = Math.sqrt( Math.pow((69.5*(Lat1 - Lat2)),2) + Math.pow((69.5 * Math.cos((Lat1 + Lat2)/360 * Math.PI)*(Long1 - Long2)),2) );
					
					heuMatrix[i][j] = v;
				}
				
			}
		}
		
	}
	
	// return node from city name
	public Node getNodefromName(String name){		
		for(int i = 0; i < size; i++){
			
			Node u = nodes.get(i);
			if(name.equals(u.city)){
				return u;			
			}
		}
		return null;

	}
	
	//get adjacent nodes of c
	public ArrayList<Node> getAdjNodes(Node c)
	{
		ArrayList adjNodes = new ArrayList<Node>();
		int i = nodes.indexOf(c);
		for(int j = 0; j < size; j++)
		{
			if(adjMatrix[i][j] != 0)
				adjNodes.add(nodes.get(j));
		}
		return adjNodes;
	}
	
	
	public void UNIFORM(Node startNode, Node endNode)
	{
		Node s = startNode;
		Node e = endNode;
		//initialize nodes
		for(int i = 0; i < size; i++)
		{
			nodes.get(i).color = "WHITE";
			nodes.get(i).parent = null;
		}
		s.color = "GRAY";
		s.parent = null;
		s.d = 0;
		s.h = 0;
		
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(s);
		while(!q.isEmpty())
		{
			Node u = getMin(q);
			q.remove(u);
			
			
		
			ArrayList<Node> adjNodes = new ArrayList<Node>();
			adjNodes = getAdjNodes(u);
			for(int i = 0; i < adjNodes.size(); i++)
			{
				Node v = adjNodes.get(i);
				if(v.color != "BLACK")
				{
					if(v.color == "WHITE"){
						v.color = "GRAY";
						v.parent = u;
						
						int sIndex=nodes.indexOf(u);
						int eIndex=nodes.indexOf(v);
						v.d = u.d + adjMatrix[sIndex][eIndex];
						v.h = 0;
						q.add(v);
					}
					else{
						
						int sIndex=nodes.indexOf(u);
						int eIndex=nodes.indexOf(v);
						double temp = u.d + adjMatrix[sIndex][eIndex];
						double temp2 = q.get(q.indexOf(v)).d + q.get(q.indexOf(v)).h;
						if(temp < temp2){
							v.parent = u;
							v.d = temp;
							v.h = 0;
						}
						
					}
				}
			}
			u.color = "BLACK";
			expandedNodes.add(u);
			
			if(u == e){
				return;
			}
			
			//removeMultiPath(q);
			

			
		}
		
	}
	
	public void BFS(Node startNode, Node endNode)
	{
		Node s = startNode;
		Node e = endNode;
		//initialize nodes
		for(int i = 0; i < size; i++)
		{
			nodes.get(i).color = "WHITE";
			nodes.get(i).parent = null;
		}
		s.color = "GRAY";
		s.parent = null;
		s.d = 0;
		s.h = 0;
		
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(s);
		while(!q.isEmpty())
		{
			Node u = q.get(0);
			q.remove(u);
			
		
			ArrayList<Node> adjNodes = new ArrayList<Node>();
			adjNodes = getAdjNodes(u);
			for(int i = 0; i < adjNodes.size(); i++)
			{
				Node v = adjNodes.get(i);
				if(v.color != "BLACK")
				{
					if(v.color == "WHITE"){
						v.color = "GRAY";
						v.parent = u;
						
						int sIndex=nodes.indexOf(u);
						int eIndex=nodes.indexOf(v);
						v.d = u.d + adjMatrix[sIndex][eIndex];
						v.h = 0;
						q.add(v);
					}
					/*else{
						
						int sIndex=nodes.indexOf(u);
						int eIndex=nodes.indexOf(v);
						double temp = u.d + adjMatrix[sIndex][eIndex];
						double temp2 = q.get(q.indexOf(v)).d + q.get(q.indexOf(v)).h;
						if(temp < temp2){
							v.parent = u;
							v.d = temp;
							v.h = 0;
						}
						
					}*/
				}
			}
			u.color = "BLACK";
			expandedNodes.add(u);
			
			if(u == e){
				return;
			}
			
			//removeMultiPath(q);
			

			
		}
		
	}
	
	public void DFS(Node startNode, Node endNode)
	{
		Node s = startNode;
		Node e = endNode;
		//initialize nodes
		for(int i = 0; i < size; i++)
		{
			nodes.get(i).color = "WHITE";
			nodes.get(i).parent = null;
		}
		s.color = "GRAY";
		s.parent = null;
		s.d = 0;
		s.h = 0;
		
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(s);
		while(!q.isEmpty())
		{
			Node u = q.get(q.size()-1);
			//q.remove(u);
			
		
			ArrayList<Node> adjNodes = new ArrayList<Node>();
			adjNodes = getAdjNodes(u);
			for(int i = 0; i < adjNodes.size(); i++)
			{
				Node v = adjNodes.get(i);
				if(v.color != "BLACK")
				{
					if(v.color == "WHITE"){
						v.color = "GRAY";
						v.parent = u;
						
						int sIndex=nodes.indexOf(u);
						int eIndex=nodes.indexOf(v);
						v.d = u.d + adjMatrix[sIndex][eIndex];
						v.h = 0;
						q.add(v);
						break;
					}
					else{
						
						q.remove(u);
						
					}
				}
			}
			u.color = "BLACK";
			expandedNodes.add(u);
			
			if(u == e){
				return;
			}
			
			//removeMultiPath(q);
			

			
		}
		
	}		
	
	
	public void Astar(Node startNode, Node endNode)
	{
		Node s = startNode;
		Node e = endNode;
		//initialize nodes
		for(int i = 0; i < size; i++)
		{
			nodes.get(i).color = "WHITE";
			nodes.get(i).parent = null;
		}
		s.color = "GRAY";
		s.parent = null;
		s.d = 0;
		s.h = 0;
		
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(s);
		while(!q.isEmpty())
		{
			Node u = getMin(q);
			q.remove(u);
			
			ArrayList<Node> adjNodes = new ArrayList<Node>();
			adjNodes = getAdjNodes(u);
			for(int i = 0; i < adjNodes.size(); i++)
			{
				Node v = adjNodes.get(i);
				if(v.color != "BLACK")
				{
					
					if(v.color == "WHITE"){
						v.color = "GRAY";
						v.parent = u;
						
						int sIndex=nodes.indexOf(u);
						int adjIndex=nodes.indexOf(v);
						int eIndex=nodes.indexOf(e);
						v.d = u.d + adjMatrix[sIndex][adjIndex];
						v.h = heuMatrix[adjIndex][eIndex];
						
						q.add(v);
					}
					else{
						
						int sIndex=nodes.indexOf(u);
						int adjIndex=nodes.indexOf(v);
						int eIndex=nodes.indexOf(e);
						
						double temp = u.d + adjMatrix[sIndex][adjIndex] + heuMatrix[adjIndex][eIndex];
						double temp2 = q.get(q.indexOf(v)).d + q.get(q.indexOf(v)).h;
						if(temp < temp2){
							v.d = u.d + adjMatrix[sIndex][adjIndex];
							v.h = heuMatrix[adjIndex][eIndex];
							v.parent = u;
						}
						
					}

				}
			}
			u.color = "BLACK";
			expandedNodes.add(u);
			if(u == e){
				return;
			}
			
		}
		
	}
	

	
	public Node getGreedyMin(ArrayList<Node> q){
		
		Node n = q.get(0);
		double tempmin = q.get(0).h;
		
		for(int i = 1; i < q.size(); i++){
			if(tempmin >= q.get(i).h){
				tempmin = q.get(i).h;
				n = q.get(i);
			}
		}
		
		return n;
	}
	
	public Node getMin(ArrayList<Node> q){
		
		Node n = q.get(0);
		double tempmin = q.get(0).d + q.get(0).h;
		
		for(int i = 1; i < q.size(); i++){
			if(tempmin >= (q.get(i).d + q.get(i).h)){
				tempmin = (q.get(i).d + q.get(i).h);
				n = q.get(i);
			}
		}
		
		return n;
	}

	
	//print path from s to v
	public void PrintPath(Node s, Node v)
	{
		if(v == s){
			System.out.print(s.city + ",");
			pathNodeNum++;
		}
		else if(v.parent == null)
			System.out.println("No path!");
		else
		{
			PrintPath(s,v.parent);
			System.out.print(v.city + ",");
			pathNodeNum++;
		}
	}
	
	public void PathNodeNum(){
		System.out.print("The number of nodes in the solution path: ");
		System.out.print(pathNodeNum);
	}
	
	//print the expanded nodes
	public void PrintExpandedNode(){
		System.out.print("The list of expanded nodes: ");
		for(int i = 0; i < expandedNodes.size(); i++){
			System.out.print(expandedNodes.get(i).city + ",");
		}
	}
	
	//print the number of expanded nodes
	public void PrintExpandedNodeNum(){
		System.out.print("The number of nodes expanded: ");
		System.out.print(expandedNodes.size());
	}

}
