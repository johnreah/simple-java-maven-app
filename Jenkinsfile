pipeline {

    environment {
        JAVA_TOOL_OPTIONS = "-Duser.home=/var/maven"
    }

    agent {
        docker {
            image "maven:3.6.3-openjdk-15"
            args "-v /tmp/maven:/var/maven/.m2 -e MAVEN_CONFIG=/var/maven/.m2"
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
