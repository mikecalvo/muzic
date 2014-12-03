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

  def 'updating a user changes data'() {
    setup:
    def user = new User(email: 'joe@smith.com', password: '7654321').save(failOnError: true)
    user.save(failOnError: true)

    when:
    def foundUser = User.get(user.id)
    foundUser.password = 'secure'
    foundUser.save(failOnError: true)

    then:
    User.get(user.id).password == 'secure'
  }

  def 'deleting an existing user removes it from the database'() {
    setup:
    def user = new User(email: 'pat@ska.com', password: 'skillz')
    user.save(failOnError: true)

    when:
    user.delete()

    then:
    !User.exists(user.id)
  }

  def 'saving a user with invalid properties fails to validate'() {
    setup:
    def user = new User(email: 'not', password: '123', status: 'a'*200)

    when:
    def result = user.save()

    then:
    !result
    user.errors.errorCount == 3
    user.errors.getFieldError('email').rejectedValue == 'not'
  }
}
