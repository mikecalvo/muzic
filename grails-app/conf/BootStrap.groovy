import muzic.Audit

class BootStrap {

    def init = { servletContext ->
      environments {
        development {
          new Audit(user: 'system', action: 'started').save()
        }
      }
    }
    def destroy = {
    }
}
