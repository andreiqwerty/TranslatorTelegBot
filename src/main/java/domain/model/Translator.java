package domain.model;

import java.io.IOException;

public interface Translator {
    String translate(String message, String lang) throws IOException;
}
