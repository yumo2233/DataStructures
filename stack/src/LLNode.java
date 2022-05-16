/**
 * @author Pzr
 * @create 2022/5/9 - 17:37
 */
//用链表实现栈
public class LLNode {
    public static void main(String[] args) {
        LLStack llStack = new LLStack();
        llStack.push(1);
        llStack.push(2);
        llStack.push(3);
        System.out.println("栈里面的个数为" + llStack.getLength());
        llStack.pop();
        System.out.println("出栈一个后，栈顶的值为" + llStack.top());
    }
    private Object data;
    private LLNode next;
    public LLNode(){

    }
    public LLNode(Object data){
        this.data = data;
    }

    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data = data;
    }
    public LLNode getNext(){
        return next;
    }
    public void setNext(LLNode next){
        this.next = next;
    }
}

//实现栈的类
 class LLStack{
    LLNode headnode = null;
    public LLStack(){
        headnode = new LLNode(null);
    }
    public  boolean isEmpty(){
        return headnode==null;
    }
    //入栈
    public void push(Object data){
        if(headnode.getData() == null){//头结点为空时
            headnode.setData(data);
        }else if(headnode == null){
            headnode = new LLNode(data);
        }else{
            LLNode newnode = new LLNode(data);
            newnode.setNext(headnode);
            headnode = newnode;
        }
    }
    //出栈
    public Object pop(){
        Object data; //= null;
        if(isEmpty()){
            System.out.println("栈为空");
            return 0;
        }
        data = headnode.getData();
        headnode = headnode.getNext();
        return data;
    }
    //返回栈顶的值
    public Object top(){
        Object data = null;
        if(isEmpty()){
            System.out.println("栈为空");
            return 0;
        }
        data = headnode.getData();
        return data;
    }
   //找到栈里面的个数
   public int getLength(){
        int count = 0;
        LLNode tempnode=headnode;
        if(isEmpty()||tempnode.getData()==null){
            count = 0;
        }else{
            while(tempnode!=null){
                count++;
                System.out.printf("栈的第%d个值为%d\n",count,tempnode.getData());
                tempnode=tempnode.getNext();
            }
        }
        return  count;
   }
}
