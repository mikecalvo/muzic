package muzic

import grails.plugin.remotecontrol.RemoteControl
import groovy.json.JsonSlurper
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DecompressingHttpClient
import org.apache.http.impl.client.DefaultHttpClient
import spock.lang.Specification

class SongRestFunctionalSpec extends Specification {

  static Integer songId

  JsonSlurper jsonSlurper = new JsonSlurper()

  private def client = new DefaultHttpClient()

  def setupSpec() {
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

  def cleanupSpec() {
    def remote = new RemoteControl()
    remote {
      Song.withTransaction {
        Song.findByTitle('Creep').delete()
        Artist.findByName('Radiohead').delete()
      }
    }
  }

  def 'returns song list'() {
    when:
    def resp = doGet('api/songs')
    assert resp.status == 200
    assert resp.contentType == 'application/json'
    def songs = resp.data

    then:
    songs.find { it.title == 'Creep' }
  }

  def 'returns song detail'() {
    when:
    def resp = doGet("api/songs/${songId}" as String)
    assert resp.status == 200
    assert resp.contentType == 'application/json'
    def song = resp.data

    then:
    song.id == songId
    song.title == 'Creep'
  }

  private def doGet(String path) {
    def baseUrl = System.getProperty('geb.build.baseUrl')
    def url = baseUrl + path
    def request = new HttpGet(url)
    def client = new DecompressingHttpClient(new DefaultHttpClient())
    def response = client.execute(request)

    String str = new String(response.entity?.content?.bytes, 'UTF-8');
    def contentType = response.entity?.contentType?.value
    if (contentType.contains(';charset=')) {
      contentType = contentType.split(';')[0]
    }
    return [
        status: response.statusLine.statusCode,
        contentType: contentType,
        data: jsonSlurper.parseText(str)
    ]
  }
}
