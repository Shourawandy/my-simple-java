stage("Quality Gate: SonarQube") {
    steps {
        script {
            // This step waits for the SonarQube analysis to be processed
            // and returns the status (PASSED, WARN, or FAILED)
            def qg = waitForQualityGate() 
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
        }
    }
}