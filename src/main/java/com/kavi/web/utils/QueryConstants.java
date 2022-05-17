package com.kavi.web.utils;

public class QueryConstants {

	public static final String GET_ITEM_BY_TAG_ID = "select * from ITEM i where i.id IN (select  tag.item_id from item_tag tag where tag.tag_id = :tagId )";

}
