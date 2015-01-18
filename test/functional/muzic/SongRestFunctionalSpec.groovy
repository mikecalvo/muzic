package muzic

import spock.lang.Specification

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
    when:
    def resp = doGet("api/songs/${songId}" as String)
    assert resp.status == 200
    assert resp.contentType == 'application/json'
    def song = resp.data

    then:
    song.id == songId
    song.title == 'Creep'
  }
}
