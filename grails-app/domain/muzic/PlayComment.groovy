package muzic

class PlayComment {

  String text
  User user

  static belongsTo = [play: Play]

}
