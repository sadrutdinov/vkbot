import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ping implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                URL url = new URL("https://yandex.ru/");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                System.out.println(Thread.currentThread().getName() + " response code : " + con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
