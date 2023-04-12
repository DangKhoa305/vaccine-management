/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Support.SupportMethod;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dangk
 */
public class VaccineInjectionList{
    List<Student> studentList = new ArrayList<>();
    List<Vaccine> vaccineList = new ArrayList<>();
    List<VaccineInjection> vaccineInjectionList = new ArrayList<>();
    public VaccineInjectionList(){
        studentList = new ArrayList<>();
        vaccineList = new ArrayList<>();
        vaccineInjectionList = new ArrayList<>();
    }
    public VaccineInjectionList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    Scanner sc = new Scanner(System.in);
    SupportMethod sp = new SupportMethod();
    
    public void writeVaccine() {
        String fileName="vaccine.dat";
        try{           
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);            
            vaccineList.add(new Vaccine("Covid-V001","AstraZeneca"));
            vaccineList.add(new Vaccine("Covid-V002","SPUTNIK V"));
            vaccineList.add(new Vaccine("Covid-V003","Vero Cell"));
            vaccineList.add(new Vaccine("Covid-V004","Pfizer"));
            vaccineList.add(new Vaccine("Covid-V005","Moderna"));             
            for(Vaccine vc: vaccineList) {
                oStream.writeObject(vc);
            }           
            oStream.close();
            file.close();
            System.out.println("WriteFile "+fileName+" Successed!");
        } catch (Exception e){
            System.out.println("WriteFile "+fileName+" Fail!");
        }
    }


    public void readVaccineList(){
        System.out.printf("%-13s %-13s\n","VaccineID","VaccineName");
        for(Vaccine s : vaccineList){
            System.out.printf("%-13s %-13s\n",s.getVaccineID(),s.getVaccineName());
        }
    }
    
    public void writeStudent(){
        String fileName="student.dat";
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);            
            studentList.add(new Student("SE15000", "Hoa Doan"));
            studentList.add(new Student("SE15021", "Hai Doan"));
            studentList.add(new Student("SE15032", "Phong Ha"));
            studentList.add(new Student("SE15001", "Nhat Tam"));
            studentList.add(new Student("SE15012", "Lam Thoi"));
            studentList.add(new Student("SE15002", "Hoa Ha"));
            studentList.add(new Student("SE15123", "Hai Loi"));
            studentList.add(new Student("SE15033", "Kiep Ha"));
            studentList.add(new Student("SE15011", "Nguyet Ha"));
            studentList.add(new Student("SE15017", "Lam Sac"));                        
            for(Student s : studentList){
                oStream.writeObject(s);
            }
            oStream.close();
            file.close();
            System.out.println("WriteFile "+fileName+" Successed!");
        } catch (Exception e){
            System.out.println("WriteFile "+fileName+" Fail!");
        }
    }

    public void readStudentList(){
        System.out.printf("%-13s %-13s\n","StudentID","StudentName");
        for(Student s : studentList){
            System.out.printf("%-13s %-13s\n",s.getStudentID(),s.getStudentName());
        }
    }
    
    private String CheckVaccine(){
        String ID = ""; 
        int flag = 1;
        do{
            ID = sp.fixContent("VaccineID");
            for (Vaccine s : this.vaccineList) {
                if (s.getVaccineID().equals(ID)) {
                    flag = 0;
                    System.out.println("Vaccine found: "+ s.getVaccineName()+"\n");
                }
            }
            if (flag==1) {
                System.out.println("Vaccine not exist.Please re-enter Vaccine ID:  ");
            }
            if ( flag == 0) {
                break;
            }  
        }while(true);
        return ID;
    }
    
    private String CheckInjectID(){
        String ID; 
        do{
            
            int flag = 0;
            ID = sp.fixContent("VaccineInjectionID");
            for (VaccineInjection vi : vaccineInjectionList) {
                if (vi.getVaccineInjectionID().equals(ID)) {
                    flag++;
                    System.out.println("VaccineInjectionID exist.");
                    System.out.println("Please re-enter new one: ");
                    break;
                }
            }
            if ( flag == 0) {
                break;
            }  
        }while (true);
        return ID;
    }
    
    private String CheckVaccineName(String name){
        for(Vaccine s : this.vaccineList){
            if(s.getVaccineID().equals(name)){
                return s.getVaccineName();
            }
        }
        return null;
    }
    
    private String TakeStudentName(String studentID){
        for(Student s: this.studentList){
            if(s.getStudentID().equals(studentID)){
              return s.getStudentName();
        }
    }
        return null;
    }
    
    private Date CheckSecondVacineDay(Date date){
        Date secondDay = null;
        {
                while(true){
                    secondDay = sp.getDate();
                    Date compareDayMin = sp.addDays(date, 28);
                    Date compareDayMax = sp.addDays(date, 84);
                    if(secondDay.before(compareDayMin)){
                        System.out.println("The time from the first injection is less than 4 weeks.\nPlease Re-Enter: ");
                    }else if(secondDay.after(compareDayMax)){
                        System.out.println("The time from the first injection is more than 12 weeks.\nPlease Re-Enter: ");
                    }else{
                        break;
                    }
                }
        }
        return secondDay;
    }

        
 private String findStudentID_ListInjection(String studentID)
    {
        for (VaccineInjection vi : vaccineInjectionList) 
             if (vi.getStudentID().equals(studentID))  
                return studentID;
        return null;
    }
