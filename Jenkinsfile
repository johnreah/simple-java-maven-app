pipeline {

    environment {
        JAVA_TOOL_OPTIONS = "-Duser.home=/home/jenkins"
    }

    agent {
        dockerfile {
            args "-v /tmp/maven:/home/jenkins/.m2 -e MAVEN_CONFIG=/home/jenkins/.m2"
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
