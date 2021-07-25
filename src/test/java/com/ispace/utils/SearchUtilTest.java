package com.ispace.utils;

import com.ispace.search.SearchCriteria;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SearchUtilTest {

    @Test
    void extractSearchCriteria() {
        // given
        String search = "title==t1,description==d1,authorEmail==test@gmail.com";
        // when
        List<SearchCriteria> criteriaList = SearchUtil.extractSearchCriteria(search);
        // then
        assertThat(criteriaList.size()).isEqualTo(3);
    }
}