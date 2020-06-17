package jp.co.sample.form;

public class UpdateEmployeeForm {
	/** 従業員ID */
	private String name;
	/** 扶養人数 */
	private String dependentsCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeForm [name=" + name + ", dependentsCount=" + dependentsCount + "]";
	}

}
