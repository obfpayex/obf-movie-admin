def mvnHome
def appName = 'vas-accounting-receiptfile-import'
def repositoryUrl = 'git@github.com:PayEx/vas-accounting-receiptfile-import.git'
def filePath

    def deployJob = 'spring-boot-dam-deployer'
    stage('Build') { // for display purposes
    node {
        // Get some code from a GitHub repository
        git repositoryUrl
        mvnHome = tool 'maven'

        try {
            // Run the maven build
            if (isUnix()) {
                sh "'${mvnHome}/bin/mvn' clean package"
            } else {
                bat("${mvnHome}\\bin\\mvn clean package")
            }
        } catch (err) {
            step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            throw err
        }

        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
        filePath = pwd() + '\\target\\*.jar'
        filePathConfig = pwd() + '\\config\\'
        filePathLib = ''
    }
}

stage('SonarQube analysis') {
    node{
        withSonarQubeEnv('SonarCube') {
            def scannerHome = tool 'SonarCubeVas';

            bat("${scannerHome}\\bin\\sonar-scanner " +
                "-Dsonar.projectName=${appName} " +
                "-Dsonar.projectKey=${appName} " +
                "-Dsonar.projectVersion=1.0 " +
                "-Dsonar.sources=. " +
                "-Dsonar.tests=. " +
                "-Dsonar.test.inclusions=**/target/surefire-reports/TEST-*.xml " +
                "-Dsonar.java.binaries=. " +
                "-Dsonar.jacoco.reportPaths=target/jacoco/jacoco.exec " +
                "-Dsonar.coverage.exclusions=src/main/java/com/payex/vas/config/**,src/main/java/com/payex/vas/Application*,src/main/resources/** " +
                "-Dsonar.exclusions=target/** " +
                "-Dsonar.issue.ignore.multicriteria=e1 " +
                "-Dsonar.issue.ignore.multicriteria.e1.ruleKey=squid:S00100 " +
                "-Dsonar.issue.ignore.multicriteria.e1.resourceKey=src/test/** ")
        }
    }
}

stage('Deploy to DEV (auto)') {
    node {
        echo 'deploying to DEV'
        build job: deployJob, parameters: [[$class: 'StringParameterValue', name: 'DeploymentTarget', value: 'dev'], [$class: 'StringParameterValue', name: 'AppName', value: appName], [$class: 'StringParameterValue', name: 'ArtifactFilePath', value: filePath], [$class: 'StringParameterValue', name: 'ConfigFilePath', value: filePathConfig], [$class: 'StringParameterValue', name: 'LibPath', value: filePathLib]]
    }
}

stage('Deploy to STAGE') {
    input 'Deploy to STAGE'

    node {
        echo 'deploying to STAGE'
        build job: deployJob, parameters: [[$class: 'StringParameterValue', name: 'DeploymentTarget', value: 'stage'], [$class: 'StringParameterValue', name: 'AppName', value: appName], [$class: 'StringParameterValue', name: 'ArtifactFilePath', value: filePath], [$class: 'StringParameterValue', name: 'ConfigFilePath', value: filePathConfig], [$class: 'StringParameterValue', name: 'LibPath', value: filePathLib]]
    }
}


stage('Package for PROD') {
    input 'Promote to PROD'

    node {
        echo 'packaging for PROD'
        build job: deployJob, parameters: [[$class: 'StringParameterValue', name: 'DeploymentTarget', value: 'prod'], [$class: 'StringParameterValue', name: 'AppName', value: appName], [$class: 'StringParameterValue', name: 'ArtifactFilePath', value: filePath], [$class: 'StringParameterValue', name: 'ConfigFilePath', value: filePathConfig], [$class: 'StringParameterValue', name: 'LibPath', value: filePathLib]]
    }
}
