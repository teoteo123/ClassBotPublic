import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.LocalDateTime;
import java.util.*;

public class ClassJoin {
    private WebDriver driver;
    private final String teamsEmailBox = "//*[@id=\"i0116\"]";
    private String studentID;
    private String studentPass;
    private final String oneLoginInput = "//*[@id=\"identification\"]";
    private final int teamsClassOrder;
    private ArrayList<String> teamsCardXpaths = new ArrayList<>();

    public ClassJoin(int teamsClassOrder, CredentialSet c) throws InterruptedException, AWTException {

        this.studentID = c.getID();
        this.studentPass = c.getPassword();
        this.teamsClassOrder = teamsClassOrder;

        //setting up xpaths of teams class cards in order 1 - 7

        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[2]/div[2]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[2]/div[3]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[2]/div[4]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[2]/div[5]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[3]/div[2]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[3]/div[3]/div/ng-include/div");
        teamsCardXpaths.add("//*[@id=\"favorite-teams-panel\"]/div/div[1]/div[3]/div[4]/div/ng-include/div");

        System.setProperty("webdriver.chrome.driver", "src/chromedriver");
        driver  = new ChromeDriver();

        System.out.println(logIntoTeams());
        enterClass();
        clickJoin();
        leaveClass();
        System.out.println("[PROCESS 2] Left Class.  Waiting for next period");

    }

    private String logIntoTeams() {
        driver.get("http://teams.microsoft.com");
        while (!driver.getCurrentUrl().contains("https://login.microsoftonline.com/common")) {
            System.out.println("[WAITING] for new page");
        }
        driver.findElement(By.xpath(teamsEmailBox)).sendKeys(studentID + "@apsva.us\n");

        System.out.println("[REACHED] APS OneLogin");
        while (!driver.getCurrentUrl().contains("myaccess.apsva.us/idp/AuthnEngine#/authn")) {
            System.out.println("[WAITING] for new page");
        }
        driver.findElement(By.xpath(oneLoginInput)).sendKeys(studentID + "\t" + studentPass + "\n");
        System.out.println("[REACHED] ");

        while (!driver.getCurrentUrl().contains("https://login.microsoftonline.com/login.srf")) {
            System.out.println("[WAITING] for new page");
        }
        driver.findElement(By.xpath("//*[@id=\"idBtn_Back\"]")).click();

        while (!driver.getCurrentUrl().contains("teams.microsoft.com/_#/school//?ctx=teamsGrid")) {
            System.out.println("[WAITING] for new page");
        }
        System.out.println("[REACHED] teams grid");
        return "[PROCESS 1] Teams successfully logged in";
    }
    private void enterClass () throws InterruptedException {
        WebElement classCard = driver.findElement(By.xpath(teamsCardXpaths.get(teamsClassOrder)));
        classCard.click();
        System.out.println("[REACHED] Teams class page");
        Thread.sleep(8000);
    }

    private void clickJoin() throws InterruptedException, AWTException {
        WebElement joinButton = driver.findElement(By.xpath("//*[@title=\"Join call with video\"]"));
        joinButton.click();
        Thread.sleep(5000);

        Robot r = new Robot();
        r.mouseMove(350,250);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(250);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2500);

        driver.findElement(By.xpath("//*[@id=\"ngdialog1\"]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div[1]/div/calling-pre-join-screen/div/div/div[2]/div[1]/div[2]/div/div/section/div[1]/div/div/button")).click();

        System.out.println("[DONE] Currently in class");

    }

    private void leaveClass() throws InterruptedException {
        for (int i = 0; i < 45; i++) {
            Thread.sleep(60000);
        }
        driver.quit();
        System.out.println("[" + LocalDateTime.now() + "] left class");
    }

}
