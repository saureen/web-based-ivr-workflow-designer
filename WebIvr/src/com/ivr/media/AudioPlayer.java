/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ivr.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.ControllerListener;
import javax.media.IncompatibleTimeBaseException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

/**
 *
 * @author swoosh
 */
public class AudioPlayer {

    private Player player;
    private URL mediaURL;
    private File file;

    public AudioPlayer(URL mediaURL){
        try {
            this.mediaURL = mediaURL;
            this.player = Manager.createPlayer(this.mediaURL);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public AudioPlayer(byte[] mediaContent){
        try {
            this.file = File.createTempFile("audio", ".wav");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(mediaContent);
            fos.close();
            this.mediaURL = this.file.toURI().toURL();
            this.player = Manager.createPlayer(mediaURL);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play(){
        player.start();
    }

    public void stop(){
        player.stop();
    }

    public void addControllerListener(ControllerListener listener) throws IncompatibleTimeBaseException{
        player.addControllerListener(listener);
    }

    public void finalize() throws Throwable{
        this.file.delete();
        super.finalize();
    }
}
