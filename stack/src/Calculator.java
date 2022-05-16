/**
 * @author Pzr
 * @create 2022/5/12 - 19:58
 */
public class Calculator {
    public static void main(String[] args) {
        //根据前面思路，完成表达式的运算
        String expression = "30+200*6-2";
        //创建两个栈，分别为数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        while(true){
            ch = expression.substring(index,index+1).charAt(0);//返回一个字符串，从起始索引到结束索引（不包括结束索引）
            //判断ch是什么
            if(operStack.isOper(ch)){
                //判断当前符号栈是否为空，
                if(!operStack.isEmpty()){
                //如果符号栈有操作符就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，就从
                //数栈中push两个数操作栈中push一个操作符进行运算，将结果push进数栈中，当前操作符入栈
                    if(operStack.proority(ch) <= operStack.proority(operStack.peek()) ){
                         num1 = numStack.pop();
                         num2 = numStack.pop();
                         oper = operStack.pop();
                        res =  numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //栈空则直接将符号入栈
                    operStack.push(ch);
                }
            }else{
                //如果是数直接入栈
                //numStack.push(ch-48);//'1'=>1
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2.需要index再向后看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果已经到最后一位了，直接入栈
                if(index == expression.length()-1){
                    numStack.push(ch-48);
                    //numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一位是不是操作运算符
                 if(numStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                     numStack.push(Integer.parseInt(keepNum));
                     keepNum="";
                 }
                //如果在这清0就是意味后面是数字也清0了
                }


            }
        //让index + 1，并判断是否扫描到expression的最后
            index++;
            if(index == expression.length()){
                break;
            }
        }
    //扫描完毕后，就顺序从数栈和符号栈中pop出相应的数和符号
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println("结果为" + numStack.pop());
    }
}
//用数组定义个栈，要添加功能
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，ps:从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //可以返回当前栈顶的值，但不是真正的pop
    public int peek() {
        return stack[top];
    }

    //返回运算符的优先级，优先级是程序员确定的。优先级使用数字表示
    //数组越大，则优先级越高
    public int proority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '/' || val == '*';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;//存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;

        }
        return res;
    }
}
