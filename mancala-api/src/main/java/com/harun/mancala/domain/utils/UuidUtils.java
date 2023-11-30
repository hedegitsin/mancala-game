package com.harun.mancala.domain.utils;

import com.harun.mancala.domain.exception.InvalidUuidException;

import java.util.UUID;

public class UuidUtils {

    public static UUID fromString(String id, String exceptionMessage) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException(exceptionMessage);
        }
    }
}
