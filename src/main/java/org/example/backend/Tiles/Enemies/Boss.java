package org.example.backend.Tiles.Enemies;

import org.example.backend.Tiles.HeroicUnit;
import org.example.backend.Tiles.Player;
import org.example.backend.Tiles.Visitor;
import org.example.backend.Util;
import org.example.backend.gameLogic.Position;
import org.example.enums.DIRECTION;

public class Boss extends Enemy implements HeroicUnit {
    private final int visionRange;
    private final int abilityFrequency;
    private int combatTicks;

    private Player player = null;
    public Boss( char tile, String name, int healthPool, int attackPoints, int defencePoints, int visionRange, int abilityFrequency, int expValue) {
        super(tile,name, healthPool, attackPoints, defencePoints,expValue);
        this.visionRange = visionRange;
        this.abilityFrequency = abilityFrequency;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit_boss(this);
    }

    @Override
    public void cast_ability() {
        if (player == null) return;
        combatTicks = 0;
        on_ability_cast();
    }


    @Override
    public void on_ability_cast() {
        int damage = get_attackPoints() - Util.roll(player.get_defencePoints());
        player.on_boss_ability_cast(damage);
    }

    @Override
    public Position get_next_position(Player player) {
        this.player = player;
        DIRECTION direction = get_direction();
        if (direction == DIRECTION.CAST_ABILITY)
            cast_ability();
        return get_next_position(get_direction());
    }

    DIRECTION get_direction(){
        if (EnemyUtil.is_player_in_range(this,player,visionRange)) {
            if (combatTicks >= abilityFrequency)
                return DIRECTION.CAST_ABILITY;
            combatTicks ++;
            return EnemyUtil.get_direction_based_on_player(this, player);
        }
        else return Util.get_random_direction();
    }

}
