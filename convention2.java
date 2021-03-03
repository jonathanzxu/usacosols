import java.io.*;
import java.util.*;

public class convention2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;

        PriorityQueue<conventionevent> events = new PriorityQueue<conventionevent>();
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            events.add(new conventionevent(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, false));
        }
        boolean occupied = false;
        conventionevent curr;
        conventioncow cow;
        int time = 0;
        int maxwait = 0;
        PriorityQueue<conventioncow> waiting = new PriorityQueue<conventioncow>();
        while(!events.isEmpty())
        {
            curr = events.poll();
            if(!curr.leaving && !occupied)
            {
                events.add(new conventionevent(curr.arrive + curr.time, -1, -1, true));
                occupied = true;
            }
            else if(!curr.leaving && occupied)
            {
                waiting.add(new conventioncow(curr.arrive, curr.time, curr.senior));
            }
            else if(curr.leaving)
            {
                occupied = false;
                if(!waiting.isEmpty())
                {
                    cow = waiting.poll();
                    occupied = true;
                    events.add(new conventionevent(curr.arrive + cow.time, cow.time, cow.senior, true));
                    maxwait = Math.max(maxwait, curr.arrive - cow.arrive);
                }
            }
        }
        writer.println(maxwait);
        reader.close();
        writer.close();
    }
}
class conventioncow implements Comparable<conventioncow>
{
    int arrive, time, senior;
    public conventioncow(int a, int t, int s)
    {
        arrive = a;
        time = t;
        senior = s;
    }
    @Override
    public int compareTo(conventioncow c)
    {
        return senior - c.senior;
    }
}
class conventionevent implements Comparable<conventionevent>
{
    int arrive, time, senior;
    boolean leaving;
    public conventionevent(int a, int t, int s, boolean l)
    {
        arrive = a;
        time = t;
        senior = s;
        leaving = l;
    }
    @Override
    public int compareTo(conventionevent c)
    {
        return arrive == c.arrive ? senior - c.senior : arrive - c.arrive;
    }
}
