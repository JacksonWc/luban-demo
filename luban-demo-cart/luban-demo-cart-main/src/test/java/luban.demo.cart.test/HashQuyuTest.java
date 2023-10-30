package luban.demo.cart.test;

public class HashQuyuTest {
    public static void main(String[] args) {
        int count=0;
        for (int i=0;i<10000;i++){
            String key="key_"+i;
            int result1=hash(key,3);
            int result2=hash(key,4);
            if (result1==result2){
                count++;
            }
        }
        System.out.println(count/10000.00);
        /*String key="key_700";
        Integer hash = hash(key, 3);
        System.out.println(hash);*/
        /*int count0=0;
        int count1=0;
        int count2=0;
        for(int i=0;i<10000;i++){
            String key="key_"+i;

            //假设我有3个节点 下标 0 1 2
            //调用hash取余算法
            Integer hash = hash(key, 3);
            if (i==700){
                System.out.println("当前key值"+key+"取余结果:"+hash);
            }
            if (hash==0){
                count0++;
            }else if(hash==1){
                count1++;
            }else{
                count2++;
            }
        }

        System.out.println(count0);
        System.out.println(count1);
        System.out.println(count2);*/
    }
    public static Integer hash(String key,Integer size){
        return Math.abs(key.hashCode())%size;
    }
}
