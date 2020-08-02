package domain.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageHandler implements Handler {

    @Override
    public void handle(Update update, SendMessage response) {
        String text = update.getMessage().getText();
        boolean isNonNull = text != null;

        if (update.hasMessage() && isNonNull) {
            Handler messageHandler;
            boolean isCommand = text.startsWith("/");

            if (isCommand) {
                messageHandler = new CommandHandler();
            } else {
                messageHandler = new TextHandler();
            }

            messageHandler.handle(update, response);
        }
    }
}
