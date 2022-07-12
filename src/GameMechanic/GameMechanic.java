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

    public GameMechanic() {
    }

    public Warrior getWarrior() {
        return new Warrior(name);
    }
    public Hunter getHunter() {
        return new Hunter(name);
    }
    public Paladin getPaladin() {
        return new Paladin(name);
    }
    public Warlock getWarlock() {
        return new Warlock(name);
    }
    public Weapons getAxe() {
        return new Axe();
    }
    public Weapons getMace() {
        return new Mace();
    }
    public Weapons getSword() {
        return new Sword();
    }
    public Weapons getBow() {
        return new Bow();
    }
    public Weapons getStaff() {
        return new Staff();
    }


    private void heroChooseText() {

        Heroes[] heroes = {getHunter(), getWarrior(), getPaladin(), getWarlock()};

        for (int i = 0; i < heroes.length; i++) {
            System.out.println("\t Enter " + (i + 1) + " = " + heroes[i].getClass().getSimpleName() + "\t(health: " + heroes[i].getHealth() + ",\t mana: " + heroes[i].getMaxMana() + ",\t damage: " + heroes[i].getDamage() + ",\t spell damage: " + heroes[i].getSpellDamage() +
                    ",\t absorb damage: " + heroes[i].getAbsorbDamage() + "\n\t\t\t\t\t\t critical hit chance: " + heroes[i].getCriticalHitChance() + "%,\t energy regeneration: " + heroes[i].getEnergyRegeneration() + ",\t mana regeneration: " + heroes[i].getManaRegeneration() + ")\n");
        }
    }

    private void weaponChooseText() {
        System.out.println("\n\tPick a weapon: ");
        System.out.println("\tEnter 1 = Sword. Damage: " + (getSword().getFinalWeaponDamage()) + ". Sword attack have 20% chance to deal double weapon damage.");
        System.out.println("\tEnter 2 = Axe. Damage: " + (getAxe().getFinalWeaponDamage()) + ". Axe attack have 10% chance to restore 10 attacker health.");
        System.out.println("\tEnter 3 = Mace. Damage: " + (getMace().getFinalWeaponDamage()) + ".");
        System.out.print("Enter number: ");
    }

    private void validValueHeroText() {
        System.out.print("Enter valid value. Enter number between 1-4: ");
    }

    private void validValueWeaponText() {
        System.out.print("Enter valid value. Enter number between 1-3: ");
    }

    public void playerHero() {
        System.out.print("\n\tEnter player 1 name: ");
        name = scanner.next();

        System.out.println("\nChoose " + name + " hero: ");

        heroChooseText();
        System.out.print("Enter number: ");
        while (true) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                validValueHeroText();
            }
            int heroSet = scanner.nextInt();
            if (heroSet <= 4 && heroSet > 0) {
                switch (heroSet) {
                    case 1:
                        playerHero = getHunter();
                        break;
                    case 2:
                        playerHero = getWarrior();
                        break;
                    case 3:
                        playerHero = getPaladin();
                        break;
                    case 4:
                        playerHero = getWarlock();
                        break;
                }
                break;
            } else {
                validValueHeroText();
            }
        }

        if (playerHero instanceof Warlock) {
            System.out.println(playerHero.getName() + " Warlock set staff. Damage: " + getStaff().getFinalWeaponDamage() + ". Staff attack have 20% chance to restore 25 attacker mana.");
            playerWeapon = new Staff();
        } else if (playerHero instanceof Hunter) {
            System.out.println(playerHero.getName() + " Hunter Set Bow. Damage: " + getBow().getFinalWeaponDamage() + ". Bow attack have 10% chance to drain 10 energy from enemy.");
            playerWeapon = new Bow();
        } else {
            weaponChooseText();
            while (true) {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    validValueWeaponText();
                }
                int weaponSet = scanner.nextInt();
                if (weaponSet >= 1 && weaponSet <= 3) {
                    switch (weaponSet) {
                        case 1:
                            playerWeapon = new Sword();
                            break;
                        case 2:
                            playerWeapon = new Axe();
                            break;
                        case 3:
                            playerWeapon = new Mace();
                            break;
                    }
                    break;
                } else {
                    validValueWeaponText();
                }
            }
        }
    }

    public void computerHero() {
        System.out.print("\n\tEnter player 2 name: ");
        name = scanner.next();

        System.out.println("\nChoose " + name + " hero: ");

        heroChooseText();
        System.out.print("Enter number: ");

        while (true) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                validValueHeroText();
            }
            int heroSet = scanner.nextInt();
            if (heroSet <= 4 && heroSet > 0) {
                switch (heroSet) {
                    case 1:
                        computerHero = getHunter();
                        break;
                    case 2:
                        computerHero = getWarrior();
                        break;
                    case 3:
                        computerHero = getPaladin();
                        break;
                    case 4:
                        computerHero = getWarlock();
                        break;
                }
                break;
            } else {
                validValueHeroText();
            }
        }

        if (computerHero instanceof Warlock) {
            System.out.println(" ");
            System.out.println(computerHero.getName() + " Warlock set staff. Damage: " + getStaff().getFinalWeaponDamage() + ". Staff attack have 20% chance to restore 25 attacker mana.");
            computerWeapon = new Staff();
        } else if (computerHero instanceof Hunter) {
            System.out.println(" ");
            System.out.println(computerHero.getName() + " Hunter Set Bow. Damage: " + getBow().getFinalWeaponDamage() + ". Bow attack have 10% chance to drain 10 energy from enemy.");
            computerWeapon = new Bow();
        } else {
            weaponChooseText();
            while (true) {
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    validValueWeaponText();
                }
                int weaponSet = scanner.nextInt();
                if (weaponSet >= 1 && weaponSet <= 3) {
                    switch (weaponSet) {
                        case 1:
                            System.out.println(computerHero.getName() + " " + computerHero.getClass().getSimpleName() + " Set Sword");
                            computerWeapon = new Sword();
                            break;
                        case 2:
                            System.out.println(computerHero.getName() + " " + computerHero.getClass().getSimpleName() + " Set Axe");
                            computerWeapon = new Axe();
                            break;
                        case 3:
                            System.out.println(computerHero.getName() + " " + computerHero.getClass().getSimpleName() + " Set Mace");
                            computerWeapon = new Mace();
                            break;
                    }
                    break;
                } else {
                    validValueWeaponText();
                }
            }
        }
    }


    private void playerDamageDeal(Heroes attacker, Heroes defender, Weapons weapon) {
        GameMechanicMethods gameMechanicMethods = new GameMechanicMethods(attacker.getName(), defender.getName());

        while (true) {
            System.out.println("\n\n\n\t" + attacker.getClass().getSimpleName() + " " + attacker.getName() + " turn: \n");

            if (attacker instanceof Warrior) {
                System.out.print(attacker.getName() + "\t\tHealths: " + attacker.getHealth()
                        + "\t\tRage: " + attacker.getRage()
                        + "\t\t Bonus absorb shield: " + attacker.getAbsorbDamageBonus());
            } else {
                System.out.print(attacker.getName() + "\t\tHealths: " + attacker.getHealth()
                        + "\t\tEnergy: " + attacker.getEnergy()
                        + "\t\tMana: " + attacker.getMana()
                        + "\t\t Bonus absorb shield: " + attacker.getAbsorbDamageBonus());
            }
            System.out.print("\n");


            attacker.attackType(weapon.getFinalWeaponDamage());
            attacker.setEnergyCost(attacker.getEnergyCost());
            attacker.setManaCost(attacker.getManaCost());


            if (attacker.getEnergy() >= attacker.getEnergyCost() && attacker.getMana() >= attacker.getManaCost() && attacker.getRage() >= attacker.getRageCost()) {
                System.out.println("\n\t\t" + attacker.getName() + " use ability " + attacker.getAbilityName()[attacker.getWhichAbilityWasUsed()] + "");

                //Critical hit double total damage.
                boolean isCriticalHit = attacker.criticalHit();
                if (isCriticalHit) {
                    attacker.setTotalDamage(attacker.getTotalDamage() * 2);
                }

                //Paladin heal
                if (attacker instanceof Paladin && attacker.getWhichAbilityWasUsed() == 3) {
                    int[] paladinHealAndCleanse = ((Paladin) attacker).paladinHealAndCleanse(attacker.getHealth(), defender.getTotalDamageOverTime(), defender.getDotStacksCount(), attacker.getName());
                    attacker.setHealth(attacker.getHealth() + paladinHealAndCleanse[0]);
                    defender.setTotalDamageOverTime(paladinHealAndCleanse[1]);
                    defender.setDotStacksCount(paladinHealAndCleanse[2]);
                }


                
                //Damage over time
                boolean canCastDot = attacker.isCanCastDot();
                int countDotTick = attacker.getCountDotTick();
                if (canCastDot) {
                    countDotTick = 0;
                    attacker.setCountDotTick(0);
                } else {
                    attacker.setCountDotTick(attacker.getCountDotTick());
                }

                int damageOverTime1 = attacker.getDamageOverTime();
                int damageOvertime2;
                if(damageOverTime1 != 0) {
                    damageOvertime2 = attacker.getDamageOverTime();
                }

                if (attacker.getDamageOverTime() != 0 || attacker.getTotalDamageOverTime() != 0) {
                    int[] damageOverTime = gameMechanicMethods.damageOrHealOverTime(attacker.isCanCastDotStacks(), attacker.getDamageOverTime(), attacker.getTotalDamageOverTime(), attacker.getDotStacksCount());

                    attacker.setDotStacksCount(damageOverTime[1]);
                    attacker.setTotalDamageOverTime(damageOverTime[0]);
                    defender.setHealth(defender.getHealth() - damageOverTime[0]);
                    System.out.println(attacker.getName() + " deal " + damageOverTime[0] + " damage to " + defender.getName() + " with damage over time ability.");


                    int dotLifeStealAmount = attacker.getTotalDamageOverTime() / 10;

                    //Dot life steal
                    if (attacker.isDotLifeSteal()) {
                        attacker.setHealth(attacker.getHealth() + gameMechanicMethods.healthAndManaRestore(attacker.getMaxHealth(), attacker.getHealth(), dotLifeStealAmount, "Health"));
                    }

                    if (attacker.getCountDotTick() == 4) {
                        attacker.setTotalDamageOverTime(0);
                        attacker.setDamageOverTime(0);
                        attacker.setCountDotTick(0);
                        attacker.setDotStacksCount(0);
                    }
                }

                //Warrior rage generator
                if (attacker instanceof Warrior) {
                    attacker.setRage(attacker.getRage() + ((Warrior) attacker).rageGenerator(attacker.getRage(), attacker.getWhichAbilityWasUsed()));
                }

                //Damage dealt
                if (attacker.getTotalDamage() == 0) {
                    System.out.print("");
                } else {
                    if (attacker.isWeaponAttack()) {
                        weapon.setWeaponDamage(weapon.getFinalWeaponDamage());

                        //Weapon special attack
                        int weaponStats = weapon.specialAttack();
                        if (weapon instanceof Bow) {
                            attacker.setEnergyDrain(attacker.getEnergyDrain() + weaponStats);
                        } else if (weapon instanceof Sword) {
                            weapon.setWeaponDamage(weapon.getWeaponDamage() + weaponStats);
                        } else if (weapon instanceof Axe) {
                            gameMechanicMethods.healthAndManaRestore(attacker.getMaxHealth(), attacker.getHealth(), weaponStats, "Health");
                        } else if (weapon instanceof Staff) {
                            gameMechanicMethods.healthAndManaRestore(attacker.getMaxMana(), attacker.getMana(), weaponStats, "Mana");
                        }
                    } else {
                        weapon.setWeaponDamage(0);
                    }


                    //Hunter kill shot double damage
                    if (attacker instanceof Hunter && attacker.getWhichAbilityWasUsed() == 3 && (double) (defender.getMaxHealth() / 100) * 20 > defender.getHealth()) {
                            attacker.setTotalDamage(attacker.getTotalDamage() * 2);
                    }

                    //Warrior double damage
                    if (attacker instanceof Warrior && (double) (defender.getMaxHealth() / 100) * 20 > defender.getHealth() && attacker.getRage() > 70) {
                            attacker.setTotalDamage(attacker.getTotalDamage() * 2);
                    }

                    int totalAbsorb = defender.getAbsorbDamage() + defender.getAbsorbDamageBonus();
                    int totalDamage = attacker.getTotalDamage() + weapon.getWeaponDamage();
                    int totalDamageAfterAbsorb = ((attacker.getTotalDamage() + weapon.getWeaponDamage()) - totalAbsorb);
                    int lifeStealAmount = totalDamageAfterAbsorb / 4;

                    if (lifeStealAmount <= 0) {
                        lifeStealAmount = 0;
                    }

                    //Warlock shield mana drain
                    if (defender instanceof Warlock && defender.getAbsorbDamageBonus() != 0 && (defender.getAbsorbDamageBonus() - totalDamage) <= 0) {
                        attacker.setMana(attacker.getMana() - ((Warlock) defender).manaDrainFromShield(attacker.getMana(), attacker.getName(), defender.getName()));
                    }

                    //health calculator
                    if (totalDamage > totalAbsorb) {
                        defender.setHealth(defender.getHealth() - totalDamageAfterAbsorb);
                        System.out.print(attacker.getName() + " deal: " + totalDamage + " damage.\t\t");
                        System.out.print(defender.getName() + " absorb " + totalAbsorb + " damage.\t\t");
                        System.out.print(attacker.getName() + " deal " + (totalDamage - totalAbsorb) + " damage after absorbing damage by " + defender.getName() + "\n");
                    } else {
                        defender.setHealth(defender.getHealth());
                        System.out.print(attacker.getName() + " deal " + totalDamage + " damage.\t\t");
                        System.out.print(defender.getName() + " absorb " + totalDamage + " damage.\t\t");
                        System.out.print(attacker.getName() + " deal 0 damage after absorbing damage by " + defender.getName() + "\n");
                    }
                    if (isCriticalHit) {
                        System.out.println("Critical Hit!");
                    }

                    //life steal
                    if (attacker.isLifeSteal()) {
                        attacker.setHealth(attacker.getHealth() + gameMechanicMethods.healthAndManaRestore(attacker.getMaxHealth(), attacker.getHealth(), lifeStealAmount, "Health"));
                    }

                    //absorb damage
                    if (defender.getAbsorbDamageBonus() != 0 && defender.getAbsorbDamageBonus() < totalDamage) {
                        defender.setAbsorbDamageBonus(0);
                    } else if (defender.getAbsorbDamageBonus() != 0 && defender.getAbsorbDamageBonus() > totalDamage) {
                        defender.setAbsorbDamageBonus(totalAbsorb - totalDamage);
                    }
                }

                //Energy and mana drain
                if (attacker.getEnergyDrain() != 0) {
                    defender.setEnergy(defender.getEnergy() - gameMechanicMethods.energyAndManaDrain(attacker.getEnergyDrain(), defender.getEnergy(), "Energy"));
                }
                if (attacker.getManaDrain() != 0) {
                    defender.setMana(defender.getMana() - gameMechanicMethods.energyAndManaDrain(attacker.getManaDrain(), defender.getMana(), "Mana"));
                }


                //Absorb damage increase
                if (attacker.getAbsorbDamageBonusIncrease() != 0) {
                    System.out.println(attacker.getName() + " increase absorb by " + attacker.getAbsorbDamageBonusIncrease());
                    attacker.setAbsorbDamageBonus(attacker.getAbsorbDamageBonus() + attacker.getAbsorbDamageBonusIncrease());
                    attacker.setAbsorbDamageBonusIncrease(0);
                }


                attacker.setCanCastDot(false);
                attacker.setDamageOverTime(0);
                attacker.setTotalDamage(0);
                attacker.setManaDrain(0);
                attacker.setEnergyDrain(0);
                attacker.setAbsorbDamageBonusIncrease(0);

                attacker.setMana(attacker.getMana() - attacker.getManaCost());
                attacker.setEnergy(attacker.getEnergy() - attacker.getEnergyCost());
                attacker.setRage(attacker.getRage() - attacker.getRageCost());
                attacker.setCountDotTick(attacker.getCountDotTick() + 1);

                break;
            } else {
                attacker.setCanCastDot(false);
                attacker.setDamageOverTime(0);
                attacker.setTotalDamage(0);
                attacker.setManaDrain(0);
                attacker.setEnergyDrain(0);
                attacker.setAbsorbDamageBonusIncrease(0);
                System.out.println("You don't have enough energy, mana or rage to perform this action. Try Another action.");
            }
        }

    }


    public void attack() {

        while (true) {
            if (playerHero.getHealth() <= 0 || computerHero.getHealth() <= 0) {
                break;
            } else {
                playerDamageDeal(playerHero, computerHero, playerWeapon);
                playerHero.manaAndEnergyRegeneration();
            }


            if (playerHero.getHealth() <= 0 || computerHero.getHealth() <= 0) {
                break;
            } else {
                playerDamageDeal(computerHero, playerHero, computerWeapon);
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