
public class DistancePath implements Comparable<DistancePath> 
{
	public Vertex dest;
	public double dist;
	
	public DistancePath(Vertex dest, double dist)
	{
		this.dest = dest;
		this.dist = dist;
		
	}
	public int compareTo(DistancePath d)
	{
		if(dist> d.dist)
		{
			return 1;
		}
		else if(dist<d.dist)
		{
			return -1;
		}
		else
			return 0;
		
	}
}
