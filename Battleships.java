import java.util.Scanner;

public class Battleships {

    public static void main (String[] args) {
        System.out.println("**** Welcome to the Battleships game! ****");
        System.out.println();
        System.out.println("Right now the sea is empty...");
        System.out.println();
        //initialize the empty sea
        char[][] sea = new char[10][10];

        //print out the sea grid and set it to a variable
        Sea(sea);

        //deploy the ships
        DeployShips(sea);

        //print out the updated sea grid
        Sea(sea);
        DeployComputerShips(sea);
        char gameOn = '0';
        Sea(sea);

        while (gameOn == '0'){
            PlayerAttack(sea);
            Sea(sea);
            ComputerAttack(sea);
            Sea(sea);

            gameOn = WinOrLose(sea);
        }

        if (gameOn == '1'){
            System.out.println("Hooray!  You've won the battle!");
        } else if (gameOn == '2') {
            System.out.println("Sorry, you've lost the battle...");
        }
    }

    public static char[][] Sea (char sea[][]) {
        //lay out the sea grid
        System.out.println("  0123456789  ");//top row
        for (int row = 0; row < sea.length; row++) {
            System.out.print(row + "|");//prints row number and left side border
            for (int col = 0; col < sea[row].length; col++) {
                if (sea[row][col] == 0) {
                    System.out.print(" ");//goes across row printing spaces where there is no character
                } else if (sea[row][col] == '1') {
                    System.out.print('@');//print out player ship positions as '@' on the sea grid
                } else if (sea[row][col] == '2') {
                    System.out.print(" ");//print out computer ship positions as '%' on the sea grid
                } else {
                    System.out.print(sea[row][col]);
                }
            }
            System.out.println("|" + row);//prints right side border and row number
        }
        System.out.println("  0123456789  ");//bottom row
        System.out.println();
        return sea;
    }

    public static char[][] DeployShips(char sea[][]) {
        Scanner input = new Scanner(System.in);
        //Revisit this code in the future.  The following issue has not been covered in the course
        //yet and having a difficult time getting it to prevent non-numeric characters
        //STILL NEED TO PREVENT THE ENTRY OF NON-NUMERIC CHARACTERS TRY SOMETHING LIKE:
        //while(!x.matches("[0-9]+")) - CAN'T GET THIS TO WORK...
        for (int playerShip = 1; playerShip < 6; playerShip++) {
            System.out.print("Enter X coordinate for your " + playerShip + " ship: ");
            int x = input.nextInt();
            while (x > 9 || x < 0) {
                System.out.println("Cannot place outside of the sea grid.  Please choose a x-value between 0-9.");
                x = input.nextInt();
            }
            System.out.print("Enter Y coordinate for your " + playerShip + " ship: ");
            int y = input.nextInt();
            while (y > 9 || y < 0) {
                System.out.println("Cannot place outside of the sea grid.  Please choose a y-value between 0-9.");
                y = input.nextInt();
            }

            //update 2D array (sea) here with ship ('1') at (x,y).  This will be done for each ship
            //have a while loop that if (x,y) position != 0 to try another location, ship is already there
            while (sea[y][x] != 0) { //need to add an OR || statement for values outside of the 10x10 grid
                System.out.println("That position is occupied.  Please choose another position.");
                System.out.print("Enter X coordinate for your " + playerShip + " ship: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your " + playerShip + " ship: ");
                y = input.nextInt();
            }
            //Store position as a value of '1' in the array but later will print out as '@' on the grid
            sea[y][x] = '1';
        }
        System.out.println();
        //input.close();
        return sea;
    }

    public static char[][] DeployComputerShips(char sea[][]) {
        for (int computerShip = 1; computerShip < 6; computerShip++) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);
            while (sea[y][x] != 0){
                x = (int)(Math.random() * 10);
                y = (int)(Math.random() * 10);
            }
            sea[y][x] = '2';
            System.out.println("Computer ship " + computerShip + " is deployed.");
        }
        System.out.println();
        return sea;
    }

    public static char[][] PlayerAttack(char sea[][]) {
        Scanner attackInput = new Scanner(System.in);

        System.out.println("YOUR TURN");

        System.out.print("Enter X coordinate to attack!");
        int x = attackInput.nextInt();
        while (x > 9 || x < 0) {
            System.out.println("Cannot attack outside of the sea grid.  Please choose a x-value between 0-9.");
            x = attackInput.nextInt();
        }

        System.out.print("Enter Y coordinate to attack!");
        int y = attackInput.nextInt();
        while (y > 9 || y < 0) {
            System.out.println("Cannot attack outside of the sea grid.  Please choose a y-value between 0-9.");
            y = attackInput.nextInt();
        }

        if (sea[y][x] == '2') {
            System.out.println("Boom!!!  You sunk the computer's ship!");
            sea[y][x] = '!';
        } else if (sea[y][x] == '1') {
            System.out.println("Oh no, you sunk your own ship!");
            sea[y][x] = 'X';
        } else {
            System.out.println("Sorry, you missed.");
            sea[y][x] = '-';
        }

        //attackInput.close();
        System.out.println();
        return sea;
    }

    public static char[][] ComputerAttack(char sea[][]) {
        System.out.println("COMPUTER'S TURN");

        int x = (int)(Math.random() * 10);
        int y = (int)(Math.random() * 10);

        if (sea[y][x] == '2') {
            System.out.println("The Computer sunk its own ship!!!");
            sea[y][x] = '!';
        } else if (sea[y][x] == '1') {
            System.out.println("The Computer sunk one of your ships!!!");
            sea[y][x] = 'X';
        } else {
            System.out.println("The Computer missed.");
            //sea[y][x] = 'O';
        }
        return sea;
    }

    public static char WinOrLose(char sea[][]){
        char gameOn;
        int playerShips = 0;
        int computerShips = 0;

        //METHOD TO RETURN REMAINING SHIP COUNT THAT WILL RETURN GAMEON VALUE
        for (int row = 0; row < sea.length; row++) {
            for (int col = 0; col < sea[row].length; col++) {
                if (sea[row][col] == '1') {
                    playerShips++;
                } else if (sea[row][col] == '2') {
                    computerShips++;
                }
            }
        }

        //ALSO PRINT LINE AT BOTTOM  Your Ships: 5 | Computer Ships: 5
        System.out.println("Your Ships: " + playerShips + " | Computer Ships: " + computerShips);
        System.out.println();

        if (computerShips == 0) {
            gameOn = '1';
        } else if (playerShips == 0) {
            gameOn = '2';
        } else {
            gameOn = '0';
        }
        return gameOn;
    }
}