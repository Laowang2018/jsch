package com.lw.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Date;
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
        session.connect(1000000000);
        ChannelSftp sftpChannel = (ChannelSftp)session.openChannel("sftp");
        sftpChannel.connect();
        Vector ls = sftpChannel.ls("/home/laowang/test");
        for(int i=0; i<ls.size(); i++) {
            Object o = ls.get(i);
            System.out.println(o);
        }
//        for(int i=0; i<500000; i++) {
//            DecimalFormat decimalFormat = new DecimalFormat("000000000000000000000");
//            String format = decimalFormat.format(i);
//            sftpChannel.mkdir("/home/laowang/test/"+format);
//        }
        System.out.println("finished");
        sftpChannel.disconnect();
        session.disconnect();

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
