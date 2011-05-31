/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

import java.util.List;
import java.util.Map;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public interface Action {

    public Object execute();

    public String getNextStep();

    public String getNextAction();

    public String getNextStepLang();

    public void setLang(String lang);

    public void setName(String name);

    public String getName();

    void setInstructions(List<Instruction> instructions);

    List<Instruction> getInstructions();

    void setParams(Map params);

    void setNextAction(String nextAction);

    void setNextStep(String nextStep);

    void setNextStepLang(String nextStepLang);

    void setScript(BaseAgiScript script);
}
