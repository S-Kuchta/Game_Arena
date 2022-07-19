package Heroes;

public class Warrior extends Heroes {

    public Warrior(String name) {
        super(name,680, 19, 0, 10,0,0,0,0,8);
    }

    private final int absorbDamageBonusIncrease = 25;
    private final int[] rageCostWarrior = {0,0,10,0,25,0};

    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Skip", "Normal Attack", "Heroic Strike", "Execute", "Rend", "Endless rage"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + " round.");
                    break;
                case 1:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ", Rage generate: 12.");
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 2:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (10 + super.getDamage() + finalWeaponDamage) + "-" + (16 + super.getDamage() + finalWeaponDamage) + ", Rage cost: " + rageCostWarrior[i] + ".");
                    System.out.println("\t\t\tStrong weapon attack. Attack restore 25% health from damage dealt.");
                    break;
                case 3:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage " + (20 + super.getDamage() + finalWeaponDamage) + "-" + (29 + super.getDamage() + finalWeaponDamage) + ", Rage cost: " + this.getRage() + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal high amount of damage. Consuming all rage generated. Damage is increased by amount of rage consume. If rage is 100, " + getAbilityName()[i] + ", give you 25% healths from damage dealt.");
                    break;
                case 4:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Damage: " + (damage+finalWeaponDamage) + ",Damage over time: " + damage + " , Rage cost: " + rageCostWarrior[i] + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal damage." + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds.");
                    break;
                case 5:
                    System.out.println("Enter " + i + " = " + getAbilityName()[i] + ". Absorb damage bonus: " + absorbDamageBonusIncrease + ", rage generate: " + 10 + ".");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability increase stackable Warrior bonus absorb damage  and generate rage.");
                    break;
            }
        }
    }

    @Override
    protected void normalAttack() {
        super.normalAttack();
        super.setRage(getRage() + rageGenerator(12));
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRageCost(0);
        super.setWeaponAttack(true);
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setWeaponAttack(true);
        super.setLifeSteal(true);
        super.setRageCost(rageCostWarrior[2]);
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
        super.setTotalDamage(damage);
        super.setDamageOverTime(damage,super.getDamageOverTime()[1]);
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRageCost(rageCostWarrior[4]);
        super.setCanCastDot(true,false,false);
        super.setWeaponAttack(true);
        super.setCanCastDotStacks(false);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(0);
        super.setEnergyCost(0);
        super.setRage(getRage() + rageGenerator(10));
        super.setAbsorbDamageBonusIncrease(absorbDamageBonusIncrease);
    }

    private int rageGenerator(int amountOfRageGenerate) {
        int maxRage = 100;
        return Math.min(maxRage - super.getRage(), amountOfRageGenerate);
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
