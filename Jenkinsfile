pipeline {
    agent any
    tools{
        maven 'mvn'
    }
    stages {
        stage('Build jar file') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://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@github.com/nic0q/muebles-stgo.git']]])
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build docker image'){
            steps {
                sh 'docker build -t inse1n/mueblestgo .'
            }
        }
        stage('Push docker image'){
            steps {
                script{
                    withCredentials([string(credentialsId: 'DcHiC1cu7Ob', variable: 'DcHiC1cu7Ob')]) {
                        sh 'docker login -u inse1n -p ${DcHiC1cu7Ob}'
                    }
                    sh 'docker push inse1n/mueblestgo'
                }
            }
        }
    }
    post {
		always {
			sh 'docker logout'
		}
	}
}