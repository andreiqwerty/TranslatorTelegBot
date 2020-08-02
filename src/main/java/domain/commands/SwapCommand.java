package domain.commands;

import dao.services.BotUserService;
import domain.model.BotUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.StringJoiner;

public class SwapCommand implements Command{

    private static final CommandType type = CommandType.SWAPLANG;
    private BotUserService botUserService = new BotUserService();

    @Override
    public void execute(User user, String argument, SendMessage response){
        BotUser botUser = botUserService.findUser(user.getId());

        if (botUser == null) {
            botUser = new BotUser(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
            botUserService.saveUser(botUser);
        }

        BotUser currentUser = botUserService.findUser(user.getId());
        String fromLang = currentUser.getLanguageCode();
        String toLang = currentUser.getTranslationLang();

        currentUser.setLanguageCode(toLang);
        currentUser.setTranslationLang(fromLang);

        String resp = new StringJoiner(" ").add("Привет")
                .add(botUser.getFirstName())
                .add((botUser.getLastName() != null ? botUser.getLastName() : "\b") + "!")
                .add("Language has been swapped/ Язык перевода был изменен")
                .toString();
        response.setText(resp);
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
