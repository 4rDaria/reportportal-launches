#!groovy
pipeline {
    agent any

    tools {
        maven 'M3'
    }

    options {
      buildDiscarder(logRotator(numToKeepStr: '5'))
    }


    stages {
        stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'sq1') {
                    withMaven(maven: 'M3') {
                        bat '/mvnw clean org.sonarsourse.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar'
                    }
                }
            }
        }
    }
}