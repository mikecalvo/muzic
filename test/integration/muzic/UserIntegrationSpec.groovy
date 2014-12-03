package muzic

import spock.lang.Specification

class UserIntegrationSpec extends Specification {

  def 'saving user persists new user in database'() {
    setup:
    def user = new User(email: 'mike@calvo.com', password: '123456')

    when:
    user.save()

    then:
    user.errors.errorCount == 0
    user.id
    user.dateCreated
    User.get(user.id).email == 'mike@calvo.com'
  }
}
