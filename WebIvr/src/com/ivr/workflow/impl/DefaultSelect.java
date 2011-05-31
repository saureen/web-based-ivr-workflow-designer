/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl;

import com.ivr.workflow.Option;
import com.ivr.workflow.Select;
import java.util.List;

/**
 *
 * @author kapil
 */
public class DefaultSelect implements Select{
    private int length;
    private String endsWith;
    private String timeoutAction;
    private String invalidSelectAction;
    private String onSelectAction;
    private String repeatKey;
    private String repeatAction;
    private List<Option> options;

    public String getEndsWith() {
        return endsWith;
    }

    public void setEndsWith(String endsWith) {
        this.endsWith = endsWith;
    }

    public String getInvalidSelectAction() {
        return invalidSelectAction;
    }

    public void setInvalidSelectAction(String invalidSelectAction) {
        this.invalidSelectAction = invalidSelectAction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOnSelectAction() {
        return onSelectAction;
    }

    public void setOnSelectAction(String onSelectAction) {
        this.onSelectAction = onSelectAction;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getRepeatAction() {
        return repeatAction;
    }

    public void setRepeatAction(String repeatAction) {
        this.repeatAction = repeatAction;
    }

    public String getRepeatKey() {
        return repeatKey;
    }

    public void setRepeatKey(String repeatKey) {
        this.repeatKey = repeatKey;
    }

    public String getTimeoutAction() {
        return timeoutAction;
    }

    public void setTimeoutAction(String timeoutAction) {
        this.timeoutAction = timeoutAction;
    }
}
