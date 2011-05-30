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
public class Answer extends DefaultInstruction{

    private Map params;
    
    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return this.params;
    }
    
    @Override
    public Object execute(BaseAgiScript script) throws AgiException {
        script.answer();
        return null;
    }
}
