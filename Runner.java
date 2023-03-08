import java.util.*;
public class Runner{
    public static void main(String [] args){
        Scanner s= new Scanner(System.in);
        LexicalAnalyser la= new LexicalAnalyser();
        String temp;
        String [] input= new String []{};

        boolean exit= false;
        do{
            System.out.println("\nPlease enter an instruction or ask \"rules please\" for the rules");
            System.out.print("Input: ");
            temp= s.nextLine();
            System.out.print("\f"); // clear the terminal of any previous outputs
            System.out.println("Input: "+temp);
            input= la.splitIn(temp);
            boolean valid= la.checkSyntax(input);

            if(valid== true)
                exit= la.compute(input);
            else
                System.out.println("\nUnable to comprehend request. Make sure you are using a please! Ask \"rules please\" to see the rules");
        }while(!exit);
    }
}