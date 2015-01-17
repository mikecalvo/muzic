package muzic

import grails.converters.JSON

class PlayController {

  def playService

  def report(String title, String artistName) {
    def play = playService.songPlayed(title, artistName)
    render play as JSON
  }
}
