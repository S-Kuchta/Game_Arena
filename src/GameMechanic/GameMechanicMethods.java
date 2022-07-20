package GameMechanic;

import Heroes.Heroes;

public class GameMechanicMethods {

    private final String attackerName;
    private final String defenderName;
    private final Heroes heroes = new Heroes();

    public GameMechanicMethods(String attackerName, String defenderName) {
        this.attackerName = attackerName;
        this.defenderName = defenderName;
    }


    /**
     * @param attackerManaOrEnergyDrain;
     * @param defenderManaOrEnergy;
     * @param whichStat;
     * @return max mana or energy or attackerManaOrEnergyDrain based on entry defender mana or energy amount.
     */
    public int energyAndManaDrain(int attackerManaOrEnergyDrain, int defenderManaOrEnergy, String whichStat) {
        if (defenderManaOrEnergy == 0) {
            return 0;
        } else if (attackerManaOrEnergyDrain != 0) {
            if (defenderManaOrEnergy < attackerManaOrEnergyDrain) {
                System.out.println("\t" + this.attackerName + " drain " + defenderManaOrEnergy + " " + whichStat + " from " + this.defenderName);
                return defenderManaOrEnergy;
            } else {
                System.out.println("\t" + this.attackerName + " drain " + heroes.colorBlue + attackerManaOrEnergyDrain + heroes.colorReset + " " + whichStat + " from " + this.defenderName + ".");
                return attackerManaOrEnergyDrain;
            }
        } else {
            return 0;
        }
    }

    /**
     * @param attackerMaxHealthOrMana;
     * @param attackerHealthOrMana;
     * @param lifeOrManaStealAmount;
     * @return lifeOrManaStealAmount. If attacker missing health are less than lifeOrManaStealAmount than return amount of missing health.
     */
    public int healthAndManaRestore(int attackerMaxHealthOrMana, int attackerHealthOrMana, int lifeOrManaStealAmount, String whichStat) {
        String color = "\u001B[0m";
        if(whichStat.equals("Health")) {
            color = "\033[0;32m";
        } else if (whichStat.equals("Mana")){
            color = "\u001B[34m";
        }

        if (attackerMaxHealthOrMana - attackerHealthOrMana < lifeOrManaStealAmount) {
            System.out.println("\t" + this.attackerName + " restore " + color + (attackerMaxHealthOrMana - attackerHealthOrMana) + heroes.colorReset + " " + whichStat + ".");
            return attackerMaxHealthOrMana - attackerHealthOrMana;
        } else if (lifeOrManaStealAmount == 0) {
            System.out.print("");
            return 0;
        } else {
            System.out.println("\t" + this.attackerName + " restore " + heroes.colorGreen + lifeOrManaStealAmount + heroes.colorReset + " " + whichStat + ".");
            return lifeOrManaStealAmount;
        }
    }

    /**
     * @param attackerCanCastDotStacks;
     * @param attackerDamageOverTime;
     * @param attackerTotalDamageOverTime;
     * @param attackerStacksCount;
     * @return attacker stacks on array position [1] and attacker total damage over time in array position [0].
     */
    public int[] damageOrHealOverTime(boolean attackerCanCastDotStacks, int attackerDamageOverTime, int attackerTotalDamageOverTime, int attackerStacksCount) {

        int[] damageOrHealOverTime = new int[2];
        damageOrHealOverTime[0] = attackerTotalDamageOverTime;
        damageOrHealOverTime[1] = attackerStacksCount;

        if (!attackerCanCastDotStacks) {
            if (attackerDamageOverTime != 0) {
                damageOrHealOverTime[0] = attackerDamageOverTime;
            }
        } else if (attackerStacksCount <= 4) {
            if (attackerDamageOverTime != 0) {
                damageOrHealOverTime[1] += 1;
                if (damageOrHealOverTime[1] == 5) {
                    damageOrHealOverTime[1] = 4;
                }
                damageOrHealOverTime[0] = damageOrHealOverTime[1] * attackerDamageOverTime;
            }
        }
        return damageOrHealOverTime;
    }

    public void damageOverTimeTextOutput(int damageDeal, String abilityName, boolean isCriticalHit) {
        if(isCriticalHit &&damageDeal != 0) {
            System.out.println("\t\t" + heroes.colorYellow + "Critical Hit with " + abilityName + heroes.colorReset + "!");
        }
        if(damageDeal != 0) {
            System.out.println("\t" + attackerName + " deal " + heroes.colorRed + damageDeal + heroes.colorReset + " damage to " + defenderName + " with " + abilityName + ".");
        }
    }

    /**
     * @param totalDamage;
     * @param totalAbsorb;
     * @return damage after absorb or 0 base on defender absorb damage.
     */
    public int damageDeal(int totalDamage, int totalAbsorb) {
        int totalDamageAfterAbsorb = totalDamage - totalAbsorb;
        if (totalDamage > totalAbsorb) {
            System.out.print("\t" + attackerName + " deal " + heroes.colorRedBright + totalDamage + heroes.colorReset + " damage.\t\t" +
                    defenderName + " absorb " + heroes.colorCyan + totalAbsorb + heroes.colorReset + " damage.\t\t" +
                    attackerName + " deal " + heroes.colorRedBright + (totalDamage - totalAbsorb) + heroes.colorReset + " damage after absorbing damage by " + defenderName + "\n");
        } else {
            System.out.print("\t" + attackerName + " deal " + heroes.colorRedBright + totalDamage + heroes.colorReset + " damage.\t\t" +
                    defenderName + " absorb " + heroes.colorCyan + totalDamage + heroes.colorReset + " damage.\t\t" +
                    attackerName + " deal " + heroes.colorRed + 0 + heroes.colorReset + " damage after absorbing damage by " + defenderName + "\n");
            totalDamageAfterAbsorb = 0;
        }
        return totalDamageAfterAbsorb;
    }

    public int absorbDamageBonus(int absorbDamageBonus, int totalDamage, int totalAbsorb) {
        if (absorbDamageBonus <= totalDamage) {
            return 0;
        } else if(totalDamage == 0) {
            return absorbDamageBonus;
        } else {
            return totalAbsorb - totalDamage;
        }
    }

}
