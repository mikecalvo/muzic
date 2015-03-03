package muzic.pages

import geb.Page

class PlaysViewPage extends Page {

  static at = {
    waitFor { $('h1').first().text() == 'Song Plays' }
  }

  static url = '#/plays'

  static content = {
    alertCount { $('div.alert div').size() }
    alertMessage { Integer wait -> waitFor(wait) { $('div.alert span')[2] } }
    closeAlertButton { $('div.alert button') }
    addPlayButton { $('#add-play-btn') }
    playsTotalCount {
      $('table tr').size()
    }

    songTitle { Integer index -> $('table tr')[index].$('td')[0].text() }
    songArtist { Integer index -> $('table tr')[index].$('td')[1].text() }
    deleteButton { Integer index -> $('table tr')[index].$('.delete') }

    newPlayTitleText { waitFor { $('#title') } }
    newPlayArtistText { waitFor { $('#artist') } }
    saveNewPlayButton { waitFor { $('#save-play-btn') } }
  }

}
