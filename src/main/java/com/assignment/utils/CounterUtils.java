package com.assignment.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.assignment.model.Criteria;

import lombok.extern.slf4j.Slf4j;

/**
 * Util class implementing logic to match individual row of the file with
 * searchCriteria
 *
 */
@Slf4j
public class CounterUtils {

	/**
	 * Split the individual row into List of String (i.e. words) and apply search
	 * criteria for matching.
	 * 
	 * @param input
	 * @param searchCriteria
	 * @param result
	 * @return
	 */
	public static boolean searchWords(String input, List<Criteria> searchCriteria, Map<Criteria, List<String>> result) {
		StringTokenizer token = new StringTokenizer(input);
		while (token.hasMoreElements()) {
			String word = (String) token.nextElement();
			applyCriteria(word, searchCriteria, result);
		}
		return true;
	}

	/**
	 * Each String/word is checked against the Search criteria and if matched, word
	 * is added to map with search criteria as Key of the map.
	 * 
	 * @param word
	 * @param searchCriteria
	 *            : Criteria to match
	 * @param result
	 *            : Map of searchCriteria and Matched words.
	 */
	private static void applyCriteria(String word, List<Criteria> searchCriteria, Map<Criteria, List<String>> result) {
		searchCriteria.forEach(criteria -> {
			if (condition(word, criteria)) {
				log.debug("Criteria Matched, incrementing count");
				List<String> matchedWords = result.get(criteria) != null ? result.get(criteria) : new ArrayList<>();
				matchedWords.add(word);
				result.put(criteria, matchedWords);
			}
		});
	}

	/**
	 * Matches SeachString based on SearchConditon passed. Also, performs regular
	 * expressions match if provided on the input string.
	 * 
	 * @param word
	 * @param criteria
	 * @return
	 */
	private static boolean condition(String word, Criteria criteria) {
		boolean flag = false;
		if (StringUtils.isNotBlank(criteria.getSearchCondition())) {
			if ("startsWith".equalsIgnoreCase(criteria.getSearchCondition())) {
				flag = criteria.isCaseSensitive() ? word.startsWith(criteria.getSearchString())
						: word.toLowerCase().startsWith((criteria.getSearchString().toLowerCase()));
			} else if ("endsWith".equalsIgnoreCase(criteria.getSearchCondition())) {
				flag = criteria.isCaseSensitive() ? word.endsWith(criteria.getSearchString())
						: word.toLowerCase().endsWith((criteria.getSearchString().toLowerCase()));
			} else if ("contains".equalsIgnoreCase(criteria.getSearchCondition())
					&& word.contains(criteria.getSearchString())) {
				flag = true;
			} else if ("equals".equalsIgnoreCase(criteria.getSearchCondition())) {
				flag = criteria.isCaseSensitive() ? word.equals(criteria.getSearchString())
						: word.equalsIgnoreCase(criteria.getSearchString());
			}
		}
		boolean patternMatch = false;
		if (StringUtils.isNotBlank(criteria.getExpression())) {
			Pattern pattern = Pattern.compile(criteria.getExpression());
			Matcher matcher = pattern.matcher(word);
			patternMatch = matcher.matches();
		}

		if (StringUtils.isNotBlank(criteria.getSearchCondition()) && StringUtils.isNotBlank(criteria.getExpression())) {
			return flag && patternMatch;
		} else if (StringUtils.isNotBlank(criteria.getSearchCondition())) {
			return flag;
		} else if (StringUtils.isNotBlank(criteria.getExpression())) {
			return patternMatch;
		} else {
			return false;
		}

	}
}
