pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=src/test/resources/pair_classes.xml'
            }
        }
        stage('Test') {
            steps {
                //
            }
        }
        stage('Deploy') {
            steps {
                //
            }
        }
    }
}