package com.circles.model;

import java.math.BigInteger;
import java.util.Date;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ApplicationModel {

	private String application_id;
	private String title;
	private String birthPlace;
	private String occupation;
	private String positionLevel;
	private String positionTitle;
	private String tenure;
	private String monthlyIncome;
	private String companyName;
	private String businessNature;
	private String tin;
	private String sss;
	private String civilStatus;
	private String gender;
	private int dependents;
	private String otherPhoneSubscriptions;
	private String fundSource;
	private String spouseFirstName;
	private String spouseMiddleName;
	private String spouseLastName;
	private BigInteger spouseBirthday;
	private String mothersMaidenName;
	private String phoneCode;
	private String planCode;
	private int status;
	private String refNo;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private BigInteger birthday;
	private int type;
	private String image;
	private String terminationImage;
	private String addRegion;
	private String addCity;
	private String addBrgy;
	private String addLine1;
	private String addLine2;
	private String zipCode;
	private String addWorkRegion;
	private String addWorkCity;
	private String addWorkBrgy;
	private String addWorkLine1;
	private String addWorkLine2;
	private String workZipCode;
	private int sendBillTo;
	private String telephone;
	private String mobile;
	private int phoneRetrivalType;
	private String businessCenterName;
	private int businessCenterLng;
	private int businessCenterLat;
	private String ccNumber;
	private String ccBank;
	private String ppEmail;
	private String ppRefNo;
	private String codAmt;
	private String docIdentitySss;
	private String docIdentitySssNo;
	private String docPagibig;
	private String docPagibigNo;
	private String docIdentityTin;
	private String docIdentityTinNo;
	private String docIdentityDriversLicense;
	private String docIdentityDriversLicenseNo;
	private String docIdentityPassport;
	private String docIdentityPassportNo;
	private String docIdentityCompanyId;
	private String docIdentityCompanyIdNo;
	private String docIdentityOthers;
	private String docIdentityOthersNo;
	private String docAddressElectricity;
	private String docAddressElectriciyNo;
	private String docAddressWater;
	private String docAddressWaterNo;
	private String docAddressIsp;
	private String docAddressIspNo;
	private String docAddressCable;
	private String docAddressCableNo;
	private String docAddressTelecom;
	private String docAddressTelecomNo;
	private String docAddressBankLoan;
	private String docAddressBankLoanNo;
	private String docAddressOthers;
	private String docAddressOthersNo;
	private String docIncomeBankStatement;
	private String docIncomeBankStatementNo;
	private String docIncomePaySlip;
	private String docIncomePaySlipNo;
	private String docIncomeSecurity;
	private String docIncomeSecurityNo;
	private String docIncomeBonds;
	private String docIncomeBondsNo;
	private String docIncomeStockcert;
	private String docIncomeStockcertNo;
	private String docIncomeCompanyOwnership;
	private String docIncomeCompanyOwnershipNo;
	private String docIncomeOthers;
	private String docIncomeOthersNo;
	private String docIncomeAutoCharge;
	private String docIncomeAutoChargeNo;
	private String docTermsSig1;
	private String docTermsSig2;
	private String docTermsSig3;
	private int step1Done;
	private int step2Done;
	private int step3Done;
	private String declineRemarks;
	private int isPushed;
	private String edited_by;
	private BigInteger created;
	private BigInteger update;
	private int version;
	private String fullname;
	
	
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPositionLevel() {
		return positionLevel;
	}
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	public String getPositionTitle() {
		return positionTitle;
	}
	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBusinessNature() {
		return businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	public String getSss() {
		return sss;
	}
	public void setSss(String sss) {
		this.sss = sss;
	}
	public String getCivilStatus() {
		return civilStatus;
	}
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getDependents() {
		return dependents;
	}
	public void setDependents(int dependents) {
		this.dependents = dependents;
	}
	public String getOtherPhoneSubscriptions() {
		return otherPhoneSubscriptions;
	}
	public void setOtherPhoneSubscriptions(String otherPhoneSubscriptions) {
		this.otherPhoneSubscriptions = otherPhoneSubscriptions;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getSpouseFirstName() {
		return spouseFirstName;
	}
	public void setSpouseFirstName(String spouseFirstName) {
		this.spouseFirstName = spouseFirstName;
	}
	public String getSpouseMiddleName() {
		return spouseMiddleName;
	}
	public void setSpouseMiddleName(String spouseMiddleName) {
		this.spouseMiddleName = spouseMiddleName;
	}
	public String getSpouseLastName() {
		return spouseLastName;
	}
	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}
	public BigInteger getSpouseBirthday() {
		return spouseBirthday;
	}
	public void setSpouseBirthday(BigInteger spouseBirthday) {
		this.spouseBirthday = spouseBirthday;
	}
	public String getMothersMaidenName() {
		return mothersMaidenName;
	}
	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}
	public String getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigInteger getBirthday() {
		return birthday;
	}
	public void setBirthday(BigInteger birthday) {
		this.birthday = birthday;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTerminationImage() {
		return terminationImage;
	}
	public void setTerminationImage(String terminationImage) {
		this.terminationImage = terminationImage;
	}
	public String getAddRegion() {
		return addRegion;
	}
	public void setAddRegion(String addRegion) {
		this.addRegion = addRegion;
	}
	public String getAddCity() {
		return addCity;
	}
	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}
	public String getAddBrgy() {
		return addBrgy;
	}
	public void setAddBrgy(String addBrgy) {
		this.addBrgy = addBrgy;
	}
	public String getAddLine1() {
		return addLine1;
	}
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}
	public String getAddLine2() {
		return addLine2;
	}
	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddWorkRegion() {
		return addWorkRegion;
	}
	public void setAddWorkRegion(String addWorkRegion) {
		this.addWorkRegion = addWorkRegion;
	}
	public String getAddWorkCity() {
		return addWorkCity;
	}
	public void setAddWorkCity(String addWorkCity) {
		this.addWorkCity = addWorkCity;
	}
	public String getAddWorkBrgy() {
		return addWorkBrgy;
	}
	public void setAddWorkBrgy(String addWorkBrgy) {
		this.addWorkBrgy = addWorkBrgy;
	}
	public String getAddWorkLine1() {
		return addWorkLine1;
	}
	public void setAddWorkLine1(String addWorkLine1) {
		this.addWorkLine1 = addWorkLine1;
	}
	public String getAddWorkLine2() {
		return addWorkLine2;
	}
	public void setAddWorkLine2(String addWorkLine2) {
		this.addWorkLine2 = addWorkLine2;
	}
	public String getWorkZipCode() {
		return workZipCode;
	}
	public void setWorkZipCode(String workZipCode) {
		this.workZipCode = workZipCode;
	}
	public int getSendBillTo() {
		return sendBillTo;
	}
	public void setSendBillTo(int sendBillTo) {
		this.sendBillTo = sendBillTo;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getPhoneRetrivalType() {
		return phoneRetrivalType;
	}
	public void setPhoneRetrivalType(int phoneRetrivalType) {
		this.phoneRetrivalType = phoneRetrivalType;
	}
	public String getBusinessCenterName() {
		return businessCenterName;
	}
	public void setBusinessCenterName(String businessCenterName) {
		this.businessCenterName = businessCenterName;
	}
	public int getBusinessCenterLng() {
		return businessCenterLng;
	}
	public void setBusinessCenterLng(int businessCenterLng) {
		this.businessCenterLng = businessCenterLng;
	}
	public int getBusinessCenterLat() {
		return businessCenterLat;
	}
	public void setBusinessCenterLat(int businessCenterLat) {
		this.businessCenterLat = businessCenterLat;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcBank() {
		return ccBank;
	}
	public void setCcBank(String ccBank) {
		this.ccBank = ccBank;
	}
	public String getPpEmail() {
		return ppEmail;
	}
	public void setPpEmail(String ppEmail) {
		this.ppEmail = ppEmail;
	}
	public String getPpRefNo() {
		return ppRefNo;
	}
	public void setPpRefNo(String ppRefNo) {
		this.ppRefNo = ppRefNo;
	}
	public String getCodAmt() {
		return codAmt;
	}
	public void setCodAmt(String codAmt) {
		this.codAmt = codAmt;
	}
	public String getDocIdentitySss() {
		return docIdentitySss;
	}
	public void setDocIdentitySss(String docIdentitySss) {
		this.docIdentitySss = docIdentitySss;
	}
	public String getDocIdentitySssNo() {
		return docIdentitySssNo;
	}
	public void setDocIdentitySssNo(String docIdentitySssNo) {
		this.docIdentitySssNo = docIdentitySssNo;
	}
	public String getDocPagibig() {
		return docPagibig;
	}
	public void setDocPagibig(String docPagibig) {
		this.docPagibig = docPagibig;
	}
	public String getDocPagibigNo() {
		return docPagibigNo;
	}
	public void setDocPagibigNo(String docPagibigNo) {
		this.docPagibigNo = docPagibigNo;
	}
	public String getDocIdentityTin() {
		return docIdentityTin;
	}
	public void setDocIdentityTin(String docIdentityTin) {
		this.docIdentityTin = docIdentityTin;
	}
	public String getDocIdentityTinNo() {
		return docIdentityTinNo;
	}
	public void setDocIdentityTinNo(String docIdentityTinNo) {
		this.docIdentityTinNo = docIdentityTinNo;
	}
	public String getDocIdentityDriversLicense() {
		return docIdentityDriversLicense;
	}
	public void setDocIdentityDriversLicense(String docIdentityDriversLicense) {
		this.docIdentityDriversLicense = docIdentityDriversLicense;
	}
	public String getDocIdentityDriversLicenseNo() {
		return docIdentityDriversLicenseNo;
	}
	public void setDocIdentityDriversLicenseNo(String docIdentityDriversLicenseNo) {
		this.docIdentityDriversLicenseNo = docIdentityDriversLicenseNo;
	}
	public String getDocIdentityPassport() {
		return docIdentityPassport;
	}
	public void setDocIdentityPassport(String docIdentityPassport) {
		this.docIdentityPassport = docIdentityPassport;
	}
	public String getDocIdentityPassportNo() {
		return docIdentityPassportNo;
	}
	public void setDocIdentityPassportNo(String docIdentityPassportNo) {
		this.docIdentityPassportNo = docIdentityPassportNo;
	}
	public String getDocIdentityCompanyId() {
		return docIdentityCompanyId;
	}
	public void setDocIdentityCompanyId(String docIdentityCompanyId) {
		this.docIdentityCompanyId = docIdentityCompanyId;
	}
	public String getDocIdentityCompanyIdNo() {
		return docIdentityCompanyIdNo;
	}
	public void setDocIdentityCompanyIdNo(String docIdentityCompanyIdNo) {
		this.docIdentityCompanyIdNo = docIdentityCompanyIdNo;
	}
	public String getDocIdentityOthers() {
		return docIdentityOthers;
	}
	public void setDocIdentityOthers(String docIdentityOthers) {
		this.docIdentityOthers = docIdentityOthers;
	}
	public String getDocIdentityOthersNo() {
		return docIdentityOthersNo;
	}
	public void setDocIdentityOthersNo(String docIdentityOthersNo) {
		this.docIdentityOthersNo = docIdentityOthersNo;
	}
	public String getDocAddressElectricity() {
		return docAddressElectricity;
	}
	public void setDocAddressElectricity(String docAddressElectricity) {
		this.docAddressElectricity = docAddressElectricity;
	}
	public String getDocAddressElectriciyNo() {
		return docAddressElectriciyNo;
	}
	public void setDocAddressElectriciyNo(String docAddressElectriciyNo) {
		this.docAddressElectriciyNo = docAddressElectriciyNo;
	}
	public String getDocAddressWater() {
		return docAddressWater;
	}
	public void setDocAddressWater(String docAddressWater) {
		this.docAddressWater = docAddressWater;
	}
	public String getDocAddressWaterNo() {
		return docAddressWaterNo;
	}
	public void setDocAddressWaterNo(String docAddressWaterNo) {
		this.docAddressWaterNo = docAddressWaterNo;
	}
	public String getDocAddressIsp() {
		return docAddressIsp;
	}
	public void setDocAddressIsp(String docAddressIsp) {
		this.docAddressIsp = docAddressIsp;
	}
	public String getDocAddressIspNo() {
		return docAddressIspNo;
	}
	public void setDocAddressIspNo(String docAddressIspNo) {
		this.docAddressIspNo = docAddressIspNo;
	}
	public String getDocAddressCable() {
		return docAddressCable;
	}
	public void setDocAddressCable(String docAddressCable) {
		this.docAddressCable = docAddressCable;
	}
	public String getDocAddressCableNo() {
		return docAddressCableNo;
	}
	public void setDocAddressCableNo(String docAddressCableNo) {
		this.docAddressCableNo = docAddressCableNo;
	}
	public String getDocAddressTelecom() {
		return docAddressTelecom;
	}
	public void setDocAddressTelecom(String docAddressTelecom) {
		this.docAddressTelecom = docAddressTelecom;
	}
	public String getDocAddressTelecomNo() {
		return docAddressTelecomNo;
	}
	public void setDocAddressTelecomNo(String docAddressTelecomNo) {
		this.docAddressTelecomNo = docAddressTelecomNo;
	}
	public String getDocAddressBankLoan() {
		return docAddressBankLoan;
	}
	public void setDocAddressBankLoan(String docAddressBankLoan) {
		this.docAddressBankLoan = docAddressBankLoan;
	}
	public String getDocAddressBankLoanNo() {
		return docAddressBankLoanNo;
	}
	public void setDocAddressBankLoanNo(String docAddressBankLoanNo) {
		this.docAddressBankLoanNo = docAddressBankLoanNo;
	}
	public String getDocAddressOthers() {
		return docAddressOthers;
	}
	public void setDocAddressOthers(String docAddressOthers) {
		this.docAddressOthers = docAddressOthers;
	}
	public String getDocAddressOthersNo() {
		return docAddressOthersNo;
	}
	public void setDocAddressOthersNo(String docAddressOthersNo) {
		this.docAddressOthersNo = docAddressOthersNo;
	}
	public String getDocIncomeBankStatement() {
		return docIncomeBankStatement;
	}
	public void setDocIncomeBankStatement(String docIncomeBankStatement) {
		this.docIncomeBankStatement = docIncomeBankStatement;
	}
	public String getDocIncomeBankStatementNo() {
		return docIncomeBankStatementNo;
	}
	public void setDocIncomeBankStatementNo(String docIncomeBankStatementNo) {
		this.docIncomeBankStatementNo = docIncomeBankStatementNo;
	}
	public String getDocIncomePaySlip() {
		return docIncomePaySlip;
	}
	public void setDocIncomePaySlip(String docIncomePaySlip) {
		this.docIncomePaySlip = docIncomePaySlip;
	}
	public String getDocIncomePaySlipNo() {
		return docIncomePaySlipNo;
	}
	public void setDocIncomePaySlipNo(String docIncomePaySlipNo) {
		this.docIncomePaySlipNo = docIncomePaySlipNo;
	}
	public String getDocIncomeSecurity() {
		return docIncomeSecurity;
	}
	public void setDocIncomeSecurity(String docIncomeSecurity) {
		this.docIncomeSecurity = docIncomeSecurity;
	}
	public String getDocIncomeSecurityNo() {
		return docIncomeSecurityNo;
	}
	public void setDocIncomeSecurityNo(String docIncomeSecurityNo) {
		this.docIncomeSecurityNo = docIncomeSecurityNo;
	}
	public String getDocIncomeBonds() {
		return docIncomeBonds;
	}
	public void setDocIncomeBonds(String docIncomeBonds) {
		this.docIncomeBonds = docIncomeBonds;
	}
	public String getDocIncomeBondsNo() {
		return docIncomeBondsNo;
	}
	public void setDocIncomeBondsNo(String docIncomeBondsNo) {
		this.docIncomeBondsNo = docIncomeBondsNo;
	}
	public String getDocIncomeStockcert() {
		return docIncomeStockcert;
	}
	public void setDocIncomeStockcert(String docIncomeStockcert) {
		this.docIncomeStockcert = docIncomeStockcert;
	}
	public String getDocIncomeStockcertNo() {
		return docIncomeStockcertNo;
	}
	public void setDocIncomeStockcertNo(String docIncomeStockcertNo) {
		this.docIncomeStockcertNo = docIncomeStockcertNo;
	}
	public String getDocIncomeCompanyOwnership() {
		return docIncomeCompanyOwnership;
	}
	public void setDocIncomeCompanyOwnership(String docIncomeCompanyOwnership) {
		this.docIncomeCompanyOwnership = docIncomeCompanyOwnership;
	}
	public String getDocIncomeCompanyOwnershipNo() {
		return docIncomeCompanyOwnershipNo;
	}
	public void setDocIncomeCompanyOwnershipNo(String docIncomeCompanyOwnershipNo) {
		this.docIncomeCompanyOwnershipNo = docIncomeCompanyOwnershipNo;
	}
	public String getDocIncomeOthers() {
		return docIncomeOthers;
	}
	public void setDocIncomeOthers(String docIncomeOthers) {
		this.docIncomeOthers = docIncomeOthers;
	}
	public String getDocIncomeOthersNo() {
		return docIncomeOthersNo;
	}
	public void setDocIncomeOthersNo(String docIncomeOthersNo) {
		this.docIncomeOthersNo = docIncomeOthersNo;
	}
	public String getDocIncomeAutoCharge() {
		return docIncomeAutoCharge;
	}
	public void setDocIncomeAutoCharge(String docIncomeAutoCharge) {
		this.docIncomeAutoCharge = docIncomeAutoCharge;
	}
	public String getDocIncomeAutoChargeNo() {
		return docIncomeAutoChargeNo;
	}
	public void setDocIncomeAutoChargeNo(String docIncomeAutoChargeNo) {
		this.docIncomeAutoChargeNo = docIncomeAutoChargeNo;
	}
	public String getDocTermsSig1() {
		return docTermsSig1;
	}
	public void setDocTermsSig1(String docTermsSig1) {
		this.docTermsSig1 = docTermsSig1;
	}
	public String getDocTermsSig2() {
		return docTermsSig2;
	}
	public void setDocTermsSig2(String docTermsSig2) {
		this.docTermsSig2 = docTermsSig2;
	}
	public String getDocTermsSig3() {
		return docTermsSig3;
	}
	public void setDocTermsSig3(String docTermsSig3) {
		this.docTermsSig3 = docTermsSig3;
	}
	public int getStep1Done() {
		return step1Done;
	}
	public void setStep1Done(int step1Done) {
		this.step1Done = step1Done;
	}
	public int getStep2Done() {
		return step2Done;
	}
	public void setStep2Done(int step2Done) {
		this.step2Done = step2Done;
	}
	public int getStep3Done() {
		return step3Done;
	}
	public void setStep3Done(int step3Done) {
		this.step3Done = step3Done;
	}
	public String getDeclineRemarks() {
		return declineRemarks;
	}
	public void setDeclineRemarks(String declineRemarks) {
		this.declineRemarks = declineRemarks;
	}
	public int getIsPushed() {
		return isPushed;
	}
	public void setIsPushed(int isPushed) {
		this.isPushed = isPushed;
	}
	public String getEdited_by() {
		return edited_by;
	}
	public void setEdited_by(String edited_by) {
		this.edited_by = edited_by;
	}
	public BigInteger getCreated() {
		return created;
	}
	public void setCreated(BigInteger created) {
		this.created = created;
	}
	public BigInteger getUpdate() {
		return update;
	}
	public void setUpdate(BigInteger update) {
		this.update = update;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	

	
}

