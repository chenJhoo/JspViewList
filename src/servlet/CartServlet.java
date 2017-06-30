package servlet;

import dao.ItemsDao;
import entity.Cart;
import entity.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cjh on 2017-06-29.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    private String action;//购物车的动作
    private ItemsDao idao=new ItemsDao(); //商品业务逻辑类的对象
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action")!=null){
            this.action=request.getParameter("action");
            if (action.equals("add")){//如果动作是添加进购物车
                addToCart(request,response);
                if (addToCart(request,response)){
                    request.getRequestDispatcher("/success.jsp").forward(request,response);
                }else {
                    request.getRequestDispatcher("/failure .jsp").forward(request,response);
                }
            }
            if (action.equals("show")){//如果动作是查看购物车
                System.out.println("________________________________________________________");
                request.getRequestDispatcher("/cart .jsp").forward(request,response);
            }
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) {

    }


    private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
        String id =request.getParameter("id");
        String number=request.getParameter("num");//商品数量
        Items item=idao.getItemsById(Integer.parseInt(id));
        //判断是否是第一次给购物车添加商品，需要给session中创建一个新的购物车对象
        if (request.getSession().getAttribute("cart")==null){
            Cart cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if (cart.addGoodsInCart(item,Integer.parseInt(number))){
            return true;
        }else {
            return false;
        }











    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
