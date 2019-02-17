// Zaki Ahmad
// Period 3
// Final Project

// This program will be really hard to make
import java.util.Scanner;
import java.text.*;
public class FinalProject{
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat roundToHundreth = new DecimalFormat("0.00");

        int difficultyChoice, sideLength=0,  mineLocation1=0, mineLocation2=0, columnChoice, rowChoice, gameChecker=0;
        System.out.print("Welcome to Minesweeper!\nIn this game you start by choosing a square and hoping that there is not a mine underneath.\nIf the circle reveals a number, like 1, then that means that there is 1 mine surrounding that circle.\nTo start, choose a level of diffuculty (1 = beginner, 2 = intermediate, 3 = Pro): ");
        difficultyChoice = sc.nextInt();
        System.out.println();

        sideLength = diffchooser(difficultyChoice);
        if(sideLength==8)
            gameChecker=53;
        if(sideLength==16)
            gameChecker=224;
        if(sideLength==25)
            gameChecker=526;
        char realBoard[][] = new char[sideLength][sideLength];
        char showBoard[][] = new char[sideLength][sideLength];
        for(int x=0;x<realBoard.length;x++){ //creates the two boards
            for(int y=0;y<realBoard[x].length;y++){
                realBoard[y][x] = 'O';
                showBoard[y][x] = '⬜';
            }
        }

