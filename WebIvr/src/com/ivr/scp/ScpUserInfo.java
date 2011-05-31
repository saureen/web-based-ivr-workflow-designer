/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.scp;

import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 *
 * @author kapil
 */
public class ScpUserInfo implements UserInfo, UIKeyboardInteractive{

    private String passphrase;
    private String password;
    private String user;

    public void setPassphrase(String passphrase){
        this.passphrase = passphrase;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return this.user;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean promptPassword(String string) {
//        System.out.println(string);
        return true;
    }

    public boolean promptPassphrase(String string) {
//        System.out.println(string);
        return true;
    }

    public boolean promptYesNo(String string) {
//        System.out.println(string);
        return true;
    }

    public void showMessage(String string) {
//        System.out.println(string);
    }

    public String[] promptKeyboardInteractive(String string, String string1,
            String string2, String[] strings, boolean[] blns) {
        return new String[]{this.password};
    }

}
