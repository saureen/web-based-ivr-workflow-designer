/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl;

import com.ivr.workflow.Action;
import com.ivr.workflow.Instruction;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class DefaultAction implements Action{

    private String name;
    private List<Instruction> instructions;
    private String nextAction;
    private String nextStep;
    private String nextStepLang;
    private String lang;
    private Map params;
    private BaseAgiScript script;

    private Logger logger = Logger.getLogger(DefaultAction.class);

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getNextStepLang(){
        return this.nextStepLang;
    }

    public void setNextStepLang(String nextStepLang){
        this.nextStepLang = nextStepLang;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public BaseAgiScript getScript() {
        return script;
    }

    public void setScript(BaseAgiScript script) {
        this.script = script;
    }

    public Object execute() {
        if(this.instructions != null){
            params.put("lang", this.lang);
            for(Instruction inst: this.instructions){
                try {
                    inst.getParams().put("lang", this.lang);
                    Object obj = inst.execute(script);
                    if(obj != null){
                        return obj;
                    }
                } catch (Exception ex) {
                    this.logger.error("Error while executing action " + this.name, ex);
                }
            }
        }
        return null;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

}
