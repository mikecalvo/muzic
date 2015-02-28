package muzic.pages

import geb.Page

class PlaysViewPage extends Page {

  static at = {
    waitFor { $('h1').first().text() == 'Song Plays' }
  }

  static url = '#/plays'

  static content = {
    addPlayButton { $('#add-play-btn') }
    playsTotalCount {
      $('table tr').size() - 1
    }

    songTitle { Integer index -> $('table tr')[index].$('td')[0].text() }
    songArtist { Integer index -> $('table tr')[index].$('td')[1].text() }
    deleteButton { Integer index -> $('table tr')[index].$('.delete') }

    newPlayTitleText { waitFor { $('#title') } }
    newPlayArtistText { waitFor { $('#artist') } }
    saveNewPlayButton { waitFor { $('#save-play-btn') } }
  }

}
