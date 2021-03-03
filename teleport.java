import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class teleport {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        ArrayList<breakpoint> points = new ArrayList<breakpoint>();
        int a, b;
        long total = 0;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            total += Math.abs(a - b);
            if(Math.abs(a) >= Math.abs(a-b)){}
            else if(a < 0 && a < b || a >= 0 && a >= b)
            {
                points.add(new breakpoint(0, -1));
                points.add(new breakpoint(b, 2));
                points.add(new breakpoint(2*b, -1));
            }
            else
            {
                points.add(new breakpoint(2*a, -1));
                points.add(new breakpoint(b, 2));
                points.add(new breakpoint(2*b-2*a, -1));
            }
        }
        Collections.sort(points);
        long currvalue = total;
        int lastloc = 0;
        long currslope = 0;
        long min = total;
        for(breakpoint p : points)
        {
            currvalue += currslope * (p.location - lastloc);
            currslope += p.change;
            lastloc = p.location;
            min = Math.min(min, currvalue);
        }
        writer.println(min);
        reader.close();
        writer.close();
    }
}
class breakpoint implements Comparable<breakpoint>
{
    int location, change;
    public breakpoint(int l, int c)
    {
        location = l;
        change = c;
    }
    @Override
    public int compareTo(breakpoint b)
    {
        return location - b.location;
    }
}
