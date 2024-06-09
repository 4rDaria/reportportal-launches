#!groovy
pipeline {
    agent any

    options {
      buildDiscarder(logRotator(numToKeepStr: '5'))
    }


    stages {
        stage('Prepare') {
            steps {
                git branch: 'module_9', url: 'https://github.com/4rDaria/reportportal-launches'
            }
        }

        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    withMaven {
                        bat '/mvn clean org.sonarsourse.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar'
                    }
                }
            }
        }
    }
}