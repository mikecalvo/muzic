eventCompileStart = { kind ->
  executeNpmInstall()
  executeBowerInstall()
  executeGruntTasks()
}

private void executeNpmInstall() {
  if (new File('node_modules').exists()) {
    return
  }

  def npmInstall = System.properties['os.name'].toLowerCase().contains('windows') ? "cmd.exe /C npm install" : "npm install"

  println "| npm install..."
  def proc = npmInstall.execute()
  proc.waitFor()
  if (proc.exitValue() != 0) {
    println "Error installing npm dependencies"
    println "Output: ${proc.in.text}"
  }
}

private void executeBowerInstall() {
  def bowerInstall = "node_modules/.bin/bower install"
  println "| bower install..."
  def proc = bowerInstall.execute()
  proc.waitFor()
  if (proc.exitValue() != 0) {
    println "Error installing bower dependencies"
    println "Output: ${proc.in.text}"
  }
}

private void executeGruntTasks() {
  def gruntScript = System.properties['os.name'].toLowerCase().contains('windows') ?
      "cmd.exe /C node_modules\\.bin\\grunt" :
      "node_modules/.bin/grunt"
  println "| Load js dependencies from cache..."
  def proc = gruntScript.execute()  // execute default task to load dependencies from local cache.
  proc.waitFor()
  if (proc.exitValue() != 0) {
    println "| Error occured while loading dependencies from local cache : ${proc.err.text}"
    println "| Try loading dependencies from web..."
    proc = gruntScript.execute()
    proc.waitFor()                               // Wait for the command to finish
    println "Output: ${proc.in.text}"
  }
}

