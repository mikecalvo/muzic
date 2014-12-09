//import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.DesiredCapabilities

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

//driver = {
//  def driver = new org.openqa.selenium.htmlunit.HtmlUnitDriver()
//  driver.javascriptEnabled = true
//  driver
//}


//driver = {
//  new PhantomJSDriver(new DesiredCapabilities())
//}

private void downloadDriver(File file, String path) {
    if (!file.exists()) {
        def ant = new AntBuilder()
        ant.get(src: path, dest: 'driver.zip')
        ant.unzip(src: 'driver.zip', dest: file.parent)
        ant.delete(file: 'driver.zip')
        ant.chmod(file: file, perm: '700')
    }
}

private WebDriver setupChromeDriver() {
    def chromeDriver = new File('target/drivers/chrome/chromedriver')
    downloadDriver(chromeDriver, "http://chromedriver.storage.googleapis.com/2.10/chromedriver_mac32.zip")
    System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)
}

waiting {
    timeout = 10
    retryInterval = 0.5
}

driver = {
    def driver = new ChromeDriver()
    driver.manage().window().maximize()
    return driver
}


