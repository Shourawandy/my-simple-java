def call() {
    echo "🚀 Starting SonarQube Static Code Analysis..."

    withSonarQubeEnv('SonarQube') {      // ← IMPORTANT: Change 'SonarQube' to the exact name you configured in Jenkins
        sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=todo-web-app'
    }

    echo "✅ SonarQube analysis completed."
}