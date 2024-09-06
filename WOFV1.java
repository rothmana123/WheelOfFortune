import java.util.Random;
import java.util.Scanner;

public class WOFV1 {
    public static void main(String[] args) {
        //Variables
        Scanner scanner = new Scanner(System.in);
        char letter;
        char guessLetter;
        int wrongAnswers = 5;
        Boolean win = false;

        //create Phrase
        String[] phrases = {
                "have a nice day",
                "hello world",
                "java programming",
                "wheel of fortune"
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        StringBuilder phrase = new StringBuilder(phrases[index]);
        StringBuilder hiddenPhrase = new StringBuilder();



        //Create Asterik String
        for (int i = 0; i < phrase.length(); i++) {
            letter = phrase.charAt(i);
            if (Character.isLetter(letter) || Character.isDigit(letter)) {
                hiddenPhrase.append("*");
            } else {
                hiddenPhrase.append(" ");
            }
        }

        //Start Game
        System.out.println("Welcome to Wheel of Fortune!  Guess the phrase:");
        System.out.println(hiddenPhrase);

        //While True Loop
        //get a Guess Letter String
        //String: make it lower case
        //set the guess letter to charAt(0)
        while(true){
            System.out.println();
            System.out.println("Guess a letter");
            String guessString = scanner.nextLine();
            String lcGuess = guessString.toLowerCase();
//          System.out.println("Lowercase Guess: " + lcGuess);
            guessLetter = lcGuess.charAt(0);

            //Check to make sure entry is a letter
            if(!Character.isLetter(guessLetter)){

                System.out.println("Only guess a letter");
                continue;
            }
            //check to see if letter char is in String1
            if(phrase.indexOf(lcGuess) == -1) {
                wrongAnswers--;
                if (wrongAnswers > 0) {
                    System.out.println("The letter is not in the phrase");
                    System.out.println(wrongAnswers + " wrong Answers left");
                    System.out.println(hiddenPhrase);
                    System.out.println();
                    continue;
                } else {
                    System.out.println("You lost. wah wah wah");
                    break;
                }
            }

            //Code to build a 3rd String
            //Set new string to old string with correctly guessed letters added
            //This method; we create a 3rd string, then set String2(asterik) even to 3rd string
            //***there is probably an easier way to do this- with replace at method
            //example: sb.replace(7, 12, "Java");
            //so, would require a variable to track index position...but wouldnt require 3rd String object
            StringBuilder newString2 = new StringBuilder();
            //check if letter is in String1
            // Update String2 with the correct guesses
            for (int i = 0; i < phrase.length(); i++) {
                letter = phrase.charAt(i);
                if (letter == guessLetter) {
                    newString2.append(letter);
                } else {
                    newString2.append(hiddenPhrase.charAt(i));
                }
            }

            // Update String2 with the new string
            hiddenPhrase = newString2;
            System.out.println(hiddenPhrase);

            //End Game message
            if(phrase.toString().equals(hiddenPhrase.toString())) {
                System.out.println("You did it!");
                break;
            }
        }

    }
}






