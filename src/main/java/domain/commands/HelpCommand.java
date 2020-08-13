package domain.commands;

import dao.services.BotUserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

public class HelpCommand implements Command {
    private CommandType type = CommandType.HELP;
    private BotUserService botUserService = new BotUserService();

    @Override
    public void execute(User user, String argument, SendMessage response) {
        response.setText(buildResponse());
    }

    public String buildResponse(){
        StringBuilder resp = new StringBuilder();
        resp.append("<b>").append("Available commands").append(":</b>\n");
        for (CommandType type : CommandType.values()) {
            String command = type.toString().toLowerCase();
            if (command.equalsIgnoreCase("stat")) continue;
            resp.append(String.format("/%s - %s%n", command, type.getDescription()));
        }
        return resp.toString();
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
