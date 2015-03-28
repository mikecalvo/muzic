class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?" {
      constraints {
        // apply constraints here
      }
    }

    "/api/artists"(resources: 'artistRest')
    "/api/songs-custom"(resources: 'songRest')

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
