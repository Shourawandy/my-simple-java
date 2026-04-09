stage("Quality Gate: SonarQube") {
    steps {
        script {
            // This waits for SonarQube to send a callback to Jenkins
            timeout(time: 1, unit: 'HOURS') { 
                def qg = waitForQualityGate()
                if (qg.status != 'OK') {
                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
                }
            }
        }
    }
}