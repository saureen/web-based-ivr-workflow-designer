/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.asterisk;

import com.velocity.ivr.util.BeanRegistry;
import com.velocity.ivr.workflow.Workflow;
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author swoosh
 */
public class WorkflowHandler extends BaseAgiScript{

    private Workflow wf;

    public WorkflowHandler(){
        super();
        this.wf = BeanRegistry.getWorkFlow();
        wf.load();
    }

    public WorkflowHandler(Workflow wf){
        this.wf = wf;
        this.wf.load();
    }

    public void service(AgiRequest ar, AgiChannel ac) throws AgiException {
        wf.setScript(this);
        wf.start();
    }

}
