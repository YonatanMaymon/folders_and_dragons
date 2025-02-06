package org.example.backend.gameLogic.visitors;

import org.example.backend.Tiles.*;
import org.example.backend.Tiles.Enemies.Boss;
import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Enemies.Monster;
import org.example.backend.Tiles.Enemies.Trap;
import org.example.backend.gameLogic.LevelMap;

public class Interact implements Visitor {
    LevelMap map;
    Unit interactor;
    public Interact(LevelMap map, Unit interactor){
        this.map=map;
        this.interactor = interactor;
    }

    void visit_enemy(Enemy enemy){
        interactor.accept(new InteractWithEnemy(map,enemy));
    }

    @Override
    public void visit_monster(Monster monster) {
        visit_enemy(monster);
    }

    @Override
    public void visit_trap(Trap trap) {
        visit_enemy(trap);
    }

    @Override
    public void visit_boss(Boss boss) {
        visit_enemy(boss);
    }

    @Override
    public void visit_player(Player player) {
        interactor.accept(new InteractWithPlayer(map,player));
    }

    @Override
    public void visit_tile(Tile tile) {
        map.change_positions(interactor,tile.get_position());
    }

    @Override
    public void visit_wall(Wall wall) {

    }

}
