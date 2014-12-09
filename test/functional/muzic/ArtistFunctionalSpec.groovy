package muzic

import geb.spock.GebSpec
import muzic.pages.ArtistGetPage
import spock.lang.Stepwise

@Stepwise
class ArtistFunctionalSpec extends GebSpec {

  def "404 when no artist specified"() {
    when:
    to ArtistGetPage, id: 1

    then:
    name.text() == 'U2'
    id.text() == 1
  }
}
