/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityq;
import java.util.*;

/**
 *
 * @author Delaney Sylvans
 */
public class PriorityQ {
    List<PQElement> queue;
   
    public PriorityQ(){
        this.queue = new ArrayList<>();
    }
    
    public void enqueue(String element, int priority){
        PQElement qE = new PQElement(element, priority);
        boolean contain = false;
        
        Iterator<PQElement> i = this.queue.iterator();
        while (i.hasNext()){ 
            PQElement e = i.next();
            if (e.priority > qE.priority){
                int index = this.queue.indexOf(e);
                this.queue.add(index, qE);
                contain = true;
                break;
            }
        }
        
        if (!contain){
            this.queue.add(qE);
        }
    }
    
    public PQElement dequeue(){
        if (isEmpty()){
            return null;
        }
        return this.queue.remove(0);
    }
    
    public PQElement front(){
        if (isEmpty()){
            return null;
        }
        return this.queue.get(0);
    }
    
    public PQElement rear(){
        if (isEmpty()){
            return null;
        }
        return this.queue.get(this.queue.size()-1);
    }
    
    public boolean isEmpty(){
        return this.queue.isEmpty();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
