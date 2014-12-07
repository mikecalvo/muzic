package muzic

class Play {

  Date timestamp

  static belongsTo = [artist: Artist]
  static hasMany = [comments: PlayComment]
}
