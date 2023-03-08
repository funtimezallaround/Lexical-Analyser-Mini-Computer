import java.util.*;
public class LexicalAnalyser{

    String [] keywords= new String[]{",", "please", "rules", "exit", "add", "sub", "mult", "div", "concat"};

    public void printRules(){ // to see rules input "rules please"
        System.out.println("\nHere are the rules:");
        System.out.println("• A request must start with an instruction, followed by 2 comma separated integer values and ending with a please");
        System.out.println("• The only accepted instructions are: ");
        System.out.println("    - \"add\"    : add the 2 numbers");
        System.out.println("    - \"sub\"    : subtract the 2nd number from the 1st");
        System.out.println("    - \"mult\"   : multiply the 2 numbers");
        System.out.println("    - \"div\"    : divide the 1st number by the 2nd");
        System.out.println("    - \"concat\" : concatenate the 2nd number onto the first");
        System.out.println("• Example input: add 1,2 please\n");
        System.out.println("• To quit the program simply write: exit please");
        System.out.println("• The program is not case sensitive\n");
    }

    public String[] splitIn(String in){
        in= in.toLowerCase();

        // finding how many words in the sentence, & what loc the spaces are @
        int wordCount= 1;
        ArrayList<Integer> spaceloc= new ArrayList<Integer>();
        for(int i= 0; i< in.length(); i++){
            if(in.charAt(i)== ' ' || in.charAt(i)== ','){
                spaceloc.add(i);
                wordCount++;
            }
        }
        // System.out.println(wordCount); // temp out to cross check results

        // creating an array to store all the words
        String [] table= new String[wordCount];

        if(table.length == 1){
            table[0]= in;
        }
        if(table.length >= 2){
            table[0]= in.substring(0, spaceloc.get(0));
        }
        if(table.length >= 3){
            for(int i= 1; i< spaceloc.size(); i++){
                table[i]= in.substring((1+spaceloc.get(i-1)), spaceloc.get(i));
            }
        }
        if(table.length >= 2){
            table[wordCount-1]= in.substring((1+spaceloc.get(wordCount-2)), in.length());
        }

        // temp out to check results
        // for(int i= 0; i< table.length; i++){
        // System.out.println(table[i]);
        // }

        return table;
    }

    public boolean checkSyntax (String [] in){
        boolean valid= true;

        if(in.length== 2){ // special case where request does not follow standard format
            if(!(in[0].equals("rules") || in[0].equals("exit")))
                valid= false;
        }
        else if(in.length== 4){ // standard request format
            int count= 0;

            // if the 1st word is not a keyword the request is not valid:
            for(int b= 0; b< keywords.length; b++){
                if(!(in[0].equals(keywords[b])))
                    count++;
            }
            if(count== keywords.length)
                valid= false;
        }
        else // if a request is any different it is not accepted
            valid= false;

        // finally, a request must end with a please
        if(!(in[in.length-1].equals("please")))
            valid= false;

        return valid;
    }

    public boolean compute(String [] in){
        /* request must contain a mode of operation followed by 2 values and end with a please
         * eg{ add 12 , 3 please
         * out: 15 }
         */ 

        boolean exit= false;
        boolean greenlight= true;
        String out= null;
        Integer a= null;
        Integer b= null;

        if(in[0].equals("concat")){
            System.out.println("Output: "+ in[1]+in[2]);
            greenlight= false;
        }
        else if(in[0].equals("rules")){
            printRules();
            greenlight= false;
        }
        else if(in[0].equals("exit")){
            exit=true;
            System.out.println("goodbye...");
            greenlight= false;
        }
        if(greenlight){
            try{
                a= Integer.valueOf(in[1]);
                b= Integer.valueOf(in[2]);
            }
            catch(NumberFormatException ex){
                System.out.println("Values provided do not comply with the rules");
                greenlight= false;
            }
        }
        if(greenlight){
            switch(in[0]){
                case "add":
                    out= ""+(a+b);
                    break;
                case "sub":
                    out= ""+(a-b);
                    break;
                case "mult":
                    out= ""+(a*b);
                    break;
                case "div":
                    out= ""+(a/b);
                    break;
            }
            System.out.println("Output: "+out);
        }

        return exit;
    }
}