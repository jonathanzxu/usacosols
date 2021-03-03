/*
ID: j.jonny1
LANG: JAVA
TASK: barn1
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter writer = new PrintWriter("barn1.out");
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] cowpos = new int[c];
        for(int i = 0; i < c; i++)
        {
            cowpos[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(cowpos);
        PriorityQueue<Integer> maxgaps = new PriorityQueue<Integer>();
        for(int i = 0; i < m-1; i++)
        {
            maxgaps.add(0);
        }
        int length = cowpos[cowpos.length - 1] - cowpos[0] + 1;
        if(maxgaps.size() > 0) {
            int gap;
            for (int i = 0; i < c - 1; i++) {
                gap = cowpos[i + 1] - cowpos[i] - 1;
                if (gap > maxgaps.peek()) {
                    maxgaps.poll();
                    maxgaps.add(gap);
                }
            }
            for (int i : maxgaps) {
                length -= i;
            }
        }
        writer.println(length);
        reader.close();
        writer.close();
    }
}
