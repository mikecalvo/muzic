package muzic

import grails.plugin.springsecurity.annotation.Secured

class ProfileController {

  def springSecurityService
  def profileService

  @Secured(closure = {
    def username = request.requestURI.substring(request.requestURI.lastIndexOf('/')+1)
    authentication.principal.username == username
  })
  def get() {
    [profile: Profile.findByUser(springSecurityService.currentUser as User)]
  }

  @Secured(closure = {
    def username = request.requestURI.substring(request.requestURI.lastIndexOf('/')+1)
    authentication.principal.username == username
  }, httpMethod = 'POST')
  def update() {
    def profile = profileService.updateProfile(params.id, params)
    if (profile.hasErrors()) {
      flash.error = 'Error saving'
      flash.errors = profile.errors.allErrors
    } else {
      flash.info = 'Profile saved'
    }
    redirect(action: 'get', id: profile.user.username)
  }
}
