package domain.commands;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class HelpCommandTest {

    @Ignore
    @Test
    public void testGelp(){
        String exp = "<b>Available commands:</b>\n" +
                "/start - Start now!\r\n" +
                "/help - List of commands\r\n" +
                "/langinfo - Get lang settings\r\n" +
                "/swaplang - Change the lang\r\n";
        HelpCommand helpCommand = new HelpCommand();
        Assert.assertEquals(exp, helpCommand.buildResponse());
    }
}
