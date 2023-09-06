//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

public class PIDCInfo extends PatientRandomDetails {

    private static String fN = getFirst_name();
    private static String lN = getLast_name();
    private static String patientPersonId = "";
    private static String patientFirstName = "";
    private static String patientLastName = "";
    private static String patientAdd1 = "";
    private static String patientAdd2 = "";
    private static String patientEmail = "";
    private static String patientUpdatedEmail = getUpdatedEmail();
    private static String updatedEmailAddress = "";
    private static String updatedDateOfBirth = "";
    private static int updatedZip = 0;
    private static String personId = "";
    private static String emailAddress = "";
    private static String inactivePatientFirstName = "";
    private static String inactivePatientLastName = "";
    private static int dob = 0;
    private static int zipCode = 0;
    private static String cityName = "";
    private static String state = "";
    private static String address1 = "";
    private static String address2 = "";
    private static String newAddress1 = "";
    private static String newAddress2 = "";
    private static String newCity = "";
    private static String city = "California";
    private static String updatedAdd1 = updateAdd1();
    private static String updatedAdd2 = updateAdd2();
    private static String newDob = PatientRandomDetails.updateDateOfBirth();
    private static int newZipCode = PatientRandomDetails.newZipcode();
    private static String autoInviteState = "";
    private static String stateForAge = "";

    public String getfN() {
        return fN;
    }

    public void setfN(String fN) {
        this.fN = fN;
    }

    public String getlN() {
        return lN;
    }

    public void setlN(String lN) {
        this.lN = lN;
    }

    public String getPatientPersonId() {
        return patientPersonId;
    }

    public void setPatientPersonId(String patientsPersonId) {
        patientPersonId = patientsPersonId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientAdd1() {
        return patientAdd1;
    }

    public void setPatientAdd1(String patientAdd1) {
        this.patientAdd1 = patientAdd1;
    }

    public String getPatientAdd2() {
        return patientAdd2;
    }

    public void setPatientAdd2(String patientAdd2) {
        this.patientAdd2 = patientAdd2;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientUpdatedEmail() {
        return patientUpdatedEmail;
    }

    public void setPatientUpdatedEmail(String patientUpdatedEmail) {
        this.patientUpdatedEmail = patientUpdatedEmail;
    }

    public String getUpdatedEmailAddress() {
        return updatedEmailAddress;
    }

    public void setUpdatedEmailAddress(String updatedEmailAddress) {
        this.updatedEmailAddress = updatedEmailAddress;
    }

    public String getUpdatedDateOfBirth() {
        return updatedDateOfBirth;
    }

    public void setUpdatedDateOfBirth(String updatedDateOfBirth) {
        this.updatedDateOfBirth = updatedDateOfBirth;
    }

    public int getUpdatedZip() {
        return updatedZip;
    }

    public void setUpdatedZip(int updatedZip) {
        this.updatedZip = updatedZip;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getInactivePatientFirstName() {
        return inactivePatientFirstName;
    }

    public void setInactivePatientFirstName(String inactivePatientFirstName) {
        this.inactivePatientFirstName = inactivePatientFirstName;
    }

    public String getInactivePatientLastName() {
        return inactivePatientLastName;
    }

    public void setInactivePatientLastName(String inactivePatientLastName) {
        this.inactivePatientLastName = inactivePatientLastName;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getNewAddress1() {
        return newAddress1;
    }

    public void setNewAddress1(String newAddress1) {
        this.newAddress1 = newAddress1;
    }

    public String getNewAddress2() {
        return newAddress2;
    }

    public void setNewAddress2(String newAddress2) {
        this.newAddress2 = newAddress2;
    }

    public String getNewCity() {
        return newCity;
    }

    public void setNewCity(String newCity) {
        this.newCity = newCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdatedAdd1() {
        return updatedAdd1;
    }

    public void setUpdatedAdd1(String updatedAdd1) {
        this.updatedAdd1 = updatedAdd1;
    }

    public String getUpdatedAdd2() {
        return updatedAdd2;
    }

    public void setUpdatedAdd2(String updatedAdd2) {
        this.updatedAdd2 = updatedAdd2;
    }

    public String getNewDob() {
        return newDob;
    }

    public void setNewDob(String newDob) {
        this.newDob = newDob;
    }

    public int getNewZipCode() {
        return newZipCode;
    }

    public void setNewZipCode(int newZipCode) {
        this.newZipCode = newZipCode;
    }

    public String getAutoInviteState() {
        return autoInviteState;
    }

    public void setAutoInviteState(String autoInviteState) {
        PIDCInfo.autoInviteState = autoInviteState;
    }

    public String getStateForAge() {
        return stateForAge;
    }

    public void setStateForAge(String stateForAge) {
        PIDCInfo.stateForAge = stateForAge;
    }
}
