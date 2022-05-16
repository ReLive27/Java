package com.relive.ssh;

import com.jcraft.jsch.JSchException;
import com.relive.ssh.jsch.JschUtils;
import com.relive.ssh.sshd.SSHdUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: ReLive
 * @date: 2022/5/15 5:07 下午
 */
public class SSHTest {

    @Test
    public void jschTest() throws InterruptedException, JSchException {
        JschUtils.execCommand("root", "password", "127.0.0.1", 22, "ls");
    }

    @Test
    public void sshdTest() throws IOException {
        SSHdUtils.execCommand("root", "password", "127.0.0.1", 22, 10, "ls");
    }
}
