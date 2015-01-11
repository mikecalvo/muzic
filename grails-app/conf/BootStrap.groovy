import muzic.Artist
import muzic.Audit
import muzic.Role
import muzic.User
import muzic.UserRole

class BootStrap {

  def setupAccessControl = {
    def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
    def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
    log.info('Created admin and user roles')

    def testUser = new User(username: 'me', password: 'password')
    testUser.save(flush: true)
    log.info('Created test user')

    UserRole.create testUser, adminRole, true
    UserRole.create testUser, userRole, true

    assert User.count() == 1
    assert Role.count() == 2
    assert UserRole.count() == 2

  }

  def init = { servletContext ->

    environments {

      test {
        setupAccessControl()
      }
      
      development {
        setupAccessControl()
        new Audit(user: 'system', action: 'started').save()

        if (!Artist.count()) {
          [
              new Artist(name: 'New Order'),
              new Artist(name: 'Daft Punk')
          ].each {
            def artist = it.save(failOnError: true)
            log.info("Saved artist ${artist.id}")
          }
        }
      }
    }
  }

  def destroy = {
  }
}
