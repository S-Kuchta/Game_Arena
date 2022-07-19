package Heroes;

public class Warlock extends Heroes{
    public Warlock(String name) {
        super(name, 510, 2, 32, 3, 0, 0, 410, 22, 7);
    }


    private final int[] manaCost = {0,0,45,65,45,25};


    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Skip", "Normal Attack", "Shadow Bolt", "Chaos Bolt", "Unstable Affliction", "Demon Armor"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + " round.");
                    break;
                case 1:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ".");
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 2:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (10 + super.getSpellDamage()) + "-" + (16 + super.getSpellDamage()) + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\tBase spell attack.");
                    break;
                case 3:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (13 + super.getSpellDamage()) + "-" + (22 + super.getSpellDamage()) + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\tIf " + getAbilityName()[3] + " have 4 stacks, " + getAbilityName()[i] + " deal triple damage and drain 25% healths from damage dealt but consume all " + getAbilityName()[3] + " stacks. ");
                    break;
                case 4:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage over time: " + (spellDamage / 2) + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds. Ability can be stacked 4 times. Ability drain 10% healths from damage dealt.");
                    break;
                case 5:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + " Absorb damage bonus: " + spellDamage + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " increase absorb damage. Can be stacked. Last until is not destroyed. When shield is destroyed, burn attacker mana equal of absorb shield.");
                    break;
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
        super.setManaCost(manaCost[2]);
        super.setCanCastDot(isCanCastDot()[0],true,isCanCastDot()[2]);
        super.setDamageOverTime(super.getDamageOverTime()[0],15);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 13);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getSpellDamage());
        super.setLifeSteal(false);
        if(super.getDotStacksCount()[0] == 4) {
            super.setTotalDamage((super.getDamageFromAttackType() + super.getSpellDamage()) * 3);
            super.setLifeSteal(true);
            super.setTotalDamageOverTime(0,getTotalDamageOverTime()[1]);
            super.setDotStacksCount(0,getDotStacksCount()[1]);
            super.setCountDotTick(4,getCountDotTick()[1],getCountDotTick()[2]);
        }
        super.setManaCost(manaCost[3]);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setDotLifeSteal(true);
        super.setCanCastDot(true,false,false);
        super.setCanCastDotStacks(true);
        super.setManaCost(manaCost[4]);
        super.setDamageOverTime((spellDamage / 2),super.getDamageOverTime()[1]);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(manaCost[5]);
        this.setAbsorbDamageBonusIncrease(spellDamage);
    }

    public int manaDrainFromShield(int attackerMana, String attackerName,String defenderName) {
        int manaBurnAfterDestroyShield = spellDamage;
        if (attackerMana > manaBurnAfterDestroyShield) {
            System.out.println(attackerName + " Destroy " + getAbilityName()[4] + ". " + defenderName + " burn  " + manaBurnAfterDestroyShield + " mana from " + attackerName + ".");
            return manaBurnAfterDestroyShield;
        } else {
            System.out.println(attackerName + " Destroy " + getAbilityName()[4] + ". " + defenderName + " burn " + attackerMana + " mana from " + attackerName + ".");
            return attackerMana;
        }
    }
}
