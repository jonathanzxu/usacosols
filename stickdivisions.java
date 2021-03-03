import javax.management.openmbean.OpenMBeanAttributeInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class stickdivisions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int x = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i = 0; i < n; i++)
        {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        long total = 0;
        int a, b;
        while(pq.size() > 1)
        {
            a = pq.poll();
            b = pq.poll();
            total += a + b;
            pq.add(a+b);
        }
        System.out.println(total);
        reader.close();
        //writer.close();
    }
}
