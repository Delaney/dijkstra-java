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
public class Dijkstra {
    private Map distances;
    private Map paths;
    private List<String> visited;
    private PriorityQ queue;
    
    private boolean isVisited(String vertex){
        return this.visited.indexOf(vertex) >= 0;
    }
    
    public Dijkstra(Graph graph, String start){
        this.distances = new HashMap<>();
        this.paths = new HashMap<>();
        this.visited = new ArrayList<>();
        this.queue = new PriorityQ();
        
        this.queue.enqueue(start, 0);
        this.visited.add(start);
        
        Iterator<Vertex> ver = graph.vertices.iterator();
        while(ver.hasNext()){
            Vertex v = ver.next();
            String dist;
            String path;
            String name = v.name;
            if (name.equals(start)){
                dist = null;
                path = start;
            }
            else {
                dist = "0";
                path = null;
            }
            this.distances.put(name, dist);
            this.paths.put(name, path);
        }
        
        String minElem = this.queue.dequeue().element;
        
        while (minElem != null){
            Set<Edge> adjacent = graph.findVertex(minElem).edges;
            
            Iterator<Edge> e = adjacent.iterator();
            while(e.hasNext()){
                Edge item = e.next();
                String node = item.destination;
                String curr = (String)this.distances.get(node);
                String parent = (String) this.distances.get(minElem);
                int currentDistance = Integer.parseInt((curr == null ? "0" : curr));
                int parentDistance = Integer.parseInt((parent == null ? "0" : parent));
                int edgeWeight = item.weight;
                int newDistance = parentDistance + edgeWeight;
                
                if (!isVisited(node)){
                    this.visited.add(node);
                    this.queue.enqueue(node, newDistance);
                }
                
                if (currentDistance == 0){
                    this.distances.put(node, Integer.toString(newDistance));
                    if (minElem == node){
                        this.paths.put(node, minElem);
                    }
                    else {
                        String path = (String) this.paths.get(node);
                        this.paths.put(node, path + ", " + minElem);
                    }
                }
                else if (currentDistance > newDistance){
                    this.distances.put(node, Integer.toString(newDistance));
                    String currentPath = (String) this.paths.get(minElem);
                    
                    if (currentPath == minElem){
                        this.paths.put(node, this.paths.get(minElem));
                    }
                    else {
                        this.paths.put(node, this.paths.get(minElem) + ", " + minElem);
                    }
                }
            }
            PQElement deq = this.queue.dequeue();
            minElem = deq == null ? null : deq.element;
        }
        
        System.out.println("Shortest Distances:");
        Set<String> set = this.distances.keySet();
        Collection<String> c = this.distances.values();
        Iterator<String> i = set.iterator();
        Iterator<String> i2 = c.iterator();
        while(i.hasNext() && i2.hasNext()){
            String s = i.next();
            String s2 = i2.next();
            System.out.println(s + ": " + s2);
        }
        System.out.println("");
        System.out.println("Shortest Paths:");
        Set<String> set2 = this.paths.keySet();
        Collection<String> c2 = this.paths.values();
        Iterator<String> ii = set2.iterator();
        Iterator<String> ii2 = c2.iterator();
        while(ii.hasNext() && ii2.hasNext()){
            String s = ii.next();
            String s2 = ii2.next();
            System.out.println(s + ": " + s2);
        }
    }
}
