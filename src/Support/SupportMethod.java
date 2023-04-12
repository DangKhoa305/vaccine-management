/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author dangk
 */
public class SupportMethod {
    Scanner sc = new Scanner(System.in);
    
    public String printDate(Date d){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(d==null) return null;
        return df.format(d);
    }
    public Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }
    
    
    public String fixContent(String blank){
        String content; int c;
        do{
            c = 1;
            content = sc.nextLine();
            if (content.length() < 1){
                c = 0;
                System.out.println("This '"+blank+ "' can't be emty");
                System.out.print("Please Re-Enter: ");
            }
        }while(c == 0);
        return content;
    }
    public int switchInt(int num){
        int c ,a = 0;
        do{
            try{
                c = 1;
                a = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {   
                System.out.print("It isn't an Integer. Please re-insert:");
                c = 0;
            }
            if(a < 0 || a > num){
                System.out.print("The number is out range. Please re-insert:");
            }
        }while(c == 0 ||(a < 0 || a > num));
        return a;
    }


public Date getDate() {
        Scanner sc = new Scanner(System.in);
        String inputDate;
        Date date = null;

        while (true) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
               inputDate = sc.nextLine();
                date = df.parse(inputDate);
                return date;
            } catch (Exception e) {
                System.out.println("Invalid input date!!!.Please Re-enter:");
            }
            

        }
    }

}
