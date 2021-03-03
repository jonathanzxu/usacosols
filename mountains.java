import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mountains {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        mountain[] peaks = new mountain[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            peaks[i] = new mountain(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(peaks);
        int maxsum = peaks[0].x + peaks[0].y;
        boolean[] removed = new boolean[N];
        int mountains = 1;
        for(int i = 1; i < N; i++) {
            if(peaks[i].x + peaks[i].y <= maxsum){
                //c
            }
            else
            {
                mountains++;
                maxsum = peaks[i].x + peaks[i].y;
            }
        }
        writer.println(mountains);
        reader.close();
        writer.close();
    }
}
class mountain implements Comparable<mountain>
{
    int x, y;
    public mountain(int a, int b)
    {
        x=a;
        y=b;
    }
    public int compareTo(mountain m)
    {
        if(x - y == m.x - m.y)
        {
            return (m.x+m.y) - (x+y);
        }
        return (x-y) - (m.x - m.y);
    }
}