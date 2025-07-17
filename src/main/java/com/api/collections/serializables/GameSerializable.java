package com.api.collections.serializables;

import com.api.collections.entities.GameEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSerializable extends ItemSerializable
{
    private String medium;
    private String console;
    private int releaseYear;

    public GameSerializable(GameEntity game)
    {
        super(game);
        this.medium = enumValToString(game.getMedium());
        this.console = game.getConsole();
        this.releaseYear = game.getReleaseYear();
    }
    
    public GameSerializable(Long id, String name, String notes, byte[] image,
            String medium, String console, int releaseYear)
    {
        super(id, name, notes, image);
        this.medium = medium;
        this.console = console;
        this.releaseYear = releaseYear;
    }
    
}
