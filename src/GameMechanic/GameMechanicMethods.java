package GameMechanic;
public class GameMechanicMethods {

    private final String attackerName;
    private final String defenderName;

    public GameMechanicMethods(String attackerName, String defenderName) {
        this.attackerName = attackerName;
        this.defenderName = defenderName;
    }

    /**
     * @param attackerManaOrEnergyDrain;
     * @param defenderManaOrEnergy;
     * @param whichStat;
     *
     * @return max mana or energy or attackerManaOrEnergyDrain based on entry defender mana or energy amount.
     */
    public int energyAndManaDrain(int attackerManaOrEnergyDrain, int defenderManaOrEnergy, String whichStat) {
        if(defenderManaOrEnergy == 0) {
            return 0;
        } else if(attackerManaOrEnergyDrain != 0) {
            if (defenderManaOrEnergy < attackerManaOrEnergyDrain) {
                System.out.print(this.attackerName + " drain " + defenderManaOrEnergy + " " + whichStat + " from " + this.defenderName);
                System.out.println("\t\t" + this.defenderName + " " + whichStat + " is 0");
                return defenderManaOrEnergy;
            } else {
                System.out.print(this.attackerName + " drain " + attackerManaOrEnergyDrain + " " + whichStat + " from " + this.defenderName);
                System.out.println("\t\t" + this.defenderName + " " + whichStat + " is " + (defenderManaOrEnergy - attackerManaOrEnergyDrain) + ".");
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
     *
     * @return lifeOrManaStealAmount. If attacker missing health are less than lifeOrManaStealAmount than return amount of missing health.
     */
    public int healthAndManaRestore(int attackerMaxHealthOrMana, int attackerHealthOrMana, int lifeOrManaStealAmount, String whichStat) {
        if (attackerMaxHealthOrMana - attackerHealthOrMana < lifeOrManaStealAmount) {
            System.out.println(this.attackerName + " restore " + (attackerMaxHealthOrMana - attackerHealthOrMana) + " " + whichStat + ".");
            return attackerMaxHealthOrMana - attackerHealthOrMana;
        } else if(lifeOrManaStealAmount == 0) {
            System.out.print("");
            return 0;
        } else {
            System.out.println(this.attackerName + " restore " + lifeOrManaStealAmount + " " + whichStat + ".");
            return lifeOrManaStealAmount;
        }
    }

    /**
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
        System.out.println(attackerName + " deal " + damageOrHealOverTime[0] + " damage to " + defenderName + " with damage over time ability.");
        return damageOrHealOverTime;
    }

    /**
     * @param totalDamage;
     * @param totalAbsorb;
     *
     * @return damage after absorb or 0 base on defender absorb damage.
     */
    public int damageDeal(int totalDamage, int totalAbsorb, boolean isCriticalHit) {
        int totalDamageAfterAbsorb = totalDamage - totalAbsorb;
        if (totalDamage > totalAbsorb) {
            System.out.print(attackerName + " deal: " + totalDamage + " damage.\t\t");
            System.out.print(defenderName + " absorb " + totalAbsorb + " damage.\t\t");
            System.out.print(attackerName + " deal " + (totalDamage - totalAbsorb) + " damage after absorbing damage by " + defenderName + "\n");
        } else {
            System.out.print(attackerName + " deal " + totalDamage + " damage.\t\t");
            System.out.print(defenderName + " absorb " + totalDamage + " damage.\t\t");
            System.out.print(attackerName + " deal 0 damage after absorbing damage by " + defenderName + "\n");
            totalDamageAfterAbsorb = 0;
        }
        if(isCriticalHit) {
            System.out.println("Critical Hit!");
        }
        return totalDamageAfterAbsorb;
    }

    public int absorbDamageBonus(int absorbDamageBonus, int totalDamage, int totalAbsorb) {
        if (absorbDamageBonus <= totalDamage) {
            return 0;
        } else {
            return totalAbsorb - totalDamage;
        }
    }

    public int absorbDamageBonusIncrease(int absorbDamageBonusIncrease) {
        int maxShieldDamageBonus = 70;
        if(maxShieldDamageBonus - absorbDamageBonusIncrease <= absorbDamageBonusIncrease) {
            return maxShieldDamageBonus - absorbDamageBonusIncrease;
        } else {
            return absorbDamageBonusIncrease;
        }
    }

}
