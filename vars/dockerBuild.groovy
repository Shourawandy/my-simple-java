def call(String project, String ImageTag, hubUser) {
    echo "🚀 Building Docker image: ${imageName}:${tag}"
    sh """docker image build -t ${hubUser}/${project} .
    docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
    docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
    """
    echo "✅ Docker image ${imageName}:${tag} built successfully."
}
]