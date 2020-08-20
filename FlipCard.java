package com.company;

public class FlipCard {
    int[][] cards = new int[4][6];

    public FlipCard(){
        int[] numbers = new int[12];
        int num;
        for(int i = 0; i< cards.length; i++){
            for(int j = 0; j<cards[0].length; j++){
                while(true){
                    num = (int)(Math.random()*12+1);
                    if(numbers[num-1]!=2){
                        numbers[num-1] ++;
                        break;
                    }
                }
                cards[i][j] = num;
            }
        }

        for(int i = 0; i<cards.length; i++){
            for(int j = 0; j<cards[0].length; j++){
                System.out.print(cards[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkCards(int one, int two){
        if(one==two){
            return true;
        }
        else{
            return false;
        }
    }
}
