/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl;

import com.ivr.workflow.Step;
import com.ivr.workflow.Workflow;
import com.ivr.workflow.xml.WorkflowReader;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class DefaultWorkflow implements Workflow{

    private String name;
    private String startStepName;
    private Map<String, Step> steps;
    private Step currentStep;
    private String description;
    private Logger logger = Logger.getLogger(DefaultWorkflow.class);
    private String defnXml;
    private WorkflowReader reader;
    private BaseAgiScript script;


    public void start(){
        Object retVal = startStepName;
        String stepLang = "";
        while(retVal != null && retVal instanceof String){
            Step step = steps.get((String)retVal);
            this.currentStep = step;
            step.setScript(this.script);
            step.setLang(stepLang);
            step.execute();
            retVal = step.getNextStep();
            stepLang = step.getNextStepLang();
        }
    }

    public void load() {
        logger.info("Loading config: " + this.defnXml);
        this.reader.load(new File(this.defnXml));
        logger.info("Loaded: " + this.defnXml);

        this.name = this.reader.getWorkflowName();
        this.startStepName = this.reader.getStartStep();
        this.steps = this.reader.getSteps();
        this.description = this.reader.getWorkflowDescription();
    }

    public void load(String defnXmlFile) {
        this.defnXml = defnXmlFile;
        this.load();
    }

    public void startInBackGround() {
        Runnable bgThread = new Runnable(){
            public void run() {
                DefaultWorkflow.this.start();
            }
        };

        ExecutorService exec =  Executors.newSingleThreadExecutor();
        exec.submit(bgThread);
    }

    public Step getStartStep() {
        return this.steps.get(this.startStepName);
    }

    public Step getCurrentStep() {
        return this.currentStep;
    }

    public String getCurrentStepName() {
        if(this.currentStep != null){
            return this.currentStep.getName();
        }
        return null;
    }

    public Step getStepByName(String name) {
        return this.steps.get(name);
    }

    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDefnXml() {
        return this.defnXml;
    }

    public void setDefnXml(String defnXml) {
        this.defnXml = defnXml;
    }

    public WorkflowReader getReader() {
        return this.reader;
    }

    public void setReader(WorkflowReader reader) {
        this.reader = reader;
    }
    
    public void setScript(BaseAgiScript script){
        this.script = script;
    }
}
