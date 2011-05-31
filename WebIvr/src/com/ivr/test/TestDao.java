/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.test;

import com.velocity.ivr.dao.CallDetailsDao;
import com.velocity.ivr.dao.CallLogDao;
import com.velocity.ivr.model.CallDetails;
import com.velocity.ivr.model.CallLog;
import com.velocity.ivr.model.CallLogId;
import com.velocity.ivr.util.BeanRegistry;
import com.velocity.ivr.util.Constants;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author swoosh
 */
public class TestDao {

    public static void main(String[] args){
        try{
            CallDetails callDetails = new CallDetails();
            callDetails.setCalledFrom("xyz");
            callDetails.setName("Test");
            callDetails.setCreationTime(new Date());
            callDetails.setStatus(Constants.CALL_INITIATED);

            CallLog callLog = new CallLog();
            callLog.setLog("".getBytes());

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
            e.printStackTrace();
        }
    }
}
