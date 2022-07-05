package com.kavi.web.utils;

public class QueryConstants {

	public static final String GET_ITEMS_BY_TAG_ID = "select * from ITEM i where i.id IN (select  tag.item_id from item_tag tag where tag.tag_id = :tagId )";
	public static final String GET_ITEMS_BY_ITEM_TYPE = "select * from item i where i.type_id IN (:itemTypes)";
	public static final String GET_ITEM_TAG_BY_TAG_IDS = "select * from item i  where i.id IN ( select distinct(it.item_id) from item_tag it where it.tag_id IN (:tagIds))";
}
