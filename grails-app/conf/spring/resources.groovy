import muzic.LyricExtractor

// Place your Spring DSL code here
beans = {
  lyricExtractor(LyricExtractor) {
    selectorsToSearchIn = ['body', '#start_lyrics']
  }
}
