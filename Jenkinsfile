#!groovy
pipeline {
    agent any

    options {
      buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    stages {
        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                  bat '/mvn clean org.sonarsourse.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar'
                }
            }
        }
    }
}