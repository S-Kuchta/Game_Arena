package GameMechanic;

import Heroes.Heroes;
import Heroes.Hunter;
import Heroes.Paladin;
import Heroes.Warrior;
import Heroes.Warlock;
import Weapons.Axe;
import Weapons.Sword;
import Weapons.Staff;
import Weapons.Mace;
import Weapons.Bow;
import Weapons.Weapons;


import java.util.Scanner;

public class GameMechanic {
    private final Scanner scanner = new Scanner(System.in);

    private Heroes playerHero;
    private Heroes computerHero;
    private String name;
    private Weapons playerWeapon;
    private Weapons computerWeapon;
    private boolean computerPlay;
    private boolean attacked = true;

    private final Heroes[] heroes = {getHunter(), getWarrior(), getPaladin(), getWarlock()};
    private Weapons[] weapons;


    private Warrior getWarrior() {
        return new Warrior(name);
    }

    private Hunter getHunter() {
        return new Hunter(name);
    }

    private Paladin getPaladin() {
        return new Paladin(name);
    }

    private Warlock getWarlock() {
        return new Warlock(name);
    }

    private Weapons getAxe() {
        return new Axe();
    }

    private Weapons getMace() {
        return new Mace();
    }

    private Weapons getSword() {
        return new Sword();
    }

    private Weapons getBow() {
        return new Bow();
    }

    private Weapons getStaff() {
        return new Staff();
    }


    private void heroChooseText() {

        for (int i = 0; i < heroes.length; i++) {
            System.out.println("\t Enter " + (i + 1) + " = " + heroes[i].getClass().getSimpleName() + "\t(health: " + heroes[i].getHealth() + ",\t mana: " + heroes[i].getMaxMana() + ",\t damage: " + heroes[i].getDamage() + ",\t spell damage: " + heroes[i].getSpellDamage() +
                    ",\t absorb damage: " + heroes[i].getAbsorbDamage() + "\n\t\t\t\t\t\t critical hit chance: " + heroes[i].getCriticalHitChance() + "%,\t energy regeneration: " + heroes[i].getEnergyRegeneration() + ",\t mana regeneration: " + heroes[i].getManaRegeneration() + ")\n");
        }
        System.out.print("Enter number: ");
    }

    private void weaponChooseText() {
        System.out.println("\n\tPick a weapon: ");
        for (int i = 0; i < weapons.length; i++) {
            System.out.print("\tEnter " + (i + 1) + " = ");
            weapons[i].weaponChooseText();
        }
        System.out.print("Enter number: ");
    }

    private void validValueHeroText() {
        System.out.println("Enter valid value. Enter number between 1-" + heroes.length + ": ");
    }
    private void validValueWeaponText() {
        System.out.println("Enter valid value. Enter number between 1-" + weapons.length + ": ");
    }

    private Heroes heroesChoose(boolean computerPlay) {
        int heroSet;
        while (true) {
        if(computerPlay) {
            heroSet = ((int)(Math.random() * heroes.length + 1));
            heroSet = 2;
        } else {
            heroChooseText();
            while (!scanner.hasNextInt()) {
                scanner.next();
                validValueHeroText();
            }
            heroSet = scanner.nextInt();
        }
            if (heroSet > 0 && heroSet <= heroes.length) {
                switch (heroSet) {
                    case 1:
                        return getHunter();
                    case 2:
                        return getWarrior();
                    case 3:
                        return getPaladin();
                    case 4:
                        return getWarlock();
                }
                break;
            } else {
                validValueHeroText();
            }
        }
        return null;
    }

