package com.ispace.utils;

import com.ispace.search.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUtil {
    public static List<SearchCriteria> extractSearchCriteria(String search) {
        List<SearchCriteria> params = new ArrayList<>();
        if (search != null && search != "") {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|==)(.+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));
            }
        }
        return params;
    }
}
