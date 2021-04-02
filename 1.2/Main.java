import java.io.*;//标准输入输出
import java.util.Scanner;

import javax.swing.text.AsyncBoxView;
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

class StudentList {
    Student[] list;//student list
    int total=0;//total number

    public void studenlist(int length)
    {
        list = new Student[length];
    }//构造方法

    boolean add(Student stu) {
        //添加学生，成功返true，否则，false
        if (total < list.length) {
            list[total] = stu;
            total++;//total始终表示当前列表中共有的学生数
            return true;
        } else {
            return false;
        }
    }

    boolean remove(String number)
    {
        int no = -1;//要删除的学生的序号
        for (int i = 0; i < total; i++)//list[total-1]是列表中的最后一个学生
        {
            if (list[i].getNumber() == number) {
                no = i;
                break;//找到就停下
            } //通过一个循环找到要删除的学生所在的位置

        }
        if (no == -1) {
            return false;
        } //没找到
        else {
            for (int j = no; j < total - 1; j++) {
                list[j] = list[j + 1];
            }
            total = total - 1;
            return true;
        } //找到了
    }

    boolean updateItem(String number, int math, int english, int science) {
        int no = -1;//要更新的学生的序号
        for (int i = 0; i < total; i++)//list[total-1]是列表中的最后一个学生
        {
            if (list[i].getNumber() == number) {
                no = i;
                break;//找到就停下
            } //通过一个循环找到要更新信息的学生所在的位置

        }
        if (no == -1) {
            return false;
        } //没找到
        else {
            list[no].enterMarks(math, english, science);
            return true;
        } //找到了
    }

    boolean isEmpty()
    {
        if (this.total == 0) {
            return true;
        } else {
            return false;
        }
    }

    int getTotal()
    {
        return total;
    }

    Student getItem(String number)
    {
        Student astudent = null;//定义一个学生对象，默认为null
        for (int i = 0; i < total; i++)//list[total-1]是列表中的最后一个学生
        {
            if (list[i].getNumber() == number) {
                astudent = list[i];
                break;//找到就停下
            } //通过一个循环找到要查找信息的学生所在的位置

        }
        return astudent;//输出找到的学生，如果找到了，正常输出
    }
}
public class Main
{
    public static void main(String[] args)
    {
        int no = -1;//辅助序号变量
    }
}