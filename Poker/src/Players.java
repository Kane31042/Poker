import java.util.Scanner;

public class Players {
    private int number;
    private int numberOfPlayers;
    private String[] nameList=new String[5];
    private String[] serifuWin=new String[5];
    private String[] serifuLose=new String[5];

    public void test(){
        MutableStringArray add=new MutableStringArray();
    }

    //设定玩家名字和名台词（不是）
    public MutableStringArray getNameList(){
        MutableStringArray nameList1=new MutableStringArray();
        nameList[0]="DIO";
        nameList[1]="空条JO太郎";
        nameList[2]="卡兹";
        nameList[3]="迪亚波罗波罗哒（不是）";
        nameList[4]="东方JO助";
        nameList1.add(nameList);
        nameList1.add("布加拉提");
        nameList1.test();
        return nameList1;
    }
    public String[] getSerifuWin(){
        serifuWin[0]="这份胜利是属于konoDIO哒！";
        serifuWin[1]="你的败因只有一个，那就是你激怒我了!";
        serifuWin[2]= "WIN~WIN~WIN~WIN~WIN~WIN~WIN~WIN~WIN~WIN~WIN~！";
        serifuWin[3]="「帝王」就是我迪亚波罗！依然从未改变！";
        serifuWin[4]="好清爽的感觉！就像是在新年的早上换上新内裤一样的感觉啊！";
        return serifuWin;
    }
    public String[] getSerifuLose(){
        serifuLose[0]="这也在你的算计之中吗？，JOJO！";
        serifuLose[1]="无敌的白金之星倒下了！";
        serifuLose[2]="卡兹停止了思考";
        serifuLose[3]="不要靠近我啊啊啊啊啊啊啊啊！";
        serifuLose[4]="快用你无敌的白金之星想个法子啊————！";
        return serifuLose;
    }

    //设定玩家个数
    private void setNumberOfPlayers(){
        System.out.print("请输入玩家个数（2-5）：");
        Scanner a=new Scanner(System.in);
        numberOfPlayers=a.nextInt();
    }

    //随机生成玩家
    private String[][] players(){
        getNameList();
        getSerifuWin();
        getSerifuLose();
        String[] nameList1=nameList;
        int b=numberOfPlayers;
        String[][] players=new String[b][3];
        System.out.println("本次玩家有：");
        while (b>0) {
            int c = (int) (Math.random() * 5);
            if(nameList1[c].compareTo(" ")!=0){
                players[b-1][0]=nameList1[c];
                nameList1[c]=" ";
                players[b-1][1]=serifuWin[c];
                players[b-1][2]=serifuLose[c];
                System.out.println(players[b-1][0]);
                b=b-1;

            }
        }
        return players;
    }

    private int play(){
        Poker play1=new Poker();
        int a=play1.play();
        return a;
    }
    public void start(){
        setNumberOfPlayers();
        String[][] players1=players();
        System.out.println("为玩家展示新牌：");
        Poker poker=new Poker();
        poker.print();
        System.out.println("正在洗牌");
        poker.wash();
        System.out.println("洗牌完成：");
        poker.showNewPokers();
        int[] result=new int[10];
        for (int i = 0; i <numberOfPlayers ; i++) {
            System.out.println("现在是"+players1[i][0]+"的回合");
            int a=play();
            result[i]=a;
            System.out.println(players1[i][0]+"是"+a+"牛");
        }
        //进行比较
        int max=result[0];
        int win=0;
        for (int j = 0; j <9 ; j++) {
            int max1=Math.max(max,result[j+1]);
            if(max!=max1){
                max=max1;
                win=j+1;
            }
        }
        System.out.println("胜者是："+players1[win][0]+"!");
        System.out.println(players1[win][0]+":"+players1[win][1]);
        for (int i = 0; i <numberOfPlayers ; i++) {
            if(i!=win){
                System.out.println(players1[i][0]+":"+players1[i][2]);
            }
        }
        System.out.println("比赛结束。");
    }
}
