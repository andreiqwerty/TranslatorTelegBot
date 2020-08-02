package domain.commands;

import dao.services.BotUserService;
import domain.model.BotUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

public class LangInfoCommand implements Command {
    private static final CommandType type = CommandType.LANGINFO;
    private BotUserService botUserService = new BotUserService();

    @Override
    public void execute(User user, String argument, SendMessage response){

        BotUser botUser = botUserService.findUser(user.getId());

        if (botUser == null) {
            botUser = new BotUser(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
            botUserService.saveUser(botUser);
        }
        BotUser currentUser = botUserService.findUser(user.getId());
        StringBuilder resp = new StringBuilder();
        resp.append("Текущий язык").append(" ")
            .append(currentUser.getLanguageCode()).append("\n")
            .append("Целевой язык").append(" ")
            .append(currentUser.getTranslationLang());
        response.setText(resp.toString());
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
