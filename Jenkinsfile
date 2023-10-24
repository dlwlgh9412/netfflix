pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo cp build/libs/netfflix-0.0.1-SNAPSHOT.jar /home/netfflix'
                sh 'sudo pkill -f netfflix-0.0.1-SNAPSHOT.jar || true'
                sh 'sudo nohup java -Dspring.profiles.active=local -jar /home/netfflix/netfflix-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
