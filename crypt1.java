/*
ID: j.jonny1
LANG: JAVA
TASK: crypt1
 */
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class crypt1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        //Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        HashSet<Integer> digits = new HashSet<Integer>();
        int[] di = new int[n];
        int dig;
        for(int i = 0; i < n; i++) {
            dig = Integer.parseInt(st.nextToken());
            digits.add(dig);
            di[i] = dig;
        }
        int valid = 0;
        int abc, eabc, dabc, abcde;
        for(int a = 0; a < n; a++) {
            for(int b = 0; b < n; b++) {
                for(int c = 0; c < n; c++) {
                    for(int d = 0; d < n; d++) {
                        for(int e = 0; e < n; e++) {
                            abc = di[a] * 100 + di[b] * 10 + di[c];
                            eabc = di[e] * abc;
                            dabc = di[d] * abc;
                            abcde = eabc + dabc * 10;
                            if(eabc < 1000 && checkdigits(eabc, digits) && dabc < 1000 && checkdigits(dabc, digits) && abcde < 10000 && checkdigits(abcde, digits)) valid++;
                        }
                    }
                }
            }
        }
        writer.println(valid);
        reader.close();
        writer.close();
    }
    public static boolean checkdigits(int num, HashSet<Integer> set)
    {
        while(num > 0)
        {
            if(!set.contains(num % 10)) return false;
            num /= 10;
        }
        return true;
    }
}
