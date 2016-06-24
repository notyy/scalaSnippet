package webtest

import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{ShouldMatchers, FunSpec}
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.Chrome

class BaiduSpec extends FunSpec with Chrome with Eventually with ShouldMatchers{
  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(5, Seconds)), interval = scaled(Span(100, Millis)))

  describe("baidu"){
    it("should be able to search google") {
      go to "http://baidu.com"
      click on "kw"
      textField("kw").value = "google"
      submit()
      eventually {
        pageTitle should be("google_百度搜索")
      }
//      close()
    }
  }
}
