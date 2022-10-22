package com.company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.lang.String;
public class Main {

    public static void main(String[] args) throws IOException {

        //read the input file name.
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        //get the input name into the File
        File file = new File(input);

        //get the path using getCanonicalPath for the input file, so we can open the file
        //REMEMBER: the text file should be placed in the Stack_Queue file not in the src
        // replace or change the backslash into "\\" inside the string, so we can open the file.
        String get_path = file.getCanonicalPath().replace("\\", "\\\\");
        File newFile = new File(get_path);
        Scanner readFile = new Scanner(newFile);

        //Scanner read = new Scanner(new File("C:\\Users\\maian\\Stack_Queue\\src\\text3.txt"));
        StackQueue addTag = new StackQueue();

        //integer for counting the line
        int countLine = 0;

        //create the character will be removed in the String

        String specialChar_end = "/";
        String char_a = "a";
        String char_exclamation = "!";

        //create the boolean to stop the loop if HTML tag is false.
        boolean test = true;
        while (readFile.hasNext()&& test) {
            String data = readFile.nextLine();

            //******using Matcher and pattern to extract the string contain < > in the  HTML file.
            Pattern pattern = Pattern.compile("<[^>]*>");
            Matcher matcher = pattern.matcher(data);
            //print out the special chars
            //*******loop through the line in file text

            while (matcher.find()) {

                // System.out.println(matcher.group());
                addTag.push(matcher.group());
                addTag.top();

                //check if the file contains "br" or "hr", if yes, we will pop them out.
                if(addTag.top().equals("<br>") || addTag.top().equals("<hr>")) {
                    addTag.pop(addTag.top());
                    //System.out.println(countLine);
                    continue;

                }

                //***** check and pass the first base if it has in HTML, it will pass without checking the end tag.
                //*** <a href= >
                if(char_a.equals(Character.toString(addTag.top().charAt(1)))){
                    //cut the extra string inside the tag <a> to compare with the end tag </a>
                    String subStringa = addTag.top().substring(2, addTag.top().length()-1);
                    String rest_String = addTag.top().replace(subStringa,"");

                    addTag.pop(addTag.top());
                    addTag.push(rest_String);
                    continue;
                }

                //***** check if the comment syntax in the HTML file or not, if yes, we will pop it out.
                //<!---->
                if(char_exclamation.equals(Character.toString(addTag.top().charAt(1)))) {
                    String subString_excla = addTag.top().substring(4, addTag.top().length() - 3);
                    String rest_String_excla = addTag.top().replace(subString_excla, "");

                    //System.out.println(rest_String_excla);
                    if (rest_String_excla.equals("<!---->")) {
                        addTag.pop(rest_String_excla);
                        //System.out.println(countLine);
                        continue;
                    }
                }
                //check and pass the element <!DOCTYPE html>
                if (addTag.top().equals("<!DOCTYPE html>")) {
                    continue;
                }

                //**** check if the next tag contain the character "/" or not.
                //if not it will continue the while loop
                if (specialChar_end.indexOf(addTag.top().charAt(1)) < 0) {
                    continue;
                }



                // *** check if the next tag contain the character "/" or not. if yes
                // *** it will check the condition
                //*** remove the char "/" to check if two strings are the same, then we will pop out
                //if (specialChar_end.indexOf(addTag.top().charAt(1)) >= 0) {

                if(specialChar_end.equals(Character.toString(addTag.top().charAt(1)))&& test) {
                    String a = addTag.top().replace("/", "");

                    //the bottom tag is the previous tag which will compare with the next tag
                    String bottomTag = addTag.getm();
                    //System.out.println("Bottom" + bottomTag +a);

                    //check the condition to compare the start tag and the end tag
                    // if the start tag contains the end tag and ignore the CSS syntax in the start tag.
                    if(a.equals(bottomTag) || bottomTag.contains(a.substring(1,a.length()-1))){

                        addTag.pop(a);
                        addTag.pop(bottomTag);

                        continue;
                    }

                    else{
                        System.out.println("Oops ... There is a problem . ." +
                                "The " + addTag.top() + " tag at line " + (countLine + 1) + " does not meet the tag rules . . ");
                        test = false;
                    }


                }

            }
            countLine++;

        }
        //if the test passes, print out the result.
        if(test){
            System.out.println("Congratulations. . . " +
                    "The given HTML file meets all the tag rules. . ");
        }

    }
}



