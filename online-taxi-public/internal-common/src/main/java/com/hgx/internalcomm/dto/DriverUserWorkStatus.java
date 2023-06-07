package com.hgx.internalcomm.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机工作状态表
 * </p>
 *
 * @author huogaoxu
 * @since 2023-06-04
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long driverId;

    /**
     * 收车：0；出车：1，暂停：2
     */
    private Integer workStatus;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}
