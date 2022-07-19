package Weapons;

public class Sword extends Weapons {

    public Sword() {
        super(10, 10, 10);
    }

    @Override
    public int specialAttack() {
        return super.specialAttack();
    }

    @Override
    public void weaponChooseText() {
        super.weaponChooseText();
        System.out.println("Sword = Damage: " + this.weaponDamage + ". Sword attack have " + this.specialWeaponAttackChance + " chance to deal additional " + this.valueOfSpecialAttack + " damage.");
    }
}
