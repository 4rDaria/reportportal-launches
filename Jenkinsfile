#!groovy
pipeline {
    agent none

    stages {
        stage('SonarQube analysis') {
            agent any
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    withMaven {
                        bat 'mvn clean org.sonarsourse.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                    }
                }
            }
        }
    }
}