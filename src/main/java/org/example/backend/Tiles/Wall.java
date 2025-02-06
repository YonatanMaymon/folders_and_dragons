package org.example.backend.Tiles;

import org.example.backend.gameLogic.Position;

public class Wall extends Tile{
    public Wall(Position position) {
        super(position, '#');
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_wall(this);
    }
}
