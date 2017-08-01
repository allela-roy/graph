import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


//https://codereview.stackexchange.com/questions/48518/depth-first-search-breadth-first-search-implementation

public class Graph {
    private Set<Vertex>vertices;
    private Map<Vertex,Map<Vertex, Integer>>adjList;
    private boolean directed;
    
    public Graph(boolean _directed){
    	vertices =new HashSet<Vertex>();
    	adjList=new HashMap<Vertex,Map<Vertex, Integer>>(); 
    	directed=_directed;
    }
    public void addVertex(Vertex v){
    	vertices.add(v);
    }
    public void addEdge(Vertex source, Vertex dest, int weight){
    	if(directed){
    	
         Map<Vertex,Integer> srcNeighbors  = adjList.get(source);
    	 if (srcNeighbors == null) {
            adjList.put(source, srcNeighbors = new HashMap<Vertex,Integer>());
          }
    	 srcNeighbors.put(dest, weight);
    	}else{
    	 //we are creating an unidirected graph
    	Map<Vertex,Integer> srcNeighbors  = adjList.get(source);
        if (srcNeighbors == null) {
                adjList.put(source, srcNeighbors = new HashMap<Vertex,Integer>());
              }
        	 srcNeighbors.put(dest, weight);
    	 
        
    	 Map<Vertex,Integer> destNeighbors  = adjList.get(dest);
    	 if (destNeighbors == null) {
            adjList.put(dest, destNeighbors = new HashMap<Vertex,Integer>());
          }
    	 destNeighbors.put(source, weight);
    	 
    	}
    	 
    }
    public boolean isConnected(Vertex v, Vertex w){
    	Map<Vertex,Integer> neighbors = adjList.get(v);
    	if(neighbors.containsKey(w)){
    		return true;
    	}
    	return false;
	}
    
    public int getDistance(Vertex v, Vertex w){
    	Map<Vertex,Integer> neighbors = adjList.get(v);
    	if(neighbors.containsKey(w)){
    		return neighbors.get(w);
    	}
    	return -1;
    }
    public Map<Vertex,Integer>  getNeighbors(String vertex) {
    	Map<Vertex,Integer> neighbors = adjList.get(vertex);
        if (neighbors == null) {
            return null;
        } else {
            return neighbors;
        }
    }
    public void BFS(Vertex v){
    	
    	//remember to find if a path exists between two nodes pass BFS(startvertex, endvertex)..then goto place
		//System.out.println(v.name);
		Set<Vertex> visited = new HashSet<Vertex>();
		
		LinkedList<Vertex> queue=new LinkedList<Vertex>();
		visited.add(v);
		queue.push(v);
		
		
		while(!queue.isEmpty()){
			Vertex s=queue.poll();
			System.out.print(s.name+" ");
			
			Map<Vertex, Integer> map_list=adjList.get(s);
			
			//https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
			//https:stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
			for (Map.Entry<Vertex, Integer> entry : map_list.entrySet()) {
			    Vertex next= entry.getKey();
			  //@place  //check if( next==endvertex return true...i.e if we were to find path between two nodes BFS
			    if(!visited.contains(next)){
					 visited.add(next);
					 queue.push(next);
				}
			}

		}
		
		
	}
    
    public void DFS(){
    	System.out.println();
    	Set<Vertex> visited = new HashSet<Vertex>();
		
    	//System.out.println("Entered DFS");
    	for (Vertex vertex : vertices) {
    		if(!visited.contains(vertex)){
    			DFSUtil(vertex, visited);
    		}
		
		}
    }
    public void DFSUtil(Vertex v, Set<Vertex> visited){
		visited.add(v);
		
		System.out.print(v.name+" ");
		
		Map<Vertex, Integer> map_list=adjList.get(v);
		
		for (Map.Entry<Vertex, Integer> entry : map_list.entrySet()) {
		    Vertex next= entry.getKey();
		    if(!visited.contains(next)){
				DFSUtil(next, visited);
			}
		}

	}
    
    public static void main (String [] args){
    	Graph g=new Graph(false);
    	Vertex a=new Vertex("A");
    	Vertex b=new Vertex("B");
    	Vertex c=new Vertex("C");
    	Vertex d=new Vertex("D");
    	Vertex e=new Vertex("E");
    	
    	g.vertices.add(a);
    	g.vertices.add(b);
    	g.vertices.add(c);
    	g.vertices.add(d);
    	g.vertices.add(e);
    	
    	g.addEdge(a, b, 10);
    	g.addEdge(a, c, 10);
    	g.addEdge(b, c, 10);
    	g.addEdge(a, d, 10);
    	g.addEdge(b, e, 10);
   
    	
    	System.out.println(g.isConnected(a, b));
    	System.out.println(g.getDistance(b, a));;
    	
    	g.BFS(a);
    	g.DFS();
    	
    	//remember to find if a path exists between two nodes us
    }
}