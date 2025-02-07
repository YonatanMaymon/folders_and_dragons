package org.example.backend.gameLogic;

import org.example.backend.Tiles.Tile;

import java.util.Arrays;

public class TileMap {
    Tile[][] map;
    TileMap(int x, int y){
        map = new Tile[x][y];
        for(int i = 0; i<map.length; i++){
            for (int j = 0; j< map[0].length;j++){
                map[i][j] = new Tile(new Position(j,i),'.');
            }
        }
    }

    public void loud_tile_on_map(Tile tile){
        int x = tile.get_position().get_x();
        int y = tile.get_position().get_y();
        map[y][x] = tile;
    }
    public Tile getTile(Position position){return map[position.get_y()][position.get_x()];}

    public String[][] get_names_map(){
        return Arrays.stream(map)
                .map(row -> Arrays.stream(row)
                        .map(tile -> tile != null ? tile.get_name() : "") // Handle null cases
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
