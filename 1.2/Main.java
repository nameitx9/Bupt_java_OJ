import java.io.*;//标准输入输出
import java.util.Scanner;
import java.util.regex.Pattern;


class StudentNumberException extends Exception {//自定义异常类，继承于exception
    public StudentNumberException()//构造方法接受异常信息
    {
        super("Illegal number format");//调用父类中的构造方法
    }
}

class ScoreException extends Exception {
    public ScoreException() {
        super("Illegal score format");
    }
}

class Student {
    private String studentName;
    private String studentNumber;
    private int markForMaths = -1;
    private int markForEnglish = -1;
    private int markForScience = -1;
    private String legal_studentnumber = "^[2][0][0-9]{8}$";//用正则式来表示合法的学号

    private boolean judgeLegalScore(int score)
    {
        return (score >= 0 && score <= 100); 
    }//判断成绩是否合法,true是合法，false是不合法

    String getNumber() {
        return studentNumber;
    }

    String getName() {
        return studentName;
    }

    void enterMarks(int Maths, int English, int Science)  throws ScoreException {
        if ((!judgeLegalScore(Maths))|| (!judgeLegalScore(English))||(!judgeLegalScore(Science)))
        {
            throw new ScoreException();
        }//三种成绩中，只要有一种不合法，就抛出异常
        markForMaths = Maths;
        markForEnglish = English;
        markForScience = Science;
    }

    public Student(String name, String number)  throws StudentNumberException {
        if (!Pattern.matches(legal_studentnumber, number))
        {
            throw new StudentNumberException();
        }//
        this.studentName = name;
        this.studentNumber = number;
    }//构造方法

    int getMathsMark() {
        return markForMaths;
    }

    int getEnglishMark() {
        return markForEnglish;
    }

    int getScienceMark() {
        return markForScience;
    }

    double calculateAverage() {
        return (markForEnglish + markForMaths + markForScience) / 3.0;
    }

    String tostring() {
        String astring = "Student ID:" + getNumber() + "\nName:" + getName() + "\nMath:" + getMathsMark() + "\nEnglish:"
                + getEnglishMark() + "\nScience:" + getScienceMark() + "\nAverage Score:"
                + Math.round(calculateAverage() * 10) / 10.0;//保留一位小数
        return astring;
    }
}

public class Main
{

  
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();//read studen ID;
        String name = sc.next();//read student name;
        int math = sc.nextInt();
        int english = sc.nextInt();
        int science = sc.nextInt();
        try{
            Student astudent=new Student(name,number);
            astudent.enterMarks(math, english ,science);
            System.out.println(astudent.toString());
        }
        catch(StudentNumberException e)
        {
            //学号错误
            System.out.println(e.getMessage());
        }
        catch(ScoreException e)
        {
            //成绩错误
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}