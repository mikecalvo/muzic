package muzic
import geb.spock.GebSpec
import muzic.pages.ConfirmDialogViewPage
import muzic.pages.LoginPage
import muzic.pages.PlaysViewPage
import spock.lang.Stepwise

@Stepwise
class PlaysFunctionalSpec extends GebSpec {

  static String clasicSongName = 'Wake Me Up Before You Go Go'
  static String longForgottenArtistName = 'Wham!'

  def setupSpec() {
    to LoginPage

    login('me', 'password')
  }

  def 'adds a play'() {
    setup:
    to PlaysViewPage
    int startPlaysCount = playsTotalCount

    when:
    addPlayButton.click()

    then:
    newPlayTitleText.displayed

    and:
    newPlayArtistText.displayed

    and:
    saveNewPlayButton.@disabled

    when:
    newPlayTitleText.value(clasicSongName)
    newPlayArtistText.value(longForgottenArtistName)

    then:
    !saveNewPlayButton.@disabled

    when:
    saveNewPlayButton.click()

    then:
    alertMessage(5).text() == "Added play: \"${clasicSongName}\" by ${longForgottenArtistName}"

    and:
    playsTotalCount == startPlaysCount + 1

    and:
    songTitle(playsTotalCount-1) == clasicSongName
    songArtist(playsTotalCount-1) == longForgottenArtistName

    when:
    closeAlertButton.click()

    then:
    alertCount == 0
  }

  def 'deletes a play'() {
    setup:
    at PlaysViewPage
    int lastPlayIndex = playsTotalCount -1

    when:
    deleteButton(lastPlayIndex).click()

    then: "Confirmation dialog appears"
    at ConfirmDialogViewPage

    and: "Confirmation message has my song name"
    message.text().contains(clasicSongName)

    when:
    cancelButton.click()
    waitFor { !$('.modal').displayed }

    then: "psych - didn't delete it"
    at PlaysViewPage

    when:
    deleteButton(lastPlayIndex).click()

    then:
    at ConfirmDialogViewPage

    when:
    okButton.click()

    then: "dialog closes"
    at PlaysViewPage

    and:
    alertMessage(5).text() == 'Song play removed'

    and: "we have one fewer plays"
    playsTotalCount == lastPlayIndex
  }
}
