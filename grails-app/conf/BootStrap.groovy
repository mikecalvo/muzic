import muzic.Artist

class BootStrap {

    def init = { servletContext ->

        if (Artist.count() == 0) {
            def u2 = new Artist(name: 'U2')
            u2.save(failOnError: true)
        }
    }
    def destroy = {
    }
}
