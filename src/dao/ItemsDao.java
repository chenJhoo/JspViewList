package dao;

import entity.Items;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cjh on 2017-06-14.
 */
//商品的业务逻辑类
public class ItemsDao {
    //获取所有的商品信息
    public ArrayList<Items> getAllItems(){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Items>list=new ArrayList<Items>();//商品集合
        try {

            conn= DBHelper.getConnection();
            String sql="select * from items;";//SQL语句
            stmt=conn.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Items items=new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                items.setCity(rs.getString("city"));
                items.setNumber(rs.getInt("number"));
                items.setPrice(rs.getInt("price"));
                items.setPicture(rs.getString("picture"));
                list.add(items);//每一次把商品加入到集合
            }
            return list;//返回集合
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {

            //从小到大释放语句对象
            if (rs!=null||stmt!=null){
                try {
                    rs.close();
                    stmt.close();
                    stmt=null;
                    rs=null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //根据商品编号商品资料
    public Items getItemsById(int id){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        ArrayList<Items>list=new ArrayList<Items>();//商品集合
        try {

            conn= DBHelper.getConnection();
            String sql="select * from items where id=?;";//SQL语句
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            if (rs.next()){
                Items items=new Items();
                items.setId(rs.getInt("id"));
                items.setName(rs.getString("name"));
                items.setCity(rs.getString("city"));
                items.setNumber(rs.getInt("number"));
                items.setPrice(rs.getInt("price"));
                items.setPicture(rs.getString("picture"));
                return items;
            }else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {

            //从小到大释放语句对象
            if (rs!=null||stmt!=null){
                try {
                    rs.close();
                    stmt.close();
                    stmt=null;
                    rs=null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
     }

    public  ArrayList<Items>getViewList(String list){
        ArrayList<Items>itemlist=new ArrayList<Items>();
        int iCount=5;
        if (list!=null&&list.length()>0){
            String[]arr=list.split(",");
            //如果商品记录大于等于5
            if (arr.length>=5){
                for (int i=arr.length-1;i>arr.length-iCount;i--){
                    itemlist.add(getItemsById(Integer.parseInt(arr[i])));
                }
            }else {
                for (int i=arr.length-1;i>=0;i--){
                    itemlist.add(getItemsById(Integer.parseInt(arr[i])));
                }
            }
            return itemlist;
        }else {
            return null;
        }

    }







}
