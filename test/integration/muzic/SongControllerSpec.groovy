package muzic

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(SongController)
class SongControllerSpec extends Specification {

  def 'lyric extractor is wired correctly'() {
    expect:
    controller.lyricExtractor
  }
}
