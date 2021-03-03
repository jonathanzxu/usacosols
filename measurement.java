import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Comparable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.StringTokenizer;
import java.util.TreeSet;

//test
public class measurement {
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader("measurement.in"));
        //Scanner reader = new Scanner(System.in);
        PrintWriter writer = new PrintWriter("measurement.out");
        StringTokenizer st = new StringTokenizer(reader.readLine());
        //StringTokenizer st = new StringTokenizer(reader.nextLine());
        int measures = Integer.parseInt(st.nextToken());
        int[] cows = new int[(int) 1e9];
        TreeSet<Log> logs = new TreeSet<Log>();
        for(int i = 0; i < measures; i++)
        {
            st = new StringTokenizer(reader.readLine());
            //st = new StringTokenizer(reader.nextLine());
            logs.add(new Log(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int changes = 0;
        int maxout = 0;
        TreeSet<Integer> maxcows = new TreeSet<Integer>();
        for(Log l : logs)
        {
            cows[l.cow] += l.change;
            if(maxcows.contains(l.cow))
            {
                if(cows[l.cow] < maxout)
                {
                    maxcows.remove(l.cow);
                    changes++;
                }
            }
            if(cows[l.cow] > maxout)
            {
                maxcows = new TreeSet<Integer>();
                maxcows.add(l.cow);
                maxout = cows[l.cow];
                changes++;
            }
            else if(cows[l.cow] == maxout)
            {
                maxcows.add(l.cow);
                changes++;
            }

        }
        writer.println(changes);
        //System.out.println(changes);
        reader.close();
        writer.close();
    }
}

class Log implements Comparable<Log> {
    public int day, cow, change;

    public Log(int d, int c, int ch)
    {
        day = d;
        cow = c;
        change = ch;
    }

    @Override
    public int compareTo(Log o)
    {
        return day - o.day;
    }

}
