@Library('my-jenkins-shared-lib') _
pipeline {
    agent {label 'agent-01'}
    parameters {
    choice(
        name: 'action',
        choices: ['create', 'delete'],
        description: 'Select what you want to do: Create or Destroy the application')
                    }

    stages {
        stage('Git-Checkout') {
            steps {
              gitCheckout (
               branch: "main",
               url: "https://github.com/Shourawandy/my-simple-java.git")
            }
        }

        stage('maven test') {
        when { expression { return params.action == 'create' } }
            steps {
                script {
                    mvnTest()
                }
            }
        }

        stage('maven intregration test') {
            when { expression { return params.action == 'create' } }
            steps {
                 script {mavenIntregrationTest() }
        }}

        stage('static code analysis:sonarqube') {
            when {expression { return params.action == 'create' } }
            steps {
                    script {staticCodeAnalaysis() }
        }
    }

    
    
}
}