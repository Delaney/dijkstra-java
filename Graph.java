/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.*;
import priorityq.*;

/**
 *
 * @author Delaney Sylvans
 */


/**
 * A collection of vertices and their edges
 * @author Delaney Sylvans
 */
public class Graph {
    
    String name;
    protected Set<Vertex> vertices;
    
    public Graph(){
        this.name = null;
        this.vertices = new HashSet<>();
    }
    
    public Graph(String name){
        this.name = name;
        this.vertices = new HashSet<>();
    }
    
    /**
     * Adds a Vertex to the Graph object
     * 
     * @param name of the vertex
     * 
     * @return <tt>true</true> if the vertex was added successfully
     */
    public boolean addVertex(String name){
        if (!vertexExists(name)){
            Vertex vertex = new Vertex(name);
            vertices.add(vertex);
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Prints a comma-separated list of the names of all vertices in the Graph object
     */
    public void printVertices(){
        String list = "";
        Iterator<Vertex> i = vertices.iterator(); 
        while (i.hasNext()){ 
            list += i.next().name + ", ";
        }
        System.out.println(list.substring(0, list.length()-2) + ".");
    }
    
    /**
     * Checks if a vertex exists
     * 
     * @param name
     * 
     * @return <tt>true</tt> if the vertex exists
     */
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
    
    /**
     * Returns a vertex in the Graph object
     * 
     * @param name
     * 
     * @return Vertex object
     */
    public Vertex getVertex(String name){
        Vertex result = null;
        for (Vertex vertex : vertices) {
            if (vertex.name == name){
                result = vertex;
                break;
            }
        }
        return result;
    }
    
    /**
     * Adds an edge between two vertices. Works by adding an edge to the Edge
     * list of the first vertex with the second vertex as the destination, and
     * vice-versa.
     * In essence, outgoing edges of the same weight from two vertices to one
     * another can count as one single non-directed edge
     * 
     * @param from
     * @param to
     * @param weight
     * 
     * @return <tt>true</tt> if the operation is successful
     */
    public boolean addEdge(String from, String to, int weight){
        if (vertexExists(from) && vertexExists(to)){
            Vertex source = getVertex(from);
            Vertex destination = getVertex(to);
            source.addEdge(to, weight);
            destination.addEdge(from, weight);
            return true;
        }
        else {
            return false;
        }
    }
    
    public void dijkstra(String startVertex){
        Map distances = new HashMap<>();
        Map paths = new HashMap<>();
        List<String> visited = new ArrayList<>();
        PriorityQ queue = new PriorityQ();
        queue.enqueue(startVertex, 0);
        visited.add(startVertex);
        
        Iterator<Vertex> ver = vertices.iterator();
        while(ver.hasNext()){
            Vertex v = ver.next();
            String dist;
            String path;
            String name = v.name;
            if (name.equals(startVertex)){
                dist = null;
                path = startVertex;
            }
            else {
                dist = "0";
                path = null;
            }
            distances.put(name, dist);
            paths.put(name, path);
        }
        
        String minElem = queue.dequeue().element;
        
        while (minElem != null){
            Set<Edge> adjacent = getVertex(minElem).edges;
            
            Iterator<Edge> e = adjacent.iterator();
            while(e.hasNext()){
                Edge item = e.next();
                String node = item.destination;
                String curr = (String) distances.get(node);
                String parent = (String) distances.get(minElem);
                int currentDistance = Integer.parseInt((curr == null ? "0" : curr));
                int parentDistance = Integer.parseInt((parent == null ? "0" : parent));
                int edgeWeight = item.weight;
                int newDistance = parentDistance + edgeWeight;
                
                if (!(visited.indexOf(node) >= 0)){
                    visited.add(node);
                    queue.enqueue(node, newDistance);
                }
                
                if (currentDistance == 0){
                    distances.put(node, Integer.toString(newDistance));
                    if (minElem == startVertex){
                        paths.put(node, minElem);
                    }
                    else {
                        String path = (String) paths.get(minElem);
                        paths.put(node, path + ", " + minElem);
                    }
                }
                else if (currentDistance > newDistance){
                    distances.put(node, Integer.toString(newDistance));
                    String currentPath = (String) paths.get(minElem);
                    
                    if (currentPath == minElem){
                        paths.put(node, paths.get(minElem));
                    }
                    else {
                        paths.put(node, paths.get(minElem) + ", " + minElem);
                    }
                }
            }
            PQElement deq = queue.dequeue();
            minElem = deq == null ? null : deq.element;
        }
        
        System.out.println("Shortest Distances:");
        Set<String> set = distances.keySet();
        Collection<String> c = distances.values();
        Iterator<String> i = set.iterator();
        Iterator<String> i2 = c.iterator();
        while(i.hasNext() && i2.hasNext()){
            String s = i.next();
            String s2 = i2.next();
            System.out.println(s + ": " + s2);
        }
        System.out.println("");
        System.out.println("Shortest Paths:");
        Set<String> set2 = paths.keySet();
        Collection<String> c2 = paths.values();
        Iterator<String> ii = set2.iterator();
        Iterator<String> ii2 = c2.iterator();
        while(ii.hasNext() && ii2.hasNext()){
            String s = ii.next();
            String s2 = ii2.next();
            System.out.println(s + ": " + s2);
        }        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph nav = new Graph("Unilag");
        
        nav.addVertex("1st Gate"); //1st Gate
        nav.addVertex("Chapel"); //Church/Mosque
        nav.addVertex("Faculty of Education"); //Faculty of Education
        nav.addVertex("Amina/Kofo/Biobaku"); //Newest Hall
        nav.addVertex("New Hall"); //New Hall
        nav.addVertex("ISL/DLI"); //ISL
        nav.addVertex("Health Center"); //Health Center
        nav.addVertex("Moremi Car Park"); //Moremi Hall/Mariere Hall
        nav.addVertex("Senate Building"); //Senate Building/Faculty of Arts
        nav.addVertex("Jaja"); //Jaja Hall
        nav.addVertex("Ozolua"); //Ozolua
        nav.addVertex("Faculty of Sciences"); //Faculty of Sciences
        nav.addVertex("Library"); //Library/Lagoon Front
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
        
        nav.dijkstra("1st Gate");
        
    }
    
    
}