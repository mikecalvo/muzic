eventCleanEnd = {
  Ant.delete('dir':'target')
  Ant.delete('dir':'grails-app/assets/bower_components')
}

eventCompileStart = { kind ->
  executeGruntTasks()
}

private void executeGruntTasks() {
  def gruntScript = "node_modules/.bin/grunt"
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