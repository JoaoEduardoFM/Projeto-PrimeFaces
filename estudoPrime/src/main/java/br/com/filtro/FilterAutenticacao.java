package br.com.filtro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;

import br.com.jpaUtil.JPAUtil;

@WebFilter(urlPatterns = "/*")
public class FilterAutenticacao implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		String url = req.getServletPath();

		if (!url.equalsIgnoreCase("index.xhtml") && usuarioLogado == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.xhtml");
			dispatcher.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

}
