pipeline {
    agent {
        docker {
            image "maven:3.6.3-openjdk-15"
            label "docker"
        }
    }
    
    stages {
        stage("Build") {
            steps {
                sh "mvn -v"
                sh "mvn clean package"
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
}
