package org.example.backend.Tiles;

import org.example.backend.gameLogic.Position;

public class Tile {
    private final char _tile;
    public static final String DEFAULT_NAME = "floor";
    protected String _name = DEFAULT_NAME;
    Position _position;
    public Tile( char tile){
        this._tile = tile;
    }
    public Tile(Position position, char tile){
        this._position = position;
        this._tile = tile;
    }
    public void accept(Visitor visitor){
        visitor.visit_tile(this);
    }

    public Character get_tile() {
        return _tile;
    }

    public Position get_position() {
        return _position;
    }

    public void set_position(Position _position) {
        this._position = _position;
    }
    public String get_name() {return _name;}
    public void set_name(String name){_name =name;}

}
