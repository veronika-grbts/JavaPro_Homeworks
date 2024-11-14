package lesson20;

import java.util.ArrayList;

public class Graph {
    ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);

        graph.addEdge(1,2);
        graph.addEdge(3,4);
        System.out.println("Graph:");
        graph.printGraph();

        graph.addEdge(4,0);
        System.out.println("Graph with edge 4,0:");
        graph.printGraph();
        graph.removeEdge(4,0);
        System.out.println("Graph without edge 4,0:");
        graph.printGraph();
        System.out.println("edge 0,2 " + graph.hasEdge(0,2));
        System.out.println("edge 4,0 " + graph.hasEdge(4,0));
        System.out.println("vertex 5 is exist "+ graph.hasVertex(5));
        System.out.println("vertex 0 is exist "+ graph.hasVertex(0));
        graph.removeVertex(4);
        System.out.println("Graph without 4 vertex:");
        graph.printGraph();
    }

    public Graph() {
    }

    public void addVertex(int vertex){
        list = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            list.add(new ArrayList<>());
        }
    }
    public void addEdge(int source, int destination) {
        list.get(source).add(destination);
        list.get(destination).add(source);
    }

    public void removeEdge(int vertex1, int vertex2) {
        if (hasVertex(vertex1) && hasVertex(vertex2)) {
            list.get(vertex1).remove((Integer) vertex2);
            list.get(vertex2).remove((Integer) vertex1);
        }
    }

    private boolean  hasVertex(int vertex) {
        return vertex >= 0 && vertex < list.size();
    }

    public void removeVertex(int vertex){
        list.remove(vertex);
    }

    public boolean hasEdge(int source, int destination){
        if (hasVertex(source) && hasVertex(destination)) {
            return list.get(source).contains(destination);
        }
        return false;
    }

    public void printGraph() {
        for (int i = 0; i < list.size(); i++) {
            System.out.print("Vertex " + i + ": ");
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(" -> " + list.get(i).get(j));
            }
            System.out.println();
        }
    }
}
