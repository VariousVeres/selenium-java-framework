pipeline {
    agent any
    parameters {

           choice(name: 'SUITE', choices: ['parallel_methods.xml', 'pair_classes.xml',], description: 'Pick file for run')

        }
    stages {
        stage('Echoing parameters') {
            steps {
            echo "Selected suite: ${params.SUITE}"
            }
        }
//         stage("SCM Checkout"){
//             steps{
//             git 'https://github.com/VariousVeres/selenium-java-framework'
//             }
//         }
        stage('Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/${params.SUITE}"
            }
        }
        stage('Deploy reports') {
            steps {
             allure includeProperties:
             false, jdk: '',
             resultPolicy: 'LEAVE_AS_IS',
             results: [[path: '/allure-results']]
            }
        }
    }
}