package Heroes;

import java.util.Scanner;

public class Heroes {
    final private Scanner scanner = new Scanner(System.in);

    private String name;
    private int health;
    private int maxHealth;
    private int damage;
    private int totalDamage;
    private int spellDamage;
    private boolean lifeSteal;
    private boolean dotLifeSteal;
    private int damageOverTime;
    private int totalDamageOverTime;
    private int absorbDamage;
    private int AbsorbDamageBonus;
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
    private boolean weaponAttack;
    private boolean canCastDot; // if true: Damage over time ability set countDotTick to 0 after cast ability
    private int countDotTick;
    private boolean canCastDotStacks; // if true: damage over time ability can stack
    private int dotStacksCount;
    private int whichAbilityWasUsed;
    private int rage;
    private int maxRage;
    private int rageCost;
    private int rageGenerate;



    public Heroes(String name, int health, int damage, int spellDamage, int absorbDamage,int energy,int energyRegeneration,int mana, int manaRegeneration, int criticalHitChance) {
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
        this.rage = 0;
        this.maxRage = 100;
        this.criticalHitChance = criticalHitChance;

    }

    public void attackTypeText(int finalWeaponDamage) {
    }

    public void attackType(int finalWeaponDamage) {
        System.out.println("\nYour next Action:");
        attackTypeText(finalWeaponDamage);

        while(true) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Enter valid value. Enter number between 1-6: ");
            }

            int type = scanner.nextInt();
            if (type <= 6 && type > 0) {
                switch (type) {
                    case 1:
                        this.setWhichAbilityWasUsed(0);
                        normalAttack();
//                        criticalHit();
                        break;

                    case 2:
                        this.setWhichAbilityWasUsed(1);
                        strongAttack();
//                        criticalHit();
                        break;

                    case 3:
                        this.setWhichAbilityWasUsed(2);
                        specialAttackAbility1();
//                        criticalHit();
                        break;

                    case 4:
                        this.setWhichAbilityWasUsed(3);
                        specialAttackAbility2();
//                        criticalHit();
                        break;

                    case 5:
                        this.setWhichAbilityWasUsed(4);
                        specialDefendAbility();
                        break;

                    case 6:
                        this.setWhichAbilityWasUsed(5);
                        this.setLifeSteal(false);
                        this.setWeaponAttack(false);
                        this.setCanCastDot(false);
                        this.setRageCost(0);
                        this.setTotalDamage(0);
                        this.setDamageOverTime(0);
                        this.setEnergyCost(0);
                        this.setManaCost(0);
                        this.setEnergyDrain(0);
                        this.setManaDrain(0);
                        break;
                }
                break;
            } else {
                System.out.println("Enter valid value. Enter number between 1-6: ");
            }
        }
    }

    public boolean criticalHit() {
        int criticalHit = (int)(Math.random() * 101);
        return criticalHit <= this.criticalHitChance;
    }

    protected void normalAttack() {
        int randomDamageChange = (int)(Math.random() * 5);
        this.setDamageFromAttackType(randomDamageChange + 4);
        this.setTotalDamage(getDamageFromAttackType() + this.damage);
        this.setEnergyCost(12);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setLifeSteal(false);
        this.setWeaponAttack(true);
    }


    protected void strongAttack() {
        int randomDamageChange = (int)(Math.random() * 7);
        this.setDamageFromAttackType(randomDamageChange + 10);
        this.setTotalDamage(getDamageFromAttackType() + this.damage);
        this.setEnergyCost(20);
        this.setRageCost(0);
        this.setManaCost(0);
        this.setLifeSteal(false);
    }


    protected void specialAttackAbility1() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false);
        this.setEnergyCost(0);
        this.setManaCost(0);
        this.setRageCost(0);
    }

    protected void specialAttackAbility2() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false);
        this.setEnergyCost(0);
        this.setManaCost(0);
        this.setRageCost(0);
    }

    protected void specialDefendAbility() {
        this.setLifeSteal(false);
        this.setWeaponAttack(false);
        this.setCanCastDotStacks(false);
        this.setCanCastDot(false);
        this.setEnergyCost(0);
        this.setManaCost(0);
        this.setRageCost(0);
    }


    public void manaAndEnergyRegeneration() {
        if(this.getMaxEnergy() - this.getEnergy() > this.getEnergyRegeneration()) {
            this.setEnergy(this.getEnergy() + this.getEnergyRegeneration());
        } else {
            this.setEnergy(this.getMaxEnergy());
        }

         if(this.getMaxMana() - this.getMana() > this.getManaRegeneration()) {
            this.setMana(this.getMana() + this.getManaRegeneration());
        } else {
            this.setMana(this.getMaxMana());
        }
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

    public boolean isDotLifeSteal() {
        return dotLifeSteal;
    }

    public void setDotLifeSteal(boolean dotLifeSteal) {
        this.dotLifeSteal = dotLifeSteal;
    }

    public int getDamageOverTime() {
        return damageOverTime;
    }

    public void setDamageOverTime(int damageOverTime) {
        this.damageOverTime = damageOverTime;
    }

    public int getTotalDamageOverTime() {
        return totalDamageOverTime;
    }

    public void setTotalDamageOverTime(int totalDamageOverTime) {
        this.totalDamageOverTime = totalDamageOverTime;
    }

    public int getAbsorbDamage() {
        return absorbDamage;
    }

    public void setAbsorbDamage(int absorbDamage) {
        this.absorbDamage = absorbDamage;
    }

    public int getAbsorbDamageBonus() {
        return AbsorbDamageBonus;
    }

    public void setAbsorbDamageBonus(int absorbDamageBonus) {
        AbsorbDamageBonus = absorbDamageBonus;
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

    public int getCountDotTick() {
        return countDotTick;
    }

    public void setCountDotTick(int countDotTick) {
        this.countDotTick = countDotTick;
    }

    public String[] getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String[] abilityName) {
        this.abilityName = abilityName;
    }

    public boolean isWeaponAttack() {
        return weaponAttack;
    }

    public void setWeaponAttack(boolean weaponAttack) {
        this.weaponAttack = weaponAttack;
    }

    public boolean isCanCastDot() {
        return canCastDot;
    }

    public void setCanCastDot(boolean canCastDot) {
        this.canCastDot = canCastDot;
    }

    public boolean isCanCastDotStacks() {
        return canCastDotStacks;
    }

    public void setCanCastDotStacks(boolean canCastDotStacks) {
        this.canCastDotStacks = canCastDotStacks;
    }

    public int getDotStacksCount() {
        return dotStacksCount;
    }

    public void setDotStacksCount(int dotStacksCount) {
        this.dotStacksCount = dotStacksCount;
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

    public int getMaxRage() {
        return maxRage;
    }

    public void setMaxRage(int maxRage) {
        this.maxRage = maxRage;
    }

    public int getRageCost() {
        return rageCost;
    }

    public void setRageCost(int rageCost) {
        this.rageCost = rageCost;
    }

    public int getRageGenerate() {
        return rageGenerate;
    }

    public void setRageGenerate(int rageGenerate) {
        this.rageGenerate = rageGenerate;
    }
}