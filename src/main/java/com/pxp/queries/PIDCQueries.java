//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.queries;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pxp.base.ConstantConfigData;
import com.pxp.base.TestBase;
import com.pxp.util.DBUtils;
import com.pxp.util.PIDCInfo;

public class PIDCQueries extends DBUtils {
	private ResultSet rs;
	private String query = null;
	PIDCInfo pidcInfo = new PIDCInfo();

	public void updatePersonDetails(String email, String dob, int zipCode) throws SQLException {
		query = "UPDATE [dbo].[person] SET [email_address] = '" + email + "',[date_of_birth] = '" + dob + "',[Zip]='"
				+ zipCode + "' WHERE person_id='" + UUID.fromString(pidcInfo.getPersonId()) + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite indicator status with query: " + query);
	}

	public ResultSet getUpdatedDetails() throws SQLException {
		query = "select email_address, date_of_birth, Zip,* from person p1 left outer join pxp_enrollments p2 "
				+ "on p1.person_id = p2.person_id where p1.person_id = '" + UUID.fromString(pidcInfo.getPersonId())
				+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient updated details with query: " + query);
		return rs;
	}

	public void updateEnrollStatus() throws SQLException {
		query = "update [dbo].[pxp_enrollments] set [enrollment_status]=1,[processing_status]=1 where person_id= '"
				+ UUID.fromString(pidcInfo.getPersonId()) + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update enroll status with query: " + query);
	}

	public ResultSet getUpdatedDemographicsDetails(String personId) throws Exception {
		query = "select * from person where person_id='" + personId+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient updated patient details with query: " + query);
		return rs;
	}

	public ResultSet getEncounterDetails(String personId) throws SQLException {
		query = "select * from patient_encounter where person_id= '" + UUID.fromString(personId) + "'";

		// RS Code: Use this isDataPopulatedForTable() method repeatedly executes this
		// query until the data is found in the table or the 3-minute timeout is
		// reached.
		new DBUtils().isDataPopulatedForTable(query);

		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		return rs;
	}

	public ResultSet getPatientMedicationsDetails(String personId) throws SQLException {
		query = "select * from patient_medication where person_id= '" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get Medication patient details with query: " + query);
		return rs;
	}

	public ResultSet getMedicationIndStatus(String mfDb) throws SQLException {

		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=13";
		rs = executeQuery(query);
		TestBase.log.info("Get Medication_IDLE Agent status with query: " + query);
		return rs;
	}

