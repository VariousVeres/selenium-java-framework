pipeline {
    agent any
    parameters {
           choice(name: 'SUITE', choices: ['pair_classes.xml', 'parallel_methods.xml'], description: 'Pick xml file for run')
       }
    stages {
        stage('Echoing parameters') {
            steps {
            echo "Selected suite: ${params.SUITE}"
            }
        }
        stage('Test') {
            steps {
              catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/${params.SUITE}"
                }
            }
        }
        stage('Deploy reports') {
            steps {
             allure includeProperties:
             false, jdk: '',
             resultPolicy: 'LEAVE_AS_IS',
             results: [[path: '/target/allure-results']]
            }
        }
    }
}