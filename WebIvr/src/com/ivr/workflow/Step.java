/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

import java.util.Map;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public interface Step {

    public void execute();

    public String getNextStepLang();

    public String getNextStep();

    public void setLang(String lang);

    public String getName();

    public void setName(String name);

    public void setStartActionName(String startActionName);

    public String getStartActionName();

    public Select getSelect();

    public void setSelect(Select selectMenu);

    public void addAction(Action action);

    public void setParams(Map<String, String> params);

    void setScript(BaseAgiScript script);
}
