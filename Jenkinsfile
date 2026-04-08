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
                mvnTest()                     // This one is correct
            }
        }

        stage('Maven Integration Test') {
            when { expression { params.action == 'create' } }
            steps {
                mavenIntregrationTest()       // ← Must match exactly (with "e")
            }
        }

        stage('Static Code Analysis: SonarQube') {
            when { expression { params.action == 'create' } }
            steps {
                staticCodeAnalaysis()         // ← Must match exactly
            }
        }
    }
}