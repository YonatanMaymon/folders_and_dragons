package org.example;

import org.example.backend.Tiles.Player;
import org.example.backend.gameLogic.LevelMap;
import org.example.backend.gameLogic.UnitFactory;
import org.example.frontend.WindowManager;

import java.io.IOException;

public class GameManager {
    WindowManager windowManager = new WindowManager();

    LevelMap levelMap;
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
        try {
            levelMap = new LevelMap(1,
                    windowManager.enemyWindow::on_battle,
                    windowManager.enemyWindow::on_kill,
                    windowManager.playerWindow::print_stats
            );
            levelMap.loudMap(player);
        }catch (IOException e){
            System.err.println(e.getStackTrace());
        }
        windowManager.set_on_key_press(this::on_input);
        windowManager.update(levelMap.tileMap.get_names_map());
    }
    void on_input(char input){
        levelMap.update(input);
        windowManager.update(levelMap.tileMap.get_names_map());
    }
}
