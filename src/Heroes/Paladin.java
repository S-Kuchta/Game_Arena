package Heroes;

public class Paladin extends Heroes {
    public Paladin(String name) {
        super(name, 720, 8, 17,12,120,8,230,14,5);
    }
    @Override
    public void attackTypeText(int finalWeaponDamage) {
        super.setAbilityName(new String[]{"Normal Attack", "Divine Storm", "Holy Shock", "Flash Heal", "Holy Shield", "Skip"});
        super.attackTypeText(finalWeaponDamage);
        for(int i = 0;i<getAbilityName().length;i++) {
            switch(i) {
                case 0:
                    System.out.println("Enter 1 = " + getAbilityName()[i] + ". Damage: " + (4 + super.getDamage() + finalWeaponDamage) + "-" + (8 + super.getDamage() + finalWeaponDamage) + ", Energy Cost: " + super.getEnergyCost());
                    System.out.println("\t\t\tNormal weapon attack.");
                    break;
                case 1:
                    System.out.println("Enter 2 = " + getAbilityName()[i] + ". Damage: " + (10 + super.getDamage() + finalWeaponDamage) + "-" + (16 + super.getDamage() + finalWeaponDamage) + ", Energy Cost: " + super.getEnergyCost());
                    System.out.println("\t\t\tStrong weapon attack.");
                    break;
                case 2:
                    System.out.println("Enter 3 = " + getAbilityName()[i] + ". Damage: " + (22 + super.getSpellDamage() + finalWeaponDamage) + "-" + (31 + super.getSpellDamage() + finalWeaponDamage) + ", Damage over time: 7, Mana Cost: 45.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal holy damage. Additional deal damage over time, damage will be dealt for 4 rounds.");
                    break;
                case 3:
                    System.out.println("Enter 4 = " + getAbilityName()[i] + ". No damage dealt. Heal: " + super.getSpellDamage() + ", Mana Cost: 40");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " ability heal Paladin for " + super.getSpellDamage() + ". Also Paladin cleanse self from 2 stacks damage over time.");
                    break;
                case 4:
                    System.out.println("Enter 5 = " + getAbilityName()[i] + ". Absorb damage bonus: 27, Mana cost: 40.");
                    System.out.println("\t\t\t" + getAbilityName()[i] + " increase absorb damage by 27. Can be stacked.");
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
        super.setWeaponAttack(true);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        super.setWeaponAttack(true);
        int randomDamageChange = (int)(Math.random() * 10);
        super.setDamageFromAttackType(randomDamageChange + 22);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getSpellDamage());
        super.setDamageOverTime(7);
        super.setManaCost(45);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        super.setWeaponAttack(false);
        super.setLifeSteal(false);
        super.setManaCost(40);
        super.setEnergyCost(0);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(40);
        super.setEnergyCost(0);
        super.setAbsorbDamageBonusIncrease(27);
    }

    public int[] paladinHealAndCleanse(int attackerHealth, int defenderTotalDamage, int defenderStacks, String attackerName) {
        int[] paladinHealAndStacks = new int[3];
        paladinHealAndStacks[0] = attackerHealth;
        paladinHealAndStacks[1] = defenderTotalDamage;
        paladinHealAndStacks[2] = defenderStacks;

            if (paladinHealAndStacks[2] <= 2) {
                paladinHealAndStacks[2] = 0;
                paladinHealAndStacks[1] = 0;
            } else if (paladinHealAndStacks[2] == 3) {
                paladinHealAndStacks[2] -= 2;
                paladinHealAndStacks[1] /= 3;
            } else {
                paladinHealAndStacks[2] -= 2;
                paladinHealAndStacks[1] /= 2;
            }

            if (super.getMaxHealth() - attackerHealth > super.getSpellDamage()) {
                System.out.println(attackerName + " self healed for " + super.getSpellDamage());
                paladinHealAndStacks[0] = super.getSpellDamage();
            } else {
                System.out.println(attackerName + " self healed for " + (super.getMaxHealth() - attackerHealth));
                paladinHealAndStacks[0] = super.getMaxHealth() - attackerHealth;
            }
            return paladinHealAndStacks;
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
