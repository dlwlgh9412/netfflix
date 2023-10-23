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
                scripts {
                    sh 'cp build/libs/netfflix.jar ./netfflix/deploy'
                    sh '''
                        if systemctl is-active --quiet netfflix
                        then
                            echo "Service is running, restarting.."
                            sudo systemctl restart netfflix
                        else
                            sudo systemctl start netfflix
                        fi
                       '''
                }
            }
        }
    }
}
