package domain.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {


    /**
     * That method receive a request and builds a response
     * @param update request
     * @param response response
     */
    void handle(Update update, SendMessage response);
}
