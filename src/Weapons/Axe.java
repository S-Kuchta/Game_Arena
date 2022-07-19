package Weapons;

public class Axe extends Weapons{

    public Axe() {
        super(13,10,10);
    }

    @Override
    public int specialAttack() {
        return super.specialAttack();
    }

    @Override
    public void weaponChooseText() {
        super.weaponChooseText();
        System.out.println("Axe = Damage: " + this.weaponDamage + ". Axe attack have " + this.specialWeaponAttackChance + " chance to restore " + this.valueOfSpecialAttack + " attacker health.");
    }
}
