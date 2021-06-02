import java.lang.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
abstract class Employee implements Comparable{
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    protected double earning;
    Employee()
    {
        this("", "", "0");
    }
    Employee(String firstName,String lastName,String socialSecurityNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
    public  double getEarning()
    {
        return  this.earning;
    }




    public abstract double earning();//需要被所有子类重写的抽象方法。

    @Override
    public String toString()//重写，输出信息
    {
        return "firstName:"+this.firstName+"; lastName:"+this.lastName+"; socialSecurityNumber:"+this.socialSecurityNumber+"; earning:";
    }

    @Override
    public int compareTo(Object e) {
     if(this.earning>((Employee)e).getEarning())
     {
         return 2;
     }
     if(this.earning<((Employee)e).getEarning())
     {
         return  -2;
     }
        return 0;
    }
}
class basePlusCommisionEmployee extends CommisionEmployee{
    private double  baseSalary;//基本工资
    public basePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary){
        super(firstName,lastName,socialSecurityNumber,grossSales,commissionRate);//调用父类的构造函数
        setBaseSalary(baseSalary);//获取基本工资
        this.earning=earning();
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public double earning() {
       return grossSales*commissionRate+baseSalary;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString函数
    }
}

class CommisionEmployee extends Employee {
    protected double grossSales;//销售额
    protected double commissionRate;//提成比率

    public CommisionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales,
            double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);//调用父类的构造函数
        setCommissionRate(commissionRate);//获取销售额
        setGrossSales(grossSales);//获取提成
        this.earning = earning();
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 重写父类的earning函数
     */
    @Override
    public double earning() {
        return grossSales * commissionRate;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString
    }
}

class HourlyEmployee extends Employee
{
    private double wage;//每小时工钱
    private double hours;//月工作小时数


    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double wage, double hours){
        super(firstName,lastName,socialSecurityNumber);//调用父类的构造函数
        setHours(hours);//获取时薪
        setWage(wage);//获取工作时长
        this.earning=earning();
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double
    getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    /**
     * 重写父类的earning函数
     */
    @Override
    public double earning() {
        return wage*hours;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString函数
    }
}

class SalaridEmployee extends Employee {
    private double weeklySalary;

    public SalaridEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);//调用父类的构造函数
        setWeeklySalary(weeklySalary);//获取周薪
        this.earning = earning();
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    /**
     * 重写父类的earning函数
     */
    @Override
    public double earning() {
        return weeklySalary * 4;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString函数
    }
}


public class Main {

    public static void main(String[] args) {
        int n = 0;
        int m = 0;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();//获取n的值
        if (n != 0) {
            Employee employees[] = new Employee[n];
            String firstName;//姓
            String lastName;//名
            String socialSecurityNumber;//社会保险号
            for (int i = 0; i < n; i++) {
                int temp = scanner.nextInt();
                switch (temp) {
                    case 0:
                        firstName = scanner.next();
                        lastName = scanner.next();
                        socialSecurityNumber = scanner.next();
                        double weeklySalary = scanner.nextDouble();
                        employees[i] = (SalaridEmployee) new SalaridEmployee(firstName, lastName, socialSecurityNumber,
                                weeklySalary);
                        break;

                    case 1:
                        firstName = scanner.next();
                        lastName = scanner.next();
                        socialSecurityNumber = scanner.next();
                        double wage, hours;
                        wage = scanner.nextDouble();
                        hours = scanner.nextDouble();
                        employees[i] = (HourlyEmployee) new HourlyEmployee(firstName, lastName, socialSecurityNumber,
                                wage, hours);
                        break;

                    case 2:
                        firstName = scanner.next();
                        lastName = scanner.next();
                        socialSecurityNumber = scanner.next();
                        double grossSales, commissionRate;
                        grossSales = scanner.nextDouble();
                        commissionRate = scanner.nextDouble();
                        employees[i] = (CommisionEmployee) new CommisionEmployee(firstName, lastName,
                                socialSecurityNumber, grossSales, commissionRate);
                        break;
                    case 3:
                        firstName = scanner.next();
                        lastName = scanner.next();
                        socialSecurityNumber = scanner.next();
                        double base_grossSales, base_commissionRate, baseSalary;
                        base_grossSales = scanner.nextDouble();
                        base_commissionRate = scanner.nextDouble();
                        baseSalary = scanner.nextDouble();
                        employees[i] = (basePlusCommisionEmployee) new basePlusCommisionEmployee(firstName, lastName,
                                socialSecurityNumber, base_grossSales, base_commissionRate, baseSalary);
                        break;
                }//读取雇员信息到雇员数组中。

            }
            Arrays.sort(employees);//对数组进行排序
            m=scanner.nextInt();//要查询的次数
            for(int i=0;i<m;i++)
            {
                int temp=scanner.nextInt();
                if(temp==0)//根据名字查询
                {
                    firstName=scanner.next();
                    for(int j=0;j<n;j++)
                    {
                        if(employees[j].getFirstName().equals(firstName))
                        {
                            System.out.println(employees[j].toString() +String.format("%.2f",employees[j].earning() ) );
                        }
                    }
                }
                else if(temp==1)//根据社会号查询
                {
                    socialSecurityNumber=scanner.next();
                    for(int j=0;j<n;j++)
                    {
                        if(employees[j].getSocialSecurityNumber().equals(socialSecurityNumber))
                        {
                            System.out.println(employees[j].toString() + String.format("%.2f",employees[j].earning() ));
                        }
                    }
                }
            }

        }
        scanner.close();
    }
}

