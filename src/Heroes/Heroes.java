package Heroes;

import java.util.Scanner;

public class Heroes {
    final private Scanner scanner = new Scanner(System.in);

    private String name;
    private int health;
    private int maxHealth;
    protected int damage;
    private int totalDamage;
    protected int spellDamage;
    private boolean lifeSteal;
    private boolean manaSteal;
    private boolean dotLifeSteal;
    private int[] damageOverTime = {0, 0};
    private int[] totalDamageOverTime = {0, 0};
    private int absorbDamage;
    private int absorbDamageBonus;
    private int absorbDamageBonusIncrease;
    private int energy;
    private int maxEnergy;
    private int energyCost;
    private int energyDrain;
    private int energyRegeneration;
    private int criticalHitChance;
    private int mana;
    private int maxMana;
    private int manaCost;
    private int manaRegeneration;
    private int manaDrain;
    private int damageFromAttackType;
    protected String[] abilityName;
    protected String[] damageOverTimeAbilityName;
    private boolean weaponAttack;
    private boolean[] canCastDot; // if true: Damage over time ability set countDotTick to 0 after cast ability
    private int[] countDotTick = {0, 0, 0};
    private boolean canCastDotStacks; // if true: damage over time ability can stack
    private int[] dotStacksCount = {0, 0};
    private int whichAbilityWasUsed;
    private int rage;
    private int rageCost;
    public String colorRed = "\u001B[31m";
    public final String colorBlueBright = "\033[1;94m";
    public final String colorYellow = "\u001B[33m";
    public final String colorCyan = "\u001B[36m";
    public final String colorRedBright = "\033[1;91m";

    public final String colorMagenta = "\u001B[35m";
    public final String colorMagentaBright = "\033[1;95m";
    public final String colorReset = "\u001B[0m";
    public final String colorBlue = "\u001B[34m";
    public final String colorGreen = "\033[0;32m";
    protected int defenderHealth;
    protected int defenderMaxHealth;
    protected int[] defenderDotStacks = {0,0};

