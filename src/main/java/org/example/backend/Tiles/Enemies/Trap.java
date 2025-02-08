package org.example.backend.Tiles.Enemies;

import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Tile;
import org.example.backend.Tiles.Visitor;
import org.example.backend.gameLogic.Position;

public class Trap extends Enemy{
    private int _visibilityTime;
    private int _invisibilityTime;
    private int _tickCount;
    private boolean visible;
    private String originalName;
    public Trap(char tile,String name, int healthPool, int attackPoints, int defencePoints, int exp_value, int visibilityTime,int invisibilityTime) {
        super( tile, name,  healthPool, attackPoints, defencePoints, exp_value);
        _visibilityTime = visibilityTime;
        _invisibilityTime = invisibilityTime;
        originalName = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_trap(this);
    }

    @Override
    public void update() {
        visible = _tickCount<_visibilityTime;
        if(visible) set_name(originalName);
        else set_name(Tile.DEFAULT_NAME);
        if(_tickCount == _visibilityTime +_invisibilityTime)
            _tickCount =0;
        else _tickCount++;
    }

    @Override
    public String get_name() {
        return isAlive ? super.get_name() : originalName;
    }

    @Override
    public Position get_next_position(Player player) {return null;}
}
