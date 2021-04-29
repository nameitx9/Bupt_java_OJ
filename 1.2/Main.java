import java.io.*;//��׼�������
import java.util.Scanner;
import java.util.regex.Pattern;


class StudentNumberException extends Exception {//�Զ����쳣�࣬�̳���exception
    public StudentNumberException()//���췽�������쳣��Ϣ
    {
        super("Illegal number format");//���ø����еĹ��췽��
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
    private String legal_studentnumber = "^[2][0][0-9]{8}$";//������ʽ����ʾ�Ϸ���ѧ��

    private boolean judgeLegalScore(int score)
    {
        return (score >= 0 && score <= 100); 
    }//�жϳɼ��Ƿ�Ϸ�,true�ǺϷ���false�ǲ��Ϸ�

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
        }//���ֳɼ��У�ֻҪ��һ�ֲ��Ϸ������׳��쳣
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
    }//���췽��

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
                + Math.round(calculateAverage() * 10) / 10.0;//����һλС��
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
            //ѧ�Ŵ���
            System.out.println(e.getMessage());
        }
        catch(ScoreException e)
        {
            //�ɼ�����
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}