private String findStudentID(String studentID)
    {
        for (Student vi : studentList) 
            if (vi.getStudentID().equals(studentID)) 
                return studentID;
        return null;
    }
    private String inputStudentID()
    {
        String ID;
        while (true)
        {
             ID = sp.fixContent("StudentID");           
            String x = findStudentID(ID);           
            if (x == null)
                System.out.println("This Student did Not Exist In Student List! Input Again.");
            else
            {
                
                String y = findStudentID_ListInjection(ID);

                
                if (y == null)
                    return ID;
                else
                    System.out.println("This Student Injected Already! Input Again.");
            }
        }
    }

    public void Add(){
        while(true){
            System.out.println("Enter the Injection ID:");
            String injectID = CheckInjectID();
            
            System.out.println("Enter Student's ID: ");
            readStudentList();
            String studentID = inputStudentID();
            String studentName = TakeStudentName(studentID);
                    
            System.out.println("Enter the VaccineID:");
            readVaccineList();
            System.out.printf("Input:");
            String VaccineID = CheckVaccine();
                        
            System.out.println("Enter the first Injection place:");
            String place1 = sp.fixContent("First Place");
                        
            System.out.println("Enter the first Injection Date:");               
            Date injectDate1 = sp.getDate();
                       
                        
            String place2 = null;                                   
            Date injectDate2 = null;

            String VaccineName = CheckVaccineName(VaccineID);
                        
            VaccineInjection VI = new VaccineInjection(injectID,place1,place2,injectDate1,injectDate2,studentID,studentName,VaccineID,VaccineName);
            vaccineInjectionList.add(VI);
            System.out.println("Add Successed");
            System.out.printf("Do you want to add more? (1:yes | 0:No): \n-> choose:  ");
            int chose = sp.switchInt(1);
            if (chose == 0){
                break;
            }
        }
    }
    
    public void ReadList(){
        if (vaccineInjectionList.isEmpty()){ System.out.println("The list is EMTPY!"); }
        else{
            System.out.printf("%-13s %-13s %-20s %-13s %-20s %-13s %-20s %-10s\n","InjectionID","StudentID","StudentName","FirstPlace","FirstDateInjection","SecondPlace","SecondDateInjection","VaccineName");
            for(VaccineInjection vi : vaccineInjectionList){
             vi.ShowInfo();
            }
        }
    }
    

    
    public void Update(){
        int flag =0;
        if (vaccineInjectionList.isEmpty()){ System.out.println("The list is EMTPY!"); }
        else {
            System.out.println("Enter Injection's ID: "); 
            String InjectionID = sp.fixContent("Injection ID");
            for(VaccineInjection vi : vaccineInjectionList){
                if(vi.getSecondPlace()!=null && vi.getSecondDateInjection()!=null ){
                        System.out.println("Student has completed 2 injections!");
                        flag =1;
                        break;
                    }
                if(vi.getVaccineInjectionID().equals(InjectionID)){   
                    flag=1;
                            
                    System.out.printf("%-13s %-13s %-20s %-13s %-20s %-13s %-20s %-10s\n","InjectionID","StudentID","StudentName","FirstPlace","FirstDateInjection","SecondPlace","SecondDateInjection","VaccineName");
                    vi.ShowInfo();
                    System.out.println("Enter the second Injection place:");
                    vi.setSecondPlace(sp.fixContent("Second place."));
                    System.out.println("Enter the second Injection Date:");
                    vi.setSecondDateInjection(CheckSecondVacineDay(vi.getFirstDateInjection()));
                    System.out.println("-> Update completed.");
                     System.out.printf("%-13s %-13s %-20s %-13s %-20s %-13s %-20s %-10s\n","InjectionID","StudentID","StudentName","FirstPlace","FirstDateInjection","SecondPlace","SecondDateInjection","VaccineName");
                    vi.ShowInfo();
                    if(vi.getSecondPlace()!=null && vi.getSecondDateInjection()!=null && !"".equals(vi.getFirstPlace()) && !"".equals(sp.printDate(vi.getFirstDateInjection()))){
                        System.out.println("Student has completed 2 injections!");
                        break;
                    }
                    break;
                }    
            }
            if (flag == 0) {
                System.out.println("This Injection ID does not exist!");
            }
        }
    }
    
    
    public void Delete(){
        int flag = 0;
        if (vaccineInjectionList.isEmpty()){ System.out.println("The list is EMTPY!"); }
        else {
            try{
                System.out.println("Enter Injection's ID: ");      
                String deleteID = sp.fixContent("Injection ID");    
                for (VaccineInjection vi : vaccineInjectionList) {
                    if (vi.getVaccineInjectionID().equals(deleteID)) {
                        flag = 1;
                        System.out.printf("Do you want to remove this Injection? (1:yes | 0:No): \n-> choose:  ");
                        int chose = sp.switchInt(1);
                        if (chose == 0){
                            System.out.println("Delete fail.");    
                            break;
                        }else {             
                            flag = 2;
                            this.vaccineInjectionList.remove(vi);
                            break;
                        }
                    }
                }
                if (flag == 0) {
                    System.out.println("This Injection ID does not exist!");
                }
            }catch(Exception e) {         
            }
            if (flag == 2) System.out.println("Delete Succeed!");
        }
    }

    public void Search(){
        int flag = 0;
        if (vaccineInjectionList.isEmpty()){ System.out.println("The list is EMTPY!"); }
        else {
            System.out.println("Enter Student ID: "); 
            String SearchName = sp.fixContent("Student ID"); 
            for (VaccineInjection vi : vaccineInjectionList) {
            if (vi.getStudentID().equals(SearchName)) {
            System.out.printf("%-13s %-13s %-20s %-13s %-20s %-13s %-20s %-10s\n","InjectionID","StudentID","StudentName","FirstPlace","FirstDateInjection","SecondPlace","SecondDateInjection","VaccineName");
            vi.ShowInfo();
                flag = 1;
            }
            else {
                flag = 0;
            }
        }
                        if (flag != 1) {
                System.out.println("Student ID not exist!");
            }
    }
    }
    
    
    public void SaveToFile(){
        String fileName="injection.dat";
        try{
           
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            
            for(VaccineInjection vi : vaccineInjectionList){
                oStream.writeObject(vi);
            }
            
            oStream.close();
            file.close();
            System.out.println("WriteFile "+fileName+" Successed!");
        } catch (Exception e){
            System.out.println("WriteFile "+fileName+" Fail!");
        }
    }
    
    
    public void ReadFile(){
        String fileName="injection.dat";
        
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream oStream = new ObjectInputStream(file);
           
            
            while (true){
              VaccineInjection read = (VaccineInjection) 
                      oStream.readObject();
                vaccineInjectionList.add(read);
                if(file.available()< 1) break;
            }
 
            oStream.close();
            file.close();
            System.out.println("ReadFile "+fileName+" Successed!");
        } catch (Exception e){
            System.out.println("ReadFile "+fileName+" Fail!");
        }
    }    
}
