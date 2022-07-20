package Heroes;

public class Hunter extends Heroes {
    public Hunter(String name) {
        super(name, 685, 24, 11,7,0,0,175,12,13);
    }

    private final int absorbDamageBonusIncrease = ((spellDamage * 2) + damage);
    private final int[] manaCost = {0,17,35,40,25};
    @Override
    public void attackTypeText(int finalWeaponDamage, boolean computerPlay) {
        super.setAbilityName(new String[]{"Ranged Shot", "Aimed Shot", "Black Arrow", "Kill Shot", "Counterattack"});
        super.setDamageOverTimeAbilityName("Black Arrow","");
        super.attackTypeText(finalWeaponDamage, computerPlay);
        if(!computerPlay) {
            for (int i = 0; i < getAbilityName().length; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (4 + damage + finalWeaponDamage) + colorReset + "-" + colorRed + (8 + damage + finalWeaponDamage) + colorReset + ".");
                        System.out.println("\t\t\tRanged weapon attack.");
                        break;
                    case 1:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + (10 + damage + spellDamage + finalWeaponDamage) + colorReset + "-" + colorRed + (16 + damage + spellDamage + finalWeaponDamage) + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\tStrong attack.");
                        break;
                    case 2:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ".Damage: " + colorRed + (finalWeaponDamage + 5) + colorReset + ", Damage over time: " + colorRed + damage + colorReset
                                + ", Mana burn: " + colorBlue + spellDamage + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal non stackable damage over time, damage will be dealt for 4 rounds. Ability burn mana from enemy after cast.");
                        break;
                    case 3:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage " + colorRed + (25 + damage + spellDamage + finalWeaponDamage) + colorReset + "-" + colorRed + (32 + damage + spellDamage + finalWeaponDamage) + colorReset
                                + ",Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " ability deal massive damage but it's cost is high. If enemy healths are below 20%, damage is doubled.");
                        break;
                    case 4:
                        System.out.println("Enter " + (i+1) + " = " + getAbilityName()[i]
                                + ". Damage: " + colorRed + damage + colorReset
                                + ", Absorb damage bonus: " + colorCyan + absorbDamageBonusIncrease + colorReset
                                + ", Mana cost: " + colorBlueBright + manaCost[i] + colorReset + ".");
                        System.out.println("\t\t\t" + getAbilityName()[i] + " Deal damage and increase Hunter bonus absorb damage. Can be stacked.");
                        break;
                }
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
        super.setTotalDamage(super.getDamageFromAttackType() + damage + spellDamage);
        super.setManaCost(manaCost[1]);
    }

    @Override
    protected void specialAttackAbility1() {
        super.specialAttackAbility1();
        super.setCanCastDot(true,false,false);
        super.setWeaponAttack(true);
        super.setManaDrain(spellDamage);
        super.setManaCost(manaCost[2]);
        super.setDamageOverTime(damage,super.getDamageOverTime()[1]);
        super.setTotalDamage(damage/2);
    }

    @Override
    protected void specialAttackAbility2() {
        super.specialAttackAbility2();
        int randomDamageChange = (int)(Math.random() * 8);
        super.setDamageFromAttackType(randomDamageChange + 25);
        super.setTotalDamage(super.getDamageFromAttackType() + super.getDamage() + super.getSpellDamage());
        super.setWeaponAttack(true);
        super.setManaCost(manaCost[3]);
    }

    @Override
    protected void specialDefendAbility() {
        super.specialDefendAbility();
        super.setManaCost(manaCost[4]);
        super.setTotalDamage(damage);
        super.absorbDamageIncrease(absorbDamageBonusIncrease);
    }

    @Override
    protected int computerHeroAutoAttack() {
        int abilityCasted = 1;
        if(super.getCountDotTick()[0] >= 3 || super.getCountDotTick()[0] == 0) {
            abilityCasted = super.attackChanceCalculator(80,3,10,2,5,1,5,5);
        }
        if(super.getHealth() < (getHealth() / 2)) {
            abilityCasted = super.attackChanceCalculator(40,5,25,2,25,1,10,3);
        }
        if(super.getMana() <= 40) {
            abilityCasted = super.attackChanceCalculator(100,1,0,0,0,0,0,0);
        } else if(super.getMana() > 40) {
            abilityCasted = super.attackChanceCalculator(80,2,5,3,5,5,10,1);
        }
        if(super.getDefenderHealth() < (super.getDefenderMaxHealth() / 100) * 20) {
            abilityCasted = super.attackChanceCalculator(90,4,5,2,5,1,0,0);
        }
        return abilityCasted;
    }

    @Override
    public void manaAndEnergyRegeneration() {
        super.manaAndEnergyRegeneration();
    }
}
