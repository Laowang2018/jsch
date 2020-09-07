package com.lw.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class JschTest {
    public static void main(String[] args) throws Exception{
        String ip = "192.168.58.128";
        String port = "22";
        String username = "laowang";
        String priKeyPath = "C:\\Users\\wsj60\\.ssh\\id_rsa";
        JSch jSch = new JSch();

        Path path = Paths.get(priKeyPath);
        byte[] bytes = Files.readAllBytes(path);
        jSch.addIdentity("",bytes,null,null);
        jSch.setKnownHosts("C:\\Users\\wsj60\\.ssh\\known_host");
        Session session = jSch.getSession(username, ip, Integer.valueOf(port));
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp sftpChannel = (ChannelSftp)session.openChannel("sftp");
        sftpChannel.connect();
        Vector ls = sftpChannel.ls("/home/laowang/.ssh");
        System.out.println(ls);

//        ChannelSftp sftpChanel = (ChannelSftp)session.openChannel("sftp");
//        Vector ls = sftpChanel.ls("/home/laowang");
//        System.out.println(ls);
//        InputStream inputStream = sftpChanel.get("./xxx.txt");
//        int c = 0;
//        while ((c = inputStream.read())!= 1) {
//            System.out.println(c);
//        }

    }
}
