package com.circles.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.circles.model.Products;

public class ProductDAOImpl {
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Products pd){
		
		String sql = "INSERT INTO product(" +
				"product_id," +
				"product_name," +
				"product_type," +
				"product_desc," +
				"product_price," +
				"filename," +
				"ispushed," +
				"edited_by," +
				"created," +
				"updated) " +
				"VALUES(?,?,?,?,?,?,?,?,?,?)";
		
//		String update = "UPDATE product SET "
//				+ "product_id = ?,"
//				+ "product_name = ?,"
//				+ "product_type = ?,"
//				+ "product_desc = ?,"
//				+ "product_price = ?,"
//				+ "filename = ?,"
//				+ "ispushed = ?,"
//				+ "edited_by = ?,"
//				+ "created = ?,"
//				+ "updated = ?";
		
		
			System.out.println("inserting: "+pd.getProduct_id());
			int iSave = jdbcTemplate.update(sql.toString(),new Object[] {
				pd.getProduct_id(),pd.getProduct_name(),pd.getProduct_type(),pd.getProduct_desc(),
				pd.getProduct_price(),pd.getFilename(),pd.getIspushed(),pd.getEdited_by(),pd.getCreated(),
				pd.getUpdated()
			});
			System.out.println("Success");
		return iSave;
	}

}
