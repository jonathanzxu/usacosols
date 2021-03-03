import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention {
    static int[] cows;
    static int N;
    static int C;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("convention.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cows = new int[N];
        st = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++)
        {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        long lo = 0, hi = 1000000000;
        while(lo < hi)
        {
            long mid = (lo + hi)/2;
            if(solve(mid))
            {
                hi = mid;
            }
            else
            {
                lo = mid + 1;
            }
        }
        writer.println(lo);
        reader.close();
        writer.close();
    }
    static boolean solve(long maxwait)
    {
        int wait;
        int numcows;
        int first;
        int buses = 0;
        int i = 0;
        while(i < N)
        {
            first = i;
            numcows = 1;
            wait = 0;
            while(i <= N && numcows <= C && wait <= maxwait)
            {
                numcows++;
                i++;
                if(i >= N) break;
                wait = cows[i] - cows[first];
            }
            buses++;
        }
        return buses <= M;
    }
}
