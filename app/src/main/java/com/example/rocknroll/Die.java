package com.example.rocknroll;
import java.util.Random;

public class Die {
    String Type;
    int Sides;
    int SideUp;
    int rollCount;


    int RollTheDie(int Sides){
        //int rnd=0;
        Random random = new Random();

        while (true){
            SideUp = random.nextInt(Sides+1);
            if(SideUp !=0) break;
        }

        return SideUp;


    }
//    public  Die(){
//        this.Type = "d6";
//        this.Sides = 6;
//        this.SideUp = RollTheDie(this.Sides);
//    }
//
//    //1 argument constructor
//    public Die(int sides)
//    {
//        this.Sides=sides;
//        this.Type="d"+sides;
//        this.SideUp = RollTheDie(sides);
//
//    }
//
//    //2 argument constructor
//    Die(int sides, String type)
//    {
//        this.Sides=sides;
//        this.Type="d"+sides;
//        this.SideUp = RollTheDie(sides);
//
//    }


    Boolean RollItTillGet(int target, int sides)
    {
        Boolean matchedIndicator=false;
        while(true)
        {
            int sideup =  RollTheDie(sides);
            rollCount=rollCount+1;
            if(sideup == target)
            {
                matchedIndicator = true;
                break;
            }
        }
        return matchedIndicator;
    }


}


