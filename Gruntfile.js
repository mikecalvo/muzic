module.exports = function (grunt) {

  grunt.loadNpmTasks("grunt-shell");

  grunt.initConfig({
    shell: {
      options: {
        stdout: true
      },
      localInstall: {
        command: "./node_modules/.bin/bower update --force --quiet --offline"
      },

      webInstall: {
        command: "./node_modules/.bin/bower update --force --quiet"
      }
    }
  });

  grunt.registerTask("localInstall", [ "shell:localInstall"]);
  grunt.registerTask("webInstall", [ "shell:webInstall"]);
  grunt.registerTask("default", ["localInstall"]);
};