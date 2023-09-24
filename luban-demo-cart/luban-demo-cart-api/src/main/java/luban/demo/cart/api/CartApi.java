package luban.demo.cart.api;

import com.tarena.demo.luban.protocol.cart.param.CartDeleteParam;

public interface CartApi {

    void deleteCart(CartDeleteParam param);
}
