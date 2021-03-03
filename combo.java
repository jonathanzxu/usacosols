/*
ID: j.jonny1
LANG: JAVA
TASK: combo
 */
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class combo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("combo.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        //Scanner reader = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        if(n <= 5)
        {
            writer.println(n * n * n);
            reader.close();
            writer.close();
            return;
        }

        int[] combo = new int[3];
        int[] master = new int[3];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i = 0; i < 3; i++)
        {
            combo[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(reader.readLine());
        for(int i = 0; i < 3; i++)
        {
            master[i] = Integer.parseInt(st.nextToken());
        }
        HashSet<Integer>[] combolist = new HashSet[3];
        HashSet<Integer>[] masterlist = new HashSet[3];
        for(int i = 0; i < 3; i++)
        {
            combolist[i] = new HashSet<Integer>();
            for(int j = -2; j <= 2; j++) {
                if (combo[i] + j < 0) combolist[i].add(combo[i] + j + n);
                else combolist[i].add((combo[i] + j) % n);
            }
        }
        for(int i = 0; i < 3; i++)
        {
            masterlist[i] = new HashSet<Integer>();
            for(int j = -2; j <= 2; j++) {
                if (master[i] + j < 0) masterlist[i].add(master[i] + j + n);
                else masterlist[i].add((master[i] + j) % n);
            }
        }
        writer.println(250 - sameTerms(combolist[0], masterlist[0]) * sameTerms(combolist[1], masterlist[1]) * sameTerms(combolist[2], masterlist[2]));
        reader.close();
        writer.close();
    }

    public static int sameTerms(HashSet<Integer> a, HashSet<Integer> b)
    {
        int count = 0;
        for(int i : a)
        {
            if(b.contains(i)) count++;
        }
        return count;
    }
}
