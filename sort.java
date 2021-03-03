import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class sort {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("sort.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        Integer[] arr = new Integer[N];
        Integer[] pos = new Integer[N];
        for(int i = 0; i < N; i++)
        {
            pos[i] = i;
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(pos, Comparator.comparingInt(i -> arr[i]));
        int maxdiff = 0;
        for(int i = 0; i < N; i++)
        {
            maxdiff = Math.max(maxdiff, Math.abs(pos[i] - i));
        }
        writer.println(maxdiff+1);
        reader.close();
        writer.close();
    }
}