    public Heroes(String name, int health, int damage, int spellDamage, int absorbDamage, int energy, int energyRegeneration, int mana, int manaRegeneration, int criticalHitChance) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.spellDamage = spellDamage;
        this.absorbDamage = absorbDamage;
        this.energy = energy;
        this.maxEnergy = energy;
        this.energyRegeneration = energyRegeneration;
        this.mana = mana;
        this.maxMana = mana;
        this.manaRegeneration = manaRegeneration;
        this.criticalHitChance = criticalHitChance;
    }

    public Heroes() {}

    public void attackTypeText(int finalWeaponDamage, boolean computerPlay) {
    }

    public void attackType(boolean computerPlay) {

        int type;
        while (true) {
            if (computerPlay) {
                    type = computerHeroAutoAttack();

            } else {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.print("Enter valid value. Enter number between 1-" + (getAbilityName().length) + ": ");
                }
                type = scanner.nextInt();
            }
            if (type > 0 && type <= getAbilityName().length) {
                switch (type) {
                    case 1:
                        this.setWhichAbilityWasUsed(1);
                        normalAttack();
                        break;

                    case 2:
                        this.setWhichAbilityWasUsed(2);
                        strongAttack();
                        break;

                    case 3:
                        this.setWhichAbilityWasUsed(3);
                        specialAttackAbility1();
                        break;

                    case 4:
                        this.setWhichAbilityWasUsed(4);
                        specialAttackAbility2();
                        break;

                    case 5:
                        this.setWhichAbilityWasUsed(5);
                        specialDefendAbility();
                        break;
                }
                break;
            } else {
                System.out.print("Enter valid value. Enter number between 1-" + (getAbilityName().length) + ": ");
            }
        }
    }

    public boolean criticalHit() {
        int criticalHit = (int) (Math.random() * 101);
        return criticalHit <= this.criticalHitChance;
    }

    protected int attackChanceCalculator(int percentageChance1,int abilityCast1, int percentageChance2, int abilityCast2, int percentageChance3, int abilityCast3,int percentageChance4, int abilityCast4) {
        int percentage = (int) (Math.random() * 101);
        int abilityCasted;
        if(percentage <= percentageChance1) {
            abilityCasted = abilityCast1;
        } else if(percentage <= (percentageChance1 + percentageChance2) ) {
            abilityCasted = abilityCast2;
        } else if(percentage <= (percentageChance1 + percentageChance2 + percentageChance3)) {
            abilityCasted = abilityCast3;
        } else if(percentage <= (percentageChance1 + percentageChance2 + percentageChance3 + percentageChance4)) {
            abilityCasted = abilityCast4;
        } else {
            while(true) {
                abilityCasted = (int)(Math.random() * getAbilityName().length) + 1;
                if(abilityCasted == abilityCast1) {
                    continue;
                }
                break;
            }
        }
        return abilityCasted;
    }

    protected void normalAttack() {
        int randomDamageChange = (int) (Math.random() * 5);
        this.setDamageFromAttackType(randomDamageChange + 4);
        this.setTotalDamage(getDamageFromAttackType() + this.damage);
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false, false, false);
        this.setManaSteal(false);
        this.setEnergyCost(0);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setEnergyDrain(0);
        this.setManaDrain(0);
    }


    protected void strongAttack() {
        int randomDamageChange = (int) (Math.random() * 7);
        this.setDamageFromAttackType(randomDamageChange + 10);
        this.setTotalDamage(getDamageFromAttackType() + this.damage);
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false, false, false);
        this.setManaSteal(false);
        this.setEnergyCost(0);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setEnergyDrain(0);
        this.setManaDrain(0);
    }


    protected void specialAttackAbility1() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false, false, false);
        this.setManaSteal(false);
        this.setEnergyCost(0);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setEnergyDrain(0);
        this.setManaDrain(0);
    }

    protected void specialAttackAbility2() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false, false, false);
        this.setManaSteal(false);
        this.setEnergyCost(0);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setEnergyDrain(0);
        this.setManaDrain(0);
    }

    protected void specialDefendAbility() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false, false, false);
        this.setManaSteal(false);
        this.setEnergyCost(0);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setEnergyDrain(0);
        this.setManaDrain(0);
    }

    protected void absorbDamageIncrease(int absorbDamageBonusIncrease) {
        this.setAbsorbDamageBonus(absorbDamageBonusIncrease);
    }


    public void manaAndEnergyRegeneration() {
        if (this.getMaxEnergy() - this.getEnergy() > this.getEnergyRegeneration()) {
            this.setEnergy(this.getEnergy() + this.getEnergyRegeneration());
        } else {
            this.setEnergy(this.getMaxEnergy());
        }

        if (this.getMaxMana() - this.getMana() > this.getManaRegeneration()) {
            this.setMana(this.getMana() + this.getManaRegeneration());
        } else {
            this.setMana(this.getMaxMana());
        }
    }

    public void damageAndStatsTakenValue(int damage, int energyDrain, int manaDrain, int absorbDamageBonus) {
        this.health -= damage;
        this.energy -= energyDrain;
        this.mana -= manaDrain;
        this.absorbDamageBonus = absorbDamageBonus;
    }

    public void statsRestoreValue(int healthRestore, int energyRestore, int manaRestore) {
        this.health += healthRestore;
        this.energy += energyRestore;
        this.mana += manaRestore;
    }

    public void statsText() {
        System.out.print("" + this.getName() + "\t\t\t\tHealths: " + colorRedBright + this.getHealth() + colorReset
                + "\t\tMana: " + colorBlueBright + this.getMana() + colorReset
                + "\t\t Bonus absorb shield: " + colorCyan + this.getAbsorbDamageBonus() + colorReset + "\n");
    }

    protected int computerHeroAutoAttack() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void setSpellDamage(int spellDamage) {
        this.spellDamage = spellDamage;
    }

    public boolean isLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(boolean lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public boolean isManaSteal() {
        return manaSteal;
    }

    public void setManaSteal(boolean manaSteal) {
        this.manaSteal = manaSteal;
    }

    public boolean isDotLifeSteal() {
        return dotLifeSteal;
    }

    public void setDotLifeSteal(boolean dotLifeSteal) {
        this.dotLifeSteal = dotLifeSteal;
    }

    public int[] getDamageOverTime() {
        return damageOverTime;
    }

    public void setDamageOverTime(int damageOverTime1, int damageOverTime2) {
        this.damageOverTime = new int[]{damageOverTime1, damageOverTime2};
    }

    public int[] getTotalDamageOverTime() {
        return totalDamageOverTime;
    }

    public void setTotalDamageOverTime(int totalDamageOverTime1, int totalDamageOverTime2) {
        this.totalDamageOverTime = new int[]{totalDamageOverTime1, totalDamageOverTime2};
    }

    public int getAbsorbDamage() {
        return absorbDamage;
    }

    public void setAbsorbDamage(int absorbDamage) {
        this.absorbDamage = absorbDamage;
    }

    public int getAbsorbDamageBonus() {
        return absorbDamageBonus;
    }

    public void setAbsorbDamageBonus(int absorbDamageBonus) {
        this.absorbDamageBonus = absorbDamageBonus;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getEnergyDrain() {
        return energyDrain;
    }

    public void setEnergyDrain(int energyDrain) {
        this.energyDrain = energyDrain;
    }

    public int getEnergyRegeneration() {
        return energyRegeneration;
    }

    public void setEnergyRegeneration(int energyRegeneration) {
        this.energyRegeneration = energyRegeneration;
    }

    public int getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(int criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public int getManaDrain() {
        return manaDrain;
    }

    public void setManaDrain(int manaDrain) {
        this.manaDrain = manaDrain;
    }

    public int getDamageFromAttackType() {
        return damageFromAttackType;
    }

    public void setDamageFromAttackType(int damageFromAttackType) {
        this.damageFromAttackType = damageFromAttackType;
    }

    public int[] getCountDotTick() {
        return countDotTick;
    }

    public void setCountDotTick(int firstCount, int secondCount, int thirdCount) {
        this.countDotTick = new int[]{firstCount, secondCount, thirdCount};
    }


    public String[] getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String[] abilityName) {
        this.abilityName = abilityName;
    }

    public String[] getDamageOverTimeAbilityName() {
        return damageOverTimeAbilityName;
    }

    public void setDamageOverTimeAbilityName(String firstName,String secondName) {
        this.damageOverTimeAbilityName = new String[]{firstName,secondName};
    }

    public boolean isWeaponAttack() {
        return weaponAttack;
    }

    public void setWeaponAttack(boolean weaponAttack) {
        this.weaponAttack = weaponAttack;
    }

    public boolean[] isCanCastDot() {
        return canCastDot;
    }

    public void setCanCastDot(boolean firstValue, boolean secondValue, boolean thirdValue) {
        this.canCastDot = new boolean[]{firstValue, secondValue, thirdValue};
    }

    public boolean isCanCastDotStacks() {
        return canCastDotStacks;
    }

    public void setCanCastDotStacks(boolean canCastDotStacks) {
        this.canCastDotStacks = canCastDotStacks;
    }

    public int[] getDotStacksCount() {
        return dotStacksCount;
    }

    public void setDotStacksCount(int dotStacksCount1, int dotStacksCount2) {
        this.dotStacksCount = new int[]{dotStacksCount1, dotStacksCount2};
    }

    public int getAbsorbDamageBonusIncrease() {
        return absorbDamageBonusIncrease;
    }

    public void setAbsorbDamageBonusIncrease(int absorbDamageBonusIncrease) {
        this.absorbDamageBonusIncrease = absorbDamageBonusIncrease;
    }

    public int getWhichAbilityWasUsed() {
        return whichAbilityWasUsed;
    }

    public void setWhichAbilityWasUsed(int whichAbilityWasUsed) {
        this.whichAbilityWasUsed = whichAbilityWasUsed;
    }

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getRageCost() {
        return rageCost;
    }

    public void setRageCost(int rageCost) {
        this.rageCost = rageCost;
    }

    public int getDefenderHealth() {
        return defenderHealth;
    }

    public void setDefenderHealth(int defenderHealth) {
        this.defenderHealth = defenderHealth;
    }

    public int getDefenderMaxHealth() {
        return defenderMaxHealth;
    }

    public void setDefenderMaxHealth(int defenderMaxHealth) {
        this.defenderMaxHealth = defenderMaxHealth;
    }

    public int[] getDefenderDotStacks() {
        return defenderDotStacks;
    }

    public void setDefenderDotStacks(int defenderDotStacks1,int defenderDotStacks2) {
        this.defenderDotStacks = new int[]{defenderDotStacks1,defenderDotStacks2};
    }
}