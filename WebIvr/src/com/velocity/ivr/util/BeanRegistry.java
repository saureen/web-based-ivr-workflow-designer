/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.util;

import com.velocity.ivr.asterisk.AsteriskServer;
import com.velocity.ivr.asterisk.IvrConfig;
import com.velocity.ivr.dao.CallDetailsDao;
import com.velocity.ivr.dao.CallLogDao;
import com.velocity.ivr.mail.MailService;
import com.velocity.ivr.scp.ScpService;
import com.velocity.ivr.workflow.Workflow;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author swoosh
 */
public class BeanRegistry {
    private static ApplicationContext ctx;

    static {
        ctx = new FileSystemXmlApplicationContext("config/spring-config.xml");
    }


    /**
     * Private to make this a singleton.
     */
    private BeanRegistry(){

    }

    public static SessionFactory getSessionFactory(){
        return (SessionFactory) ctx.getBean("factory", SessionFactory.class);
    }

    public static CallDetailsDao getCallDetailsDao(){
        return (CallDetailsDao)ctx.getBean("callDetailsDao", CallDetailsDao.class);
    }

    public static CallLogDao getCallLogDao(){
        return (CallLogDao)ctx.getBean("callLogDao", CallLogDao.class);
    }

    public static MailService getMailService(){
        return (MailService)ctx.getBean("mailService", MailService.class);
    }

    public static ScpService getScpService(){
        return (ScpService)ctx.getBean("scpService", ScpService.class);
    }

    public static IvrConfig getIvrConfig() {
        return (IvrConfig) ctx.getBean("ivrConfig", IvrConfig.class);
    }
    
    public static Workflow getWorkFlow() {
        return (Workflow) ctx.getBean("workflow", Workflow.class);
    }
    
    public static AsteriskServer getAsteriskServer() {
        return (AsteriskServer) ctx.getBean("asteriskServer", AsteriskServer.class);
    }
}
