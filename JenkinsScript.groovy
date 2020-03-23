def appName
def VersionNumber
def nodeLabel = 'master'
def approvalList 
node('${nodeLabel}'){
    appName = "JenkinsAssignment"
    stage('Checkout'){
        branchName = "master"
        url = "https://github.com/vn0exdy/CompugainTask.git"
        git branch: "${branchName}", url: "${url}"
    }
    stage('Preparation'){
    // Get the Maven tool.
    // Set it as Maven Home
    mvnHome = tool 'maven3'
    def pom = readMavenPom file: 'pom.xml'
    versionNumber = "${pom.version.replace("\${version}","${currentBuild.number}")}"
    currentBuild.displayName = versionNumber
        
    }
    stage("Build ${appName} and ${versionNumber}"){
  
      if (isUnix()) {
        // Run the maven build with tests when machine is linux
        sh "'${mvnHome}/bin/mvn' -U -s '$MAVEN_SETTINGS' -Dversion=$BUILD_NUMBER clean package"
      } 
      else {
      // Run the maven build with tests when machine is windows
      bat(/"${mvnHome}\bin\mvnmaven -U  -Djenkinsversion=$BUILD_NUMBER clean package /)
      } 
    }
    stage("Approval ${appName} and ${versionNumber}"){

      timeout(time: 5, unit: "MINUTES") {
        input message: "Deploy Application: ${appName} and version number: ${versionNumber}", submitter: "${approvalList}"  
      }      
    }
    stage("Deploy ${appName} and ${versionNumber}"){
         println "Deploy using ansible" 
    }
    stage("Test ${appName} and ${versionNumber}"){
      println "Validate by using ansible or curl to get the status"
    }
    
    
}