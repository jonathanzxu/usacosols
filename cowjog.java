import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowjog {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cowjog.in"));
        PrintWriter writer = new PrintWriter("cowjog.out");
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());
        long[] positions = new long[n];
        long[] speeds = new long[n];
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            positions[i] = Long.parseLong(st.nextToken());
            speeds[i] = Long.parseLong(st.nextToken());
        }
        int groups = 1;
        int leader = n-1;
        for(int i = n-2; i >= 0; i--){
            if(positions[i] + speeds[i] * t < positions[leader] + speeds[leader] * t){
                groups++;
                leader = i;
            }
        }
        writer.println(groups);
        reader.close();
        writer.close();
    }
}
