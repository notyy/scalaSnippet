package cakePatternSimple

import org.scalatest.FunSpec

class UserServiceTest extends FunSpec {
  describe("UserService"){
    it("can find all users"){
//      val userService = new DefaultUserService with UserRepositoryImpl with DefaultTransactionManager
      val userService = new UserServiceImpl with UserRepositoryImpl with TransactionManagerImpl
      userService.findAll
    }
  }
}
