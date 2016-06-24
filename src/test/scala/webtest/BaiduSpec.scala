//package webtest
//
//import org.openqa.selenium.chrome.ChromeDriver
//import org.scalatest.time.{Millis, Seconds, Span}
//import org.scalatest.{FunSpec, ShouldMatchers}
//import org.scalatest.concurrent.Eventually
//import org.scalatest.selenium.Chrome
//
//class BaiduSpec extends FunSpec with Chrome with Eventually with ShouldMatchers{
//
//  System.setProperty("webdriver.chrome.driver", "/Users/twer/dev/chromedriver")
//  override implicit val webDriver = new ChromeDriver()
//
//  implicit override val patienceConfig =
//    PatienceConfig(timeout = scaled(Span(5, Seconds)), interval = scaled(Span(100, Millis)))
//
//  describe("baidu"){
//    it("should be able to search google") {
//      go to "http://baidu.com"
//      click on "kw"
//      textField("kw").value = "google"
//      submit()
//      eventually {
//        pageTitle should be("google_百度搜索")
//      }
////      close()
//    }
//  }
//}
