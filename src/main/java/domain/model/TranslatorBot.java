package domain.model;

import domain.handlers.Handler;
import domain.handlers.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TranslatorBot extends TelegramLongPollingBot {
    private String username;
    private String token;
    private static TranslatorBot translatorBot;
    private final ExecutorService executorService = Executors.newFixedThreadPool(8);

    private TranslatorBot() {
        setProperties();
    }

    public static TranslatorBot getInstance() {
        if (translatorBot == null) {
            translatorBot = new TranslatorBot();
        }
        return translatorBot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        executorService.submit(() -> {
            Handler handler = new MessageHandler();
            SendMessage response = new SendMessage();

            handler.handle(update, response);
            sendMessage(response);
        });
    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    private void setProperties() {
        try (InputStream in = new FileInputStream("src/main/resources/botinfo.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            username = prop.getProperty("username");
            token = prop.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
