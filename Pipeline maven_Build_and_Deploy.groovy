pipeline {
    agent any

        
         stages {
                  stage('Git Clone') {
                         steps {
                               // Get some code from a GitHub repository
                               git 'https://github.com/jleetutorial/maven-project.git'

                              }
                         }
                   //
                 stage('Build') {
                       steps {
             
                            sh '''#!/bin/bash
                             mvn package
                            '''

                           }
                       }
                //
                stage('Archive the artifact') {
                    steps {

                    archiveArtifacts artifacts: '**/*.war'//, followSymlinks: false
                
                    }
                
                }
                //
                stage('deploy the artifact') {
                    steps {

                deploy adapters: [tomcat9(credentialsId: 'cd0f80d3-8181-4d1a-9236-c1a5f8d07a24', path: '', url: 'http://3.93.151.218:8888/')], contextPath: 'maven-project', war: '**/*.war'                
                 //   deploy adapters: [tomcat9(credentialsId: 'b2fbcb6f-a045-4f5c-b547-3f58d96536b0', path: '', url: 'http://3.93.151.218:8888/')], contextPath: 'maven-project', war: '**/*.war'                    }
                
                }
            // 
             }
         }

}

