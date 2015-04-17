import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities

driver = {
  def path = System.properties['os.name'].toLowerCase().contains('windows') ?
      'node_modules\\.bin\\chromedriver.exe' : './node_modules/.bin/chromedriver'
  System.setProperty("webdriver.chrome.driver", path);
  new ChromeDriver(new DesiredCapabilities())
}
