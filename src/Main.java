import GameMechanic.GameMechanic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameMechanic gameMechanic = new GameMechanic();


        gameMechanic.playerHero();
        System.out.println("Do you want play against computer? yes/no");
        String howYouWantToPlay = scanner.nextLine();
        gameMechanic.computerHero(howYouWantToPlay.equals("yes"));
        gameMechanic.attack();


    }
}
