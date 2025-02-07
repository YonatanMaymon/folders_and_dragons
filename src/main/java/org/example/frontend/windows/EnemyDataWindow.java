package org.example.frontend.windows;

import org.example.data_records.AbilityUseData;
import org.example.data_records.BattleData;

public class EnemyDataWindow extends Window{
    public EnemyDataWindow(String title, int width, int height, int x, int y) {
        super(title, width, height, x, y, true);
    }
    public void on_battle(BattleData bd){
        text.add_dashed_message(bd.attacker() + " is attacking "+bd.defender());
        text.add_message(bd.attacker() + " has rolled " + bd.attackRoll());
        text.add_message(bd.defender() + " has rolled "+ bd.defenceRoll());
        text.add_message(bd.defender() + " has taken " + bd.damage_taken()+" damage");
        text.add_message(bd.defender() + " remaining health: " + bd.remainingHealth());
        text.add_dividing_line();
    }
    public void on_kill(String name){
        text.add_message(name + " has been slayed");
        text.add_dividing_line();
    }
    public void print_ability_use_log(AbilityUseData data){
        text.add_dashed_message(data.name() + "has been casted");
        if (data.damageMap().isEmpty())
            text.add_message("no enemies in range");
        for(String name: data.damageMap().keySet()){
            text.add_message(name+" got hit for " + data.damageMap().get(name));
        }
        text.add_dividing_line();
    }
}
