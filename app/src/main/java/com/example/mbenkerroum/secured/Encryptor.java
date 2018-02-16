package com.example.mbenkerroum.secured;


/**
 * Created by mbenkerroum on 09/02/2018.
 */

public interface Encryptor<T, V> {

    public V encrypt(T item);

    public T decrypt(V item);
}
