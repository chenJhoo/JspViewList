package entity;

/**
 * Created by cjh on 2017-06-14.
 */
public class Items {
    private int id;
    private String name;
    private String city;
    private int price;
    private int number;
    private String picture;

    public Items() {
    }
    public Items(int id,String name,String city,int price,int number,String picture){
        this.id=id;
        this.name=name;
        this.city=city;
        this.price=price;
        this.number=number;
        this.picture=picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 重写这个方法是为了当添加两件相同的商品时生成同一个hashCode,
     * 而判断是不是同一个hashCode则用下面的equals()方法
     * 避免了添加两个同样的商品进购物车
     * @return
     */
    @Override
    public int hashCode() {
        return this.getId()+this.getName().hashCode();
    }

    /**
     * 判断两个对象的内容是否相同（即Hashcode是不是相同）
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this==obj){
            return true;
        }
        //判断传进来的对象obj是不是属于商品类
        if (obj instanceof Items){
            Items i= (Items) obj;
            if (this.getId()==i.getId() && this.getName().equals(i.getName())){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "商品编号："+this.getId()+",商品名称："+this.getName();
    }









}
