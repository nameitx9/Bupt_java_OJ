import java.lang.*;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
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
class CommisionEmployee extends  Employee{
    protected double grossSales;//销售额
    protected double commissionRate;//提成比率

    public CommisionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate){
        super(firstName,lastName,socialSecurityNumber);//调用父类的构造函数
        setCommissionRate(commissionRate);//获取销售额
        setGrossSales(grossSales);//获取提成
        this.earning=earning();
    }
    public CommisionEmployee(){this("","","2",0,0);}

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
       return grossSales*commissionRate;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString
    }
}
 class SalaridEmployee extends Employee{
    private double weeklySalary;
    public SalaridEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary){
        super(firstName,lastName,socialSecurityNumber);//调用父类的构造函数
        setWeeklySalary(weeklySalary);//获取周薪
        this.earning=earning();
    }
    public SalaridEmployee()
    {
        this(" "," ","0",0);
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
        return weeklySalary*4;
    }

    @Override
    public String toString() {
        return super.toString();//调用父类的toString函数
    }
}
class EmployeeException extends Exception{
    private String message;
    private int code;
    public EmployeeException(int code,String message)
    {
        super(message);
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return this.code;
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
    public HourlyEmployee(){this(" "," ","1",0,0);}

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

class basePlusCommisionEmployee extends CommisionEmployee{
    private double  baseSalary;//基本工资
    public basePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary){
        super(firstName,lastName,socialSecurityNumber,grossSales,commissionRate);//调用父类的构造函数
        setBaseSalary(baseSalary);//获取基本工资
        this.earning=earning();
    }

    public basePlusCommisionEmployee(){this("","","3",0,0,0);}
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



class factory {
    private TreeMap<String,Employee>employees =new TreeMap<>();
    Employee getEmployee (String empSecNum)throws EmployeeException{
        //遍历Treemap，用迭代器实现
        Iterator<String> it = employees.keySet().iterator();
        while (it.hasNext())
        {
            String key=it.next();
            if(key.equals(empSecNum))
            {
                return employees.get(key);
            }
        }
        throw new EmployeeException(1004,"employee not found.");
    }
    Employee deleteEmployee(String empSecNum)throws EmployeeException{
        //与get函数类似，只不过找到了就删除
        Iterator<String> it = employees.keySet().iterator();
        while (it.hasNext())
        {
            String key=it.next();
            if(key.equals(empSecNum))
            {
                Employee emp=employees.remove(empSecNum);
                return emp;
            }
        }
        throw new EmployeeException(1002,"employee not found.");
    }
    Employee addEmployee(Employee emp)throws EmployeeException{
        Iterator<String> it = employees.keySet().iterator();
        while (it.hasNext())
        {
            String key=it.next();
            if(key.equals(emp.getSocialSecurityNumber()))
            {
                throw new EmployeeException(1001,"employee exists.");
            }
        }
//如果找不到该对象，就在Treemap中加入该对象。返回他的引用
            employees.put(emp.getSocialSecurityNumber(),emp);
            return emp;

    }
    Employee updateEmployee(String empSecNum ,Employee emp)throws EmployeeException
    {
        Iterator<String> it = employees.keySet().iterator();
        while (it.hasNext())
        {
            String key=it.next();
            if(key.equals(empSecNum))
            {
                employees.put(empSecNum,emp);//覆盖原有的内容
                return emp;
            }
        }
        throw new EmployeeException(1003,"employee not found.");//找不到该人，返回异常
    }
    void printEmployees()
    {
        Iterator<String> it = employees.keySet().iterator();
        while (it.hasNext())
        {
            String key=it.next();
           System.out.println(employees.get(key).toString()+employees.get(key).earning);
        }
    }




}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command;
        factory f=new factory();
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext())
        {
            command=scanner.next();
            //add命令要分类型添加
            if(command.equals("add"))
            {
                //先建立一个空的临时雇员，没有任何信息，之后会重新给他覆盖信息
                Employee aEmployee=new basePlusCommisionEmployee();
                int type=scanner.nextInt();
                switch (type)
                {
                    case 0:
                        aEmployee=new SalaridEmployee(scanner.next(),scanner.next(),scanner.next(), scanner.nextDouble());
                        break;
                    case 1:
                        aEmployee= new HourlyEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    case 2:
                        aEmployee= new CommisionEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    case 3:
                        aEmployee=new basePlusCommisionEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    default:System.out.println("非法输入");
                }
                try
                {
                    //如果添加成功，还要将添加的对象的信息显示出来
                   Employee emp = f.addEmployee(aEmployee);
                   System.out.println(emp.toString() + emp.earning());
                }
                catch (EmployeeException employeeException)
                {
                    System.out.println(employeeException.getCode());
                    System.out.println(employeeException.getMessage());
                }

            }
            //删除和查找命令很类似
            else if(command.equals("get"))
            {
                String empS=scanner.next();
                try
                {
                    Employee emp= f.getEmployee(empS);
                    System.out.println(emp.toString()+emp.earning);
                }
                catch (EmployeeException employeeException)
                {
                    System.out.println(employeeException.getCode());
                    System.out.println(employeeException.getMessage());
                }
            }
            else if(command.equals("delete"))
            {
                String empS=scanner.next();
                try
                {
                    Employee emp= f.deleteEmployee(empS);
                    System.out.println(emp.toString()+emp.earning);
                }
                catch (EmployeeException employeeException)
                {
                    System.out.println(employeeException.getCode());
                    System.out.println(employeeException.getMessage());
                }
            }
            //更新命令是和add命令差不多的命令，只是参数多了一个
            else if(command.equals("update"))
            {
                //先建立一个空的临时雇员，没有任何信息，之后会重新给他覆盖信息
                Employee aEmployee=new basePlusCommisionEmployee();
                int type=scanner.nextInt();
                switch (type)
                {
                    case 0:
                        aEmployee=new SalaridEmployee(scanner.next(),scanner.next(),scanner.next(), scanner.nextDouble());
                        break;
                    case 1:
                        aEmployee= new HourlyEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    case 2:
                        aEmployee= new CommisionEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    case 3:
                        aEmployee=new basePlusCommisionEmployee(scanner.next(),scanner.next(),scanner.next(),scanner.nextDouble(),scanner.nextDouble(),scanner.nextDouble());
                        break;
                    default:
                        System.out.println("非法输入");
                        return;
                }
                try
                {
                    //如果添加成功，还要将添加的对象的信息显示出来
                    Employee emp = f.updateEmployee(aEmployee.getSocialSecurityNumber(),aEmployee);
                    System.out.println(emp.toString()+emp.earning);
                }
                catch (EmployeeException employeeException)
                {
                    System.out.println(employeeException.getCode());
                    System.out.println(employeeException.getMessage());
                }
            }
            else if(command.equals("print"))
            {
                f.printEmployees();
            }
            else if(command.equals("exit"))
            {
                return;
            }
            else
            {
                System.out.println("无效命令");
            }
        }
        scanner.close();
    }
}
