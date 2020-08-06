import domain.model.TranslatorBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        try {
            logger.info("Start bot");
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            botsApi.registerBot(TranslatorBot.getInstance());
        } catch (TelegramApiRequestException e) {
            logger.error("Telegram API doesn't work", e);
        }
    }
}
