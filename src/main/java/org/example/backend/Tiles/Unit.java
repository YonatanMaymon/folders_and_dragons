package org.example.backend.Tiles;

import org.example.backend.Util;
import org.example.backend.gameLogic.resources.Health;
import org.example.backend.gameLogic.Position;
import org.example.data_records.BattleData;
import org.example.enums.DIRECTION;

public class Unit extends Tile{
    public boolean isAlive = true;
    public Health health;
    private int _attackPoints;
    private int _defencePoints;

    public Unit(String name, char tile, int healthPool, int attackPoints, int defencePoints) {
        super( tile);
        this._name =name;
        this.health = new Health(healthPool);
        this._attackPoints = attackPoints;
        this._defencePoints = defencePoints;
    }

    public void update(){}

    public Position get_next_position(DIRECTION direction){
        Position nextPosition = new Position();
        nextPosition.setPosition( _position);
        switch (direction) {
            case DIRECTION.LEFT->
                nextPosition.move(-1,0);
            case DIRECTION.RIGHT->
                nextPosition.move(1,0);
            case DIRECTION.DOWN->
                nextPosition.move(0,1);
            case DIRECTION.UP->
                nextPosition.move(0,-1);
        }
        return nextPosition;
    }


    void on_death(){
        isAlive = false;
    }

    public int take_damage(int damage){
        int damageTaken = Math.max(damage,0);
        health.take_damage(damageTaken);
        if (health.get_resource_amount() <= 0){on_death();}
        return damageTaken;
    }

    public BattleData on_attack(Unit attacker){
        int attackRoll = Util.roll(attacker.get_attackPoints());
        int defenceRoll = Util.roll(get_defencePoints());
        int damageTaken = Math.max(0,attackRoll-defenceRoll);
        take_damage(damageTaken);
        return new BattleData
                (attacker.get_name(),get_name(),attackRoll,defenceRoll, health.get_resource_amount());
    }

    public int get_attackPoints() {
        return _attackPoints;
    }

    public void add_attackPoints(int amount) {
        this._attackPoints += amount;
    }

    public int get_defencePoints() {
        return _defencePoints;
    }

    public void add_defencePoints(int amount) {
        this._defencePoints += amount;
    }

}
