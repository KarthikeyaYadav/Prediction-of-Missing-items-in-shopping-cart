/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dwdm;

/**
 *
 * @author hp
 */
public class Dwdm {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        String i2[];
        i2 = new String[12];
       // i2[12] = {,,,,,,};
        i2[0]="packaged_vegetables_fruits";
        i2[1]="yogurt";
        i2[2]="eggs";
        i2[3]="fresh_vegetables";
        i2[4]="milk";
        i2[5]="packaged_cheese";
        i2[6]="soy_lactosefree";
        i2[7]="bread";
        i2[8]="packaged_vegetables_fruits";
        i2[9]="chips_pretzels";
        i2[10]="packaged_cheese";
        i2[11]="fresh_fruits";
        String r2[];
        r2 = new String[12];
        r2[0]="fresh_fruits";
        r2[1]="fresh_fruits";
        r2[2]="fresh_fruits";
        r2[3]="fresh_fruits";
        r2[4]="fresh_fruits";
        r2[5]="fresh_fruits";
        r2[6]="fresh_fruits";
        r2[7]="fresh_fruits";
        r2[8]="fresh_vegetables";
        r2[9]="fresh_fruits";
        r2[10]="fresh_vegetables";
        r2[11]="fresh_vegetables";
        String i3[][];
        i3 = new String[11][2];
        i3[0][0]="packaged_vegetables_fruits";
        i3[0][1]="yogurt";
        i3[1][0]="fresh_vegetables";
        i3[1][1]="yogurt";
        i3[2][0]="fresh_vegetables";
        i3[2][1]="packaged_vegetables_fruits";
        i3[3][0]="fresh_vegetables";
        i3[3][1]="milk";
        i3[4][0]="fresh_vegetables";
        i3[4][1]="packaged_cheese";
        i3[5][0]="fresh_fruits";
        i3[5][1]="packaged_vegetables_fruits";
        i3[6][0]="fresh_fruits";
        i3[6][1]="packaged_cheese";
        i3[7][0]="fresh_fruits";
        i3[7][1]="yogurt";
        i3[8][0]="fresh_fruits";
        i3[8][1]="milk";
        i3[9][0]="fresh_fruits";
        i3[9][1]="fresh_vegetables";
        i3[10][0]="fresh_fruits";
        i3[10][1]="yogurt";
        String r3[];
        r3 = new String[11];
        r3[0]="fresh_fruits";
        r3[1]="fresh_fruits";
        r3[2]="fresh_fruits";
        r3[3]="fresh_fruits";
        r3[4]="fresh_fruits";
        r3[5]="fresh_vegetables";
        r3[6]="fresh_vegetables";
        r3[7]="fresh_vegetables";
        r3[8]="fresh_vegetables";
        r3[9]="packaged_vegetables_fruits";
        r3[10]="packaged_vegetables_fruits";
        String input[]=new String[11];
        input[0]="milk";
        input[1]="yogurt";
        input[2]="packaged_vegetables_fruits";
        input[3]="dog_food_care";
        input[4]="frozen_breads_doughs";
        input[5]="soy_lactosefree";
        input[6]="coffee";
        input[7]="bread";
        input[8]="beauty";
        input[9]="butter";
        input[10]="chips_pretzels";
        String results[]=new String[20];
        int count=0;
        int flag=0;
     for(int i=0;i<11;i++){
         for(int j=0;j<12;j++){
         if(input[i].equals(i2[j])){
             flag=0;
             //System.out.println(input[i]+"->"+r2[j]);
             for(int k=0;k<count && flag==0;k++){
                  flag=0;
                 if(r2[j].equals(results[k]))
                     flag=1;
             }
             if(flag==0){
             results[count]=r2[j];
             count++;}
         }
     }}
     flag=0;
     for(int i=0;i<10;i++){
         for(int j=i+1;j<11;j++){
             for(int k=0;k<11;k++){
                 if((input[i].equals(i3[k][0]) && input[j].equals(i3[k][1])) ||(input[i].equals(i3[k][1]) && input[j].equals(i3[k][0]))){
                 flag=0;   
                 //System.out.println(input[i]+"  "+input[j]+"->"+r3[k]); 
                    for(int l=0;l<count && flag==0;l++){
                  flag=0;
                 if(r2[j].equals(results[l]))
                     flag=1;
             }
                    if(flag==0){
             results[count]=r2[j];
             count++;}
                 }
             }
         }
     }
        if(count>0){
            System.out.println("Predicted Items For Your Transaction are:");
            for(int i=0;i<count;i++){
                System.out.println(results[i]);
            }
        }
    }
    
    
}
