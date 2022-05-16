import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * @author Pzr
 * @create 2022/5/9 - 16:37
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
         ArrayStack stack = new ArrayStack(4);
            String key ="";
            boolean loop = true;//控制是否退出菜单
            Scanner scanner = new Scanner(System.in);

            while(loop) {
                key = scanner.next();
                switch (key) {
                    case "show":
                        stack.list();
                        break;
                    case "push":

                        int value = scanner.nextInt();
                        stack.push(value);

                        break;
                    case "pop":
                        try {
                            int res = stack.pop();
                            System.out.printf("出栈的数据为%d\n", res);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "exit":
                        scanner.close();//scanner是一个流
                        loop = false;
                        break;
                    default:
                        break;
                }
            }
        System.out.println("程序退出");
    }
}
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private  int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            return;
        }
        stack[++top] = value;
    }
    //出栈
    public int  pop(){
        if(isEmpty()){
            throw  new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈，ps:从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，无数据");
            return;
        }
        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
