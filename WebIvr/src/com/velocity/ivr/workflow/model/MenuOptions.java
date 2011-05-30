/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.model;

import java.util.Map;

/**
 *
 * @author kapil
 */
public class MenuOptions {
    private int length;
    private Map<Integer, String> options;
    private char endsWith;
    private int enteredValue;

    public char getEndsWith() {
        return endsWith;
    }

    public void setEndsWith(char endsWith) {
        this.endsWith = endsWith;
    }

    public int getEnteredValue() {
        return enteredValue;
    }

    public void setEnteredValue(int enteredValue) {
        this.enteredValue = enteredValue;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer, String> options) {
        this.options = options;
    }

    public String getId(int value){
        return this.options.get(value);
    }

    public boolean isValid(){
        return this.options.containsKey(this.enteredValue);
    }
}
