@Library('my-jenkins-shared-lib') _
pipeline {
    agent {label 'agent-01'}

    stages {
        stage('Git-Checkout') {
            steps {
              gitCheckout (
               branch: "main",
               url: "https://github.com/Shourawandy/my-simple-java.git")
            }
        }

        stage('unitTest') {
            steps {
                script {
                    mvnTest()
                }
            }
        }

        stage('Test') {
            steps {
                sh 'echo Running tests...'
            }
        }

        stage('Archive') {
            when {
                expression { return env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master' }
            }
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}