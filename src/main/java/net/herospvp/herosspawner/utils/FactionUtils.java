package net.herospvp.herosspawner.utils;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.struct.Role;

public class FactionUtils {

    public static boolean isMod(FPlayer fPlayer) {
        Role role = fPlayer.getRole();
        return role.value >= 2;
    }

}
