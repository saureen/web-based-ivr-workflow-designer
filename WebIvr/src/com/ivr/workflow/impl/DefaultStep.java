/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl;

import com.ivr.workflow.Action;
import com.ivr.workflow.Option;
import com.ivr.workflow.Select;
import com.ivr.workflow.Step;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class DefaultStep implements Step{

    private String startActionName;
    private Select selectMenu;
    private Map<String, Action> actions;
    private Map<String, String> params;
    private String lang;
    private String nextStepLang;
    private String nextStep;
    private String name;
    private BaseAgiScript script;

    public void execute(){
        Object retVal = startActionName;
        while(retVal != null && retVal instanceof String){
            Action action = actions.get((String)retVal);
            action.setScript(this.script);
            action.setLang(lang);
            retVal = action.execute();

            if(retVal != null){
                List<Option> options = selectMenu.getOptions();
                for(Option option: options){
                    if(retVal.equals(option.getValue())){
                        retVal = option.getOnSelect();
                        break;
                    }
                }
            }else if(action.getNextAction() != null){
                retVal = action.getNextAction();
            }else if(action.getNextStep() != null){
                this.nextStepLang = action.getNextStepLang();
                this.nextStep = action.getNextStep();
            }
        }
    }

    public Map<String, Action> getActions() {
        return actions;
    }

    public void setActions(Map<String, Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action){
        if(this.actions == null){
            this.actions = new HashMap<String, Action>();
        }
        this.actions.put(action.getName(), action);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Select getSelectMenu() {
        return selectMenu;
    }

    public void setSelectMenu(Select selectMenu) {
        this.selectMenu = selectMenu;
    }

    public String getStartActionName() {
        return startActionName;
    }

    public void setStartActionName(String startActionName) {
        this.startActionName = startActionName;
    }

    public String getNextStepLang() {
        return this.nextStepLang;
    }

    public String getNextStep() {
        return this.nextStep;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Select getSelect() {
        return this.selectMenu;
    }

    public void setSelect(Select selectMenu) {
        this.selectMenu = selectMenu;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
    public void setScript(BaseAgiScript script){
        this.script = script;
    }

}
