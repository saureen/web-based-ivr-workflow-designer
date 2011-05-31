/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.impl.instruction;

import com.velocity.ivr.workflow.impl.DefaultInstruction;
import java.util.Map;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author swoosh
 */
public class PlayDigits extends DefaultInstruction{

    private Map params;
    
    @Override
    public Object execute(BaseAgiScript script) throws AgiException{
        String digits = (String)params.get("digits");
        script.sayDigits(digits);
        return null;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return this.params;
    }
}
