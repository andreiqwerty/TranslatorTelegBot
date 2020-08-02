package domain.handlers;

import domain.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler implements Handler {
    private static Map<String, Command> availableCommands;
    private Command currentCommand;

    static {
        initCommands();
    }

    @Override
    public void handle(Update update, SendMessage response) {
        Message message = update.getMessage();
        long chatId = message.getChatId();
        String text = message.getText();

        response.setChatId(chatId).setParseMode("HTML");

        try {
            currentCommand = availableCommands.get(defineCommand(text));
            currentCommand.execute(message.getFrom(), null, response);
        } catch (Exception e) {
            return;
        }
    }

    private String defineCommand(String message) throws Exception {
        String command = message.split(" ")[0].substring(1);
        if (availableCommands.containsKey(command)) {
            return command;
        }
        throw new Exception("The command '" + message + "' not found");
    }

    private static void initCommands(){
        availableCommands = new HashMap<>();
        availableCommands.put("start", new StartCommand());
        availableCommands.put("help", new HelpCommand());
        availableCommands.put("langinfo", new LangInfoCommand());
        availableCommands.put("swaplang", new SwapCommand());
    }
}
