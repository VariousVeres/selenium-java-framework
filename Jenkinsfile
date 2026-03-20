pipeline {
    agent any
    parameters {
           choice(name: 'SUITE', choices: ['pair_classes.xml'], description: 'Pick xml file for run')
       }
    stages {
        stage('Echoing parameters') {
            steps {
            echo "Selected suite: ${params.SUITE}"
            }
        }
        stage('Build') {
                    steps {
                      catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        echo "Clearing project!!!"
                        bat "mvn clean"
                        }
                    }
                }
        stage('Test') {
            steps {
              catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                echo "Starting tests!!!"
                bat "mvn test -DsuiteXmlFile=src/test/resources/${params.SUITE}"
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