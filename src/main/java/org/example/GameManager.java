package org.example;

import org.example.backend.Tiles.Player;
import org.example.backend.Util;
import org.example.backend.gameLogic.LevelMap;
import org.example.backend.gameLogic.UnitFactory;
import org.example.frontend.WindowManager;

import java.io.IOException;

public class GameManager {
    WindowManager windowManager = new WindowManager();
    LevelMap levelMap;
    private int level = 1;
    public GameManager(){
        UnitFactory unitFactory = new UnitFactory();
        windowManager.load_character_selection(
                num -> this.start_game(unitFactory.get_player(num)),
                unitFactory.listPlayers());
    }
    public void start_game(Player player){
        windowManager.load_windows();
        player.setOnAbilityUse(windowManager.enemyWindow::print_ability_use_log);
        player.set_on_death(windowManager::on_game_over);
        construct_new_map(player);
        windowManager.set_on_key_press(this::on_input);
        windowManager.update(levelMap.tileMap.get_names_map());
    }
    void on_input(char input){
        levelMap.update(input);
        if(levelMap.getEnemies().isEmpty()) {
            level++;
            if (level > Util.get_max_lvl())
                windowManager.on_game_over();
            construct_new_map(levelMap.player);
        }
        windowManager.update(levelMap.tileMap.get_names_map());
    }
    void construct_new_map(Player player){
        try {
            levelMap = new LevelMap(level,
                    windowManager.enemyWindow::on_battle,
                    windowManager.enemyWindow::on_kill,
                    windowManager.playerWindow::print_stats
            );
            levelMap.loudMap(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
