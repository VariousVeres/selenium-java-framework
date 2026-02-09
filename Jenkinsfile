pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            echo 'Test stage Build placeholder'
            }
        }
        stage("SCM Checkout"){
            steps{
            git 'https://github.com/VariousVeres/selenium-java-framework.git'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=src/test/resources/pair_classes.xml'
            }
        }
//         stage('Deploy') {
//             steps {
//              echo 'Test stage placeholder'
//             }
//         }
    }
}