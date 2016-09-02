package com.github.aklimashevsky;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageTest {

    @Test
    public void testGeters() throws Exception {
        Language language = new Language(1L, "a", "b", "c", "d");
        assertEquals(1L, language.getFacebookId());
        assertEquals("a", language.getIso3Code());
        assertEquals("b", language.getIso2Code());
        assertEquals("c", language.getIso1Code());
        assertEquals("d", language.getName());
    }
}