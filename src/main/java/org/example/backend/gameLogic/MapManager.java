package org.example.backend.gameLogic;

import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Wall;

import java.util.ArrayList;

public class MapManager {
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();
    public Player player;
    private final UnitFactory unitFactory = new UnitFactory();

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls(){return walls;}

    public MapManager(Player player){
        this.player = player;
    }



    public void addGameObject(char object, Position position){
        switch (object){
            case '@'->
                player.set_position(position);
            case '#'->walls.add(new Wall(position));
            case '.'->{}
            default-> {
                Enemy enemy = unitFactory.get_enemy(object);
                enemy.set_position(position);
                enemies.add(enemy);
            }
        }
    }
}
