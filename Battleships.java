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
        sea = Sea(sea);

        //deploy the ships
        DeployShips(sea);

        //print out the updated sea grid
        Sea(sea);
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
                }
            }
            System.out.println("|" + row);//prints right side border and row number
        }
        System.out.println("  0123456789  ");//bottom row
        return sea;
    }

    public static char[][] DeployShips(char sea[][]) {
        Scanner input = new Scanner(System.in);

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
            while (sea[x][y] != 0) { //need to add an OR || statement for values outside of the 10x10 grid
                System.out.println("That position is occupied.  Please choose another position.");
                System.out.print("Enter X coordinate for your " + playerShip + " ship: ");
                x = input.nextInt();
                System.out.print("Enter Y coordinate for your " + playerShip + " ship: ");
                y = input.nextInt();
            }
            //Store position as a value of '1' in the array but later will print out as '@' on the grid
            sea[x][y] = '1';
        }
        System.out.println();
        input.close();
        return sea;
    }
    //need to randomly deploy computer's ships

    //set up a while loop for the game for the battle

    //set up the the end of game

}