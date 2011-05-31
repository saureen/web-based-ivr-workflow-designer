/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public interface Workflow {
    
    public void start();

    public void load();

    public void load(String defnXmlFile);

    public void startInBackGround();

    public Step getStartStep();

    public Step getCurrentStep();

    public String getCurrentStepName();

    public Step getStepByName(String name);

    public String getName();

    String getDescription();

    void setScript(BaseAgiScript script);
}