	public void setMedicationIDLEAgentOff() throws SQLException {
		query = "update agent_solution_config set isassociated=0, isactive=0, status='STOP' where id=18";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public ResultSet getMedicationIndStatusUpdate() throws SQLException {
		query = "select * from [pxpconfigdb].[dbo].[agent_solution_config] where id=13";
		rs = executeQuery(query);
		TestBase.log.info("Get Medication_IDLE Agent status with query: " + query);
		return rs;
	}

	public ResultSet getMedicationUpdateIndStatus() throws SQLException {
		query = "select * from [pxpconfigdb].[dbo].[agent_solution_config] where id=13";
		rs = executeQuery(query);
		TestBase.log.info("Get auto update demographics indicator status with query: " + query);
		return rs;
	}

	public void setMedicationUpdateAgentOff() throws SQLException {
		query = "update agent_solution_config set isassociated=0, isactive=0, status='STOP' where id=13";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public void setMedicationUpdateAgentON() throws SQLException {
		query = "update agent_solution_config set isassociated=1, isactive=1, status='RUNNING' where id=13";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public ResultSet getMedicationUpdateStatus(String mfDb) throws SQLException {
		query = "select * from ["+ mfDb +"].[dbo].[agent_solution_config] where id=18";
		rs = executeQuery(query);
		TestBase.log.info("Get Medication Update status with query: " + query);
		return rs;
	}

	public void setMedicationAgentOff() throws SQLException {
		query = "update agent_solution_config set isassociated=0, isactive=0, status='STOP' where id=13";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public ResultSet getPatientDetails(String firstName, String LastName) throws SQLException {
		query = "select * from person where first_name= '" + firstName + "' and last_name = '" + LastName + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		return rs;
	}

	public ResultSet getInactivePatient() throws SQLException {
		query = "select email_address,* from person p1 left outer join pxp_enrollments p2 on p1.person_id = p2.person_id where enrollment_status = '1' "
				+ "and processing_status IN ('2','3') and p1.first_name like 'Ron%' ";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		return rs;
	}

	public ResultSet getPatient(String personId) throws SQLException {
		query = "select * from person p1 left outer join pxp_enrollments p2 on p1.person_id = p2.person_id where p1.person_id ='"+ personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		return rs;
	}

	public ResultSet getActivatedPatientStatus(String personId) throws SQLException {
		query = "select enrollment_status, processing_status from pxp_enrollments p1 left outer join person p2 on p1.person_id = p2.person_id where p1.person_id= '"
				+ personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient status with query: " + query);
		return rs;
	}

	public ResultSet getActivatedPatientDetails() throws SQLException {
		query = "select email_address,* from person p1 left outer join pxp_enrollments p2 on p1.person_id = p2.person_id "
				+ "where enrollment_status = '9' and processing_status ='4' and p1.first_name like 'Ron%' order by p1.create_timestamp desc";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient details with query: " + query);
		return rs;
	}

	public ResultSet getPatientDetailsWithPersonId(String ngdb, String personId) throws SQLException {
		query = "select * from [" + ngdb + "].[dbo].[person] where person_id = '" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient details with query: " + query);
		return rs;
	}

	public void updatePersonDetails(String email, String dob, int zipCode, String personId) throws SQLException {
		query = "UPDATE [dbo].[person] SET [email_address] = '" + email + "',[date_of_birth] = '" + dob + "',[Zip]='"
				+ zipCode + "', modify_timestamp = '"+getDate()+"' WHERE person_id='" + UUID.fromString(personId) + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update person details with query: " + query);
	}

	public ResultSet getUpdatedDetails(String personId) throws SQLException {
		query = "select email_address, date_of_birth, Zip,* from person p1 left outer join pxp_enrollments p2 "
				+ "on p1.person_id = p2.person_id where p1.person_id = '" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient updated details with query: " + query);
		return rs;
	}

	public void updateEnrollStatus(String personId) throws SQLException {
		query = "update [dbo].[pxp_enrollments] set [enrollment_status]=1,[processing_status]=1, modify_timestamp = '"+getDate()+"' where person_id= '"
				+personId+"'";
		executeUpdateQuery(query);
	}

	public ResultSet getProcessingStatusEntityDetails(String personId) throws SQLException {
		query = " select *" + " FROM [" + ConstantConfigData.MFDB + "].[dbo].[processingstatus_entity] t1" + " JOIN [" + ConstantConfigData.NGDB
				+ "].[dbo].[person] t2 ON t1.entityidentifier = ltrim(t2.person_nbr) where t2.person_id = '" + personId
				+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get processing status with query: " + query);
		return rs;
	}

	public ResultSet getAutoInviteIndicatorStatus() throws SQLException {
		query = "select * from configuration_options where key_name like 'AutoInvite_Patient' and instance_name = '0001'";
		rs = executeQuery(query);
		TestBase.log.info("Get auto invite indicator status with query: " + query);
		return rs;
	}

	public void setAutoInviteIndicatorOff() throws SQLException {
		query = "update configuration_options set option_value = '0' where key_name = 'AutoInvite_Patient' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite indicator status with query: " + query);
	}

	public void setAutoInviteIndicatorOn() throws SQLException {
		query = "update configuration_options set option_value = '1' where key_name = 'AutoInvite_Patient' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite indicator status with query: " + query);
	}

	public ResultSet getAutoAgeFilterIndicatorStatus() throws SQLException {
		query = "select * from configuration_options where key_name like 'AutoInvite_AgeFilter' and instance_name = '0001'";
		rs = executeQuery(query);
		TestBase.log.info("Get age filter indicator status with query: " + query);
		return rs;
	}

	public void setAgeFilterIndicatorOff() throws SQLException {
		query = "update configuration_options set option_value = '0' where key_name = 'AutoInvite_AgeFilter' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite indicator status with query: " + query);
	}

	public void setAgeFilterIndicatorOn() throws SQLException {
		query = "update configuration_options set option_value = '1' where key_name = 'AutoInvite_AgeFilter' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update age filter indicator status with query: " + query);
	}

