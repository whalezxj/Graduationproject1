package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Leave;
import com.example.demo.pojo.model.OperationArgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LeaveMapper  extends BaseMapper<Leave> {


    @Insert("insert into leaves l(l.leave_id, l.leave_type, l.starttime,l.endtime, l.reason, l.staff_id,l.staff_name, l.leave_status, l.createtime )" +
            " values (#{leave.leaveId}, #{leave.leaveType}, #{leave.starttime},#{leave.endtime}, #{leave.reason}, #{leave.staffId},#{leave.staffName}, #{leave.leaveStatus}, #{leave.createtime})")
    void addLeave(@Param("leave") Leave leave);

    @Select("select * from leaves")
    List<Leave> queryLeave();

    @Update("update  leaves l  set l.leave_status = #{args.leavStatus} where l.leave_id = #{args.leaveId} ")
    void operationLeave(@Param("args") OperationArgs args);

    @Select("select *  from leaves where staff_id = #{staffId}")
    List<Leave> getLeave(@Param("staffId") String staffId);
}
