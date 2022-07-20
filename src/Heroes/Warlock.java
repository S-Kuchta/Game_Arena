package Heroes;

public class Warlock extends Heroes {
    public Warlock(String name) {
        super(name, 610, 2, 32, 3, 0, 0, 410, 22, 7);
    }


    private final int[] manaCost = {0, 45, 65, 45, 35};

    @Override
    public void attackTypeText(int finalWeaponDamage, boolean computerPlay) {
        super.setAbilityName(new String[]{"Normal Attack", "Shadow Bolt", "Chaos Bolt", "Unstable Affliction", "Demon Armor"});
        super.setDamageOverTimeAbilityName("Unstable Affliction", "Course of Agony");
        super.attackTypeText(finalWeaponDamage, computerPlay);
        if (!computerPlay) {
            for (int i = 0; i < getAbilityName().length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Enter " + (i + 1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (4 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (8 + damage + finalWeaponDamage) + colorReset + ".");
                        System.out.println("\t\t\tNormal weapon attack.");
                        break;
                    case 1:
                        System.out.println("Enter " + (i + 1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (10 + spellDamage) + colorReset + "-" + colorRed + (16 + spellDamage) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\tBase spell attack.");
                        break;
                    case 2:
                        System.out.println("Enter " + (i + 1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (13 + spellDamage) + colorReset + "-" + colorRed + (22 + spellDamage) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\tIf " + getAbilityName()[3] + " have 4 stacks, " + getAbilityName()[i] + " deal triple damage and drain 25% healths from damage dealt but consume all " + getAbilityName()[3] + " stacks. ");
                        break;
                    case 3:
                        System.out.println("Enter " + (i + 1) + " = " + getAbilityName()[i]
                                + ". Damage over time: " + colorRed + (spellDamage / 2) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds. Ability can be stacked 4 times. Ability drain 10% healths from damage dealt.");
                        break;
                    case 4:
                        System.out.println("Enter " + (i + 1) + " = " + getAbilityName()[i]
                                + " Absorb damage bonus: " + colorCyan + (spellDamage * 2) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " increase absorb damage. Can be stacked. Last until is not destroyed. When shield is destroyed, burn attacker mana equal of absorb shield.");
                        break;
                }
            }
        }
    }

    @Override
    protected void normalAttack() {
        super.normalAttack();
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setTotalDamage(super.getDamageFromAttackType() + super.getSpellDamage());
        super.setWeaponAttack(false);
        super.setLifeSteal(false);
        super.setManaCost(manaCost[1]);
        super.setCanCastDot(isCanCastDot()[0], true, isCanCastDot()[2]);
        super.setDamageOverTime(super.getDamageOverTime()[0], super.getDamageOverTime()[1]);
        super.setCanCastDotStacks(false);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        int randomDamageChange = (int) (Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 13);
        super.setTotalDamage(super.getDamageFromAttackType() + spellDamage);
        super.setLifeSteal(false);
        if (super.getDotStacksCount()[0] == 4) {
            super.setTotalDamage((super.getDamageFromAttackType() + spellDamage) * 3);
            super.setLifeSteal(true);
            super.setTotalDamageOverTime(0, getTotalDamageOverTime()[1]);
            super.setDotStacksCount(0, getDotStacksCount()[1]);
            super.setCountDotTick(4, getCountDotTick()[1], getCountDotTick()[2]);
        }
        super.setManaCost(manaCost[2]);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setDotLifeSteal(true);
        super.setCanCastDot(true, false, false);
        super.setCanCastDotStacks(true);
        super.setManaCost(manaCost[3]);
        super.setDamageOverTime((spellDamage / 2), super.getDamageOverTime()[1]);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.absorbDamageIncrease(spellDamage * 2);
        super.setManaCost(manaCost[4]);
    }

    public int manaDrainFromShield(int attackerMana, String attackerName, String defenderName) {
        int manaBurnAfterDestroyShield = spellDamage;
        if (attackerMana > manaBurnAfterDestroyShield) {
            System.out.println(attackerName + "\tDestroy " + getAbilityName()[4] + ". " + defenderName + " burn  " + manaBurnAfterDestroyShield + " mana from " + attackerName + ".");
            return manaBurnAfterDestroyShield;
        } else {
            System.out.println(attackerName + "\tDestroy " + getAbilityName()[4] + ". " + defenderName + " burn " + attackerMana + " mana from " + attackerName + ".");
            return attackerMana;
        }
    }

    @Override
    protected int computerHeroAutoAttack() {
        int abilityCasted = 1;
        if (super.getMana() <= manaCost[1]) {
            abilityCasted = super.attackChanceCalculator(100, 1, 0, 0, 0, 0, 0, 0);
        } else {
            if (super.getHealth() > (super.getMaxHealth() / 2)) {
                if (super.getDotStacksCount()[0] == 4) {
                    abilityCasted = super.attackChanceCalculator(90, 3, 5, 2, 3, 5, 2, 1);
                }
                if (super.getDotStacksCount()[0] < 4) {
                    abilityCasted = super.attackChanceCalculator(95, 4, 4, 2, 1, 1, 0, 0);
                }
                if (super.getMana() > (super.getMaxMana() / 2) && super.getDotStacksCount()[0] == 4) {
                    abilityCasted = super.attackChanceCalculator(90, 2, 5, 1, 5, 5, 0, 0);
                }
                if (super.getDotStacksCount()[0] == 4 && super.getCountDotTick()[0] >= 3) {
                    abilityCasted = super.attackChanceCalculator(100, 3, 0, 0, 0, 0, 0, 0);
                }
            } else {
                abilityCasted = super.attackChanceCalculator(80, 2, 20, 5, 0, 0, 0, 0);
                if (super.getDotStacksCount()[0] == 4) {
                    abilityCasted = super.attackChanceCalculator(90, 3, 5, 2, 3, 5, 2, 1);
                }
                if (super.getDotStacksCount()[0] < 4) {
                    abilityCasted = super.attackChanceCalculator(90, 4, 9, 2, 1, 1, 0, 0);
                }
            }
        }
        return abilityCasted;
    }

}
