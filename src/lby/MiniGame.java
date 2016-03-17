/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import shared.util.Log;

/**
 *
 * @author yanxing wang
 */
public class MiniGame {
    
    private String name = "";
    private boolean singlePlayer = true;
    private boolean available = true;
    private int port = 0;
    private String serverPath = ""; // path to the jar file
    private String absServerPath = "";
    private Process process;

    public MiniGame(String name) {
        this.name = name;
    }
    
    public void setAsMultiPlayerGame(String path, int port) {
        this.setSinglePlayer(false);
        this.serverPath = path;
        File file = new File(this.serverPath);
        this.absServerPath = file.getAbsolutePath();
        this.port = port;
        validate();
    }
    
    public void setAsSinglePlayerGame() {
        this.setSinglePlayer(true);
    }
    
    public void validate() {
        File f = new File(this.serverPath);
        this.available = f.exists() && !f.isDirectory();
    }

    /**
     * @return the singlePlayer
     */
    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    /**
     * @param singlePlayer the singlePlayer to set
     */
    private void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public int getPort() {
        return this.port;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    void run() throws IOException {
        Log.println("Running server: " + this.name);
        Log.println("Jar: " + this.serverPath);
        
        String filePath = absServerPath.substring(0, absServerPath.lastIndexOf(File.separator));
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", this.absServerPath);
        pb.directory(new File(filePath));
        try {
            this.process = pb.start();            
            {
                LogStreamReader lsr = new LogStreamReader(this.process.getInputStream(), this.name);
                Thread thread = new Thread(lsr, "LogStreamReader");
                thread.start();
            }
            {
                LogStreamReader lsr = new LogStreamReader(this.process.getErrorStream(), this.name);
                Thread thread = new Thread(lsr, "LogStreamReader");
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class LogStreamReader implements Runnable {

    private BufferedReader reader;
    private String name;
    
    public LogStreamReader(InputStream is, String name) {
        this.reader = new BufferedReader(new InputStreamReader(is));
        this.name = name;
    }

    @Override
    public void run() {
        try {
            String line = reader.readLine();
            while (line != null) {
                System.out.println("[" + name + "] " + line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
