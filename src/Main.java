public class Main {

    public static void main(String[] args) {
        int x [][]=new int[3][3];
        int y [][]=new int[3][3];
        int matrix [][]= new int[3][3];
        int matrix_aux[][]  = matrix;
        int count = 0;
        x[0][1]=1;
        x[0][0]=1;
        x[1][1]=1;
        x[2][2]=1;
        y[0][0]=1;
        y[1][0]=1;

        count = Counting(x);

        PrintMatrix(matrix);

        matrix_aux = Matrix_figure(matrix_aux,x,matrix);

        PrintMatrix(matrix_aux);

        matrix_aux= Clean_matrix(matrix_aux);

        PrintMatrix(matrix_aux);

        matrix_aux = Matrix_figure(matrix_aux,y,matrix);

        PrintMatrix(matrix_aux);

    }
    public static int [][] Matrix_figure(int matrix_aux[][], int x [][], int matrix[][] )
    {
        while(Counting(x)!= 0) {
            Searchloop:
            {
                int position_matrix[] = Matrix_position(matrix_aux, 0); //num is to know what is what they are looking for
                int position_figure[] = Matrix_position(x, 1);

                //Go through the x axis to the right
                for (int a = 0; (a + position_matrix[0] < matrix.length) && (a + position_figure[0] < matrix.length); a++) {
                    if (matrix_aux[a + position_matrix[0]][position_matrix[1]] == 0 && x[a + position_figure[0]][position_figure[1]] == 1) {
                        if (CheckOtherPositions(matrix_aux, a + position_matrix[0], position_matrix[1], x, a + position_figure[0], position_figure[1])) //check if the other positions are okey
                        {
                            matrix_aux[a + position_matrix[0]][position_matrix[1]] = 2;
                            x[a + position_figure[0]][position_figure[1]] = 2;
                            break Searchloop;
                        }
                    }
                }

                //Go through the x axis to the left
                for (int a1 = 0; (position_matrix[0] - a1 < 0) && (position_figure[0] - a1 < 0); a1++) {
                    if (matrix_aux[position_matrix[0] - a1][position_matrix[1]] == 0 && x[position_figure[0] - a1][position_figure[1]] == 1) {
                        if (CheckOtherPositions(matrix_aux, position_matrix[0] - a1, position_matrix[1], x, position_figure[0] - a1, position_figure[1])) {
                            matrix_aux[position_matrix[0] - a1][position_matrix[1]] = 2;
                            x[position_figure[0] - a1][position_figure[1]] = 2;
                            break Searchloop;
                        }
                    }
                }

                //Go through the y axis right
                for (int b = 0; (b + position_matrix[1] < matrix[0].length) && (b + position_figure[1] < matrix[0].length); b++) {
                    if (matrix_aux[position_matrix[0]][b + position_matrix[1]] == 0 && x[position_figure[0]][b + position_figure[1]] == 1) {
                        if (CheckOtherPositions(matrix_aux, position_matrix[0], b + position_matrix[1], x, position_figure[0], b + position_figure[1])) {
                            matrix_aux[position_matrix[0]][b + position_matrix[1]] = 2;
                            x[position_figure[0]][b + position_figure[1]] = 2;
                            break Searchloop;
                        }
                    }
                }
                //Go through the y axis left
                for (int b1 = 0; (position_matrix[1] - b1 > 0) && (position_figure[1] - b1 > 0); b1++) {
                    if (matrix_aux[position_matrix[0]][position_matrix[1] - b1] == 0 && x[position_figure[0]][position_figure[1] - b1] == 1) {
                        if (CheckOtherPositions(matrix_aux, position_matrix[0], position_matrix[1] - b1, x, position_figure[0], position_figure[1] - b1)) {
                            matrix_aux[position_matrix[0]][position_matrix[1] - b1] = 2;
                            x[position_figure[0]][position_figure[1] - b1] = 2;
                            break Searchloop;
                        }
                    }
                }
                matrix_aux[position_matrix[0]][position_matrix[1]]=3;
            }
        }

        return matrix_aux;
    }
    public static int[] Matrix_position (int matrix_aux [][], int num)
    {
        int position [] = new int [2];
        for (int x= 0; x < matrix_aux.length; x++)
        {
            for(int y=0; y< matrix_aux[0].length; y++)
            {
                if (matrix_aux[x][y] == num ) {
                    position[0] = x;
                    position[1]=y;
                    return position;
                }
            }
        }
        return null;
    }

    public static void PrintMatrix (int matrix_print[][])
    {
        for (int i = 0; i < matrix_print.length; i++) {
            for (int j = 0; j < matrix_print[i].length; j++) {
                System.out.print(matrix_print[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int Counting (int matrix_aux [][])
    {
        int count = 0;
        for (int x= 0; x < matrix_aux.length; x++)
        {
            for(int y=0; y< matrix_aux[0].length; y++)
            {
                if (matrix_aux[x][y] == 1)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean CheckOtherPositions (int matrix [][], int posX_matrix, int posY_matrix, int figure [][], int posX_figure, int posY_figure)
    {
        for(int a= 0; a < figure.length;a++)
        {
            for(int b =0; b < figure[0].length;b++)
            {
                if(figure[a][b] == 1 || figure[a][b]==2 )
                {
                    try{
                        if(matrix[(a-posX_figure)+posX_matrix][(b-posY_figure)+posY_matrix] == 0 || matrix[(a-posX_figure)+posX_matrix][(b-posY_figure)+posY_matrix] == 3|| matrix[(a-posX_figure)+posX_matrix][(b-posY_figure)+posY_matrix] == 2)
                        {
                            return true;
                        }
                        else{
                            return false;
                        }

                    }catch (IndexOutOfBoundsException e)
                    {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static int[][] Clean_matrix (int matrix [][])
    {
        for (int x= 0; x < matrix.length; x++)
        {
            for(int y=0; y< matrix[0].length; y++)
            {
                if (matrix[x][y] == 3)
                {
                    matrix[x][y]=0;
                }
            }
        }
        return matrix;
    }


}
