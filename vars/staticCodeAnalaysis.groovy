def call() {
    echo "🚀 Starting SonarQube Static Code Analysis..."

    // Change 'SonarQube' below to the exact name you configured in Jenkins
    withSonarQubeEnv('sonar-qube-api') {
        sh 'mvn clean verify sonar:sonar'
    }

    echo "✅ SonarQube analysis completed."
}