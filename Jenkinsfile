pipeline {
    agent {
        dockerfile true
    }
    
    tools {
        maven "Maven 3.6.3"
    }
    
    stages {
        stage("Build") {
            steps {
                sh "git clone https://github.com/johnreah/simple-java-maven-app.git ."
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
