import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
public class cereal {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter writer = new PrintWriter("cereal.out");
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int cows = Integer.parseInt(st.nextToken());
        int cereals = Integer.parseInt(st.nextToken());
        cow[] line = new cow[cows];
        for(int i = 0; i < line.length; i++)
        {
            st = new StringTokenizer(reader.readLine());
            line[i] = new cow();
            line[i].f = Integer.parseInt(st.nextToken())-1;
            line[i].s = Integer.parseInt(st.nextToken())-1;
        }
        /*
        int[] purchases = new int[cows];
        for(int i = 0; i < purchases.length; i++)
        {
            purchases[i] = -1;
        }
        int[] stock = new int[cereals];
        for(int i = 0; i < stock.length; i++)
        {
            stock[i] = 1;
        }
        ArrayList<Integer> hungry = new ArrayList<Integer>();

        for(int i = 0; i < line.length; i++)
        {
            if(stock[line[i].f] > 0)
            {
                stock[line[i].f]--;
                purchases[i] = line[i].f;
            }
            else if(stock[line[i].s] > 0)
            {
                stock[line[i].s]--;
                purchases[i] = line[i].s;
            }
            else
            {
                hungry.add(i);
            }
        }

         */
        HashSet<Integer> cerav = new HashSet<Integer>();
        boolean allcereals = false;
        int[] output = new int[cows];
        for(int i = cows-1; i >= 0; i--)
        {
            if(!allcereals) {
                for (int j = i; j < cows; j++) {

                    cerav.add(line[j].f);
                    cerav.add(line[j].s);
                }
            }
            if(cerav.size() == cereals) allcereals = true;
            if(cows-i <= cereals)
            {
                if(cerav.size() >= cows-i)
                {
                    output[i] = cows-i;
                }
                else output[i] = cerav.size();
            }
            else output[i] = cerav.size();
        }
        for(int i : output) writer.println(i);

        reader.close();
        writer.close();
    }
}
class cow
{
    int f, s;
}
