def call(String imageName, String imageTag, String hubUser) {

    echo "🚀 Building Docker image: ${hubUser}/${imageName}:${imageTag}"

    sh """
        docker build -t ${hubUser}/${imageName}:${imageTag} .
        docker tag ${hubUser}/${imageName}:${imageTag} ${hubUser}/${imageName}:latest
    """

    echo "✅ Docker image ${hubUser}/${imageName}:${imageTag} built successfully."
}