package scripting

trait Service {
  def execute(arg1: String, arg2: String, dbConn: String, tableName: String): String
//  = {
//    s"SELECT * FROM $tableName WHERE arg1='$arg1' AND arg2='$arg2'"
//  }
}
