package org.example.backend.Tiles;

import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.PlayerTypes.PlayersVisitor;
import org.example.backend.Util;
import org.example.backend.gameLogic.Position;
import org.example.data_records.AbilityUseData;
import org.example.enums.DIRECTION;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

public class Player extends Unit implements HeroicUnit{
    int _xp;
    int _lvl;
    protected Runnable onDeath;
    protected Consumer<AbilityUseData> onAbilityUse = (AbilityUseData)->{};
    protected ArrayList<Enemy> enemies = new ArrayList<>();

    public Player(String name, int healthPool, int attackPoints, int defencePoints) {
        super(name, '@', healthPool, attackPoints, defencePoints);
        this._lvl = 1;
        this._xp = 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_player(this);
    }
    public Map<String,Integer> accept(PlayersVisitor visitor){return null;}

    public void set_on_death(Runnable onDeath) {
        this.onDeath = onDeath;
    }

    @Override
    void on_death() {
        super.on_death();
        onDeath.run();
    }

    protected ArrayList<Enemy> getHitList(int range){
        ArrayList<Enemy> hitList= new ArrayList<>();
        for (Enemy enemy : enemies){
            if(get_position().distance_from(enemy.get_position())<=range && enemy.isAlive){
                hitList.add(enemy);
            }
        }
        return hitList;
    }

    protected Enemy get_random_enemy(ArrayList<Enemy> hitList){
        Random random = new Random();
        return hitList.get(random.nextInt(0,hitList.size()));
    }

    public void setOnAbilityUse(Consumer<AbilityUseData> onAbilityUse) {
        this.onAbilityUse = onAbilityUse;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    public void on_boss_ability_cast(int damage){
        damage = take_damage(damage);
        HashMap <String,Integer> damageMap = new HashMap<>();
        damageMap.put(get_name(),damage);
        onAbilityUse.accept(new AbilityUseData("Boss Ability", damageMap));
    }

    public Position get_next_position(char input){
        DIRECTION direction = Util.input_interpreter(input);
        if (direction == null) return null;
        else if(direction == DIRECTION.CAST_ABILITY) cast_ability();
        return get_next_position(direction);
    }
    public void on_ability_cast(){}
    public boolean has_resources_for_ability(){
        return false;
    }
    public void cast_ability(){
        if (has_resources_for_ability()){
            on_ability_cast();
            return;
        }
//        throw new InsufficientResourcesException();
    }

    int get_xp_threshold(){return 50 * this._lvl;}

    public void gain_xp(int amount){
        this._xp += amount;
        int xp_threshold = get_xp_threshold();
        if (this._xp >=xp_threshold){
            this._xp -= xp_threshold;
            lvl_up();
        }
    }
    void lvl_up(){
        this._lvl ++;
        on_lvl_up();
    }
    public void on_lvl_up(){
        this.health.increase_health_pool(10*this._lvl);
        this.add_attackPoints(4*this._lvl);
        this.add_defencePoints( this._lvl);
    }

    public int get_lvl() {
        return _lvl;
    }

    public int get_xp_progress_percentage() {return 100 - (get_xp_threshold() - _xp) *100 /get_xp_threshold();}
}
