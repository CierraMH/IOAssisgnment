package IOAssisgnment;

import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.*;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
       Path path = FileSystems.getDefault().getPath("src", "input1.txt");
       Path path2 = Paths.get("src", "IOAssisgnment", "input2.txt");
       Path paths = Paths.get("src", "IOAssisgnment", "merged.txt");
       Path path3 = FileSystems.getDefault().getPath("src", "common.txt");

        List<Integer> listOfNumbers = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        List<String> numberss = new ArrayList<>();
        try {
            //this will gather the details for the input1 file

            numbers = Files.readAllLines((path));
            for( String line : numbers) {
                int i = Integer.parseInt(line);
                listOfNumbers.add(i);
//                System.out.println(i);
            }
            System.out.println("Input1 details: " + numbers);

                //this will gather input2 details
                numberss = Files.readAllLines((path2));
                for( String lines : numberss){
                    int ii = Integer.parseInt(lines);
                    listOfNumbers.add(ii);
//                    System.out.println(ii);
            }
            System.out.println("Input2 details: " + numberss);

            //this will gather the details for the merge list and will add to the merge file
            List<String> aList = new ArrayList<>();
            for (Integer allLines : listOfNumbers){
                    String a = allLines.toString();
//                    System.out.println(a);
                aList.add(a);
                }
            Files.write(paths, aList);
            System.out.println("Both input files merged together: " +listOfNumbers);

            //this will gather the details for the merge list and will add to the merge file
            List<Integer> allList;
            allList = listOfNumbers;
            List<Integer> commonList = allList.stream().
                    collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() > 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            //common list details and FILE.WRITE to add commonList to the file
            System.out.println("Numbers in common: " + commonList);
            String onlyCommons = commonList.toString();
            Files.write(path3, onlyCommons.getBytes());

        } catch (FileNotFoundException fnf){
            System.out.println("Error! File not found");
            fnf.printStackTrace();
        } catch (IOException e ){
            System.out.println("Error! IO trouble");
            e.printStackTrace();
        } catch (NumberFormatException ne){
            System.out.println("Error! Number format incorrect");
            ne.printStackTrace();
        }
    }
}
