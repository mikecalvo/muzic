package muzic

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class PlayService {

  def jmsService

  def songPlayed(String title, String artistName) {

    def song = Song.findByTitleAndArtist(title, Artist.findByName(artistName))
    if (song) {
      def play = new Play(song: song, timestamp: new Date()).save()

      def messageString = (play as JSON) as String
      log.info("Sending song message: ${messageString}")
      jmsService.send(queue:'song-played', messageString, { def message ->
        println "Closure: ${this.class.name}"
        log.info("Send completed ${message}")
      })

      return play
    }

    return null
  }
}
