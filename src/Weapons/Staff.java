package Weapons;

public class Staff extends Weapons{
    public Staff() {
        super(4,100,25);
    }

    @Override
    public int specialAttack() {
        return super.specialAttack();
    }

    @Override
    public void weaponChooseText() {
        super.weaponChooseText();
        System.out.println("Staff = Damage: " + this.weaponDamage + ". Staff attack have " + this.specialWeaponAttackChance + " chance to restore " + this.valueOfSpecialAttack + " attacker mana.");
    }
}
