import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class feb2021silver1 {
    static HashSet<hashPair> real;
    static HashSet<hashPair> fake;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        real = new HashSet<hashPair>();
        fake = new HashSet<hashPair>();
        hashPair[] order = new hashPair[N];
        boolean[] comfortable = new boolean[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            order[i] = new hashPair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[] ans = new int[N];
        for(int i = 0; i < N; i++)
        {
            real.add(order[i]);
            if(fake.contains(order[i]))
            {
                fake.remove(order[i]);
                real.add(order[i]);
            }
            fix(order[i]);
            fix(new hashPair(order[i].a-1, order[i].b));
            fix(new hashPair(order[i].a+1, order[i].b));
            fix(new hashPair(order[i].a, order[i].b-1));
            fix(new hashPair(order[i].a, order[i].b+1));
            ans[i] = fake.size();
        }
        for(int i = 0; i < N; i++)
        {
            System.out.println(ans[i]);
        }
        reader.close();
        //writer.close();
    }
    static void fix(hashPair add)
    {
        boolean[] present = new boolean[4];
        present[0] = real.contains(new hashPair(add.a-1, add.b)) || fake.contains(new hashPair(add.a-1, add.b));
        present[1] = real.contains(new hashPair(add.a+1, add.b)) || fake.contains(new hashPair(add.a+1, add.b));
        present[2] = real.contains(new hashPair(add.a, add.b-1)) || fake.contains(new hashPair(add.a, add.b-1));
        present[3] = real.contains(new hashPair(add.a, add.b+1)) || fake.contains(new hashPair(add.a, add.b+1));
        int neighbors = 0;
        for(int i = 0; i < 4; i++)
        {
            if(present[i]) neighbors++;
        }
        if(neighbors == 3)
        {
            if(!present[0])
            {
                fake.add(new hashPair(add.a-1, add.b));
                fix(new hashPair(add.a-1, add.b));
                fix(add);
                fix(new hashPair(add.a-2, add.b));
                fix(new hashPair(add.a-1, add.b+1));
                fix(new hashPair(add.a-1, add.b-1));
            }
            if(!present[1])
            {
                fake.add(new hashPair(add.a+1, add.b));
                fix(add);
                fix(new hashPair(add.a+1, add.b));
                fix(new hashPair(add.a+2, add.b));
                fix(new hashPair(add.a+1, add.b+1));
                fix(new hashPair(add.a+1, add.b-1));
            }
            if(!present[2])
            {
                fake.add(new hashPair(add.a, add.b-1));
                fix(add);
                fix(new hashPair(add.a, add.b-1));
                fix(new hashPair(add.a, add.b-2));
                fix(new hashPair(add.a+1, add.b-1));
                fix(new hashPair(add.a-1, add.b-1));
            }
            if(!present[3])
            {
                fake.add(new hashPair(add.a, add.b+1));
                fix(add);
                fix(new hashPair(add.a, add.b+1));
                fix(new hashPair(add.a, add.b+2));
                fix(new hashPair(add.a+1, add.b+1));
                fix(new hashPair(add.a-1, add.b+1));
            }
        }
        /*
        if(orig >= 0) {
            if (present[0]) fix(new hashPair(add.a - 1, add.b), 1);
            if (present[1]) fix(new hashPair(add.a + 1, add.b), 0);
            if (present[2]) fix(new hashPair(add.a, add.b - 1), 3);
            if (present[3]) fix(new hashPair(add.a, add.b + 1), 2);
        }
        else
        {
            if (orig != 0 && present[0]) fix(new hashPair(add.a - 1, add.b), 1);
            if (orig != 1 && present[1]) fix(new hashPair(add.a + 1, add.b), 0);
            if (orig != 2 && present[2]) fix(new hashPair(add.a, add.b - 1), 3);
            if (orig != 3 && present[3]) fix(new hashPair(add.a, add.b + 1), 2);
        }
         */
    }
}
class hashPair implements Comparable<hashPair>
{
    int a, b;
    public hashPair(int c, int d)
    {
        a = c;
        b = d;
    }
    @Override
    public int compareTo(hashPair m)
    {
        return a == m.a ? b - m.b : a - m.a;
    }

    @Override
    public boolean equals(Object m)
    {
        return m instanceof hashPair && compareTo((hashPair) m) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + a;
        result = 31 * result + b;
        return result;
    }
}