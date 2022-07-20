package Heroes;

public class Paladin extends Heroes {
    public Paladin(String name) {
        super(name, 820, 8, 17,12,0,0,330,14,5);
    }


    private final int[] manaCost = {0,25,55,40,40};
    @Override
    public void attackTypeText(int finalWeaponDamage, boolean computerPlay) {
        super.setAbilityName(new String[]{"Normal Attack", "Divine Storm", "Holy Shock", "Flash Heal", "Holy Shield"});
        super.setDamageOverTimeAbilityName("Consecration","");
        super.attackTypeText(finalWeaponDamage, computerPlay);
        if (!computerPlay) {
            for (int i = 0; i < getAbilityName().length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i] + ". Damage: " + colorRed + (4 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (8 + damage + finalWeaponDamage) + colorReset + ".");
                        System.out.println("\t\t\tNormal weapon attack.");
                        break;
                    case 1:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (10 + damage + finalWeaponDamage + (this.spellDamage / 2)) + colorReset + "-" + colorRed +(16 + damage + finalWeaponDamage + (spellDamage / 2)) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\tStrong attack.");
                        break;
                    case 2:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (22 + spellDamage + finalWeaponDamage) + colorReset + "-" + colorRed + (31 + spellDamage + finalWeaponDamage) + colorReset + ", Damage over time: " + colorRed + (spellDamage / 2) + colorReset
                                + ", Mana Cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal holy damage. " + getDamageOverTimeAbilityName()[0] + " additional deal damage over time, damage will be dealt for 4 rounds.");
                        break;
                    case 3:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". No damage dealt. Heal: " + colorCyan + spellDamage + colorReset + ", Mana Cost: "
                                + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability restore Paladin Health. Also Paladin cleanse self from 2 stacks damage over time.");
                        break;
                    case 4:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Absorb damage bonus: " + colorCyan +  ((spellDamage * 2) + damage) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " increase absorb damage. Can be stacked.");
                        break;

                }
            }
        }
    }

    @Override
    protected void normalAttack() {
        super.normalAttack();
        super.setWeaponAttack(true);
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setWeaponAttack(true);
        super.setTotalDamage(super.getDamageFromAttackType() + (this.spellDamage / 2) + damage);
        super.setManaCost(manaCost[1]);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        super.setWeaponAttack(true);
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 22);
        super.setTotalDamage(super.getDamageFromAttackType() + spellDamage);
        super.setDamageOverTime((spellDamage/2),super.getDamageOverTime()[1]);
        super.setCanCastDot(true,false,false);
        super.setManaCost(manaCost[2]);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setWeaponAttack(false);
        super.setLifeSteal(false);
        super.setManaCost(manaCost[3]);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(manaCost[4]);
        super.absorbDamageIncrease((spellDamage * 2) + damage);
    }

    public int[] healAndCleanse(int attackerHealth, int defenderTotalDamage, int defenderStacks) {
        int[] paladinHealAndStacks = new int[3];
        paladinHealAndStacks[0] = attackerHealth;
        if(defenderStacks != 0) {
            paladinHealAndStacks[1] = defenderTotalDamage / defenderStacks;
            paladinHealAndStacks[2] = defenderStacks - 1;
        } else {
            paladinHealAndStacks[1] = defenderTotalDamage;
        }

            if (super.getMaxHealth() - attackerHealth > super.getSpellDamage()) {
                paladinHealAndStacks[0] = spellDamage * 2;
            } else {
                paladinHealAndStacks[0] = super.getMaxHealth() - attackerHealth;
            }
            return paladinHealAndStacks;
    }

    @Override
    protected int computerHeroAutoAttack() {
        int abilityCasted;
        if(super.getMana() < 60) {
            abilityCasted = super.attackChanceCalculator(100,1,0,0,0,0,0,0);
        } else if(super.getHealth() > (super.getMaxHealth() / 2)) {
            if(super.getCountDotTick()[0] >= 3 || super.getCountDotTick()[0] == 0) {
                abilityCasted = super.attackChanceCalculator(80,3,10,2,5,1,5,5);
            } else {
                abilityCasted = super.attackChanceCalculator(60,2,30,1,10,3,0,0);
            }
            if(super.getDefenderDotStacks()[0] > 2 || super.getDefenderDotStacks()[1] > 2) {
                abilityCasted = super.attackChanceCalculator(50,4,30,2,15,3,5,1);
            }
        } else  {
            abilityCasted = super.attackChanceCalculator(35,2,15,3,20,5,30,4);
        }
        return abilityCasted;
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
