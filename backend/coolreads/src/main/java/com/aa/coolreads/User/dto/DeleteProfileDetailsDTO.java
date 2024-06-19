package com.aa.coolreads.User.dto;

import java.util.Date;

public class DeleteProfileDetailsDTO {

    private Boolean deleteName;

    private String deleteGender;

    private String deletePronouns;

    private Date deleteBirthDate;

    private String deleteCountry;

    private String deleteDescription;

    private String deleteInterests;

    private String deleteProfileImageUrl;

    private String deleteProfileBannerUrl;

    private BookShelfDTO deleteHighlightedBookshelf;

    public DeleteProfileDetailsDTO(Boolean deleteName, String deleteGender, String deletePronouns, Date deleteBirthDate, String deleteCountry, String deleteDescription, String deleteInterests, String deleteProfileImageUrl, String deleteProfileBannerUrl, BookShelfDTO deleteHighlightedBookshelf) {
        this.deleteName = deleteName;
        this.deleteGender = deleteGender;
        this.deletePronouns = deletePronouns;
        this.deleteBirthDate = deleteBirthDate;
        this.deleteCountry = deleteCountry;
        this.deleteDescription = deleteDescription;
        this.deleteInterests = deleteInterests;
        this.deleteProfileImageUrl = deleteProfileImageUrl;
        this.deleteProfileBannerUrl = deleteProfileBannerUrl;
        this.deleteHighlightedBookshelf = deleteHighlightedBookshelf;
    }

    public Boolean getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(Boolean deleteName) {
        this.deleteName = deleteName;
    }

    public String getDeleteGender() {
        return deleteGender;
    }

    public void setDeleteGender(String deleteGender) {
        this.deleteGender = deleteGender;
    }

    public String getDeletePronouns() {
        return deletePronouns;
    }

    public void setDeletePronouns(String deletePronouns) {
        this.deletePronouns = deletePronouns;
    }

    public Date getDeleteBirthDate() {
        return deleteBirthDate;
    }

    public void setDeleteBirthDate(Date deleteBirthDate) {
        this.deleteBirthDate = deleteBirthDate;
    }

    public String getDeleteCountry() {
        return deleteCountry;
    }

    public void setDeleteCountry(String deleteCountry) {
        this.deleteCountry = deleteCountry;
    }

    public String getDeleteDescription() {
        return deleteDescription;
    }

    public void setDeleteDescription(String deleteDescription) {
        this.deleteDescription = deleteDescription;
    }

    public String getDeleteInterests() {
        return deleteInterests;
    }

    public void setDeleteInterests(String deleteInterests) {
        this.deleteInterests = deleteInterests;
    }

    public String getDeleteProfileImageUrl() {
        return deleteProfileImageUrl;
    }

    public void setDeleteProfileImageUrl(String deleteProfileImageUrl) {
        this.deleteProfileImageUrl = deleteProfileImageUrl;
    }

    public String getDeleteProfileBannerUrl() {
        return deleteProfileBannerUrl;
    }

    public void setDeleteProfileBannerUrl(String deleteProfileBannerUrl) {
        this.deleteProfileBannerUrl = deleteProfileBannerUrl;
    }

    public BookShelfDTO getDeleteHighlightedBookshelf() {
        return deleteHighlightedBookshelf;
    }

    public void setDeleteHighlightedBookshelf(BookShelfDTO deleteHighlightedBookshelf) {
        this.deleteHighlightedBookshelf = deleteHighlightedBookshelf;
    }
}
