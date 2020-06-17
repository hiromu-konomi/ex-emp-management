package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス
 * 
 * @author konomitaimu
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を挿入する
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}

	/**
	 * ログイン処理をする
	 * 
	 * @param mailAddress
	 * @param password
	 * @return 取得された管理者情報
	 */
	public Administrator login(String mailAddress, String password) {
		Administrator adm = new Administrator();
		adm = administratorRepository.findByMailAddressPassword(mailAddress, password);
		return adm;
	}
}
