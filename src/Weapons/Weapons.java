package Weapons;

import java.util.Scanner;

public class Weapons {
    private final Scanner scanner = new Scanner(System.in);

    protected int weaponDamage;
    protected int finalWeaponDamage;
    protected int specialWeaponAttackChance;
    protected int valueOfSpecialAttack;

    public Weapons(int weaponDamage, int specialWeaponAttackChance, int valueOfSpecialAttack) {
        this.weaponDamage = weaponDamage;
        this.finalWeaponDamage = weaponDamage;
        this.specialWeaponAttackChance = specialWeaponAttackChance;
        this.valueOfSpecialAttack = valueOfSpecialAttack;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public int getFinalWeaponDamage() {
        return finalWeaponDamage;
    }

    public int specialAttack() {
        int randomChance = (int) (Math.random() * 101);
        if (randomChance <= specialWeaponAttackChance) {
            System.out.println("Special attack with " + getClass().getSimpleName());
            return valueOfSpecialAttack;
        } else {
            return 0;
        }
    }

    public void weaponChooseText() {}

}
