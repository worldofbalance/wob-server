/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvg;

/**
 *
 * @author justinacotter
 */
public class ConvergeHint {
    
    protected int hintId;
    protected String text;
    
    public ConvergeHint () {
 
    }
    
    public ConvergeHint (int hintId, String text) {
        this.hintId = hintId;
        this.text = text;
    }

    public int getHintId () {
        return hintId;
    }
    
    public String getText () {
        return text;
    }
    
    public void setHintId (int hintId) {
        this.hintId = hintId;
    }
    
    public void setText (String text) {
        this.text = text;
    }
}
