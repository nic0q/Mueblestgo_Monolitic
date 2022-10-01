pipeline {
    agent any
    tools{
        maven 'mvn'
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
        stage('Pubat docker image'){
            steps {
                script{
                    withCredentials([string(credentialsId: 'DcHiC1cu7Ob', variable: 'DcHiC1cu7Ob')]) {
                        bat 'docker login -u inse1n -p ${DcHiC1cu7Ob}'
                    }
                    bat 'docker pubat inse1n/mueblestgo'
                }
            }
        }
    }
    post {
		always {
			bat 'docker logout'
		}
	}
}