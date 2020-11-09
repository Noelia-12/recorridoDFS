package data.structure;

public class Vertex {
    private String label;
    private ListLinked<Edge> edges;
    private State state;
    private int jumps;
    private Vertex parent;
    private int timeEntry;
    private int timeExit;
    private double dijkstraValue;
    private int entrada;
    private int salida;
private int bajo;

    public Vertex(String label) {
        this.label = label;
        this.jumps = 0;
        timeEntry = 0;
        timeExit = 0;
        entrada = 0;
        salida = 0;
        dijkstraValue = 0;

        edges = new ListLinked<>();
        state = State.NO_VISITADO;
    }

    public  void setDijkstraValue(double dijkstraValue)
    {
        this.dijkstraValue = dijkstraValue;
    }

    
    public  double getDijkstraValue()
    {
       return dijkstraValue;
    }


    public  int getBajo()
    {
       return bajo;
    }

    public void setBajo(int bajo) {
        this.bajo= bajo;
    }
    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setSalida(int salida) {
        this.salida= salida;
    }

    public int getSalida() {
        return salida;
    }


    public void setTimeEntry(int timeEntry) {
        this.timeEntry = timeEntry;
    }

    public int getTimeEntry() {
        return timeEntry;
    }

    public void setTimeExit(int timeExit) {
        this.timeExit = timeExit;
    }

    public int getTimeExit() {
        return timeExit;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex getParent() {
        return parent;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public void setStatus(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
    }

    public String getLabel() {
        return label;
    }

    public ListLinked<Edge> getEdges() {
        return edges;
    }

    public String toString() {
        return "Vertex={label={" + label + "},edges={" + edges + "}}";
    }
}
