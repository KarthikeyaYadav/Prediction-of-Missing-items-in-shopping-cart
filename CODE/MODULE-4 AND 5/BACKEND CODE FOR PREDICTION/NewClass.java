/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingdemoexample;
import com.mysql.jdbc.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class NewClass extends javax.swing.JFrame {
    
       
    NewClass() throws FileNotFoundException, IOException{
         try {
             Connection  con=null;
             PreparedStatement pstmt=null;
             ResultSet rs;
             try {
                 Class.forName("com.mysql.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dwdm","root","pass");
             } catch (Exception e) {
                 System.out.println(e);
                 System.exit(0);
             }
             String selsql="select * from probability where order_id=?";
            
             HashMap<String, Integer> map = new HashMap<>();
             final JFrame frame = new JFrame("Demo program for JFrame");
             JTextArea textFieldUserName = new JTextArea();
             JButton b1=new JButton("Back to Transaction");
             // textFieldUserName.setBounds(50,50, 150,20);
             // b1.setBounds(50,100,95,30);
             // b1.setSize(50, 50);
             //b1.addActionListener(new ActionListener(){
             frame.add(textFieldUserName);
             //frame.add(b1);
             frame.setSize(400,400);
             File file=new File("F:\\items.txt");//Reading Items From File
             File file1=new File("F:\\itemss.txt");//Reading Item Class From File
             BufferedReader br=new BufferedReader(new FileReader(file));
             BufferedReader br1=new BufferedReader(new FileReader(file1));
             String st,st1,st2,o="";
             int ff;
             while((st=br.readLine())!=null && (st1=br1.readLine())!=null){
                 //System.out.print(st1+" ");
                 //System.out.println(st);
                 int i=Integer.parseInt(st);
                 map.put(st1,i);//Creating hashmap for items along with their classes
                 
                 
             }
             // System.out.println("Customer id:" + cid);
             print(map);
             System.out.println();
             Scanner scanner = new Scanner(new FileInputStream("F:\\itemz1.txt"));//Reading Current Transaction Items From File
             String offer[]=new String[100];
             int oin=0;
             String cid=scanner.next();
             ArrayList<String> list1=new ArrayList<String>();
             System.out.println("Customer id:"+cid);
              pstmt=con.prepareStatement(selsql);
             pstmt.setString(1,cid);
             rs=pstmt.executeQuery();
              
             textFieldUserName.append(" Customer id: "+cid+"\n\n");
             ArrayList<String> list=new ArrayList<String>();
             int a[]=new int[100];
             int ind=0;
             while(scanner.hasNext()){
                 list.add(scanner.next());//Adding Current Transaction Item Names
                 a[ind++]=Integer.parseInt(scanner.next());//Adding Quantity Of above item
             }
             BufferedReader br2=new BufferedReader(new FileReader(file1));
              while(rs.next()){
                  while((st2=br2.readLine())!=null){
                      
                  
                  int f1=0;
                 //st2=br2.readLine();
                float prob= Float.parseFloat(rs.getString(st2));
               // System.out.println(prob);
               // System.out.print(st2+" ");
                if(prob>=0.5){
                    Iterator itrr=list.iterator();  
  while(itrr.hasNext()){ 
      String f2=itrr.next().toString();
      if(f2.equals(st2)){
          f1=1;
          break;
      }
          
  // System.out.println(f2);  
  }
if(f1==0){
    list1.add(st2);
} 
                }
                  }
                 //System.out.print(st2+" ");
             }
             int sum=0;
             ind=0;
             int count=0;
             for(String q:list){
                 if(map.containsKey(q)){
                     Integer z=map.get(q);//Retrieving Class of Current item
                     Scanner sc = new Scanner(new FileInputStream("F:\\offer1.txt"));//Reading Offers On an item
                     Scanner sc1 = new Scanner(new FileInputStream("F:\\offer2.txt"));//Reading combo offers
                     while(sc.hasNext()){
                         String b=sc.next();
                         int i1=sc.nextInt();
                         if(b.equals(q) && i1>=50 && z>1){
                             z--;
                             break;
                         }
                         
                     }
                     count=count+a[ind];
                     sum=sum+(z*a[ind++]);
                     while(sc1.hasNext()){
                         String d1=sc1.next();
                         String d2=sc1.next();
                         if(d1.equals(q)){
                             offer[oin++]=q;
                             offer[oin++]=d2;
                             break;
                         }
                         /* else if(d2.equals(q)){
                         offer[oin++]=q;
                         offer[oin++]=d1;
                         break;
                         }*/
                         
                     }
                 }
             }
             sum=sum/count;
             int cls;
             if(sum<=1.5)
                 cls=1;
             else if(sum<=2.5)
                 cls=2;
             else cls=3;
             System.out.println("Class of Current Customer is "+cls);//Printing class of overall Transaction.
             //o=o+"Class of Current Customer is "+cls+"\n";
             textFieldUserName.append("Class of Current Customer is "+cls+"\n\n");
             System.out.println();
             String result[]=new String[100];
             textFieldUserName.append("Recommendations Based on your Previous history :\n");
             Iterator itr=list1.iterator();  
  while(itr.hasNext()){
      //System.out.println("hi");
       textFieldUserName.append(itr.next().toString()+"\n");
  }
             int r=0;
             if(cls==1){
                 Scanner sc = new Scanner(new FileInputStream("F:\\o1op.txt"));//Reading Class 1 Assosiation Rules Generated from Weka.
                 String i1[]=new String[100];
                 String o1[]=new String[100];
                 int ind1=0;
                 while(sc.hasNext()){
                     i1[ind1]=sc.next();//Reading Antecedent Of generated Rules
                     o1[ind1++]=sc.next();//Reading Consequent Of generated Rules
                 }
                 for(String q:list){
                     for(int g=0;g<ind1;g++){
                         if(i1[g].equals(q)){
                             int flag=0;
                             for(int p=0;p<r;p++){
                                 if(result[p].equals(o1[g])){//Checking For Non-Repetition of Predicted Items
                                     flag=1;
                                     break;
                                 }
                             }
                             if(flag==0){
                                 result[r++]=o1[g];
                             }
                         }
                     }
                 }
             }
             else if(cls==2){
                 Scanner sc1 = new Scanner(new FileInputStream("F:\\o2op1.txt"));//Reading Class 2 Assosiation Rules with one Antecedent Generated from Weka.
                 Scanner sc2 = new Scanner(new FileInputStream("F:\\o2op2.txt"));//Reading Class 2 Assosiation Rules with two Antecedent Generated from Weka.
                 String i1[]=new String[100];
                 String o1[]=new String[100];
                 String i2[][]=new String[100][2];
                 String o2[]=new String[100];
                 int ind1=0;
                 while(sc1.hasNext()){
                     i1[ind1]=sc1.next();//Reading Antecedent Of generated Rules
                     o1[ind1++]=sc1.next();//Reading Consequent Of generated Rules
                 }
                 int ind2=0;
                 while(sc2.hasNext()){
                     i2[ind2][0]=sc2.next();//Reading 1st Antecedent Of generated Rules
                     i2[ind2][1]=sc2.next();//Reading 2nd Antecedent Of generated Rules
                     o2[ind2++]=sc2.next();//Reading Consequent Of generated Rules
                 }
                 for(String q:list){
                     for(int g=0;g<ind1;g++){
                         if(i1[g].equals(q)){
                             int flag=0;
                             for(int p=0;p<r;p++){
                                 if(result[p].equals(o1[g])){
                                     flag=1;
                                     break;
                                 }
                             }
                             if(flag==0){
                                 result[r++]=o1[g];
                             }
                         }
                     }
                 }
                 for(String q:list){
                     for(String q1:list){
                         for(int g=0;g<ind2;g++){
                             if((i2[g][0].equals(q) && i2[g][1].equals(q1)) || (i2[g][1].equals(q) && i2[g][0].equals(q1))){
                                 int flag=0;
                                 for(int p=0;p<r;p++){
                                     if(result[p].equals(o2[g])){
                                         flag=1;
                                         break;
                                     }
                                 }
                                 if(flag==0){
                                     result[r++]=o1[g];
                                 }
                             }
                         }
                     }
                 }
             }
             else {
                 Scanner sc = new Scanner(new FileInputStream("F:\\o3op.txt"));//Reading Class 3 Assosiation Rules Generated from Weka.
                 String i1[]=new String[100];
                 String o1[]=new String[100];
                 int ind1=0;
                 while(sc.hasNext()){
                     i1[ind1]=sc.next();//Reading Antecedent Of generated Rules
                     o1[ind1++]=sc.next();//Reading Consequent Of generated Rules
                 }
                 for(String q:list){
                     for(int g=0;g<ind1;g++){
                         if(i1[g].equals(q)){
                             int flag=0;
                             for(int p=0;p<r;p++){
                                 if(result[p].equals(o1[g])){
                                     flag=1;
                                     break;
                                 }
                             }
                             if(flag==0){
                                 result[r++]=o1[g];
                             }
                         }
                     }
                 }
             }
             System.out.println("Recommended Items For Your Present Transaction :");
             //o=o+"Recommended Items For Your Present Transaction :/n";
             textFieldUserName.append("\n\nRecommended Items For Your Present Transaction :");
             for(int h=0;h<r;h++){
                 ff=0;
                 for(String q:list){
                     if(q.equals(result[h])){
                         ff=1;
                         break;
                     }
                 }
                 if(ff==0){
                     textFieldUserName.append("\n"+result[h]);
                     System.out.println(result[h]);}
             }
             System.out.println();
             textFieldUserName.append("\n\n\nOffers Available:");
             System.out.println("Offer Available:");
             for(int h=0;h<oin;h++){
                 ff=0;
                 for(String q:list){
                     if(q.equals(result[h])){
                         ff=1;
                         break;
                     }
                 }
                 if(ff==0){
                     if(offer[h+1]!=null){
                         textFieldUserName.append("\nOffer for "+offer[h]+": "+offer[h+1]);
                         System.out.println("Offer for "+offer[h++]+"-> "+offer[h]);}}
             }
             //textFieldUserName.setText(o);
             frame.setSize(500, 500);
             frame.setVisible(true);
             File realName2 = new File("F:\\itemz.txt");
             realName2.delete();
             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          } catch (SQLException ex) {
                     Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null,ex);
                          }
        
        
    }

    public static void print(Map<String, Integer> map) 
    {
        if (map.isEmpty()) 
        {
            System.out.println("map is empty");
        }
         
        else
        {
            System.out.println(map);
        }
    }
}
