/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow;

import java.util.List;

/**
 *
 * @author kapil
 */
public interface Select {

    String getEndsWith();

    String getInvalidSelectAction();

    int getLength();

    String getOnSelectAction();

    List<Option> getOptions();

    String getRepeatAction();

    String getRepeatKey();

    String getTimeoutAction();

    void setEndsWith(String endsWith);

    void setInvalidSelectAction(String invalidSelectAction);

    void setLength(int length);

    void setOnSelectAction(String onSelectAction);

    void setOptions(List<Option> options);

    void setRepeatAction(String repeatAction);

    void setRepeatKey(String repeatKey);

    void setTimeoutAction(String timeoutAction);
    
}
