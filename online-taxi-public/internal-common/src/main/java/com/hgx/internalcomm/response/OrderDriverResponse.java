package com.hgx.internalcomm.response;

import lombok.Data;

@Data
public class OrderDriverResponse {

    private Long driverId;

    private String driverPhone;

    private Long carId;
}