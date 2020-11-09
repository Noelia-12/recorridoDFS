package test;
import data.structure.Graph;
import data.structure.Vertex;

public class testDFS {

  public static void testGraphDFS()
  {Graph graph = new Graph(false);
    graph.readFileInput("testDFS.txt");
   graph.printGraph();
    //graph.DFS(graph.getVertexList().getHead().getData());
    System.out.println("vertice de articulacion:  ")
    graph.Vertice_Articulation(graph.getVertexList().getHead().getData());
    graph.printGraph();
    graph.Dijkstra(graph.getVertexList().getHead().getData());
    graph.printGraph();

  }
    public static void main(String[] args) {
     
        testGraphDFS();

    }
    
}
