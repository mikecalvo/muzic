import muzic.Artist
import muzic.Audit
import muzic.Profile
import muzic.Role
import muzic.User
import muzic.UserRole

class BootStrap {

  def setupAccessControl = {
    ['ROLE_ADMIN', 'ROLE_USER'].each {
      new Role(authority: it).save(flush: true)
    }

    ['me', 'you'].each { String username ->
      def testUser = new User(username: username, password: 'password')
      testUser.save(flush: true)
      new Profile(email: "${username}@test.com", user: testUser).save(flush: true)
      log.info("Created test user: ${username}")
      Role.list().each { Role role ->
        UserRole.create(testUser, role, true)
        log.info("Role added to ${username}: ${role.authority}")
      }
    }
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
