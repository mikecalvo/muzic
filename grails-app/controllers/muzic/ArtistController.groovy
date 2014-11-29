package muzic

import grails.converters.JSON

class ArtistController {

    def index() {
      def count = params.count != null ? Math.min(params.count, 10) : 10
      render Artist.list(max: count) as JSON
    }
}
