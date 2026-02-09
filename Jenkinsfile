pipeline {
    agent any
    parameters {

           choice(name: 'SUITE', choices: ['api_tests.xml', 'pair_classes.xml',], description: 'Pick file for run')

        }
    stages {
        stage('Echoing parameters') {
            steps {
            echo "Selected suite: ${params.SUITE}"
            }
        }
        stage("SCM Checkout"){
            steps{
            git 'https://github.com/VariousVeres/selenium-java-framework'
            }
        }
        stage('Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=src/test/resources/${params.SUITE}"
            }
        }
//         stage('Deploy') {
//             steps {
//              echo 'Test stage placeholder'
//             }
//         }
    }
}