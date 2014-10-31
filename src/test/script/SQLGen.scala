import scripting.Service

class SQLGen extends Service {
  override def execute(arg1: String, arg2: String, dbConn: String, tableName: String): String = {
    s"test yyy SELECT * FROM $tableName WHERE arg1='$arg1' AND arg2='$arg2'"
  }
}
