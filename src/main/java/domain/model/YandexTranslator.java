package domain.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

public class YandexTranslator implements Translator {
    private static final Logger logger = LoggerFactory.getLogger(YandexTranslator.class);
    private String url;
    private String key;
    private static Translator translator;

    private YandexTranslator() {
        setProperties();
    }

    public static Translator getInstance() {
        if (translator == null) {
            translator = new YandexTranslator();
            logger.info("Translator instance created and can help you ");
        }
        return translator;
    }

    @Override
    public String translate(String message, String lang) throws IOException {
        logger.debug("Starting translating text: {}. Translation lang: {}", message, lang);
        URL urlObj = new URL(url + "translate?key=" + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes("text=" + URLEncoder.encode(message, "UTF-8") + "&lang=" + lang);
        InputStream response = connection.getInputStream();
        String json = new Scanner(response).nextLine();
        String text = getTextFromJson(json);
        out.close();
        response.close();
        logger.debug("Text successfully translated. Translated text: {}", text);
        return text;
    }

    private String getTextFromJson(String json) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);
        return message.getFormattedText();
    }

    private void setProperties() {
        try (InputStream in = new FileInputStream("src/main/resources/translator.properties")) {
            Properties prop = new Properties();
            prop.load(in);
            url = prop.getProperty("url");
            key = prop.getProperty("key");
        } catch (IOException e) {
            logger.error("An error occurred while loading translator properties", e);
        }
        logger.info("Translator properties set successfully");
    }
}
