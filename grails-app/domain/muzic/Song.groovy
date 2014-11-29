package muzic

class Song {

  String title
  String lyrics
  Artist artist
  Long releaseYear

  static constraints = {
    title(blank: false, maxSize: 64)
    artist(nullable: false)
    lyrics(maxSize: 1024)
  }
}
