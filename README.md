## Git Account Source Plugin
This plugin supports dynamic accounts loading from Git repository by cloning a repository and reads a Yml/JSON file for loading Google accounts without restarting clouddriver service.


### Introduction
1. Git Account Source Plugin supports Spinnaker Google Accounts addition, removal, and update by poller syncs with a configured Git repository.
2. Dynamic accounts loading with given Git repository to fetch accounts details from a file at runtime, without restarting clouddriver service.


### Setup
1. Clouddriver must have support for Dynamic Accounts Loading [Google Accounts Support](https://github.com/kirangodishala/clouddriver/tree/1.26.x-external-accounts-support) & [Account Management](https://github.com/spinnaker/governance/blob/master/rfc/account-management.md).
2. Must have a Git repository with credentials, filename to access the Yml/JSON file to load Google Accounts.
   <br/>E.g. File stored in Git repository [accounts.yml](https://github.com/ashish-ck/git-accounts-yml/blob/main/accounts.yml)
3. Attribute `jsonPath` of Google Account is stored as an encrypted file as a secret. [profile](https://spinnaker.io/docs/reference/halyard/secrets/gcs-secrets/)
   <br/>E.g. ```jsonPath: encryptedFile:gcs!b:gce-accounts-v1!f:gce-account.json```

```yaml
google:
   accounts:
      - name: spinnaker-gce-account-v1.2
        requiredGroupMembership: []
        permissions:
           WRITE:
              - compute.projects.write
        project: opsmx-ggproject-2022
        jsonPath: encryptedFile:gcs!b:gce-accounts-v1!f:gce-account.json
        alphaListed: false
        imageProjects: []
        consul:
           enabled: false
           agentEndpoint: localhost
           agentPort: 8500
           datacenters: []
```


### Build
1. To build the plugin run the following command `./gradlew clean build`.
2. To build the plugin zip, run the following command `./gradlew releaseBundle`.
   The above command will produce a zip file, `git-account-source-plugin/git-account-registration-plugin/build/distributions/git-account-registration-plugin*.zip`.
3. Publish the release at GitHub site and update `plugins.json` with the URL of plugin zip. or
   Copy the zip file to Clouddriver plugin directory. Defaults to `/opt/clouddriver/plugins`. This directory can be specified by the `plugins-root-path` configuration property.
4. Enable the plugin by placing the following in Clouddriver [profile](https://spinnaker.io/reference/halyard/custom/#custom-profiles).


### Deploy and run
1. Add the following to `clouddriver.yml` in the necessary [profile](https://spinnaker.io/reference/halyard/custom/#custom-profiles) to load plugin.
```yaml
spinnaker:
   extensibility:
      plugins-root-path: /opt/clouddriver/plugins
      plugins:
         Git.AccountRegistration:
            id: Git.AccountRegistration
            enabled: true
            version: 0.0.1
            extensions: {}
            config: {}
      repositories:
         gitAccountRegistrationPluginRepo:
            id: gitAccountRegistrationPluginRepo
            url: https://raw.githubusercontent.com/OpsMx/git-account-source-plugin/dev/sample/repositories.json

config:
   repositoryName: 'https://github.com/ashish-ck/git-accounts-yml'
   filename: 'accounts.yml'
   gitHttpsUsername: ''
   gitHttpsPassword: ''
   githubOAuthAccessToken: ''
   sshPrivateKeyFilePath: '~/.ssh/id_ed25519.pub'
   sshPrivateKeyPassphrase: ''
   sshKnownHostsFilePath: '~/.ssh/known_hosts'
   credentialType: 'NONE'
   sshTrustUnknownHosts: false

credentials:
   poller:
      enabled: true
      types:
         gce:
            reloadFrequencyMs: 20000
```

2. Deploy this configuration with [hal command](https://spinnaker.io/docs/setup/install/deploy/).    `hal deploy apply && hal deploy connect`
3. Run the clouddriver  `./gradlew`


### Note
[Stackoverflow](https://stackoverflow.com/questions/53134212/invalid-privatekey-when-using-jsch)


## License
This project is licensed under the Apache-2.0 License.

