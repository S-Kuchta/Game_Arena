package Heroes;

public class Warrior extends Heroes {

    public Warrior(String name) {
        super(name,750, 21, 0, 7,0,0,0,0,8);
    }

    private final int absorbDamageBonusIncrease = damage * 2;
    private final int[] rageCostWarrior = {0,10,executeRageCostCalculate(),25,0};

    @Override
    public void attackTypeText(int finalWeaponDamage, boolean computerPlay) {
        super.attackTypeText(finalWeaponDamage, computerPlay);
        super.setAbilityName(new String[]{"Normal Attack", "Heroic Strike", "Execute", "Rend", "Endless rage"});
        super.setDamageOverTimeAbilityName("Bleed","");
        if(!computerPlay) {
            for (int i = 0; i < getAbilityName().length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (4 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (8 + damage + finalWeaponDamage) + colorReset
                                + ", Rage generate: " + colorMagenta + 12 + colorReset + ".");
                        System.out.println("\t\t\tNormal weapon attack.");
                        break;
                    case 1:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (10 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (16 + damage + finalWeaponDamage) + colorReset
                                + ", Rage cost: " + colorMagentaBright + rageCostWarrior[i] + colorReset + ".");
                        System.out.println("\t\t\tStrong weapon attack. Attack restore 25% health from damage dealt.");
                        break;
                    case 2:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage " + colorRed + (20 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (29 + damage + finalWeaponDamage) + colorReset
                                + ", Rage cost: " + colorMagentaBright + executeRageCostCalculate() + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal high amount of damage. Consuming all rage generated. Damage is increased by amount of rage consume. If rage is 100, " + getAbilityName()[i] + ", give you 25% healths from damage dealt.");
                        break;
                    case 3:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (damage + finalWeaponDamage) + colorReset + ",Damage over time: " + colorRed + damage + colorReset
                                + " , Rage cost: " + colorMagentaBright + rageCostWarrior[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal damage. " + getDamageOverTimeAbilityName()[0] + " effect of " + getAbilityName()[i] + " deal damage over time, damage will be dealt for 4 rounds.");
                        break;
                    case 4:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Absorb damage bonus: " + colorCyan + absorbDamageBonusIncrease + colorReset
                                + ", Rage generate: " + colorMagenta + 10 + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability increase stackable Warrior bonus absorb damage  and generate rage.");
                        break;
                }
            }
        }
    }

    private int executeRageCostCalculate() {
        if((super.getRage() - 30) > 100) {
            return 100;
        } else return Math.max(super.getRage(), 30);
    }

    @Override
    protected void normalAttack() {
        super.normalAttack();
        super.setRage(getRage() + rageGenerator(12));
        super.setRageCost(rageCostWarrior[0]);
        super.setWeaponAttack(true);
    }

    @Override
    protected void strongAttack() {
        super.strongAttack();
        super.setWeaponAttack(true);
        super.setLifeSteal(true);
        super.setRageCost(rageCostWarrior[1]);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 20);
        super.setLifeSteal(super.getRage() == 100);
        super.setTotalDamage(getDamageFromAttackType() + damage + super.getRage());
        super.setRageCost(executeRageCostCalculate());
        super.setWeaponAttack(true);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setTotalDamage(damage);
        super.setDamageOverTime(damage,super.getDamageOverTime()[1]);
        super.setRageCost(rageCostWarrior[3]);
        super.setCanCastDot(true,false,false);
        super.setWeaponAttack(true);
        super.setCanCastDotStacks(false);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setRageCost(rageCostWarrior[4]);
        super.setRage(getRage() + rageGenerator(10));
        super.absorbDamageIncrease(absorbDamageBonusIncrease);
    }

    private int rageGenerator(int amountOfRageGenerate) {
        int maxRage = 100;
        return Math.min(maxRage - super.getRage(), amountOfRageGenerate);
    }


    @Override
    public void statsText() {
        System.out.print("" + this.getName() + "\t\t\t\tHealths: " + colorRedBright + this.getHealth() + colorReset
                + "\t\tRage: " + colorMagentaBright + this.getRage() + colorReset
                + "\t\t Bonus absorb shield: " + colorCyan + this.getAbsorbDamageBonus() + colorReset + "\n");
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }

    @Override
    protected int computerHeroAutoAttack() {
        int abilityCasted = 1;

        if(super.getRage() == 100) {
            abilityCasted = super.attackChanceCalculator(100,3,0,0,0,0,0,0);
        }
        if(super.getDefenderHealth() < (super.getDefenderMaxHealth() / 100) * 20) {
            abilityCasted = super.attackChanceCalculator(80,3,10,2,10,1,0,0);
        }
        if(super.getHealth() <= super.getMaxHealth() / 4) {
             if (super.getRage() <= 100 && super.getRage() > 80) {
                abilityCasted = super.attackChanceCalculator(60,3,20,2,10,1,10,5);
            }
        } else if(super.getHealth() < (super.getMaxHealth() / 2)) {
            if(super.getRage() <= 100) {
                abilityCasted = super.attackChanceCalculator(40,1,30,2,20,3,10,5);
            }
            if(super.getRage() > 30 && super.getCountDotTick()[0] == 0 || super.getCountDotTick()[0] >= 3) {
                abilityCasted = super.attackChanceCalculator(80,4,10,1,10,2,10,5);
            }
        } else if(super.getHealth() > (super.getMaxHealth() / 2)) {
            if(super.getRage() <= 15) {
                abilityCasted = super.attackChanceCalculator(95,1,5,5,0,0,0,0);
            }
            if(super.getRage() <= 30 && super.getRage() > 15) {
                abilityCasted = super.attackChanceCalculator(85, 1,10,2,5,5,0,0);
            }
            if(super.getRage() > 25 && super.getCountDotTick()[0] == 0 ||super.getCountDotTick()[0] >= 3) {
                abilityCasted = super.attackChanceCalculator(90,4,5,1,5,2,0,0);
            }
            if(super.getRage() <= 60) {
                abilityCasted = super.attackChanceCalculator(70, 1,25,2,5,5,0,0);
            }
        }
        return abilityCasted;
    }
}