
public class Node 
{
	public String city; //city name
	public String color;  //help control visit condition in DFS and BFS, the value is WHITE, GRAY, BLACK
	public double d; //the distance from start to destination
	public double h; //the heuristic distance
	public Node parent; //the prior node in the path
	public double longitude;
	public double latitude;
	
	//initial node
	public Node(String cname, double lat, double lon)
	{
		this.city=cname;
		this.latitude = lat;
		this.longitude = lon;
	}

}
