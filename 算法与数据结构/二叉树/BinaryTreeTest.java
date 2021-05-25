package 二叉树;

import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BinaryTreeTest {
    public static void main(String args[]) {
        BinaryTree<Integer,String> MyTree = new BinaryTree<Integer,String>();

        MyTree.put(10, "lilei");
        MyTree.put(15,"sansha");
        MyTree.put(91,"s3sha");
        MyTree.put(1,"saha");
        MyTree.put(5,"clara");

        String s = MyTree.get(91);

        System.out.println("K:91 -> V:"+s);
        System.out.println("tree's size is "+MyTree.size());

        MyTree.delete(91);
        s = MyTree.get(91);

        System.out.println("K:91 -> V:"+s);
        System.out.println("tree's size is "+MyTree.size());


        Queue<Integer> keys = MyTree.preErgodic();
        for(Integer key : keys){
            System.out.println(key);
        }

        MyTree.preErgodic()
                .forEach(System.out::println);

                

    }
    
}
