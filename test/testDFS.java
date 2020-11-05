package test;
import data.structure.Graph;
import data.structure.Vertex;

public class testDFS {

    public static void main(String[] args) {
     
        Graph graph = new Graph(false);
        graph.readFileInput("testDFS.txt");
       // graph.printGraph();
        Vertex vertex = graph.getVertexList().getHead().getData();
        // graph.BFS(vertex);
        graph.DFS(vertex);
         graph.DFS(vertex,0,21);
         graph.printGraph();
         graph.shortesPaths();

       boolean isConnected = graph.isConnected();

      System.out.println("Es connectado: " + isConnected);
        System.out.println("Componentes conectados: " + graph.getComponentConnected());
    }
    
}
