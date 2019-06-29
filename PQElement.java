/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityq;

/**
 *
 * @author Delaney Sylvans
 */
public class PQElement {
    public String element;
    public int priority;
    
    public PQElement(String element, int priority){
        this.element = element;
        this.priority = priority;
    }
}
