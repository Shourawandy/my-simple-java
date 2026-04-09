@Library('my-jenkins-shared-lib') _

pipeline {
    agent { label 'agent-01' }

    parameters {
        choice(
            name: 'action',
            choices: ['create', 'delete'],
            description: 'Select what you want to do: Create or Destroy the application'
        )
    }

    stages {
        stage('Git-Checkout') {
            steps {
                gitCheckout(
                    branch: "main",
                    url: "https://github.com/Shourawandy/my-simple-java.git"
                )
            }
        }

        stage('Maven Test') {
            when { expression { params.action == 'create' } }
            steps {
                mvnTest()
            }
        }

        stage('Maven Integration Test') {
            when { expression { params.action == 'create' } }
            steps {
                mavenIntregrationTest()   // update after renaming
            }
        }

        stage('Static Code Analysis: SonarQube') {
            when { expression { params.action == 'create' } }
            steps {
                staticCodeAnalaysis()     // update after renaming
            }
        }
     stage('Quality Gate: SonarQube') {
            when { expression { params.action == 'create' } }
            steps {
                script {
                    def qg = waitForQualityGate() 
                    if (qg.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
        }

        stage('maven build') {
            when { expression { params.action == 'create' } }
            steps {
              mvnBuild(goals: 'clean package -DskipTests') 
              mvnBuild()
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed'
        }
    }
}