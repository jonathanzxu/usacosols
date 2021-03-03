import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lifeguards {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        lg[] guards = new lg[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            guards[i] = new lg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(guards);
        int curr = 1;
        int start = guards[0].start;
        int end = guards[0].end;
        int total = end - start;
        int minalone;
        if(guards[1].start < guards[0].end)
        {
            minalone = guards[0].end - guards[1].start;
        } else minalone = guards[0].end - guards[0].start;
        int length;
        while(curr < N-1)
        {
            length = guards[curr].end - guards[curr].start;
            total += length;
            if(guards[curr].start < guards[curr-1].end) {
                if (guards[curr].end <= guards[curr - 1].end) {
                    minalone = 0;
                    total -= length;
                }
                else {
                    length -= guards[curr - 1].end - guards[curr].start;
                    total -= guards[curr - 1].end - guards[curr].start;
                }
            }
            if(guards[curr+1].start < guards[curr].end)
            {
                length -= guards[curr].end - guards[curr+1].start;
            }
            minalone = Math.min(minalone, length);
            curr++;
        }
        total += guards[curr].end - guards[curr].start;
        if(guards[curr].start < guards[curr-1].end){
            if(guards[curr].end <= guards[curr-1].end) {
                minalone = 0;
                total -= guards[curr].end - guards[curr].start;
            }
            else {
                minalone = Math.min(minalone, guards[curr].end - guards[curr].start - guards[curr-1].end + guards[curr].start);
                total -= guards[curr - 1].end - guards[curr].start;
            }
        }
        writer.println(total - minalone);
        reader.close();
        writer.close();
    }
}
class lg implements Comparable<lg>
{
    int start, end;
    public lg(int s, int e)
    {
        start = s;
        end = e;
    }
    @Override
    public int compareTo(lg l)
    {
        return start - l.start;
    }
}