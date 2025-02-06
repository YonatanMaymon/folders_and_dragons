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
                () -> new Monster('g', "Goblin", 30, 5, 2, 4, 15),
                () -> new Monster('o', "Ogre", 120, 12, 6, 6, 50),
                () -> new Monster('d', "Specter", 50, 7, 3, 10, 40),
                () -> new Boss('D', "Dreadlord Varkas", 300, 25, 15, 12, 3, 500),
                () -> new Boss('A', "Ancient War Titan", 500, 35, 20, 10, 4, 750),

                // Traps
                () -> new Trap('S', "Spike Pit", 50, 12, 2, 30, 5, 10),
                () -> new Trap('B', "Blade Swing", 40, 15, 3, 40, 4, 8),
                () -> new Trap('F', "Flame Jet", 60, 20, 5, 50, 6, 12)

        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().get_tile(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Hunter("Shadowfang", 100, 15, 5, 8),
                () -> new Mage("Arcanis the Wise", 80, 20, 3, 100, 10, 3, 25, 6),
                () -> new Rogue("Silent Dagger", 90, 18, 4, 5),
                () -> new Warrior("Ironclad Brutus", 120, 25, 10, 3)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }


}
