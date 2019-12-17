package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:teacher_files_db
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-6下午1:45
 * @Describe:
 **/
@Entity
@Table(name = "teacher_honor", schema = "teacher_files", catalog = "")
public class TeacherHonorEntity {
    private long honorId;
    private String honorName;
    private String grantUnit;
    private String memberCode;
    private String memberName;
    private String honorTime;
    private Long deptId;//保存添加用户的部门id
    private TeacherEntity itemPerson;  //项目负责人；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeGradeEntity prizeGrade;  //等次：单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeLevelEntity prizeLevel;  //级别：单向一对一，可以从关联的一方去查询另一方，却不能反向查询

    @Id
    @Column(name = "honor_id")
    public long getHonorId() {
        return honorId;
    }
    public void setHonorId(long honorId) {
        this.honorId = honorId;
    }

    @Basic
    @Column(name = "honor_name")
    public String getHonorName() {
        return honorName;
    }
    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    @Basic
    @Column(name = "grant_unit")
    public String getGrantUnit() {
        return grantUnit;
    }
    public void setGrantUnit(String grantUnit) {
        this.grantUnit = grantUnit;
    }

    @Basic
    @Column(name = "member_code")
    public String getMemberCode() {
        return memberCode;
    }
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    @Basic
    @Column(name = "member_name")
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Basic
    @Column(name = "honor_time")
    public String getHonorTime() {
        return honorTime;
    }
    public void setHonorTime(String honorTime) {
        this.honorTime = honorTime;
    }

    @Basic
    @Column(name = "dept_id", nullable = true)
    public Long getDeptId() {
        return deptId;
    }
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherHonorEntity that = (TeacherHonorEntity) o;
        return honorId == that.honorId &&
                Objects.equals(honorName, that.honorName) &&
                Objects.equals(grantUnit, that.grantUnit) &&
                Objects.equals(memberCode, that.memberCode) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(honorTime, that.honorTime) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(honorId, honorName, grantUnit, memberCode, memberName, honorTime, deptId);
    }

    /**
     * 这样子就可以实现单向一对一了，对方实体中什么都不用该，因为只是单向的
     */
    @OneToOne
    @JoinColumn(name = "item_person")
    public TeacherEntity getItemPerson() {
        return itemPerson;
    }

    public void setItemPerson(TeacherEntity itemPerson) {
        this.itemPerson = itemPerson;
    }

    @OneToOne
    @JoinColumn(name = "prize_grade_id")
    public CompetitionPrizeGradeEntity getPrizeGrade() {
        return prizeGrade;
    }

    public void setPrizeGrade(CompetitionPrizeGradeEntity prizeGrade) {
        this.prizeGrade = prizeGrade;
    }

    @OneToOne
    @JoinColumn(name = "prize_level_id")
    public CompetitionPrizeLevelEntity getPrizeLevel() {
        return prizeLevel;
    }

    public void setPrizeLevel(CompetitionPrizeLevelEntity prizeLevel) {
        this.prizeLevel = prizeLevel;
    }
}
