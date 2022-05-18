import java.util.*;

/**
 * @author Pzr
 * @create 2022/5/18 - 16:38
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayqueue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s:显示队列");
            System.out.println("e:退出程序");
            System.out.println("a:添加数据到队列");
            System.out.println("g:从队列取出数据");
            System.out.println("h:查看队列头数据");
            key = scanner.next().charAt(0);
            switch(key){
                case's':
                    arrayqueue.showQueue();
                    break;
                case'a':
                    System.out.println("输入一个数据");
                    int value = scanner.nextInt();
                    arrayqueue.addQueue(value);
                    break;
                case'g':
                    try{
                        int res = arrayqueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'h':
                    try{
                        int res = arrayqueue.headQueue();
                        System.out.printf("队列的头数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class ArrayQueue{
    private int maxSize;
    private int front;//指向队列前一个，默认是-1
    private int rear;
    private int[] arr;

    //创建队列的构造器
    public ArrayQueue(int arrMaxsize){
        maxSize = arrMaxsize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[++rear] = n;
    }
    //出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[++front];
    }
    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，无数据");
            return;
        }
        for(int i :arr){
            System.out.println(i);//这边的i就是arr[i] ，如果写成arr[i]就相当于arr[arr[i]]
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.printf("arr[%d]=%d\n", i, arr[i]);
//        }
    }
    //显示队头是谁，类似栈的peek()
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无数据");
        }
        return arr[front+1];
    }
}
