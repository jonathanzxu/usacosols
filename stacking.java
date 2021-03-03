import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stacking {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("stacking.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("stacking.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] prefix = new int[N+1];
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            prefix[Integer.parseInt(st.nextToken())-1]++;
            prefix[Integer.parseInt(st.nextToken())]--;
        }
        int[] arr = new int[N];
        arr[0] = prefix[0];
        for(int i = 1; i < N; i++)
        {
            arr[i] = arr[i-1] + prefix[i];
        }
        Arrays.sort(arr);
        writer.println(arr[N/2]);
        reader.close();
        writer.close();
    }
}
