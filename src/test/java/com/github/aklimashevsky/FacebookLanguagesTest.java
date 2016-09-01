package com.github.aklimashevsky;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FacebookLanguagesTest {

    @org.junit.Before
    public void setUp() throws Exception {
        Locale locale = new Locale("aar");
        String test = locale.getDisplayLanguage();
        assertEquals("Afar", test);
    }

    @Test
    public void testLocale() {
        Locale locale = new Locale("aar");
        String test = locale.getDisplayLanguage();
        assertEquals("Afar", test);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitWith() throws Exception {

        Collection<Language> languages = new ArrayList<>();
        languages.add(new Language(1, "a"));
        languages.add(new Language(2, "b"));


        FacebookLanguages facebookLanguages = new FacebookLanguages();

        facebookLanguages.initWith(languages);

        assertEquals(languages.size(), facebookLanguages.getCount());
        assertTrue(facebookLanguages.contains(1));
        assertTrue(facebookLanguages.contains(2));
    }

    @Test
    public void testLoadFrom() throws Exception {

        String json = "[]";

        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        FacebookLanguages facebookLanguages = new FacebookLanguages();
        facebookLanguages.loadFrom(stream);
        assertEquals(facebookLanguages.getCount(), 0);
    }

    @Test
    public void testGetLocaleISO2() throws Exception {

        Language language = new Language(1, "aar", "aar", "ar", "Afar");

        Collection<Language> languages = new ArrayList<>();
        languages.add(language);

        FacebookLanguages facebookLanguages = new FacebookLanguages();
        facebookLanguages.initWith(languages);


        Locale locale = facebookLanguages.getLanguage(1);
        assertEquals("aar", locale.getLanguage());
        assertEquals("Afar", locale.getDisplayLanguage());
    }

    //apk				I	L	Kiowa Apache
    @Test
    public void testGetLocaleISO3Only() throws Exception {

        Language language = new Language(1, "apk", null, null, "Kiowa Apache");

        Collection<Language> languages = new ArrayList<>();
        languages.add(language);

        FacebookLanguages facebookLanguages = new FacebookLanguages();
        facebookLanguages.initWith(languages);

        Locale locale = facebookLanguages.getLanguage(1);
        assertEquals("apk", locale.getLanguage());
        assertEquals("apk", locale.getDisplayLanguage());
    }
}