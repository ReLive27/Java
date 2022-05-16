package com.relive.ssh.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author: ReLive
 * @date: 2022/5/13 4:45 下午
 */
@Slf4j
public class JschUtils {

    private JschUtils() {
    }

    public static void execCommand(String username, String password, String host, Integer port, String command) throws JSchException, InterruptedException {
        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            ByteArrayOutputStream errorResponseStream = new ByteArrayOutputStream();
            channel.setErrStream(errorResponseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray(), StandardCharsets.UTF_8);
            if (!responseString.isEmpty()) {
                log.info(responseString);
            }
            String errorResponseString = new String(errorResponseStream.toByteArray(), StandardCharsets.UTF_8);
            if (!errorResponseString.isEmpty()) {
                log.warn(errorResponseString);
            }
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
