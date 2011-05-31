/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

import java.util.Map;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public interface Instruction {
    public Object execute(BaseAgiScript script) throws AgiException;
    public void setParams(Map params);
    public Map getParams();
}
