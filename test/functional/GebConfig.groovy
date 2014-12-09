import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

//driver = {
//  def driver = new org.openqa.selenium.htmlunit.HtmlUnitDriver()
//  driver.javascriptEnabled = true
//  driver
//}


driver = {
  new PhantomJSDriver(new DesiredCapabilities())
}
