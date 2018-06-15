package com.assignment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Contains Search Criteria.
 * @author Himanshu Sharma 
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Criteria {
	/** Search String */
	private String searchString;
	/** Search Condition i.e StartsWiths or Contains etc */
	private String searchCondition;
	/** Enable Case Sensitive Search */
	private boolean caseSensitive;
	/** Contains Regular Expression */
	private String expression;

}
