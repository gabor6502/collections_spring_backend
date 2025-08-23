package com.api.collections.services.exceptions;

public class NoCreatorsSuppliedException extends Exception
{
    public NoCreatorsSuppliedException()
    {
        super("No creators were supplied with the item.");
    }
}
