package domain.handlers;

import domain.model.Translator;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class TextHandlerTest {

    @Mock
    Translator translator;
    @Mock
    Update update;
    @Mock
    Message message;
    TextHandler textHandler;

    @Before
    public void before() throws IOException {
        when(translator.translate("Hello", "ru")).thenReturn("Привет");
        when(message.getText()).thenReturn("Hello");
        when(update.getMessage()).thenReturn(message);
        textHandler = spy(new TextHandler(translator));
    }

    @Test
    public void test1(){
        doReturn("ru").when(textHandler).getTranslationLang(anyObject());
        SendMessage response = new SendMessage();
        textHandler.handle(update, response);
        Assert.assertEquals(response.getText(), "Привет");
    }
}
