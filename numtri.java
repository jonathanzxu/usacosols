/*
ID: j.jonny1
LANG: JAVA
TASK: numtri
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class numtri {
    //public static boolean[][] visited;
    //public static int max = 0;
    //public static int numtimes = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st;
        int n = Integer.parseInt(reader.readLine());
        int[][] triangle = new int[n][n];
        int[][] dp = new int[n][n];
        //visited = new boolean[n][n];
        for(int i = 1; i <= n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < i; j++)
            {
                triangle[i-1][j] = Integer.parseInt(st.nextToken());
                if(i == n)
                {
                    dp[i-1][j] = triangle[i-1][j];
                }
            }
        }
        for(int i = n-2; i >= 0; i--)
        {
            for(int j = 0; j <= i; j++)
            {
                dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j];
            }
        }
        writer.println(dp[0][0]);
        //writer.println(numtimes);
        reader.close();
        writer.close();
    }
    /*
    public static void solve(int sum, int pos, int row, int n, int[][] triangle)
    {
        numtimes++;
        sum += triangle[row][pos];
        visited[row][pos] = true;
        if(row == n-1) {
            max = Math.max(max, sum);
            return;
        }
        if(!visited[pos][row+1]) {
            solve(sum, pos, row + 1, n, triangle);
        }
        if(!visited[pos+1][row+1]) {
            solve(sum, pos + 1, row + 1, n, triangle);
        }
    }
    
     */
}
