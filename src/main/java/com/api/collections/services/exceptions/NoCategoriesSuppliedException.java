package com.api.collections.services.exceptions;

public class NoCategoriesSuppliedException extends Exception
{
    public NoCategoriesSuppliedException()
    {
        super("No creators were supplied with the item.");
    }
}
