/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.impl.instruction;

import com.velocity.ivr.workflow.Instruction;
import java.util.Map;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class HangUp implements Instruction{

    Map params;
    
    public Object execute(BaseAgiScript script) throws AgiException{
        script.hangup();
        return null;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return this.params;
    }
}
