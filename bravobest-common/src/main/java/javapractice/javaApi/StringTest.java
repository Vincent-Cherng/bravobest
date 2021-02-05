package javapractice.javaApi;

public class StringTest {

    //1.String是不可变的。
    /*
    final 修饰类，代表类不可被继承。那么其成员方法也无法被重写。
    final 修饰变量，代表变量不可变。对于基本类型，那么值是不可变的。对于引用类型，那么对象是不可变的，但是对象的内容是可以变的。
    final 修饰方法，代表方法不可重写。

    那我们现在就来理一理 String为什么是不可变的。

    首先我们String类是被final修饰的，那么String类是不可被继承的，那么它的成员方法就没有机会被重写。
    其次我们String类的底层char数组是被final修饰的，那么char数组的地址是无法改变的。
    最后我们String类中并没有提供修改char数组内容的方法，因此String是不可变的。
    */

    //2.String设计不可变有哪些好处
    /*
    String设计成不可变的好处：
    安全问题。在这里解释下，为什么安全？比如在网络传输过程中，字符串的值是不能被改变的。如果可以改变，那么无法保证安全，
        因为我们可以讲值改为我们任意的。
    线程安全问题。不可变就天生具备线程安全。我们一般提的线程安全都是相对线程安全。
    可以缓存hash值。因为String的hash值经常被使用，比如String作为HashMap的key。不可变的特性可以使得hash值可以进行缓存，因为是不变的。
        这也是为什么String类中有hash这个成员变量。
    */

    public static void main(String[] args) {
        String f = "laochou";
        String g = "laochou";
        String h = new String("laochou");
        System.out.println(f == g);//true
        System.out.println(f == h);//false
        System.out.println(f.equals(h));//true

    }
}
