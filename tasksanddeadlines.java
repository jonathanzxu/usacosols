import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class tasksanddeadlines {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        Task[] tasks = new Task[n];
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            tasks[i] = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(tasks);
        long time = 0;
        long points = 0;
        for(int i = 0; i < n; i++)
        {
            time += tasks[i].time;
            points += tasks[i].deadline - time;
        }
        System.out.println(points);
        reader.close();
        //writer.close();
    }
}
class Task implements Comparable<Task>
{
    int time, deadline;
    public Task(int t, int d)
    {
        time = t;
        deadline = d;
    }
    @Override
    public int compareTo(Task t)
    {
        return time - t.time;
    }
}
