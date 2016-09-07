package com.github.aklimashevsky;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LanguageMapper<K> {

    private Map<K, Language<K>> map = new Hashtable<>();

    public void loadFrom(InputStream inputStream) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
        String json = writer.toString();
        Gson gson = new Gson();
        ArrayList<Language<K>> languages =
                gson.fromJson(json, new TypeToken<ArrayList<Language<K>>>() {
                }.getType());
        initWith(languages);
    }

    public Locale getLanguage(long id) {

        Language<K> language = map.get(id);
        if (language == null) {
            return null;
        }
        if (language.getIso2Code() != null) {
            return new Locale(language.getIso2Code());
        } else {
            return new Locale(language.getIso3Code());
        }
    }

    public void initWith(Collection<Language<K>> languages) {
        map = new Hashtable<>();
        for (Language<K> language : languages) {
            map.put(language.getId(), language);
        }
    }

    public int getCount() {
        return map.size();
    }

    public boolean contains(long id) {
        return map.containsKey(id);
    }
}