	public ResultSet getAutoInviteState() throws SQLException {
		query = "select option_value from configuration_options where key_name like 'AutoInvite_State' and instance_name = '0001'";
		rs = executeQuery(query);
		TestBase.log.info("Get auto invite state with query: " + query);
		return rs;
	}

	public ResultSet getAutoInviteScheduleIndicatorStatus() throws SQLException {
		query = "select * from [dbo].[configuration_options] where key_name = 'AutoInvite_ScheduleFilter' and instance_name = '0001'";
		rs = executeQuery(query);
		TestBase.log.info("Get auto invite schedule indicator status with query: " + query);
		return rs;
	}

	public void setAutoInviteScheduleIndicatorOff() throws SQLException {
		query = "update configuration_options set option_value = '0' where key_name = 'AutoInvite_ScheduleFilter' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite schedule indicator status with query: " + query);
	}

	public void setAgeForAgeFilter(String age, String columnName) throws SQLException {
		query = "update [dbo].[configuration_options] set option_value = '" + age
				+ "' where key_name = '" + columnName + "' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update Age with query: " + query);
	}

	public void setAutoInviteScheduleTime(String setTime, String columnName) throws SQLException {

		query = "update [dbo].[configuration_options] set option_value = '" + setTime
				+ "' where key_name = '" + columnName + "' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite schedule indicator status with query: " + query);
	}

	public void setAutoInviteScheduleIndicatorOn() throws SQLException {
		query = "update configuration_options set option_value = '1' where key_name = 'AutoInvite_ScheduleFilter' and instance_name = '0001'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite schedule indicator status with query: " + query);
	}

	public void setAutoInviteState() throws SQLException {
		query = "update configuration_options set option_value = '[\"CN\",\"CZ\"]' where key_name like 'AutoInvite_State' and instance_name = '"+ConstantConfigData.DEFAULTPRACTICEID+"'";
		executeUpdateQuery(query);
		TestBase.log.info("Update state for age range with query: " + query);
	}

	public ResultSet getEnrollStatus(String personId) throws SQLException {
		query = "select enrollment_status, processing_status from [dbo].[pxp_enrollments] p1 left outer join [dbo].[person] p2 on p1.person_id = p2.person_id where p2.person_id= '" + personId+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient enroll status with query: " + query);
		return rs;
	}

	public ResultSet getDuplicatePatientInviteIndStatus(String ngDB) throws SQLException {
		query = "select * from service_setting where [key]='enable_duplicate_invite' and practice_id = '4'";
		rs = executeQuery(query);
		TestBase.log.info("Get duplicate patient invite status with query: " + query);
		return rs;
	}

	public ResultSet getAutoUpdateDemographicsIndStatus() throws SQLException {
		query = "select * from [" + ConstantConfigData.MFDB + "].[dbo].service_setting where [key]='enableautoacceptdemographic' and practice_id = '"+ConstantConfigData.PRACTICEID+"'";
		rs = executeQuery(query);
		TestBase.log.info("Get auto update demographics indicator status with query: " + query);
		return rs;
	}

	public ResultSet getAutoUpdateDemographics(String personId) throws SQLException {
		query = "select *" + " FROM [" + ConstantConfigData.MFDB + "].[dbo].[person_record] t1" + " JOIN [" + ConstantConfigData.MFDB
				+ "].[dbo].[person] t2 ON t1.person_id = t2.id" + "	 JOIN [" + ConstantConfigData.NGDB
				+ "].[dbo].[person] t3 ON t2.externalid = ltrim(t3.person_nbr) where t3.person_id = '"
				+ personId+"';";
		rs = executeQuery(query);
		TestBase.log.info("Get auto update demographics indicator status with query: " + query);
		return rs;
	}

	public void setAutoUpdateDemographicsIndOn() throws SQLException {
		query = "update service_setting set [value]= 1 where [key]='enableautoacceptdemographic' and practice_id = '4'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public void setAutoUpdateDemographicsIndOff() throws SQLException {
		query = "update service_setting set [value]= 0 where [key]='enableautoacceptdemographic' and practice_id = '4'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public void changeDatabase(String dbName) throws SQLException {
		query = "use " + dbName;
		executeUpdateQuery(query);
		TestBase.log.info("Connect to database: " + dbName);
	}

	public ResultSet getEnterpriseChartIndStatus() throws SQLException {
		query = "select * from [ngqa_59ud5_medfusionSIT].[dbo].[patient_enterprise_chart] where person_id='B5BF053F-8E45-41E0-8507-CB46A0564B90'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient Enterprise Chart indicator status: " + query);
		return rs;
	}

	public void setEnterpriseChartAgentFlipOFF() throws SQLException {
		query = "update [ngqa_59ud5_medfusionSIT].[dbo].[patient_enterprise_chart] set enterprise_chart_ind='N' where person_id='B5BF053F-8E45-41E0-8507-CB46A0564B90'";
		executeUpdateQuery(query);
		TestBase.log.info("Update patient Enterprise Chart indicator status with query: " + query);
	}

	public void setEnterpriseChartAgentFlipON() throws SQLException {
		query = "update [ngqa_59ud5_medfusionSIT].[dbo].[patient_enterprise_chart] set enterprise_chart_ind='Y' where person_id='B5BF053F-8E45-41E0-8507-CB46A0564B90'";
		executeUpdateQuery(query);
		TestBase.log.info("Update patient Enterprise Chart indicator status with query: " + query);
	}

	public ResultSet getPharmacySyncAgentStatus() throws SQLException {
		query = "select * from [pxpconfigdb].[dbo].[agent_solution_config] where id=41";
		rs = executeQuery(query);
		TestBase.log.info("Get Pharmacy Sync Agent status with query: " + query);
		return rs;
	}

	public void setPharmacySyncAgentOff() throws SQLException {
		query = "update agent_solution_config set isassociated=0, isactive=0, status='STOP' where id=41";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto update demographics indicator status with query: " + query);
	}

	public ResultSet getReadReceiptDetails(String personId, String Subject) throws SQLException {
		query = "select * from ngweb_communications a1 join ngweb_comm_recpts a2 on a1.comm_id = a2.comm_id join person a3  on a2.recipient_id = CAST(a3.person_id as varchar(50)) where a3.person_id ='"
				+ personId + "' and subject = '" + Subject + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get Read Receipt Detailswith query: " + query);
		return rs;
	}

	public ResultSet getReadReceiptReadTime(String Comm_id, String personId) throws SQLException {
		query = "select * from ngweb_communications a1 join ngweb_comm_recpts a2 on a1.comm_id = a2.comm_id join person a3  on a2.recipient_id = CAST(a3.person_id as varchar(50)) where a3.person_id ='"
				+ personId + "' and a2.comm_id = '" + Comm_id + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get auto invite state with query: " + query);
		return rs;
	}

	public ResultSet getReadReceiptWhenCheckboxDisable(String Comm_id, String personId) throws SQLException {
		query = "select * from ngweb_communications a1 join ngweb_comm_recpts a2 on a1.comm_id = a2.comm_id join person a3  on a2.recipient_id = CAST(a3.person_id as varchar(50)) where a3.person_id = '"
				+ UUID.fromString(personId) + "'and read_receipt='N'";
		rs = executeQuery(query);
		TestBase.log.info("Get Read Receipt state with query: " + query);
		return rs;
	}

	public ResultSet getReadReceiptStatus(String mfDB) throws SQLException {
		query = "select * from [" + mfDB + "].[dbo].[agent_solution_config] where practice_id=4  and agent_id=13";
		rs = executeQuery(query);
		TestBase.log.info("Get ReadReceipt Agent status with query: " + query);
		return rs;
	}

	public ResultSet getActivatedPatientLastLoginDetails(String personId) throws SQLException {
		query = "select * from pxp_enrollments p1 left outer join person p2 on p1.person_id = p2.person_id where p1.person_id= '"
				+ UUID.fromString(personId) + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient status with query: " + query);
		return rs;
	}

	public ResultSet updatestatusPendingForLastLogin(String personId) throws SQLException {
		query = "update [dbo].[pxp_enrollments] set [enrollment_status]=1,[processing_status]=3 where person_id= '"
				+ UUID.fromString(personId) + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update enroll status with query: " + query);
		return rs;
	}

	public ResultSet getActivatedPatientStatusForLA(String personId) throws SQLException {
		query = "select enrollment_status, processing_status,last_login_timestamp from pxp_enrollments p1 left outer join person p2 on p1.person_id = p2.person_id where p1.person_id= '"
				+ UUID.fromString(personId) + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient status with query: " + query);
		return rs;
	}

	public ResultSet getActivatePatientforsecondPractice(String personId) throws SQLException {
		query = "select * from pxp_enrollments where practice_id ='0002' and person_id='" + UUID.fromString(personId)
				+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient status with query: " + query);
		return rs;
	}

	public ResultSet getLastLoginTimeMultiplePatient(String practice_id, String personId) throws SQLException {
		query = " Select * from pxp_enrollments where practice_id ='" + practice_id + "' and person_id='" + personId
				+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient status with query: " + query);
		return rs;
	}

	public ResultSet getInsuranceStatus() throws SQLException {
		query = "select * from person_payer where person_id = '" + pidcInfo.getPersonId() + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get insurance name with query: " + query);
		return rs;
	}

	public void createPharmaForPatient() throws SQLException {
		query = "update patient set pharmacy_code_1_id = '97994F28-ADC4-4C6B-8B96-F4DE30F1B6B8' where person_id = '"
				+ pidcInfo.getPersonId() + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Pharmacy has been successfully added for the patient " + pidcInfo.getPatientFirstName());
	}

	public ResultSet numberOfRecords() throws SQLException {
		query = "select count(*) as number_of_records from person_payer where person_id = '" + pidcInfo.getPersonId()
				+ "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet verifyPharmacyDetail() throws SQLException {
		query = "select * from patient where person_id = '" + pidcInfo.getPersonId() + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet verifyInsuranceForSubscriber(String subscriberFirstName) throws SQLException, IOException {
		query = "select * from person_payer where person_id = (select person_id from person where first_name = '"
				+ subscriberFirstName + "')";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet comparePayerID() throws Exception {
		query = "select * from person_payer where person_id = '" + pidcInfo.getPersonId() + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet deleteInsurance() throws Exception {
		query = "update person_payer set delete_ind = 'Y' where person_id = '" + pidcInfo.getPersonId() + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet getPerson() throws Exception {
		query = "select top 1 * from person where first_name like 'subscriberRon%' order by create_timestamp desc";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet validateGuarantor() throws Exception {
		query = "select * from patient where person_id = '" + pidcInfo.getPersonId() + "'";
		return executeQuery(query);
	}

	public ResultSet getPersonId() throws Exception {
		query = "select * from person where first_name = '" + pidcInfo.getPatientFirstName() + "'";
		return executeQuery(query);
	}

	public ResultSet getAgentStatus(String agentName) throws SQLException {
		query = "select * from [" + ConstantConfigData.MFDB + "].[dbo].[agent_solution_config] a1 join [" + ConstantConfigData.MFDB
				+ "].[dbo].[agent] a2 on a1.agent_id = a2.id where a1.practice_id = 4 and a2.[name] = '" + agentName
				+ "'";
		rs = executeQuery(query);
		TestBase.log.info("Get agent status with query: " + query);
		return rs;
	}

	public void updateStatusOfAgent( int isassociated, int isactive, String status, String agentName)
			throws SQLException {
		query = "update [" +  ConstantConfigData.MFDB + "].[dbo].[agent_solution_config] set isassociated = " + isassociated
				+ ", isactive = " + isactive + " , [status]='" + status + "' from [" +  ConstantConfigData.MFDB
				+ "].[dbo].[agent_solution_config] a1 join [" +  ConstantConfigData.MFDB + "].[dbo].[agent] a2 on a1.agent_id = a2.id "
				+ "where a1.practice_id = 4 and a2.[name] = '" + agentName + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update auto invite schedule indicator status with query: " + query);
	}

	public ResultSet getUnreadMessageNotificationAgentStatus(String mfDB) throws SQLException {
		query = "select * from [" + mfDB + "].[dbo].[agent_solution_config] where practice_id=4  and agent_id=14";
		rs = executeQuery(query);
		TestBase.log.info("Get ReadReceipt Agent status with query: " + query);
		return rs;
	}

	public ResultSet getUnreadNotificationWhenCheckboxDisable(String comm_id) throws SQLException {
		query = "select * from ngweb_communications where comm_id = '" + comm_id + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get Unread Message functionality with query: " + query);
		return rs;
	}

	public ResultSet getEncounterIdForPerson(String personId) throws SQLException {
		query = "select TOP 1 enc_id from patient_encounter where person_id='" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);

		return rs;
	}

	public String getPersonIdForActivatedPatient() throws SQLException {
		String personId = "";
		query = "select TOP 1 person.person_id, person.last_name, person.first_name, person.email_address,person.create_timestamp, pxp_enrollments.person_id, pxp_enrollments.enrollment_status, pxp_enrollments.processing_status from person join pxp_enrollments on person.person_id=pxp_enrollments.person_id where enrollment_status = '9' and processing_status = '4' and  first_name like'Ron%' order by create_timestamp desc";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		while (rs.next()) {

			personId = rs.getString("person_id");
			TestBase.log.info("PersonId is : " + personId);

		}
		return personId;
	}

	public String getFirstNameForActivatedPatient(String personId) throws SQLException {
		String firstName = "";
		query = "select first_name from person where person_id='" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		while (rs.next()) {

			firstName = rs.getString("first_name");
			TestBase.log.info("first_name is : " + firstName);

		}
		return firstName;
	}

	public String getLastNameForActivatedPatient(String personId) throws Exception {
		String lastName = "";
		query = "select last_name from person where person_id='" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		while (rs.next()) {
			lastName = rs.getString("last_name");
			TestBase.log.info("last_name is : " + lastName);
		}
		return lastName;
	}

	public void setEnterpriseChartAgentFlipOFF(String practiceId, String personId) throws SQLException {
		query = "update [ngqa_59ud5_medfusionSIT].[dbo].[patient_enterprise_chart] set enterprise_chart_ind='N' where person_id ='"
				+ personId + "' and enterprise_id = '" + practiceId + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update patient Enterprise Chart indicator status with query: " + query);
	}

	public void setEnterpriseChartAgentFlipON(String practiceId, String personId) throws SQLException {
		query = "update [ngqa_59ud5_medfusionSIT].[dbo].[patient_enterprise_chart] set enterprise_chart_ind='Y' where person_id ='"
				+ personId + "' and enterprise_id = '" + practiceId + "'";
		;
		executeUpdateQuery(query);
		TestBase.log.info("Update patient Enterprise Chart indicator status with query: " + query);
	}

	public ResultSet getSTMPUpdatesAgentStatus(String mfDb) throws SQLException {
		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=25";
		rs = executeQuery(query);
		TestBase.log.info("Get Statement Preferences Updates Agent status with query: " + query);
		return rs;
	}

	public ResultSet getHQMAgentStatus(String mfDb) throws SQLException {
		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=6";
		rs = executeQuery(query);
		TestBase.log.info("Get Health Quality Measure Events Agent status with query: " + query);
		return rs;
	}

	public ResultSet getHFUpdatesAgentStatus(String mfDb) throws SQLException {
		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=38";
		rs = executeQuery(query);
		TestBase.log.info("Get Health Quality Measure Events Agent status with query: " + query);
		return rs;
	}

	public ResultSet getActivatedPatientDetailsForBulk(int noOfPatients) throws SQLException {
		query = "select top " + noOfPatients
				+ " * from person p1 left outer join pxp_enrollments p2 on p1.person_id = p2.person_id "
				+ "where enrollment_status = '9' and processing_status ='4' and p1.first_name like 'Ron%' order by p1.create_timestamp desc";
		rs = executeQuery(query);
		TestBase.log.info("Get activated patient details with query: " + query);
		return rs;
	}

	public ResultSet getFirstLastName(String personId, int no) throws SQLException {
		query = "select * from person where person_id = '" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get First & Last Name : " + query);
		return rs;
	}

	public ResultSet getReadReceipt(String parentId, String subject) throws SQLException {
		query = "select * from ngweb_communications where parent_id = '" + parentId + "'and  [subject] like '" + subject
				+ "%'";
		rs = executeQuery(query);
		return rs;
	}

	public int getReceivedMessageDetails(String commid, String subject) throws SQLException {
		query = "select * from [ngweb_communications] where comm_id = '" + commid + "' and  [subject] like '%" + subject
				+ "%'";
		rs = executeQuery(query);
		int value = 0;
		while (rs.next()) {
			value = rs.getInt("sender_type");
		}
		TestBase.log.info("Getting sender_type column value with query: " + query);
		return value;
	}

	public ResultSet getUnreadMessage(String comm_id, String Sub) throws SQLException {
		query = "select * from ngweb_communications where parent_id = '" + comm_id + "' and subject = '" + Sub + "'";
		rs = executeQuery(query);
		return rs;
	}

	public void updateUnreadNotificationCheckboxDisable(String comm_id) {
		query = "update [ngweb_communications] set unread_notify_flag='N',unread_notif_interval= 0 where  comm_id = '"
				+ comm_id + "'";
	}

	public void updateSecureMessageForUnread(String comm_id) throws SQLException {
		query = "update [ngweb_communications] set unread_notify_flag='Y',unread_notif_interval= 1 where  comm_id = '"
				+ comm_id + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Update a unread message notification for message successfully with query: " + query);
	}

	public String getAgentStatusFromDb(String agentName) throws SQLException {
		String status = "";
		ResultSet rs = getAgentStatus(agentName);
		while (rs.next()) {
			status = rs.getString("status");
			TestBase.log.info("Agent status is: " + status);
			break;
		}
		return status;
	}

	public void updateAgentDisable( String agentName) {
		try {
			String agentStatus = getAgentStatusFromDb(agentName);
			if (agentStatus.equalsIgnoreCase("RUNNING")) {
				updateStatusOfAgent(0, 0, "STOP", agentName);
				TestBase.log.info("======================Query executed successfully for " + agentName
						+ " agent disable======================");
			} else if (agentStatus.equalsIgnoreCase("STOP")) {
				TestBase.log.info("=====================" + agentName + " agent is disable========================");
			}
		} catch (Exception e) {
			TestBase.log.info(e.getMessage());
		}
	}

	public void updateAgentEnable(String agentName) {
		try {
			String agentStatus = getAgentStatusFromDb( agentName);
			if (agentStatus.equalsIgnoreCase("STOP")) {
				updateStatusOfAgent( 1, 1, "RUNNING", agentName);
				TestBase.log.info("======================Query executed successfully to run " + agentName
						+ " agent ======================");
			} else if (agentStatus.equalsIgnoreCase("RUNNING")) {

				TestBase.log.info("=====================" + agentName + " agent is enable========================");
			}
		} catch (Exception e) {
			TestBase.log.info(e.getMessage());
		}
	}

	public ResultSet getPatientDocumentStatus(String mfDb) throws SQLException {
		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=15";
		rs = executeQuery(query);
		TestBase.log.info("Get Patient Document Agent status with query: " + query);
		return rs;
	}

	public String getPersonIdWithAppointmentBooked() throws SQLException {
		String personId = "";
		query = "select TOP 1 person.person_id, person.last_name, person.first_name, person.email_address,person.create_timestamp, pxp_enrollments.person_id, pxp_enrollments.enrollment_status, pxp_enrollments.processing_status from person join pxp_enrollments on person.person_id=pxp_enrollments.person_id where enrollment_status = '9' and processing_status = '4' and  first_name like'Ron%' order by create_timestamp desc";
		rs = executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		while (rs.next()) {
			personId = rs.getString("person_id");
			TestBase.log.info("PersonId is : " + personId);
		}
		return personId;
	}

	public ResultSet getInsuranceStatusPreCheck(String personId) throws SQLException {
		query = "select * from person_payer where person_id = '" + personId + "'";
		rs = executeQuery(query);
		TestBase.log.info("Get insurance name with query: " + query);
		return rs;
	}

	public ResultSet numberOfRecord(String personId) throws SQLException {
		query = "select count(*) as number_of_records from person_payer where person_id = '" + personId + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet verifyPharmacy(String personId) throws SQLException {
		query = "select * from patient where person_id = '" + personId + "'";
		rs = executeQuery(query);
		return rs;
	}

	public void createPharmacyForPatient(String personId, String firstName) throws SQLException {
		query = "update patient set pharmacy_code_1_id = '97994F28-ADC4-4C6B-8B96-F4DE30F1B6B8' where person_id = '"
				+ personId + "'";
		executeUpdateQuery(query);
		TestBase.log.info("Pharmacy has been successfully added for the patient " + firstName);
	}

	public ResultSet comparePayerIDS(String personId) throws Exception {
		query = "select * from person_payer where person_id = '" + personId + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet comparesPayerID(String personId) throws Exception {
		query = "select * from person_payer where person_id = '" + pidcInfo.getPersonId() + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet deleteInsurances(String personId) throws Exception {
		query = "update person_payer set delete_ind = 'Y' where person_id = '" + personId + "'";
		rs = executeQuery(query);
		return rs;
	}

	public ResultSet getPersonIds(String firstname) throws Exception {
		query = "select * from person where first_name = '" + firstname + "'";
		return executeQuery(query);
	}

	public ResultSet getIPFUpdatesAgentStatus(String mfDb) throws SQLException {
		query = "select * from [" + mfDb + "].[dbo].[agent_solution_config] where id=44";
		rs = executeQuery(query);
		TestBase.log.info("Get Health Quality Measure Events Agent status with query: " + query);
		return rs;
	}
	
	public String getPersonId(String firstName) throws Exception {
		String personId=" ";
		query = "select * from person where first_name = '" + firstName+ "'";
		 rs=executeQuery(query);
		TestBase.log.info("Get patient details with query: " + query);
		while (rs.next()) {
			personId = rs.getString("person_id");
			TestBase.log.info("PersonId is : " + personId);
		}
		return personId;
	}

	public String getEmail(String personId) throws SQLException {
		String email = "";
		query = "select email_address from person where person_id = '" + personId+ "'";
		rs=executeQuery(query);
		TestBase.log.info("Get patient Email with query: " + query);
		while (rs.next()) {
			email = rs.getString("email_address");
			TestBase.log.info("Email is : " + email);
		}
		return email;
	}
}
