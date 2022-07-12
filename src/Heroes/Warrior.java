package Heroes;

public class Warrior extends Heroes {

    public Warrior(String name) {
        super(name,630, 15, 0, 10,0,0,0,0,7);
    }

    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Normal Attack", "Heroic Strike", "Execute", "Rend", "Endless rage", "Skip"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter 1 = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ", Rage generate: 6");
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 1:
                    System.out.println("Enter 2 = " + getAbilityName()[i] + ". Damage: " + (10 + super.getDamage() + finalWeaponDamage) + "-" + (16 + super.getDamage() + finalWeaponDamage) + ", Rage generate: 11");
                    System.out.println("\t\t\tStrong weapon attack.");
                    break;
                case 2:
                    System.out.println("Enter 3 = " + getAbilityName()[i] + ". Damage " + (20 + super.getDamage() + finalWeaponDamage) + "-" + (29 + super.getDamage() + finalWeaponDamage) + ", Rage cost 0-100");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal high amount of damage. Consuming all rage generated. Damage is increased by amount of rage consume. If rage is 100, " + getAbilityName()[i] + ", give you 25% healths from damage dealt.");
                    break;
                case 3:
                    System.out.println("Enter 4 = " + getAbilityName()[i] + ". Damage: " + (15+finalWeaponDamage) + ",Damage over time: 16, Rage cost: 25.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal " + (15+finalWeaponDamage) + " damage." + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds");
                    break;
                case 4:
                    System.out.println("Enter 5 = " + getAbilityName()[i] + ". Absorb damage bonus: 25.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability increase stackable Warrior bonus absorb damage by 25 and generate 10 rage.");
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
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRageGenerate(6);
        super.setWeaponAttack(true);
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setWeaponAttack(true);
        super.setLifeSteal(false);
        super.setRageGenerate(11);
        super.setEnergyCost(0);
        super.setManaCost(0);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 20);
        super.setLifeSteal(super.getRage() == 100);
        super.setTotalDamage(getDamageFromAttackType() + super.getDamage() + super.getRage());
        super.setRageCost(super.getRage());
        super.setWeaponAttack(true);

    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setTotalDamage(15);
        super.setDamageOverTime(16);
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRageCost(25);
        super.setCanCastDot(true);
        super.setWeaponAttack(true);
        super.setCanCastDotStacks(false);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRageGenerate(10);
        super.setAbsorbDamageBonusIncrease(25);
    }

    /**
     *
     * @param attackerRage;
     * @param whichAbilityWasUsed;
     *
     * @return rage generated.
     */
    public int rageGenerator(int attackerRage, int whichAbilityWasUsed) {
        int rageGenerate = 0;
        switch(whichAbilityWasUsed) {
            case 0:
                rageGenerate = 6;
                break;
            case 1:
                rageGenerate = 11;
                break;
            case 4:
                rageGenerate = 10;
                break;
        }

        if (super.getMaxRage() - attackerRage >= rageGenerate) {
            return rageGenerate;
        } else {
            return super.getMaxRage() - attackerRage;
        }
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
