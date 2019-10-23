import java.util.Scanner;

public class Poker {
    private static String[][] pokers=new String[4][15];
    private static String[][] newPokers =new String[4][15];
    private static int[][] size = new int[4][15];
    private int times;//洗牌次数

    private String[][] poker(){
        String[] nums=new String[14];
        String[] suit=new String[4];
        for (int i = 0; i <14 ; i++) {
            nums[i]=i+"";
        }
        nums[1]="A";
        nums[11]="J";
        nums[12]="Q";
        nums[13]="K";
        suit[0]="spades";
        suit[1]="clubs";
        suit[2]="hearts";
        suit[3]="dias";
        for (int i = 0; i <4 ; i++) {
            pokers[i][0]=" ";
        }
        for (int i = 0; i <4 ; i++) {
            for (int j = 1; j <14 ; j++) {
                pokers[i][j]=suit[i]+nums[j]+"  ";
            }
        }
        pokers[0][14]="Black King  ";
        pokers[1][14]=" ";
        pokers[2][14]="Red King  ";
        pokers[3][14]=" ";
        for (int i = 0; i <4 ; i++) {
            for (int j = 1; j <15; j++) {
                size[i][j]=j;
            }
        }
        return pokers;
    }
    public void print(){
        poker();
        for (int i = 0; i <4 ; i++) {
            for (int j = 1; j <15 ; j++) {
                System.out.print(pokers[i][j]);
            }
            System.out.println("");
        }
    }
    public void wash() {
        times = (int)((Math.random()*10+1)*(Math.random()*100+1));//进行100-1000次的交换
        String[][] mark = new String[4][15];
        newPokers =poker();
        while (times > 0) {
            int a = (int) (Math.random() * 4);
            int b = (int) (Math.random() * 14);
            int c = (int) (Math.random() * 4);
            int d = (int) (Math.random() * 14);
            int e=0;//用于交换
            if(newPokers[a][b].compareTo(" ")!=0&&newPokers[c][d].compareTo(" ")!=0){//两张都不为空牌
                mark[a][b]=newPokers[a][b];
                newPokers[a][b]=newPokers[c][d];
                newPokers[c][d]=mark[a][b];//交换牌
                e=size[a][b];
                size[a][b]=size[c][d];
                size[c][d]=e;//用于比较大小
                times=times-1;//次数减少一
            }
        }
    }

    public void showNewPokers(){
        for (int i = 0; i <4 ; i++) {
            for (int j = 1; j <15 ; j++) {
                System.out.print(newPokers[i][j]);
            }
            System.out.println("");
        }
    }

    public int play(){
        //showNewPokers();//Testing
        String[][] mark=newPokers;
        String[] myCard=new String[5];
        int[] mySize=new int[5];
        //抽牌
        int i=4;
        while (i>=0) {
            int a = (int) (Math.random() * 4);
            int b = (int) (Math.random() * 14);
            if(mark[a][b].compareTo(" ")!=0&&mark[a][b].compareTo(pokers[0][14])!=0&&mark[a][b].compareTo(pokers[2][14])!=0){
                myCard[i]=mark[a][b];
                mark[a][b]=" ";
                mySize[i]=size[a][b];
                times=times-1;
                i--;
            }
        }

        //计算分数
        int[] score=new int[5];
        int[] bull=new int[10];
        for (int j = 0; j <5 ; j++) {
            score[j]=mySize[j];
            if(score[j]>10){
                score[j]=10;//JQK都为10
            }
        }
        for (int m = 0; m <10; m++) {
            for (int j = 0; j <3 ; j++) {
                for (int k = j+1; k <4 ; k++) {
                    for (int l = j+2; l <5 ; l++) {
                        int a=score[j];
                        int b=score[k];
                        int c=score[l];
                        bull[m]=(a+b+c)%10;//取余
                    }
                }
            }
        }
        int max=bull[0];
        for (int j = 0; j <9 ; j++) {
            int max1=Math.max(max,bull[j+1]);
            max=max1;
        }
        return max;
    }
}
