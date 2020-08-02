package domain.commands;

import dao.services.BotUserService;
import domain.model.BotUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.StringJoiner;

public class StartCommand implements Command {
    private static final CommandType type = CommandType.START;
    private BotUserService botUserService = new BotUserService();

    @Override
    public void execute(User user, String argument, SendMessage response){
        BotUser botUser = botUserService.findUser(user.getId());

        if (botUser == null) {
            botUser = new BotUser(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
            botUserService.saveUser(botUser);
        }

        StringBuilder resp = new StringBuilder();
        resp.append("Привет")
                .append(botUser.getFirstName())
                .append((botUser.getLastName() != null ? botUser.getLastName() : "\b") + "!")
                .append("R2-D2 готов служить и крушить империю с его блестящим другом");
        response.setText(resp.toString());
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
