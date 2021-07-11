import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        Keyboard keyboard = new VkKeyboard().getKeyboard();
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();

        Ping ping = new Ping();
        new Thread(ping, "ping").start();

        ConfigReader configReader = new ConfigReader();
        GroupActor actor = new GroupActor(configReader.getId(), configReader.getToken());
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();

        while (true) {

            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if (!messages.isEmpty()) {
                messages.forEach(message -> {
                    System.out.println(message.toString());
                    try {
                        switch (message.getText()) {
                            case "Привет":
                                vk.messages().send(actor).message("Привет! Я бот этого сообщества." +
                                        " Готов ответить на все твои вопросы!").userId(message.getFromId())
                                        .randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                                break;
                            case "Где вы находитесь?":
                                vk.messages().send(actor).message("Мы находимся по адресу ул. Авангардная д.12" +
                                        "\nВход с торца здания").userId(message.getFromId())
                                        .randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                                break;
                            case "Расписание тренировок":
                                vk.messages().send(actor).message("Взрослая группа (14+) ПН СР ПТ 19:00" +
                                        "\nМладшая группа (6+) ПН СР ПТ 17:30").userId(message.getFromId())
                                        .randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                                break;
                            case "Другой вопрос":
                                vk.messages().send(actor).message("Наш менеджер ответит на любой Ваш вопрос. Телефон: " +
                                        "8-963-481-21-06").userId(message.getFromId())
                                        .randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                                break;
                            case "Что взять на первую тренировку?":
                                vk.messages().send(actor).message("Шорты, футболка, кроссовки - для первого раза" +
                                        " этого вполне достаточно! Кстати, первая тренировка бесплатно!").
                                        userId(message.getFromId()).randomId(random.nextInt(10000)).
                                        keyboard(keyboard).execute();
                                break;
                            default:
                                vk.messages().send(actor).message("Нажимай на кнопки, чтобы получить ответы" +
                                        " на самые частые вопросы!").userId(message.getFromId())
                                        .randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                                break;
                        }
                    } catch (ApiException | ClientException e) {
                        e.printStackTrace();
                    }
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }

}
