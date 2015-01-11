package muzic

import geb.spock.GebSpec
import grails.plugin.remotecontrol.RemoteControl
import muzic.pages.LoginPage
import muzic.pages.ProfilePage
import spock.lang.Stepwise

@Stepwise
class ProfileFunctionalSpec extends GebSpec {

  def setupSpec() {
    def remote = new RemoteControl()
    remote {
      def user = new User(username: 'profile-test', password: 'password')
      user.save(flush: true, failOnError: true)
      new Profile(email: 'profile@test.com', user: user).save(failOnError: true)
      UserRole.create(user, Role.findByAuthority('USER_ROLE'), true)
      return user.id
    }
    to LoginPage

    login('profile-test', 'password')
  }

  def cleanupSpec() {
    def remote = new RemoteControl()
    remote {
      def user = User.findByUsername('profile-test')
      def profile = Profile.findByUser(user)
      profile.delete()
      user.delete()
    }
  }

  def 'displays profile details'() {
    when:
    go 'profile/get/profile-test'

    then:
    at ProfilePage

    and:
    email.value() == 'profile@test.com'
  }

  def 'updates profile email'() {
    given:
    at ProfilePage

    when:
    email.value('modified@test.com')
    saveBtn.click()

    then:
    at ProfilePage

    and:
    message.text() == 'Profile saved'

    and:
    email.value() == 'modified@test.com'
  }

  def 'prevents accessing another profile'() {
    when:
    go 'profile/get/different-user'

    then:
    page.title == 'Denied'
  }

}
