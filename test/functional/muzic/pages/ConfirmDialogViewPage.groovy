package muzic.pages

import geb.Page

class ConfirmDialogViewPage extends Page {
  static at = {
    waitFor { $('h3').first().text() ==~ 'Confirm .*' }
  }

  static content = {
    message { waitFor { $('.modal-body') } }
    okButton { waitFor { $('#ok-btn') } }
    cancelButton { waitFor { $('#cancel-btn') } }
  }
}
