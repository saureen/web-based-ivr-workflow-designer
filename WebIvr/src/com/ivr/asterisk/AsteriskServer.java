/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.asterisk;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.asteriskjava.fastagi.AgiServer;
import org.asteriskjava.fastagi.AgiServerThread;
import org.asteriskjava.fastagi.DefaultAgiServer;

/**
 *
 * @author kapil
 */
public class AsteriskServer {
    
    Logger logger = Logger.getLogger(AsteriskServer.class);
    static boolean status; 
    
    private static AgiServerThread serverThread;
    private static AgiServer instance;
    
    static{
    	instance = new DefaultAgiServer();
    	serverThread = new AgiServerThread();
    	status = false;
    	serverThread.setAgiServer(instance);
    }
    
    private AsteriskServer(){
    	
    }
    
    public static synchronized void start() throws IllegalStateException, IOException{
    	if(status){
    		return;
    	}
    	serverThread.startup();
    	status = true;
    }
    
    public static synchronized void stop() throws IllegalStateException, IOException{
    	if(!status){
    		return;
    	}
    	serverThread.shutdown();
    	status = false;
    }
    
    public static boolean getStatus(){
    	return status;
    }

}
