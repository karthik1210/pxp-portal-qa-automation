package com.pxp.objectmaps;

import com.medfusion.common.utils.PropertyFileLoader;
import com.pxp.base.ConstantConfigData;
import com.pxp.base.IntegrationDb;
import com.pxp.base.TestBase;
import com.pxp.queries.PIDCQueries;
import com.pxp.util.DBUtils;
import com.pxp.util.PIDCInfo;
import com.pxp.util.PatientRandomDetails;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;

public class PatientCreationAndActivationPage extends TestBase {

    static ResultSet rs = null;
    PIDCQueries dbQuerries = new PIDCQueries();
    PropertyFileLoader testData;
    PIDCInfo pidcInfo = new PIDCInfo();
    private ConstantConfigData data = new ConstantConfigData();
    String firstname = null;
    String lastname = null;
    String emailid = null;
    String userName = firstname + "." + lastname;
    String apptId = "";
    String apptIds = "";

    public PatientCreationAndActivationPage() throws IOException {
        testData = new PropertyFileLoader();
        integrationDb = new IntegrationDb(testData);
    }

    public String createPatient(String practiceId, String firstName, String lastName, int zip,
                                int dob, String email, String state) throws Exception {
        String patientPersonId = "";
        String add1 = "123";
        String add2 = "New Street";
        CallableStatement cp = DBUtils.ngConnection
                .prepareCall("{call [dbo].[ng_add_patient](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cp.setInt("@po_result_code", 0);
        cp.setString("@po_result_message", null);
        cp.setString("@pi_enterprise_id", "00001");
        cp.setString("@pi_practice_id", practiceId);
        cp.setString("@pio_person_id", null);
        cp.setString("@pi_last_name", lastName);
        cp.setString("@pi_first_name", firstName);
        cp.setInt("@pi_user_id", 007);
        cp.setString("@pi_add_patient_only_ind", "N");
        cp.setInt("@pi_create_timestamp_tz", 0);
        cp.setString("@pi_site_id", null);
        cp.setString("@pi_middle_name", null);
        cp.setString("@pi_prior_last_name", null);
        cp.setString("@pi_address_line_1", add1);
        cp.setString("@pi_address_line_2", add2);
        cp.setString("@pi_city", "Alaska");
        cp.setString("@pi_state", state);
        cp.setInt("@pi_zip", zip);
        cp.setString("@pi_country_id", null);
        cp.setString("@pi_county_id", null);
        cp.setString("@pi_country", null);
        cp.setString("@pi_county", null);
        cp.setString("@pi_home_phone", null);
        cp.setString("@pi_sec_address_line_1", null);
        cp.setString("@pi_sec_address_line_2", null);
        cp.setString("@pi_sec_city", null);
        cp.setString("@pi_sec_state", null);
        cp.setString("@pi_sec_zip", null);
        cp.setString("@pi_sec_country_id", null);
        cp.setString("@pi_sec_county_id", null);
        cp.setString("@pi_sec_country", null);
        cp.setString("@pi_sec_county", null);
        cp.setString("@pi_sec_home_phone", null);
        cp.setString("@pi_day_phone", null);
        cp.setString("@pi_day_phone_ext", null);
        cp.setString("@pi_alt_phone", null);
        cp.setString("@pi_alt_phone_desc", null);
        cp.setString("@pi_alt_phone_ext", null);
        cp.setInt("@pi_date_of_birth", dob);
        cp.setString("@pi_sex", "M");
        cp.setString("@pi_ssn", null);
        cp.setString("@pi_marital_status", null);
        cp.setString("@pi_expired_date", null);
        cp.setString("@pi_address_type", null);
        cp.setString("@pi_address_type_id", null);
        cp.setString("@pi_expired_ind", "N");
        cp.setString("@pi_smoker_ind", "N");
        cp.setString("@pi_email_ind", "N");
        cp.setString("@pi_phone_ind", "N");
        cp.setString("@pi_portal_ind", "N");
        cp.setString("@pi_sms_ind", "N");
        cp.setString("@pi_voice_ind", "N");
        cp.setString("@pi_optout_ind", "N");
        cp.setString("@pi_notific_pref_ind", "N");
        cp.setString("@pi_veteran_ind", "N");
        cp.setString("@pi_email_address", email);
        cp.setString("@pi_cell_phone", null);
        cp.setString("@pi_cell_phone_comment", null);
        cp.setString("@pi_race_id", null);
        cp.setString("@pi_language_id", null);
        cp.setString("@pi_religion_id", null);
        cp.setString("@pi_church_id", null);
        cp.setString("@pi_language", null);
        cp.setString("@pi_religion", null);
        cp.setString("@pi_church", null);
        cp.setString("@pi_race", null);
        cp.setString("@pi_student_status", null);
        cp.setString("@pi_primarycare_prov_id", null);
        cp.setString("@pi_primarycare_prov_name", null);
        cp.setString("@pi_image_id", null);
        cp.setString("@pi_external_id", null);
        cp.setString("@pi_other_id_number", null);
        cp.setString("@pi_int_home_phone", null);
        cp.setString("@pi_int_work_phone", null);
        cp.setString("@pi_int_zip", null);
        cp.setString("@pi_nickname", null);
        cp.setString("@pi_first_office_enc_date", null);
        cp.setString("@pi_last_office_enc_date", null);
        cp.setString("@pi_next_office_enc_date", null);
        cp.setString("@pi_pharmacy_code_1_id", null);
        cp.setString("@pi_pharmacy_code_2_id", null);
        cp.setString("@pi_default_location_id", null);
        cp.setString("@pi_privacy_level", null);
        cp.setString("@pi_suppress_billing_ind", "N");
        cp.setString("@pi_preferred_provider_id", null);
        cp.setString("@pi_financial_class", null);
        cp.setString("@pio_med_rec_nbr", null);
        cp.setString("@pi_guar_id", null);
        cp.setString("@pi_guar_type", null);
        cp.setString("@pi_contact_person_id", null);
        cp.setString("@pi_user_defined1", null);
        cp.setString("@pi_user_defined2", null);
        cp.setString("@pi_user_defined3", null);
        cp.setString("@pi_user_defined4", null);
        cp.setString("@pi_print_stmt_ind", "N");
        cp.setString("@pi_last_letter_date", null);
        cp.setString("@pi_rendering_prov_id", null);
        cp.setString("@pi_co_managed_ind", "N");
        cp.setString("@pi_co_managed_prov_id", null);
        cp.setString("@pi_mrkt_plan_id", null);
        cp.setString("@pi_mrkt_source_id", null);
        cp.setString("@pi_mrkt_source_type", null);
        cp.setString("@pi_mrkt_comments", null);
        cp.setString("@pi_mrkt_details", null);
        cp.setString("@pi_referring_prov_id", null);
        cp.setString("@pi_last_print_statement_date", null);
        cp.setString("@pi_privacy_notice_code_id", null);
        cp.setString("@pi_privacy_notice_issued", null);
        cp.setString("@pi_privacy_notice_received", null);
        cp.setString("@pi_privacy_notice_notes", null);
        cp.setString("@pi_uds_homeless_status_id", null);
        cp.setString("@pi_uds_migrant_wkr_status_id", null);
        cp.setString("@pi_uds_language_barrier_id", null);
        cp.setString("@pi_uds_primary_med_coverage_id", null);
        cp.setString("@pi_home_phone_comment", null);
        cp.setString("@pi_day_phone_comment", null);
        cp.setString("@pi_alt_phone_comment", null);
        cp.setString("@pi_sec_home_phone_comment", null);
        cp.setString("@pi_email_address_comment", null);
        cp.setString("@pi_contact_pref_id", null);
        cp.setString("@pi_contact_pref_desc", null);
        cp.setString("@pi_contact_seq", null);
        cp.setString("@pi_contact_alert_ind", null);
        cp.setString("@pi_user_defined5", null);
        cp.setString("@pi_user_defined6", null);
        cp.setString("@pi_user_defined7", null);
        cp.setString("@pi_user_defined8", null);
        cp.setString("@pi_uds_pub_hsng_pri_care_id", null);
        cp.setString("@pi_uds_school_hlth_ctr_id", null);
        cp.setString("@pi_self_pay", null);
        cp.setString("@pi_enterprise_chart_ind", null);
        cp.setString("@pi_uds_race_id", null);
        cp.setString("@pi_uds_ethnicity_id", null);
        cp.setString("@pi_uds_tribal_affiliation_id", null);
        cp.setString("@pi_uds_blood_quantum_id", null);
        cp.setString("@pi_uds_veteran_status", null);
        cp.setString("@pi_uds_consent_to_treat", "N");
        cp.setString("@pi_community_code_id", null);
        cp.setString("@pi_head_of_household_id", null);
        cp.setString("@pi_uds_ihs_elig_status_id", null);
        cp.setString("@pi_uds_tribal_class_id", null);
        cp.setString("@pi_uds_decendancy_id", null);
        cp.setString("@pi_uds_consent_to_treat_date", null);
        cp.setString("@pi_mothers_maiden_name", null);
        cp.setString("@pi_ethnicity_id", null);
        cp.setString("@pi_primary_dental_prov_id", null);
        cp.setString("@pi_primary_dental_prov_name", null);
        cp.setString("@pi_no_active_problems", null);
        cp.setString("@pi_no_unresolved_allergies_ind", null);
        cp.setString("@pi_no_active_medications_ind", null);
        cp.setString("@pi_prefix", null);
        cp.setString("@pi_suffix", null);
        cp.setString("@pi_prefix_id", null);
        cp.setString("@pi_suffix_id", null);
        cp.setString("@pi_cause_of_death", null);
        cp.setString("@pi_cause_of_death_code", null);
        cp.setString("@pi_birth_mothers_lname", null);
        cp.setString("@pi_birth_mothers_fname", null);
        cp.setString("@pi_birth_mothers_mname", null);
        cp.setString("@pi_expired_time", null);
        cp.setString("@pi_expired_time_tz", null);
        cp.setString("@pi_risk_level", null);
        cp.setString("@pi_exempt_frm_person_merge", "N");
        cp.setString("@pi_sexual_orientation", null);
        cp.setString("@pi_preferred_pronoun", null);
        cp.setString("@pi_gender_identity_code", null);
        cp.setString("@pi_enable_home_phone_ind", "N");
        cp.setString("@pi_enable_day_phone_ind", "N");
        cp.setString("@pi_enable_alt_phone_ind", "N");
        cp.setString("@pi_enable_sec_hm_phone_ind", "N");
        cp.setString("@pi_enable_email_address_ind", "N");
        cp.setString("@pi_enable_cell_phone_ind", "N");
        cp.setString("@pi_enable_int_phone_ind", "N");
        cp.setString("@pi_current_gender", "M");
        cp.setString("@pi_prev_first_name", null);
        cp.setString("@pi_other_reason_so", null);
        cp.setString("@pi_other_reason_gi", null);
        cp.setString("@pi_sec_address_type", null);
        cp.setString("@pi_sec_address_type_id", null);
        cp.setString("@pi_Correspondence_lang_id", null);
        cp.setString("@pi_contact_seq_edi", null);
        boolean gotResults = cp.execute();
        rs = null;
        if (!gotResults) {
        } else {
            rs = cp.getResultSet();
        }
        TestBase.log.info("Query executed successfully for creating patient");
        cp.close();
        rs = dbQuerries.getPatientDetails(firstName, lastName);
        while (rs.next()) {
            practiceId = rs.getString("practice_id");
            String enterpriseId = rs.getString("enterprise_id");
            patientPersonId = rs.getString("person_id");
            pidcInfo.setPatientPersonId(patientPersonId);
            String personNbr = rs.getString("person_nbr");
            String createTimeStamp = rs.getString("create_timestamp");
            TestBase.log.info("Creating person for enterpriseId and practiceId " + enterpriseId + ", " + practiceId
                    + " on " + createTimeStamp + " EST");
            TestBase.log.info("Person id of person: " + patientPersonId);
            pidcInfo.setPatientFirstName(rs.getString("first_name"));
            TestBase.log.info("First name is: " + pidcInfo.getPatientFirstName());
            pidcInfo.setPatientLastName(rs.getString("last_name"));
            TestBase.log.info("Last name is: " + pidcInfo.getPatientLastName());
            String emailAdd = rs.getString("email_address");
            TestBase.log.info("Email address is: " + emailAdd);
            pidcInfo.setPatientAdd1(rs.getString("address_line_1"));
            pidcInfo.setPatientAdd2(rs.getString("address_line_2"));
            pidcInfo.setPatientEmail(rs.getString("email_address"));
        }
        return patientPersonId;
    }

    public String createPatient() throws InterruptedException, Exception {
        TestBase.log.info("Create New Patient and Create Appointment");
        firstname = PatientRandomDetails.getFirst_name();
        lastname = PatientRandomDetails.getLast_name();
        emailid = PatientRandomDetails.getEmail();
        String personId = integrationDb.getIntegrationDb().getPatientCreationAndActivationPage().createPatient(data.DEFAULTPRACTICEID, firstname, lastname, data.ZIPCODE,
                data.DOBVAR, emailid, data.STATE);
        return personId;
    }


}
