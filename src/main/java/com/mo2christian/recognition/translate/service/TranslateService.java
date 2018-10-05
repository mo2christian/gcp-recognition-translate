package com.mo2christian.recognition.translate.service;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service de traduction.
 */
@Service
public class TranslateService {

    private final Logger logger = Logger.getLogger("translate");

    /**
     * Traduit un texte d'une langue vers une autre.
     * @param from langue source
     * @param target langue de destination
     * @param texts liste de testes à traduire
     * @return
     */
    public List<String> translate(String from, String target, List<String> texts){
        logger.info("Translating text " + texts + " from " + from + " to " + target);
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        List<Translation> translations = translate.translate(texts,
                Translate.TranslateOption.sourceLanguage(from),
                Translate.TranslateOption.targetLanguage(target));
        List<String> responses = new LinkedList<>();
        translations.forEach(t -> responses.add(t.getTranslatedText()));
        return responses;
    }

    /**
     * Detecte la langue du texte.
     * @param text le texte
     * @return la langue correspondante
     */
    public String detectLanguage(String text){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Detection detection = translate.detect(text);
        return detection.getLanguage();
    }

}
