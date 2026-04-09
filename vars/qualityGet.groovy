def call() { 
    echo "⏳ Waiting for SonarQube Quality Gate results..."
    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-qube-test'
    echo "✅ Quality Gate check completed."}

