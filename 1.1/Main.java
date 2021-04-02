import java.io.*;//标准输入输出
import java.util.Scanner;
class Student{
    private String studentName;
    private String studentNumber;
    private int markForMaths = -1;
    private int markForEnglish = -1;
    private int markForScience = -1;
    String getNumber(){
        return studentNumber;
    }
    String getName(){
        return studentName;
    }
    void enterMarks(int Maths,int English,int Science)
    {
        markForMaths = Maths;
        markForEnglish = English;
        markForScience = Science;
    }
    public Student(String name, String number)
    {
        this.studentName = name;
        this.studentNumber = number;
    }//构造方法
    int getMathsMark(){
        return markForMaths;
    }
    int getEnglishMark(){
        return markForEnglish;
    }
    int getScienceMark(){
        return markForScience;
    }
    double calculateAverage()
    {
       return (markForEnglish + markForMaths + markForScience)/3.0;
    }
    String tostring()
    {
        String astring = "Student ID:" + getNumber()
                + 
                "\nName:" + getName()
                + 
                "\nMath:" + getMathsMark()
                + 
                "\nEnglish:" + getEnglishMark()
                + 
                "\nScience:" + getScienceMark() 
                + 
        "\nAverage Score:"+Math.round(calculateAverage()*10)/10.0;//保留一位小数
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
        Student astudent = new Student(name, number);
        astudent.enterMarks(math, english, science);//读入成绩
        System.out.println(astudent.tostring());
        sc.close();
    }
}