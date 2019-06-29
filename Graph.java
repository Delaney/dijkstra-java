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
    protected Set<Vertex> vertices;
    
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
        Graph nav = new Graph("Unilag");
        
        nav.addVertex("1st Gate"); //1st Gate
        nav.addVertex("Chapel"); //Church/Mosque
        nav.addVertex("Faculty of Education"); //Education
        nav.addVertex("Amina/Kofo/Biobaku"); //Newest Hall
        nav.addVertex("New Hall"); //New Hall
        nav.addVertex("ISL/DLI"); //ISL
        nav.addVertex("Health Center"); //Health Center
        nav.addVertex("Moremi Car Park"); //Moremi Car Park
        nav.addVertex("Senate Building"); //Senate Building
        nav.addVertex("Jaja"); //Jaja
        nav.addVertex("Ozolua"); //Ozolua
        nav.addVertex("Faculty of Sciences"); //Sciences
        nav.addVertex("Library"); //Library
        nav.addVertex("Highrise"); //Highrise

        nav.addEdge("1st Gate", "Chapel", 2);
        nav.addEdge("1st Gate", "Faculty of Education", 1);

        nav.addEdge("Chapel", "New Hall", 1);
        nav.addEdge("Chapel", "ISL/DLI", 4);

        nav.addEdge("Faculty of Education", "Amina/Kofo/Biobaku", 1);

        nav.addEdge("Amina/Kofo/Biobaku", "ISL/DLI", 2);

        nav.addEdge("New Hall", "ISL/DLI", 4);
        nav.addEdge("New Hall", "Health Center", 3);
        nav.addEdge("New Hall", "Moremi Car Park", 2);
        nav.addEdge("New Hall", "Senate Building", 3);

        nav.addEdge("ISL/DLI", "Health Center", 1);
        nav.addEdge("ISL/DLI", "Jaja", 2);
        nav.addEdge("ISL/DLI", "Ozolua", 3);

        nav.addEdge("Health Center", "Jaja", 1);

        nav.addEdge("Moremi Car Park", "Senate Building", 2);
        nav.addEdge("Moremi Car Park", "Jaja", 1);

        nav.addEdge("Senate Building", "Jaja", 3);
        nav.addEdge("Senate Building", "Library", 1);

        nav.addEdge("Jaja", "Ozolua", 2);
        nav.addEdge("Jaja", "Faculty of Sciences", 1);
        nav.addEdge("Jaja", "Library", 2);

        nav.addEdge("Ozolua", "Highrise", 2);

        nav.addEdge("Faculty of Sciences", "Library", 2);
        
        //System.out.println(unilag.findVertex("Education").noOfEdges());
        Dijkstra d = new Dijkstra(nav, "1st Gate");
        
    }
    
    
}