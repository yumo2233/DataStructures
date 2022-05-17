import java.util.*;

/**
 * @author Pzr
 * @create 2022/5/15 - 18:27
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成一个中缀表达式转成后缀表达式的功能
        //说明
        //直接对字符串操作不方便，先将其转成ArrayList
        //(3+4)*5-6  => 3 4 + 5 * 6 -
//       String suffixExpression = "3 4 + 5 * 6 -";
       //先将SuffixExpression放到ArrayList中再将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        int res = calculate(list);
//        System.out.println("list= " + list);
//        System.out.println("计算的结果是=" + res);
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式为" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        //test github 的相关问题 无视这行
        System.out.println("后缀表达式为" + suffixExpressionList);
        System.out.printf("expression=%d",calculate(suffixExpressionList));
    }

//    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
//    public static List<String> getListString(String suffixExpression) {
//        //将suffixExpression分割
//        String[] split = suffixExpression.split(" ");
//        List<String> list = new ArrayList<>();
//        for (String ele : split) {
//            list.add(ele);
//        }
//        return list;
//    }
//
    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i = 0;
        char c;
        String str;
        do{
            str ="";
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                str = ""+c;
                ls.add(str);
                i++;
            }else {
                while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i))<= 57 ){//i是否小于长度要放在前面判断以防越界
                    str = str + c;
                    //str += c;
                    i++;
                }
                ls.add(str);
            }
        }while(i <  s.length());
        return ls;
    }
    //方法：将得到的中缀表达式对应的list转换成后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<>();
        //s2栈在抓换过程中没有Pop操作，而且后面还要逆序输出，因此直接定义为ArrayList
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for(String item :ls){
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //是），则依次弹出栈顶的运算符并压入s2直到遇到左括号为止
                while(! s1.peek().equals( "(") ){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                //当item的优先级小于等于栈顶的运算符，将s1栈顶的运算符弹出并加入到s2中，与新的栈顶运算符进行比较
                while(s1.size()!=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入到s2中
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }


    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls){
        //创建个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for(String item : ls){
            //这里使用正则表达式
            //\d是匹配一个数字，\d+是匹配1个或多个数字，前面多一个\是为了转义
            if(item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
  //编写一个类Operation 可以返回一个运算符 对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String operation){
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                return 0;
            default:
                System.out.println("输入的运算符有误");
                break;
        }
        return result;
    }
  }



