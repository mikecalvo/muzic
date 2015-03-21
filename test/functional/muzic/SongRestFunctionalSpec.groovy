package muzic

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class SongRestFunctionalSpec extends Specification {

  @Delegate static FunctionalTestUtils utils = new FunctionalTestUtils()

  def setupSpec() {
    if (!utils) {
      utils = new FunctionalTestUtils()
    }
    setupSampleData()
  }

  def cleanupSpec() {
    cleanupSampleData()
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
    when: 'A REST Call is made to get a known song by id'
    def resp = doGet("api/songs/${songId}" as String)

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
    def resp = doJsonPost('api/songs', [title: 'Lucky', artist: [id: radioheadId, name: 'Radiohead']])

    then: 'Server returns a status of Created'
    resp.status == 201

    and: 'The saved data has an id value'
    resp.data.id

    when: 'Get REST Call is made for created song'
    resp = doGet("api/songs/${resp.data.id}" as String)

    then: 'Server returns OK status'
    resp.status == 200

    and: 'Server returns JSON'
    resp.contentType == 'application/json'

    and: 'The title of the created song is the title we sent in the JSON'
    resp.data.title == 'Lucky'
  }
}
