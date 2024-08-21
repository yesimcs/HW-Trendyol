import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class LoginTest {

    @Test
    public void LoginTestChrome() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize(); //tam ekran olmasını sağlıyor.

        chromeDriver.get("https://www.trendyol.com/"); //HW 1. MADDE Go to https://www.trendyol.com/

        String currentUrl = chromeDriver.getCurrentUrl();
        if (currentUrl.equals("https://www.trendyol.com/")) {
            System.out.println("Ana sayfa başarılı bir şekilde açıldı.");
        } else {
            System.out.println("Ana sayfa açılmadı.");
        }
        //HW 2. MADDE Check that the main page is opened.
        //2.madde için daha kesin bir kontrol yolu var mı? yoksa Bu yöntem iyi mi?

        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        try {
            WebElement cerezKabulEtButonu = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            cerezKabulEtButonu.click();

            WebElement girisYap = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[.='Giriş Yap']")));
            girisYap.click();

        } catch (TimeoutException e) {
            System.out.println("Öğe tıklanabilir hale gelmedi.");
        }
        //çerezler giriş yap butonuna tıklanmasına engel oluyordu. çerezleri tıklayıp kapattık.

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try { //GİRİŞ BİLGİLERİNİ TRY-CATCH İÇİNE ALIYORUZ. YANLIŞ GİRİŞTE BİZE BİLGİ VERSİN DİYE..
            WebElement epostaAdresi = chromeDriver.findElement(By.id("login-email"));
            epostaAdresi.sendKeys("testdeneme2468@gmail.com");

            WebElement sifre = chromeDriver.findElement(By.id("login-password-input"));
            sifre.sendKeys("ABCdef"); //yanlış şifre bilgisi girdik.
            //HW-3.MADDE:Attempts are made to log in to the site with incorrect information.

            WebElement girisYapButonu = chromeDriver.findElement(By.xpath("//button[@class='q-primary q-fluid q-button-medium q-button submit']/span[.='Giriş Yap']"));
            girisYapButonu.click();

        } catch (Exception e) {
            System.out.println("Giriş işlemi sırasında bir hata oluştu: " + e.getMessage());
            chromeDriver.quit();
            return;
        }
        //şifre yanlış olmasına rağmen hata mesajı dönmüyor..????????

        //WebElement aramaKutusu =chromeDriver.findElement(By.cssSelector("[placeholder='laptop']")); //BU ŞEKİLDE HATA VERİYOR? BUNU ÇÖZEMEDİM HENÜZ.
        WebElement aramaKutusu = chromeDriver.findElement(By.xpath("//input[@class='V8wbcUhU']"));

        aramaKutusu.sendKeys("laptop");
        aramaKutusu.sendKeys(Keys.RETURN); // Enter tuşuna bas
        //HW-4.MADDE . The word "laptop" is entered in the search box.

        List<WebElement> urunler = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.p-card-chldrn-cntnr")));


            // Rastgele ürün seçimi
            Random random = new Random();
            int index = random.nextInt(urunler.size());
            WebElement randomUrun = urunler.get(index);
            randomUrun.click();

        //HW- 5. MADDE: According to the result, a random product is selected from the products.


        //WebElement anladimButonu = wait.until(ExpectedConditions.elementToBeClickable(By.className("onboarding-button")));
        //anladimButonu.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


       // WebElement sepetEkleButonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'add-to-basket')]")));
        //sepetEkleButonu.click();

        //WebElement sepetEkleButonu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //div[@class='add-to-basket-button-text']")));








        //ekran 5 saniye açık kalsın, hemen kapanmasın diye bekleme süresi ekledim.

        //chromeDriver.quit(); //ekranı kapatmak için kullanıyoruz.


    /* @Test
    public void LoginTestFirefox() {
        System.setProperty("webdriver.geckodriver.driver", "src/main/resources/geckodriver.exe");

        WebDriver fireFoxDriver = new FirefoxDriver();
        fireFoxDriver.manage().window().maximize();
        fireFoxDriver.get("https://www.trendyol.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fireFoxDriver.quit();

    } */

    }
}