module.exports = function (grunt) {

  grunt.loadNpmTasks("grunt-shell");

  grunt.initConfig({
    shell: {
      options: {
        stdout: true
      },
      localInstall: {
        command: "./node_modules/.bin/bower update --quiet --offline --force"
      },

      webInstall: {
        command: "./node_modules/.bin/bower update --quiet --force"
      }
    }
  });

  grunt.registerTask("localInstall", [ "shell:localInstall"]);
  grunt.registerTask("webInstall", [ "shell:webInstall"]);
  grunt.registerTask("default", ["localInstall"]);
};