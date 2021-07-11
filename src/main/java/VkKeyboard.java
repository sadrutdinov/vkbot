import com.vk.api.sdk.objects.messages.*;

import java.util.ArrayList;
import java.util.List;


public class VkKeyboard {

    private final Keyboard keyboard = new Keyboard();

    public VkKeyboard() {
        List<KeyboardButton> line1 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Привет")
                .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Расписание тренировок")
                .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        List<KeyboardButton> line2 = new ArrayList<>();
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Где вы находитесь?")
                .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Что взять на первую тренировку?")
                .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        List<KeyboardButton> line3 = new ArrayList<>();
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Другой вопрос")
                .setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));


        List<List<KeyboardButton>> allKey = new ArrayList<>();
        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        keyboard.setButtons(allKey);

    }

    public Keyboard getKeyboard() {
        return keyboard;
    }


}
