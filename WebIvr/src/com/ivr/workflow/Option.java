/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

/**
 *
 * @author kapil
 */
public interface Option {

    String getName();

    String getOnSelect();

    String getValue();

    void setName(String name);

    void setOnSelect(String onSelect);

    void setValue(String value);
    
}
