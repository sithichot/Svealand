import java.io.*;
import java.util.*;

public class Graph {
	/**
	 * 
	 */
	public static final double INFINITY = Double.MAX_VALUE;
	private HashTable vertexMap = new HashTable();

	/**
	 * @param sourceName
	 * @param destName
	 * @param cost
	 */
	public void addEdge(String sourceName, String destName, double cost) {
		Vertex v = getVertex(sourceName);
		Vertex w = getVertex(destName);
		v.adj.add(new Edge(w, cost));
	}

	private Vertex getVertex(String vertexName) {
		Vertex v = vertexMap.get(vertexName);
		if (v == null) {
			v = new Vertex(vertexName);
			vertexMap.insert(v);
		}
		return v;
	}

	public void printPathK(String destName) {
		Vertex w = (vertexMap.get(destName));
		if (w == null)
			throw new NoSuchElementException("Destination vertex not found");
		else if (w.dist == INFINITY)
			System.out.println(destName + " is unreachable");
		else {
			System.out.print("(Cost is: " + w.dist + " km) ");
			printPath(w);
			System.out.println();
		}
	}

	public void printPathT(String destName) {
		Vertex w = (vertexMap.get(destName));
		if (w == null)
			throw new NoSuchElementException("Destination vertex not found");
		else if (w.dist == INFINITY)
			System.out.println(destName + " is unreachable");
		else {
			System.out.print("(Cost is: " + w.dist + " h) ");
			printPath(w);
			System.out.println();
		}
	}

	private void printPath(Vertex dest) {
		if (dest.prev != null) {
			printPath(dest.prev);
			System.out.print(" to ");
		}
		System.out.print(dest.name);
	}

	private void clearAll() {
		List[] temp = vertexMap.getArray();
		for (int i = 0; i < temp.length; i++) {
			List<Vertex> tmp = temp[i];
			for (int j = 0; j < tmp.size(); j++) {
				tmp.get(j).reset();
			}
		}
	}

	public void dijkstra(String startName) {
		PriorityQueue<DistancePath> pq = new PriorityQueue<DistancePath>();

		Vertex start = vertexMap.get(startName);
		if (start == null)
			throw new NoSuchElementException("Start vertex not found");

		clearAll();
		pq.add(new DistancePath(start, 0));
		start.dist = 0;

		int nodesSeen = 0;
		while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
			DistancePath vrec = pq.remove();
			Vertex v = vrec.dest;
			if (v.scratch != 0) // already processed v
				continue;

			v.scratch = 1;
			nodesSeen++;

			for (Edge e : v.adj) {
				Vertex w = e.dest;
				double cvw = e.dist;

				if (cvw < 0)
					throw new GraphException("Graph has negative edges");

				if (w.dist > v.dist + cvw) {
					w.dist = v.dist + cvw;
					w.prev = v;
					pq.add(new DistancePath(w, w.dist));
				}
			}
		}
	}

	class GraphException extends RuntimeException {
		public GraphException(String name) {
			super(name);
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph();

		try {
			Scanner scan = new Scanner(new File("svealand.txt"));
			while (scan.hasNext()) {
				String start = scan.next();
				String dest = scan.next();
				int dist = scan.nextInt();
				g.addEdge(start, dest, dist);
			}

			scan.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		;

		Scanner s = new Scanner(System.in);
		System.out.print("Start: ");
		String start = s.next();
		System.out.println();
		System.out.print("Destination: ");
		String dest = s.next();
		g.dijkstra(start);
		g.printPathK(dest);
	}
}
