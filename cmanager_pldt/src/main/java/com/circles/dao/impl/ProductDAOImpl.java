package com.circles.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.circles.model.Category;
import com.circles.model.Devices;
import com.circles.model.Products;

public class ProductDAOImpl {
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int save(Products pd){
		
		String check ="SELECT * FROM product WHERE product_id = ?";
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(check.toString(),new Object[]{
			pd.getProduct_id()
		});
		
		if(!rs.next()){
			String sql = "INSERT INTO product(" +
					"product_id," +
					"product_name," +
					"product_desc," +
					"one_time_price," +
					"parent_groupid," +
					"image_name," +
					"conditions,"+
					"device,"+
					"monthly_price,"+
					"edited_by," +
					"created," +
					"updated," +
					"version) " +
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
				System.out.println("inserting: "+pd.getProduct_id());
				int iSave = jdbcTemplate.update(sql.toString(),new Object[] {
					pd.getProduct_id(),pd.getProduct_name(),pd.getProduct_desc(),
					pd.getOne_time_price(),pd.getGroupid(),pd.getFilename(),pd.getCondition(),pd.getDevice(),
					pd.getMonthly_price(),pd.getEdited_by(),pd.getCreated(),pd.getUpdated(),pd.getVersion()
				});
				System.out.println("Success");
			return iSave;
		}
		else{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE product SET product_name =?, product_desc =?, one_time_price =?, image_name =?, ");
			sql.append("conditions =?,device =?,monthly_price =?,edited_by =?,created =?,updated =?,version =?  ");
			sql.append("WHERE product_id = ? ");
			
				System.out.println("Updating: "+pd.getProduct_id());
				int iSave = jdbcTemplate.update(sql.toString(),new Object[] {
					pd.getProduct_name(),pd.getProduct_desc(),pd.getOne_time_price(),
					pd.getFilename(),pd.getCondition(),pd.getDevice(),pd.getMonthly_price(),
					pd.getEdited_by(),pd.getCreated(),pd.getUpdated(),pd.getVersion(),
					pd.getProduct_id()
				});
				System.out.println("Success");
				return iSave;
						
		}
		
	}
	
