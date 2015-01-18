package muzic

import spock.lang.Specification

class PlayServiceSpec extends Specification {

    def playService

    def artist
    def song
    def profile
    def follow

    def setup() {
        artist = new Artist(name: 'The Cure').save(failOnError: true, flush: true)
        song = new Song(title: 'Just Like Heaven', artist: artist).save(failOnError: true, flush: true)
        profile = Profile.findByEmail('me@test.com')
        follow = new Follow(artist: artist, profile: profile).save(failOnError: true, flush: true)
    }

    def cleanup() {
        follow.delete()
        song.delete()
        artist.delete()
    }

    void "song play is saved"() {
        given:

        when:
        Play play = playService.songPlayed('Just Like Heaven', 'The Cure')

        then:
        Play.findBySong(song).id == play.id
        // ProfileMessage.findAllByProfile(profile).find { it.text.contains('Just Like Heaven')}
    }
}
