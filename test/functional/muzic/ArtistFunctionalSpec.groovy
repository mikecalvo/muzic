package muzic

import geb.spock.GebSpec
import grails.plugin.remotecontrol.RemoteControl
import muzic.pages.ArtistGetPage
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class ArtistFunctionalSpec extends GebSpec {

  def remote = new RemoteControl()

  @Shared
  def artistId

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
    def xxx = artistId
    to ArtistGetPage, id: xxx

    then:
    name.text() == 'U2'
    id.text() == "Id: ${xxx}"
  }
}
