package muzic

import spock.lang.Specification

class PlayServiceSpec extends Specification {

    def jmsService
    def playService

    def artist
    def song

    def setup() {
        artist = new Artist(name: 'The Cure').save(failOnError: true, flush: true)
        song = new Song(title: 'Just Like Heaven', artist: artist).save(failOnError: true, flush: true)
    }

    def cleanup() {
        song.delete()
        artist.delete()
    }

    void "message is sent to JMS when a song is played"() {
        given:
        Integer messageCount = jmsService.browse('song-played').size()

        when:
        Play play = playService.songPlayed('Just Like Heaven', 'The Cure')

        then:
        play
        jmsService.browse('song-played').size() == messageCount+1
    }
}
