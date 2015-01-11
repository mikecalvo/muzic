package muzic

class Profile {

  String email
  String password
  String status
  Date dateCreated

  static constraints = {
    email nullable: false, email: true, unique: true
    password nullable: false, size: 6..12
    status nullable: true, size: 0..64
  }
}
