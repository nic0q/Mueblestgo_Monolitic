pipeline {
    agent any
    tools{
        maven 'mvn'
    }
    environment{
        DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    }
    stages {
        stage('Build jar file') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://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@github.com/nic0q/muebles-stgo.git']]])
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Build docker image'){
            steps {
                bat 'docker build -t inse1n/mueblestgo .'
            }
        }
        stage('Login'){
            steps {
                bat 'docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%'
            }
        }
        stage('Push'){
            steps{
                bat 'docker push inse1n/mueblestgo'
            }
        }
    }
    post {
		always {
			bat 'docker logout'
		}
	}
}