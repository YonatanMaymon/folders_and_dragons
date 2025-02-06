package org.example.backend.gameLogic.visitors;

import org.example.backend.Tiles.*;
import org.example.backend.Tiles.Enemies.Boss;
import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Enemies.Monster;
import org.example.backend.Tiles.Enemies.Trap;
import org.example.backend.gameLogic.LevelMap;

public class InteractWithEnemy implements Visitor {
    LevelMap map;
    Enemy enemy;
    public InteractWithEnemy(LevelMap map, Enemy enemy){
        this.map=map;
        this.enemy = enemy;
    }
    @Override
    public void visit_player(Player player) {
        map.onCombat.accept(enemy.on_attack(player));
        if (!enemy.isAlive) {
            map.change_positions(player, enemy.get_position());
        }
    }
    @Override
    public void visit_monster(Monster monster) {}
    @Override
    public void visit_trap(Trap trap) {}
    @Override
    public void visit_tile(Tile tile) {}
    @Override
    public void visit_wall(Wall wall) {}
    @Override
    public void visit_boss(Boss boss) {}
}
