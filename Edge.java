/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Delaney Sylvans
 */
public class Edge {
    public String source;
    public String destination;
    public int weight;
    
    public Edge(String source, String destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
