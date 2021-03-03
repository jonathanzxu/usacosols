import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class rectpasture {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        int[] x = new int[N];
        int[] y = new int[N];
        Integer[] cows = new Integer[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            cows[i] = i;
        }
        //compressing x
        Arrays.sort(cows, Comparator.comparingInt(i -> x[i]));
        for(int i = 0; i < N; i++)
        {
            x[cows[i]] = i+1;
        }
        //compress y
        Arrays.sort(cows, Comparator.comparingInt(i -> y[i]));
        for(int i = 0; i < N; i++)
        {
            y[cows[i]] = i+1;
        }
        //create initial matrix
        int[][] prefix = new int[N+1][N+1];
        for(int i = 0; i < N; i++)
        {
            prefix[x[i]][y[i]] = 1;
        }
        //prefix calc
        for(int i = 1; i < N+1; i++)
        {
            for(int j = 1; j < N+1; j++)
            {
                prefix[i][j] = prefix[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        //problem calc
        long sets = N + 1;
        for(int i = 0; i < N; i++)
        {
            for(int j = i+1; j < N; j++)
            {
                sets += getSum(prefix, 1, Math.min(x[i], x[j]), Math.min(y[i], y[j]), Math.max(y[i], y[j])) * getSum(prefix, Math.max(x[i], x[j]), N, Math.min(y[i], y[j]), Math.max(y[i], y[j]));
            }
        }
        System.out.println(sets);
        reader.close();
        //writer.close();
    }
    static int getSum(int[][] prefix, int fromx, int tox, int fromy, int toy)
    {
        return prefix[tox][toy] - prefix[fromx-1][toy] - prefix[tox][fromy-1] + prefix[fromx-1][fromy-1];
    }
}
