package com.servicehub.servicehubapi.security; // Define o pacote da classe.

import org.apache.commons.text.StringEscapeUtils; // Importa ferramentas para escapar caracteres especiais.

public class TextSanitizer {

    // Impede que caracteres de um texto sejam interpretados como HTML.
    public static String sanitize(String text) {

        // Se o texto for nulo, retorna nulo.
        if (text == null) {
            return null;
        }

        // Escapa caracteres especiais para que sejam exibidos como texto.
        return StringEscapeUtils.escapeHtml4(text);
    }
}