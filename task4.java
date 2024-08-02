import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Ques {
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Ques(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Quiz {
    private List<Ques> questions;
    private int score;
    private int currentQuestionIndex;
    private Scanner scanner = new Scanner(System.in);

    public Quiz(List<Ques> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Ques question = questions.get(currentQuestionIndex);
            System.out.println("\n" + question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ": " + options[i]);
            }
            final int timeLimit = 20;
            final long startTime = System.currentTimeMillis();
            Timer timer = new Timer();
            TimerTask Task = new TimerTask(){
                private int remainingTime = timeLimit;
                public void run() {
                    if (remainingTime <= 0) {
                        System.out.println("\nTime's up! Moving to the next question...");
                        scanner.close(); 
                        timer.cancel(); 
                    }
                }
            };
            timer.scheduleAtFixedRate(Task, 0, 1000);
            String answer ="";
            while (System.currentTimeMillis() - startTime < 20000) { 
                if (scanner.hasNextLine()) {
                    answer = scanner.nextLine();
                    timer.cancel(); 
                    break;
                }
            }
            if (question.isCorrectAnswer(answer)) {
                System.out.println("Answer is correct!");
                score++;
            } else {
                System.out.println("Incorrect answer. The correct answer was: " + question.getCorrectAnswer());
            }
        }

        display();
    }

    public void display() {
        System.out.println("\nQuiz finished!");
        System.out.println("Score: " + score + "/" + questions.size());
    }
}

public class task4 {
    public static void main(String[] args) {
        List<Ques> questions = new ArrayList<>();
        questions.add(new Ques("Number of primitive data types in Java are?", new String[]{"3", "6", "8", "9"}, "8"));
        questions.add(new Ques("What is the size of float and double in Java?", new String[]{"32and64", "32and32", "64and64", "64and32"}, "32and64"));
        questions.add(new Ques("Automatic type conversion is possible in which of the possible cases?", new String[]{"Byte to int", "int to long", "Long to int", "Short to int"}, "int to long"));
        questions.add(new Ques("Select the valid statement.", new String[]{"char[] ch=new char[5]", "char[] ch=new char(5)", "char[] ch=new char()", "char[] ch=new char[]"}, "char[] ch=new char[5]"));
        questions.add(new Ques("When an array is passed to a method, what does the method receive?", new String[]{"The reference of the array", "A copy of the array", "Length of the array", "Copy of the first element"}, "The reference of the array"));

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}