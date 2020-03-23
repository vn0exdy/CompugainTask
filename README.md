# Jenkins pipeline for the Build And Deployment

## Sample Jenkins Job
#Variables
```
  * Variables can be set in Jenkinsfile or Jenkins Job
  * **********
  *   appName:            Application Name
  *   nodeLabel:          label of the node where code need to build and deploy
  *   approvalList:       approval list for the deployment of code
```
# Checkout
```
Git checkout the code  need the folling items required
  *   url:                Git Url to clone the code
  *   branch:             Git branch which need to code if you dont define branch defaulr it will take master branch
  *   credentialsId:      UserName and Password or SSH key to get access to clone the code

```
# Preparation

```
Preparation stage read the pom.xml file from the code and get the version number and predefined version parameter will replace and form the incremental version for the build and set the same number as display number

note: 1.0.0.1 is define as ${majorRelease}.${minorRelease}.${patchNumber}.${incrementalNumber} as per the coding standards can change version strategy  
```

#build

```
Build the code using mvn tool if mvn is not installed using jenkins global maven setup and install required packages and build code with the incremental number

note: identifying the os type  and build can be done

```

# Approval

```

Approval process can be done as a input varaible with proper approval list  who can only summit the next step

```

# Deployment

```
Once approval is done deployment can be done by using ansible with the proper inventory managment and paths setup

note: Deployment can de done by using configuration management tools

```

# Validation

```
validation can be done by using many ways always prefer for the configuration management tool, check the issues as apart of post deployment steps in configuration management tools

```
