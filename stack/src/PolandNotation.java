import java.util.*;

/**
 * @author Pzr
 * @create 2022/5/15 - 18:27
 */
public class PolandNotation {
    public static void main(String[] args) {
        //(3+4)*5-6  => 3 4 + 5 * 6 -
       String suffixExpression = "3 4 + 5 * 6 -";
       //先将SuffixExpression放到ArrayList中再将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println("list= " + list);
        System.out.println("计算的结果是=" + res);
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
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



