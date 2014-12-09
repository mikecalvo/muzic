package muzic.pages

import geb.Page

class ArtistGetPage extends Page {

  static url = 'artist/get'

  static content = {
    id { $("#id") }
    name { $("#name") }
  }

}
