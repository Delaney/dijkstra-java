/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.*;

/**
 *
 * @author Delaney Sylvans
 */
public class Vertex {
    public String name;
    private Set<Edge> edges;
    
    public Vertex(String name){
        this.name = name;
        this.edges = new HashSet<>();
    }
    
    public String getName(){
        return this.name;
    }
    
    public boolean addEdge(String to, int weight){
        boolean exists = false;
        for (Edge edge : edges) {
            if (edge.destination == to){
                exists = true;
                break;
            }
        }
        if (exists) return false;
        else {
            Edge edge = new Edge(this.name, to, weight);
            this.edges.add(edge);
            return true;
        }
    }
    
    public void printEdges(){
        String list = "";
        Iterator<Edge> i = this.edges.iterator();
        while (i.hasNext()){ 
            Edge e = i.next();
            list += "To: " + e.destination + ", Weight: " + e.weight + "\n";
        }
        System.out.println(list);
    }
    
    public int noOfEdges(){
        return this.edges.size();
    }
    
}
