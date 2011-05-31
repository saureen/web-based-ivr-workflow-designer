/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.velocity.ivr.asterisk;

/**
 *
 * @author swoosh
 */
public class IvrConfig {
    private String welcome;
    private String langMenu;
    private String invalidSelection;
    private String exceededTries;
    private String instructions;
    private String recordInstructions;
    private String recordError;
    private String thankNote;
    private String callIdQueryInstru;
    private String sysErr;
    private String notFound;
    private String statusIs;

    private String recordFolder;
    private String recordFile;
    private String recordFormat;
    private String recordEscape;
    private String notifyTo;
    private String mailTemplate;
    private String mailSubject;
    private int maxTries;
    private long inputTimeOut;

    public String getCallIdQueryInstru() {
        return callIdQueryInstru;
    }

    public void setCallIdQueryInstru(String callIdQueryInstru) {
        this.callIdQueryInstru = callIdQueryInstru;
    }

    public String getExceededTries() {
        return exceededTries;
    }

    public void setExceededTries(String exceededTries) {
        this.exceededTries = exceededTries;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInvalidSelection() {
        return invalidSelection;
    }

    public void setInvalidSelection(String invalidSelection) {
        this.invalidSelection = invalidSelection;
    }

    public String getLangMenu() {
        return langMenu;
    }

    public void setLangMenu(String langMenu) {
        this.langMenu = langMenu;
    }

    public String getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
    }

    public String getNotFound() {
        return notFound;
    }

    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }

    public String getNotifyTo() {
        return notifyTo;
    }

    public void setNotifyTo(String notifyTo) {
        this.notifyTo = notifyTo;
    }

    public String getRecordError() {
        return recordError;
    }

    public void setRecordError(String recordError) {
        this.recordError = recordError;
    }

    public String getRecordEscape() {
        return recordEscape;
    }

    public void setRecordEscape(String recordEscape) {
        this.recordEscape = recordEscape;
    }

    public String getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(String recordFile) {
        this.recordFile = recordFile;
    }

    public String getRecordFolder() {
        return recordFolder;
    }

    public void setRecordFolder(String recordFolder) {
        this.recordFolder = recordFolder;
    }

    public String getRecordFormat() {
        return recordFormat;
    }

    public void setRecordFormat(String recordFormat) {
        this.recordFormat = recordFormat;
    }

    public String getRecordInstructions() {
        return recordInstructions;
    }

    public void setRecordInstructions(String recordInstructions) {
        this.recordInstructions = recordInstructions;
    }

    public String getStatusIs() {
        return statusIs;
    }

    public void setStatusIs(String statusIs) {
        this.statusIs = statusIs;
    }

    public String getSysErr() {
        return sysErr;
    }

    public void setSysErr(String sysErr) {
        this.sysErr = sysErr;
    }

    public String getThankNote() {
        return thankNote;
    }

    public void setThankNote(String thankNote) {
        this.thankNote = thankNote;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public long getInputTimeOut() {
        return inputTimeOut;
    }

    public void setInputTimeOut(long inputTimeOut) {
        this.inputTimeOut = inputTimeOut;
    }
}
