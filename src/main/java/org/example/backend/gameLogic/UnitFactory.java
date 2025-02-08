package org.example.backend.gameLogic;

import org.example.backend.Tiles.Enemies.Boss;
import org.example.backend.Tiles.Enemies.Enemy;
import org.example.backend.Tiles.Enemies.Monster;
import org.example.backend.Tiles.Enemies.Trap;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.PlayerTypes.Hunter;
import org.example.backend.Tiles.PlayerTypes.Mage;
import org.example.backend.Tiles.PlayerTypes.Rogue;
import org.example.backend.Tiles.PlayerTypes.Warrior;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UnitFactory {
    private final List<Supplier<Player>> playersList;
    private final Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selected;

    public Enemy get_enemy(char tile){return enemiesMap.get(tile).get();
    }
    public Player get_player(int num){return playersList.get(num).get();}

    public UnitFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                // Monsters
                () -> new Monster('g', "Goblin", 30, 5, 2, 3, 15),
                () -> new Monster('o', "Ogre", 120, 12, 6, 2, 40),
                () -> new Monster('d', "Specter", 50, 7, 3, 5, 30),
                () -> new Boss('D', "Dreadlord Varkas", 300, 22, 15, 5, 3, 100),
                () -> new Boss('A', "Ancient War Titan", 500, 35, 20, 4, 4, 150),

                // Traps
                () -> new Trap('S', "Spike Pit", 50, 12, 2, 25, 5, 10),
                () -> new Trap('B', "Blade Swing", 40, 15, 3, 25, 4, 8),
                () -> new Trap('F', "Flame Jet", 60, 20, 5, 40, 6, 12)

        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().get_tile(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Hunter("Shadowfang", 40, 12, 3, 6),
                () -> new Mage("Arcanis the Wise", 80, 12, 3, 100, 40, 3, 20, 4),
                () -> new Rogue("Silent Dagger", 90, 20, 7, 20),
                () -> new Warrior("Ironclad Brutus", 200, 30, 10, 4)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }


}
