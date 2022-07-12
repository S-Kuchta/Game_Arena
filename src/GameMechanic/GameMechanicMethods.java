package GameMechanic;
public class GameMechanicMethods {

    private final String attackerName;
    private final String defenderName;

    public GameMechanicMethods(String attackerName, String defenderName) {
        this.attackerName = attackerName;
        this.defenderName = defenderName;
    }

    /**
     *
     * @param attackerManaOrEnergyDrain;
     * @param defenderManaOrEnergy;
     * @param whichStat;
     *
     * @return max mana or energy or attackerManaOrEnergyDrain based on entry defender mana or energy amount.
     */
    public int energyAndManaDrain(int attackerManaOrEnergyDrain, int defenderManaOrEnergy, String whichStat) {
        if (defenderManaOrEnergy < attackerManaOrEnergyDrain) {
            System.out.print(this.attackerName + " drain " + defenderManaOrEnergy + " " + whichStat + " from " + this.defenderName);
            System.out.println("\t\t" + this.defenderName + " " + whichStat + " is 0");
            return defenderManaOrEnergy;
        } else {
            System.out.print(this.attackerName + " drain " + attackerManaOrEnergyDrain + " " + whichStat + " from " + this.defenderName);
            System.out.println("\t\t" + this.defenderName + " " + whichStat + " is " + (defenderManaOrEnergy - attackerManaOrEnergyDrain) + ".");
            return attackerManaOrEnergyDrain;
        }
    }

    /**
     *
     * @param attackerMaxHealth;
     * @param attackerHealth;
     * @param lifeStealAmount;
     *
     * @return lifeStealAmount. If attacker missing health are less than lifeStealAmount than return amount of missing health.
     */
    public int healthAndManaRestore(int attackerMaxHealth, int attackerHealth, int lifeStealAmount, String whichStat) {
        if (attackerMaxHealth - attackerHealth < lifeStealAmount) {
            System.out.println(this.attackerName + " restore " + (attackerMaxHealth - attackerHealth) + " " + whichStat + ".");
            return attackerMaxHealth - attackerHealth;
        } else if(lifeStealAmount == 0) {
            System.out.print("");
            return 0;
        } else {
            System.out.println(this.attackerName + " restore " + lifeStealAmount + " " + whichStat + ".");
            return lifeStealAmount;
        }
    }

    /**
     *
     * @param attackerCanCastDotStacks;
     * @param attackerDamageOverTime;
     * @param attackerTotalDamageOverTime;
     * @param attackerStacksCount;
     *
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
                if(damageOrHealOverTime[1] == 5) {
                    damageOrHealOverTime[1] = 4;
                }
                damageOrHealOverTime[0] = damageOrHealOverTime[1] * attackerDamageOverTime;
            }
        }
        return damageOrHealOverTime;
    }

    public int canCastDot() {
        return 0;
    }


}
