package com.relive.ssh.sshd;

import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * @author: ReLive
 * @date: 2022/5/15 5:01 下午
 */
@Slf4j
public class SSHdUtils {
    private SSHdUtils() {
    }

    public static void execCommand(String username, String password, String host, Integer port, Integer defaultTimeoutSeconds, String command) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try (ClientSession session = client.connect(username, host, port)
                .verify(defaultTimeoutSeconds, TimeUnit.SECONDS)
                .getSession()) {
            session.addPasswordIdentity(password);
            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
            try (ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
                 ByteArrayOutputStream errorResponseStream = new ByteArrayOutputStream();
                 ClientChannel channel = session.createChannel(Channel.CHANNEL_SHELL)) {
                channel.setOut(responseStream);
                channel.setErr(errorResponseStream);
                try {
                    channel.open().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
                    try (OutputStream pipedIn = channel.getInvertedIn()) {
                        pipedIn.write(command.getBytes());
                        pipedIn.flush();
                    }
                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.toMillis(defaultTimeoutSeconds));
                    String errorString = new String(errorResponseStream.toByteArray());
                    if (!errorString.isEmpty()) {
                        log.warn(errorString);
                    }
                    String responseString = new String(responseStream.toByteArray());
                    if (!responseString.isEmpty()) {
                        log.info(responseString);
                    }
                } finally {
                    channel.close(false);
                }
            }
        } finally {
            client.stop();
        }
    }
}
