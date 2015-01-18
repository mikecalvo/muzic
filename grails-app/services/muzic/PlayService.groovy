package muzic
import grails.converters.JSON

class PlayService {

  def jmsService
  static def SEND_QUEUE = 'songPlayed'

  static transactional = false

  def songPlayed(String title, String artistName) {

    def song = Song.findByTitleAndArtist(title, Artist.findByName(artistName))
    if (song) {
      def before = Play.count()
      def play = Song.withTransaction {
        new Play(song: song, timestamp: new Date()).save(flush: true, failOnError: true)
      }
      assert Play.count() == before+1

      def messageString = (play as JSON) as String
      log.info("Sending song message: ${messageString}")
      jmsService.send(queue:SEND_QUEUE, messageString, { def message ->
        log.info("Send completed ${message}")
      })

      println '********* On songPlayed end: songs='+Song.count+' artists='+Artist.count+' profiles='+Profile.count
      return play
    }

    return null
  }
}
