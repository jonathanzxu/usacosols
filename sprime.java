/*
ID: j.jonny1
LANG: JAVA
TASK: sprime
 */
import java.io.*;
import java.util.TreeSet;

public class sprime {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        //Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        TreeSet<Integer> sprimes = new TreeSet<Integer>();
        TreeSet<Integer> temp;
        sprimes.add(2);
        sprimes.add(3);
        sprimes.add(5);
        sprimes.add(7);
        for(int i = 1; i < n; i++)
        {
            temp = new TreeSet<Integer>();
            for(int j : sprimes)
            {
                for(int k = 1; k <= 9; k += 2)
                {
                    if(prime(j * 10 + k)) temp.add(j * 10 + k);
                }
            }
            sprimes = temp;
        }
        for(int i : sprimes)
        {
            writer.println(i);
        }
        /*
        boolean superprime;
        int number;
        for(int i = (int) Math.pow(10, n-1) + 1; i < (int) Math.pow(10, n); i+=2)
        {
            number = i;
            superprime = true;
            while(number > 0)
            {
                if(!prime(number)) superprime = false;
                number /= 10;
            }
            if(superprime) writer.println(i);
        }
         */

        reader.close();
        writer.close();
    }
    public static boolean prime(int num)
    {
        if(num <= 3)
        {
            return num > 1;
        }
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
