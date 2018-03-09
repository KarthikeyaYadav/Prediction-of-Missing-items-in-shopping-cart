/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwdm;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class fread {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        HashMap<String, Integer> map = new HashMap<>();
        File file=new File("F:\\items.txt");//Reading Items From File
        File file1=new File("F:\\itemss.txt");//Reading Item Class From File
        BufferedReader br=new BufferedReader(new FileReader(file));
        BufferedReader br1=new BufferedReader(new FileReader(file1));
        String st,st1;
        int ff;
        while((st=br.readLine())!=null && (st1=br1.readLine())!=null){
            int i=Integer.parseInt(st);
            map.put(st1,i);//Creating hashmap for items along with their classes
             }
         print(map);
         System.out.println();
        Scanner scanner = new Scanner(new FileInputStream("F:\\input.txt"));//Reading Current Transaction Items From File
        String offer[]=new String[100];
        int oin=0;
        ArrayList<String> list=new ArrayList<String>();
        int a[]=new int[100];
        int ind=0;
        while(scanner.hasNext()){
            list.add(scanner.next());//Adding Current Transaction Item Names
            a[ind++]=Integer.parseInt(scanner.next());//Adding Quantity Of above item
        }
        
        int sum=0;ind=0;int count=0;
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
        System.out.println();
        String result[]=new String[100];
        
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
        for(int h=0;h<r;h++){
             ff=0;
            for(String q:list){
                if(q.equals(result[h])){
                    ff=1;
                    break;
                }
            }
            if(ff==0)
            System.out.println(result[h]);
        }
        System.out.println();
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
                if(offer[h+1]!=null)
            System.out.println("Offer for "+offer[h++]+": "+offer[h]);}
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
