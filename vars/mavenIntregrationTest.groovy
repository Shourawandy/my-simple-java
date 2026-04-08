def call() {
    sh 'mvn verify -DskipTests'
}