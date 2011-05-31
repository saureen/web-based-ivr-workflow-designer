/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.xml;

import com.velocity.ivr.workflow.Step;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author swoosh
 */
public interface WorkflowReader {
    public void load(File file);

    public void load(InputStream is);

    public Map<String, Step> getSteps();

    public String getWorkflowName();

    public String getWorkflowDescription();

    public String getStartStep();

}
