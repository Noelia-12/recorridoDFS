package data.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;
//import sun.jvm.hotspot.oops.Oops Printer;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class Graph {
    private boolean directed;
    // private boolean weighted;
    private ListLinked<Vertex> vertexList;
    private Vertex[] vertexs;
    private ListLinked<Edge> edgesList;
    private int numVertexs;

    private boolean isConnected;
    private int componentConnected;
    private int time;
	//private PriorityQueque<Vertex> queque2;

    public Graph(boolean directed) {
        this.directed = directed;
        isConnected = false;
        componentConnected = 0;
        vertexList = new ListLinked<>();
    }

    public Graph(boolean directed, int numVertexs) {
        this.directed = directed;
        isConnected = false;
        componentConnected = 0;
        vertexs = new Vertex[numVertexs];
    }

    public boolean isConnected() {
        BFS();
        return isConnected;
    }

    public int getComponentConnected() {
        return componentConnected;
    }

    public boolean getDirected() {
        return directed;
    }

    public ListLinked<Vertex> getVertexList() {
        return vertexList;
    }

    public Vertex[] getVertexs() {
        return vertexs;
    }

    public int getNumVertexs() {
        return numVertexs;
    }

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        v1.addEdge(edge);
        if (!directed) {
            Edge edge2 = new Edge(v2, v1, weight);
            v2.addEdge(edge2);
        }
    }

    public void DFS_Stack(Vertex vertex) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        // Queue<Vertex> queue = new LinkedList<>();// estructura de datos cola
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);
        vertex.setStatus(State.VISITADO);
        travelBFS.add(vertex);
        while (!stack.isEmpty()) {
            vertex = stack.pop();// vertice padre
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while (node != null) {
                Vertex opposite = node.getData().getV2();// vertices hijos
                if (opposite.getState() == State.NO_VISITADO) {
                    stack.add(opposite);
                    opposite.setStatus(State.VISITADO);

                    opposite.setJumps(vertex.getJumps() + 1);
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESADO);
        }
        Node<Vertex> temp = travelBFS.getHead();
        while (temp != null) {
            System.out.print(temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}\t");
            temp = temp.getLink();
        }
    }

    public void BFS(Vertex vertex) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();// estructura de datos cola
        queue.add(vertex);
        vertex.setStatus(State.VISITADO);
        travelBFS.add(vertex);
        while (!queue.isEmpty()) {
            vertex = queue.poll();// vertice padre
            ListLinked<Edge> lEdges = vertex.getEdges();
            Node<Edge> node = lEdges.getHead();
            while (node != null) {
                Vertex opposite = node.getData().getV2();// vertices hijos
                if (opposite.getState() == State.NO_VISITADO) {
                    queue.add(opposite);
                    opposite.setStatus(State.VISITADO);
                    opposite.setParent(vertex);
                    opposite.setJumps(vertex.getJumps() + 1);
                    travelBFS.add(opposite);
                }
                node = node.getLink();
            }
            vertex.setStatus(State.PROCESADO);
        }
        /*
         * Node<Vertex> temp = travelBFS.getHead(); while (temp != null) {
         * System.out.print(temp.getData().getLabel() + "{" + temp.getData().getJumps()
         * + "}\t"); temp = temp.getLink(); }
         */
    }

    public void BFS() {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while (iterator != null) {
            Vertex vertex = iterator.getData();
            if (vertex.getState().compareTo(State.NO_VISITADO) == 0) {
                BFS(vertex);
                componentConnected++;
                isConnected = componentConnected == 1;
            }
            iterator = iterator.getLink();
        }
    }

    /*public void DFS(Vertex vertex, int timeEntry, int timeExit) {
        Node<Vertex> iterator = vertexList.getHead();
        isConnected = false;
        while (iterator != null) {
            vertex = iterator.getData();
            if (vertex.getState().compareTo(State.NO_VISITADO) == 0) {
                DFS(vertex);
                componentConnected++;
                isConnected = componentConnected == 1;
                timeEntry++;
                timeExit = timeEntry + 1;
            }
            iterator = iterator.getLink();
        }
        timeEntry++;
        timeExit = timeEntry + 1;
        System.out.println("\nEntrada: " + vertex.getTimeEntry());
        System.out.println("\nSalida: " + vertex.getTimeExit());

    }*/

   /*public void DFS(Vertex vertex, ListLinked<Vertex>travelDFS)
    {
        vertex.setStatus(State.VISITADO);
        travelDFS.add(vertex);
        vertex.setTimeEntry(time);
        time++;
        Node<Edge> nEdge = vertex.getEdges().getHead();
        while(nEdge!= null)
        {
            if(oppositeVertex.getState().compareTo(State.NO_VISITADO)==0)
            {
                nEdge.getData().setType(TypeEdge.TREE);
                System.out.println(nEdge.getData());
                DFS(oppositeVertex,travelDFS);

            }
            else if(oppositeVertex.getState().compareTo(State.PROCESADO)==0)
            {
                nEdge.getData().setType(TypeEdge.LATER);
                System.out.println(nEdge.getData());
            }
            nEdge = nEdge.getLink();
        }
        vertex.setStatus(State.PROCESADO);
        vertex.setTimeExit(time);
        time++;

    }
    public void DFS(Vertex vertex)
    {
        LinkedList<Vertex>travelDFS = new LinkedList<>();
        time = 0;
        DFS(vertexStart, travelDFS);
        Node<Vertex>nVertex = travelDFS.getHead();
        while(nVertex != null)
        {
            Vertex vertex = nVertex.getData();
            System.out.println(vertex.getLabel() + " i(" + vertex.getTimeEntry() +") o ( " + vertex.getTimeExit() + ") state ( " + vertex.getState() + ")");
            nVertex = nVertex.getLink();
        }
    }*/

    
    public void Vertice_Articulation(Vertex vertex){
        Vertex act;
        vertex.setStatus(State.VISITADO);
        vertex.setEntrada(time+1);
        vertex.setSalida(time+1);
        int cantidad = 0;
        Node<Edge> near = vertex.getEdges().getHead(); //para los cercanos
         
        while(near != null){
            act = near.getData().getV2();
            if(act.getState().compareTo(State.NO_VISITADO) == 0){
                cantidad = cantidad + 1;
                act.setParent(vertex);
                //de forma recursiva
                Vertice_Articulation(act);
                vertex.setBajo(Math.min(vertex.getBajo(), act.getBajo()));

                if((vertex.getParent() == null && cantidad > 1) || (vertex.getParent() != null && act.getBajo() >= vertex.getEntrada()))
                {
                    Vertice_Articulation(vertex); //a la lista de los vertices que creamos al principio
                }
            }
            near = near.getLink(); //pasamos al siguiente
        }
    }


 public void Dijkstra(Vertex vertex)
 {
     vertex.setStatus(State.VISITADO);
     PriorityQueue<Vertex> queque = new PriorityQueue<>();
        queque.offer(vertex);
        while(!queque.isEmpty())
        { Vertex minWeiVertex =queque.poll();
            minWeiVertex.setStatus(State.PROCESADO);
            //System.out.println(minWeiVertex.getLabel() + " " + minWeiVertex.getSate());
            Node<Edge> nEdge = minWeiVertex.getEdges().getHead();
            while(nEdge != null)
            {
                Edge edge = nEdge.getData();
                Vertex opposite = edge.getV2();
                double pathCost = edge.getWeight() + minWeiVertex.getDijkstraValue();
                if(opposite.getState().compareTo(State.NO_VISITADO) == 0)
                {
                    opposite.setStatus(State.VISITADO);
                    opposite.setDijkstraValue(pathCost);
                    opposite.setParent(minWeiVertex);
                    queque.offer(opposite);

                }
                else if(opposite.getState().compareTo(State.VISITADO)==0)
                {
                    if(opposite.getDijkstraValue() > pathCost)
                    {
                        opposite.setDijkstraValue(pathCost);
                        opposite.setParent(minWeiVertex);
                    }

                }
            }
            System.out.println(opposite.getLabel() +  "" + opposite.getState() + " " + opposite.getDijkstraValue());
            
        }


 }

    public void shortesPaths() {
        BFS(vertexList.getHead().getData());
        System.out.println("Caminos cortos");
        for (int i = 0; i < vertexs.length; i++) {
            String route = printShortPath(getShortPath(vertexs[i]));
            System.out.println(vertexs[i].getLabel() + "(" + vertexs[i].getJumps() + "): " + route);
        }
    }

    public void shortPath(Vertex start, Vertex finish) {
        BFS(start);
        Stack<Vertex> shortPath = getShortPath(finish);
        printShortPath(shortPath);
    }

    public String printShortPath(Stack<Vertex> shortPath) {
        String output = "";
        if (!shortPath.isEmpty()) {
            while (!shortPath.isEmpty()) {
                output += shortPath.pop().getLabel();
                if (shortPath.size() >= 1) {
                    output += " -> ";
                }
            }
        } else {
            output += "Sin caminos cortos, vertice aislado";
        }
        // output += "\n";
        return output;
    }

    public Stack<Vertex> getShortPath(Vertex vertex) {
        Stack<Vertex> shortPath = new Stack<>();
        Vertex parent = vertex.getParent();
        if (parent != null) {
            shortPath.push(vertex);
            while (parent != null) {
                shortPath.push(parent);
                parent = parent.getParent();
            }
        }
        return shortPath;
    }

    public void printGraph() {
        ListLinked<Edge> edges;
        String output = "";
        for (int i = 0; i < vertexs.length; i++) {
            Vertex vertex = vertexs[i];
            output = output + vertex.getLabel();
            edges = vertex.getEdges();
            output = output + "(" + edges.size() + ") -> ";
            Node<Edge> temp = edges.getHead();
            while (temp != null) {
                output = output + "{" + temp.getData().getV2().getLabel() + "} ";
                temp = temp.getLink();
            }
            output = output + "\n";
        }
        System.out.println(output);
    }

    public void readFileInput(String filename) {
        String path = System.getProperty("user.dir") + "\\input\\" + filename;
        try {

            System.out.println(path);
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String line = "";
            line = scanner.next();
            Pattern pattern;
            Matcher matcher;

            pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            matcher = pattern.matcher(line);
            matcher.find();
            String strSize = matcher.group(1);
            System.out.println(strSize);

            vertexs = new Vertex[Integer.parseInt(strSize)];
            // Obteniendo las lineas de informacion de vertices
            while (!(line = scanner.nextLine()).equals(";")) {

                pattern = Pattern.compile("(\\d+)\\s*=\\s*(.+)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Vertex vertex = new Vertex(matcher.group(2));
                    addVertex(vertex);
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                }
                // System.out.println(line);
            }

            // Obteniendo las lineas de informacion de aristas
            while (!(line = scanner.nextLine()).equals(";")) {
                pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int posV1 = Integer.parseInt(matcher.group(1));
                    int posV2 = Integer.parseInt(matcher.group(2));
                    Vertex v1 = vertexs[posV1];
                    Vertex v2 = vertexs[posV2];
                    double weight = Double.parseDouble(matcher.group(3));
                    addEdge(v1, v2, weight);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        /*
         */

    }

    public static void main(String[] args) {
        Graph graph = new Graph(false);

        Vertex LaPaz = new Vertex("La Paz");
        Vertex Cochabamba = new Vertex("Cochabamba");
        Vertex SantaCruz = new Vertex("Santa Cruz");
        Vertex Riberalta = new Vertex("Riberalta");

        LaPaz.addEdge(new Edge(LaPaz, Cochabamba));
        LaPaz.addEdge(new Edge(LaPaz, SantaCruz));
        LaPaz.addEdge(new Edge(LaPaz, Riberalta));

        graph.addVertex(LaPaz);
        graph.addVertex(Cochabamba);
        graph.addVertex(SantaCruz);
        graph.addVertex(Riberalta);

        graph.readFileInput("bolivia.txt");
    }
}
