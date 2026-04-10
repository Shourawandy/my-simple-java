def call(String imageName, String imageTag, String hubUser) {
    echo "Pushing Docker image: ${hubUser}/${imageName}:${imageTag}"

    withCredentials([string(credentialsId: 'jenkins-docker', variable: 'jenkins-docker-pass')]) {
        sh """
            docker login -u ${hubUser} -p \${jenkins-docker-pass}
            docker push ${hubUser}/${imageName}:${imageTag}
            docker push ${hubUser}/${imageName}:latest
        """
    }

    echo "✅ Docker image push completed successfully!"
}