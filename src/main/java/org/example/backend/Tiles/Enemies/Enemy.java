package org.example.backend.Tiles.Enemies;

import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Unit;
import org.example.backend.gameLogic.Position;

public abstract class Enemy extends Unit {
    public final int exp_value;
    public Enemy(char tile ,String name, int healthPool, int attackPoints, int defencePoints, int exp_value) {
        super(name, tile, healthPool, attackPoints, defencePoints);
        this.exp_value = exp_value;
    }
    public abstract Position get_next_position(Player player);
}
