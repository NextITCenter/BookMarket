package kr.or.nextit.bookmarket.cart;

import kr.or.nextit.bookmarket.book.BookService;
import kr.or.nextit.bookmarket.book.BookVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    private final BookService service;
    public CartController(BookService service) {
        this.service = service;
    }
    @GetMapping("/carts")
    public String carts(Model model) {
        return "cart/list";
    }

    @GetMapping("/cart/add")
    public String addCart(Model model, String id, HttpSession session) {
        BookVO book = service.selectBook(id);
        List<CartVO> carts = (List<CartVO>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
            // 장바구니 번호
            long no = 1;
            CartVO cart = new CartVO(no, book, 1);
            carts.add(cart);
        } else {
            int count = carts.size();
            boolean flag = false;
            for (int i = 0; i < count; i++) {
                CartVO vo = carts.get(i);
                if (vo.getBook().getId().equals(id)) {
                    // 수량
                    vo.setQuantity(vo.getQuantity() + 1);
                    carts.set(i, vo);
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag){
                // 장바구니 번호
                long no = count + 1;
                CartVO cart = new CartVO(no, book, 1);
                carts.add(cart);
            }
        }
        // 위의 반복문을 StreamAPI를 사용해서 변경
        session.setAttribute("carts", carts);

        return "redirect:/carts";
    }
}
