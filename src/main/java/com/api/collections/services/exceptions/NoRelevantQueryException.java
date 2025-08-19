package com.api.collections.services.exceptions;

public class NoRelevantQueryException extends Exception
{
    public NoRelevantQueryException(Class c)
    {
        super("A query relevant to the management of the "+c.getName()+" foreign entity has not been created.");
    }
}
