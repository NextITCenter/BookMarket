package cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.BookService;
import book.BookVO;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		BookService service = BookService.getInstance();
		BookVO book = service.selectBook(id);
		// 세션에 장바구니를 넣는다.
		HttpSession session = req.getSession();
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
		req.getRequestDispatcher("/WEB-INF/views/cart/list.jsp").forward(req, resp);
	}
}












