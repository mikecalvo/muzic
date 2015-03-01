import muzic.*

class BootStrap {

  def setupAccessControl = {

    ['ROLE_ADMIN', 'ROLE_USER'].each {
      if (!Role.findByAuthority(it)) {
        new Role(authority: it).save(flush: true, failOnError: true)
      }
    }

    ['me', 'you'].each { String username ->
      if (User.findByUsername(username)) {
        return
      }

      def testUser = new User(username: username, password: 'password')
      testUser.save(flush: true)
      new Profile(email: "${username}@test.com", user: testUser).save(flush: true, failOnError: true)
      log.info("Created test user: ${username}")
      Role.list().each { Role role ->
        new UserRole(user: testUser, role: role).save(flush: true, failOnError: true)
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
              'New Order': ['Blue Monday', 'Temptation', 'True Faith'],
              'Daft Punk': ['One More Time', 'Lucky', 'Around the World']
          ].each { def artistName, def tracks ->
            def artist = new Artist(name: artistName)
            artist.save(failOnError: true)
            tracks.each { def track ->
              def song = new Song(artist: artist, title: track).save(failOnError: true)
              def timestamp = new Date() - ((song.id+artist.id) as int)
              new muzic.Play(song: song, timestamp: timestamp).save(failOnError: true)
            }
            log.info("Saved artist ${artist.name} (${artist.id})")
          }
        }
      }
    }
  }

  def destroy = {
  }
}
