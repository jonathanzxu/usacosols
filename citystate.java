import java.io.*;
import java.util.*;

public class citystate {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st;
        int N = Integer.parseInt(reader.readLine());
        CSN[] cities = new CSN[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            cities[i] = new CSN(st.nextToken(), st.nextToken());
        }
        Arrays.sort(cities);
        HashSet<CSN> combos = new HashSet<CSN>();
        combos.addAll(Arrays.asList(cities));
        Stack<CSN> stack = new Stack<CSN>();
        stack.addAll(combos);
        int pairs = 0;
        //HashSet<>
        while(!stack.isEmpty())
        {
            CSN c = stack.pop();
            pairs += howMany(c.c1, c.c2, c.s1, c.s2, cities) * howMany(c.s1, c.s2, c.c1, c.c2, cities);
            stack.remove(new CSN("" +  c.s1 + c.s2, "" + c.c1 + c.c2));
        }
        writer.println(pairs);
        reader.close();
        writer.close();
    }
    public static int howMany(char c1, char c2, char s1, char s2, CSN[] cities)
    {
        int lo = 0, hi = cities.length-1;
        int loc = -1;
        while(lo < hi)
        {
            int mid = (lo+hi+1)/2;
            if(cities[mid].compareTo(new CSN("" + c1 + c2, "" + s1 + s2)) > 0)
            {
                hi = mid - 1;
            }
            else if (cities[mid].compareTo(new CSN("" + c1 + c2, "" + s1 + s2)) < 0)
            {
                lo = mid;
            }
            else
            {
                loc = mid;
                break;
            }
        }
        int num = 0;
        int l = loc, r = loc+1;
        if(loc >= 0)
        {
            while(l >= 0 && cities[l].compareTo(cities[loc]) == 0)
            {
                l--;
                num++;
            }
            while(r < cities.length && cities[r].compareTo(cities[loc]) == 0)
            {
                r++;
                num++;
            }
        }
        return num;
    }
}
class CSN implements Comparable<CSN>{
    char c1, c2, s1, s2;
    public CSN(String c, String s)
    {
        c1 = c.charAt(0);
        c2 = c.charAt(1);
        s1 = s.charAt(0);
        s2 = s.charAt(1);
    }
    @Override
    public int compareTo(CSN c)
    {
        if(c1 == c.c1)
        {
            if(c2 == c.c2)
            {
                if(s1 == c.s1)
                {
                    return s2 - c.s2;
                }
                return s1 - c.s1;
            }
            return c2 - c.c2;
        }
        return c1 - c.c1;
    }
    @Override
    public boolean equals(Object c)
    {
        if(!(c instanceof CSN)) return false;
        return compareTo((CSN) c) == 0;
    }
}

