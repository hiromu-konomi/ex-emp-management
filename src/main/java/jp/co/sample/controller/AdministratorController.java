package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;

	/**
	 * InsertAdministratorFormをインスタンス化しそのままreturnする処理
	 * 
	 * @return InsertAdministratorFormオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	/**
	 * LoginFormをインスタンス化しそのままreturnする処理
	 * 
	 * @return LoginFormオブジェクト
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * 「administrator/login.html」にフォワードする処理
	 * 
	 * @return「administrator/login.html」にフォワード
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * 「administrator/insert.html」にフォワードする処理
	 * 
	 * @return 「administrator/insert.html」にフォワード
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録する
	 * 
	 * @param form
	 * @return 「/」にリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator adm = new Administrator();
		adm.setName(form.getName());
		adm.setMailAddress(form.getMailAddress());
		adm.setPassword(form.getPassword());

		administratorService.insert(adm);

		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator adm = new Administrator();
		try {
			adm = administratorService.login(form.getMailAddress(), form.getPassword());
		} catch(EmptyResultDataAccessException ex) {
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		session.setAttribute("administratorName", adm.getName());
		return "forward:/employee/showList";
	}
}
