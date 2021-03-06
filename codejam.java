import java.io.*;
import java.util.*;
public class codejam {
    public static void main(String[] args) throws IOException
    {
        Scanner r = new Scanner(System.in);
        int cases = Integer.parseInt(r.nextLine());
        StringTokenizer st;
        int activities;
        for(int i = 1; i <= cases; i++)
        {
            activities = Integer.parseInt(r.nextLine());
            Activity[] sched = new Activity[activities];
            for(int j = 0; j < activities; j++)
            {
                st = new StringTokenizer(r.nextLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                sched[j] = new Activity(start, end);
            }
            ArrayList<Activity> cam = new ArrayList<Activity>();
            ArrayList<Activity> jam = new ArrayList<Activity>();
            String out = "";
            for(Activity a: sched)
            {
                if(fine(a, cam))
                {
                    out = out + "C";
                    cam.add(a);
                }
                else if(fine(a, jam))
                {
                    out = out + "J";
                    jam.add(a);
                }
                else
                {
                    out = "IMPOSSIBLE";
                    break;
                }
            }
            System.out.println("Case #" + i + ": " + out);
        }
    }
    public static boolean fine(Activity ac, ArrayList<Activity> person)
    {
        if(person.size() > 0) {
            for (Activity a : person) {
                if (!((ac.start >= a.end && ac.end >= a.end) || (ac.start <= a.start && ac.end <= a.start)))
                    return false;
            }
        }
        return true;
    }


}

class Activity
{
    int start;
    int end;
    public Activity(int s, int e)
    {
        start = s;
        end = e;
    }
}
