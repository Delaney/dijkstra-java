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
public class Graph {
    
    String name;
    private Set<Vertex> vertices;
    
    public Graph(){
        this.name = null;
        this.vertices = new HashSet<>();
    }
    
    public  Graph(String name){
        this.name = name;
        this.vertices = new HashSet<>();
    }
    
    public void addVertex(String name){
        Vertex vertex = new Vertex(name);
        vertices.add(vertex);
    }
    
    public void printVertices(){
        String list = "";
        Iterator<Vertex> i = vertices.iterator(); 
        while (i.hasNext()){ 
            list += i.next().name + ", ";
        }
        System.out.println(list.substring(0, list.length()-2) + ".");
    }
    
    public boolean vertexExists(String name){
        boolean result = false;
        for (Vertex vertex : vertices) {
            if (vertex.name == name){
                result = true;
                break;
            }
        }
        return result;
    }
    
    public Vertex findVertex(String name){
        Vertex result = null;
        for (Vertex vertex : vertices) {
            if (vertex.name == name){
                result = vertex;
                break;
            }
        }
        return result;
    }
    
    public boolean addEdge(String from, String to, int weight){
        if (vertexExists(from) && vertexExists(to)){
            Vertex source = findVertex(from);
            Vertex destination = findVertex(to);
            source.addEdge(to, weight);
            destination.addEdge(from, weight);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph unilag = new Graph("Unilag");
        
        unilag.addVertex("1st Gate");
        unilag.addVertex("Education");
        unilag.addVertex("New Hall");
        
        unilag.printVertices();
        
        unilag.addEdge("1st Gate", "Education", 2);
        unilag.addEdge("1st Gate", "New Hall", 5);
        unilag.addEdge("New Hall", "Education", 3);
        
        System.out.println(unilag.findVertex("Education").noOfEdges());
        
    }
    
    
}