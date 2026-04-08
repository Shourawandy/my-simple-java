def call() {
    echo " Starting SonarQube Static Code Analysis..."

    withSonarQubeEnv('sonar-qube-api') {
        withCredentials([string(credentialsId: 'sonar-qube-test', variable: 'SONAR_TOKEN')]) {
            sh '''
                mvn clean verify sonar:sonar \
                    -Dsonar.projectKey=todo-web-app \
                    -Dsonar.token=$SONAR_TOKEN
            '''
        }
    }

    echo " SonarQube analysis completed."
}