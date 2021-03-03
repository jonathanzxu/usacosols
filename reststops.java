import java.io.*;
import java.util.*;

public class reststops {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int rF = Integer.parseInt(st.nextToken());
        int rB = Integer.parseInt(st.nextToken());
        long secs = rF - rB;
        int[] stops = new int[N];
        int[] taste = new int[N];
        Integer[] id = new Integer[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            stops[i] = Integer.parseInt(st.nextToken());
            taste[i] = Integer.parseInt(st.nextToken());
            id[i] = i;
        }
        Arrays.sort(id, Comparator.comparingInt(i -> stops[i]));
        Arrays.sort(stops);
        int[] tastes = new int[N];
        for(int i = 0; i < N; i++)
        {
            tastes[id[i]] = taste[i];
        }
        int max = 0;
        TreeSet<Integer> maxes = new TreeSet<Integer>();
        for(int i = N-1; i >= 0; i--)
        {
            if(tastes[i] > max)
            {
                maxes.add(i);
                max = tastes[i];
            }
        }
        int prevx = 0;
        long ans = 0;
        for(int i : maxes)
        {
            ans += secs * (stops[i] - prevx) * tastes[i];
            prevx = stops[i];
        }
        writer.println(ans);
        reader.close();
        writer.close();
    }
}