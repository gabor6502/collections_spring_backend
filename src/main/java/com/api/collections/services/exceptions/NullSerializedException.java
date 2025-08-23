package com.api.collections.services.exceptions;

public class NullSerializedException extends Exception
{
    public NullSerializedException()
    {
        super("Serialized object was null.");
    }
}
