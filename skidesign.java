/*
ID: j.jonny1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.TreeSet;

public class skidesign {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        //Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        int[] initial = new int[n];
        int[] finish = new int[n];
        for(int i = 0; i < n; i++)
        {
            initial[i] = Integer.parseInt(reader.readLine());
            finish[i] = initial[i];
        }
        long expense = Long.MAX_VALUE;
        for(int i = 0; i < 84; i++)
        {
            expense = Math.min(expense, solve(initial, i, i+17));
        }

        /*
        double diff;
        int mindex, maxdex, even;
        while(range(finish) > 17)
        {
            mindex = minIndex(finish);
            maxdex = maxIndex(finish);
            diff = (range(finish) - 17);
            even = (int) Math.min(diff, Math.abs(Math.abs(initial[mindex] - finish[mindex]) - Math.abs(initial[maxdex] - finish[maxdex])));
            if(Math.abs(initial[mindex] - finish[mindex]) > Math.abs(initial[maxdex] - finish[maxdex]))
            {
                finish[maxdex] -= even;
                diff -= even;
            }
            else if(Math.abs(initial[mindex] - finish[mindex]) < Math.abs(initial[maxdex] - finish[maxdex]))
            {
                finish[mindex] += even;
                diff -= even;
            }
            finish[mindex] += Math.floor(diff/2.0);
            finish[maxdex] -= Math.ceil(diff/2.0);
        }
        int spent = 0;
        for(int i = 0; i < n; i++) {
            spent += Math.pow(initial[i] - finish[i], 2);
        }
         */
        writer.println(expense);
        /*
        for(int i = 0; i < n; i++)
        {
            writer.println(initial[i] + ":" + finish[i]);
        }
        */
        reader.close();
        writer.close();
    }
    public static int range(int[] arr)
    {
        int min = arr[0];
        int max = arr[0];
        for(int i : arr)
        {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        return max - min;
    }
    public static int minIndex(int[] arr)
    {
        int min = arr[0];
        int minindex = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(min > arr[i])
            {
                min = arr[i];
                minindex = i;
            }
        }
        return minindex;
    }
    public static int maxIndex(int[] arr)
    {
        int max = arr[0];
        int maxindex = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(max < arr[i])
            {
                max = arr[i];
                maxindex = i;
            }
        }
        return maxindex;
    }
    public static int solve(int[] arr, int lo, int hi)
    {
        int sum = 0;
        for(int n : arr)
        {
            if(n < lo) sum += (lo - n) * (lo - n);
            else if(n > hi) sum += (n - hi) * (n - hi);
        }
        return sum;
    }
}
