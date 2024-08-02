import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("...Student Grade Calculator...");
        boolean start=true;
        while(start){
            System.out.print("Enter the Number of Subjects: ");
            int noofsub = sc.nextInt();
            sc.nextLine();
            String[] subjects = new String[noofsub];
            int[] marks= new int[noofsub];
            double avgmarks=0;
            int tot=0;
            char g;
            System.out.println("List out the subjects and marks one by one:");
            for (int i = 0; i < noofsub; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                subjects[i] = sc.next();
                System.out.print("marks: ");
                marks[i]=sc.nextInt();
                tot+=marks[i];
            }
            avgmarks=(double)tot/noofsub;
            if(avgmarks>=90)
            {
                g='A';
            }
            else if(avgmarks>=80)
            {
                g='B';
            }
            else if(avgmarks>=70)
            {
                g='C';
            }
            else if(avgmarks>=60)
            {
                g='D';
            }
            else if(avgmarks>=50)
            {
                g='E';
            }
            else 
            {
                g='F';
            }
            System.out.println("the subjects and the marks u optained :");
            for(int i=0;i<noofsub;i++)
            {
                System.out.println("Subject"+(i+1)+":"+subjects[i]+"mark"+(i+1)+":"+marks[i]);
            }
            System.out.println("the total marks of this Student:"+tot);
            System.out.println("the average percentage of the student is:"+avgmarks+"%");
            System.out.println("the Grade of the student  :"+g);
            System.out.println("Do u want to continue ???(yes/no)");
            String playagain =sc.next().toLowerCase();
            if(!playagain.equals("yes"))
            {
                start=false;
            }
        }
        System.out.println("Thank you");



        

        sc.close();
    }
}