        mineChooser(realBoard, showBoard, difficultyChoice, sideLength);
        for(;;){ //game will start here
            for(int x=0;x<realBoard.length;x++){
                for(int y=0;y<realBoard[x].length;y++){
                    System.out.print(showBoard[y][x] + " ");
                }
                System.out.println();
            }
            System.out.print("Choose a column(from 1" + " to  " + sideLength + "): ");
            columnChoice = sc.nextInt()-1;
            if(columnChoice>sideLength || columnChoice<0){
                System.out.println("That's not a column!");
                continue;
            }
            System.out.print("Choose a row(from 1" + " to  " + sideLength + "): ");
            rowChoice = sc.nextInt()-1;
            if(rowChoice>sideLength || rowChoice<0){
                System.out.println("That's not a row!");
                continue;
            }
            System.out.println();

            if(realBoard[columnChoice][rowChoice] == '*'){ //if the player hits a mine
                for(int x=0;x<realBoard.length;x++){
                    for(int y=0;y<realBoard[x].length;y++){

                        System.out.print(realBoard[y][x] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("YOU LOSE!");
                break;
            }
            if(realBoard[columnChoice][rowChoice] != '*'){// if the player chooses a spot with no mine
                showBoard[columnChoice][rowChoice] = realBoard[columnChoice][rowChoice];
                gameChecker--;
                if(gameChecker==0){
                    for(int x=0;x<realBoard.length;x++){
                        for(int y=0;y<realBoard[x].length;y++){

                            System.out.print(realBoard[y][x] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.print("YOU WON!");
                    break;

                }
            }

        }
    }

    public static int diffchooser(int difficultyChoice){ //determines the size of the board
        int sideLength=0;
        if(difficultyChoice == 1)
            sideLength = 8;

        if(difficultyChoice == 2)
            sideLength = 16;

        if(difficultyChoice == 3)
            sideLength = 25;

        return sideLength;
    }

    public static void mineChooser(char realBoard[][], char showBoard[][], int difficultyChoice, int sideLength){ //determines the amount of mines hidden on the board
        int mineAmount=0, mineLocation1, mineLocation2;
        if(difficultyChoice == 1)
            mineAmount = 11;
        if(difficultyChoice == 2)
            mineAmount = 32;
        if(difficultyChoice == 3)
            mineAmount = 99;

        for(int x=1;x<=mineAmount;x++){ //places the mines
            mineLocation1 =  (int)(Math.random()*(sideLength-1));
            mineLocation2 =  (int)(Math.random()*(sideLength-1));

            if(realBoard[mineLocation1][mineLocation2] == '*'){
                x++;
                continue;
            }
            realBoard[mineLocation1][mineLocation2] = '*';
            numberPlacement(realBoard, showBoard,mineLocation1, mineLocation2, sideLength);
        }
        
    }

    public static void numberPlacement(char realBoard[][], char showBoard[][], int mineLocation1, int mineLocation2, int sideLength){//determines the number behind a box if there are no mines
        for(int x=0;x<realBoard.length;x++){
            for(int y=0;y<realBoard[x].length;y++){
                int mineCount=0;
                if(x==0){ //in left column
                    if(y==0){
                        if(realBoard[x][y+1]=='*')
                            mineCount++;
                        if(realBoard[x+1][y]=='*')
                            mineCount++;
                        if(realBoard[x+1][y+1]=='*')                     
                            mineCount++;
                    }
                    else{
                        if(y==(sideLength-1)){
                            if(realBoard[x][y-1]=='*')
                                mineCount++;
                            if(realBoard[x+1][y]=='*')
                                mineCount++;
                            if(realBoard[x+1][y-1]=='*')
                                mineCount++;

                        }
                        else{
                            if(realBoard[x][y-1]=='*')
                                mineCount++;
                            if(realBoard[x][y+1]=='*')
                                mineCount++;
                            if(realBoard[x+1][y]=='*')
                                mineCount++;
                            if(realBoard[x+1][y-1]=='*')
                                mineCount++;
                            if(realBoard[x+1][y+1]=='*')
                                mineCount++;

                        }
                    }
                }

                if(x==(sideLength-1)){ //in right column
                    if(y==(sideLength-1)){
                        if(realBoard[x][y-1]=='*')
                            mineCount++;
                        if(realBoard[x-1][y]=='*')
                            mineCount++;
                        if(realBoard[x-1][y-1]=='*')
                            mineCount++;
                    }
                    if(y==0){
                        if(realBoard[x][y+1]=='*')
                            mineCount++;
                        if(realBoard[x-1][y]=='*')
                            mineCount++;
                        if(realBoard[x-1][y+1]=='*')
                            mineCount++;

                    }
                    if((y<(sideLength-1)) && y>0){
                        if(realBoard[x][y+1]=='*')
                            mineCount++;
                        if(realBoard[x][y-1]=='*')
                            mineCount++;
                        if(realBoard[x-1][y]=='*')
                            mineCount++;
                        if(realBoard[x-1][y+1]=='*')
                            mineCount++;
                        if(realBoard[x-1][y-1]=='*')
                            mineCount++;

                    }
                }

                if((x>0 && x<(sideLength-1)) && y==0){ //top
                    if(realBoard[x][y+1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y+1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y+1]=='*')
                        mineCount++;

                }
                if((x>0 && x<(sideLength-1)) && y==(sideLength-1)){ //bottom
                    if(realBoard[x][y-1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y-1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y-1]=='*')
                        mineCount++;

                }
                if(x>1 && x<(sideLength-1) && (y>1 && y<(sideLength-1))){ //in the middle of the board 
                    if(realBoard[x][y+1]=='*')
                        mineCount++;
                    if(realBoard[x][y-1]=='*')  
                        mineCount++;
                    if(realBoard[x+1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y]=='*')
                        mineCount++;
                    if(realBoard[x-1][y-1]=='*')
                        mineCount++;
                    if(realBoard[x-1][y+1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y-1]=='*')
                        mineCount++;
                    if(realBoard[x+1][y+1]=='*')
                        mineCount++;
                }

                if(mineCount==1)
                    realBoard[x][y]='1';
                if(mineCount==2)
                    realBoard[x][y]='2';
                if(mineCount==3)
                    realBoard[x][y]='3';
                if(mineCount==4)
                    realBoard[x][y]='4';
                if(mineCount==5)
                    realBoard[x][y]='5';
                if(mineCount==6)
                    realBoard[x][y]='6';
                if(mineCount==7)
                    realBoard[x][y]='7';
                if(mineCount==8)
                    realBoard[x][y]='8';
            }
        }
    }
}
/*

 */