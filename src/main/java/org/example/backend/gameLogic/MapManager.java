package org.example.backend.gameLogic;

import org.example.Main;
import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Wall;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MapManager {
    public static Path get_level_dir() {
        return Paths.get(Main.get_main_path().toString(), "levels_dir");
    }

    protected ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Wall> walls = new ArrayList<>();
    public Player player;
    private final UnitFactory unitFactory = new UnitFactory();

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public ArrayList<Wall> getWalls(){return walls;}

    public void setPlayer(Player player) {
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
