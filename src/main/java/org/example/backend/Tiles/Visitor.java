package org.example.backend.Tiles;

import org.example.backend.Tiles.Enemies.Boss;
import org.example.backend.Tiles.Enemies.Monster;
import org.example.backend.Tiles.Enemies.Trap;

public interface Visitor{
    void visit_monster(Monster monster);
    void visit_trap(Trap trap);
    void visit_player(Player player);
    void visit_tile(Tile tile);
    void visit_wall(Wall wall);
    void visit_boss(Boss boss);
}
