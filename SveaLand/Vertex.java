import java.util.*;
public class Vertex 
{
	public String 	name;			//name
	public LinkedList<Edge> adj;		//neigbour vertex
	public double 	dist;			//cost???
	public Vertex 	prev;
	public int scratch;				//extra variable
	
	public Vertex(String name)
	{
		this.name = name;
		adj = new LinkedList<Edge>();
		
	}
	public void reset()
	{
		dist = Graph.INFINITY;
		prev = null;
		scratch = 0;
	}
}
