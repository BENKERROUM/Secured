package com.example.mbenkerroum.secured;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        App.get().getDB().productDao().insert(new Password("eee"));

        List<Password> products = App.get().getDB().productDao().getAll();
        assertEquals(products.get(0), "eee");

    }
}