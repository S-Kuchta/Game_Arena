import GameMechanic.GameMechanic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameMechanic gameMechanic = new GameMechanic();

        gameMechanic.playerHero();
        gameMechanic.computerHero();
        gameMechanic.attack();



    }
}
