package org.example.backend.gameLogic;

import org.example.backend.gameLogic.visitors.Interact;
import org.example.data_records.BattleData;
import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.PlayerTypes.PlayerStatExtractor;
import org.example.backend.Tiles.Tile;
import org.example.backend.Tiles.Unit;
import org.example.backend.Tiles.Wall;
import org.example.backend.gameLogic.visitors.UnitMovement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class LevelMap extends MapManager {
    UnitMovement unitMovement = new UnitMovement(this);

    private Path level_dir = get_level_dir();
    public TileMap tileMap;
    int num_col=0;
    int num_row=0;
    public boolean levelPlaying = true;
    public Consumer<BattleData> onCombat;
    public Consumer<String> onDeath;
    public Consumer<Map<String,Integer>> onMapAndStatsUpdate;
    private final PlayerStatExtractor playerStatExtractor = new PlayerStatExtractor();

    public LevelMap(int level, Consumer<BattleData> onCombat, Consumer<String> onDeath, Consumer<Map<String,Integer>> onMapAndStatsUpdate) throws IOException {
        level_dir = Paths.get(level_dir.toString(),"level"+level+ ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(level_dir.toFile()));
        String line;
        while ((line = reader.readLine()) != null){
            num_col = Math.max(num_col, line.length());
            num_row++;
        }
        tileMap = new TileMap(num_row,num_col);
        this.onCombat = onCombat;
        this.onDeath = onDeath;
        this.onMapAndStatsUpdate = onMapAndStatsUpdate;
    }

    public void loudMap(Player player) throws IOException {
        this.player = player;
        BufferedReader reader = new BufferedReader(new FileReader(level_dir.toFile()));
        String line;
        int j = 0;
        while ((line = reader.readLine()) != null){
            for(int i =0 ; i < line.length(); i++){
                if(line.charAt(i) !='.')
                    addGameObject(line.charAt(i),new Position(i,j));
            }
            j++;
        }
        reader.close();
        player.setEnemies(getEnemies());
        for (Wall wall : getWalls()){tileMap.loud_tile_on_map(wall);}
        for (Enemy enemy : getEnemies()){tileMap.loud_tile_on_map(enemy);}
        tileMap.loud_tile_on_map(player);
    }

    public void update(Character input){
        onMapAndStatsUpdate.accept(player.accept(playerStatExtractor));
        player.update();
        Interact interact = new Interact(this, player);
        Position new_pos = player.get_next_position(input);
        tileMap.getTile(new_pos).accept(interact);

        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy enemy: enemies){
            if (!enemy.isAlive){
                onDeath.accept(enemy.get_name());
                player.gain_xp(enemy.exp_value);
                enemiesToRemove.add(enemy);
            }
            enemy.update();
            enemy.accept(unitMovement);
        }
        for(Enemy enemy: enemiesToRemove){
            Position position = enemy.get_position();
            tileMap.map[position.get_y()][position.get_x()] = new Tile(position,'.');
            enemies.remove(enemy);
        }
        if (enemies.isEmpty()){
            levelPlaying = false;
        }
    }

    public void change_positions(Unit unit, Position position){
        Position prev_position = unit.get_position();
        Tile pervTile = tileMap.map[position.get_y()][position.get_x()];
        tileMap.map[position.get_y()][position.get_x()] = unit;
        tileMap.map[prev_position.get_y()][prev_position.get_x()] = pervTile;
        unit.set_position(position);
        pervTile.set_position(prev_position);
    }
}
