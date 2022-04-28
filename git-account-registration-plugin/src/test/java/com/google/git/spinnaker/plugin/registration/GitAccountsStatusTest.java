/*
 * Copyright 2022 OpsMx, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.git.spinnaker.plugin.registration;

import com.netflix.spinnaker.kork.secrets.SecretManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.anyString;

class GitAccountsStatusTest {

    private GitAccountsStatus gitAccountsStatus;

//    @BeforeEach
//    public void setUp() {
//        String accountsYml = "google:\n" +
//                "  enabled: true\n" +
//                "  accounts:\n" +
//                "  - name: spinnaker-gce-account-v1.2\n" +
//                "    jsonPath: encryptedFile:gcs!b:gce-accounts-v1!f:gce-account.json\n" +
//                "    consul:\n" +
//                "      enabled: false\n" +
//                "  primaryAccount: spinnaker-gce-account-v1.2\n" +
//                "  bakeryDefaults:\n" +
//                "    useInternalIp: false";
//        String gitHttpsUsername = "git_username";
//        String gitHttpsPassword = "password";
//        String githubOAuthAccessToken = "ghp_5b58J65pqQDbKdsdrgDT9iq7pDPI3uciFh";
//        String sshPrivateKeyFilePath = "~/.ssh/id_ed25519";
//        String sshPrivateKeyPassphrase = "passphrase";
//        String sshKnownHostsFilePath = "~/.ssh/known_hosts";
//        Boolean sshTrustUnknownHosts = true;
//        gitAccountsStatus = spy(new GitAccountsStatus(gitHttpsUsername, gitHttpsPassword,
//                githubOAuthAccessToken, sshPrivateKeyFilePath, sshPrivateKeyPassphrase, sshKnownHostsFilePath,
//                sshTrustUnknownHosts));
//        ReflectionTestUtils.setField(gitAccountsStatus, "repositoryName", "https://test.git");
//        ReflectionTestUtils.setField(gitAccountsStatus, "filename", "accounts.yml");
//        SecretManager secretManager = spy(mock(SecretManager.class));
//        ReflectionTestUtils.setField(gitAccountsStatus, "secretManager", secretManager);
//        ReflectionTestUtils.setField(gitAccountsStatus, "credentialType", GitAccountsStatus.GitCredentialType.NONE);
//        doReturn(new ByteArrayInputStream(accountsYml.getBytes())).when(gitAccountsStatus).downloadRemoteFile();
//        doReturn(mock(Path.class)).when(secretManager).decryptAsFile(anyString());
//    }

    @Test
    public void testFetchAccounts() {
        String accountsYml = "google:\n" +
                "  enabled: true\n" +
                "  accounts:\n" +
                "  - name: spinnaker-gce-account-v1.2\n" +
                "    jsonPath: encryptedFile:gcs!b:gce-accounts-v1!f:gce-account.json\n" +
                "    consul:\n" +
                "      enabled: false\n" +
                "  primaryAccount: spinnaker-gce-account-v1.2\n" +
                "  bakeryDefaults:\n" +
                "    useInternalIp: false";
        String gitHttpsUsername = "git_username";
        String gitHttpsPassword = "password";
        String githubOAuthAccessToken = "ghp_5b58J65pqQDbKdsdrgDT9iq7pDPI3uciFh";
        String sshPrivateKeyFilePath = "~/.ssh/id_ed25519";
        String sshPrivateKeyPassphrase = "passphrase";
        String sshKnownHostsFilePath = "~/.ssh/known_hosts";
        Boolean sshTrustUnknownHosts = true;
        gitAccountsStatus = spy(new GitAccountsStatus(gitHttpsUsername, gitHttpsPassword,
                githubOAuthAccessToken, sshPrivateKeyFilePath, sshPrivateKeyPassphrase, sshKnownHostsFilePath,
                sshTrustUnknownHosts));
        ReflectionTestUtils.setField(gitAccountsStatus, "repositoryName", "https://test.git");
        ReflectionTestUtils.setField(gitAccountsStatus, "filename", "accounts.yml");
        SecretManager secretManager = spy(mock(SecretManager.class));
        ReflectionTestUtils.setField(gitAccountsStatus, "secretManager", secretManager);
        ReflectionTestUtils.setField(gitAccountsStatus, "credentialType", GitAccountsStatus.GitCredentialType.NONE);
        doReturn(new ByteArrayInputStream(accountsYml.getBytes())).when(gitAccountsStatus).downloadRemoteFile();
        doReturn(mock(Path.class)).when(secretManager).decryptAsFile(anyString());
        assertTrue(gitAccountsStatus.fetchAccounts());
    }

    @Test
    public void testFetchAccountsSSH() {
        String accountsYml = "google:\n" +
                "  enabled: true\n" +
                "  accounts:\n" +
                "  - name: spinnaker-gce-account-v1.2\n" +
                "    jsonPath: encryptedFile:gcs!b:gce-accounts-v1!f:gce-account.json\n" +
                "    consul:\n" +
                "      enabled: false\n" +
                "  primaryAccount: spinnaker-gce-account-v1.2\n" +
                "  bakeryDefaults:\n" +
                "    useInternalIp: false";
        String gitHttpsUsername = "git_username";
        String gitHttpsPassword = "password";
        String githubOAuthAccessToken = "ghp_5b58J65pqQDbKdsdrgDT9iq7pDPI3uciFh";
        String sshPrivateKeyFilePath = "~/.ssh/id_ed25519";
        String sshPrivateKeyPassphrase = "passphrase";
        String sshKnownHostsFilePath = "~/.ssh/known_hosts";
        Boolean sshTrustUnknownHosts = true;
        gitAccountsStatus = spy(new GitAccountsStatus(gitHttpsUsername, gitHttpsPassword,
                githubOAuthAccessToken, sshPrivateKeyFilePath, sshPrivateKeyPassphrase, sshKnownHostsFilePath,
                sshTrustUnknownHosts));
        ReflectionTestUtils.setField(gitAccountsStatus, "repositoryName", "https://test.git");
        ReflectionTestUtils.setField(gitAccountsStatus, "filename", "accounts.yml");
        SecretManager secretManager = spy(mock(SecretManager.class));
        ReflectionTestUtils.setField(gitAccountsStatus, "secretManager", secretManager);
        ReflectionTestUtils.setField(gitAccountsStatus, "credentialType", GitAccountsStatus.GitCredentialType.SSH);
        doReturn(new ByteArrayInputStream(accountsYml.getBytes())).when(gitAccountsStatus).downloadRemoteFile();
        doReturn(mock(Path.class)).when(secretManager).decryptAsFile(anyString());
        assertTrue(gitAccountsStatus.fetchAccounts());
    }
}