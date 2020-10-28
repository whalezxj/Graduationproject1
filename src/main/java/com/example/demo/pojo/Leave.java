package com.example.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel("请假表单")
public class Leave   extends BaseModel{

    private static final long serialVersionUID = 1L;

    //请假单ID
    @ApiModelProperty("leave_id")
	private String leaveId;

	//创建请假单时间
	@ApiModelProperty("createtime")
	private Date createtime;
	//请假类型
	@ApiModelProperty("leave_type")
	private String leaveType;
	//开始时间
	@ApiModelProperty("starttime")
	private Date starttime;
	//结束时间
	@ApiModelProperty("endtime")
	private Date endtime;
	//请假事由
	@ApiModelProperty("leave_reason")
	private String leaveReason;
	//请假人id
	@ApiModelProperty("staff_id")
	private String staffId;
	//请假人姓名
	@ApiModelProperty("staff_name")
	private String staffName;
	//审批状态
	@ApiModelProperty("leave_status")
	private String leaveStatus;

}
