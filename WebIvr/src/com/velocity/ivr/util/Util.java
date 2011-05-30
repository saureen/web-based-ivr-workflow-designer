/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.util;

/**
 *
 * @author swoosh
 */
public class Util {

    public static String appendToFileName(String fileName, String suffix){
        if(fileName.contains(".")){
            String[] fileNameParts = fileName.split("\\.");
            fileName = fileNameParts[0] + suffix + "." + fileNameParts[1];
            if(fileNameParts.length > 2){
                for(int i=2; i< fileNameParts.length; i++){
                    fileName = fileName + "." + fileNameParts[i];
                }
            }
        }else{
            fileName = fileName + suffix;
        }

        return fileName;
    }
}
