package Heroes;

public class Warlock extends Heroes{
    public Warlock(String name) {
        super(name, 510, 2, 27, 3, 100, 7, 330, 22, 12);
    }


    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Normal Attack", "Shadow Bolt", "Chaos Bolt", "Unstable Affliction", "Demon Armor", "Skip"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter 1 = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ", Energy cost: 12.");
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 1:
                    System.out.println("Enter 2 = " + getAbilityName()[i] + ". Damage: " + (10 + super.getSpellDamage()) + "-" + (16 + super.getSpellDamage()) + ", Mana cost: 45.");
                    System.out.println("\t\t\tBase spell attack.");
                    break;
                case 2:
                    System.out.println("Enter 3 = " + getAbilityName()[i] + ". Damage: " + (13 + super.getSpellDamage()) + "-" + (22 + super.getSpellDamage()) + ", Mana cost: 65." );
                    System.out.println("\t\t\tIf " + getAbilityName()[3] + " have 4 stacks, " + getAbilityName()[i] + " deal triple damage and drain 25% healths from damage dealt but consume all " + getAbilityName()[3] + " stacks. ");
                    break;
                case 3:
                    System.out.println("Enter 4 = " + getAbilityName()[i] + ". Damage over time: 13, Mana cost: 35.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds. Ability can be stacked 4 times. Ability drain 10% healths from damage dealt.");
                    break;
                case 4:
                    System.out.println("Enter 5 = " + getAbilityName()[i] + " Absorb damage bonus: 30.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " increase absorb damage by 30. Can be stacked. Last until is not destroyed. When shield is destroyed, burn 30 attacker mana.");
                    break;
                case 5:
                    System.out.println("Enter 6 = " + getAbilityName()[i] + " round.");
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
        super.setEnergyCost(0);
        super.setManaCost(45);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 13);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getSpellDamage());
        super.setLifeSteal(false);
        if(super.getDotStacksCount() == 4) {
            super.setTotalDamage((super.getDamageFromAttackType() + super.getSpellDamage()) * 3);
            super.setLifeSteal(true);
            super.setTotalDamageOverTime(0);
            super.setDotStacksCount(0);
            super.setCountDotTick(4);
        }
        super.setEnergyCost(0);
        super.setManaCost(65);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setDotLifeSteal(true);
        super.setCanCastDot(true);
        super.setCanCastDotStacks(true);
        super.setManaCost(45);
        super.setEnergyCost(0);
        super.setDamageOverTime(13);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(40);
        super.setEnergyCost(0);
        super.setAbsorbDamageBonusIncrease(30);
    }

    public int manaDrainFromShield(int attackerMana, String attackerName,String defenderName) {
        int manaBurnAfterDestroyShield = 25;
        if (attackerMana > manaBurnAfterDestroyShield) {
            System.out.println(attackerName + " Destroy " + getAbilityName()[4] + ". " + defenderName + " burn  " + manaBurnAfterDestroyShield + " mana from " + attackerName + ".");
            return manaBurnAfterDestroyShield;
        } else {
            System.out.println(attackerName + " Destroy " + getAbilityName()[4] + ". " + defenderName + " burn " + attackerMana + " mana from " + attackerName + ".");
            return attackerMana;
        }
    }
}
