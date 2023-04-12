/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Source.VaccineInjectionList;
import Support.Mymenu;
import Support.SupportMethod;
import java.util.Scanner;
/**
 *
 * @author dangk
 */
public class MainControl {
    public static void main(String[] args) {
        SupportMethod sp = new SupportMethod();
        VaccineInjectionList menu = new VaccineInjectionList();
        try{
            menu.ReadFile();
        }catch (Exception e) {
        }
      
        menu.writeStudent();
        menu.writeVaccine();
       
            Scanner sc = new Scanner(System.in);
            Mymenu strList = new Mymenu();
            System.out.println("Welcome to Vaccine Management - @ 2021 by <SE150493 - Nguyen Dang Khoa >");
            strList.add(" - Show information all students have been injected");
            strList.add(" - Add student's vaccine injection information ");
            strList.add(" - Updating information of students' for second injection");
            strList.add(" - Delete student vaccine injection information");
            strList.add(" - Search for injection information by studentID");
            strList.add(" - Save To File");
            strList.add(" - Read Student List");
            strList.add(" - Read Vaccine List");
            strList.add(" - Exit Program");
        
            int Choice;
        do {
            Choice = strList.getChoice();
            switch (Choice) {
                case 1:
                    menu.ReadList();
                    break;                    
                case 2:                    
                    menu.Add();
                  break;
                case 3:
                    menu.Update();
                    break;
                case 4:
                  menu.Delete();
                    break;
                case 5:
                    menu.Search();
                    break;   
                case 6:
                    menu.SaveToFile();
                    break;
                case 7:
                    menu.readStudentList();
                    break;
                case 8:
                    menu.readVaccineList();
                    break;
               
            }
        
        }while (Choice != 9);     
        System.out.println("You choose: " + Choice);  
     } 
}   
