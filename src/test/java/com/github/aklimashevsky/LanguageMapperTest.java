package com.github.aklimashevsky;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.*;

public class LanguageMapperTest {

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

        Collection<Language<Integer>> languages = new ArrayList<>();
        languages.add(new Language<>(1, "a"));
        languages.add(new Language<>(2, "b"));


        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();

        languageMapper.initWith(languages);

        assertEquals(languages.size(), languageMapper.getCount());
        assertTrue(languageMapper.contains(1));
        assertTrue(languageMapper.contains(2));
    }

    @Test
    public void testNotInit() throws Exception {
        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();
        assertEquals(0, languageMapper.getCount());
        assertFalse(languageMapper.contains(42));
    }

    @Test
    public void testLoadFrom() throws Exception {

        String json = "[]";

        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();
        languageMapper.loadFrom(stream);
        assertEquals(languageMapper.getCount(), 0);
    }

    @Test
    public void testGetLocaleISO2() throws Exception {

        Language<Integer> language = new Language<>(1, "aar", "aar", "ar", "Afar");

        Collection<Language<Integer>> languages = new ArrayList<>();
        languages.add(language);

        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();
        languageMapper.initWith(languages);


        Locale locale = languageMapper.getLanguage(1);
        assertNotNull(locale);
        assertEquals("aar", locale.getLanguage());
        assertEquals("Afar", locale.getDisplayLanguage());
    }

    @Test
    public void testGetNullLocale() throws Exception {
        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();

        Locale locale = languageMapper.getLanguage(1);

        assertNull(locale);
    }

    //apk				I	L	Kiowa Apache
    @Test
    public void testGetLocaleISO3Only() throws Exception {

        Language<Integer> language = new Language<>(1, "apk", null, null, "Kiowa Apache");

        Collection<Language<Integer>> languages = new ArrayList<>();
        languages.add(language);

        LanguageMapper<Integer> languageMapper = new LanguageMapper<>();
        languageMapper.initWith(languages);

        Locale locale = languageMapper.getLanguage(1);
        assertNotNull(locale);
        assertEquals("apk", locale.getLanguage());
        assertEquals("apk", locale.getDisplayLanguage());
    }
}