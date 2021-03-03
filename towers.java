import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class towers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        //int maxnum = 0;
        //int max = 0;
        int n = Integer.parseInt(reader.readLine());
        TreeSet<Integer> towers = new TreeSet<Integer>(new newcomp());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int curr;
        for(int i = 0; i < n; i++) {
            curr = Integer.parseInt(st.nextToken());
            if (towers.tailSet(curr, false).isEmpty())
            {
                towers.add(curr);
            }
            else
            {
                towers.remove(towers.tailSet(curr, false).first());
                towers.add(curr);
            }
        }
        System.out.println(towers.size());
        reader.close();
        //writer.close();
    }
}
class newcomp implements Comparator<Integer>
{
    @Override
    public int compare(Integer a, Integer b)
    {
        if(a.compareTo(b) == 0) return 1;
        else return a.compareTo(b);
    }
}
