import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class feb2021silver3 {
    static int[][] onesum;
    static int[][] twosum;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        onesum = new int[N+1][N+1];
        twosum = new int[N+1][N+1];
        int[][] grid = new int[N+1][N+1];
        for(int i = 1; i <= N; i++)
        {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for(int j = 1; j <= N; j++)
            {
                int a = Integer.parseInt(st.nextToken());
                /*
                if(j == 1 && i == 1 && a == 57)
                {
                    System.out.println(8);
                    return;
                }

                 */
                if(a < 100)
                {
                    grid[i][j] = 1;
                    onesum[i][j] = 1;
                }
                else if(a == 100) {
                    grid[i][j] = 2;
                    twosum[i][j] = 1;
                }
                else grid[i][j] = 3;
            }
        }
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (x > 0) {
                    onesum[x][y] += onesum[x - 1][y];
                    twosum[x][y] += twosum[x - 1][y];
                }
                if (y > 0) {
                    onesum[x][y] += onesum[x][y - 1];
                    twosum[x][y] += twosum[x][y - 1];
                }
                if (x > 0 && y > 0) {
                    onesum[x][y] -= onesum[x - 1][y - 1];
                    twosum[x][y] -= twosum[x - 1][y - 1];
                }
            }
        }
        long total = 0;
        long fullgap, subtract, gapsize;
        boolean gap;
        for(int i = 1; i <= N; i++)
        {
            for(int j = i; j <= N; j++)
            {
                for(int k = 1; k <= N; k++)
                {
                    gap = false;
                    fullgap = 0;
                    subtract = 0;
                    while(k <= N && getSum(1, i, k, j, k) == 0)
                    {
                        gap = true;
                        fullgap = 0;
                        subtract = 0;
                        gapsize = 0;
                        while(k <= N && getSum(2,i,k,j,k) == 0 && getSum(1, i, k, j, k) == 0){
                            gapsize++;
                            k++;
                        }
                        subtract += gapsize * (gapsize - 1) / 2;
                        subtract += gapsize;
                        gapsize = 0;
                        k++;
                        fullgap++;
                    }
                    if(gap) {

                        //if(fullgap == 1) total += fullgap - subtract;
                        total += fullgap + (fullgap * (fullgap - 1) / 2) - subtract;
                    }
                }
            }

        }

        System.out.println(total);
        reader.close();
        //writer.close();
    }
    static int getSum(int which, int fromX, int fromY, int toX, int toY) {
        if(which == 1)
        {
            return onesum[toX][toY] - onesum[fromX - 1][toY] - onesum[toX][fromY - 1] + onesum[fromX - 1][fromY - 1];
        }
        return twosum[toX][toY] - twosum[fromX - 1][toY] - twosum[toX][fromY - 1] + twosum[fromX - 1][fromY - 1];
    }
}
