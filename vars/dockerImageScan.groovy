def call(String imageName, String imageTag, String hubUser) {

    echo "Docker image scan is not implemented yet. This is a placeholder for future implementation."

    sh """
        trivy image  ${hubUser}/${imageName}:${imageTag} > scan-results.txt
        cat scan-results.txt
    """

    echo "Docker image scan completed."
}