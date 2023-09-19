package api;

import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;

public interface CartApi {
    // 删除购物车中商品的方法
    void deleteUserCart(CartDeleteParam cartDeleteParam);
}
