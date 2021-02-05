package javapractice.util.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * bloomFilter
 */
public class BloomFilterTest {

    /*布隆过滤器(Bloom Filter) 是一种节省空间的概率数据结构， 用来测试一个元素是否在一个集合里。
    它实际上是一个很长的二进制向量和一系列随机映射函数。相比于传统的List、Set、Map 等数据结构，
    它更高效、占用空间更少，但是缺点是其返回的结果是概率性的，而不是确切的。*/

    /*原理：
    可以拆分成两个环节：
    插入，当一个元素被加入集合时，通过K个散列（Hash）函数将这个元素映射成一个位数组中的K个点，把它们置为1。
    查找，当需要判断这个元素是否存在时，我们只要看看这些点是不是都是1就，大约知道集合中有没有它了，如果这些点有任何一个0，
    则被检元素一定不在；如果都是1，则被检元素很可能存在。*/

    /*优点
    1.节省存储空间
    2.查找速度快。

    缺点
    1.存在误判，可能要查到的元素并没有在容器中，但是hash之后得到的k个位置上值都是1。
    2.删除困难。一个放入容器的元素映射到bit数组的k个位置上是1，删除的时候不能简单的直接置为0，可能会影响其他元素的判断。*/


    public static void main(String[] args) {

        int total = 1000000;

        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), total);

        //可以自己规定错误率，默认0.03%
        //BloomFilter<CharSequence> bloomFilter1 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), total, 0.0001);

        for (int i = 0; i < total; i++){
            bloomFilter.put("" + i);
        }

        for (int i = 0; i < total; i++){
            if (!bloomFilter.mightContain(i + "")){
                System.out.println(i + "应该在过滤器里，但是却不在！");
            }
        }

        int count = 0;
        for (int i = total; i < total * 2 ; i++){
            if (bloomFilter.mightContain(i + "")){
                System.out.println(i + "不应该在过滤器里，但是却在！");
                count++;
            }
        }
        System.out.println("不应该在过滤器里，但是却在的个数总共有" + count + "个！");
        System.out.println("不应该在过滤器里，但是却在的个数占总数的比例为" + (double)count/total + "！");
    }
}
