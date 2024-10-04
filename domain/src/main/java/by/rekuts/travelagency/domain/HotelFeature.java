package by.rekuts.travelagency.domain;

public enum HotelFeature{
    A("swimming pool"),
    B("all inclusive"),
    C("fresh towel"),
    D("carpets on the walls"),
    E("free wifi"),
    F("close to sea"),
    G("free animators"),
    H("free beer"),
    I("air conditioning"),
    J("children room");

    private final String feature;

    HotelFeature(String feature) {
        this.feature = feature;
    }

    public String getValue() {
        return feature;
    }
}
