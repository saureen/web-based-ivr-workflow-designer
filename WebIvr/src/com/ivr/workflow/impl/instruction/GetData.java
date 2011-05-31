/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.workflow.impl.instruction;

import com.ivr.util.Util;
import com.ivr.workflow.impl.DefaultInstruction;
import java.util.Map;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class GetData extends DefaultInstruction{

    private Map params;
    
    @Override
    public Object execute(BaseAgiScript script) throws AgiException {
        String lang = (String)params.get("lang");
        String audioFile = (String)params.get("audioFile");
        if(lang != null && !lang.isEmpty()){
            audioFile = Util.appendToFileName(audioFile, "-" + lang);
        }
        long timeout = -1;
        if(params.get("timeOut") != null){
            try{
                timeout = Long.parseLong((String)params.get("timeOut"));
            }catch(NumberFormatException e){
                
            }
        }
        int maxDigits = -1;
        if(params.get("maxDigits") != null){
            try{
                maxDigits = Integer.parseInt((String)params.get("maxDigits"));
            }catch(NumberFormatException e){

            }
        }

        if(timeout > 0){
            if(maxDigits > 0){
                return script.getData(audioFile, timeout, maxDigits);
            }else{
                return script.getData(audioFile, timeout);
            }
        }else{
            return script.getData(audioFile);
        }
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return this.params;
    }
}
