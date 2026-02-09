pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
            echo 'Test stage placeholder'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B clean test -DsuiteXmlFile=src/test/resources/pair_classes.xml'
            }
        }
        stage('Deploy') {
            steps {
             echo 'Test stage placeholder'
            }
        }
    }
}