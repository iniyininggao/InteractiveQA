import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InteractiveQA {
    static String newInputQuestionAndAnswer,userInputQuestion,newInputQuestion,s1;
    static String newInputAnswers[];
    static Enumeration<String> savedQuestionsHashKeys;
    static int userInputNumber;
    static ArrayList<String> answerList = new ArrayList<>();
    static Hashtable questionAndAnswersToBeSaved = new Hashtable();
    static Hashtable savedQuestionsList;


    public static void addQuestion(Scanner keyboard) {
        System.out.println(Constants.ADDING_QA_WORDS);
        newInputQuestionAndAnswer = keyboard.nextLine();
        try {
            String[] QaAString = newInputQuestionAndAnswer.split("\\?");
            newInputQuestion = QaAString[0].trim();
            newInputAnswers = QaAString[1].trim().split("\\s+");

            System.out.println("Questionlist: " + QaAString[0]);
            for (String part : newInputAnswers) {
                if (part.startsWith("\"") && part.endsWith("\"")) {
                    String content = part.substring(1, part.length() - 1);
                    answerList.add(content);
                    //System.out.println("Answers are :" + content);
                }
            }
            //System.out.println("Answers are ：" + answerList);
            questionAndAnswersToBeSaved.put(newInputQuestion, newInputAnswers);
        } catch (Exception e){
            System.out.println(Constants.NO_ANSWETR_WARNING);
        }
    }

    public static void saveQuestionInJson(Hashtable<String, ArrayList[]> questionLists) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            savedQuestionsList = readQAFromJsonFile(Constants.FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedQuestionsList.putAll(questionLists);
        try {
            objectMapper.writeValue(new File(Constants.FILE_PATH), savedQuestionsList);
            //System.out.println("Data saved to savedQuestions.json file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void userActivityInput(int userInputNumber,Scanner keyboard){
        switch (userInputNumber){
            case 1 :
                askQuestion(keyboard);
                break;
            case 2 :
                s1 = keyboard.nextLine();
                addQuestion(keyboard);
                try {
                    saveQuestionInJson(questionAndAnswersToBeSaved);
                } catch (IOException e) {
                    System.out.println(Constants.SAVING_QUESTION_WRONG + e.getMessage());
                }
                break;
            case 0 :
                System.out.println(Constants.ENDING_WORDS);
                System.exit(0);
                break;
            default:
                System.out.println(Constants.WRONG_INPUT_NUMBER);
                keyboard.nextLine();
        }
    }

    public static void askQuestion(Scanner keyboard){
        System.out.println(Constants.LEADING_WORDS);
        s1 = keyboard.nextLine();
        userInputQuestion = keyboard.nextLine().split("\\?")[0];
        try {
            savedQuestionsList = readQAFromJsonFile(Constants.FILE_PATH);
            savedQuestionsHashKeys = savedQuestionsList.keys();

            if(savedQuestionsList.containsKey(userInputQuestion) || savedQuestionsList.containsKey(userInputQuestion+"?")){
                //System.out.println(userInputQuestion);
                System.out.println(savedQuestionsList.get(userInputQuestion));
            }else {
                System.out.println(Constants.DEFAULT_ANSWER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Hashtable<String, ArrayList> readQAFromJsonFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            //System.out.println("the answer to life, universe and everything is 42");
            return new Hashtable<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, Hashtable.class);
        } catch (IOException e) {
            System.out.println(Constants.READING_FROM_FILE_WRONG);
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Constants.WELCOME_WORDS);

        Scanner keyboard = new Scanner(System.in);
        while(true){
            System.out.println(Constants.EXPLANATION_WORDS);
            try {
                userInputNumber = keyboard.nextInt();
                userActivityInput(userInputNumber,keyboard);
            } catch (InputMismatchException e){
                System.out.println(Constants.WRONG_INPUT_NUMBER);
                keyboard.nextLine();
            }
        }

    }
}
