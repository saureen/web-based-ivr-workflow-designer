/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl;

import com.ivr.workflow.Instruction;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public abstract class DefaultInstruction implements Instruction{

    public Object execute(BaseAgiScript script) throws AgiException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
