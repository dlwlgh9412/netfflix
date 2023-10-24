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
                scripts {
                    sh 'cp build/libs/netfflix-0.0.1-SNAPSHOT.jar ./netfflix/deploy'
                    sh 'pkill -f 'netfflix-0.0.1-SNAPSHOT.jar || true'
                    sh 'nohup java -jar ./netfflix/deploy/netfflix-0.0.1-SNAPSHOT.jar'
                }
            }
        }
    }
}
