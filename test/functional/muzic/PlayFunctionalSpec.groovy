package muzic

import spock.lang.Specification

class PlayFunctionalSpec extends Specification {

  @Delegate static FunctionalTestUtils utils = new FunctionalTestUtils()

  def remote = new MuzicRemoteControl()

  def setupSpec() {
    if (!utils) {
      utils = new FunctionalTestUtils()
    }
    setupSampleData()
  }

  def cleanupSpec() {
    cleanupSampleData()
  }

  def 'report a play'() {
    given:
    def followId = remote {
      new Follow(profile: Profile.findByEmail('me@test.com'), artist: Artist.findByName('Radiohead')).save(flush: true).id
    }
    def originalProfileMessageCount = remote { ProfileMessage.count }
    assert followId

    when:
    def resp = utils.doGet('play/report?title=Creep&artistName=Radiohead')
    Thread.sleep(250)

    then:
    resp.data.id
    resp.data.song.id == songId
    remote { ProfileMessage.count } == originalProfileMessageCount+1

  }
}
