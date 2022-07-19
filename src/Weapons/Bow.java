package Weapons;

public class Bow extends Weapons{

    public Bow() {
        super(12, 15,10);
    }

    @Override
    public int specialAttack() {
        return super.specialAttack();
    }

    @Override
    public void weaponChooseText() {
        super.weaponChooseText();
        System.out.println("Bow = Damage: " + this.weaponDamage + ". Bow attack have " + this.specialWeaponAttackChance + " chance to drain " + this.valueOfSpecialAttack + " energy from enemy.");
    }
}
