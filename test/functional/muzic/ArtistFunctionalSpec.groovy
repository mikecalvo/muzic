package muzic

import geb.spock.GebSpec
import grails.plugin.remotecontrol.RemoteControl
import muzic.pages.ArtistGetPage
import muzic.pages.LoginPage
import spock.lang.Stepwise

@Stepwise
class ArtistFunctionalSpec extends GebSpec {

  def remote = new RemoteControl()

  def artistId

  def setupSpec() {
    to LoginPage

    login('me', 'password')
  }

  void setup() {
    artistId = remote {
      def artist = new Artist(name: "U2")
      artist.save()
      artist.id
    }
  }

  void cleanup() {
    remote {
      Artist.findByName('U2').delete()
    }
  }

  def "gets artist details"() {
    when:
    to ArtistGetPage, id: artistId

    then:
    name.text() == 'U2'
    id.text() == "Id: ${artistId}"
  }
}
