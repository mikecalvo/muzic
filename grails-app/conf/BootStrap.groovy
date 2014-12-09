import muzic.Artist
import muzic.Audit

class BootStrap {

  def init = { servletContext ->
    environments {
      development {
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
