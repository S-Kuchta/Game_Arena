package Weapons;

public class Mace extends Weapons{

    public Mace() {
        super(17,10,10);
    }

    @Override
    public void weaponChooseText() {
        super.weaponChooseText();
        System.out.println("Mace = Damage: " + this.weaponDamage + ".");
    }
}
