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
    }//���췽��
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
        "Average Score:"+Math.round(calculateAverage()*10)/10.0;//����һλС��
        return astring;
    }
}

class StudentList {
    Student[] list;//student list
    int total=0;//total number

    public StudentList(int length) {
        list = new Student[length];
    }//���췽��

    boolean add(Student stu) {
        //���ѧ�����ɹ���true������false
        if (total < list.length) {
            list[total] = stu;
            total++;//totalʼ�ձ�ʾ��ǰ�б��й��е�ѧ����
            return true;
        } else {
            return false;
        }
    }

    boolean remove(String number)
    {
        int no = -1;//Ҫɾ����ѧ�������
        for (int i = 0; i < total; i++)//list[total-1]���б��е����һ��ѧ��
        {
            if (list[i].getNumber().equals(number)) {
                no = i;
                break;//�ҵ���ͣ��
            } //ͨ��һ��ѭ���ҵ�Ҫɾ����ѧ�����ڵ�λ��

        }
        if (no == -1) {
            return false;
        } //û�ҵ�
        else {
            for (int j = no; j < total - 1; j++) {
                list[j] = list[j + 1];
            }
            total = total - 1;
            return true;
        } //�ҵ���
    }

    boolean updateItem(String number, int math, int english, int science) {
        int no = -1;//Ҫ���µ�ѧ�������
        for (int i = 0; i < total; i++)//list[total-1]���б��е����һ��ѧ��
        {
            if (list[i].getNumber().equals(number)) {
                no = i;
                break;//�ҵ���ͣ��
            } //ͨ��һ��ѭ���ҵ�Ҫ������Ϣ��ѧ�����ڵ�λ��

        }
        if (no == -1) {
            return false;
        } //û�ҵ�
        else {
            list[no].enterMarks(math, english, science);
            return true;
        } //�ҵ���
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
        for (int i = 0; i < total || i==0; i++)//list[total-1]���б��е����һ��ѧ��,��Ҫע��i��0��ʼ���
        {
            if (total==0)
            {
                break;//��һ��ʼ��i=0����listΪ�յ�ʱ��Ҳ�ܽ���������
            }
            if(list[i].getNumber().equals(number))
            {
                return list[i];//�ҵ���ֱ�����
            }

        }
        return null;//û�ҵ�
    }
}
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        StudentList studentlist = new StudentList(length);//����ѧ���б�
        System.out.println("�б����ɹ�");

        Student stu;
        String number;
        String name;
        int math;
        int english;
        int science;//��ʱʹ�õĸ�������

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
                stu.enterMarks(math, english, science);//����һ��ѧ������
                if(studentlist.getItem(number)==null)//ѧ�������ھͼ���
                {
                    boolean flag = studentlist.add(stu);//���Լ���
                    if (flag == true) {
                        System.out.println("Add success");
                    } else {
                        System.out.println("�������");
                    }

                }
                else {
                    System.out.println("Students already exist");//ѧ���Ѵ���
                }
                break;
            case 2://delete student
                number = sc.next();
                stu=studentlist.getItem(number);
                if( stu == null)//Ҫ�ҵ�ѧ��������
                {

                    System.out.println("Students do not exist");
                }
                else {
                    //Ҫ�ҵ�ѧ������
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
            if( stu == null)//Ҫ�ҵ�ѧ��������
            {

                System.out.println("Students do not exist");
            }
            else {
                //Ҫ�ҵ�ѧ������
                studentlist.updateItem(number, math, english, science);
                System.out.println("Update success");
            }
            break;
        case 4://��ʾƽ���ɼ�
            number = sc.next();
            System.out.println(number);
            stu = studentlist.getItem(number);
            if( stu == null)//Ҫ�ҵ�ѧ��������
            {

                System.out.println("Students do not exist");
            }
            else {
                //Ҫ�ҵ�ѧ������
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