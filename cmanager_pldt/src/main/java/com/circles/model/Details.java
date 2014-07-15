package com.circles.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("serial")
public class Details implements Serializable, Cloneable{
	
	private address address;
	private String worknumber;
	private namelistvalues namelistvalues;
	private List<Attachments> attachments;
	private packageplan packageplan;
	private spousedetails spousedetails;
	
	public class attachements{
		private String number;
		private String imgfilename;
		private String type;
		private String issuer;
		private String attachment_type;
		private String expiry;
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getImgfilename() {
			return imgfilename;
		}
		public void setImgfilename(String imgfilename) {
			this.imgfilename = imgfilename;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getAttachment_type() {
			return attachment_type;
		}
		public void setAttachment_type(String attachment_type) {
			this.attachment_type = attachment_type;
		}
		public String getExpiry() {
			return expiry;
		}
		public void setExpiry(String expiry) {
			this.expiry = expiry;
		}
	}
	
	public spousedetails getSpousedetails() {
		return spousedetails;
	}

	public void setSpousedetails(spousedetails spousedetails) {
		this.spousedetails = spousedetails;
	}

	private packageplandetails packageplandetails;
	private signature signature;
	
	
	private String civilstatus;
	private String title;
	private String telephone;
	
	@SerializedName("package")
	private packages packages;
	
	private String lastname;
	private String employement;
	private String account;
	private String mothers_maiden_name;
	
	public class spousedetails implements Serializable, Cloneable{
		private String lastname;
		private String firstname;
		private String title;
		private spouseaddress spouseaddress;
		
		public class spouseaddress implements Serializable, Cloneable{
			private String address1;
			private String barangay;
			private String postalcode;
			private String region;
			private String city;
			
			public String getAddress1() {
				return address1;
			}
			public void setAddress1(String address1) {
				this.address1 = address1;
			}
			public String getBarangay() {
				return barangay;
			}
			public void setBarangay(String barangay) {
				this.barangay = barangay;
			}
			public String getPostalcode() {
				return postalcode;
			}
			public void setPostalcode(String postalcode) {
				this.postalcode = postalcode;
			}
			public String getRegion() {
				return region;
			}
			public void setRegion(String region) {
				this.region = region;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
		}
		
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public spouseaddress getSpouseaddress() {
			return spouseaddress;
		}
		public void setSpouseaddress(spouseaddress spouseaddress) {
			this.spouseaddress = spouseaddress;
		}
		
	}
	
	public class address implements Serializable, Cloneable{
		private String address1;
		private String barangay;
		private String postalcode;
		private String region;
		private String city;
		private location location;
		
		public class location{
			
			private String lat;
			private String lng;
			
			public String getLat() {
				return lat;
			}
			public void setLat(String lat) {
				this.lat = lat;
			}
			public String getLng() {
				return lng;
			}
			public void setLng(String lng) {
				this.lng = lng;
			}
			
		}
		
		

		
		
		public String getAddress1() {
			return address1;
		}
		public void setAddress1(String address1) {
			this.address1 = address1;
		}
		public String getBarangay() {
			return barangay;
		}
		public void setBarangay(String barangay) {
			this.barangay = barangay;
		}
		public String getPostalcode() {
			return postalcode;
		}
		public void setPostalcode(String postalcode) {
			this.postalcode = postalcode;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public location getLocation() {
			return location;
		}
		public void setLocation(location location) {
			this.location = location;
		}
	}
	
	public class namelistvalues implements Serializable, Cloneable{
		private String number;
		private String imgfilename;
		private String issuer;
		private String type;
		private String expiry;
		private String attachement_type;
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getImgfilename() {
			return imgfilename;
		}
		public void setImgfilename(String imgfilename) {
			this.imgfilename = imgfilename;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getExpiry() {
			return expiry;
		}
		public void setExpiry(String expiry) {
			this.expiry = expiry;
		}
		public String getAttachement_type() {
			return attachement_type;
		}
		public void setAttachement_type(String attachement_type) {
			this.attachement_type = attachement_type;
		}
	}
	
	public class packageplandetails implements Serializable, Cloneable{
		private String instructions;

