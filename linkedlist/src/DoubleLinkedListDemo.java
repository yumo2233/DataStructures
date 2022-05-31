/**
 * @author Pzr
 * @create 2022/5/31 - 19:35
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.add(hero4);

        list.list();
        //修改
        HeroNode2 newhero4 = new HeroNode2(4, "公孙胜", "入云龙");
        list.update(newhero4);
        list.del(3);
       // list.add(hero4);
        //加上这行代码就死循环了，因为Hero4的信息已经被改成公孙胜了，前后都指向自己
        System.out.println("修改后的链表情况");
        list.list();

    }
}




class DoubleLinkedList{
    //初始化一个头结点，头结点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
        public HeroNode2 getHead(){
            return head;
    }
    //遍历双向链表
    //遍历链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
        }
    //添加结点到双向链表,尾插法
    public void add(HeroNode2 heroNode) {
        //通过头结点就可以找到链表，因此头结点别瞎动，再定义一个“指针”来辅助遍历
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;

            }
            temp = temp.next;
        }
       temp.next = heroNode;
        heroNode.pre = temp;
    }
    //根据no编号来修改结点信息，即no编号不能改
    //双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到%d的节点，不能修改\n", newHeroNode.no);
        }
    }
    //删除节点
    //对于双向链表，可以直接找到要删除的节点，进行自我删除
    public void del(int no){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空无法删除");
            return ;
        }

        HeroNode2 temp = head.next;//辅助指针 注意为什么是head.next
        boolean flag = false;
        while(true){
            if(temp == null){
                break;//已经找到链表的最后了（最后结点的next域）
            }else if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            //假如是最后一个结点，next为空了！
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("要删除的结点不存在");
        }
    }
}
//定义链表的结点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;//指向前一个结点，默认为空

    public HeroNode2(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重新定义tostring()
    @Override
    public String toString(){
        return "HeroNode2[no=" + no + ",name=" + name +",nickname=" +nickname + "]";}
}

