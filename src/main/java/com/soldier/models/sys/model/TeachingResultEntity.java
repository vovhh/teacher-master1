package com.soldier.models.sys.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName:teacher_files_db
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-5上午8:44
 * @Describe:
 **/
@Entity
@Table(name = "teaching_result", schema = "teacher_files", catalog = "")
public class TeachingResultEntity {
    private Long resultId;
    private String resultName;
    private String memberCode;
    private String memberName;
    private String resultTime;
    private Long deptId;//保存添加用户的部门id
    private TeacherEntity itemPerson;  //项目负责人；单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeGradeEntity prizeGrade;  //等次：单向一对一，可以从关联的一方去查询另一方，却不能反向查询
    private CompetitionPrizeLevelEntity prizeLevel;  //级别：单向一对一，可以从关联的一方去查询另一方，却不能反向查询

    @Id
    @Column(name = "result_id")
    public Long getResultId() {
        return resultId;
    }
    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    @Basic
    @Column(name = "result_name")
    public String getResultName() {
        return resultName;
    }
    public void setResultName(String resultName) {
        this.resultName = resultName;
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
    @Column(name = "result_time")
    public String getResultTime() {
        return resultTime;
    }
    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
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
        TeachingResultEntity that = (TeachingResultEntity) o;
        return resultId == that.resultId &&
                Objects.equals(resultName, that.resultName) &&
                Objects.equals(memberCode, that.memberCode) &&
                Objects.equals(memberName, that.memberName) &&
                Objects.equals(prizeLevel, that.prizeLevel) &&
                Objects.equals(prizeGrade, that.prizeGrade) &&
                Objects.equals(resultTime, that.resultTime) &&
                Objects.equals(deptId, that.deptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultId, resultName, memberCode, memberName, prizeLevel, prizeGrade, resultTime, deptId);
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
