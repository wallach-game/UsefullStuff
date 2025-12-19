 stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'refs/heads/bugfix/ODT-1140-fix---dry-run-in-versionupdate']],
                    userRemoteConfigs: [[
                        url: 'https://bitbucket.org/optimidoc/terminal_installer2.git',
                        credentialsId: 'bitbucket-creds'
                    ]]
                ])
            }
        }
