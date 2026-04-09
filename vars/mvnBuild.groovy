def call() {
    echo "🚀 Starting Maven Build..."

    sh 'mvn clean package -DskipTests'

    echo "✅ Maven Build completed."
}