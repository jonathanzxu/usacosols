/*
ID: j.jonny1
LANG: JAVA
TASK: pprime
 */
import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class pprime {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        String aye = "" + a;
        String bee = "" + b;
        for(int i = aye.length(); i <= bee.length(); i++)
        {
            for(int j : palis(i))
            {
                if(j >= a && j <= b && prime(j)) writer.println(j);
            }
        }
        reader.close();
        writer.close();
    }

    public static TreeSet<Integer> palis(int n) {
        TreeSet<Integer> out = new TreeSet<Integer>();
        if (n == 1) {
            for (int a = 5; a <= 9; a += 2) {
                out.add(a);
            }
        }
        if (n == 2) {
            for (int a = 1; a <= 9; a += 2) {
                out.add(mirror(a, true));
            }
        }
        if (n == 3) {
            for (int a = 1; a <= 9; a += 2)
            {
                for(int b = 0; b <= 9; b++)
                {
                    out.add(mirror(a * 10 + b, false));
                }
            }
        }
        if (n == 4) {
            for (int a = 1; a <= 9; a += 2)
            {
                for (int b = 0; b <= 9; b++)
                {
                    out.add(mirror(a * 10 + b, true));
                }
            }
        }
        if (n == 5) {
            for (int a = 1; a <= 9; a += 2)
            {
                for (int b = 0; b <= 9; b++)
                {
                    for (int c = 0; c <= 9; c++)
                    {
                        out.add(mirror(a * 100 + b * 10 + c, false));
                    }
                }
            }
        }
        if (n == 6) {
            for (int a = 1; a <= 9; a += 2)
            {
                for (int b = 0; b <= 9; b++)
                {
                    for (int c = 0; c <= 9; c++)
                    {
                        out.add(mirror(a * 100 + b * 10 + c, true));
                    }
                }
            }
        }
        if (n == 7) {
            for (int a = 1; a <= 9; a += 2)
            {
                for (int b = 0; b <= 9; b++)
                {
                    for (int c = 0; c <= 9; c++)
                    {
                        for (int d = 0; d <= 9; d++)
                        {
                            out.add(mirror(a * 1000 + b * 100 + c * 10 + d, false));
                        }
                    }
                }
            }
        }
        if (n == 8) {
            for (int a = 1; a <= 9; a += 2)
            {
                for (int b = 0; b <= 9; b++)
                {
                    for (int c = 0; c <= 9; c++)
                    {
                        for (int d = 0; d <= 9; d++)
                        {
                                out.add(mirror(a * 1000 + b * 100 + c * 10 + d, true));
                        }
                    }
                }
            }
        }
        return out;
    }

    public static int mirror(int num, boolean even)
    {
        String out = "" + num;
        String append = "";
        if(even)
        {
            for(int i = out.length() - 1; i >= 0; i--)
            {
                append += out.charAt(i);
            }
        }
        else
        {
            for(int i = out.length() - 2; i >= 0; i--)
            {
                append += out.charAt(i);
            }
        }
        return Integer.parseInt(out + append);
    }

    public static boolean prime(int num)
    {
        if(num % 2 == 0 || num % 3 == 0) return false;
        int i = 5;
        while(i*i <= num)
        {
            if(num % i == 0 || num % (i+2) == 0) return false;
            i += 6;
        }
        return true;
    }

}
