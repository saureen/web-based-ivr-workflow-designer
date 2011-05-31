/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.asterisk;

import com.ivr.dao.CallDetailsDao;
import com.ivr.dao.CallLogDao;
import com.ivr.mail.MailService;
import com.ivr.model.CallDetails;
import com.ivr.model.CallLog;
import com.ivr.model.CallLogId;
import com.ivr.util.BeanRegistry;
import com.ivr.util.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;

/**
 *
 * @author swoosh
 */
public class CallHandler extends BaseAgiScript
{
    
    public void service(AgiRequest request, AgiChannel channel)
            throws AgiException
    {
        IvrConfig ivrConfig = BeanRegistry.getIvrConfig();
        // Answer the channel...
        answer();


        streamFile(ivrConfig.getWelcome());
        String selection = null;
        String callerId = request.getCallerIdNumber();
        
        String lang = null;
        for(int i=0; i<3; i++){
            selection = getData(ivrConfig.getLangMenu(), (int)ivrConfig.getInputTimeOut());
            if(selection.equals("1")){
                lang = "hi";
                break;
            }else if(selection.equals("2")){
                lang = "en";
                break;
            }
            streamFile(ivrConfig.getInvalidSelection());
        }
        
        if(lang == null){
            streamFile(ivrConfig.getExceededTries());
            hangup();
            return;
        }
        
        int i;
        for(i=0; i<ivrConfig.getMaxTries(); i++){
            selection = getData(ivrConfig.getInstructions() + lang, ivrConfig.getInputTimeOut());
            if(selection.equals("1")){
                // Record to record-*.wav, till user presses # or 180000ms, 
                // 0 offset from current file, with beep, allow not more than 10s of silence
                String recordFolder = ivrConfig.getRecordFolder();
                String recordFile = ivrConfig.getRecordFile() + callerId;
                streamFile(ivrConfig.getRecordInstructions() + lang);
                int ch = recordFile(recordFolder + recordFile, ivrConfig.getRecordFormat(), ivrConfig.getRecordEscape(), 180000, 0, true, 10);
                
                if(ch == -1){
                    streamFile(ivrConfig.getRecordError() + lang);
                    hangup();
                    return;
                }
                
                streamFile(ivrConfig.getThankNote() + lang);
                hangup();

                recordFile = recordFile + "." + ivrConfig.getRecordFormat();
                String localFile = System.getProperty("java.io.tmpdir") + File.separator + recordFile;
                
                BeanRegistry.getScpService().getFile(recordFolder + recordFile, localFile);
                File file = new File(localFile);
                byte[] data = new byte[(int)file.length()];
                
                FileInputStream in = null;
                try{
                    in = new FileInputStream(file);
                    in.read(data);
                }catch(Exception e){
                    Logger.getLogger(CallHandler.class.getName()).log(Level.SEVERE, null, e);
                }finally{
                    if(in != null){
                        try {
                            in.close();
                        } catch (IOException ex) {
                            Logger.getLogger(CallHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                CallDetails callDetails = new CallDetails();
                try{
                    callDetails.setCalledFrom(callerId);
                    callDetails.setName(request.getCallerIdName());
                    callDetails.setCreationTime(new Date());
                    callDetails.setStatus(Constants.CALL_INITIATED);

                    CallLog callLog = new CallLog();
                    callLog.setLog(data);

                    Set logSet = new HashSet();
                    logSet.add(callLog);
                    callDetails.setCallLogs(logSet);

                    CallDetailsDao dao = BeanRegistry.getCallDetailsDao();
                    dao.saveOrUpdate(callDetails);

                    callLog.setCallDetails(callDetails);

                    CallLogDao logDao = BeanRegistry.getCallLogDao();
                    callLog.setId(new CallLogId(callDetails.getCallId(), callDetails.getCreationTime()));
                    logDao.saveOrUpdate(callLog);
                }catch(Exception e){
                    Logger.getLogger(CallHandler.class.getName()).log(Level.SEVERE, null, e);
                }
                
                
                MailService mailService = BeanRegistry.getMailService();

                List<File> attachments = new ArrayList<File>();
                attachments.add(file);
                mailService.sendCallNotificationByEmail(ivrConfig.getNotifyTo(), ivrConfig.getMailSubject() + callDetails.getCalledFrom(),
                        ivrConfig.getMailTemplate(), attachments);
                
                break;
            }else if(selection.equals("2")){

                CallDetails callDetails = null;
                for(int j=0; j<ivrConfig.getMaxTries(); j++){
                    selection = getData(ivrConfig.getCallIdQueryInstru() + lang, ivrConfig.getInputTimeOut());

                    CallDetailsDao dao = BeanRegistry.getCallDetailsDao();
                    try{
                        callDetails = (CallDetails)dao.find(Integer.parseInt(selection));
                    }catch(NumberFormatException e){
                        streamFile(ivrConfig.getInvalidSelection() + lang);
                    }catch(Exception e){
                        streamFile(ivrConfig.getSysErr() + lang);
                        Logger.getLogger(CallHandler.class.getName()).log(Level.SEVERE, null, e);
                    }

                    if(callDetails == null){
                        sayDigits(selection);
                        streamFile(ivrConfig.getNotFound() + lang);
                    }else{
                        streamFile(ivrConfig.getStatusIs() + lang);
                        streamFile("status" + "-" + callDetails.getStatus() + "-" + lang);
                        streamFile(ivrConfig.getThankNote() + lang);
                        hangup();
                        return;
                    }
                }
                
                if(callDetails == null){
                    streamFile(ivrConfig.getExceededTries() + lang);
                    hangup();
                    return;
                }
                break;
            }
            streamFile(ivrConfig.getInvalidSelection() + lang);
        }
        
        if(i == 3){
            streamFile(ivrConfig.getExceededTries() + lang);
            hangup();
            return;
        }
        
        hangup();
    }

}
