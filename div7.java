import java.io.*;

public class div7 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("div7.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        long[] prefix = new long[N];
        prefix[0] = Integer.parseInt(reader.readLine());
        for(int i = 1; i < N; i++)
        {
            prefix[i] = prefix[i-1] + Integer.parseInt(reader.readLine());
        }
        long[] mins = new long[7];
        long[] maxs = new long[7];
        for(int i = 0; i < 7; i++)
        {
            mins[i] = -1;
            maxs[i] = -1;
        }
        for(int i = 0; i < N; i++) {
            if(mins[(int) (prefix[i]%7)] == -1) mins[(int) (prefix[i]%7)] = i;
            maxs[(int) (prefix[i]%7)] = i;
        }
        long max = maxs[0] - mins[0];
        for(int i = 0; i < 7; i++)
        {
            max = Math.max(max, maxs[i] - mins[i]);
        }
        writer.println(max);
        reader.close();
        writer.close();
    }
}
