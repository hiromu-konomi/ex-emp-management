package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報登録画面を表示する処理を記述する
 * @author konomitaimu
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

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
}
