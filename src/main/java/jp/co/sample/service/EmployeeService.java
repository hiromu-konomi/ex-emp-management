package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得する
	 * @return 取得された従業員情報一覧
	 */
	public List<Employee> showList(){
		List<Employee> employeeList = new ArrayList<>();
		employeeList = employeeRepository.findAll();
		return employeeList;
	}
}
