package muzic

class Song {

  String title
  String lyrics
  Long releaseYear

  static belongsTo = [artist: Artist]

  static constraints = {
    lyrics nullable: true
    releaseYear nullable: true
  }
}
