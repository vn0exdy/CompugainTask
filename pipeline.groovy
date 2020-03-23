def v, a

//checkout from Git repositpory
stage 'checkout source code'
node{
    git url: https://github.com/vn0exdy/CompugainTask.git
    v = version()
    if (v) {
        echo "Building version ${v}"
    }
    a = artifactId()
    if (a) {
        echo "Building artifactId ${a}"
    }
}
    def version() {
        def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
        matcher ? matcher[0][1] : null
    }
    def artifactId() {
        def matcher = readFile('pom.xml') =~ '<artifactId>(.+)</artifactId>'
        matcher ? matcher[0][1] : null
    }

//Build using Maven
def build = 'Build '+ a+' and '+v
stage build
node{
  def mvnHome = tool 'M3'
    bat "mvn clean install"
}

//Approval Stage with timeout
def approval = 'Awaiting for approval for deploying '+a+ ' and '+v
stage approval
node() {
    timeout(time:5, unit:'MINUTES') {
        input message:'Approve deployment?', submitter: 'admin'
   }
}

//Deploying stage
def deploying = 'Deploying '+a+ ' and '+v
stage deploying

def tests = 'Executing tests'
stage tests
//test
