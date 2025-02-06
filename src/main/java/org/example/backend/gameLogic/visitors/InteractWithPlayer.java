package org.example.backend.gameLogic.visitors;

import org.example.backend.Tiles.Enemies.Boss;
import org.example.backend.Tiles.Enemies.Monster;
import org.example.backend.Tiles.Enemies.Trap;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Tile;
import org.example.backend.Tiles.Visitor;
import org.example.backend.Tiles.Wall;
import org.example.backend.gameLogic.LevelMap;

public class InteractWithPlayer implements Visitor {
    Player player;
    LevelMap map;
    public InteractWithPlayer(LevelMap map, Player player){
        this.map = map;
        this.player = player;
    }

    @Override
    public void visit_monster(Monster monster) {
        map.onCombat.accept(player.on_attack(monster));
    }

    @Override
    public void visit_trap(Trap trap) {
        map.onCombat.accept(player.on_attack(trap));
    }

    @Override
    public void visit_boss(Boss boss) {
        map.onCombat.accept(player.on_attack(boss));
    }

    @Override
    public void visit_player(Player player) {}
    @Override
    public void visit_tile(Tile tile) {}
    @Override
    public void visit_wall(Wall wall) {}
}
