import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + (x+1) + ", " + (y+1) + "]";
    }
}

class PointsAndScores {
    int score;
    Point point;

    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

class Board { 
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);
    int[][] board = new int[5][5];  // two dimensional array edited for

    public Board() {
    }

    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }



    // NEED TO CHANGE THIS SO THAT IT COVERS ALL WINNING CONDITIONS

    public boolean hasXWon() {
        // added values board[3][3] %% board[0][0] etc. so that if statement recognises extended potential winning positions.
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4] && board[0][0] == 1)
                || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[0][4] && board[3][1] == board[0][4] && board[4][0] == 1)) {
            return true;
        }
        for (int i = 0; i < 5; ++i) {   // changed so iterates for 5 indexes
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][0] && board[i][3] == board[i][0] && board[i][4] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == board[3][3] && board[0][0] == board[4][4] && board[0][0] == 2)
                || (board[0][4] == board[1][3] && board[0][4] == board[2][2] && board[0][4] == board[0][4] && board[3][1] == board[0][4] && board[4][0] == 2)) {
            return true;
        }
        for (int i = 0; i < 5; ++i) {   // changed so iterates for 4 indexes
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == board[i][0] && board[i][3] == board[i][0] && board[i][4] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == board[3][i] && board[0][i] == board[4][i] && board[0][i] == 2))) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {   // edited nested loop to go through 5 points instead of 3
            for (int j = 0; j < 5; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }
    //////////////
    public int heuristic(){
        int countofX = 0;
        int countofO = 0;
        int countofEmpty = 0;
        double total = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (board[i][j]==1)
                    countofX += 1;
                else if (board[i][j]==2)
                    countofO += 1;
                else
                    countofEmpty += 1;
            }
        }
        total = total + (Math.pow(10, countofX)) - (Math.pow(10, countofO));

        System.out.println("Total is " + total);
        return (int) total;
    }
    ///////////////
    public int getState(Point point){
    	return board[point.x][point.y];
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   
    }

    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 5; ++i) {   // edited nested loop to display 4 points instead of 3
            for (int j = 0; j < 5; ++j) {
 		if (board[i][j]==1)           
                    System.out.print("X ");
                else if (board[i][j]==2)
                    System.out.print("O ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
