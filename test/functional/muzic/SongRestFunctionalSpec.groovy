package muzic

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class SongRestFunctionalSpec extends Specification {

  static Integer songId
  static Integer radioheadId

  static HttpUtils httpUtils = new HttpUtils()

  def setupSpec() {
    def response = httpUtils.doFormPost('j_spring_security_check', [j_username: 'me', j_password: 'password'])
    assert response.status == 302
    assert response.statusText == 'Found'

    def remote = new MuzicRemoteControl()
    songId = remote {
      def radiohead = new Artist(name: 'Radiohead')
      radiohead.save(flush: true)
      def song = new Song(title: 'Creep', artist: radiohead)
      song.save(flush: true)
      println "Song id ${song.id}"
      return song.id
    } as Integer

    radioheadId = remote {
      Artist.findByName('Radiohead').id
    } as Integer
  }

  def cleanupSpec() {
    def remote = new MuzicRemoteControl()
    remote {
      Song.withTransaction {
        def radiohead = Artist.findByName('Radiohead')
        Song.findAllWhere([artist: radiohead]).each {
          it.delete()
        }
        radiohead.delete()
      }
    }
  }

  def 'returns song list'() {
    when:
    def resp = httpUtils.doGet('api/songs')
    assert resp.status == 200
    assert resp.contentType == 'application/json'
    def songs = resp.data

    then:
    songs.find { it.title == 'Creep' }
  }

  def 'returns song detail'() {
    when: 'A REST Call is made to get a known song by id'
    def resp = httpUtils.doGet("api/songs/${songId}" as String)

    then: 'Server responds with OK status'
    resp.status == 200

    and: 'Server returns JSON'
    resp.contentType == 'application/json'

    and: 'Id and title are what we expect from the setup'
    resp.data.id == songId
    resp.data.title == 'Creep'
  }

  def 'creates a song'() {
    when: 'Create REST Call is made'
    def resp = httpUtils.doJsonPost('api/songs', [title: 'Lucky', artist: [id: radioheadId, name: 'Radiohead']])

    then: 'Server returns a status of Created'
    resp.status == 201

    and: 'The saved data has an id value'
    resp.data.id

    when: 'Get REST Call is made for created song'
    resp = httpUtils.doGet("api/songs/${resp.data.id}" as String)

    then: 'Server returns OK status'
    resp.status == 200

    and: 'Server returns JSON'
    resp.contentType == 'application/json'

    and: 'The title of the created song is the title we sent in the JSON'
    resp.data.title == 'Lucky'
  }
}

