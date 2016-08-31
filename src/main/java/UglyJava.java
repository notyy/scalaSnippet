import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UglyJava {

    private String sumFilePath = "./temp/sum.txt";

    public int getSum(List<Integer> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("empty list parameter!");
        int sum = 0;
        List<Integer> newList = new ArrayList<Integer>();
        for(int i : list){
            if(i > 2){
                int j = i * 2;
                sum += j;
                newList.add(j);
            }
        }
        list.clear();
        list.addAll(newList);
        int prevSum = readSum();
        int finalSum = sum + prevSum;
        saveSum(finalSum);
        return finalSum;
    }

    private void saveSum(int sum){
        BufferedWriter writer = null;
        try {
            File file = new File(sumFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(sum));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int readSum() {
        int result = 0;
        BufferedReader reader = null;
        try {
            File file = new File(sumFilePath);
            if(!file.exists()) {
                return 0;
            }
            reader = new BufferedReader(new FileReader(sumFilePath));
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                return 0;
            } else {
                result = Integer.valueOf(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if(reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
