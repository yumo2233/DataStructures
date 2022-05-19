import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author Pzr
 * @create 2022/5/19 - 17:42
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("用数组模拟环形队列的案例");
        CircleArray arrayqueue = new CircleArray(4);//实际可用长度为3，有一个预留空间
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


class CircleArray{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;


    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    //判断栈是否为满
     public boolean isFull() {
         return (rear + 1) % maxSize == front;//数组预留一个空间区别栈满和栈空的情况
     }
     public boolean isEmpty(){
        return rear == front;

    }
    //添加数据
     public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        //将rear后移
         rear =( rear + 1)%maxSize;
     }
     //取出数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        //先把front对应的值保留到一个临时变量
        //将front后移
        //将临时变量返回
        int value = arr[front];
        front = (front + 1)%maxSize;
        return value;
    }
    //求有效数据的个数
    public int size(){
        return (rear + maxSize - front)%maxSize;
        ////rear-front是核心，+maxSize防止负数，取模得到一倍结果,应对的是r-f为正数的情况，其实就是相减啊喂
    }
    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for(int i = front; i < front + size();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i%maxSize]);
        }
    }
    //peek()
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，没数据");
        }
        return arr[front];
    }
}


