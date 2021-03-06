#!/usr/bin/env groovy
import groovy.json.JsonSlurper
import groovy.transform.ToString

class Artifact {
    String groupId
    String artifactId
    String packaging
    String version
    String url
    
    String toString() {
        return "Artifact: artifactId=" + artifactId + " url=" + url
    }
}

def findInNexus(Artifact artifact) {
    def connection = new URL(""
            + "http://172.17.0.2:8081/service/rest"
            + '/v1/search'
            + "?repository=maven-public"
            + "&group=${artifact.groupId}"
            + "&name=${artifact.artifactId}"
            + "&sort=version"
            + "&direction=desc")
            .openConnection() as HttpURLConnection
    connection.setRequestProperty('Accept', 'application/json')
    if (connection.responseCode == 200) {
        def json = connection.inputStream.withCloseable { new JsonSlurper().parse(it) }
        def asset = json.items[0].assets.find{it.path.endsWith(artifact.packaging)}
        artifact.version = asset.maven2.version
        artifact.url = asset.downloadUrl
    } else {
        throw new Exception("connection error")
    }
}

def collectUrls() {
List<Artifact> artifacts = [
        new Artifact(groupId: "com.mycompany.app", artifactId: "my-app", packaging: "jar"),
        new Artifact(groupId: "com.google.collections", artifactId: "google-collections", packaging: "jar"),
        new Artifact(groupId: "commons-logging", artifactId: "commons-logging-api", packaging: "jar")
]
  artifacts.each {
      findInNexus it
      println it.toString()
  }
}

return this

