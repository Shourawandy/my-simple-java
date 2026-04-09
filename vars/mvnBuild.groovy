def call(Map config = [:]) {
    def goals = config.goals ?: 'clean package -DskipTests'
    echo "Running Maven build with goals: ${goals}"
    sh "mvn ${goals}"
}