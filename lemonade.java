import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lemonade {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] cows = new int[N];
        for(int i = 0; i < N; i++)
        {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows);
        int i = N-1;
        while(i >= 0 && cows[i] >= N-i-1)
        {
            i--;
        }
        writer.println(N-1-i);
        reader.close();
        writer.close();
    }
}
