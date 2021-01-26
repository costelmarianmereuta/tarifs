package com.marian.tennis.api.tarifs.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {


    public static final String TARIF_NOT_FOUND = "tarif not found with this name";
    public static final String INVALID_DATE = "The End date should be after the starting date";
    public static final String TARIF_REMOVED = "tarif removed with name : ";
}