		public String getInstructions() {
			return instructions;
		}

		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}
	}
	
	public class signature implements Serializable, Cloneable{
		private String sig1;
		private String sig2;
		private String sig3;
		
		public String getSig1() {
			return sig1;
		}
		public void setSig1(String sig1) {
			this.sig1 = sig1;
		}
		public String getSig2() {
			return sig2;
		}
		public void setSig2(String sig2) {
			this.sig2 = sig2;
		}
		public String getSig3() {
			return sig3;
		}
		public void setSig3(String sig3) {
			this.sig3 = sig3;
		}
	}
	
	private String mobile;
	private String email;
	private String firstname;
	private String birthday;
	
	public class attachments implements Serializable, Cloneable{
		private String number;
		private String imgfilename;
		private String issuer;
		private String type;
		private String expiry;
		
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getImgfilename() {
			return imgfilename;
		}
		public void setImgfilename(String imgfilename) {
			this.imgfilename = imgfilename;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getExpiry() {
			return expiry;
		}
		public void setExpiry(String expiry) {
			this.expiry = expiry;
		}
	}
	
	public class packageplan implements Serializable, Cloneable{
		private String category_name;
		@SerializedName("details")
		private detail_string detail_string;
		private long version;
		private String groupid;
		
		
		public detail_string getDetail_string() {
			return detail_string;
		}
		public void setDetail_string(detail_string detail_string) {
			this.detail_string = detail_string;
		}
		public String getCategory_name() {
			return category_name;
		}
		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}
		public long getVersion() {
			return version;
		}
		public void setVersion(long version) {
			this.version = version;
		}
		public String getGroupid() {
			return groupid;
		}
		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}
	}
	
	public class detail_string implements Serializable, Cloneable{
		
	}
	
	public class packages implements Serializable, Cloneable{
		private String condition;
		private String device;
		private String filename;
		private String groupid;
		private String monthly_price;
		private String one_time_price;
		private String product_desc;
		private String product_id;
		private String product_name;
		private String version;
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public String getDevice() {
			return device;
		}
		public void setDevice(String device) {
			this.device = device;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public String getGroupid() {
			return groupid;
		}
		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}
		public String getMonthly_price() {
			return monthly_price;
		}
		public void setMonthly_price(String monthly_price) {
			this.monthly_price = monthly_price;
		}
		public String getOne_time_price() {
			return one_time_price;
		}
		public void setOne_time_price(String one_time_price) {
			this.one_time_price = one_time_price;
		}
		public String getProduct_desc() {
			return product_desc;
		}
		public void setProduct_desc(String product_desc) {
			this.product_desc = product_desc;
		}
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
	}

	public String getWorknumber() {
		return worknumber;
	}

	public void setWorknumber(String worknumber) {
		this.worknumber = worknumber;
	}

	public List<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	}


	public String getCivilstatus() {
		return civilstatus;
	}

	public void setCivilstatus(String civilstatus) {
		this.civilstatus = civilstatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmployement() {
		return employement;
	}

	public void setEmployement(String employement) {
		this.employement = employement;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMothers_maiden_name() {
		return mothers_maiden_name;
	}

	public void setMothers_maiden_name(String mothers_maiden_name) {
		this.mothers_maiden_name = mothers_maiden_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public address getAddress() {
		return address;
	}

	public void setAddress(address address) {
		this.address = address;
	}

	public namelistvalues getNamelistvalues() {
		return namelistvalues;
	}

	public void setNamelistvalues(namelistvalues namelistvalues) {
		this.namelistvalues = namelistvalues;
	}

	public packageplan getPackageplan() {
		return packageplan;
	}

	public void setPackageplan(packageplan packageplan) {
		this.packageplan = packageplan;
	}

	public packageplandetails getPackageplandetails() {
		return packageplandetails;
	}

	public void setPackageplandetails(packageplandetails packageplandetails) {
		this.packageplandetails = packageplandetails;
	}

	public signature getSignature() {
		return signature;
	}

	public void setSignature(signature signature) {
		this.signature = signature;
	}
	
	public packages getPackages() {
		return packages;
	}

	public void setPackages(packages packages) {
		this.packages = packages;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}
