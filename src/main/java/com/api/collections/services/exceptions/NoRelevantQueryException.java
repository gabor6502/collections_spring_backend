package com.api.collections.services.exceptions;

public class NoRelevantQueryException extends Exception
{
    public NoRelevantQueryException(Class c)
    {
        super("A query relevant to the management of this "+c.getName()+" has not been created.");
    }
}
