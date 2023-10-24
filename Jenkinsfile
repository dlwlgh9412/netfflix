pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew -Pprofile=local build'
            }
        }
        stage('Deploy') {
            steps {
                sh 'cp build/libs/netfflix-0.0.1-SNAPSHOT.jar /home/netfflix'
                sh 'pkill -f netfflix-0.0.1-SNAPSHOT.jar || true'
                sh 'nohup java -jar /home/netfflix/netfflix-0.0.1-SNAPSHOT.jar &'
            }
        }
    }
}
