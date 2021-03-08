import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Pinger {

    private static CredentialSet loginInfo;

    public Pinger(CredentialSet c) throws AWTException, InterruptedException {
        System.out.println("Pinger Started");
        loginInfo = c;
        mainProcess();
    }

    private void mainProcess() throws InterruptedException, AWTException {

        while (LocalDateTime.now().getYear() == 2021) {
            while (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.TUESDAY) ||
                    LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                tuesThurs();
                Thread.sleep(50000);
            }
            while (LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.WEDNESDAY) ||
                    LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                wedFri();
                Thread.sleep(50000);
            }
            while (!(LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.TUESDAY) ||
                    LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.WEDNESDAY) ||
                    LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.THURSDAY) ||
                    LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.FRIDAY))) {
                System.out.println("[" + LocalDateTime.now().getDayOfWeek() + "] not a school day yet");
                Thread.sleep(50000);
            }
            Thread.sleep(50000);
        }
    }

    public void tuesThurs() throws AWTException, InterruptedException {

        System.out.println("[TUE/THU]");

        if (LocalDateTime.now().toString().contains("08:19:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(0, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
        if (LocalDateTime.now().toString().contains("10:00:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(1, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
        if (LocalDateTime.now().toString().contains("11:50:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(3, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
        if (LocalDateTime.now().toString().contains("13:30:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(5, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
    }
    public void wedFri() throws AWTException, InterruptedException {

        System.out.println("[WED/FRI]");

        if (LocalDateTime.now().toString().contains("10:00:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(1, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
        if (LocalDateTime.now().toString().contains("11:50:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(2, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
        if (LocalDateTime.now().toString().contains("13:30:")) {
            System.out.println("[" + LocalDateTime.now() + "] starting process");
            new ClassJoin(4, loginInfo);
            System.out.println("[" + LocalDateTime.now() + "] ended process");
        }
    }

}