    private Weapons weaponChoose(Heroes whoIsChoosing) {
        if (whoIsChoosing instanceof Warlock) {
            setWeapons(new Weapons[]{getStaff()});
        } else if (whoIsChoosing instanceof Hunter) {
            setWeapons(new Weapons[]{getBow()});
        } else {
            setWeapons(new Weapons[]{getSword(), getAxe(), getMace()});
        }

        while (true) {
            int weaponSet;
            if(computerPlay) {
                weaponSet = ((int)(Math.random() * weapons.length + 1));
            } else {
                weaponChooseText();
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    validValueWeaponText();
                }
                weaponSet = scanner.nextInt();
            }

            if (weaponSet >= 1 && weaponSet <= weapons.length) {
                switch (weaponSet) {
                    case 1:
                        return weapons[0];
                    case 2:
                        return weapons[1];
                    case 3:
                        return weapons[2];
                    case 4:
                        return weapons[3];
                    case 5:
                        return weapons[4];
                    case 6:
                        return weapons[5];
                }
                break;
            } else {
                validValueWeaponText();
            }
        }
        return null;
    }

    public void computerChosenHeroAndWeapon() {
        System.out.println("\tComputer hero is " + computerHero.getClass().getSimpleName() + ".");
        System.out.println("\tComputer weapon is " + computerWeapon.getClass().getSimpleName() + ".");
    }

    public void playerHero() {
        System.out.print("\n\tEnter player 1 name: ");
        name = scanner.next();
        System.out.println("\nChoose " + name + " hero: ");
        playerHero = heroesChoose(false);
        playerWeapon = weaponChoose(playerHero);
    }

    public void computerHero(boolean computerPlay) {
        this.computerPlay = computerPlay;
        System.out.print("\n\tEnter player 2 name: ");
        name = scanner.next();
        if(!computerPlay) {
            System.out.println("\nChoose " + name + " hero: ");
        }
        computerHero = heroesChoose(computerPlay);
        computerWeapon = weaponChoose(computerHero);
        computerChosenHeroAndWeapon();
    }

    private void playerDamageDeal(Heroes attacker, Heroes defender, Weapons weapon, boolean computerPlay) {
        GameMechanicMethods gameMechanicMethods = new GameMechanicMethods(attacker.getName(), defender.getName());

        while (true) {


            if(this.attacked) {
                System.out.println("\n\t");
                System.out.println(attacker.colorYellow + "\t\t\t" + attacker.getClass().getSimpleName() + " " + attacker.getName() + " turn: \n" + attacker.colorReset);
            }

            if(!computerPlay) {
                System.out.print("\tYour stats: ");
                attacker.statsText();
                System.out.print("\tEnemy stats: ");
                defender.statsText();
                System.out.print("\n");
            }

            attacker.attackTypeText(weapon.getFinalWeaponDamage(),computerPlay);
            attacker.attackType(computerPlay);

            if (attacker.getEnergy() >= attacker.getEnergyCost() && attacker.getMana() >= attacker.getManaCost() && attacker.getRage() >= attacker.getRageCost()) {

                attacker.setMana(attacker.getMana() - attacker.getManaCost());
                attacker.setEnergy(attacker.getEnergy() - attacker.getEnergyCost());
                attacker.setRage(attacker.getRage() - attacker.getRageCost());

                //damage
                int totalDamage = attacker.getTotalDamage();
                int totalDamageAfterAbsorb /*= 0*/;
                int totalAbsorb = defender.getAbsorbDamage() + defender.getAbsorbDamageBonus();
                int absorbDamageBonus = defender.getAbsorbDamageBonus();
                int absorbDamageBonusIncrease = attacker.getAbsorbDamageBonusIncrease();
                int weaponDamage = 0;

                //damage over time
                int[] totalDamageOverTime = {attacker.getTotalDamageOverTime()[0], attacker.getTotalDamageOverTime()[1]};
                int[] damageOverTime = {attacker.getDamageOverTime()[0], attacker.getDamageOverTime()[1]};
                int[] dotStacksCount = {attacker.getDotStacksCount()[0], attacker.getDotStacksCount()[1]};
                int[] damageAndStacksOverTime1 = new int[2];
                int[] damageAndStacksOverTime2 = new int[2];

                int energyDrain = attacker.getEnergyDrain();
                int manaDrain = attacker.getManaDrain();

                int healthRestore = 0;
                int energyRestore = 0;
                int manaRestore = 0;

                //ability over time count
                int[] dotCountTick = {attacker.getCountDotTick()[0], attacker.getCountDotTick()[1], attacker.getCountDotTick()[2]};
                boolean[] canCastDot = {attacker.isCanCastDot()[0], attacker.isCanCastDot()[1], attacker.isCanCastDot()[2]};

                //ability used
                String abilityUsed = attacker.getAbilityName()[attacker.getWhichAbilityWasUsed() - 1];
                System.out.println("\t\t" + attacker.getName() + " use ability " + attacker.colorYellow + abilityUsed + attacker.colorReset + "");

                //Critical hit double total damage.
                boolean isCriticalHit = attacker.criticalHit();
                if (isCriticalHit) {
                    totalDamage *= 2;
                }
                //Hunter kill shot double damage
                if (attacker instanceof Hunter && attacker.getWhichAbilityWasUsed() == 3 && (double) (defender.getMaxHealth() / 100) * 20 > defender.getHealth()) {
                    totalDamage *= 2;
                }
                //Warrior double damage
                if (attacker instanceof Warrior && (double) (defender.getMaxHealth() / 100) * 20 > defender.getHealth() && attacker.getRage() > 70) {
                    totalDamage *= 2;
                }

                //Paladin heal
                if (attacker instanceof Paladin && attacker.getWhichAbilityWasUsed() == 4) {
                    int[] paladinHealAndCleanse = ((Paladin) attacker).paladinHealAndCleanse(attacker.getHealth(), defender.getTotalDamageOverTime()[0], defender.getDotStacksCount()[0], attacker.getName());
                    int[] paladinHealAndCleanse1 = ((Paladin) attacker).paladinHealAndCleanse(attacker.getHealth(), defender.getTotalDamageOverTime()[1], defender.getDotStacksCount()[1], attacker.getName());
                    healthRestore += paladinHealAndCleanse[0];
                    defender.setTotalDamageOverTime((defender.getTotalDamageOverTime()[0] - paladinHealAndCleanse[1]), (defender.getTotalDamageOverTime()[1] - paladinHealAndCleanse1[1]));
                    defender.setDotStacksCount(paladinHealAndCleanse[2], paladinHealAndCleanse[2]);

                }

                //Damage over time
                for (int i = 0; i < canCastDot.length; i++) {
                    if (canCastDot[i]) {
                        dotCountTick[i] = 0;
                    }
                }

                if (damageOverTime[0] != 0 || totalDamageOverTime[0] != 0) {
                    damageAndStacksOverTime1 = gameMechanicMethods.damageOrHealOverTime(attacker.isCanCastDotStacks(), damageOverTime[0], totalDamageOverTime[0], dotStacksCount[0]);
                    totalDamageOverTime[0] = damageAndStacksOverTime1[0];
                    dotStacksCount[0] = damageAndStacksOverTime1[1];
                    dotCountTick[0]++;
                }

                if (damageOverTime[1] != 0 || totalDamageOverTime[1] != 0) {
                    damageAndStacksOverTime2 = gameMechanicMethods.damageOrHealOverTime(attacker.isCanCastDotStacks(), damageOverTime[1], totalDamageOverTime[1], dotStacksCount[1]);
                    totalDamageOverTime[1] = damageAndStacksOverTime2[0];
                    dotStacksCount[1] = damageAndStacksOverTime2[1];
                    dotCountTick[1]++;
                }

                totalDamage += totalDamageOverTime[0] + totalDamageOverTime[1];

                //Damage dealt
                if (attacker.isWeaponAttack()) {
                    totalDamage += weapon.getFinalWeaponDamage();
                    weaponDamage += weapon.getFinalWeaponDamage();

                    //Weapon special attack
                    int weaponStats = weapon.specialAttack();
                    if (weapon instanceof Bow) {
                        energyDrain += weaponStats;
                    } else if (weapon instanceof Sword) {
                        weaponDamage += weaponStats;
                        totalDamage += weaponStats;
                    } else if (weapon instanceof Axe) {
                        healthRestore += weaponStats;
                    } else if (weapon instanceof Staff) {
                        manaRestore += weaponStats;
                    }
                }

                //Warlock shield mana drain
                if (defender instanceof Warlock && absorbDamageBonus != 0 && (absorbDamageBonus - totalDamage) <= 0) {
                    int manaBurnAfterDestroyShield = ((Warlock) defender).manaDrainFromShield(attacker.getMana(), attacker.getName(), defender.getName());
                    attacker.statsRestoreValue(0, 0, -manaBurnAfterDestroyShield, 0);
                }

                //defender health calculator
                totalDamageAfterAbsorb = gameMechanicMethods.damageDeal(totalDamage, totalAbsorb, isCriticalHit, attacker.getTotalDamage());
                gameMechanicMethods.damageOverTimeTextOutput(attacker.getTotalDamage(),abilityUsed);
                gameMechanicMethods.damageOverTimeTextOutput(weaponDamage,weapon.getClass().getSimpleName());
                gameMechanicMethods.damageOverTimeTextOutput(damageAndStacksOverTime1[0],attacker.getDamageOverTimeAbilityName()[0]);
                gameMechanicMethods.damageOverTimeTextOutput(damageAndStacksOverTime2[0],attacker.getDamageOverTimeAbilityName()[1]);

                //defender absorb damage calculator
                absorbDamageBonus = gameMechanicMethods.absorbDamageBonus(absorbDamageBonus, totalDamage, totalAbsorb);


                //life steal
                if (attacker.isLifeSteal()) {
                    healthRestore += totalDamageAfterAbsorb / 4;
                }
                //Dot life steal
                if (attacker.isDotLifeSteal()) {
                    healthRestore += damageAndStacksOverTime1[0] / 10;
                }

                //Absorb damage increase
                if (attacker.getAbsorbDamageBonusIncrease() != 0) {
//                    System.out.println("\t" + attacker.getName() + " increase absorb by " + attacker.getAbsorbDamageBonusIncrease());
                    absorbDamageBonusIncrease = gameMechanicMethods.absorbDamageBonusIncrease(attacker.getAbsorbDamageBonus(), absorbDamageBonusIncrease);
                }

                //Mana steal
                int manaStealAmount = defender.getMana() / 10;
                if (attacker.isManaSteal()) {
                    manaRestore += manaStealAmount;
                    manaDrain += manaStealAmount;
                }

                //Energy and mana drain
                energyDrain = gameMechanicMethods.energyAndManaDrain(energyDrain, defender.getEnergy(), "Energy");
                manaDrain = gameMechanicMethods.energyAndManaDrain(manaDrain, defender.getMana(), "Mana");

                //Energy, health and mana restore
                healthRestore = gameMechanicMethods.healthAndManaRestore(attacker.getMaxHealth(), attacker.getHealth(), healthRestore, "Health");
                manaRestore = gameMechanicMethods.healthAndManaRestore(attacker.getMaxMana(), attacker.getMana(), manaRestore, "Mana");
                energyRestore = gameMechanicMethods.healthAndManaRestore(attacker.getMaxEnergy(), attacker.getEnergy(), energyRestore, "Energy");

                //Stats changer
                defender.damageAndStatsTakenValue(totalDamageAfterAbsorb, energyDrain, manaDrain, absorbDamageBonus);
                attacker.statsRestoreValue(healthRestore, energyRestore, manaRestore, absorbDamageBonusIncrease);

                //Damage over time stacks remove after 4 rounds
                for (int i = 0; i < totalDamageOverTime.length; i++) {
                    if (dotCountTick[i] == 4) {
                        dotStacksCount[i] = 0;
                        totalDamageOverTime[i] = 0;
                    }
                }

                System.out.println("------------------------------------------------------------------------------------------------");
                attacker.setCountDotTick(dotCountTick[0], dotCountTick[1], dotCountTick[2]);
                attacker.setTotalDamageOverTime(totalDamageOverTime[0], totalDamageOverTime[1]);
                attacker.setDotStacksCount(dotStacksCount[0], dotStacksCount[1]);
                attacker.setDamageOverTime(0, 0);
                attacker.setTotalDamage(0);
                attacker.setAbsorbDamageBonusIncrease(0);
                this.attacked = true;
                break;
            } else {
                attacked = false;
                attacker.setManaSteal(false);
                attacker.setCanCastDot(false, false, false);
                attacker.setDamageOverTime(0, 0);
                attacker.setTotalDamage(0);
                attacker.setAbsorbDamageBonusIncrease(0);
                if(!computerPlay) {
                    System.out.println(attacker.colorYellow + "You don't have enough energy, mana or rage to perform this action. Try Another action." + attacker.colorReset);
                }
            }
        }
    }

    public void setWeapons(Weapons[] weapons) {
        this.weapons = weapons;
    }

    public void attack() {

        while (true) {
            if (playerHero.getHealth() <= 0 || computerHero.getHealth() <= 0) {
                break;
            } else {
                playerDamageDeal(playerHero, computerHero, playerWeapon,false);
                playerHero.manaAndEnergyRegeneration();
            }


            if (playerHero.getHealth() <= 0 || computerHero.getHealth() <= 0) {
                break;
            } else {
                playerDamageDeal(computerHero, playerHero, computerWeapon, computerPlay);
                computerHero.manaAndEnergyRegeneration();
            }
        }

        System.out.println("***********************************");
        System.out.println("\n\n" + playerHero.getName() + " healths are: " + playerHero.getHealth());
        System.out.println(computerHero.getName() + " healths are: " + computerHero.getHealth());

        if (playerHero.getHealth() > computerHero.getHealth()) {
            System.out.println(playerHero.getName() + " Win!");
        } else if (computerHero.getHealth() > playerHero.getHealth()) {
            System.out.println(computerHero.getName() + " Win!");
        } else {
            System.out.println("No one win");
        }
    }
}