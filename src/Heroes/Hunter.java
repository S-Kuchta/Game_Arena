package Heroes;

public class Hunter extends Heroes {
    public Hunter(String name) {
        super(name, 585, 16, 10,7,220,16,160,10,13);
    }

    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Normal Shot", "Aimed Shot", "Black Arrow", "Kill Shot", "Counterattack", "Skip"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter 1 = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ", Energy cost: 12");
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 1:
                    System.out.println("Enter 2 = " + getAbilityName()[i] + ". Damage: " + (10 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + "-" + (16 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + ", Energy cost: 20, Mana cost: 15.");
                    System.out.println("\t\t\tStrong attack.");
                    break;
                case 2:
                    System.out.println("Enter 3 = " + getAbilityName()[i] + ".Damage: " + (finalWeaponDamage + 5) + ", Damage over time: 19, Mana cost: 35, Mana burn: 20.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal non stackable damage over time, damage will be dealt for 4 rounds. Ability burn 20 mana from enemy after cast.");
                    break;
                case 3:
                    System.out.println("Enter 4 = " + getAbilityName()[i] + ". Damage " + (25 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + "-" + (32 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + ", Energy cost: 40, Mana cost: 40.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal massive damage but it's cost is high. If enemy healths are below 20%, damage is doubled.");
                    break;
                case 4:
                    System.out.println("Enter 5 = " + getAbilityName()[i] + ". Damage: 20, Absorb damage bonus: 20, Energy cost: 35, Mana cost: 25.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " Deal 20 damage and increase Hunter bonus absorb damage by 20. Can be stacked.");
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
        super.setWeaponAttack(true);
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setManaCost(10);
        super.setEnergyCost(20);
        super.setWeaponAttack(true);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getDamage() + super.getSpellDamage());
    }


    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        super.setManaDrain(20);
        super.setManaCost(35);
        super.setDamageOverTime(19);
        super.setCanCastDot(true);
        super.setTotalDamage(5);
        super.setWeaponAttack(true);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        int randomDamageChange = (int)(Math.random() * 8);
        super.setDamageFromAttackType(randomDamageChange + 25);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getDamage() + super.getSpellDamage());
        super.setWeaponAttack(true);
        super.setManaCost(40);
        super.setEnergyCost(40);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(25);
        super.setEnergyCost(35);
        super.setTotalDamage(20);
        super.setAbsorbDamageBonusIncrease(20);
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
