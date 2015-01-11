package muzic.pages

import geb.Page

class ProfilePage extends Page {

  static url = 'profile/get/${id}'

  static at = {
    title == 'Profile Detail Page'
  }

  static content = {
    email { $('#email') }
    username { $('#username') }
    message { $('.message') }
    saveBtn { $('#save-btn') }
  }
}
