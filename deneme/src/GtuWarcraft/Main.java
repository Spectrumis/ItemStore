package GtuWarcraft;

import GtuWarcraft.Players.ElfMage;
import GtuWarcraft.Players.ElfWarrior;

public class Main {
    public static void main(String args[]) {
        ElfMage sami = new ElfMage();
        ElfWarrior sinan = new ElfWarrior();
        Arena gtu = new Arena(sami,sinan);
    }

}
