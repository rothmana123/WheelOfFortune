//**Version 2: WheelOfFortuneMethods**
//
//- In this version, provide the following **static** methods, but **don't define any data members**:
//    - `randomPhrase` -- returns a single phrase randomly chosen from a list.
//    - `generateHiddenPhrase` -- returns the initial hidden phrase.
//    - `getGuess` -- gets input from the user and returns it.
//    - `processGuess` -- returns whether a letter matches, and modifies the partially hidden phrase
//    - if there is a match.
//- Use these exact method names along with appropriate parameters.

//go back and create Strings with string Builder

import java.util.Random;
import java.util.Scanner;

public class WOFV2 {

    private static Scanner scanner = new Scanner(System.in);

    //randomPhrase` -- returns a single phrase randomly chosen from a list.
    public static StringBuilder randomPhrase() {

        String[] phrases = {
                "have a nice day",
                "hello world",
                "java programming",
                "wheel of fortune"
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        //System.out.println("this is the random number: " + index);
        StringBuilder phrase = new StringBuilder(phrases[index]);
        return phrase;
    }

    //generateHiddenPhrase` -- returns the initial hidden phrase.
    public static StringBuilder generateHiddenPhrase(StringBuilder phrase){
        //is method parameter a String or StringBuilder
        StringBuilder hiddenPhrase = new StringBuilder();
        //String hiddenPhrase = "";
        char letter;
        for (int i = 0; i < phrase.length(); i++){
            letter = phrase.charAt(i);
            if(letter == ' '){
                hiddenPhrase.append(' ');
            }
            else {
                hiddenPhrase.append("*");
            }
        }
        return hiddenPhrase;
    }

    //getGuess
    public static String getGuess(){
        while(true) {
            System.out.println("Guess a Letter");
            String guessString = scanner.nextLine().toLowerCase();

            // Check if the user pressed Enter without entering any input
            //method only works with a String
            if (guessString.isEmpty()) {
                System.out.println("Please enter a valid letter");
                continue;
            }

            if (guessString.length() > 1){
                System.out.println("Only enter 1 letter");
                continue;
            }

            //alternative without switching String to Char
            //**But you still only want to return 1 letter...

            //if (!guessString.matches("[a-zA-Z]")) {
            //            System.out.println("Only guess a letter");
            //            continue;
            //        }

            //change String to Char
            char guessLetter = guessString.charAt(0);

            //Check to make sure entry is a letter
            if (!Character.isLetter(guessLetter)) {
                System.out.println("Only guess a letter");
                continue;
            }

            //convert back to String
            return Character.toString(guessLetter);
            ///next move is to process guessLetter through Process Guess
        }
    }

    //processGuess` -- returns whether a letter matches, and modifies the partially hidden phrase- if there is a match.
    public static int processGuess(String guess, StringBuilder phrase, StringBuilder hiddenPhrase, int wrongAnswers){

        //get the character to check against phrase
        String checkLetter = guess.substring(0, 1);

        //check if char in string first.  If yes, run the for loop to modify hiddenPhrase
        //if not, update wrongAnswers and return
        if(phrase.indexOf(checkLetter) != -1) {
            for (int i = 0; i < phrase.length(); i++) {
                if (checkLetter.charAt(0) == phrase.charAt(i)) {
                    hiddenPhrase.replace(i, i + 1, checkLetter);
                }
            }
        }
        else {
            wrongAnswers--;
            System.out.println("Nope.  Wrong Answers left: " + wrongAnswers);
                }
        return wrongAnswers;
    }

    public static void playGame(){
        int wrongAnswers = 5;
        Boolean win = false;

        //Generate phrase and hidden phrase
        StringBuilder phrase = WOFV2.randomPhrase();
        System.out.println(phrase.toString());
        StringBuilder hiddenPhrase = WOFV2.generateHiddenPhrase(phrase);
        System.out.println(hiddenPhrase.toString());

        while(true) {
            //get user guess, return hidden phrase,
            System.out.println();
            String guess = WOFV2.getGuess();
            System.out.println("Player guessed " + guess);

            //keep track of wrong answers
            wrongAnswers = processGuess(guess, phrase, hiddenPhrase, wrongAnswers);
            System.out.println(hiddenPhrase);

            //check win condition
            win = checkWin(phrase, hiddenPhrase);
            if(win){
                System.out.println("You won!");
                break;
            } else if (wrongAnswers <= 0) {
                System.out.println("You lost!");
                break;
            }
        }
    }

    public static Boolean checkWin(StringBuilder phrase, StringBuilder hiddenPhrase){
        boolean win = false;
        if(phrase.toString().equals(hiddenPhrase.toString())) {
//          System.out.println("You did it!");
            win = true;
            return win;
        }
        return win;
    }

    public static void main(String[] args) {
        WOFV2.playGame();

    }
}
