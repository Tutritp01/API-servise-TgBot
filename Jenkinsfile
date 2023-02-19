pipeline {
    agent any
    stages {
        stage('Build core') {
            steps {
               sh 'mvn clean package -f ./persistence-core/pom.xml'
            }
        }
        stage('Build all') {
            steps {
               sh 'mvn -DskipTests clean package'
            }
        }
        stage('Unit Tests') {
            steps {
               sh 'mvn clean test'
            }
        }
    }
}
