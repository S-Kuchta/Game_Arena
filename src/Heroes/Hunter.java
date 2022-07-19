package Heroes;

public class Hunter extends Heroes {
    public Hunter(String name) {
        super(name, 585, 16, 10,7,0,0,130,10,13);
    }

    private final int absorbDamageBonusIncrease = 20;
    private final int[] manaCost = {0,0,15,35,40,25};
    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Skip","Normal Shot", "Aimed Shot", "Black Arrow", "Kill Shot", "Counterattack"});
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
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (10 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + "-" + (16 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\tStrong attack.");
                    break;
                case 3:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ".Damage: " + (finalWeaponDamage + 5) + ", Damage over time: " + damage + ", Mana burn: " + spellDamage + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal non stackable damage over time, damage will be dealt for 4 rounds. Ability burn mana from enemy after cast.");
                    break;
                case 4:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage " + (25 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + "-" + (32 + super.getDamage() + super.getSpellDamage() + finalWeaponDamage) + ",Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal massive damage but it's cost is high. If enemy healths are below 20%, damage is doubled.");
                    break;
                case 5:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + damage + ", Absorb damage bonus: " + absorbDamageBonusIncrease + ", Mana cost: " + manaCost[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " Deal damage and increase Hunter bonus absorb damage. Can be stacked.");
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
        super.setWeaponAttack(true);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getDamage() + super.getSpellDamage());
        super.setManaCost(manaCost[2]);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        super.setCanCastDot(true,false,false);
        super.setWeaponAttack(true);
        super.setManaDrain(spellDamage);
        super.setManaCost(manaCost[3]);
        super.setDamageOverTime(damage,super.getDamageOverTime()[1]);
        super.setTotalDamage(5);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        int randomDamageChange = (int)(Math.random() * 8);
        super.setDamageFromAttackType(randomDamageChange + 25);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getDamage() + super.getSpellDamage());
        super.setWeaponAttack(true);
        super.setManaCost(manaCost[4]);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(manaCost[5]);
        super.setTotalDamage(damage);
        super.setAbsorbDamageBonusIncrease(absorbDamageBonusIncrease);
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
