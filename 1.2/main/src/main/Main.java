package main;
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
                "Name:" + getName()
                + 
                "Math:" + getMathsMark()
                + 
                "English:" + getEnglishMark()
                + 
                "Science:" + getScienceMark() 
                + 
        "Average Score:"+Math.round(calculateAverage()*10)/10.0;//保留一位小数
        return astring;
    }
}

class StudentList {
    Student[] list;//student list
    int total=0;//total number

    public StudentList(int length) {
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
            if (list[i].getNumber().equals(number)) {
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
            if (list[i].getNumber().equals(number)) {
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
        for (int i = 0; i < total || i==0; i++)//list[total-1]是列表中的最后一个学生,还要注意i从0开始检查
        {
            if (total==0)
            {
                break;//当一开始，i=0，即list为空的时候也能进行搜索。
            }
            if(list[i].getNumber().equals(number))
            {
                return list[i];//找到了直接输出
            }

        }
        return null;//没找到
    }
}
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        StudentList studentlist = new StudentList(length);//创建学生列表
        System.out.println("列表建立成功");

        Student stu;
        String number;
        String name;
        int math;
        int english;
        int science;//临时使用的辅助变量

        while(sc.hasNextLine())
        {

            int i = sc.nextInt();
            switch(i)
            {
            case 1://ADD STUDENT
                //add student
                number = sc.next();
                name = sc.next();
                math = sc.nextInt();
                english = sc.nextInt();
                science = sc.nextInt();
                stu = new Student(name, number);
                stu.enterMarks(math, english, science);//建立一个学生对象
                if(studentlist.getItem(number)==null)//学生不存在就加入
                {
                    boolean flag = studentlist.add(stu);//尝试加入
                    if (flag == true) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("数组溢出");
                    }

                }
                else {
                    System.out.println("Students already exist");//学生已存在
                }
                break;
            case 2://delete student
                number = sc.next();
                stu=studentlist.getItem(number);
                if( stu == null)//要找的学生不存在
                {

                    System.out.println("Students do not exist");
                }
                else {
                    //要找到学生存在
                    studentlist.remove(number);
                    System.out.println("Delete success");
                }
                break;
            case 3://update student
                number = sc.next();
                math = sc.nextInt();
                english = sc.nextInt();
                science = sc.nextInt();
            stu=studentlist.getItem(number);
            if( stu == null)//要找的学生不存在
            {

                System.out.println("Students do not exist");
            }
            else {
                //要找到学生存在
                studentlist.updateItem(number, math, english, science);
                System.out.println("Update success");
            }
            break;
        case 4://显示平均成绩
            number = sc.next();
            System.out.println(number);
            stu = studentlist.getItem(number);
            if( stu == null)//要找的学生不存在
            {

                System.out.println("Students do not exist");
            }
            else {
                //要找到学生存在
                System.out.println("Student ID:"+stu.getNumber()
                +
                "Name:"+stu.getName()
                +
                "\nAverage Score:"+stu.calculateAverage());
            }
            break;
        case 5:
            if(studentlist.isEmpty()==true)
            {
                //list is empty
                System.out.println("List is empty");
            }
            else {
                System.out.println("List is not empty");
            }
            break;
        case 6:
            System.out.println(studentlist.getTotal());
            break;
            }
        }
    }
}