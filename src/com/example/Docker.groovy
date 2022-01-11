#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.bat "docker build -t ${imageName} ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.bat "docker login -u ${script.USER} -p ${script.PASS}"
        }
    }

    def dockerPush(String imageName) {
        script.bat "docker push ${imageName}"
    }

}
