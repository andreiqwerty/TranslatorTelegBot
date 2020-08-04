package domain.handlers;

import dao.services.BotUserService;
import domain.model.BotUser;
import domain.model.Translator;
import domain.model.YandexTranslator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class TextHandler implements Handler {
    private Translator translator;
    private BotUserService userService = new BotUserService();

    public TextHandler() {
        translator = YandexTranslator.getInstance();
    }

    public TextHandler(Translator translator){
        this.translator = translator;
    }

    @Override
    public void handle(Update update, SendMessage response) {
        long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        response.setChatId(chatId).setParseMode("HTML");

        try {
            String translatedText = translator.translate(text, getTranslationLang(update));
            if (translatedText != null) response.setText(translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getTranslationLang(Update update) {
        BotUser botUser = userService.findUser(update.getMessage().getFrom().getId());
        return botUser.getTranslationLang();
    }
}