	public int checkProduct(Products pd) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM product WHERE product_name = ? ");
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql.toString(),new Object[]{pd.getProduct_name()});
		if(rs.next()){
			System.out.println("product already exist!");
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int delete(Products pd){
		String sql ="DELETE Product WHERE product_id = ?";
		int delete = jdbcTemplate.update(sql.toString(),new Object[]{pd.getProduct_id()});
				
		return delete;
	}
	
	public int deleteDev(Devices dev){
		String sql ="DELETE Devices WHERE deviceid = ?";
		int delete = jdbcTemplate.update(sql.toString(),new Object[]{dev.getDeviceid()});
				
		return delete;
	}
	
	public int deleteCat(Category cat){
		String sql = "DELETE prod_cat Where groupid = ?";
		int delete = jdbcTemplate.update(sql.toString(),new Object[]{cat.getGroupid()});
		return delete;
	}
	
	public int addCategory(Category cat){
		
		String check ="SELECT * FROM prod_cat WHERE category_name = ?";
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(check.toString(),new Object[]{
			cat.getCategory_name()
		});
		
		if(!rs.next()){
			
			StringBuilder str = new StringBuilder();

			System.out.println("Inserting category ");
			
			str.append("INSERT INTO prod_cat(category_name,created,updated,version,ispushed,edited_by) ");
			str.append("VALUES(?,?,?,?,?,?) ");
			
			try{
			
			int addcat = jdbcTemplate.update(str.toString(),new Object[]{
				cat.getCategory_name(),cat.getCreated(),cat.getUpdated(),
				cat.getVersion(),cat.getIspushed(),cat.getEdited_by()
			});
			
			System.out.println("Inserting category successfully inserted");
			
			return addcat;
			
			}catch(Exception ex){
				System.out.println("Failed to update category for ID: "+cat.getCategory_name());
				return 0;
			}
		}
		
		else{
			
			StringBuilder str = new StringBuilder();

			System.out.println("Updating category ");
			
			str.append("UPDATE prod_cat SET category_name =?,created =?,updated =?,version =?,ispushed =?,edited_by =? ");
			str.append("WHERE category_name = ?");
			try{
			
			int updatecat = jdbcTemplate.update(str.toString(),new Object[]{
				cat.getCategory_name(),cat.getCreated(),cat.getUpdated(),
				cat.getVersion(),cat.getIspushed(),cat.getEdited_by(),
				cat.getCategory_name()
			});
			
			System.out.println("Updating category successfully inserted");
			
			return updatecat;
			
			}catch(Exception ex){
				System.out.println("Failed to update category for ID: "+cat.getCategory_name());
				return 0;
			}
			
		}
		
			
	
	}
	
	public int addDevice(Devices dev){
		
		StringBuilder check = new StringBuilder();
		
		check.append("SELECT * FROM device WHERE device_name = ? ");
		
		System.out.print("checking devices/hardware....");
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(check.toString(),new Object[]{dev.getDevice_name()});
		
		if(!rs.next()){
			
			StringBuilder str = new StringBuilder();
			
			str.append("INSERT INTO device(deviceid,device_name,description,device_image,created) ");
			str.append("VALUES (?,?,?,?,?) ");
			
			System.out.println("Inserting Device for ID: "+dev.getDeviceid());
			
			int adddevice = jdbcTemplate.update(str.toString(),new Object[] {
				dev.getDeviceid(),dev.getDevice_name(),dev.getDescription(),dev.getDevice_image(),dev.getCreated()
			});
			
			System.out.println("inserting device for ID: "+dev.getDeviceid()+ "Successfully inserted");
			
			return adddevice;
		}
		else{
			
			System.out.print("devices/hardware already exist!");
			return 0;
		}
		
		
	}
	
	public Collection viewSelectedProduct(Products pd,String id){
		return jdbcTemplate.query("SELECT * FROM product WHERE product_id = ? ", new ProductMapper1(),id);
	}
	public Collection viewSelectDevice2(Devices dev,String id){
		return jdbcTemplate.query("SELECT * FROM device WHERE deviceid = ?" , new DeviceMapper(),id);	
	}
	
	public Collection viewCategory(Category cat){
		return jdbcTemplate.query("SELECT * FROM prod_cat" , new ProductMapper());	
	}
	
	public Collection viewSelectedCategory(Category cat){
		return jdbcTemplate.query("SELECT * FROM prod_cat WHERE groupid=? " , new ProductMapper(),cat.getGroupid());	
	}
	
	public Collection viewAll(Products pd){
	
		return jdbcTemplate.query("SELECT * FROM product WHERE parent_groupid = ?" , new ProductMapper1(),pd.getGroupid());	
	
	}
	
	public Collection viewEdit(Products pd){
		
		return jdbcTemplate.query("SELECT * FROM product WHERE product_id = ?" , new ProductMapper1(),pd.getProduct_id());	
	}
	
	public Collection viewDesc(Products pd){
		System.out.println("product id: "+pd.getProduct_id());
		return jdbcTemplate.query("SELECT product_id, monthly_price, product_desc, one_time_price FROM product WHERE product_id = ?" , new ProductMapper2(),pd.getProduct_id());	
	}
	
	public Collection viewDevice(Devices dev){
		return jdbcTemplate.query("SELECT * FROM device " , new DeviceMapper());	
	}
	
	public Collection viewSelectDevice(Devices dev){
		return jdbcTemplate.query("SELECT * FROM device WHERE deviceid = ?" , new DeviceMapper(),dev.getDeviceid());	
	}
	
	public Collection viewNameDevice(Devices dev){
		return jdbcTemplate.query("SELECT * FROM device WHERE device_name = ?" , new DeviceMapper(),dev.getDevice_name());	
	}
	
	public Collection viewAllProduct(Products pd){
		return jdbcTemplate.query("SELECT * FROM product " , new ProductMapper1());	
	}
	
	private static final class ProductMapper implements RowMapper<Category> {

	    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Category cat = new Category();
	    	
	        
	    	cat.setCategory_name(rs.getString("category_name"));
	    	cat.setGroupid(rs.getString("groupid"));
	    	cat.setCreated(rs.getString("created"));
	    	cat.setUpdated(rs.getString("updated"));
	    	cat.setVersion(rs.getString("version"));
	    	cat.setIspushed(rs.getString("ispushed"));
	    	cat.setEdited_by(rs.getString("edited_by"));
	        return cat;
	        
	    }
	}
	private static final class ProductMapper1 implements RowMapper<Products> {

	    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Products pd = new Products();
	        
	    	pd.setProduct_id(rs.getString("product_id"));
	    	pd.setGroupid(rs.getString("parent_groupid"));
	    	pd.setProduct_desc(rs.getString("product_desc"));
	    	pd.setProduct_name(rs.getString("product_name"));
	    	pd.setOne_time_price(rs.getString("one_time_price"));
	    	pd.setCondition(rs.getString("conditions"));
	    	pd.setMonthly_price(rs.getString("monthly_price"));
	    	pd.setDevice(rs.getString("device"));
	    	pd.setFilename(rs.getString("image_name"));
	    	pd.setIspushed(rs.getString("ispushed"));
	    	pd.setEdited_by(rs.getString("edited_by"));
	    	pd.setCreated(rs.getString("created"));
	    	pd.setUpdated(rs.getString("updated"));
	    	pd.setVersion(rs.getString("version"));
	    	
	        return pd;
	        
	    }
	}
	
	private static final class ProductMapper2 implements RowMapper<Products> {

	    public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Products pd = new Products();
	        
	    	pd.setProduct_id(rs.getString("product_id"));	    	
	    	pd.setProduct_desc(rs.getString("product_desc"));
	    	pd.setOne_time_price(rs.getString("one_time_price"));
	    	pd.setMonthly_price(rs.getString("monthly_price"));
	    	
	        return pd;
	        
	    }
	}
	
	private static final class DeviceMapper implements RowMapper<Devices> {

	    public Devices mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Devices dev = new Devices();
	        
	    	dev.setDeviceid(rs.getString("deviceid"));
	    	dev.setDevice_name(rs.getString("device_name"));
	    	dev.setDescription(rs.getString("description"));
	    	dev.setDevice_image(rs.getString("device_image"));
	    	dev.setCreated(rs.getString("created"));
	    	
	        return dev;
	        
	    }
	}
}
