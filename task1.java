import java.util.Random;
import java.util.Scanner;
public class task1
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Random rd=new Random();
        int min=1;
        int max=100;
        int noattempt=10;
        int score=0;
        System.out.println("NUMBER GUESSING GAME............");
        boolean start=true;
        while(start)
        {
            int randomnumber=rd.nextInt(max-min+1)+min;
            System.out.println("\n the random number is from " +min+ "and" +max+" Guess the Random Number" );
            System.out.println("Total chances for attempting:"+noattempt);
            boolean guessedans=false;
            int attempt=0;
            while(!guessedans && attempt<noattempt)
            {
                System.out.println(" enter ur geuss:");
                int guess= sc.nextInt();
                attempt++;
                if(guess<randomnumber)
                {
                    System.out.println("lower than random number");
                }
                else if(guess>randomnumber)
                {
                    System.out.println("higher than random number");
                }
                else
                {
                    System.out.println("U guessed the Random Number ...Congrats....");
                    System.out.println("number of attempts taken to guess the random Number:"+attempt);
                    score=noattempt-attempt+1;
                    guessedans= true;                    
                }
                

            }
            if(!guessedans)
            {
                System.out.println("U Reached the Max attempt the Random number is:"+randomnumber);
            }
       
             System.out.println(" Score:"+score);
             System.out.println("Do u want to continue the Game???(yes/no):");
             String playagain =sc.next().toLowerCase();
             if(playagain.equals("yes"))
             {
                System.out.println("Restarting the Game::::::::::.........:::::::: Guess The Number");
             }
             else{
                start=false;
             }
        }
        System.out.println("\n............ thank you playing this game...........");
        sc.close();
    

    }
}

