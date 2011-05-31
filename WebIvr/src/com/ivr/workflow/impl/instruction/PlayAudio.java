/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.workflow.impl.instruction;

import com.velocity.ivr.util.Util;
import com.velocity.ivr.workflow.Instruction;
import java.util.Map;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author kapil
 */
public class PlayAudio implements Instruction{

    private Map params;
    
    public Object execute(BaseAgiScript script) throws AgiException{
        String lang = (String)params.get("lang");
        String audioFile = (String)params.get("audioFile");
        if(lang != null && !lang.isEmpty()){
            audioFile = Util.appendToFileName(audioFile, "-" + lang);
        }
        
        if(params.get("escapeDigits") != null){
            String escapeDigits = (String)params.get("escapeDigits");
            int offset = -1;
            if(params.get("offset") != null){
                try{
                    offset = Integer.parseInt((String)params.get("offset"));
                }catch(NumberFormatException e){
                    
                }
            }
            if(!escapeDigits.isEmpty()){
                if(offset > 0){
                    return script.streamFile(audioFile, escapeDigits, offset);
                }else{
                    return script.streamFile(audioFile, escapeDigits);
                }
            }else{
                script.streamFile(audioFile, escapeDigits);
            }
        }else{
            script.streamFile(audioFile);
        }
        return null;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public Map getParams() {
        return this.params;
    }
}
