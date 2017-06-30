package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by cjh on 2017-06-29.
 */

/**
 * 购物车类
 * 因为购物车内是键值对的关系，把商品的属性（价格，名称，产地等）作为键
 * 购买商品的数量作为值，所以这里用Map集合
 */
public class Cart {
    //购买商品的集合
    private HashMap<Items,Integer>goods;
    //购物车的总金额
    private double totalPrice;

    //构造方法

    public Cart() {
        //初始化商品属性
        goods=new HashMap<Items,Integer>();
        totalPrice=0.0;

    }

    public HashMap<Items, Integer> getGoods() {
        return goods;
    }

    public void setGoods(HashMap<Items, Integer> goods) {
        this.goods = goods;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 添加商品进购物车的方法
     */
    public boolean addGoodsInCart(Items item,int number){
        //判断购物车内是否已经有该商品了，如果已经有了，则累加购买数量
        if (goods.containsKey(item)){
            goods.put(item,goods.get(item)+number);
        }else {
            goods.put(item,number);
        }

        calTotalPrice();//每次添加或删除 要重新计算购物车的总金额
        return true;
    }

    /**
     * 删除商品的方法
     */
    public boolean removeGoodsFromCart(Items item){
        goods.remove(item);
        calTotalPrice();//每次添加或删除 要重新计算购物车的总金额
        return true;
    }

    /**
     * 统计购物车的总金额
     */
    public double calTotalPrice(){
        double sum =0.0;
        Set<Items>keys=goods.keySet();//获得键的集合
        Iterator<Items> it=keys.iterator();//获得迭代器对象
        while (it.hasNext()){
            Items i=it.next();
            sum+=i.getPrice()*goods.get(i);
        }
        this.setTotalPrice(sum);//设置购物车的总金额
        return this.getTotalPrice();

    }


    public static void main(String[] args) {
        Items i1=new Items(1,"Adidas","温州",200,500,"0.01.jpg");
        Items i2=new Items(2,"Nike","晋江",300,330,"0.02.jpg");
        Items i3=new Items(1,"Adidas","温州",200,500,"0.01.jpg");
        Cart c=new Cart();
        c.addGoodsInCart(i1,1);
        c.addGoodsInCart(i2,2);
        c.addGoodsInCart(i3,3);

        /**
         * 变量购物商品集合
         * Map集合没有继承Iterable接口所以不能直接遍历
         * 需要entrySet()先转化为set类型，Set的每一个元素就是Map集合中的一个键值对
         */
        Set<Map.Entry<Items,Integer>>items=c.getGoods().entrySet();
        for (Map.Entry<Items,Integer> obj:items){
            System.out.println(obj);
        }




        System.out.println("总金额："+c.getTotalPrice());
    }



















}
