package muzic

import grails.rest.RestfulController

class ArtistRestController extends RestfulController<Artist> {

  @SuppressWarnings("GroovyUnusedDeclaration")
  static responseFormats = ['json', 'xml']

  ArtistRestController() {
    super(Artist)
  }
}
