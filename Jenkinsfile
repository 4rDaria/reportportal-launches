#!groovy
pipeline {
    agent none

    stages {
        stage('SonarQube analysis') {
            agent any
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    withMaven {
                        bat 'mvn clean org.sonarsourse.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar'
                    }
                }
            }
        }
    }
}