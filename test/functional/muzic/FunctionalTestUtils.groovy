package muzic

import grails.plugin.remotecontrol.RemoteControl
import grails.util.Holders
import groovy.json.JsonSlurper
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DecompressingHttpClient
import org.apache.http.impl.client.DefaultHttpClient

class FunctionalTestUtils {

  JsonSlurper jsonSlurper = new JsonSlurper()
  Integer songId

  def setupSampleData() {
    def remote = new RemoteControl()
    songId = remote {
      def radiohead = new Artist(name: 'Radiohead')
      radiohead.save(flush: true)
      def song = new Song(title: 'Creep', artist: radiohead)
      song.save(flush: true)
      println "Song id ${song.id}"
      return song.id
    } as Integer
  }

  def cleanupSampleData() {
    def remote = new RemoteControl()
    remote {
      Song.withTransaction {
        def creep = Song.findByTitle('Creep')
        if (creep) {
          Play.findAllBySong(creep).each { it.delete() }
          creep.delete()
        }
        def radiohead = Artist.findByName('Radiohead')
        if (radiohead) {
          Follow.findAllByArtist(radiohead).each { it.delete() }
          radiohead.delete()
        }
      }
    }
  }

  def doGet(String path) {
    String url = Holders.config.grails.serverURL + '/' + path
    def request = new HttpGet(url)
    def client = new DecompressingHttpClient(new DefaultHttpClient())
    def response = client.execute(request)

    String str = new String(response.entity?.content?.bytes, 'UTF-8');

    def contentType = response.entity?.contentType?.value
    if (contentType?.contains(';charset=')) {
      contentType = contentType.split(';')[0]
    }
    return [
        status     : response.statusLine.statusCode,
        contentType: contentType,
        data       : jsonSlurper.parseText(str)
    ]
  }
}
